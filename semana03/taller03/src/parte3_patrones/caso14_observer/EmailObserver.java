package parte3_patrones.caso14_observer;

public class EmailObserver implements OrderStateObserver {
    @Override
    public void onOrderStatusChanged(String orderId, String previousStatus, String newStatus) {
        System.out.printf("  ↳ [Email] Correo transaccional enviado al cliente: 'Su pedido #%s ahora está %s'.%n",
                orderId, newStatus);
    }
}
