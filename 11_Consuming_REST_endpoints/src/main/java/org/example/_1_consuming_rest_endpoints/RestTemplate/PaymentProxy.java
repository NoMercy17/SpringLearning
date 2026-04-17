package org.example._1_consuming_rest_endpoints.RestTemplate;

import org.example._1_consuming_rest_endpoints.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class PaymentProxy {
    private final RestTemplate rest;

    @Value("${name.service.url}")
    private String paymentServiceUrl;

    public PaymentProxy(RestTemplate rest){
        this.rest = rest;
    }

    public Payment createPayment(Payment payment){
        String url = paymentServiceUrl + "/payment";

        HttpHeaders headers = new HttpHeaders();

        // Just send whatever payment object we're given
        HttpEntity<Payment> httpEntity = new HttpEntity<>(payment, headers);

        ResponseEntity<Payment> response = rest.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                Payment.class
        );

        return response.getBody();
    }
}
