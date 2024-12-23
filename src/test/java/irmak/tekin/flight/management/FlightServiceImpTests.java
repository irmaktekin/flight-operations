package irmak.tekin.flight.management;

import irmak.tekin.flight.management.dto.request.FlightCreateRequestDTO;
import irmak.tekin.flight.management.dto.request.FlightUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.FlightResponseDTO;
import irmak.tekin.flight.management.entity.Flight;
import irmak.tekin.flight.management.mapper.FlightMapper;
import irmak.tekin.flight.management.repository.FlightRepository;
import irmak.tekin.flight.management.service.impl.FlightServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImpTests {
    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightMapper flightMapper;

    @InjectMocks
    private FlightServiceImpl flightService;

    private Flight flight;
    private FlightCreateRequestDTO createRequestDTO;
    private FlightUpdateRequestDTO updateRequestDTO;
    private FlightResponseDTO flightResponseDTO;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("FL123");
        flight.setDepartureTerminal("Terminal A");
        flight.setArrivalTerminal("Terminal B");
        flight.setDepartureTime("2024-12-25T10:00");
        flight.setArrivalTime("2024-12-25T10:00");
        flight.setPrice(200.0);

        createRequestDTO = new FlightCreateRequestDTO(1L, "FL123", "Terminal A", "Terminal B", "2024-12-24T10:00", "2024-12-24T12:00", 200.0);
        updateRequestDTO = new FlightUpdateRequestDTO( "Terminal C",  "2024-12-25T10:00",300.0,"Terminal A", "TK-102");
        flightResponseDTO = new FlightResponseDTO(1L, "FL123", "Terminal A", "Terminal B", "2024-12-24 10:00", "2024-12-24 12:00", 200.0);
    }
    @Test
    void addFlight_ShouldReturnFlightResponseDTO(){
        Mockito.when(flightMapper.toConvert(Mockito.any(Flight.class))).thenReturn(flightResponseDTO);
        FlightResponseDTO result = flightService.addFlight(createRequestDTO);
        Assertions.assertEquals(result, flightResponseDTO);
        Mockito.verify(flightMapper).toConvert(Mockito.any(Flight.class));


    }

}
