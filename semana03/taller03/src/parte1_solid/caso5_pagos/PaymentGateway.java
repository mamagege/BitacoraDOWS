package parte1_solid.caso5_pagos;

/**
 * Abstracción de pasarela de pagos (DIP: Los módulos de alto nivel y bajo nivel
 * dependen de esta abstracción).
 */
public interface PaymentGateway {
    void pay(double amount);
    String getProviderName();
}
