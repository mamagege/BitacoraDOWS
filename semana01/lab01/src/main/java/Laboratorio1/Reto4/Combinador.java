package Reto4;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 4: Combinador de Mapas Heterogéneos (HashMap + HashTable)
 * ============================================================================
 *
 * OBJETIVO:
 * Fusionar un `HashMap` y un `Hashtable` en un único `LinkedHashMap` ordenado
 * alfabéticamente, con las claves en MAYÚSCULAS y priorizando los valores
 * del `Hashtable` en caso de clave duplicada.
 *
 * CONCEPTOS CLAVE:
 *
 * 1. `Stream.concat(s1, s2)`:
 *    - Crea un único `Stream` que primero emite todos los elementos de `s1`
 *      y luego todos los de `s2`, sin modificar ninguna colección original.
 *    - Es la forma idiomática de "unir" dos streams en uno solo (lazy).
 *    - Aquí une: `mapaHash.entrySet().stream()` + `mapaTable.entrySet().stream()`.
 *
 * 2. `.map(entry -> Map.entry(entry.getKey().toUpperCase(), entry.getValue()))`:
 *    - Transformación 1:1 de cada entrada del mapa.
 *    - Convierte la CLAVE a mayúsculas, manteniendo el valor intacto.
 *    - `Map.entry(k, v)` (Java 9+) crea una entrada inmutable de un mapa.
 *
 * 3. `.sorted(Map.Entry.comparingByKey())`:
 *    - Ordena las entradas alfabéticamente (A → Z) por su clave String.
 *    - Operación intermedia `Stateful`: requiere acumular todo el stream antes
 *      de emitir el primer resultado ordenado.
 *
 * 4. `.collect(Collectors.toMap(k, v, mergeFunction, mapFactory))`:
 *    - Versión completa de `toMap` con 4 argumentos:
 *      * `Map.Entry::getKey`   → extractor de clave.
 *      * `Map.Entry::getValue` → extractor de valor.
 *      * `(v1, v2) -> v2`     → MERGE FUNCTION: cuando hay clave duplicada,
 *                               se queda con `v2` (el valor del Hashtable,
 *                               que viene segundo en el stream).
 *      * `LinkedHashMap::new` → preserva el orden de inserción (que ya es
 *                               el orden alfabético dado por `sorted()`).
 */
public class Combinador {

    /**
     * Combina un HashMap y un Hashtable en un LinkedHashMap unificado.
     *
     * @param mapaHash   Entradas del HashMap (pueden tener claves en minúsculas).
     * @param mapaTable  Entradas del Hashtable (tienen prioridad en duplicados).
     * @return           LinkedHashMap ordenado alfabéticamente por clave en MAYÚSCULAS.
     */
    public static Map<String, Integer> combinarMapas(Map<String, Integer> mapaHash,
                                                     Hashtable<String, Integer> mapaTable) {
        return Stream.concat(mapaHash.entrySet().stream(), mapaTable.entrySet().stream())
                // Paso 1: Fusiona ambas colecciones en un único flujo
                .map(entry -> Map.entry(entry.getKey().toUpperCase(), entry.getValue()))
                // Paso 2: Normaliza todas las claves a MAYÚSCULAS
                .sorted(Map.Entry.comparingByKey())
                // Paso 3: Ordena alfabéticamente (A → Z)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v2,   // Prioriza el valor del Hashtable (v2) ante duplicados
                        LinkedHashMap::new // Usa LinkedHashMap para conservar el orden de sorted()
                ));
    }
}
