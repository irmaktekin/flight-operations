package irmak.tekin.flight.management.repository;

import irmak.tekin.flight.management.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository <Flight,Long> {
}
