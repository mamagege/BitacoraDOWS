package parte3_patrones.caso16_bridge;

public class H264Codec implements CompressionCodec {
    @Override
    public byte[] compress(byte[] rawData) {
        System.out.printf("    ↳ [Codec H.264 / AVC] Compresión de fotogramas por predicción espacial/temporal.%n");
        return rawData;
    }

    @Override
    public String getCodecName() {
        return "H.264";
    }
}
