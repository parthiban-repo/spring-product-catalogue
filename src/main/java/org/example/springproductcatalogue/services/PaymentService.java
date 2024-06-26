package org.example.springproductcatalogue.services;

import com.razorpay.RazorpayException;
import org.example.springproductcatalogue.dtos.InitiatePaymentRequestDto;
import org.example.springproductcatalogue.dtos.PaymentResponse;

public interface PaymentService {
    PaymentResponse doPayment(InitiatePaymentRequestDto paymentRequestDto) throws RazorpayException;
}
