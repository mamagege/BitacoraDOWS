package parte3_patrones.caso16_bridge;

public class Mp3Codec implements CompressionCodec {
    @Override
    public byte[] compress(byte[] rawData) {
        System.out.printf("    ↳ [Codec MP3] Comprimiendo audio con tasa 128kbps (Ratio 10:1).%n");
        return rawData;
    }

    @Override
    public String getCodecName() {
        return "MP3";
    }
}
