package parte1_solid.caso2_transporte;

/**
 * Contrato base para entidades capaces de desplazarse.
 * Todo subtipo que implemente Movable garantiza que puede moverse sin lanzar excepciones runtime.
 */
public interface Movable {
    void move();
}
