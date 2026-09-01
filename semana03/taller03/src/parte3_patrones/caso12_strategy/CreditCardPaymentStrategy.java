package parte3_patrones.caso12_strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    private final String cardNumber;
    private final String holderName;

    public CreditCardPaymentStrategy(String cardNumber, String holderName) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("[Tarjeta de Crédito] Cobro de $%.2f a nombre de %s (Tarjeta: ****-%s)%n",
                amount, holderName, cardNumber.substring(Math.max(0, cardNumber.length() - 4)));
    }

    @Override
    public String getMethodName() {
        return "Tarjeta de Crédito";
    }
}
