package parte3_patrones.caso18_chain;

/**
 * Patrón: CHAIN OF RESPONSIBILITY (Comportamiento)
 * Manejador abstracto base: Gestiona el enlace al siguiente eslabón de la cadena.
 */
public abstract class SecurityHandler {
    private SecurityHandler next;

    public SecurityHandler linkWith(SecurityHandler next) {
        this.next = next;
        return next;
    }

    public abstract boolean handle(SecurityRequest request);

    protected boolean checkNext(SecurityRequest request) {
        if (next == null) {
            return true; // Todos los eslabones superados con éxito
        }
        return next.handle(request);
    }
}
