package com.example.Resturant.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CreatePaymentResponse {

    private String clientSecret;

    public CreatePaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
