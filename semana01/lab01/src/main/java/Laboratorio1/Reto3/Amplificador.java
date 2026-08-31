package Reto3;

import java.util.stream.*;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 3: Amplificador de Mensajes (IntStream + joining)
 * ============================================================================
 *
 * OBJETIVO:
 * Repetir una palabra 3 veces separadas por espacio de forma funcional.
 * Ej: "hola" → "hola hola hola"
 *
 * CONCEPTOS FUNCIONALES CLAVE:
 *
 * 1. `IntStream.range(0, 3)`:
 *    - Crea un stream primitivo de enteros [0, 1, 2] (3 elementos).
 *    - Alternativa eficiente y sin boxing a `Stream.of(0, 1, 2)` para rangos numéricos.
 *    - La semilla (0) y el límite exclusivo (3) siguen la convención de índices Java.
 *
 * 2. `.mapToObj(i -> palabra)`:
 *    - Convierte cada entero del `IntStream` en un `String` (la misma `palabra`).
 *    - Resultado: un `Stream<String>` con 3 copias de `palabra`.
 *
 * 3. `.collect(Collectors.joining(" "))`:
 *    - Operación Terminal que concatena todos los Strings del stream
 *      insertando " " (espacio) como delimitador entre ellos.
 *    - Internamente usa `StringBuilder` para hacer las concatenaciones sin
 *      crear objetos `String` intermedios (eficiente en memoria).
 *    - Es la alternativa funcional y moderna al clásico `sb.append(x + " ")`.
 */
public class Amplificador {

    /**
     * Repite la `palabra` 3 veces separada por espacios usando un pipeline funcional.
     *
     * @param palabra La palabra que se desea amplificar.
     * @return String resultante. Ej: "hola" → "hola hola hola"
     */
    public static String amplificar(String palabra) {
        return IntStream.range(0, 3)
                // Genera un stream de 3 enteros: [0, 1, 2]
                .mapToObj(i -> palabra)
                // Convierte cada entero a la misma `palabra` → Stream<String>["hola","hola","hola"]
                .collect(Collectors.joining(" "));
                // Une los 3 strings con " " como separador → "hola hola hola"
    }
}
