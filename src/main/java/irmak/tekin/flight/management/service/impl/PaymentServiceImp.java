package irmak.tekin.flight.management.service.impl;

import irmak.tekin.flight.management.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService {
    @Override
    public boolean getPayment(Long seatId) {
        return true;
    }

}
