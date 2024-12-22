package irmak.tekin.flight.management.service.impl;

import irmak.tekin.flight.management.exception.FlightNotFoundException;
import irmak.tekin.flight.management.service.SeatService;

import irmak.tekin.flight.management.dto.request.SeatCreateRequestDTO;
import irmak.tekin.flight.management.dto.request.SeatUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.SeatResponseDTO;
import irmak.tekin.flight.management.exception.SeatNotFoundException;
import irmak.tekin.flight.management.entity.Flight;
import irmak.tekin.flight.management.entity.Seat;
import irmak.tekin.flight.management.repository.FlightRepository;
import irmak.tekin.flight.management.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final FlightRepository flightRepository;


    @Override
    public SeatResponseDTO addSeat(SeatCreateRequestDTO seatCreateRequestDTO){
        Flight flight = flightRepository.findById(seatCreateRequestDTO.getFlightId())
                .orElseThrow(() -> new FlightNotFoundException(seatCreateRequestDTO.getFlightId()));

        Seat seat = new Seat();
        seat.setSeatNumber(seatCreateRequestDTO.getSeatNumber());
        seat.setFlight(flight);
        seat.setPrice(seatCreateRequestDTO.getPrice());
        Seat savedSeat = seatRepository.save(seat);

        return new SeatResponseDTO(
                savedSeat.getId(),
                savedSeat.getSeatNumber(),
                savedSeat.isReserved(),
                savedSeat.getFlight()
        );

    }
    public void deleteSeat(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new SeatNotFoundException(id));

        seatRepository.deleteById(id);  // Hard delete from the database
    }

    @Override
    public SeatResponseDTO updateSeat(Long id, SeatUpdateRequestDTO seatUpdateRequestDTO) {

        Seat updatedSeat = seatRepository.findById(id)
                .orElseThrow(() -> new SeatNotFoundException(id));

        updatedSeat.setSeatNumber(seatUpdateRequestDTO.getSeatNumber());
        updatedSeat.setPrice(seatUpdateRequestDTO.getPrice());
        updatedSeat = seatRepository.save(updatedSeat);

        return new SeatResponseDTO(updatedSeat.getId(),updatedSeat.getSeatNumber(),updatedSeat.isReserved(),updatedSeat.getFlight());
    }

}
