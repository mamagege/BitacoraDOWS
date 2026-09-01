package edu.dosw.lab.comportamiento.reto6;

/**
 * Atiende dolencias de nivel LEVE y prioridad máxima BAJA (1).
 */
public class EnfermeroHandler extends ManejadorAtencion {

    @Override
    protected boolean puedeAtender(Paciente paciente) {
        return paciente.getNivel() == NivelGravedad.LEVE && paciente.getPrioridad().getValor() <= 1;
    }

    @Override
    protected void procesar(Paciente paciente) {
        paciente.setAtendidoPor("Enfermero");
        System.out.println(paciente.getId() + ": Enfermero atendió.");
    }
}
