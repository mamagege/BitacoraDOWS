package parte1_solid.caso5_pagos;

import java.util.Objects;

/**
 * Módulo de alto nivel: Procesa pagos de la aplicación.
 * Depende exclusivamente de la abstracción PaymentGateway (DIP).
 */
public class PaymentProcessor {
    private final PaymentGateway gateway;

    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway, "PaymentGateway no puede ser nulo");
    }

    public void processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a procesar debe ser mayor a 0");
        }
        System.out.printf("[PaymentProcessor] Iniciando transacción con el proveedor: %s...%n", gateway.getProviderName());
        gateway.pay(amount);
        System.out.printf("[PaymentProcessor] Transacción con %s finalizada con éxito.%n", gateway.getProviderName());
    }
}
