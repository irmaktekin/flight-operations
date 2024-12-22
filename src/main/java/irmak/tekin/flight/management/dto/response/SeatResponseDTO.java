package irmak.tekin.flight.management.dto.response;

import irmak.tekin.flight.management.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatResponseDTO {
    private Long id;
    private String seatNumber;
    private boolean isReserved;
    private Flight flight;
}
