package irmak.tekin.flight.management.exception;

public class SeatNotFoundException extends RuntimeException{
    private Long seatId;

    public SeatNotFoundException(Long seatId){
        super("Seat with ID " + seatId + " not found.");
        this.seatId = seatId;
    }
    public Long getSeatId() {
        return seatId;
    }
}
