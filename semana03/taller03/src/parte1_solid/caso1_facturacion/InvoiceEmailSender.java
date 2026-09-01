package parte1_solid.caso1_facturacion;

/**
 * Responsabilidad única: Canal de comunicación y entrega por correo electrónico.
 */
public class InvoiceEmailSender {
    public void sendInvoice(String recipientEmail, byte[] pdfAttachment) {
        System.out.printf("[Email] Factura enviada exitosamente a %s (%d bytes adjuntos).%n",
                recipientEmail, pdfAttachment.length);
    }
}
