/**
 * INTEGRACIÓN BANCARIA - DEMOSTRACIÓN DE ADAPTER + FACADE
 * Enfoque: Capa Anticorrupción, Separación de Responsabilidades (SRP) y Clean
 * Code.
 */

// --- 1. SISTEMA LEGACY (El código que NO podemos modificar) ---
// Simulamos la clase antigua, pesada e incompatible del banco.
class LegacyBankService {
    public void step1_initNetwork() {
        System.out.println("[Legacy] 1. Red inicializada...");
    }

    public void step2_bindPort() {
        System.out.println("[Legacy] 2. Puerto vinculado...");
    }

    public void step3_handshake() {
        System.out.println("[Legacy] 3. Handshake completado...");
    }

    public void step4_createContext() {
        System.out.println("[Legacy] 4. Contexto creado...");
    }

    public void step5_openSession() {
        System.out.println("[Legacy] 5. Sesión abierta...");
    }

    public void step6_verifyCert() {
        System.out.println("[Legacy] 6. Certificado verificado...");
    }

    public void step7_allocateBuffer() {
        System.out.println("[Legacy] 7. Buffer asignado...");
    }

    public void step8_ready() {
        System.out.println("[Legacy] 8. Sistema listo para operar.");
    }

    // Método de pago incompatible (requiere centavos como entero)
    public void executeTransaction(String accountCode, int cents) {
        System.out.println("[Legacy] TRANSACCIÓN EJECUTADA: Cuenta=" + accountCode + ", Centavos=" + cents);
    }
}

// --- 2. DOMINIO MODERNO (Nuestro código limpio) ---
// La interfaz estándar que el resto de nuestra aplicación espera usar.
interface PaymentProcessor {
    void pay(double amount);
}

// --- 3. PATRÓN ADAPTER (Traductor de Idiomas) ---[cite: 2]
// Convierte el llamado moderno a las primitivas del sistema legacy.
class LegacyBankAdapter implements PaymentProcessor {
    private final LegacyBankService legacyService;
    private final String defaultAccount;

    public LegacyBankAdapter(LegacyBankService legacyService, String defaultAccount) {
        this.legacyService = legacyService;
        this.defaultAccount = defaultAccount;
    }

    @Override
    public void pay(double amount) {
        // XP: Traducción explícita y documentada
        int cents = (int) Math.round(amount * 100);
        System.out.println("[Adapter] Traduciendo monto moderno ($" + amount + ") a centavos (" + cents + ")");

        legacyService.executeTransaction(this.defaultAccount, cents);
    }
}

// --- 4. PATRÓN FACADE (Orquestador de Complejidad) ---[cite: 2]
// Oculta los detalles tóxicos de infraestructura a los desarrolladores
// modernos.
class BankFacade {
    private final LegacyBankService legacyService;
    private final PaymentProcessor adapter;

    // DIP: Inyectamos el servicio, lo configuramos e inicializamos el Adapter
    // internamente.
    public BankFacade(String accountCode) {
        this.legacyService = new LegacyBankService();
        this.adapter = new LegacyBankAdapter(this.legacyService, accountCode);
    }

    // El único método que nuestros desarrolladores modernos verán y usarán.
    public void procesarPago(double monto) {
        System.out.println("--- FACADE: Iniciando conexión segura con Banco ---");
        initializeLegacySystem();

        System.out.println("--- FACADE: Delegando al Adapter ---");
        adapter.pay(monto);
    }

    // Encapsula los "8 pasos del infierno" que los devs no deben conocer[cite: 2].
    private void initializeLegacySystem() {
        legacyService.step1_initNetwork();
        legacyService.step2_bindPort();
        legacyService.step3_handshake();
        legacyService.step4_createContext();
        legacyService.step5_openSession();
        legacyService.step6_verifyCert();
        legacyService.step7_allocateBuffer();
        legacyService.step8_ready();
    }
}

// --- 5. DEMOSTRACIÓN FUNCIONAL ---
public class BankingIntegrationSystem {
    public static void main(String[] args) {
        System.out.println("=== CLIENTE MODERNO DE E-COMMERCE ===");
        double cartTotal = 145.50; // Monto moderno en dólares/pesos decimales

        // El cliente (nuestro e-commerce) solo necesita interactuar con la Facade.
        // No instanciamos el servicio legacy, no llamamos los 8 pasos, no calculamos
        // centavos.
        BankFacade facade = new BankFacade("ACC-9982-COL");

        System.out.println("Solicitando pago de: $" + cartTotal + "\n");
        facade.procesarPago(cartTotal);
    }
}
