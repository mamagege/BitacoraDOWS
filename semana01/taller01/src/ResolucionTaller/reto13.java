//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.List;

/**
 * ============================================================================
 * RETO 13: Verificar si existe al menos un par con anyMatch()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de números, verificar si existe al menos un número par dentro
 * de la colección.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.anyMatch(Predicate<T>):
 *    - Tipo: Operación Terminal (Eager / Short-Circuiting / Cortocircuito).
 *    - Complejidad: O(1) en el mejor caso, O(N) en el peor caso.
 *    - Recibe un `Predicate<Integer>` que evalúa la condición de paridad `(numero % 2 == 0)`.
 *    - Comportamiento de Cortocircuito: En cuanto encuentra el primer número par
 *      (en este caso 20), el Stream detiene de inmediato la evaluación de los
 *      elementos restantes y retorna `true`, ahorrando ciclos de CPU.
 */
public class reto13 {

    public static void main(String[] args) {
        List<Integer> numeros = List.of(7, 11, 13, 20, 25);

        // anyMatch cortocircuita al evaluar el 20 (cuarto elemento)
        boolean existePar = numeros.stream()
                .anyMatch(numero -> (numero % 2 == 0));

        // Salida esperada: Hay algun par?: true
        System.out.println("Hay algun par?: " + existePar);
    }
}