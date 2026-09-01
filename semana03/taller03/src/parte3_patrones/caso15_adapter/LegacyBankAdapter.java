package parte3_patrones.caso15_adapter;

import java.util.Objects;

/**
 * Patrón: ADAPTER (Estructural)
 * ¿Por qué?: Adapta la interfaz incompatible de LegacyBankService al contrato
 * ModernPaymentProcessor sin modificar el código del banco antiguo.
 */
public class LegacyBankAdapter implements ModernPaymentProcessor {
    private final LegacyBankService legacyBankService;

    public LegacyBankAdapter(LegacyBankService legacyBankService) {
        this.legacyBankService = Objects.requireNonNull(legacyBankService, "El servicio legado no puede ser nulo");
    }

    @Override
    public void modernPay(String accountNumber, double amountInDollars) {
        System.out.printf("[LegacyBankAdapter] Adaptando llamada moderna ($%.2f USD) a protocolo legado...%n", amountInDollars);
        
        // 1. Verificación de balance previo
        if (!legacyBankService.verifyBalance(accountNumber)) {
            throw new IllegalStateException("Fondos insuficientes en el sistema bancario legado");
        }

        // 2. Conversión de dólares a centavos (requerimiento de la API antigua)
        long cents = Math.round(amountInDollars * 100);

        // 3. Delegación al servicio legado
        legacyBankService.executeTransaction(accountNumber, cents);
        System.out.println("[LegacyBankAdapter] Transacción legada completada y respuesta normalizada.");
    }
}
