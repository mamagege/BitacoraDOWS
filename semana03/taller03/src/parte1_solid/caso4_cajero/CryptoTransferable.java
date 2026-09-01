package parte1_solid.caso4_cajero;

/**
 * Interfaz segregada para transferencias y operaciones cripto.
 */
public interface CryptoTransferable {
    void transferCrypto(String walletAddress, double amountInCrypto);
}
