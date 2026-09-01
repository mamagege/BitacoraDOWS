package ejercicios;

import java.util.List;

/**
 * EJERCICIO #2:
 * Dada una lista de palabras, se requiere:
 * - Filtrar las palabras que tengan más de 4 caracteres.
 * - Convertirlas a Mayúsculas.
 * - Ordenarlas alfabéticamente.
 * - Obtener la cantidad total de palabras resultantes.
 * 
 * Datos de Entrada:
 * List<String> words = List.of("java", "stream", "api", "functional", "code", "git");
 */
public class Ejercicio02 {

    public static void main(String[] args) {
        List<String> words = List.of("java", "stream", "api", "functional", "code", "git");

        // Enfoque 1: Procesar en lista intermedia y luego contar elementos
        // Explicación:
        // 1. .filter(w -> w.length() > 4): Retiene solo palabras con longitud > 4 ("stream", "functional").
        // 2. .map(String::toUpperCase): Transforma cada cadena a mayúsculas mediante una referencia a método.
        // 3. .sorted(): Ordena los elementos según su orden natural (alfabético).
        // 4. .toList(): Acumula las palabras procesadas en una lista inmutable.
        List<String> processed = words.stream()
                .filter(w -> w.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .toList();

        long countProcessed = processed.stream().count();

        // Enfoque 2: Procesamiento directo en un solo Stream con `peek` e inspección
        // Explicación:
        // 1. .filter y .map realizan la filtración y transformación.
        // 2. .sorted() realiza el ordenamiento alfabético en el stream.
        // 3. .peek(System.out::println): Operación intermedia que permite inspeccionar/imprimir los elementos
        //    que pasan por esa etapa sin alterar el flujo (ideal para depuración/debugging).
        // 4. .count(): Operación terminal que cuenta la cantidad final de elementos en el flujo.
        System.out.println("--- Ejercicio 02 ---");
        System.out.println("Entrada: " + words);
        System.out.println("Elementos filtrados y procesados (Enfoque 2 con peek):");
        
        long cantidad = words.stream()
                .filter(w -> w.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .peek(System.out::println)
                .count();

        System.out.println("Resultado de la lista procesada (Enfoque 1): " + processed);
        System.out.println("Cantidad total resultante: " + cantidad);
    }
}
