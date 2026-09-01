package ejercicio01;

public class StripeStrategy implements PaymentStrategy {
    private final String stripeCustomerId;

    public StripeStrategy(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    @Override
    public void process(double amount) {
        System.out.printf("  [Stripe USA] Cargo automático de $%.2f ejecutado mediante PaymentIntent (Customer: %s).%n",
                amount, stripeCustomerId);
    }

    @Override
    public String getMethodName() {
        return "Stripe (" + stripeCustomerId + ")";
    }
}
