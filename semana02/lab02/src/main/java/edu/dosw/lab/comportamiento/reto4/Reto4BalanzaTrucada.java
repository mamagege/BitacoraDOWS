package edu.dosw.lab.comportamiento.reto4;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;


public class Reto4BalanzaTrucada {

    public static void ejecutar() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Balanza Honesta del Mercado");
        System.out.print("¿Cuántos pesajes? ");
        int cantidadPesajes = Integer.parseInt(sc.nextLine().trim());

        List<Pesaje> sesion = new ArrayList<>();

        for (int i = 1; i <= cantidadPesajes; i++) {
            System.out.println("\nPesaje " + i + ":");
            System.out.print("  Cantidad numérica: ");
            double cantidad = Double.parseDouble(sc.nextLine().trim());
            
            System.out.print("  Unidad de origen (g, lb, @, kg): ");
            EstrategiaUnidad origen = seleccionarEstrategia(sc.nextLine().trim());
            
            System.out.print("  Unidad de destino (g, lb, @, kg): ");
            EstrategiaUnidad destino = seleccionarEstrategia(sc.nextLine().trim());

            sesion.add(new Pesaje(cantidad, origen, destino));
        }
        imprimirResumen(sesion);
    }

    private static EstrategiaUnidad seleccionarEstrategia(String simbolo) {
        if (simbolo.equalsIgnoreCase("g")) return new Gramo();
        if (simbolo.equalsIgnoreCase("lb")) return new Libra();
        if (simbolo.equalsIgnoreCase("@")) return new Arroba();
        return new Kilogramo();
    }

    private static void imprimirResumen(List<Pesaje> sesion) {
        DecimalFormat df = configurarFormatoNumerico();
        System.out.println();

        for (int i = 0; i < sesion.size(); i++) {
            Pesaje p = sesion.get(i);
            String originalStr = df.format(p.getCantidadOriginal());
            String resultadoStr = df.format(p.calcularResultado());
            
            System.out.println("P " + (i + 1) + ": " + originalStr + " " + p.getOrigen().getSimbolo() + 
                               " = " + resultadoStr + " " + p.getDestino().getSimbolo());
        }

        double totalKilos = sesion.stream()
                .mapToDouble(Pesaje::obtenerKilosBase)
                .sum();

        System.out.println("--- Resumen ---");
        System.out.println("Total kg equivalente: " + df.format(totalKilos) + " kg");
        System.out.println("¡Gracias por comprar en la plaza!");
    }

    private static DecimalFormat configurarFormatoNumerico() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
        return new DecimalFormat("#,##0.###", simbolos);
    }
}