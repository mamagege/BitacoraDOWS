package parte1_solid.caso4_cajero;

/**
 * Interfaz segregada para operaciones con divisas extranjeras (Pregunta guía #3).
 */
public interface MultiCurrencyCapable {
    void dispenseForeignCurrency(String currencyCode, double amount);
}
