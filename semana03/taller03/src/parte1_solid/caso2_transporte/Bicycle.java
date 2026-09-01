package parte1_solid.caso2_transporte;

public class Bicycle implements Movable {
    @Override
    public void move() {
        System.out.println("[Bicycle] Pedaleando por la ciclorruta.");
    }
}
