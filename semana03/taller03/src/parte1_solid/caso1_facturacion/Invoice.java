package parte1_solid.caso1_facturacion;

import java.util.List;

/**
 * Entidad de dominio inmutable que representa una factura.
 * Responsabilidad única: Contener los datos del estado de una factura.
 */
public record Invoice(
        String id,
        String customerName,
        String customerEmail,
        List<InvoiceItem> items,
        double subtotal
) {
    public record InvoiceItem(String description, int quantity, double unitPrice) {
        public double total() {
            return quantity * unitPrice;
        }
    }
}
