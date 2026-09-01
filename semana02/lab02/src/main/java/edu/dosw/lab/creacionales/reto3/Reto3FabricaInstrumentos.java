package edu.dosw.lab.creacionales.reto3;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Reto3FabricaInstrumentos {

    public static void ejecutar() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a Armonía Andina");
        System.out.print("¿Cuántos instrumentos desea pedir? ");
        int cantidad = Integer.parseInt(sc.nextLine().trim());

        Pedido pedido = new Pedido();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("Instrumento " + i + ":");
            System.out.print("  Familia (Cuerda / Viento / Percusión): ");
            String familia = sc.nextLine().trim();

            System.out.print("  Modelo: ");
            String modelo = sc.nextLine().trim();

            System.out.print("  Gama (Estudiante / Profesional / Vintage): ");
            String gama = sc.nextLine().trim();

            FabricaInstrumentos fabrica = seleccionarFabrica(gama);
            Instrumento inst = fabricarPorFamilia(fabrica, familia, modelo);
            
            pedido.agregarInstrumento(inst);
        }

        imprimirResumen(pedido);

    }

    private static FabricaInstrumentos seleccionarFabrica(String gama) {
        if (gama.equalsIgnoreCase("Profesional")) return new FabricaProfesional();
        if (gama.equalsIgnoreCase("Vintage")) return new FabricaVintage();
        return new FabricaEstudiante();
    }

    private static Instrumento fabricarPorFamilia(FabricaInstrumentos fabrica, String familia, String modelo) {
        if (familia.toLowerCase().contains("viento")) return fabrica.crearViento(modelo);
        if (familia.toLowerCase().contains("percus")) return fabrica.crearPercusion(modelo);
        return fabrica.crearCuerda(modelo);
    }

    private static void imprimirResumen(Pedido pedido) {
        NumberFormat formato = NumberFormat.getNumberInstance(Locale.GERMANY);
        System.out.println();

        for (int i = 0; i < pedido.getInstrumentos().size(); i++) {
            Instrumento inst = pedido.getInstrumentos().get(i);
            System.out.println("Instrumento " + (i + 1) + ": " + inst.getNombreCompleto());
            System.out.println("  Afinación: " + inst.getAfinacion());
            System.out.println("  Precio: $" + formato.format(inst.calcularPrecioFinal()));
        }

        System.out.println("Total a pagar: $" + formato.format(pedido.calcularTotal()));
        System.out.println("¡Gracias por su pedido!");
    }
}