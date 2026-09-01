package edu.dosw.lab01.ejerciciosPresentacion;

import java.util.List;

/**
 * EJERCICIO #4:
 * Dado un listado de Usuarios y utilizando los mismos atributos anteriores (id,
 * name, age, active),
 * filtrar las personas mayores de edad (edad >= 18) y obtener sus nombres.
 * 
 * Datos de Entrada:
 * List<Usuario> users = List.of(
 * new Usuario(1, "carlos", 17, true),
 * new Usuario(2, "ana", 30, false),
 * new Usuario(3, "miguel", 15, true),
 * new Usuario(4, "beatriz", 28, false),
 * new Usuario(5, "juan", 35, true)
 * );
 */
public class Ejercicio04 {

    // Definición de modelo Usuario como Record inmutable
    public record Usuario(int id, String name, int age, boolean active) {
    }

    public static void main(String[] args) {
        List<Usuario> users = List.of(
                new Usuario(1, "carlos", 17, true),
                new Usuario(2, "ana", 30, false),
                new Usuario(3, "miguel", 15, true),
                new Usuario(4, "beatriz", 28, false),
                new Usuario(5, "juan", 35, true));

        // Explicación de la solución:
        // 1. .stream(): Inicia el flujo de procesamiento sobre la lista de objetos
        // Usuario.
        // 2. .filter(u -> u.age() >= 18): Filtra las personas evaluando que su edad sea
        // mayor o igual a 18 años.
        // 3. .map(Usuario::name): Proyecta/extrae únicamente el atributo de nombre de
        // los objetos filtrados.
        // 4. .toList(): Colecta los nombres resultantes en una colección inmutable de
        // tipo List<String>.
        List<String> resultado = users.stream()
                .filter(u -> u.age() >= 18)
                .map(Usuario::name)
                .toList();

        System.out.println("--- Ejercicio 04 ---");
        System.out.println("Lista total de usuarios:");
        users.forEach(u -> System.out.println("  - " + u));

        System.out.println("\nNombres de personas mayores de edad (edad >= 18):");
        System.out.println(resultado);
    }
}
