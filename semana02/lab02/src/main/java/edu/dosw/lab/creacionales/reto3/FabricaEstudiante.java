package edu.dosw.lab.creacionales.reto3;

public class FabricaEstudiante implements FabricaInstrumentos {
    @Override
    public Instrumento crearCuerda(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Estudiante", "440 Hz", 1.0);
    }

    @Override
    public Instrumento crearViento(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Estudiante", "440 Hz", 1.0);
    }

    @Override
    public Instrumento crearPercusion(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Estudiante", "440 Hz", 1.0);
    }
}