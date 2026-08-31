//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.List;

/**
 * ============================================================================
 * RETO 2: Imprimir elementos de una colección con forEach()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de productos, recorrer la colección e imprimir cada elemento
 * en consola con el mensaje: "Producto disponible: <nombre>".
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Iterable.forEach(Consumer<T>) / Stream.forEach(Consumer<T>):
 *    - Tipo: Operación Terminal (Eager / Side Effect).
 *    - Complejidad: O(N).
 *    - Recibe un `Consumer<String>` que acepta un valor de entrada y ejecuta
 *      una acción sin retornar ningún valor (void).
 *    - Regla de Arquitectura: Los efectos secundarios (como imprimir en consola,
 *      escribir en disco o enviar mensajes por red) deben confinarse
 *      exclusivamente a las operaciones terminales (`forEach`) y NUNCA
 *      dentro de operaciones intermedias como `map()` o `filter()`.
 */
public class reto2 {

    public static void main(String[] args) {
        List<String> productos = List.of(
                "Laptop", "Mouse", "Teclado", "Monitor", "Impresora"
        );

        // Se ejecuta la acción de consumo (Consumer) para cada elemento de la colección
        productos.forEach(producto -> System.out.println("Producto Disponible: " + producto));
    }
}
