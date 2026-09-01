package parte3_patrones.caso14_observer;

public class InventoryObserver implements OrderStateObserver {
    @Override
    public void onOrderStatusChanged(String orderId, String previousStatus, String newStatus) {
        if ("CONFIRMED".equalsIgnoreCase(newStatus)) {
            System.out.printf("  ↳ [Inventario] Reservando stock en bodega para el pedido #%s.%n", orderId);
        } else if ("CANCELLED".equalsIgnoreCase(newStatus)) {
            System.out.printf("  ↳ [Inventario] Liberando stock reservado del pedido #%s.%n", orderId);
        }
    }
}
