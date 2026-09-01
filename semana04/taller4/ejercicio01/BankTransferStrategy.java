package ejercicio01;

public class BankTransferStrategy implements PaymentStrategy {
    private final String iban;

    public BankTransferStrategy(String iban) {
        this.iban = iban;
    }

    @Override
    public void process(double amount) {
        System.out.printf("  [Transferencia Bancaria] Generando orden de transferencia SEPA/ACH por $%.2f (IBAN/Cuenta: %s).%n",
                amount, iban);
    }

    @Override
    public String getMethodName() {
        return "Transferencia Bancaria (" + iban + ")";
    }
}
