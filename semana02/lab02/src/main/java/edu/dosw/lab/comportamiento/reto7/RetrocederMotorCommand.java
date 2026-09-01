package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para retroceder el motor.
 */
public class RetrocederMotorCommand extends BaseComandoRover {
    private final int metros;

    public RetrocederMotorCommand(RoverChibchombo rover, String operador, int metros) {
        super(rover, operador);
        this.metros = metros;
    }

    @Override
    public void ejecutar() {
        rover.retrocederMotor(metros, operador);
    }

    @Override
    public void deshacer() {
        rover.avanzarMotor(metros, operador);
        setDeshecho(true);
    }

    @Override
    public String getDescripcion() {
        return "Motor Retroceder(" + metros + "m)";
    }
}
