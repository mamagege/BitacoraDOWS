package Reto2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 2: Funciones de Análisis Estadístico sobre Listas
 * ============================================================================
 *
 * OBJETIVO:
 * Proveer funciones puras y estáticas (estilo programación funcional) para
 * calcular el mínimo, el máximo, el tamaño y verificar paridad sobre listas
 * de enteros.
 *
 * CONCEPTOS FUNCIONALES APLICADOS:
 *
 * 1. `stream().min(Comparator.naturalOrder())`:
 *    - Operación Terminal de reducción que recorre el Stream y retorna el
 *      elemento más pequeño según el orden natural.
 *    - Devuelve `Optional<Integer>`, y `.orElseThrow()` lanza excepción si la
 *      lista estuviera vacía (defensa robusta contra NPE).
 *
 * 2. `stream().max(Comparator.naturalOrder())`:
 *    - Idéntico a `min()` pero retorna el elemento de mayor valor.
 *
 * 3. `lista.size()`:
 *    - Complejidad O(1): ArrayList mantiene el conteo actualizado en memoria.
 *    - Alternativa funcional equivalente: `.stream().count()`, O(N).
 *      Se prefiere `.size()` aquí por eficiencia al no requerir iterar el stream.
 *
 * 4. Composición de Funciones (isMaxMultiple2, minMaxCantidad, allFunctionsMixed):
 *    - Funciones que llaman a otras funciones: la base de la composición
 *      funcional. Cada función hace UNA sola cosa (SRP) y se combinan para
 *      construir comportamientos más complejos.
 */
public class Funciones {

    /** Retorna el número más pequeño de la lista. */
    public static int numeroMasPequeno(List<Integer> lista) {
        return lista.stream().min(Comparator.naturalOrder()).orElseThrow();
    }

    /** Retorna la cantidad de elementos de la lista (O(1) usando `.size()`). */
    public static long cantidadNumeros(List<Integer> lista) {
        return lista.size();
    }

    /** Retorna el número más grande de la lista. */
    public static int numeroMasGrande(List<Integer> lista) {
        return lista.stream().max(Comparator.naturalOrder()).orElseThrow();
    }

    /** Retorna una lista inmutable con [max, min, cantidad]. */
    public static List<Number> minMaxCantidad(List<Integer> lista) {
        return List.of(numeroMasGrande(lista), numeroMasPequeno(lista), cantidadNumeros(lista));
    }

    /** Función pura: retorna `true` si el número es múltiplo de 2 (es par). */
    public static boolean isNumberMultiple2(int numero) {
        return (numero % 2 == 0);
    }

    /** Retorna `true` si el elemento máximo de la lista es múltiplo de 2. */
    public static boolean isMaxMultiple2(List<Integer> lista) {
        return (isNumberMultiple2(numeroMasGrande(lista)));
    }

    /**
     * Combina en una sola lista los resultados de `minMaxCantidad` y `isMaxMultiple2`.
     * Demuestra composición funcional: [max, min, cantidad, esMaxPar].
     */
    public static List<Object> allFunctionsMixed(List<Integer> lista) {
        List<Object> resultado = new ArrayList<>(minMaxCantidad(lista));
        resultado.add(isMaxMultiple2(lista));
        return resultado;
    }
}
