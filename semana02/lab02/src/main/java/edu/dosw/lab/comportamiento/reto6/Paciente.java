package edu.dosw.lab.comportamiento.reto6;

/**
 * Representa a un paciente que ingresa a la sala de urgencias.
 */
public class Paciente {
    private final String id;
    private final String sintoma;
    private final NivelGravedad nivel;
    private final Prioridad prioridad;
    private String atendidoPor;
    private boolean remitido;

    public Paciente(String id, String sintoma, NivelGravedad nivel, Prioridad prioridad) {
        this.id = id;
        this.sintoma = sintoma;
        this.nivel = nivel;
        this.prioridad = prioridad;
        this.atendidoPor = null;
        this.remitido = false;
    }

    public String getId() {
        return id;
    }

    public String getSintoma() {
        return sintoma;
    }

    public NivelGravedad getNivel() {
        return nivel;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public String getAtendidoPor() {
        return atendidoPor;
    }

    public void setAtendidoPor(String atendidoPor) {
        this.atendidoPor = atendidoPor;
    }

    public boolean isRemitido() {
        return remitido;
    }

    public void setRemitido(boolean remitido) {
        this.remitido = remitido;
    }

    public boolean isAtendido() {
        return atendidoPor != null && !remitido;
    }
}
