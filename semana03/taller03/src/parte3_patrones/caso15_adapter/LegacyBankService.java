package parte3_patrones.caso15_adapter;

/**
 * Servicio legado que no puede modificarse (Adaptee).
 * Opera con centavos y métodos antiguos.
 */
public class LegacyBankService {
    public boolean verifyBalance(String account) {
        System.out.printf("[LegacyBank] Verificando solvencia y fondos para cuenta %s... APROBADO.%n", account);
        return true;
    }

    public void executeTransaction(String account, long cents) {
        System.out.printf("[LegacyBank] Ejecución transaccional COBOL/Mainframe: Cuenta %s débitada por %d centavos.%n",
                account, cents);
    }
}
