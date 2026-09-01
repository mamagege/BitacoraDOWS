package parte3_patrones.caso12_strategy;

public class NequiPaymentStrategy implements PaymentStrategy {
    private final String phoneNumber;

    public NequiPaymentStrategy(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("[Nequi] Notificación push de cobro por $%.2f enviada al celular %s%n", amount, phoneNumber);
    }

    @Override
    public String getMethodName() {
        return "Nequi";
    }
}
