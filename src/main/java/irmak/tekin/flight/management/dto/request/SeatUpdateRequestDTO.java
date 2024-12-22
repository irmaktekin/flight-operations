package irmak.tekin.flight.management.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SeatUpdateRequestDTO {
    @NotNull
    private String seatNumber;
    @NotNull
    private Double price;
}
