package Reto1;

/**
 * Leer los datos de dos estudiantes por consola y generar un mensaje de
 * bienvenida personalizado que muestre su nombre, semestre, edad y correos.
 *
 * FLUJO DE EJECUCIÓN:
 * 1. Se instancia `LectorEstudiantes` → lee datos de A y B desde consola.
 * 2. Se instancia `MensajeBienvenida` → formatea e imprime el saludo.
 */
public class reto1 {
    public static void main(String[] args) {
        LectorEstudiantes lector = new LectorEstudiantes();
        lector.leerDatos();

        MensajeBienvenida mensaje = new MensajeBienvenida();
        mensaje.creadorMensaje(lector.getEstudiantes());
    }
}
