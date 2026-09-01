package parte3_patrones.caso14_observer;

import java.time.Instant;

public class AuditObserver implements OrderStateObserver {
    @Override
    public void onOrderStatusChanged(String orderId, String previousStatus, String newStatus) {
        System.out.printf("  ↳ [Auditoría] [%s] Registro inmutable: Pedido #%s transición [%s -> %s].%n",
                Instant.now(), orderId, previousStatus, newStatus);
    }
}
