package parte1_solid.caso4_cajero;

/**
 * Interfaz segregada para autenticación biométrica de usuarios.
 */
public interface BiometricCapable {
    boolean validateBiometrics(String biometricToken);
}
