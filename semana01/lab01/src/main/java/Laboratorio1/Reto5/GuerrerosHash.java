package Reto5;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 5: Lector de Conjuntos (HashSet y TreeSet) desde Consola
 * ============================================================================
 *
 * OBJETIVO:
 * Leer una secuencia de enteros desde consola y poblar un `HashSet` o un
 * `TreeSet` según el contexto del reto ("guerreros hash" vs "guerreros tree").
 *
 * CONCEPTOS CLAVE:
 *
 * 1. Separación de Responsabilidades (SRP):
 *    - `leerHashSet()` y `leerTreeSet()` son métodos de fachada (fábrica):
 *      crean el tipo concreto de Set y delegan el llenado a `llenarConjunto()`.
 *    - `llenarConjunto()` es `private`: es un detalle de implementación que
 *      el resto del sistema no necesita conocer.
 *
 * 2. Polimorfismo mediante `Set<Integer>`:
 *    - El método `llenarConjunto` recibe `Set<Integer>` (interfaz),
 *      no `HashSet` o `TreeSet` (clases concretas).
 *    - Esto permite que el mismo método funcione con cualquier implementación
 *      de Set sin modificar su código (Abierto/Cerrado - OCP).
 *
 * 3. `try-with-resources` para el `Scanner`:
 *    - `try (Scanner scannerLinea = new Scanner(linea))` garantiza que el
 *      scanner interno se cierra automáticamente al salir del bloque `try`,
 *      evitando fugas de recursos.
 *
 * 4. `Scanner.hasNextInt()` / `nextInt()`:
 *    - Lee enteros uno a uno mientras existan en la línea de entrada.
 *    - Permite manejar secuencias de longitud variable sin fijar el tamaño.
 *
 * DIFERENCIA HashSet vs TreeSet (relevante para el Reto):
 * - `HashSet`: sin orden garantizado, O(1) para inserción y búsqueda.
 * - `TreeSet`: ordenado automáticamente (menor a mayor), O(log N) para operaciones.
 */
public class GuerrerosHash {

    /** Lee una secuencia de enteros en un HashSet (orden aleatorio). */
    public static HashSet<Integer> leerHashSet() {
        HashSet<Integer> hashSet = new HashSet<>();
        llenarConjunto(hashSet, "Escribe los guerreros hash (Separados por espacio. Ej: 4 6 8): ");
        return hashSet;
    }

    /** Lee una secuencia de enteros en un TreeSet (orden ascendente automático). */
    public static TreeSet<Integer> leerTreeSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        llenarConjunto(treeSet, "Escribe los guerreros tree (Separados por espacio. Ej: 1 2 3): ");
        return treeSet;
    }

    /**
     * Detalle privado de implementación: lee enteros separados por espacio
     * desde consola y los agrega al conjunto provisto.
     * Usa try-with-resources para garantizar el cierre del Scanner interno.
     */
    private static void llenarConjunto(Set<Integer> conjunto, String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(mensaje);
        String linea = scanner.nextLine();

        // try-with-resources: el Scanner interno se cierra automáticamente
        try (Scanner scannerLinea = new Scanner(linea)) {
            while (scannerLinea.hasNextInt()) {
                conjunto.add(scannerLinea.nextInt());
            }
        }
    }
}
