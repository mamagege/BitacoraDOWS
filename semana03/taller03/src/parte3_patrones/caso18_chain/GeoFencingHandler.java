package parte3_patrones.caso18_chain;

import java.util.Set;

public class GeoFencingHandler extends SecurityHandler {
    private static final Set<String> ALLOWED_COUNTRIES = Set.of("CO", "US", "CA", "ES");

    @Override
    public boolean handle(SecurityRequest request) {
        if (!ALLOWED_COUNTRIES.contains(request.ipCountry().toUpperCase())) {
            System.out.printf("  ✗ [Seguridad - GeoFencing] Acceso bloqueado desde país no autorizado: %s.%n", request.ipCountry());
            return false;
        }
        System.out.printf("  ✓ [Seguridad - GeoFencing] Origen geográfico válido (%s).%n", request.ipCountry());
        return checkNext(request);
    }
}
