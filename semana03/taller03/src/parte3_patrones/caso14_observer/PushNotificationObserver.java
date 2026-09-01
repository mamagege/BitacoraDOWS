package parte3_patrones.caso14_observer;

public class PushNotificationObserver implements OrderStateObserver {
    @Override
    public void onOrderStatusChanged(String orderId, String previousStatus, String newStatus) {
        System.out.printf("  ↳ [Push App Móvil] Alerta en tiempo real: Pedido #%s actualizado a %s.%n",
                orderId, newStatus);
    }
}
