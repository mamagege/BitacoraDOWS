package ejercicio01;

public class PseStrategy implements PaymentStrategy {
    private final String bankName;

    public PseStrategy(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public void process(double amount) {
        System.out.printf("  [PSE Colombia] Débito en cuenta de ahorros/corriente por $%.2f vía banco %s.%n",
                amount, bankName);
    }

    @Override
    public String getMethodName() {
        return "PSE (" + bankName + ")";
    }
}
