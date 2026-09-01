package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para avanzar el motor.
 */
public class AvanzarMotorCommand extends BaseComandoRover {
    private final int metros;

    public AvanzarMotorCommand(RoverChibchombo rover, String operador, int metros) {
        super(rover, operador);
        this.metros = metros;
    }

    @Override
    public void ejecutar() {
        rover.avanzarMotor(metros, operador);
    }

    @Override
    public void deshacer() {
        rover.retrocederMotor(metros, operador);
        setDeshecho(true);
    }

    @Override
    public String getDescripcion() {
        return "Motor Avanzar(" + metros + "m)";
    }
}
