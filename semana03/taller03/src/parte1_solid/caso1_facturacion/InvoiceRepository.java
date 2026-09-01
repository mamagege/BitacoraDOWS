package parte1_solid.caso1_facturacion;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Responsabilidad única: Persistencia y acceso a datos de facturas.
 */
public class InvoiceRepository {
    private final Map<String, Invoice> database = new HashMap<>();

    public void save(Invoice invoice) {
        database.put(invoice.id(), invoice);
        System.out.printf("[DB] Factura %s persistida correctamente en la base de datos.%n", invoice.id());
    }

    public Optional<Invoice> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }
}
