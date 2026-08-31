//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO 5: Eliminar duplicados con colector con collect()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de correos electrónicos donde algunos están repetidos,
 * transformar la colección en una estructura que elimine automáticamente los
 * elementos duplicados.
 * 
 * CONCEPTOS FUNCIONALES Y ESTRUCTURALES CLAVE:
 * 1. Collectors.toCollection(TreeSet::new):
 *    - Tipo: Operación Terminal (Eager / Mutable Reduction).
 *    - Un `TreeSet` garantiza dos propiedades fundamentales:
 *      a) Unicidad: No permite elementos duplicados según su orden natural o comparator.
 *      b) Orden Natural: Mantiene los correos ordenados alfabéticamente (A -> Z).
 * 
 * 2. Alternativas Funcionales:
 *    - `.collect(Collectors.toSet())`: Devuelve un `HashSet` (unicidad rápida O(1), sin orden).
 *    - `.distinct().toList()`: Mantiene el orden de inserción original eliminando duplicados.
 */
public class reto5 {

    public static void main(String[] args) {
        List<String> correos = List.of(
                "a@correo.com", "b@correo.com", "a@correo.com", "c@correo.com", "b@correo.com"
        );

        // Se recolecta directamente en un TreeSet para asegurar unicidad y orden alfabético
        TreeSet<String> correosUnicos = correos.stream()
                .collect(Collectors.toCollection(TreeSet::new));

        // Salida esperada: Set ordenado con 3 elementos únicos: "a@correo.com", "b@correo.com", "c@correo.com"
        System.out.println("Set con 3 elementos unicos:");
        correosUnicos.forEach(correo -> System.out.println('"' + correo + '"'));
    }
}
