package parte1_solid.caso1_facturacion;

/**
 * Responsabilidad única: Aplicar reglas fiscales y cálculo de impuestos.
 */
public class TaxCalculator {
    private static final double VAT_RATE = 0.19; // IVA 19%

    public double calculateVat(double subtotal) {
        return subtotal * VAT_RATE;
    }

    public double calculateTotalWithTaxes(double subtotal) {
        return subtotal + calculateVat(subtotal);
    }
}
