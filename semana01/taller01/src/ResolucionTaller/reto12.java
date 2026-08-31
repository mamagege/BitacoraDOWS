//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.Comparator;
import java.util.List;

/**
 * ============================================================================
 * RETO 12: Encontrar el valor máximo con max(Comparator)
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de salarios, determinar cuál es el salario más alto utilizando Streams.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.max(Comparator<T>):
 *    - Tipo: Operación Terminal (Eager / Reducción).
 *    - Complejidad: O(N).
 *    - Compara secuencialmente los elementos y retorna un `Optional<T>` con el máximo.
 * 
 * 2. Optional.orElse(T other):
 *    - Provee un valor por defecto (fallback) en caso de que la lista esté vacía,
 *      garantizando que la aplicación nunca falle por referencias nulas.
 */
public class reto12 {

    public static void main(String[] args) {
        List<Integer> salarios = List.of(
                1800000, 2500000, 3200000, 2100000, 4000000
        );

        // Se busca el salario máximo con valor fallback de 0 si la lista estuviera vacía
        Integer maxSalario = salarios.stream()
                .max(Comparator.naturalOrder())
                .orElse(0);

        // Salida esperada: Salario maximo: 4000000
        System.out.println("Salario maximo: " + maxSalario);
    }
}
