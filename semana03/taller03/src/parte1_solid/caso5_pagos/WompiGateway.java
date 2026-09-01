package parte1_solid.caso5_pagos;

public class WompiGateway implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.printf("[Wompi] Procesando cobro de $%.2f vía pasarela de pagos Wompi.%n", amount);
    }

    @Override
    public String getProviderName() {
        return "Wompi";
    }
}
