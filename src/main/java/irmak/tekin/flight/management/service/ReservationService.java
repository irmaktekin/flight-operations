package irmak.tekin.flight.management.service;

import irmak.tekin.flight.management.dto.request.ReservationRequestDTO;
import irmak.tekin.flight.management.dto.response.ReservationResponseDTO;

public interface ReservationService {
    ReservationResponseDTO makeReservation(ReservationRequestDTO reservationRequestDTO);

}
