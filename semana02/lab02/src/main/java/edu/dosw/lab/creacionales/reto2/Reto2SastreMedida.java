package edu.dosw.lab.creacionales.reto2;

import java.text.NumberFormat;
import java.util.*;

public class Reto2SastreMedida {

    public static void ejecutar() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al Taller del Sastre");
        System.out.println("Arma tu traje:");

        TrajeBuilder builder = new TrajeBuilder();

        System.out.print("  ¿Tela? (Lana italiana / Paño nacional): ");
        builder.conTela(sc.nextLine().trim());

        System.out.print("  ¿Saco? (Cruzado / Recto): ");
        builder.conSaco(sc.nextLine().trim());

        System.out.print("  ¿Pantalón? (Corte slim / Corte clásico): ");
        builder.conPantalon(sc.nextLine().trim());

        System.out.print("  ¿Chaleco? (Escriba el tipo o 'Ninguno'): ");
        builder.agregarOpcional("Chaleco", sc.nextLine().trim());

        System.out.print("  ¿Bordado? (Escriba iniciales o 'Ninguno'): ");
        builder.agregarOpcional("Bordado", sc.nextLine().trim());

        Traje trajeFinal = builder.build();
        imprimirTraje(trajeFinal);
    }

    private static void imprimirTraje(Traje traje) {
        NumberFormat formato = NumberFormat.getNumberInstance(Locale.GERMANY);
        System.out.println("\n-------- Tu Traje --------");
        
        traje.getPiezas().forEach(pieza -> 
            System.out.printf("%-10s %-11s $%s%n", obtenerCategoria(pieza.getNombre()), pieza.getNombre(), formato.format(pieza.getPrecio()))
        );

        System.out.println(" ");
        System.out.println("Total:    $" + formato.format(traje.calcularPrecioTotal()));
        System.out.println("¡Lo esperamos en la prueba!");
    }

    private static String obtenerCategoria(String nombrePieza) {
        if (nombrePieza.contains("Lana") || nombrePieza.contains("Paño")) return "Tela:";
        if (nombrePieza.contains("Cruzado") || nombrePieza.contains("Recto")) return "Saco:";
        if (nombrePieza.contains("Slim") || nombrePieza.contains("Clásico")) return "Pantalón:";
        if (nombrePieza.contains("Chaleco")) return "Chaleco:";
        if (nombrePieza.contains("Forro")) return "Forro:";
        if (nombrePieza.contains("Bordado")) return "Bordado:";
        return "Pieza:";
    }
}