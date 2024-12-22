package irmak.tekin.flight.management.exception;

public class FlightNotFoundException extends RuntimeException{
    private Long flightId;

    public FlightNotFoundException(Long flightId){
        super("Flight with ID " + flightId + " not found.");
        this.flightId = flightId;
    }
    public Long getFlightId() {
        return flightId;
    }
}
