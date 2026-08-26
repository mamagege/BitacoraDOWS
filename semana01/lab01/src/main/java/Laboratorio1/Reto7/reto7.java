package Reto7;

import java.util.ArrayList;
import java.util.List;

public class reto7 {
    public static void main(String[] args) {
        System.out.println("¡Bienvenidos al Juego del Calamar!");


        List<Jugador> participantes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            participantes.add(new Jugador(i));
        }


        JuegoDelCalamar juego = new JuegoDelCalamar(participantes);


        juego.jugarRonda("Luz Roja, Luz Verde", j -> j.getDeuda() < 170);

        juego.jugarRonda("Los Dados", j -> j.getNumero() % 2 == 0);

        juego.jugarRonda("Puente de Cristal", j -> j.getNombre().length() > 5);


        System.out.println("\n========= RESULTADO FINAL =========");
        List<Jugador> ganadores = juego.getJugadoresActivos();

        if (ganadores.isEmpty()) {
            System.out.println("No hubo sobrevivientes. El premio se donará.");
        } else {
            System.out.println("GANADORES: " + ganadores);
            System.out.println("Premio Total: " + juego.getPremioTotal() + "M");
            System.out.println("Dinero por ganador: " + (juego.getPremioTotal() / ganadores.size()) + "M");
        }
    }
}