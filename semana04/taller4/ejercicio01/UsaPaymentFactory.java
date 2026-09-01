package ejercicio01;

public class UsaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        if (type == null) {
            throw new IllegalArgumentException("El tipo de medio de pago no puede ser nulo");
        }
        return switch (type.trim().toUpperCase()) {
            case "PAYPAL" -> new PaypalStrategy("customer.usa@globalstore.com");
            case "STRIPE" -> new StripeStrategy("cus_NY8721990xZ");
            case "TARJETA" -> new TarjetaStrategy("4000123456789010");
            case "TRANSFERENCIA", "WIRE" -> new BankTransferStrategy("US99-CHASE-00129381");
            default -> throw new IllegalArgumentException("Medio de pago no soportado en USA: " + type);
        };
    }
}
