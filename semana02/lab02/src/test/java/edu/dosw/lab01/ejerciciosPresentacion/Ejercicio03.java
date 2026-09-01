package edu.dosw.lab01.ejerciciosPresentacion;

import java.util.List;

/**
 * EJERCICIO #3:
 * Dada una lista de usuarios con los atributos: id, name, age, active.
 * 
 * Requerimientos:
 * - Filtra únicamente los usuarios activos.
 * - Obtén una lista con los nombres en mayúscula.
 * - Ordena alfabéticamente el resultado.
 * 
 * Datos de Entrada:
 * List<Usuario> users
 * 
 * Resultado: List<String>
 */
public class Ejercicio03 {

        // Se define el modelo Usuario como un Java Record (disponible en Java 14+ /
        // Java 16 SE)
        // Permite una definición inmutable, concisa y genera automáticamente getters,
        // equals, hashCode y toString.
        public record Usuario(int id, String name, int age, boolean active) {
        }

        public static void main(String[] args) {
                List<Usuario> users = List.of(
                                new Usuario(1, "carlos", 17, true),
                                new Usuario(2, "ana", 30, false),
                                new Usuario(3, "miguel", 15, true),
                                new Usuario(4, "beatriz", 28, false),
                                new Usuario(5, "juan", 35, true));

                // Opción A: Usando Referencias a Métodos (Method References)
                // Explicación:
                // 1. .filter(Usuario::active): Filtra mediante el getter del record/clase para
                // validar si está activo.
                // 2. .map(Usuario::name): Extrae el atributo 'name' de cada objeto Usuario.
                // 3. .map(String::toUpperCase): Transforma la cadena obtenida a mayúsculas.
                // 4. .sorted(): Ordena los nombres alfabéticamente.
                // 5. .toList(): Devuelve la lista de Strings resultante.
                List<String> resultA = users.stream()
                                .filter(Usuario::active)
                                .map(Usuario::name)
                                .map(String::toUpperCase)
                                .sorted()
                                .toList();

                // Opción B: Usando Expresión Lambda Directa
                // Explicación:
                // 1. .filter(Usuario::active): Filtra los usuarios donde active es true.
                // 2. .map(u -> u.name().toUpperCase()): Combina la extracción del nombre y la
                // conversión a mayúsculas
                // en un solo paso usando una función lambda explícita.
                // 3. .sorted(): Ordena los nombres alfabéticamente.
                List<String> resultB = users.stream()
                                .filter(Usuario::active)
                                .map(u -> u.name().toUpperCase())
                                .sorted()
                                .toList();

                System.out.println("--- Ejercicio 03 ---");
                System.out.println("Usuarios de entrada: " + users);
                System.out.println("Nombres de usuarios activos ordenados (Opción A): " + resultA);
                System.out.println("Nombres de usuarios activos ordenados (Opción B): " + resultB);
        }
}
