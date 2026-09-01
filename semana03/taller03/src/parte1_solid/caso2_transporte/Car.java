package parte1_solid.caso2_transporte;

public class Car implements Movable {
    @Override
    public void move() {
        System.out.println("[Car] Conduciendo sobre carretera asfaltada.");
    }
}
