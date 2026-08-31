package Reto1;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 1: Punto de Entrada Principal
 * ============================================================================
 *
 * OBJETIVO:
 * Leer los datos de dos estudiantes por consola y generar un mensaje de
 * bienvenida personalizado que muestre su nombre, semestre, edad y correos.
 *
 * ARQUITECTURA DEL RETO (Patrón Separación de Responsabilidades):
 *
 *   ┌──────────────────┐     leerDatos()       ┌───────────────────────┐
 *   │    reto1 (main)  │ ──────────────────► │   LectorEstudiantes   │
 *   └──────────────────┘                       │  (captura datos E/S)  │
 *           │                                  └───────────────────────┘
 *           │ getEstudiantes()
 *           ▼
 *   ┌──────────────────────┐
 *   │  MensajeBienvenida   │
 *   │  (formatea y muestra)│
 *   └──────────────────────┘
 *
 * FLUJO DE EJECUCIÓN:
 *   1. Se instancia `LectorEstudiantes` → lee datos de A y B desde consola.
 *   2. Se instancia `MensajeBienvenida` → formatea e imprime el saludo.
 *   3. La separación entre lectura (E/S) y presentación facilita el testing
 *      y cumple con el Principio de Responsabilidad Única (SRP).
 */
public class reto1 {
    public static void main(String[] args) {
        LectorEstudiantes lector = new LectorEstudiantes();
        lector.leerDatos();

        MensajeBienvenida mensaje = new MensajeBienvenida();
        mensaje.creadorMensaje(lector.getEstudiantes());
    }
}
