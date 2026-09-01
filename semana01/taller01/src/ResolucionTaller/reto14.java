//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.List;

/**
 * ============================================================================
 * RETO 14: Verificar condición en todos los elementos con allMatch()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de notas académicas, determinar si todas son mayores o iguales a 3.0.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.allMatch(Predicate<T>):
 *    - Tipo: Operación Terminal (Eager / Short-Circuiting / Cortocircuito).
 *    - Complejidad: O(1) en el mejor caso (si el primer elemento falla), O(N) si todos aprueban.
 *    - Recibe un `Predicate<Double>` con la condición `nota >= 3.0`.
 *    - Cortocircuito: Retorna `false` inmediatamente al encontrar el primer elemento
 *      que no satisfaga la condición, sin evaluar el resto.
 */
public class reto14 {

    public static void main(String[] args) {
        List<Double> notas = List.of(4.0, 3.5, 4.2, 5.0, 3.8);

        // Se valida que la totalidad del conjunto cumpla la regla de negocio
        boolean notaMayorIgual3 = notas.stream()
                .allMatch(nota -> nota >= 3.0);

        // Salida esperada: Todos son mayores o iguales a 3.0?: true
        System.out.println("Todos son mayores o iguales a 3.0?: " + notaMayorIgual3);
    }
}
