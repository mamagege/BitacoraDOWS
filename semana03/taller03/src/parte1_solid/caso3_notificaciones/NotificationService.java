package parte1_solid.caso3_notificaciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Servicio de notificaciones cerrado para modificación y abierto para extensión (OCP).
 * Permite registrar nuevos canales en tiempo de compilación o runtime sin modificar esta clase.
 */
public class NotificationService {
    private final Map<String, NotificationChannel> channels = new HashMap<>();

    public NotificationService(List<NotificationChannel> initialChannels) {
        for (NotificationChannel channel : initialChannels) {
            registerChannel(channel);
        }
    }

    public void registerChannel(NotificationChannel channel) {
        Objects.requireNonNull(channel, "El canal no puede ser nulo");
        this.channels.put(channel.getChannelType().toUpperCase(), channel);
    }

    public void sendNotification(String type, String recipient, String message) {
        NotificationChannel channel = channels.get(type.toUpperCase());
        if (channel == null) {
            throw new IllegalArgumentException("Canal de notificación no soportado: " + type);
        }
        channel.send(recipient, message);
    }
}
