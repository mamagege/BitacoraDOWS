package Reto2;

import java.util.List;
import Reto2.Funciones.*;

public class reto2 {

    public static void main(String[] args) {

        LectorNumeros lector = new LectorNumeros();
        List<Integer> lista1 = lector.leerNumeros();
        List<Integer> lista2 = lector.leerNumeros();

        MensajeFunciones mensajeFunciones = new MensajeFunciones();

        mensajeFunciones.mostrarMensaje(lista1);
        mensajeFunciones.mostrarMensaje(lista2);




    }
}
