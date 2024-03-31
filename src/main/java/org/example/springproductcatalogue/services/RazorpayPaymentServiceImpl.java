package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.dtos.InitiatePaymentRequestDto;
import org.example.springproductcatalogue.dtos.PaymentResponse;
import org.springframework.stereotype.Service;

@Service("razorpayPaymentService")
public class RazorpayPaymentServiceImpl implements PaymentService {

    @Override
    public PaymentResponse doPayment(InitiatePaymentRequestDto paymentRequestDto) {


        return null;
    }
}
