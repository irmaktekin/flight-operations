package irmak.tekin.flight.management.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationResponseDTO {
    private Long flightId;
    private String customerName;
}
