package parte1_solid.caso1_facturacion;

/**
 * Caso de uso / Orquestador: Coordina el flujo de facturación delegando
 * cada aspecto a su componente especializado (SRP).
 */
public class InvoiceService {
    private final TaxCalculator taxCalculator;
    private final InvoicePdfGenerator pdfGenerator;
    private final InvoiceEmailSender emailSender;
    private final InvoiceRepository repository;

    public InvoiceService(
            TaxCalculator taxCalculator,
            InvoicePdfGenerator pdfGenerator,
            InvoiceEmailSender emailSender,
            InvoiceRepository repository
    ) {
        this.taxCalculator = taxCalculator;
        this.pdfGenerator = pdfGenerator;
        this.emailSender = emailSender;
        this.repository = repository;
    }

    public void processInvoice(Invoice invoice) {
        double totalWithTax = taxCalculator.calculateTotalWithTaxes(invoice.subtotal());
        byte[] pdf = pdfGenerator.generatePdf(invoice, totalWithTax);
        repository.save(invoice);
        emailSender.sendInvoice(invoice.customerEmail(), pdf);
    }
}
