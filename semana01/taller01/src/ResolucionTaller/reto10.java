//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO 10: Saltar los primeros elementos con skip()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de películas, ignorar las dos primeras y obtener una nueva
 * lista con las películas restantes.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.skip(long n):
 *    - Tipo: Operación Intermedia (Lazy / Stateful).
 *    - Complejidad: O(N) para recorrer y descartar los primeros `n` elementos.
 *    - Descarta los primeros `n` elementos del stream y emite el resto sin modificar.
 * 
 * 2. Patrón de Paginación en Arquitecturas Empresariales:
 *    - La combinación `.skip((page - 1) * pageSize).limit(pageSize)` constituye
 *      el estándar para implementar paginación funcional en colecciones en memoria.
 */
public class reto10 {

    public static void main(String[] args) {
        List<String> peliculas = List.of(
                "Avatar", "Titanic", "Interestelar", "Matrix", "Gladiador"
        );

        // Se descartan los dos primeros elementos ("Avatar", "Titanic")
        List<String> peliculasSinPrimeras2 = peliculas.stream()
                .skip(2)
                .collect(Collectors.toList());

        // Salida esperada: [Interestelar, Matrix, Gladiador]
        System.out.println("Películas restantes: " + peliculasSinPrimeras2);
    }
}
