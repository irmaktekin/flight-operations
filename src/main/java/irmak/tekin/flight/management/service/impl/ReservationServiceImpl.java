package irmak.tekin.flight.management.service.impl;

import irmak.tekin.flight.management.dto.response.ReservationResponseDTO;
import irmak.tekin.flight.management.entity.Flight;
import irmak.tekin.flight.management.entity.Reservation;
import irmak.tekin.flight.management.exception.FlightNotFoundException;
import irmak.tekin.flight.management.exception.PaymentFailedException;
import irmak.tekin.flight.management.exception.SeatAlreadyReservedException;
import irmak.tekin.flight.management.mapper.ReservationMapper;
import irmak.tekin.flight.management.repository.FlightRepository;
import irmak.tekin.flight.management.service.PaymentService;
import irmak.tekin.flight.management.service.ReservationService;
import irmak.tekin.flight.management.dto.request.ReservationRequestDTO;
import irmak.tekin.flight.management.exception.SeatNotFoundException;
import irmak.tekin.flight.management.entity.Seat;
import irmak.tekin.flight.management.repository.ReservationRepository;
import irmak.tekin.flight.management.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private FlightRepository flightRepository;

    public ReservationServiceImpl(FlightRepository flightRepository, SeatRepository seatRepository) {
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional
    @Override
    public ReservationResponseDTO makeReservation(ReservationRequestDTO reservationRequestDTO) {

        Flight flight = flightRepository.findById(reservationRequestDTO.getFlightId())
                .orElseThrow(() -> new FlightNotFoundException(reservationRequestDTO.getFlightId()));

        Seat seat = seatRepository.findById(reservationRequestDTO.getSeatId()).orElseThrow(() -> new SeatNotFoundException(reservationRequestDTO.getSeatId()));

        if (seat.isReserved()) {
            throw new SeatAlreadyReservedException(reservationRequestDTO.getSeatId());
        }
        seat.setReserved(true);

        seatRepository.save(seat);
        Reservation reservation = new Reservation();
        reservation.setFlightId(reservationRequestDTO.getFlightId());
        reservation.setSeatId(reservationRequestDTO.getSeatId());
        reservation.setCustomerName(reservationRequestDTO.getCustomerName());
        reservationRepository.save(reservation);

        log.info("Reservation is created.Reservation ID:{}" ,reservation.getId());
        boolean isPaymentSuccessful = paymentService.getPayment(reservationRequestDTO.getSeatId());
        if (isPaymentSuccessful) {
            seat = seatRepository.findById(reservationRequestDTO.getSeatId())
                    .orElseThrow(() -> new SeatNotFoundException(reservationRequestDTO.getSeatId()));

            if (seat.isReserved()) {
                reservation.setPaid(true);
                reservationRepository.save(reservation);
                log.info("Payment successful. Reservation ID: {} is now paid.", reservation.getId());
            } else {
                throw new SeatAlreadyReservedException(reservationRequestDTO.getSeatId());
            }
        } else {
            throw new PaymentFailedException();
        }

        return new ReservationResponseDTO(reservation.getFlightId(),reservation.getCustomerName());
    }

}
