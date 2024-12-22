package irmak.tekin.flight.management.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="flight_id",nullable = false)
    private Flight flight;

    private String seatNumber;
    private boolean isReserved;
    private double price;


}
