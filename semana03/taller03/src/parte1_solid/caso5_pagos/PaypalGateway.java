package parte1_solid.caso5_pagos;

public class PaypalGateway implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.printf("[PayPal] Procesando cobro de $%.2f vía API REST v2 de PayPal.%n", amount);
    }

    @Override
    public String getProviderName() {
        return "PayPal";
    }
}
