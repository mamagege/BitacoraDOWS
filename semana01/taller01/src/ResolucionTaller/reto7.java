//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO 7: Ordenar una colección con sorted()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de edades, obtener una nueva lista ordenada de menor a mayor
 * (ascendente) y otra de mayor a menor (descendente).
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.sorted():
 *    - Tipo: Operación Intermedia (Lazy / Stateful - Con Estado).
 *    - Complejidad: O(N log N).
 *    - Utiliza el orden natural (`Comparable`) de los elementos.
 *    - Al ser *Stateful*, requiere acumular internamente todos los elementos
 *      del flujo antes de poder emitir el primer elemento ordenado.
 * 
 * 2. Stream.sorted(Comparator<T>):
 *    - Permite inyectar estrategias de comparación personalizadas.
 *    - `Comparator.reverseOrder()`: Invierte el orden natural para lograr un
 *      ordenamiento estrictamente descendente.
 */
public class reto7 {

    public static void main(String[] args) {
        List<Integer> edades = List.of(25, 18, 32, 21, 19, 28);

        // 1. Ordenamiento Ascendente (Orden Natural: Menor a Mayor)
        List<Integer> edadAscendente = edades.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Ascendente: ");
        System.out.println(edadAscendente);

        // 2. Ordenamiento Descendente (Comparator.reverseOrder(): Mayor a Menor)
        List<Integer> edadDescendente = edades.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("\nDescendente: ");
        System.out.println(edadDescendente);
    }
}
