package parte3_patrones.caso18_chain;

/**
 * Petición de seguridad que fluye a través de la cadena de validaciones.
 */
public record SecurityRequest(
        String username,
        String authToken,
        String role,
        String requestedResource,
        String ipCountry,
        String mfaCode,
        boolean isTrustedDevice
) {}
