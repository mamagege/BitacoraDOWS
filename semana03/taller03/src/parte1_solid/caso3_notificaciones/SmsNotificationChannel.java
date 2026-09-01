package parte1_solid.caso3_notificaciones;

public class SmsNotificationChannel implements NotificationChannel {
    @Override
    public String getChannelType() {
        return "SMS";
    }

    @Override
    public void send(String recipient, String message) {
        System.out.printf("[SmsChannel] Enviando SMS a %s: %s%n", recipient, message);
    }
}
