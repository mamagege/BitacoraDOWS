package parte1_solid.caso3_notificaciones;

public class EmailNotificationChannel implements NotificationChannel {
    @Override
    public String getChannelType() {
        return "EMAIL";
    }

    @Override
    public void send(String recipient, String message) {
        System.out.printf("[EmailChannel] Enviando correo a %s: %s%n", recipient, message);
    }
}
