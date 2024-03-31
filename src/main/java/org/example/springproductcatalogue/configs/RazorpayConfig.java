package org.example.springproductcatalogue.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    @Value("${razorpay.key.id}") // fetch values from application.properties and update the variables here
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;
}
