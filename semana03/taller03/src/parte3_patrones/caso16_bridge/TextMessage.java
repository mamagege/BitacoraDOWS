package parte3_patrones.caso16_bridge;

import java.nio.charset.StandardCharsets;

public class TextMessage extends Message {
    public TextMessage(CompressionCodec codec) {
        super(codec);
    }

    @Override
    public void send(String recipient, byte[] rawPayload) {
        System.out.printf("[Mensaje de Texto] Preparando envío hacia %s...%n", recipient);
        byte[] compressed = codec.compress(rawPayload);
        String text = new String(rawPayload, StandardCharsets.UTF_8);
        System.out.printf("  ✓ Mensaje de texto (\"%s\") despachado con códec %s.%n", text, codec.getCodecName());
    }
}
