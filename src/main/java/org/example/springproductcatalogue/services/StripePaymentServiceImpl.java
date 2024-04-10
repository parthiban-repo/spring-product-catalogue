/***** THIS IS WORK IN PROGRESS. PLEASE IGNORE *****/

package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.dtos.InitiatePaymentRequestDto;
import org.example.springproductcatalogue.dtos.PaymentResponse;
import org.springframework.stereotype.Service;

// write Stripe Payment Gateway implementation here.
@Service("stripePaymentService")
public class StripePaymentServiceImpl implements PaymentService {
    @Override
    public PaymentResponse doPayment(InitiatePaymentRequestDto paymentRequestDto) {
        return null;
    }
}
