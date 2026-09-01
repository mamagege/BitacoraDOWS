package Reto1;

/**
 * RESPONSABILIDADES (Principio SRP - Single Responsibility):
 * - Esta clase tiene UNA sola responsabilidad: encapsular los datos de un
 * estudiante y exponerlos a través de getters.
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
