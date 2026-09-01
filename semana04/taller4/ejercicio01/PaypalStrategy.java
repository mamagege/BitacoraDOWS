package ejercicio01;

public class PaypalStrategy implements PaymentStrategy {
    private final String email;

    public PaypalStrategy(String email) {
        this.email = email;
    }

    @Override
    public void process(double amount) {
        System.out.printf("  [PayPal USA/Global] Cobro procesado vía PayPal Checkout por $%.2f (Cuenta: %s).%n",
                amount, email);
    }

    @Override
    public String getMethodName() {
        return "PayPal (" + email + ")";
    }
}
