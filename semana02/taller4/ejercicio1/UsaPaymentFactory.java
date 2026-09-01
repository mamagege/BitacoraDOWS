package ejercicio1;

public class UsaPaymentFactory implements PaymentFactory {

    @Override
    public PaymentStrategy create(String type) {
        return switch (type) {
            case "Tarjeta" -> new TarjetaStrategy();
            default -> null;

        };
    }
}


