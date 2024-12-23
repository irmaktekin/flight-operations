package irmak.tekin.flight.management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class FlightUpdateRequestDTO {
    @NotBlank
    private String flightNumber;

    public FlightUpdateRequestDTO(String arrivalTerminal, String departureTime, double price, String departureTerminal, String flightNumber) {
        this.arrivalTerminal = arrivalTerminal;
        this.departureTime = departureTime;
        this.price = price;
        this.departureTerminal = departureTerminal;
        this.flightNumber = flightNumber;
    }

    @NotBlank
    private String departureTerminal;
    @NotBlank
    private String arrivalTerminal;
    private String departureTime;
    private double price;
}
