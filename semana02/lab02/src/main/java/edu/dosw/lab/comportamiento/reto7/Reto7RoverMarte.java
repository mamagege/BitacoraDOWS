package edu.dosw.lab.comportamiento.reto7;

import java.util.List;
import java.util.Scanner;

/**
 * Reto 7: El Rover Explorador de Marte
 * Patrón: Command
 */
public class Reto7RoverMarte {

    public static void ejecutar() {
        ejecutarConScanner(new Scanner(System.in));
    }

    public static void ejecutarConScanner(Scanner scanner) {
        RoverChibchombo rover = new RoverChibchombo();
        ControladorMision controlador = new ControladorMision();

        System.out.println("Rover Chibchombo");
        System.out.print("¿Cuántas acciones desea registrar? (por defecto 5): ");
        String cantStr = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        int cantidad = 5;
        if (!cantStr.isEmpty()) {
            try {
                cantidad = Integer.parseInt(cantStr);
            } catch (NumberFormatException e) {
                cantidad = 5;
            }
        }

        // Comandos predeterminados de apoyo según la guía
        List<ComandoRover> predeterminados = List.of(
                new AvanzarMotorCommand(rover, "Camila", 12),
                new GrabarCamaraCommand(rover, "Camila", 30),
                new PerforarTaladroCommand(rover, "Camila", 15),
                new RecogerBrazoCommand(rover, "Julián"),
                new RetrocederMotorCommand(rover, "Julián", 4)
        );

        System.out.println("\n--- Registro y Ejecución de Comandos ---");
        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Acción " + i + " - Operador (Enter para predeterminado): ");
            String operador = scanner.hasNextLine() ? scanner.nextLine().trim() : "";

            if (operador.isEmpty() && i <= predeterminados.size()) {
                ComandoRover cmdPredeterminado = predeterminados.get(i - 1);
                controlador.enviarComando(cmdPredeterminado);
                continue;
            }

            if (operador.isEmpty()) operador = "Operador" + i;

            System.out.println(" Elige módulo/acción:");
            System.out.println("  1. Motor Avanzar  2. Motor Retroceder  3. Cámara Grabar  4. Taladro Perforar  5. Brazo Recoger");
            System.out.print("  Opción (1-5): ");
            String opc = scanner.hasNextLine() ? scanner.nextLine().trim() : "1";

            ComandoRover comando;
            switch (opc) {
                case "2" -> {
                    System.out.print("  Metros a retroceder: ");
                    int m = leerEntero(scanner, 4);
                    comando = new RetrocederMotorCommand(rover, operador, m);
                }
                case "3" -> {
                    System.out.print("  Segundos de grabación (0-120): ");
                    int s = leerEntero(scanner, 30);
                    comando = new GrabarCamaraCommand(rover, operador, s);
                }
                case "4" -> {
                    System.out.print("  Profundidad a perforar (cm): ");
                    int cm = leerEntero(scanner, 15);
                    comando = new PerforarTaladroCommand(rover, operador, cm);
                }
                case "5" -> comando = new RecogerBrazoCommand(rover, operador);
                default -> {
                    System.out.print("  Metros a avanzar: ");
                    int m = leerEntero(scanner, 12);
                    comando = new AvanzarMotorCommand(rover, operador, m);
                }
            }

            controlador.enviarComando(comando);
        }

        // Deshacer acción
        System.out.print("\n¿Desea deshacer alguna acción? (Ingrese número de acción o Enter para deshacer la 3): ");
        String desStr = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        int accionADeshacer = 3;
        if (!desStr.isEmpty()) {
            try {
                accionADeshacer = Integer.parseInt(desStr);
            } catch (NumberFormatException e) {
                accionADeshacer = 3;
            }
        }

        controlador.deshacerComando(accionADeshacer);

        System.out.println();
        // Mostrar historial formateado con Streams
        controlador.mostrarHistorial();
        System.out.println();
    }

    private static int leerEntero(Scanner scanner, int defecto) {
        String s = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        if (s.isEmpty()) return defecto;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defecto;
        }
    }
}
