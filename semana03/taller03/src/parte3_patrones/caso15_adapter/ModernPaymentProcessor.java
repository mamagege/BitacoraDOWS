package parte3_patrones.caso15_adapter;

/**
 * Interfaz objetivo moderna requerida por el sistema actual.
 */
public interface ModernPaymentProcessor {
    void modernPay(String accountNumber, double amountInDollars);
}
