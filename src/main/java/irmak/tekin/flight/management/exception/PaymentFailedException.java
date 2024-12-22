package irmak.tekin.flight.management.exception;

public class PaymentFailedException extends RuntimeException{
    public PaymentFailedException(){
        super("Payment Failed!");
    }
}
