package org.example._0_implementing_rest_services.paymentExample;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    private static Logger logger = Logger.getLogger(PaymentController.class.getName());

    @PostMapping("/payment")
    public ResponseEntity<?> makePayment(
            @RequestBody PaymentDetails paymentDetails
    ) {

        logger.info("Received payment: " + paymentDetails.getAmount());

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(paymentDetails);
    }


}

