package org.example.springproductcatalogue.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for integrating Razorpay payment gateway.
 * This class provides configuration details required to create a Razorpay client.
 */
@Configuration
public class RazorpayConfig {
    @Value("${razorpay.key.id}") // fetch values from application.properties and update the variables here
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    /**
     * Creates a RazorpayClient instance to interact with the Razorpay payment gateway.
     * This method retrieves Razorpay API key and secret from application.properties
     * and initializes a RazorpayClient object for making API calls to Razorpay.
     *
     * @return RazorpayClient initialized with the provided API key and secret.
     * @throws RazorpayException if there is an issue initializing the RazorpayClient.
     */
    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorpayKeyId,razorpayKeySecret);
    }
}
