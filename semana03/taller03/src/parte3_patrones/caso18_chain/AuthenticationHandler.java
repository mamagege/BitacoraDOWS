package parte3_patrones.caso18_chain;

public class AuthenticationHandler extends SecurityHandler {
    @Override
    public boolean handle(SecurityRequest request) {
        if (request.authToken() == null || !request.authToken().startsWith("Bearer valid-jwt-token")) {
            System.out.printf("  ✗ [Seguridad - Autenticación] Acceso denegado: Token inválido para '%s'.%n", request.username());
            return false;
        }
        System.out.printf("  ✓ [Seguridad - Autenticación] Usuario '%s' autenticado correctamente.%n", request.username());
        return checkNext(request);
    }
}
