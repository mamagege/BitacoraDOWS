//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.List;

/**
 * ============================================================================
 * RETO 4: Suma total de una lista con reduce()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de números enteros, calcular la suma total de todos los
 * elementos utilizando una operación de reducción funcional.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.reduce(T identity, BinaryOperator<T> accumulator):
 *    - Tipo: Operación Terminal (Eager / Immutable Reduction / Folding).
 *    - Complejidad: O(N).
 *    - Parámetros:
 *      * identity (0): Valor inicial e identidad algebraica para la suma (x + 0 = x).
 *      * accumulator (Integer::sum): `BinaryOperator<Integer>` asociativo que toma
 *        el acumulado parcial y el siguiente elemento para producir el nuevo acumulado.
 * 
 * 2. Alternativa Primitiva de Alto Rendimiento:
 *    - En entornos de alta concurrencia o listas masivas, se recomienda usar:
 *      `numeros.stream().mapToInt(Integer::intValue).sum()`
 *      para evitar el costo de empaquetado/desempaquetado (Boxing/Unboxing).
 */
public class reto4 {

    public static void main(String[] args) {
        List<Integer> numeros = List.of(12, 8, 5, 10, 15);

        // Reducción funcional asociativa: ((0 + 12) + 8) + 5 + 10 + 15 = 50
        int resultado = numeros.stream()
                .reduce(0, Integer::sum);

        // Salida esperada: suma = 50
        System.out.println("suma = " + resultado);
    }
}
