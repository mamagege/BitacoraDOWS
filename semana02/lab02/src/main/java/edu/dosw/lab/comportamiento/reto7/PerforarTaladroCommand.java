package edu.dosw.lab.comportamiento.reto7;

/**
 * Comando concreto para accionar el taladro.
 */
public class PerforarTaladroCommand extends BaseComandoRover {
    private final int profundidadCm;

    public PerforarTaladroCommand(RoverChibchombo rover, String operador, int profundidadCm) {
        super(rover, operador);
        this.profundidadCm = profundidadCm;
    }

    @Override
    public void ejecutar() {
        rover.perforarTaladro(profundidadCm, operador);
    }

    @Override
    public void deshacer() {
        rover.retraerTaladro();
        setDeshecho(true);
    }

    @Override
    public String getDescripcion() {
        return "Taladro Perforar(" + profundidadCm + "cm)";
    }
}
