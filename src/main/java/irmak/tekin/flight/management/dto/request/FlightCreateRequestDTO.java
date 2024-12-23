package irmak.tekin.flight.management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightCreateRequestDTO {
    @Positive
    private Long id;
    @NotBlank
    @Size(min = 5, max=20)
    private String flightNumber;
    @NotBlank
    private String departureTerminal;
    @NotBlank
    private String arrivalTerminal;
    private String departureTime;
    private String arrivalTime;
    @DecimalMin(value = "0.0",inclusive = false)
    private double price;

}
