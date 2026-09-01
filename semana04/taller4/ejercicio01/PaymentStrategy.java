package ejercicio01;

/**
 * Patrón 1: STRATEGY (Comportamiento)
 * Rol: Encapsula la lógica de procesamiento de cobro para cada pasarela o método
 * financiero en clases independientes e intercambiables en tiempo de ejecución.
 */
public interface PaymentStrategy {
    void process(double amount);
    String getMethodName();
}
