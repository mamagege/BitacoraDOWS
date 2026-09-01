package taller2.pokemon;

import java.util.List;

/**
 * Reto #03: Poder Total del Equipo
 * Operación Stream: reduce() con Method Reference (Integer::sum)
 * 
 * Enunciado: Dada una lista de niveles de Pokémon, calcular la suma total de niveles del equipo.
 */
public class Ejercicio03 {
    public static void main(String[] args) {
        List<Integer> niveles = List.of(45, 62, 38, 71, 55, 29);

        int sumaTotal = niveles.stream()
                .reduce(0, Integer::sum);

        System.out.println("=== Reto #03: Poder Total del Equipo ===");
        System.out.println("Suma total de niveles: " + sumaTotal);
    }
}
