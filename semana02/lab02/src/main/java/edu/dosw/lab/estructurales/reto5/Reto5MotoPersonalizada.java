package edu.dosw.lab.estructurales.reto5;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Reto 5: La Moto Personalizada
 * Patrón: Decorator (Estructural)
 */
public class Reto5MotoPersonalizada {

    private static final Locale LOCALE_COL = Locale.of("es", "CO");

    public static void ejecutar() {
        ejecutarConScanner(new Scanner(System.in));
    }

    public static void ejecutarConScanner(Scanner scanner) {
       
        //Creación de la moto base
        Moto moto = new MotoBase("Naked 250");

        System.out.println("Taller Turbo Andes");
        System.out.println("Moto base: " + moto.getModelo() + " (" + formatearMoneda(moto.getPrecioBase()) + ")");
        System.out.println("Elige tus mejoras:");

        //Catálogo de mejoras disponibles
        CatalogoTaller.getMejorasDisponibles().values().stream()
                .limit(4)
                .forEach(m -> System.out.println(" " + m.id() + ". " + m.nombre() + " (+" + formatearMoneda(m.precio()) + ")"));

        System.out.print("\nMejoras elegidas (ej: 1, 2, 4): ");
        String linea = "";
        if (scanner.hasNextLine()) {
            linea = scanner.nextLine().trim();
        }
        if (linea.isEmpty()) {
            linea = "1, 2, 4";
            System.out.println(linea);
        } else {
            System.out.println();
        }

        // Procesamiento funcional con Streams
        moto = Arrays.stream(linea.split("[,\\s]+"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .mapToObj(CatalogoTaller::buscarMejoraPorId)
                .flatMap(java.util.Optional::stream)
                .reduce(moto,
                        (motoAcumulada, opcion) -> new AccesorioDecorator(motoAcumulada, opcion.nombre()),
                        (m1, m2) -> m2
                );

        //Presentación de resultados utilizando Streams
        System.out.println("\n--- Tu Moto ---");
        System.out.println(moto.getModelo());

        moto.getMejoras().stream()
                .map(mejora -> " + " + mejora)
                .forEach(System.out::println);

        System.out.println("\nDescripción:");
        System.out.println(moto.getDescripcion());

        System.out.println("\nPrecio base: " + formatearMoneda(moto.getPrecioBase()));
        System.out.println("Mejoras:     " + formatearMoneda(moto.getPrecioMejoras()));
        System.out.println("Total:       " + formatearMoneda(moto.getPrecioTotal()));
        System.out.println("¡Buen viaje!\n");
    }

    public static String formatearMoneda(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(LOCALE_COL);
        formato.setMaximumFractionDigits(0);
        return formato.format(valor).replace("COP", "$").trim();
    }
}
