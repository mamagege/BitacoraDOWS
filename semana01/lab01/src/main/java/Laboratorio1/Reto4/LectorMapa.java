package Reto4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 4: Lector de Mapas desde Consola (funcional)
 * ============================================================================
 *
 * OBJETIVO:
 * Leer pares clave-valor desde consola en formato "nombre valor, nombre valor, ..."
 * y construir un `HashMap` y un `Hashtable` usando la Stream API.
 *
 * CONCEPTOS CLAVE:
 *
 * 1. `String.split(",")` → `List.of(...)`:
 *    - Divide la línea de entrada por comas, obteniendo los pares "clave valor".
 *
 * 2. `.map(String::strip)`:
 *    - Elimina espacios en blanco al inicio y al final de cada par.
 *    - Equivalente a `.trim()` pero también elimina espacios unicode.
 *
 * 3. `.map(e -> e.split(" "))`:
 *    - Divide cada par por el espacio interno para separar la clave del valor.
 *    - Resultado: `Stream<String[]>` donde cada array es `[clave, valor]`.
 *
 * 4. `.collect(Collectors.toMap(..., ..., ..., HashMap::new))`:
 *    - Recolecta el stream de arrays en un mapa clave→valor.
 *    - `partes[0]` → clave (String).
 *    - `Integer.parseInt(partes[1])` → valor (int parseado desde String).
 *    - `(v1, v2) -> v1` → merge function para mantener el primer valor
 *      si hubiera claves duplicadas en la entrada del usuario.
 *    - `HashMap::new` / `Hashtable::new` → fábrica del tipo de mapa deseado.
 */
public class LectorMapa {

    /**
     * Lee un HashMap desde consola.
     * Formato esperado: "oro 10, metal 20, madera 1"
     */
    public static HashMap<String, Integer> leerHashMap() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elementos del HashMap " +
                "(Separado por espacios y coma. Ej: oro 10, metal 20, madera 1): ");
        String linea = scanner.nextLine();

        List<String> elementos = List.of(linea.split(","));

        return elementos.stream()
                .map(String::strip)                  // Quita espacios redundantes
                .map(e -> e.split(" "))              // Separa "clave valor" → ["clave", "valor"]
                .collect(Collectors.toMap(
                        partes -> partes[0],                        // Extrae la clave
                        partes -> Integer.parseInt(partes[1]),      // Parsea el valor a int
                        (v1, v2) -> v1,                            // En duplicados, mantiene el primero
                        HashMap::new                               // Crea un HashMap concreto
                ));
    }

    /**
     * Lee un Hashtable desde consola.
     * Formato esperado: "oro 10, metal 20, madera 1"
     * Nota: Hashtable no admite claves ni valores null.
     */
    public static Hashtable<String, Integer> leerHashTable() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elementos del HashTable " +
                "(Separado por espacios y coma. Ej: oro 10, metal 20, madera 1): ");
        String linea = scanner.nextLine();

        List<String> elementos = List.of(linea.split(","));

        return elementos.stream()
                .map(String::strip)
                .map(e -> e.split(" "))
                .collect(Collectors.toMap(
                        partes -> partes[0],
                        partes -> Integer.parseInt(partes[1]),
                        (v1, v2) -> v1,
                        Hashtable::new  // Crea un Hashtable (legacy, sincronizado)
                ));
    }
}
