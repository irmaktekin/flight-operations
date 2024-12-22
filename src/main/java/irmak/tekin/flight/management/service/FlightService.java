package irmak.tekin.flight.management.service;


import irmak.tekin.flight.management.dto.request.FlightCreateRequestDTO;
import irmak.tekin.flight.management.dto.request.FlightUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.FlightResponseDTO;

import java.util.List;

public interface FlightService {
    FlightResponseDTO addFlight(FlightCreateRequestDTO flightCreateRequestDTO);
    void cancelFlight(Long flightId);
    FlightResponseDTO updateFlight(Long id, FlightUpdateRequestDTO flightUpdateRequestDTO);
    List<FlightResponseDTO> getAllFlights();

}
