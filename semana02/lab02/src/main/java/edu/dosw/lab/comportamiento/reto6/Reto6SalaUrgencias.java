package edu.dosw.lab.comportamiento.reto6;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Reto 6: Sala de Urgencias
 * Patrón: Chain of Responsibility
 */
public class Reto6SalaUrgencias {

    public static void ejecutar() {
        ejecutarConScanner(new Scanner(System.in));
    }

    public static void ejecutarConScanner(Scanner scanner) {
        //Configurar la cadena de responsabilidad
        ManejadorAtencion cadena = new EnfermeroHandler();
        cadena.setSiguiente(new MedicoGeneralHandler())
              .setSiguiente(new EspecialistaHandler());

        List<Paciente> pacientes = new ArrayList<>();

        System.out.print("¿Cuántos pacientes desea ingresar? (por defecto 4): ");
        String cantStr = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        int cantidad = 4;
        if (!cantStr.isEmpty()) {
            try {
                cantidad = Integer.parseInt(cantStr);
            } catch (NumberFormatException e) {
                cantidad = 4;
            }
        }

        System.out.println("Ingrese " + cantidad + " pacientes:");

        //Plantilla predeterminada de apoyo
        List<Paciente> predeterminados = List.of(
                new Paciente("P1", "Dolor de garganta", NivelGravedad.LEVE, Prioridad.BAJA),
                new Paciente("P2", "Fractura de muñeca", NivelGravedad.MODERADO, Prioridad.MEDIA),
                new Paciente("P3", "Dolor en el pecho", NivelGravedad.GRAVE, Prioridad.ALTA),
                new Paciente("P4", "Paro cardiaco", NivelGravedad.CRITICO, Prioridad.ALTA)
        );

        for (int i = 1; i <= cantidad; i++) {
            String id = "P" + i;
            System.out.print(" " + id + " - Síntoma: ");
            String sintoma = scanner.hasNextLine() ? scanner.nextLine().trim() : "";

            if (sintoma.isEmpty() && i <= predeterminados.size()) {
                Paciente def = predeterminados.get(i - 1);
                sintoma = def.getSintoma();
                System.out.println("'" + sintoma + "'");
                System.out.println("   Nivel: " + def.getNivel().getDescripcion() + "  Prior: " + def.getPrioridad().getEtiqueta());
                pacientes.add(def);
                continue;
            }

            System.out.print("   Nivel (Leve, Moderado, Grave, Crítico): ");
            String nivelStr = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
            NivelGravedad nivel = NivelGravedad.desdeTexto(nivelStr);

            System.out.print("   Prioridad (Baja, Media, Alta): ");
            String priorStr = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
            Prioridad prioridad = Prioridad.desdeTexto(priorStr);

            pacientes.add(new Paciente(id, sintoma, nivel, prioridad));
        }

        System.out.println();
        //Procesar cada paciente a través de la cadena
        pacientes.forEach(cadena::atender);

        //Generar estadísticas usando Streams
        long leves = pacientes.stream()
                .filter(p -> p.isAtendido() && p.getNivel() == NivelGravedad.LEVE)
                .count();

        long moderados = pacientes.stream()
                .filter(p -> p.isAtendido() && p.getNivel() == NivelGravedad.MODERADO)
                .count();

        long graves = pacientes.stream()
                .filter(p -> p.isAtendido() && p.getNivel() == NivelGravedad.GRAVE)
                .count();

        long remitidos = pacientes.stream()
                .filter(Paciente::isRemitido)
                .count();

        double promedioPrioridadAtendidos = pacientes.stream()
                .filter(Paciente::isAtendido)
                .mapToInt(p -> p.getPrioridad().getValor())
                .average()
                .orElse(0.0);

        System.out.println("\n--- Estadísticas ---");
        System.out.printf("Atendidos - Leve: %d Moderado: %d Grave: %d%n", leves, moderados, graves);
        System.out.println("Remitidos a otra institución: " + remitidos);
        System.out.printf(Locale.US, "Promedio prioridad atendidos: %.1f%n%n", promedioPrioridadAtendidos);
    }
}
