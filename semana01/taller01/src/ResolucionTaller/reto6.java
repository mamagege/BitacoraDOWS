//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO 6: Registrar transformaciones intermedias con peek()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de empleados, convertir los nombres a mayúsculas y registrar
 * en consola cada transformación realizada antes de guardar el resultado final en una lista.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.peek(Consumer<T>):
 *    - Tipo: Operación Intermedia (Lazy / Stateless).
 *    - Complejidad: O(N).
 *    - Propósito Técnico: Diseñado exclusivamente para **depuración (*Debugging*)** y observabilidad
 *      intermedia sin interrumpir ni mutar el flujo de elementos.
 *    - Buenas Prácticas: No uses `peek()` para modificar estados de negocio o alterar objetos;
 *      su uso debe limitarse a logs de diagnóstico y trazabilidad.
 * 
 * 2. Pipeline Multietapa:
 *    [ Fuente ] -> map (mayúsculas) -> peek (log intermedio) -> collect (materialización final)
 */
public class reto6 {

    public static void main(String[] args) {
        List<String> nombres = List.of("Laura", "Pedro", "Carlos", "Ana");

        // El pipeline aplica la transformación y permite inspeccionar cada elemento en tránsito
        List<String> nuevosNombres = nombres.stream()
                .map(String::toUpperCase)
                .peek(nombre -> System.out.println("Transformado: " + nombre))
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Lista: " + nuevosNombres);
    }
}
