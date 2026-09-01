package edu.dosw.lab.estructurales.reto5;

import java.util.ArrayList;
import java.util.List;

/**
 * Mantiene una referencia al componente envuelto y delega las operaciones base.
 */
public abstract class MejoraMotoDecorator implements Moto {
    protected final Moto motoDecorada;
    protected final String nombreMejora;
    protected final double costoMejora;

    public MejoraMotoDecorator(Moto motoDecorada, String nombreMejora, double costoMejora) {
        this.motoDecorada = motoDecorada;
        this.nombreMejora = nombreMejora;
        this.costoMejora = costoMejora;
    }

    @Override
    public String getModelo() {
        return motoDecorada.getModelo();
    }

    @Override
    public double getPrecioBase() {
        return motoDecorada.getPrecioBase();
    }

    @Override
    public double getPrecioMejoras() {
        return motoDecorada.getPrecioMejoras() + costoMejora;
    }

    @Override
    public double getPrecioTotal() {
        return getPrecioBase() + getPrecioMejoras();
    }

    @Override
    public List<String> getMejoras() {
        List<String> lista = new ArrayList<>(motoDecorada.getMejoras());
        lista.add(nombreMejora);
        return lista;
    }

    @Override
    public String getDescripcion() {
        List<String> mejoras = getMejoras();
        if (mejoras.isEmpty()) {
            return getModelo();
        }
        if (mejoras.size() == 1) {
            return getModelo() + " con " + mejoras.getFirst();
        }

        String unidas = String.join(", ", mejoras.subList(0, mejoras.size() - 1));
        return getModelo() + " con " + unidas + " y " + mejoras.getLast();
    }
}
