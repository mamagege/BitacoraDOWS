package parte3_patrones.caso12_strategy;

public class PayPalPaymentStrategy implements PaymentStrategy {
    private final String paypalEmail;

    public PayPalPaymentStrategy(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("[PayPal Strategy] Pago digital de $%.2f completado con la cuenta %s%n", amount, paypalEmail);
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }
}
