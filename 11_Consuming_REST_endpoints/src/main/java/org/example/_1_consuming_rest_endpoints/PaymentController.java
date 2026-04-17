package org.example._1_consuming_rest_endpoints;

import org.example._1_consuming_rest_endpoints.WebClient.PaymentProxy;
//import org.example._1_consuming_rest_endpoints.RestTemplate.PaymentProxy;
//import org.example._1_consuming_rest_endpoints.OpenFeign.PaymentProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class PaymentController {

    private final PaymentProxy paymentProxy;

    public PaymentController(PaymentProxy paymentProxy) {
        this.paymentProxy = paymentProxy;
    }

    @PostMapping("/payment")
    public Mono<Payment> createPayment(
            @RequestBody Payment payment
    ) {
        // Set ID before sending to the proxy!
        //payment.setId(UUID.randomUUID().toString());
        String requestId = UUID.randomUUID().toString();

        return paymentProxy.createPayment(requestId,payment);
    }
}
