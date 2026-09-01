package edu.dosw.lab.comportamiento.reto7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Invoker del patrón Command.
 * Almacena el historial de comandos, coordina su ejecución y permite operaciones de reversión (undo).
 */
public class ControladorMision {
    private final List<ComandoRover> historial = new ArrayList<>();

    public void enviarComando(ComandoRover comando) {
        historial.add(comando);
        comando.ejecutar();
    }

    public void deshacerComando(int indice1Based) {
        int indice = indice1Based - 1;
        if (indice >= 0 && indice < historial.size()) {
            ComandoRover comando = historial.get(indice);
            comando.deshacer();
        } else {
            System.err.println("Índice de comando inválido: " + indice1Based);
        }
    }

    public List<ComandoRover> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public void mostrarHistorial() {
        System.out.println("--- Historial ---");
        // Uso de Streams para indexar y dar formato a la lista de historial
        IntStream.range(0, historial.size())
                .mapToObj(i -> {
                    ComandoRover cmd = historial.get(i);
                    String desc;
                    if (cmd.isDeshecho()) {
                        String modulo = cmd.getDescripcion().split(" ")[0];
                        desc = "[DESHECHO] " + modulo;
                    } else {
                        desc = cmd.getDescripcion();
                    }
                    return String.format("#%d %s - %s", (i + 1), desc, cmd.getOperador());
                })
                .forEach(System.out::println);
    }
}
