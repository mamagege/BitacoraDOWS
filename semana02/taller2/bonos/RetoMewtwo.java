package taller2.bonos;

import taller2.model.Pokemon;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 🧬 RETO ESPECIAL MEWTWO (+1.0 punto)
 * 
 * Enunciado Propuesto: "Evaluación Táctica de Élite Regional"
 * La Comisión de DOSW requiere procesar una lista global de Pokémon de alto rendimiento para:
 * 1. filter(): Filtrar únicamente Pokémon competitivos (nivel >= 50).
 * 2. map(): Transformar y enriquecer los datos a un perfil táctico (calculando el Coeficiente de Combate Efectivo: PC + Nivel * 2.0).
 * 3. sorted(): Ordenar de forma descendente por el coeficiente de combate calculado.
 * 4. groupingBy(): Agrupar los perfiles tácticos por su región de origen.
 * 5. reduce(): Aplicar una reducción funcional (BinaryOperator) dentro de cada región para consolidar el puntaje acumulado de poder de élite regional.
 */
public class RetoMewtwo {

    // Registro inmutable para modelar la proyección táctica
    public record PerfilTactico(String nombre, String region, String tipo, double coeficienteEfectivo) {}

    public static void main(String[] args) {
        List<Pokemon> pokedexGlobal = List.of(
            new Pokemon(1L, "Mewtwo", "Psíquico", 75, 680.0, "Kanto", true),
            new Pokemon(2L, "Charizard", "Fuego", 60, 610.0, "Kanto", false),
            new Pokemon(3L, "Pikachu", "Eléctrico", 35, 320.0, "Kanto", false), // Filtrado (nivel < 50)
            new Pokemon(4L, "Lugia", "Psíquico", 70, 670.0, "Johto", true),
            new Pokemon(5L, "Typhlosion", "Fuego", 55, 590.0, "Johto", false),
            new Pokemon(6L, "Totodile", "Agua", 20, 180.0, "Johto", false),    // Filtrado (nivel < 50)
            new Pokemon(7L, "Rayquaza", "Dragón", 72, 675.0, "Hoenn", true),
            new Pokemon(8L, "Blaziken", "Fuego", 58, 600.0, "Hoenn", false),
            new Pokemon(9L, "Garchomp", "Dragón", 65, 640.0, "Sinnoh", false),
            new Pokemon(10L, "Lucario", "Lucha", 54, 550.0, "Sinnoh", false)
        );

        System.out.println("=================================================================");
        System.out.println("  🧬 RETO MEWTWO: EVALUACIÓN TÁCTICA Y PODER REGIONAL ACUMULADO  ");
        System.out.println("=================================================================");

        // Pipeline unificado que integra: filter() -> map() -> sorted() -> groupingBy() -> reduce()
        Map<String, Double> poderTacticoRegional = pokedexGlobal.stream()
                // 1. filter(): Selección de Pokémon de élite (nivel >= 50)
                .filter(p -> p.getNivel() >= 50)
                // 2. map(): Transformación a PerfilTactico calculando Coeficiente Efectivo
                .map(p -> new PerfilTactico(
                        p.getNombre(),
                        p.getRegion(),
                        p.getTipo(),
                        p.getPoderCombate() + (p.getNivel() * 2.0)
                ))
                // 3. sorted(): Orden descendente por Coeficiente Efectivo
                .sorted(Comparator.comparingDouble(PerfilTactico::coeficienteEfectivo).reversed())
                // 4. groupingBy(): Agrupación por Región
                // 5. reduce() mediante Collectors.reducing(): Sumatoria/Plegado funcional de coeficientes
                .collect(Collectors.groupingBy(
                        PerfilTactico::region,
                        Collectors.reducing(
                                0.0,
                                PerfilTactico::coeficienteEfectivo,
                                Double::sum // BinaryOperator para reducción funcional
                        )
                ));

        // Presentación de resultados consolidados
        System.out.println("\n📊 Resumen de Poder Táctico por Región (Élite Nivel >= 50):");
        poderTacticoRegional.forEach((region, totalPoder) -> 
            System.out.printf(" - Región %-8s : %,.1f pts de poder efectivo acumulado%n", region, totalPoder)
        );

        // Determinación del Campeón Supremo Absoluto con un segundo pipeline funcional directo usando reduce()
        pokedexGlobal.stream()
                .filter(p -> p.getNivel() >= 50)
                .map(p -> new PerfilTactico(p.getNombre(), p.getRegion(), p.getTipo(), p.getPoderCombate() + (p.getNivel() * 2.0)))
                .reduce((p1, p2) -> p1.coeficienteEfectivo() >= p2.coeficienteEfectivo() ? p1 : p2)
                .ifPresent(campeon -> {
                    System.out.println("\n👑 Campeón Supremo Absoluto (vía reduce directo):");
                    System.out.printf("   %s (%s) - Coeficiente: %.1f%n", campeon.nombre(), campeon.region(), campeon.coeficienteEfectivo());
                });
    }
}
