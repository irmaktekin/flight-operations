package irmak.tekin.flight.management.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FlightUpdateRequestDTO {
    @NotBlank
    private String flightNumber;
    @NotBlank
    private String departureTerminal;
    @NotBlank
    private String arrivalTerminal;
    private String departureTime;
    private double price;
}
