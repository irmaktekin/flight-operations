package irmak.tekin.flight.management.controller;

import irmak.tekin.flight.management.dto.request.FlightCreateRequestDTO;
import irmak.tekin.flight.management.dto.response.FlightResponseDTO;
import irmak.tekin.flight.management.entity.Flight;
import irmak.tekin.flight.management.mapper.FlightMapper;
import irmak.tekin.flight.management.repository.FlightRepository;
import irmak.tekin.flight.management.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @MockBean
    private FlightServiceImpl flightService;
    @MockBean
    private FlightRepository flightRepository;
    @MockBean
    private FlightMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    private FlightCreateRequestDTO createRequestDTO;
    private FlightResponseDTO flightResponseDTO;

    @BeforeEach
    void setup(){
        createRequestDTO = new FlightCreateRequestDTO(1L, "FL123", "Terminal A", "Terminal B", "2024-12-24T10:00", "2024-12-24T12:00", 200.0);
        flightResponseDTO = new FlightResponseDTO(1L, "FL123", "Terminal A", "Terminal B", "2024-12-24 10:00", "2024-12-24 12:00", 200.0);
    }
    @Test
    void addFlight_ShouldReturnFlightResponseDto() throws Exception{
        if (flightService == null) {
            logger.error("Received null flightCreateRequest");
            throw new IllegalArgumentException("Flight data is null");
        }
        when(flightRepository.save(Mockito.any(Flight.class))).thenReturn(new Flight());  // Mock Repository

        when(flightService.addFlight(Mockito.any(FlightCreateRequestDTO.class))).thenReturn(flightResponseDTO);
        mockMvc.perform(post("/api/v1/flights")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"flightNumber\":\"FL123\",\"departureTerminal\":\"Terminal A\",\"arrivalTerminal\":\"Terminal B\",\"departureTime\":\"2024-12-24T10:00\",\"arrivalTime\":\"2024-12-24T12:00\",\"price\":200.0}"))
                .andExpect(status().isCreated()) // Check for HTTP 201 status
                .andExpect(jsonPath("$.flightNumber").value("FL123")) // Check response properties
                .andExpect(jsonPath("$.departureTerminal").value("Terminal A"))
                .andExpect(jsonPath("$.arrivalTerminal").value("Terminal B"));

    }
}
