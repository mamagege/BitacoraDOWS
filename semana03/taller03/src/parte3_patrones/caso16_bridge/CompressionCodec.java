package parte3_patrones.caso16_bridge;

/**
 * Patrón: BRIDGE (Estructural)
 * Implementador (Implementor): Define la interfaz para los algoritmos de compresión.
 */
public interface CompressionCodec {
    byte[] compress(byte[] rawData);
    String getCodecName();
}
