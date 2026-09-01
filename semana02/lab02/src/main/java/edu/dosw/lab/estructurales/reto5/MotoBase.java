package edu.dosw.lab.estructurales.reto5;

import java.util.Collections;
import java.util.List;

/**
 * Representa la moto estándar sin modificaciones ni accesorios.
 */
public class MotoBase implements Moto {
    private final String modelo;
    private final double precioBase;

    public MotoBase(String modelo) {
        this(modelo, CatalogoTaller.getPrecioMoto(modelo));
    }

    /**
     * Constructor con precio explícito.
     */
    public MotoBase(String modelo, double precioBase) {
        this.modelo = modelo;
        this.precioBase = precioBase;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public String getDescripcion() {
        return modelo;
    }

    @Override
    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public double getPrecioMejoras() {
        return 0.0;
    }

    @Override
    public double getPrecioTotal() {
        return precioBase;
    }

    @Override
    public List<String> getMejoras() {
        return Collections.emptyList();
    }
}
