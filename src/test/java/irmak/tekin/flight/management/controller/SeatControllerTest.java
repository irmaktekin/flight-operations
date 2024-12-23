package irmak.tekin.flight.management.controller;

import irmak.tekin.flight.management.dto.request.SeatCreateRequestDTO;
import irmak.tekin.flight.management.dto.response.SeatResponseDTO;
import irmak.tekin.flight.management.entity.Flight;
import irmak.tekin.flight.management.entity.Seat;
import irmak.tekin.flight.management.mapper.SeatMapper;
import irmak.tekin.flight.management.repository.SeatRepository;
import irmak.tekin.flight.management.service.impl.SeatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
public class SeatControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(SeatController.class);

    @Mock
    private SeatServiceImpl seatService;
    @Mock
    private SeatRepository seatRepository;
    @MockBean
    private SeatMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    private SeatCreateRequestDTO createRequestDTO;
    private SeatResponseDTO seatResponseDTO;

    @BeforeEach
    void setup(){
        Flight flight = new Flight(1L, "FL123", "Terminal A", "Terminal B", "2024-12-24T10:00", "2024-12-24T12:00", 200.0);

        createRequestDTO = new SeatCreateRequestDTO(
                "22",
                false,
                200.0,
                1L
        );

        seatResponseDTO = new SeatResponseDTO(
                1L,
                "ATK-11",
                false,
                flight
        );
       }
    @Test
    void addSeat_ShouldReturnBadRequestWhenJsonIsInvalid() throws Exception{
        when(seatRepository.save(Mockito.any(Seat.class))).thenReturn(new Seat());
        when(seatService.addSeat(Mockito.any(SeatCreateRequestDTO.class))).thenReturn(seatResponseDTO);

        String invalidJson = "{\"seatNumber\":\"FL123\", \"isAvailable\":\"Y\", \"price\":300.0, \"departureTime\":\"2024-12-24T10:00\", \"arrivalTime\":\"2024-12-24T12:00\"}";

        if (seatService == null) {
            logger.error("Received null seatservice");
            throw new IllegalArgumentException("Seat service is null");
        }
        mockMvc.perform(post("/api/v1/seats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());

    }
}
