package org.example._0_implementing_rest_services.paymentExample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// We use the @RestControllerAdvice annotation to mark the class as a REST controller advice.
@RestControllerAdvice
public class ExceptionControllerAdvice {

    // We use the ExceptionHandler method to associate an exception with the logic the method implements.
    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorDetails> exceptionNotEnoughMoneyHandler(){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Not enough money to make the payments");
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
