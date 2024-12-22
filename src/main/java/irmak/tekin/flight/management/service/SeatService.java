package irmak.tekin.flight.management.service;

import irmak.tekin.flight.management.dto.request.SeatCreateRequestDTO;
import irmak.tekin.flight.management.dto.request.SeatUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.SeatResponseDTO;


public interface SeatService {
    SeatResponseDTO addSeat(SeatCreateRequestDTO seatCreateRequestDTO);
    void deleteSeat(Long seatId);
    SeatResponseDTO updateSeat(Long id, SeatUpdateRequestDTO seatUpdateRequestDTO);

}
