package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para grabar video con la cámara.
 */
public class GrabarCamaraCommand extends BaseComandoRover {
    private final int segundos;

    public GrabarCamaraCommand(RoverChibchombo rover, String operador, int segundos) {
        super(rover, operador);
        this.segundos = segundos;
    }

    @Override
    public void ejecutar() {
        rover.grabarCamara(segundos, operador);
    }

    @Override
    public void deshacer() {
        rover.detenerCamara(operador);
        setDeshecho(true);
    }

    @Override
    public String getDescripcion() {
        return "Cámara Grabar(" + segundos + "s)";
    }
}
