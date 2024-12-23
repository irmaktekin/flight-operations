package irmak.tekin.flight.management.service.impl;

import irmak.tekin.flight.management.mapper.FlightMapper;
import irmak.tekin.flight.management.service.FlightService;

import irmak.tekin.flight.management.dto.request.FlightCreateRequestDTO;
import irmak.tekin.flight.management.dto.request.FlightUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.FlightResponseDTO;
import irmak.tekin.flight.management.exception.FlightNotFoundException;

import irmak.tekin.flight.management.entity.Flight;
import irmak.tekin.flight.management.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper mapper;

    // Add new flight
    @Override
    public FlightResponseDTO addFlight(FlightCreateRequestDTO flightCreateRequestDTO) {
        Flight flight = new Flight();
        flight.setId(flightCreateRequestDTO.getId());
        flight.setFlightNumber(flightCreateRequestDTO.getFlightNumber());
        flight.setDepartureTerminal(flightCreateRequestDTO.getDepartureTerminal());
        flight.setArrivalTerminal(flightCreateRequestDTO.getArrivalTerminal());
        flight.setDepartureTime((flightCreateRequestDTO.getDepartureTime()));
        flight.setArrivalTime(flightCreateRequestDTO.getArrivalTime());
        flight.setPrice(flightCreateRequestDTO.getPrice());
        log.info("Flight is created.Flight ID:{}" ,flight.getId());
        return mapper.toConvert(flight);

    }

    @Override
    public void cancelFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException(flightId));

        flightRepository.deleteById(flightId);
    }

    @Override
    public FlightResponseDTO updateFlight(Long id, FlightUpdateRequestDTO flightUpdateRequestDTO) {

        Flight flight = getFlightById(id);
        mapper.updateFlight(flight, flightUpdateRequestDTO);
        Flight updatedFlight = flightRepository.save(flight);
        log.info("Flight updated.Flight ID:{}" ,updatedFlight.getId());
        return mapper.toConvert(updatedFlight);

    }

    @Override
    public List<FlightResponseDTO> getAllFlights() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        List<Flight> flights = flightRepository.findAll();
        return flights.stream()
                .map(flight -> new FlightResponseDTO(
                        flight.getId(),
                        flight.getFlightNumber(),
                        flight.getDepartureTerminal(),
                        flight.getArrivalTerminal(),
                        flight.getDepartureTime(),
                        flight.getArrivalTime(),
                        flight.getPrice()
                ))
                .collect(Collectors.toList());
    }

    private Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException(flightId));
    }

}
