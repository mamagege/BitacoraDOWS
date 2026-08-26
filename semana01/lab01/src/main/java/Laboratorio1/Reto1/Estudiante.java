package Reto1;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 1: Mensaje de Bienvenida con Objetos y Lambda
 * ============================================================================
 *
 * MODELO DE DOMINIO: Estudiante
 * ============================================================================
 * Clase POJO (Plain Old Java Object) que modela a un estudiante con cuatro
 * atributos de valor: nombre, edad, correo y semestre.
 *
 * RESPONSABILIDADES (Principio SRP - Single Responsibility):
 * - Esta clase tiene UNA sola responsabilidad: encapsular los datos de un
 *   estudiante y exponerlos a través de getters (acceso de solo lectura).
 * - No contiene lógica de negocio ni de presentación. Eso corresponde a
 *   otras clases del paquete (MensajeBienvenida).
 *
 * NOTA DE DISEÑO:
 * - Todos los campos son `private` y no tienen setters, lo que garantiza
 *   que el objeto, una vez construido, es efectivamente inmutable.
 * - En Java moderno (16+), este patrón puede reemplazarse directamente con
 *   un `record`, que genera `equals()`, `hashCode()` y `toString()` automáticamente.
 */
public class Estudiante {
    private String nombre;
    private int edad;
    private String correo;
    private int semestre;

    Estudiante(String nombre, int edad, String correo, int semestre) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.semestre = semestre;
    }

    public int getEdad() {
        return edad;
    }

    public int getSemestre() {
        return semestre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }
}
