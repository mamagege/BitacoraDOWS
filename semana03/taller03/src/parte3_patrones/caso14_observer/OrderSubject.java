package parte3_patrones.caso14_observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Sujeto (Subject): Mantiene la lista de observadores y propaga el evento
 * cuando el estado del pedido cambia sin acoplarse a los módulos receptores.
 */
public class OrderSubject {
    private final String orderId;
    private String currentStatus;
    private final List<OrderStateObserver> observers = new ArrayList<>();

    public OrderSubject(String orderId, String initialStatus) {
        this.orderId = orderId;
        this.currentStatus = initialStatus;
    }

    public void attach(OrderStateObserver observer) {
        Objects.requireNonNull(observer, "El observador no puede ser nulo");
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(OrderStateObserver observer) {
        observers.remove(observer);
    }

    public void updateStatus(String newStatus) {
        String oldStatus = this.currentStatus;
        this.currentStatus = newStatus;
        System.out.printf("%n[OrderSubject] Pedido #%s cambió de estado: '%s' -> '%s'%n", orderId, oldStatus, newStatus);
        notifyObservers(oldStatus, newStatus);
    }

    private void notifyObservers(String oldStatus, String newStatus) {
        for (OrderStateObserver observer : observers) {
            observer.onOrderStatusChanged(orderId, oldStatus, newStatus);
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }
}
