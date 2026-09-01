package ejercicio1;

public class ColombiaPaymentFactory implements PaymentFactory{
    @Override
    public PaymentStrategy create(String type) {
        return switch (type) {
            case "Nequi" -> new NequiStrategy();
            case "PSE" -> new PseStrategy();
            default -> null;
        };
    }
}
