//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO 1: Filtrar por letra inicial con filter()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de nombres de estudiantes, obtener una nueva lista que
 * contenga únicamente los nombres que empiezan por la letra 'A'.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.filter(Predicate<T>):
 *    - Tipo: Operación Intermedia (Lazy / Stateless).
 *    - Complejidad: O(N).
 *    - Recibe un `Predicate<String>` (condición booleana). Si devuelve `true`,
 *      el elemento continúa por la canalización; si es `false`, se descarta.
 * 
 * 2. Collectors.toCollection(ArrayList::new):
 *    - Tipo: Operación Terminal (Eager / Mutable Reduction).
 *    - Acumula los elementos filtrados en una instancia específica de `ArrayList`.
 *    - Nota moderna: En Java 16+ se puede simplificar con `.toList()` si se
 *      desea una lista inmutable.
 */
public class reto1 {

    public static void main(String[] args) {
        // Colección fuente inmutable
        List<String> estudiantes = List.of(
                "Ana", "Carlos", "Andres", "Pedro", "Alejandra", "Juan", "Amanda"
        );

        // Pipeline Funcional:
        // 1. .stream(): Inicia la secuencia bajo demanda.
        // 2. .filter(): Evalúa la condición funcional 'startsWith("A")'.
        // 3. .collect(): Materializa el flujo resultante en una nueva lista.
        List<String> estudiantesA = estudiantes.stream()
                .filter(estudiante -> estudiante.startsWith("A"))
                .collect(Collectors.toCollection(ArrayList::new));

        // Salida esperada: [Ana, Andres, Alejandra, Amanda]
        System.out.println("Estudiantes que inician con 'A': " + estudiantesA);
    }
}
