package edu.dosw.lab.comportamiento.reto6;

/**
 * Atiende dolencias de nivel MODERADO y prioridad máxima MEDIA (2).
 */
public class MedicoGeneralHandler extends ManejadorAtencion {

    @Override
    protected boolean puedeAtender(Paciente paciente) {
        return paciente.getNivel() == NivelGravedad.MODERADO && paciente.getPrioridad().getValor() <= 2;
    }

    @Override
    protected void procesar(Paciente paciente) {
        paciente.setAtendidoPor("Médico General");
        System.out.println(paciente.getId() + ": Médico General atendió.");
    }
}
