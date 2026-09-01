package ejercicio01;

import java.util.Objects;

/**
 * Contexto del patrón Strategy:
 * Trabaja exclusivamente contra la abstracción PaymentStrategy.
 * Nunca conoce cómo se instancian los gateways ni qué reglas regionales aplican.
 */
public class Checkout {
    private final PaymentStrategy paymentStrategy;

    public Checkout(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = Objects.requireNonNull(paymentStrategy, "La estrategia de pago no puede ser nula");
    }

    public void process(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El importe de compra debe ser mayor a 0");
        }
        System.out.printf("--- [Checkout] Iniciando procesamiento con medio: %s ---%n", paymentStrategy.getMethodName());
        paymentStrategy.process(amount);
        System.out.println("--- [Checkout] Pago confirmado y orden completada con éxito. ---\n");
    }
}
