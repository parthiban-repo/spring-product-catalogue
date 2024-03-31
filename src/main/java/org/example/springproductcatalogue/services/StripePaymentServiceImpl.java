package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.dtos.InitiatePaymentRequestDto;
import org.example.springproductcatalogue.dtos.PaymentResponse;
import org.springframework.stereotype.Service;

@Service("stripePaymentService")
public class StripePaymentServiceImpl implements PaymentService {
    @Override
    public PaymentResponse doPayment(InitiatePaymentRequestDto paymentRequestDto) {
        return null;
    }
}
