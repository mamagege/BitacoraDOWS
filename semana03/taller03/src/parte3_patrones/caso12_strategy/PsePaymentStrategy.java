package parte3_patrones.caso12_strategy;

public class PsePaymentStrategy implements PaymentStrategy {
    private final String bankName;
    private final String userEmail;

    public PsePaymentStrategy(String bankName, String userEmail) {
        this.bankName = bankName;
        this.userEmail = userEmail;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("[PSE] Débito bancario de $%.2f procesado vía %s (Usuario: %s)%n", amount, bankName, userEmail);
    }

    @Override
    public String getMethodName() {
        return "PSE (" + bankName + ")";
    }
}
