package parte3_patrones.caso12_strategy;

/**
 * Patrón: STRATEGY (Comportamiento)
 * ¿Por qué?: Encapsula cada algoritmo de pago en su propia clase, permitiendo que el
 * flujo de compra sea idéntico e intercambiable en runtime sin if-else.
 */
public interface PaymentStrategy {
    void pay(double amount);
    String getMethodName();
}
