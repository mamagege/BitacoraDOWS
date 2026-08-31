package Reto7;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class JuegoDelCalamar {
    private int premioTotal = 150000;
    private List<Jugador> jugadoresActivos;

    // Constructor: Inyección de dependencias
    public JuegoDelCalamar(List<Jugador> jugadores) {
        this.jugadoresActivos = new ArrayList<>(jugadores);
    }


    public void jugarRonda(String nombreRonda, Predicate<Jugador> condicionVictoria) {
        System.out.println("\n--- INICIANDO: " + nombreRonda + " ---");

        List<Jugador> ganadores = new ArrayList<>();
        List<Jugador> eliminados = new ArrayList<>();

        // Separamos ganadores y eliminados usando la condición
        for (Jugador j : jugadoresActivos) {
            if (condicionVictoria.test(j)) {
                ganadores.add(j);
            } else {
                j.setEstado("ELIMINADO");
                eliminados.add(j);
            }
        }

        // Actualizamos estado interno
        this.jugadoresActivos = ganadores;
        int dineroSumado = eliminados.size() * 50;
        this.premioTotal += dineroSumado;

        // Mostramos resultados
        System.out.println("PASAN: " + ganadores);
        System.out.println("ELIMINADOS: " + eliminados.size() + " jugadores.");
        System.out.println("Se han agregado al bote: " + dineroSumado + "M");
        System.out.println("Premio Actual: " + premioTotal + "M");
    }

    public List<Jugador> getJugadoresActivos() { return jugadoresActivos; }
    public int getPremioTotal() { return premioTotal; }
}