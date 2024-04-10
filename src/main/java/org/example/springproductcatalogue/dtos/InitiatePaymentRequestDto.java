package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO class: Represents the request data for initiating a payment.
 */
@Getter
@Setter
public class InitiatePaymentRequestDto {
    private String email;
    private String phoneNumber;
    private Long amount;
    private String orderId;
}
