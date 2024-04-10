package org.example.springproductcatalogue.services;

import org.springframework.stereotype.Service;

/**
 * Service class responsible for selecting the payment gateway.
 */
@Service
public class PaymentGatewaySelectionStrategy {

    /**
     * Selects the appropriate payment gateway based on some logic.
     *
     * @return An integer representing the selected payment gateway:
     */
    public int paymentGatewaySelection() {
        // add logic to choose the appropriate payment gateway
        // returning 1 or 2 for test purpose
        return 1;
    }
}
