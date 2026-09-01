package parte1_solid.caso3_notificaciones;

public class TelegramNotificationChannel implements NotificationChannel {
    @Override
    public String getChannelType() {
        return "TELEGRAM";
    }

    @Override
    public void send(String recipient, String message) {
        System.out.printf("[TelegramChannel] Enviando mensaje Telegram a %s: %s%n", recipient, message);
    }
}
