package parte3_patrones.caso14_observer;

/**
 * Nuevo observador añadido sin modificar la clase OrderSubject ni los otros observadores.
 */
public class AutomatedBillingObserver implements OrderStateObserver {
    @Override
    public void onOrderStatusChanged(String orderId, String previousStatus, String newStatus) {
        if ("DELIVERED".equalsIgnoreCase(newStatus)) {
            System.out.printf("  ↳ [Facturación Automática] Generando factura electrónica final para el pedido #%s entregado.%n", orderId);
        }
    }
}
