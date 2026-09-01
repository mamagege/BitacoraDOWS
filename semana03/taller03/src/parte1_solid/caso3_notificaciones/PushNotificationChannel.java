package parte1_solid.caso3_notificaciones;

public class PushNotificationChannel implements NotificationChannel {
    @Override
    public String getChannelType() {
        return "PUSH";
    }

    @Override
    public void send(String recipient, String message) {
        System.out.printf("[PushChannel] Enviando notificación Push a %s: %s%n", recipient, message);
    }
}
