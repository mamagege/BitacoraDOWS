package parte1_solid.caso3_notificaciones;

/**
 * Contrato de canal de notificación (Abierto para extensión).
 */
public interface NotificationChannel {
    String getChannelType();
    void send(String recipient, String message);
}
