package parte1_solid.caso5_pagos;

public class StripeGateway implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.printf("[Stripe] Procesando cobro de $%.2f vía Stripe PaymentIntents API.%n", amount);
    }

    @Override
    public String getProviderName() {
        return "Stripe";
    }
}
