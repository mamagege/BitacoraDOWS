package ejercicio2;

public class EmailMessageFactory implements MessageFactory {

    @Override
    public Message build(OrderEvent event) {
        return new Message();

    }
}
