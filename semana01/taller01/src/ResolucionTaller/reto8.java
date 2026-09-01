//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO 8: Eliminar valores repetidos con distinct()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de códigos de producto con repeticiones, generar una nueva
 * colección donde cada código aparezca una sola vez, preservando el orden de aparición.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.distinct():
 *    - Tipo: Operación Intermedia (Lazy / Stateful - Con Estado).
 *    - Complejidad: O(N) en tiempo, usando internamente una tabla Hash para
 *      rastrear elementos previamente vistos.
 *    - Requiere que los objetos implementen correctamente los métodos
 *      `equals(Object o)` y `hashCode()`.
 *    - A diferencia de convertir a un `HashSet`, `.distinct()` en un stream
 *      secuencial **preserva el orden de inserción original** de los elementos.
 */
public class reto8 {

    public static void main(String[] args) {
        List<String> codigos = List.of(
                "P01", "P02", "P01", "P03", "P02", "P04"
        );

        // Se eliminan duplicados manteniendo el orden del primer encuentro
        List<String> codigosUnicos = codigos.stream()
                .distinct()
                .collect(Collectors.toList());

        // Salida esperada: [P01, P02, P03, P04]
        System.out.println("Códigos únicos (sin duplicados): " + codigosUnicos);
    }
}
