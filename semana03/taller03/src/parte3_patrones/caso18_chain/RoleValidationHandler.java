package parte3_patrones.caso18_chain;

public class RoleValidationHandler extends SecurityHandler {
    @Override
    public boolean handle(SecurityRequest request) {
        if (!"ADMIN".equalsIgnoreCase(request.role()) && !"MANAGER".equalsIgnoreCase(request.role())) {
            System.out.printf("  ✗ [Seguridad - Rol] Acceso denegado: El rol '%s' no tiene nivel suficiente.%n", request.role());
            return false;
        }
        System.out.printf("  ✓ [Seguridad - Rol] Rol '%s' verificado con éxito.%n", request.role());
        return checkNext(request);
    }
}
