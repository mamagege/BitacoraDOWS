package parte1_solid.caso1_facturacion;

/**
 * Responsabilidad única: Formato de presentación y renderizado en PDF.
 */
public class InvoicePdfGenerator {
    public byte[] generatePdf(Invoice invoice, double totalWithTax) {
        String template = """
                =================================================
                FACTURA FISCAL: %s
                Cliente: %s (%s)
                Subtotal: $%.2f
                Total con IVA: $%.2f
                =================================================
                """.formatted(invoice.id(), invoice.customerName(), invoice.customerEmail(), invoice.subtotal(), totalWithTax);
        return template.getBytes();
    }
}
