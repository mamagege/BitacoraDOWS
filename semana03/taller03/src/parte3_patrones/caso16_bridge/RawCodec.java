package parte3_patrones.caso16_bridge;

public class RawCodec implements CompressionCodec {
    @Override
    public byte[] compress(byte[] rawData) {
        System.out.printf("    ↳ [Codec Raw/Plano] Sin compresión aplicada (Passthrough).%n");
        return rawData;
    }

    @Override
    public String getCodecName() {
        return "Raw (Sin compresión)";
    }
}
