package ejercicio01;

public class ColombiaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        if (type == null) {
            throw new IllegalArgumentException("El tipo de medio de pago no puede ser nulo");
        }
        return switch (type.trim().toUpperCase()) {
            case "PSE" -> new PseStrategy("Bancolombia");
            case "NEQUI" -> new NequiStrategy("3108765432");
            case "TARJETA" -> new TarjetaStrategy("4532009812345678");
            case "TRANSFERENCIA" -> new BankTransferStrategy("CO00-9812-3344-01");
            default -> throw new IllegalArgumentException("Medio de pago no soportado en Colombia: " + type);
        };
    }
}
