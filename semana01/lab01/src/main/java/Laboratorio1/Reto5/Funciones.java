package Reto5;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 5: Funciones de Filtrado sobre Sets
 * ============================================================================
 *
 * OBJETIVO:
 * Proveer una función pura para filtrar (eliminar) de un `Set<Integer>` todos
 * los elementos que sean múltiplos de un número dado.
 *
 * CONCEPTOS FUNCIONALES CLAVE:
 *
 * 1. `set.stream().filter(e -> e % multiplo != 0)`:
 *    - `filter()` es una operación intermedia (Lazy / Stateless).
 *    - Conserva solo los elementos para los cuales la condición devuelve `true`.
 *    - Aquí: conserva solo los que NO son múltiplos de `multiplo`.
 *    - El `Predicate<Integer>` es `e -> e % multiplo != 0`.
 *
 * 2. `.collect(Collectors.toSet())`:
 *    - Materializa el stream filtrado en un nuevo `HashSet` (sin orden garantizado).
 *    - El `Set` devuelto es INDEPENDIENTE del original: la función es pura y
 *      no modifica la colección de entrada.
 *
 * DECISIÓN DE DISEÑO:
 *    - Recibir un `Set<Integer>` genérico (no `HashSet` ni `TreeSet`) aplica
 *      el Principio de Inversión de Dependencias (DIP): dependemos de la
 *      abstracción, no de la implementación concreta.
 *    - El llamador decide si pasa un `HashSet` o un `TreeSet`.
 */
public class Funciones {

    /**
     * Retorna un nuevo Set que contiene solo los elementos de `set` que
     * NO son múltiplos de `multiplo`.
     *
     * @param set       El conjunto de enteros a filtrar.
     * @param multiplo  El divisor: se eliminan los que sean múltiplos de este valor.
     * @return          Nuevo Set sin los múltiplos del número indicado.
     */
    public static Set<Integer> eliminarMultiplos(Set<Integer> set, int multiplo) {
        return set.stream()
                .filter(e -> e % multiplo != 0)  // Conserva solo los NO múltiplos
                .collect(Collectors.toSet());     // Materializa en un nuevo HashSet
    }
}
