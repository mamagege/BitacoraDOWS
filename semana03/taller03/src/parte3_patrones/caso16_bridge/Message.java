package parte3_patrones.caso16_bridge;

import java.util.Objects;

/**
 * Abstracción (Bridge): Mantiene la referencia al implementador (CompressionCodec)
 * permitiendo combinar libremente cualquier tipo de mensaje con cualquier códec.
 */
public abstract class Message {
    protected final CompressionCodec codec;

    protected Message(CompressionCodec codec) {
        this.codec = Objects.requireNonNull(codec, "El códec no puede ser nulo");
    }

    public abstract void send(String recipient, byte[] rawPayload);
}
