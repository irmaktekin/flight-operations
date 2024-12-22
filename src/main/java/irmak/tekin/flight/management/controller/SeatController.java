package irmak.tekin.flight.management.controller;

import irmak.tekin.flight.management.dto.ResultDto;
import irmak.tekin.flight.management.service.SeatService;
import irmak.tekin.flight.management.dto.request.SeatCreateRequestDTO;
import irmak.tekin.flight.management.dto.request.SeatUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.SeatResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seats")

public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<SeatResponseDTO> addFlight(@Validated @RequestBody SeatCreateRequestDTO seatCreateRequestDTO){
        SeatResponseDTO seatResponseDTO = seatService.addSeat(seatCreateRequestDTO);
        return new ResponseEntity<>(seatResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultDto> deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        ResultDto resultDto = new ResultDto("Seat deleted succesfully");
        return new ResponseEntity<>(resultDto,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SeatResponseDTO> updateSeat(@PathVariable Long id, @RequestBody SeatUpdateRequestDTO seatUpdateRequestDTO) {
        SeatResponseDTO seatResponseDTO = seatService.updateSeat(id, seatUpdateRequestDTO);
        return new ResponseEntity<>(seatResponseDTO,HttpStatus.OK);
    }



}
