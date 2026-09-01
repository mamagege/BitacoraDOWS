package parte1_solid.caso4_cajero;

/**
 * Cajero básico antiguo. Solo implementa Withdrawable porque es su única capacidad real.
 * No es forzado a implementar métodos dummy o lanzar excepciones (Cumple ISP).
 */
public class BasicATM implements Withdrawable {
    private final String atmId;

    public BasicATM(String atmId) {
        this.atmId = atmId;
    }

    @Override
    public void withdraw(double amount) {
        System.out.printf("[BasicATM - %s] Retiro exitoso de $%.2f en moneda local.%n", atmId, amount);
    }
}
