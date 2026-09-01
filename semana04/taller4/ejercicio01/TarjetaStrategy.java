package ejercicio01;

public class TarjetaStrategy implements PaymentStrategy {
    private final String cardNumber;

    public TarjetaStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void process(double amount) {
        System.out.printf("  [Tarjeta Crédito/Débito] Procesando cobro de $%.2f con franquicia autorizada (Tarjeta: ****-%s).%n",
                amount, cardNumber.substring(Math.max(0, cardNumber.length() - 4)));
    }

    @Override
    public String getMethodName() {
        return "Tarjeta (Crédito/Débito)";
    }
}
