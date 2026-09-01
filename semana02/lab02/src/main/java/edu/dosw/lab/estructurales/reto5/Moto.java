package edu.dosw.lab.estructurales.reto5;

import java.util.List;

/**
 * Define las operaciones básicas que cualquier moto debe ofrecer.
 */
public interface Moto {
    String getModelo();
    String getDescripcion();
    double getPrecioBase();
    double getPrecioMejoras();
    double getPrecioTotal();
    List<String> getMejoras();
}
