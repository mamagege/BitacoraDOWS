package parte3_patrones.caso18_chain;

public class PermissionValidationHandler extends SecurityHandler {
    @Override
    public boolean handle(SecurityRequest request) {
        if ("/api/v1/vault/secret-keys".equalsIgnoreCase(request.requestedResource()) && !"ADMIN".equalsIgnoreCase(request.role())) {
            System.out.printf("  ✗ [Seguridad - Permisos] Permiso insuficiente para el recurso '%s'.%n", request.requestedResource());
            return false;
        }
        System.out.printf("  ✓ [Seguridad - Permisos] Permiso concedido para acceder a '%s'.%n", request.requestedResource());
        return checkNext(request);
    }
}
