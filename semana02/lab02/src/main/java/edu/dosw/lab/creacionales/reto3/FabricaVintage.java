package edu.dosw.lab.creacionales.reto3;

public class FabricaVintage implements FabricaInstrumentos {
    @Override
    public Instrumento crearCuerda(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Vintage", "442 Hz", 5.0);
    }

    @Override
    public Instrumento crearViento(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Vintage", "442 Hz", 5.0);
    }

    @Override
    public Instrumento crearPercusion(String modelo) {
        return new Instrumento(CatalogoPrecio.capitalizar(modelo), CatalogoPrecio.obtenerPrecioBase(modelo), "Vintage", "442 Hz", 5.0);
    }
}