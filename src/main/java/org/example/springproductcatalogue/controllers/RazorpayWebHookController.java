package org.example.springproductcatalogue.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class: Handles webhook requests from Razorpay.
 *
 * <p>This controller class is responsible for handling incoming webhook requests sent by Razorpay's webhook system.
 * It provides endpoints to receive and process webhook events related to payments.</p>
 *
 * <p>The endpoints defined in this controller class allow integration with Razorpay's webhook system,
 * enabling real-time processing of payment-related events such as successful payments, refunds, etc.</p>
 */
@RestController
@RequestMapping("/razorpayWebHook")
public class RazorpayWebHookController {

    /**
     * Controller method: Accepts a webhook request from Razorpay.
     *
     * <p>Upon receiving a webhook request, this method processes the payment response and performs necessary
     * actions based on the webhook event.</p>
     *
     * @return ResponseEntity indicating the status of the webhook request processing.
     */
    @PostMapping("/")
    public ResponseEntity acceptWebHookRequest() {

        System.out.println("payment response");
        return null;
    }

}
