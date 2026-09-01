package parte3_patrones.caso16_bridge;

public class HevcCodec implements CompressionCodec {
    @Override
    public byte[] compress(byte[] rawData) {
        System.out.printf("    ↳ [Codec H.265 / HEVC] Compresión avanzada 4K/8K (50%% mayor eficiencia).%n");
        return rawData;
    }

    @Override
    public String getCodecName() {
        return "H.265 (HEVC)";
    }
}
