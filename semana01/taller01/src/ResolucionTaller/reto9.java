//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO 9: Limitar cantidad de resultados con limit()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de 20 mejores puntuaciones de un videojuego, obtener
 * únicamente los primeros 5 elementos con mayor puntuación para armar el ranking Top 5.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.limit(long maxSize):
 *    - Tipo: Operación Intermedia (Lazy / Short-Circuiting / Cortocircuito).
 *    - Complejidad: O(K) donde K = maxSize.
 *    - Trunca el flujo para no emitir más de `maxSize` elementos.
 *    - Optimización: Cuando se combina con fuentes infinitas o flujos masivos,
 *      garantiza que el procesamiento se detenga exactamente al alcanzar el límite.
 * 
 * 2. Composición de Pipeline (Top N):
 *    [ 20 Elementos ] -> sorted(Descendente) -> limit(5) -> collect(Lista)
 */
public class reto9 {

    public static void main(String[] args) {
        List<Integer> puntuaciones = List.of(
                4500, 1250, 8900, 3200, 5600,
                9100, 2300, 7850, 1100, 6700,
                8400, 9950, 3400, 5100, 2750,
                6200, 1900, 8800, 4100, 7300
        );

        // 1. Ordenar de mayor a menor con Comparator.reverseOrder()
        // 2. Truncar a los 5 primeros con .limit(5)
        List<Integer> top5 = puntuaciones.stream()
                .sorted(Comparator.reverseOrder())
                .limit(5)
                .collect(Collectors.toList());

        // Salida esperada: [9950, 9100, 8900, 8800, 8400]
        System.out.println("Top 5 puntuaciones: " + top5);
    }
}
