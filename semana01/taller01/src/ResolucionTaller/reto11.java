//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.Comparator;
import java.util.List;

/**
 * ============================================================================
 * RETO 11: Encontrar el valor mínimo con min(Comparator)
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de precios, determinar cuál es el precio más bajo utilizando Streams.
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.min(Comparator<T>):
 *    - Tipo: Operación Terminal (Eager / Reducción).
 *    - Complejidad: O(N).
 *    - Retorna un `Optional<T>` para manejar con seguridad el caso en que la
 *      colección de entrada esté vacía (evitando `NullPointerException`).
 * 
 * 2. Optional.orElseThrow():
 *    - Desempaqueta el valor si existe, o lanza una `NoSuchElementException`
 *      si el stream estaba vacío (comportamiento estricto e idiomático en Java 10+).
 */
public class reto11 {

    public static void main(String[] args) {
        List<Integer> precios = List.of(12000, 5000, 18000, 7500, 3000);

        // Se busca el elemento mínimo según el orden natural numérico
        Integer precioMinimo = precios.stream()
                .min(Comparator.naturalOrder())
                .orElseThrow();

        // Salida esperada: Precio minimo: 3000
        System.out.println("Precio minimo: " + precioMinimo);
    }
}
