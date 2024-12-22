package irmak.tekin.flight.management.dto.request;

import irmak.tekin.flight.management.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {
    @Positive
    private Long seatId;
    @Positive
    private Long flightId;
    @NotBlank
    private String customerName;
}
