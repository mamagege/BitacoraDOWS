//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO 3: Transformar a mayúsculas con map()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de nombres de ciudades, generar una nueva lista donde todas
 * estén escritas completamente en mayúsculas.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.map(Function<T, R>):
 *    - Tipo: Operación Intermedia (Lazy / Stateless).
 *    - Complejidad: O(N).
 *    - Recibe una `Function<String, String>` que define una transformación 1 a 1.
 * 
 * 2. Referencias a Métodos (Method Reference `String::toUpperCase`):
 *    - Sintaxis ultra-compacta que equivale exactamente a la lambda `ciudad -> ciudad.toUpperCase()`.
 *    - Mejora la legibilidad y reduce el ruido sintáctico.
 * 
 * 3. Inmutabilidad:
 *    - La lista original `ciudades` permanece intacta. Se genera una nueva
 *      estructura que contiene los datos transformados.
 */
public class reto3 {

    public static void main(String[] args) {
        List<String> ciudades = List.of(
                "Bogotá", "Medellín", "Cali", "Barranquilla"
        );

        // Pipeline: Fuente -> map (transformación 1:1) -> collect (materialización)
        List<String> ciudadesMayusculas = ciudades.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(ArrayList::new));

        // Salida esperada: [BOGOTÁ, MEDELLÍN, CALI, BARRANQUILLA]
        System.out.println("Ciudades en mayúsculas: " + ciudadesMayusculas);
    }
}
