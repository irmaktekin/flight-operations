package irmak.tekin.flight.management.exception;

public class SeatAlreadyReservedException extends RuntimeException {

    private Long seatId;

    public SeatAlreadyReservedException(Long seatId){
        super("Seat with ID " + seatId + " already reserved.");
        this.seatId = seatId;
    }
    public Long getSeatId() {
        return seatId;
    }
}
