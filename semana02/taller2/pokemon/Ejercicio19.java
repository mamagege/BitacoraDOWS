package taller2.pokemon;

import taller2.model.Entrenador;
import taller2.model.Pokemon;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Reto #19: Top 3 Entrenadores
 * Operación Stream: sorted() con Comparator multi-criterio + limit(3)
 * 
 * Enunciado: Generar un ranking de los 3 mejores entrenadores considerando:
 * 1° más medallas (descendente)
 * 2° mayor poder acumulado (descendente)
 * 3° orden alfabético (ascendente) como criterio de desempate.
 */
public class Ejercicio19 {
    public static void main(String[] args) {
        Entrenador gary = new Entrenador("Gary", 10, List.of(
            new Pokemon("Blastoise", 800.0),
            new Pokemon("Arcanine", 780.0),
            new Pokemon("Electivire", 760.0)
        ));

        Entrenador ash = new Entrenador("Ash", 8, List.of(
            new Pokemon("Charizard", 650.0),
            new Pokemon("Pikachu", 600.0),
            new Pokemon("Snorlax", 600.0)
        ));

        Entrenador dawn = new Entrenador("Dawn", 7, List.of(
            new Pokemon("Empoleon", 750.0),
            new Pokemon("Togekiss", 700.0),
            new Pokemon("Mamoswine", 650.0)
        ));

        Entrenador brock = new Entrenador("Brock", 6, List.of(
            new Pokemon("Onix", 570.0),
            new Pokemon("Geodude", 550.0),
            new Pokemon("Steelix", 550.0)
        ));

        List<Entrenador> entrenadores = List.of(gary, ash, dawn, brock);

        Comparator<Entrenador> rankingComparator = Comparator
                .comparingInt(Entrenador::getMedallas).reversed()
                .thenComparing(Comparator.comparingDouble(Entrenador::calcularPoderTotal).reversed())
                .thenComparing(Entrenador::getNombre);

        System.out.println("=== Reto #19: Top 3 Entrenadores ===");
        AtomicInteger rank = new AtomicInteger(1);
        entrenadores.stream()
                .sorted(rankingComparator)
                .limit(3)
                .forEach(e -> System.out.println("#" + rank.getAndIncrement() + " " + e.getNombre() + " – " 
                        + e.getMedallas() + " medallas, PC: " + (int) e.calcularPoderTotal()));
    }
}
