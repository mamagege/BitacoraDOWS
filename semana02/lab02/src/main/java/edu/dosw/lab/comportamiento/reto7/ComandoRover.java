package edu.dosw.lab.comportamiento.reto7;

/**
 * Interfaz Command del patrón Command.
 * Encapsula una petición como un objeto, permitiendo parametrización, encolamiento y operaciones de deshacer (undo).
 */
public interface ComandoRover {
    void ejecutar();
    void deshacer();
    String getOperador();
    String getDescripcion();
    boolean isDeshecho();
    void setDeshecho(boolean deshecho);
}
