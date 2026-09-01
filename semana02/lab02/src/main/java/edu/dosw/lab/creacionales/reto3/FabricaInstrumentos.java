package edu.dosw.lab.creacionales.reto3;

public interface FabricaInstrumentos {
    Instrumento crearCuerda(String modelo);
    Instrumento crearViento(String modelo);
    Instrumento crearPercusion(String modelo);
}