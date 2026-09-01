package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para accionar el brazo robótico (recoger muestra).
 */
public class RecogerBrazoCommand extends BaseComandoRover {

    public RecogerBrazoCommand(RoverChibchombo rover, String operador) {
        super(rover, operador);
    }

    @Override
    public void ejecutar() {
        rover.recogerBrazo(operador);
    }

    @Override
    public void deshacer() {
        rover.soltarBrazo(operador);
        setDeshecho(true);
    }

    @Override
    public String getDescripcion() {
        return "Brazo Recoger";
    }
}
