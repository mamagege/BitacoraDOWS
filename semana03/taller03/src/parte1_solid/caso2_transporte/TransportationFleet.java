package parte1_solid.caso2_transporte;

import java.util.List;

/**
 * Servicio cliente: Demuestra que cualquier subtipo de Movable (Car, Boat, etc.)
 * puede ser sustituido de manera transparente cumpliendo el principio LSP.
 */
public class TransportationFleet {
    private final List<Movable> fleet;

    public TransportationFleet(List<Movable> fleet) {
        this.fleet = fleet;
    }

    public void dispatchAll() {
        for (Movable vehicle : fleet) {
            vehicle.move(); // Sustitución segura sin excepciones runtime inesperadas
        }
    }
}
