package parte1_solid.caso3_notificaciones;

public class WhatsAppNotificationChannel implements NotificationChannel {
    @Override
    public String getChannelType() {
        return "WHATSAPP";
    }

    @Override
    public void send(String recipient, String message) {
        System.out.printf("[WhatsAppChannel] Enviando mensaje WhatsApp a %s: %s%n", recipient, message);
    }
}
