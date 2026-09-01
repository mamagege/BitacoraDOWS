package parte3_patrones.caso14_observer;

/**
 * Patrón: OBSERVER (Comportamiento)
 * Interfaz observadora para suscriptores interesados en cambios de estado de pedidos.
 */
public interface OrderStateObserver {
    void onOrderStatusChanged(String orderId, String previousStatus, String newStatus);
}
