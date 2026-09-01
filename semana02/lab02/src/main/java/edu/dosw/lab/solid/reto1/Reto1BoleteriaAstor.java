package edu.dosw.lab.solid.reto1;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Reto1BoleteriaAstor {

    public static void ejecutar() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al Cine Astor!");
        
        System.out.print("Espectador (General / Estudiante / Tercera edad): ");
        String tipoStr = sc.nextLine().trim();
        Espectador espectador = instanciarEspectador(tipoStr);

        System.out.println("\nCartelera y precios:");
        System.out.println("  Boleta 2D    $14.000");
        System.out.println("  Boleta 3D    $22.000");
        System.out.println("  Crispetas    $9.000");
        System.out.println("  Gaseosa      $4.500");

        Orden orden = new Orden(espectador);
        
        Item boleta2D = new Item("Boleta 2D", 14000);
        Item boleta3D = new Item("Boleta 3D", 22000);
        Item crispetas = new Item("Crispetas", 9000);
        Item gaseosa = new Item("Gaseosa", 4500);

        System.out.println("\nIngrese su orden (cantidades numéricas, 0 si no desea):");
        
        System.out.print("  Boleta 2D: ");
        int cant2D = sc.nextInt();
        
        System.out.print("  Boleta 3D: ");
        int cant3D = sc.nextInt();
        
        System.out.print("  Crispetas: ");
        int cantCrispetas = sc.nextInt();
        
        System.out.print("  Gaseosa:   ");
        int cantGaseosa = sc.nextInt();

        System.out.println(); 

        if(cant2D > 0) { 
            orden.agregarItem(boleta2D, cant2D); 
            System.out.println("Boleta 2D: " + cant2D + " unidades agregadas a la orden."); 
        }
        if(cant3D > 0) { 
            orden.agregarItem(boleta3D, cant3D); 
            System.out.println("Boleta 3D: " + cant3D + " unidades agregadas a la orden."); 
        }
        if(cantCrispetas > 0) { 
            orden.agregarItem(crispetas, cantCrispetas); 
            System.out.println("Crispetas: " + cantCrispetas + " unidades agregadas a la orden."); 
        }
        if(cantGaseosa > 0) { 
            orden.agregarItem(gaseosa, cantGaseosa); 
            System.out.println("Gaseosa: " + cantGaseosa + " unidades agregadas a la orden."); 
        }

        imprimirFactura(orden);

    }

    private static Espectador instanciarEspectador(String tipo) {
        if (tipo.equalsIgnoreCase("Estudiante")) return new Estudiante();
        if (tipo.equalsIgnoreCase("Tercera edad")) return new TerceraEdad();
        return new General();
    }

    private static void imprimirFactura(Orden orden) {
        NumberFormat formato = NumberFormat.getNumberInstance(Locale.GERMANY); 
        
        System.out.println(" ");
        System.out.println("------ FACTURA DE TAQUILLA -----");
        System.out.println("Espectador: " + orden.getEspectador().getTipo());
        System.out.println("Ítems:");
        
        Map<String, Long> conteoItems = orden.getItems().stream()
            .collect(Collectors.groupingBy(Item::getNombre, Collectors.counting()));
            
        orden.getItems().stream()
            .map(Item::getNombre).distinct()
            .forEach(nombre -> {
                long cantidad = conteoItems.get(nombre);
                int precioUnit = orden.getItems().stream().filter(i -> i.getNombre().equals(nombre)).findFirst().get().getPrecio();
                System.out.println("  " + nombre + "    - $" + formato.format(cantidad * precioUnit));
            });

        System.out.println("Subtotal:          $" + formato.format(orden.calcularSubtotal()));
        System.out.println("Descuento:         $" + formato.format(orden.calcularDescuento()));
        System.out.println("Total a pagar:     $" + formato.format(orden.calcularTotal()));
        System.out.println("--------------------------------");
        System.out.println("¡Disfrute la función!");
    }
}