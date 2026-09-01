package parte3_patrones.caso18_chain;

public class MfaValidationHandler extends SecurityHandler {
    @Override
    public boolean handle(SecurityRequest request) {
        if (!request.isTrustedDevice()) {
            if (request.mfaCode() == null || !request.mfaCode().equals("987654")) {
                System.out.printf("  ✗ [Seguridad - MFA] Dispositivo no confiable y código MFA inválido o ausente.%n");
                return false;
            }
            System.out.println("  ✓ [Seguridad - MFA] Autenticación multifactor validada satisfactoriamente.");
        } else {
            System.out.println("  ✓ [Seguridad - MFA] Dispositivo de confianza registrado (MFA bypassed de forma segura).");
        }
        return checkNext(request);
    }
}
