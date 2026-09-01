package parte1_solid.caso4_cajero;

/**
 * Cajero inteligente moderno con todas las capacidades.
 * Implementa múltiples interfaces de rol específicas.
 */
public class FullFeaturedSmartATM implements Withdrawable, Depositable, StatementPrintable, BiometricCapable, CryptoTransferable {
    private final String atmId;

    public FullFeaturedSmartATM(String atmId) {
        this.atmId = atmId;
    }

    @Override
    public void withdraw(double amount) {
        System.out.printf("[SmartATM - %s] Retirando $%.2f con dispensador de alta velocidad.%n", atmId, amount);
    }

    @Override
    public void deposit(double amount) {
        System.out.printf("[SmartATM - %s] Depósito de $%.2f recibido y validado.%n", atmId, amount);
    }

    @Override
    public void printStatement() {
        System.out.printf("[SmartATM - %s] Imprimiendo extracto en papel térmico.%n", atmId);
    }

    @Override
    public boolean validateBiometrics(String biometricToken) {
        System.out.printf("[SmartATM - %s] Validación biométrica exitosa para token %s.%n", atmId, biometricToken);
        return true;
    }

    @Override
    public void transferCrypto(String walletAddress, double amountInCrypto) {
        System.out.printf("[SmartATM - %s] Transferencia de %.4f BTC enviada a la wallet %s.%n", atmId, amountInCrypto, walletAddress);
    }
}
