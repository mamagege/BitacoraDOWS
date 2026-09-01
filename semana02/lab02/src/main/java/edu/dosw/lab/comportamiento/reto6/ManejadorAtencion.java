package edu.dosw.lab.comportamiento.reto6;

/**
 * Define la estructura para encadenar manejadores y delegar solicitudes.
 */
public abstract class ManejadorAtencion {
    protected ManejadorAtencion siguiente;

    public ManejadorAtencion setSiguiente(ManejadorAtencion siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public void atender(Paciente paciente) {
        if (puedeAtender(paciente)) {
            procesar(paciente);
        } else if (siguiente != null) {
            siguiente.atender(paciente);
        } else {
            paciente.setRemitido(true);
            System.out.println(paciente.getId() + ": Sin profesional disponible.");
            System.out.println(" Paciente remitido a otra institución.");
        }
    }

    protected abstract boolean puedeAtender(Paciente paciente);
    protected abstract void procesar(Paciente paciente);
}
