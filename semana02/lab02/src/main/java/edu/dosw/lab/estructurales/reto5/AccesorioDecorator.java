package edu.dosw.lab.estructurales.reto5;

/**
 * Permite añadir cualquier accesorio, pintura o complemento
 */
public class AccesorioDecorator extends MejoraMotoDecorator {

    /**
     * Constructor que obtiene automáticamente el precio del catálogo.
     */
    public AccesorioDecorator(Moto motoDecorada, String nombreMejora) {
        super(motoDecorada, nombreMejora, CatalogoTaller.getPrecioMejoraPorNombre(nombreMejora));
    }

    /**
     * Constructor con costo explícito.
     */
    public AccesorioDecorator(Moto motoDecorada, String nombreMejora, double costoMejora) {
        super(motoDecorada, nombreMejora, costoMejora);
    }
}
