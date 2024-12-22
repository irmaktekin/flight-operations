package irmak.tekin.flight.management.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String departureTerminal;
    private String arrivalTerminal;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;

}
