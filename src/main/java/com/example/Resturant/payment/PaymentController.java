package com.example.Resturant.payment;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private static Gson gson = new Gson();

    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {

        // This is your real test secret API key.
        Stripe.apiKey = "";

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setAmount(15 * 100L)
                .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);
        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());
        return gson.toJson(paymentResponse);
    }
}
