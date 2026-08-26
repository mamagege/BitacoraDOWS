package Reto1;

import java.util.ArrayList;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 1: Generador del Mensaje de Bienvenida
 * ============================================================================
 *
 * OBJETIVO DEL RETO:
 * Recibir una lista de estudiantes y generar un mensaje de bienvenida
 * personalizado que incluya el nombre, semestre, edad y correo de cada uno.
 *
 * CONCEPTOS FUNCIONALES APLICADOS:
 *
 * 1. `ArrayList.forEach(Consumer<T>)`:
 *    - Se utiliza `forEach` con una expresión lambda para iterar de forma
 *      declarativa la colección, en lugar de escribir un bucle `for` tradicional.
 *    - La lambda actúa como un `Consumer<Estudiante>`: consume cada objeto
 *      estudiante y ejecuta una acción (imprimir) sin retornar nada.
 *
 * 2. Primer `forEach` (líneas de nombre/semestre/edad):
 *    - Accede a los getters del objeto para extraer sus atributos y
 *      construye la línea formateada de salida.
 *
 * 3. Segundo `forEach` (recolección de correos):
 *    - Extrae el correo de cada estudiante y lo acumula en una `ArrayList<String>`.
 *    - NOTA DE ARQUITECTURA: Esta es la única parte "impura" del diseño,
 *      ya que la lambda modifica la lista `correos` externa. En programación
 *      funcional pura, esto sería un efecto secundario. Una refactorización
 *      idiomática usaría `.map().collect()` de la Stream API para evitarlo.
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
