package parte3_patrones.caso16_bridge;

public class VideoMessage extends Message {
    private final String resolution;

    public VideoMessage(CompressionCodec codec, String resolution) {
        super(codec);
        this.resolution = resolution;
    }

    @Override
    public void send(String recipient, byte[] rawPayload) {
        System.out.printf("[Mensaje de Video - %s] Preparando envío hacia %s...%n", resolution, recipient);
        byte[] compressed = codec.compress(rawPayload);
        System.out.printf("  ✓ Stream de video (%s) despachado exitosamente con compresión %s.%n", resolution, codec.getCodecName());
    }
}
