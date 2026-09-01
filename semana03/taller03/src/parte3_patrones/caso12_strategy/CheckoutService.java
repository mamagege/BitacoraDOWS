package parte3_patrones.caso12_strategy;

import java.util.Objects;

/**
 * Contexto del patrón Strategy: El flujo de checkout es invariable
 * (selección -> validación -> cobro con estrategia elegida -> confirmación).
 */
public class CheckoutService {
    private PaymentStrategy paymentStrategy;

    public CheckoutService(PaymentStrategy initialStrategy) {
        this.paymentStrategy = Objects.requireNonNull(initialStrategy, "La estrategia de pago no puede ser nula");
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = Objects.requireNonNull(paymentStrategy, "La estrategia de pago no puede ser nula");
    }

    public void executeCheckout(String orderId, double totalAmount) {
        System.out.printf("--- Iniciando Checkout para Pedido #%s por $%.2f ---%n", orderId, totalAmount);
        System.out.printf("1. Verificando inventario del pedido #%s... OK%n", orderId);
        System.out.printf("2. Ejecutando cobro mediante estrategia: %s...%n", paymentStrategy.getMethodName());
        paymentStrategy.pay(totalAmount);
        System.out.printf("3. Pedido #%s confirmado y en preparación para envío.%n", orderId);
    }
}
