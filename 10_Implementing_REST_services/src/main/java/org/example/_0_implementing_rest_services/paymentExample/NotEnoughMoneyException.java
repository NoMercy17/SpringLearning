package org.example._0_implementing_rest_services.paymentExample;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("Not enough money to make the payment.");    }
}
