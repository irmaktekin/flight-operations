package irmak.tekin.flight.management.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long flightId;
    @Column(name = "seatId", nullable = false)
    private Long seatId;
    @NotEmpty
    private String customerName;
    private boolean isPaid;

}
