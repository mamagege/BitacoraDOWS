package Reto3;


import java.util.Scanner;
import static Reto3.Amplificador.*;
import static Reto3.Descifrador.*;


public class reto3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Mensaje interceptado: ");
        String palabra = scanner.nextLine();
        scanner.close();

        System.out.println("Canal A: " + amplificar(palabra));
        System.out.println("Canal B: " + descrifar(palabra));
        System.out.println("\nDescifrado Final: " + amplificar(descrifar(palabra)));


    }
}
