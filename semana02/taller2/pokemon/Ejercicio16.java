package taller2.pokemon;

import taller2.model.Entrenador;
import java.util.List;

/**
 * Reto #16: Entrenadores Experimentados
 * Operación Stream: filter()
 * 
 * Enunciado: Mostrar únicamente los entrenadores que posean más de 5 medallas.
 */
public class Ejercicio16 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
            new Entrenador("Ash", 8),
            new Entrenador("Misty", 5),
            new Entrenador("Brock", 6),
            new Entrenador("Gary", 10),
            new Entrenador("May", 3),
            new Entrenador("Dawn", 7)
        );

        List<String> experimentados = entrenadores.stream()
                .filter(e -> e.getMedallas() > 5)
                .map(e -> e.getNombre() + "(" + e.getMedallas() + ")")
                .toList();

        System.out.println("Entrenadores con > 5 medallas:");
        System.out.println(experimentados);
    }
}
