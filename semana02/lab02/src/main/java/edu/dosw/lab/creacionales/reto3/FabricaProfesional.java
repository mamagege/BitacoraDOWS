package edu.dosw.lab.creacionales.reto3;

public class FabricaProfesional implements FabricaInstrumentos {
    @Override
    public Instrumento crearCuerda(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Profesional", "440 Hz", 3.0);
    }

    @Override
    public Instrumento crearViento(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Profesional", "440 Hz", 3.0);
    }

    @Override
    public Instrumento crearPercusion(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Profesional", "440 Hz", 3.0);
    }
}