/**
 * SISTEMA DE AUTENTICACIÓN EMPRESARIAL - STRATEGY + CHAIN OF RESPONSIBILITY
 * Enfoque: Separación de Identidad y Autorización, Principio OCP y Fail-Fast.
 */

// --- MODELOS DE DOMINIO ---
// Representa las credenciales entrantes
class Credentials {
    public final String userId;
    public final String payload; // Puede ser un token, un hash de password, etc.

    public Credentials(String userId, String payload) {
        this.userId = userId;
        this.payload = payload;
    }
}

// Representa el perfil del usuario tras ser identificado
class UserContext {
    public final String userId;
    public final String role;
    public final String location;
    public final int currentHour;

    public UserContext(String userId, String role, String location, int currentHour) {
        this.userId = userId;
        this.role = role;
        this.location = location;
        this.currentHour = currentHour;
    }
}

// --- PATRÓN 1: STRATEGY (Fase de Identidad) ---[cite: 2]
interface AuthStrategy {
    UserContext authenticate(Credentials c);
}

class PasswordStrategy implements AuthStrategy {
    @Override
    public UserContext authenticate(Credentials c) {
        System.out.println("[Strategy] Autenticando vía Usuario/Contraseña...");
        if (!"secreto123".equals(c.payload))
            throw new SecurityException("Contraseña inválida");
        return new UserContext(c.userId, "ADMIN", "CO", 14); // Simulación de DB
    }
}

class BiometricStrategy implements AuthStrategy {
    @Override
    public UserContext authenticate(Credentials c) {
        System.out.println("[Strategy] Autenticando vía Huella Dactilar...");
        if (!c.payload.startsWith("FINGERPRINT_"))
            throw new SecurityException("Biometría no coincide");
        return new UserContext(c.userId, "EMPLOYEE", "USA", 22);
    }
}

// --- PATRÓN 2: CHAIN OF RESPONSIBILITY (Fase de Autorización) ---[cite: 2]
abstract class SecurityValidator {
    private SecurityValidator next;

    public SecurityValidator setNext(SecurityValidator next) {
        this.next = next;
        return this.next; // Fluent interface
    }

    public void validate(UserContext context) {
        // Ejecuta la validación propia de la clase hija
        check(context);
        // Si pasa la validación y hay un siguiente eslabón, delega
        if (next != null) {
            next.validate(context);
        }
    }

    // Método abstracto protegido: Obliga a cada validador a implementar su regla
    // (SRP)
    protected abstract void check(UserContext context);
}

class PermissionValidator extends SecurityValidator {
    @Override
    protected void check(UserContext context) {
        System.out.println("[Chain] Validando permisos para rol: " + context.role);
        if ("GUEST".equals(context.role)) {
            throw new SecurityException("Acceso denegado: Permisos insuficientes.");
        }
    }
}

class LocationValidator extends SecurityValidator {
    @Override
    protected void check(UserContext context) {
        System.out.println("[Chain] Validando ubicación geográfica: " + context.location);
        if ("RU".equals(context.location)) { // Ejemplo de geobloqueo
            throw new SecurityException("Acceso denegado: Región bloqueada.");
        }
    }
}

class TimeValidator extends SecurityValidator {
    @Override
    protected void check(UserContext context) {
        System.out.println("[Chain] Validando horario laboral: " + context.currentHour + ":00");
        if (context.currentHour < 8 || context.currentHour > 18) {
            throw new SecurityException("Acceso denegado: Fuera del horario laboral permitido.");
        }
    }
}

// --- FACHADA Y CONTEXTO (El Servicio Core) ---
class AuthService {
    private final SecurityValidator securityChain;

    // DIP: El servicio requiere que le inyecten la cadena pre-configurada
    public AuthService(SecurityValidator securityChain) {
        this.securityChain = securityChain;
    }

    // El flujo principal orquestado
    public void login(Credentials credentials, AuthStrategy strategy) {
        try {
            System.out.println("\n=== INICIANDO PROCESO DE LOGIN ===");
            // 1. Identidad (Strategy)[cite: 2]
            UserContext authenticatedUser = strategy.authenticate(credentials);

            // 2. Autorización (Chain)[cite: 2]
            securityChain.validate(authenticatedUser);

            System.out.println("[ÉXITO] Acceso concedido al sistema para: " + authenticatedUser.userId);
        } catch (SecurityException e) {
            System.out.println("[FALLO] " + e.getMessage());
        }
    }
}

// --- DEMOSTRACIÓN FUNCIONAL ---
public class EnterpriseAuthSystem {
    public static void main(String[] args) {
        // 1. Configuración de la infraestructura (Generalmente se hace al iniciar la
        // app o vía Spring/Guice)
        SecurityValidator pipeline = new PermissionValidator();
        pipeline.setNext(new LocationValidator())
                .setNext(new TimeValidator());

        AuthService authService = new AuthService(pipeline);

        // CASO 1: Admin entra con clave en horario hábil
        Credentials cred1 = new Credentials("admin_juan", "secreto123");
        authService.login(cred1, new PasswordStrategy());

        // CASO 2: Empleado intenta acceder con biometría fuera de horario (Ej. 22:00)
        Credentials cred2 = new Credentials("emp_maria", "FINGERPRINT_A98X");
        authService.login(cred2, new BiometricStrategy());
    }
}