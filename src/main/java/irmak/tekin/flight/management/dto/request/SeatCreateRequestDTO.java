package irmak.tekin.flight.management.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SeatCreateRequestDTO {
        @NotNull
        private String seatNumber;
        private boolean isAvailable;
        @NotNull
        private double price;
        @Positive
        private Long flightId;

}
