package parte1_solid.caso4_cajero;

/**
 * Cajero Multidivisa (Pregunta guía #3).
 * Solo implementa Withdrawable y MultiCurrencyCapable, sin impactar a BasicATM.
 */
public class MultiCurrencyATM implements Withdrawable, MultiCurrencyCapable {
    private final String atmId;

    public MultiCurrencyATM(String atmId) {
        this.atmId = atmId;
    }

    @Override
    public void withdraw(double amount) {
        System.out.printf("[MultiCurrencyATM - %s] Retiro local de $%.2f.%n", atmId, amount);
    }

    @Override
    public void dispenseForeignCurrency(String currencyCode, double amount) {
        System.out.printf("[MultiCurrencyATM - %s] Dispensando %.2f %s.%n", atmId, amount, currencyCode);
    }
}
