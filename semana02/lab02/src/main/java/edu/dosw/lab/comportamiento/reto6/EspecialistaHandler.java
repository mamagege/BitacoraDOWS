package edu.dosw.lab.comportamiento.reto6;

/**
 * Atiende dolencias de nivel GRAVE y prioridad máxima ALTA (3).
 */
public class EspecialistaHandler extends ManejadorAtencion {

    @Override
    protected boolean puedeAtender(Paciente paciente) {
        return paciente.getNivel() == NivelGravedad.GRAVE && paciente.getPrioridad().getValor() <= 3;
    }

    @Override
    protected void procesar(Paciente paciente) {
        paciente.setAtendidoPor("Especialista");
        System.out.println(paciente.getId() + ": Especialista atendió.");
    }
}
