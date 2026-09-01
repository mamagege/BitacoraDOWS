package parte1_solid.caso5_pagos;

public class MercadoPagoGateway implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.printf("[MercadoPago] Procesando cobro de $%.2f vía Mercado Pago Checkout.%n", amount);
    }

    @Override
    public String getProviderName() {
        return "MercadoPago";
    }
}
