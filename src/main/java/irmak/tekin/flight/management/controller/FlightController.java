package irmak.tekin.flight.management.controller;

import irmak.tekin.flight.management.dto.ResultDto;
import irmak.tekin.flight.management.service.FlightService;
import irmak.tekin.flight.management.dto.request.FlightCreateRequestDTO;
import irmak.tekin.flight.management.dto.request.FlightUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.FlightResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    @GetMapping
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights() {
        List<FlightResponseDTO> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<FlightResponseDTO> addFlight(@Validated @RequestBody FlightCreateRequestDTO flightCreateRequestDTO){
        FlightResponseDTO flightResponseDTO = flightService.addFlight(flightCreateRequestDTO);
        if (flightCreateRequestDTO == null) {
            throw new IllegalArgumentException("FlightCreateRequestDTO cannot be null");
        }
        //
        return new ResponseEntity<>(flightResponseDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultDto> cancelFlight(@PathVariable Long id){
        flightService.cancelFlight(id);
        ResultDto resultDto = new ResultDto("Flight cancelled succesfully");
        return new ResponseEntity<>(resultDto,HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> updateFlight(@PathVariable Long id,
                                                            @RequestBody FlightUpdateRequestDTO flightUpdateRequestDTO) {
        FlightResponseDTO flightResponseDTO = flightService.updateFlight(id, flightUpdateRequestDTO);
        return new ResponseEntity<>(flightResponseDTO,HttpStatus.OK);
    }


}
