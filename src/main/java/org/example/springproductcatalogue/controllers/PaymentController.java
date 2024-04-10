package org.example.springproductcatalogue.controllers;

import org.example.springproductcatalogue.dtos.InitiatePaymentRequestDto;
import org.example.springproductcatalogue.dtos.PaymentResponse;
import org.example.springproductcatalogue.services.PaymentGatewaySelectionStrategy;
import org.example.springproductcatalogue.services.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling payment-related requests.
 * This class provides endpoints for initiating payments using different payment gateways.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService razorpayPaymentService;
    private PaymentService stripePaymentService;
    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;

    public PaymentController(@Qualifier("razorpayPaymentService") PaymentService razorpayPaymentService,
                             @Qualifier("stripePaymentService") PaymentService stripePaymentService,
                             PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy) {
        this.razorpayPaymentService = razorpayPaymentService;
        this.stripePaymentService = stripePaymentService;
        this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }

    /**
     * Default payment gateway end point that initiates a payment request based on the selected payment
     * gateway strategy.
     *
     * @param paymentRequestDto The payment request details.
     * @return ResponseEntity containing the payment response.
     * @throws Exception if an error occurs during payment initiation.
     */
    @PostMapping("/")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody InitiatePaymentRequestDto paymentRequestDto) throws Exception {

        PaymentResponse paymentResponse;
        switch (paymentGatewaySelectionStrategy.paymentGatewaySelection()) {
            case 1:
                paymentResponse = razorpayPaymentService.doPayment(paymentRequestDto);
                return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
            case 2:
                paymentResponse = stripePaymentService.doPayment(paymentRequestDto);
                return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
        }

        return null;

    }

}
