package org.example._1_consuming_rest_endpoints.WebClient;

import org.example._1_consuming_rest_endpoints.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PaymentProxy {

    private final WebClient webClient;

    @Value("${name.service.url}")
    private String url;

    public PaymentProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    // method performing the call doesn’t get the input
    //directly. Instead, we send a Mono

    //reate an independent task that
    //provides the request body value
    public Mono<Payment> createPayment(
            String id,
            Payment payment
    ) {
        return webClient.post()
                .uri(url + "/payment")
                .header("requestId", id)
                .body(Mono.just(payment), Payment.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }
}
