package edu.dosw.lab.comportamiento.reto7;

/**
 * Clase base abstracta para los comandos del Rover.
 */
public abstract class BaseComandoRover implements ComandoRover {
    protected final RoverChibchombo rover;
    protected final String operador;
    private boolean deshecho;

    public BaseComandoRover(RoverChibchombo rover, String operador) {
        this.rover = rover;
        this.operador = operador;
        this.deshecho = false;
    }

    @Override
    public String getOperador() {
        return operador;
    }

    @Override
    public boolean isDeshecho() {
        return deshecho;
    }

    @Override
    public void setDeshecho(boolean deshecho) {
        this.deshecho = deshecho;
    }
}
