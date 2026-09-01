package ejercicios;

import java.util.List;

/**
 * EJERCICIO #1:
 * Dada una lista de números enteros, necesitamos obtener una nueva lista solo con los números pares mayores a diez.
 * 
 * Datos de Entrada:
 * List<Integer> numbers = List.of(3, 8, 10, 12, 15, 18, 20);
 * 
 * Resultado Esperado:
 * [12, 18, 20]
 */
public class Ejercicio01 {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 8, 10, 12, 15, 18, 20);

        // Opción A: Encadenando dos filtros independientes
        // Explicación: 
        // 1. .stream(): Convierte la lista en un flujo de datos (Stream).
        // 2. .filter(n -> n % 2 == 0): Evalúa la condición de paridad de forma aislada.
        // 3. .filter(n -> n > 10): Evalúa que sea estrictamente mayor a 10 de forma aislada.
        // 4. .toList(): Colecciona el resultado en una nueva lista inmutable (Java 16+).
        List<Integer> resultA = numbers.stream()
                .filter(n -> n % 2 == 0)
                .filter(n -> n > 10)
                .toList();

        // Opción B: Combinando condiciones dentro de un único filtro
        // Explicación: 
        // 1. .filter(n -> n % 2 == 0 && n > 10): Utiliza el operador lógico AND (&&) para evaluar
        //    ambas condiciones en un solo paso lambda, reduciendo la cantidad de etapas en el pipeline.
        List<Integer> resultB = numbers.stream()
                .filter(n -> n % 2 == 0 && n > 10)
                .toList();

        // Salida por consola
        System.out.println("--- Ejercicio 01 ---");
        System.out.println("Entrada: " + numbers);
        System.out.println("Resultado (Opción A): " + resultA);
        System.out.println("Resultado (Opción B): " + resultB);
    }
}
