package ejercicio01;

public class NequiStrategy implements PaymentStrategy {
    private final String phoneNumber;

    public NequiStrategy(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(double amount) {
        System.out.printf("  [Nequi Colombia] Notificación push de débito por $%.2f enviada a la app del celular %s.%n",
                amount, phoneNumber);
    }

    @Override
    public String getMethodName() {
        return "Nequi (" + phoneNumber + ")";
    }
}
