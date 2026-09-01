package Reto1;

import java.util.ArrayList;

/**
 * Recibir una lista de estudiantes y generar un mensaje de bienvenida
 * personalizado que incluya el nombre, semestre, edad y correo de cada uno.
 */
public class MensajeBienvenida {

    public void creadorMensaje(ArrayList<Estudiante> estudiantes) {

        System.out.println("!Hola, bienvenidos! Somos la pareja conformada por ");

        // Itera con Consumer<Estudiante>: imprime los datos de presentación de cada uno
        estudiantes.forEach(estudiante -> {
            String nombre = estudiante.getNombre();
            int semestre = estudiante.getSemestre();
            int edad = estudiante.getEdad();
            System.out.println(nombre + ", estudiante de: " + semestre + "° semestre de " + edad + " años.");
        });

        // Recolecta los correos en una lista auxiliar (efecto secundario controlado)
        ArrayList<String> correos = new ArrayList<>();
        estudiantes.forEach(estudiante -> {
            String correo = estudiante.getCorreo();
            correos.add(correo);
        });

        System.out.print("Nuestros correos  son: ");
        for (String correo : correos) {
            System.out.print(correo + ", ");
        }
    }
}
