package org.example.springproductcatalogue.controllers;

import org.example.springproductcatalogue.dtos.InitiatePaymentRequestDto;
import org.example.springproductcatalogue.dtos.PaymentResponse;
import org.example.springproductcatalogue.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private PaymentService razorpayPaymentService;
    private PaymentService stripePaymentService;

    public PaymentController(@Qualifier("razorpayPaymentService") PaymentService razorpayPaymentService, @Qualifier("stripePaymentService") PaymentService stripePaymentService) {
        this.razorpayPaymentService = razorpayPaymentService;
        this.stripePaymentService = stripePaymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody InitiatePaymentRequestDto paymentRequestDto) {

        int paymentGatewayOption = choosePaymentGateway();
        PaymentResponse paymentResponse;
        switch (paymentGatewayOption) {
            case 1:
                paymentResponse = razorpayPaymentService.doPayment(paymentRequestDto);
                return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
            case 2:
                paymentResponse = stripePaymentService.doPayment(paymentRequestDto);
                return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
        }
        return null;
    }

    private int choosePaymentGateway() {
        // add logic to choose the appropriate payment gateway
        // returning 1 or 2 for test purpose
        return 1;
    }


}
