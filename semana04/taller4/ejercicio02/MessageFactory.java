package ejercicio2;

public interface MessageFactory {
    Message build(OrderEvent event);
}
