package org.example.springproductcatalogue.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.example.springproductcatalogue.dtos.InitiatePaymentRequestDto;
import org.example.springproductcatalogue.dtos.PaymentResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Service class implementing the PaymentService interface for Razorpay payments.
 */
@Service("razorpayPaymentService")
public class RazorpayPaymentServiceImpl implements PaymentService {

    RazorpayClient razorpayClient;

    /**
     * Constructor for RazorpayPaymentServiceImpl.
     *
     * @param razorpayClient The RazorpayClient instance to interact with the Razorpay API.
     */
    public RazorpayPaymentServiceImpl(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    /**
     * Initiates a payment using the Razorpay payment gateway.
     *
     * @param paymentRequestDto The payment request DTO containing payment details.
     * @return PaymentResponse containing information about the initiated payment.
     * @throws RazorpayException If an error occurs during the payment initiation process.
     */
    @Override
    public PaymentResponse doPayment(InitiatePaymentRequestDto paymentRequestDto) throws RazorpayException{

        try {

            JSONObject paymentRequestOptions = new JSONObject();
            paymentRequestOptions.put("amount",paymentRequestDto.getAmount());
            paymentRequestOptions.put("currency","INR");
            paymentRequestOptions.put("receipt", paymentRequestDto.getOrderId());

            JSONObject notes = new JSONObject();
            notes.put("notes_key_1","This is a demo Razorpay order payment.");
            paymentRequestOptions.put("notes",notes);

            JSONObject customer = new JSONObject();
            customer.put("email",paymentRequestDto.getEmail());
            customer.put("phone",paymentRequestDto.getPhoneNumber());
            paymentRequestOptions.put("customer",customer);

            JSONObject notify = new JSONObject();
            customer.put("sms",true);
            customer.put("email",true);
            paymentRequestOptions.put("notify",notify);

            paymentRequestOptions.put("callback_url","http://localhost:8080/razorpayWebHook/");
            paymentRequestOptions.put("callback_method","get");

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentRequestOptions);
            PaymentResponse paymentResponse = new PaymentResponse();

            return paymentResponse;

        } catch (RazorpayException e) {
            e.printStackTrace();
        }

        return null;
    }
}
