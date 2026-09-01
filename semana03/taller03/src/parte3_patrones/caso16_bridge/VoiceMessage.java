package parte3_patrones.caso16_bridge;

public class VoiceMessage extends Message {
    private final int durationSeconds;

    public VoiceMessage(CompressionCodec codec, int durationSeconds) {
        super(codec);
        this.durationSeconds = durationSeconds;
    }

    @Override
    public void send(String recipient, byte[] rawPayload) {
        System.out.printf("[Nota de Voz - %ds] Preparando envío hacia %s...%n", durationSeconds, recipient);
        byte[] compressed = codec.compress(rawPayload);
        System.out.printf("  ✓ Audio de %ds enviado comprimido con algoritmo %s.%n", durationSeconds, codec.getCodecName());
    }
}
