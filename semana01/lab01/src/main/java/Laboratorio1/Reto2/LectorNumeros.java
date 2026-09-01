package Reto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectorNumeros {

    public List<Integer> leerNumeros () {
        Scanner scanner = new Scanner(System.in);
        List<Integer> listaNumeros = new ArrayList<>();

        System.out.print("Lista (Separado por espacios):");
        String linea = scanner.nextLine();

        Scanner lineScanner = new Scanner(linea);

        while (lineScanner.hasNextInt()) {
            listaNumeros.add(lineScanner.nextInt());
        }


        lineScanner.close();

        return listaNumeros;
    }

}
