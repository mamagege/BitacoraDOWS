package parte3_patrones.caso16_bridge;

public class AacCodec implements CompressionCodec {
    @Override
    public byte[] compress(byte[] rawData) {
        System.out.printf("    ↳ [Codec AAC] Comprimiendo audio de alta fidelidad Advanced Audio Coding.%n");
        return rawData;
    }

    @Override
    public String getCodecName() {
        return "AAC";
    }
}
