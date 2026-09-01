package edu.dosw.lab01.ejerciciosPresentacion;

import java.util.List;

/**
 * EJERCICIO #5:
 * Dada una lista de transacciones bancarias representadas por objetos:
 * class Transaction {
 * String id;
 * double amount;
 * boolean approved;
 * }
 * 
 * Se requiere procesar la lista usando Streams para:
 * - Usar peek para ver cada transacción procesada (Utilizar System.out::println
 * para ver la transacción).
 * - Verificar si existe al menos una transacción no aprobada.
 * - Retornar true o false indicando si el lote de transacciones es válido (es
 * válido si NO hay transacciones no aprobadas).
 */
public class Ejercicio05 {

    // Definición de la entidad Transaction como Record para inmutabilidad y
    // concisión
    public record Transaction(String id, double amount, boolean approved) {
        // Alias para compatibilidad con getter isApproved()
        public boolean isApproved() {
            return approved;
        }
    }

    /**
     * Valida si un lote de transacciones es válido.
     * Un lote es válido si TODAS las transacciones están aprobadas (ninguna no
     * aprobada).
     */
    public static boolean esLoteValido(List<Transaction> transactions) {
        // Explicación de la solución:
        // 1. .stream(): Inicia la secuencia de procesamiento.
        // 2. .peek(System.out::println): Inspecciona cada elemento en tránsito por el
        // flujo imprimiéndolo en consola.
        // 3. .anyMatch(transaction -> !transaction.isApproved()): Evalúa si EXISTE AL
        // MENOS UNA transacción
        // que no esté aprobada (!isApproved). Retorna true si encuentra al menos una
        // rechazada.
        // 4. return !hasUnapprovedTransaction: Niega el resultado anterior. Si existían
        // no aprobadas (true),
        // el lote no es válido (false). Si no existían no aprobadas (false), el lote es
        // válido (true).
        boolean hasUnapprovedTransaction = transactions.stream()
                .peek(System.out::println)
                .anyMatch(transaction -> !transaction.isApproved());

        return !hasUnapprovedTransaction;
    }

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("TX-001", 120.50, true),
                new Transaction("TX-002", 350.00, true),
                new Transaction("TX-003", 90.25, false),
                new Transaction("TX-004", 500.00, true));

        System.out.println("--- Ejercicio 05 ---");
        System.out.println("Procesando lote de transacciones con peek:");
        boolean esValido = esLoteValido(transactions);

        System.out.println("\n¿El lote de transacciones es válido? -> " + esValido);
    }
}
