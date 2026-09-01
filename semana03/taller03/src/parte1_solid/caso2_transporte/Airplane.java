package parte1_solid.caso2_transporte;

public class Airplane implements Movable {
    @Override
    public void move() {
        System.out.println("[Airplane] Volando por el espacio aéreo.");
    }
}
