package ejercicio01;

/**
 * Demostración de ejecución para el Ejercicio #01: Plataforma de Pagos Inteligentes.
 * Patrones combinados: Strategy + Factory Method.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  EJERCICIO #01: PLATAFORMA DE PAGOS INTELIGENTES");
        System.out.println("  Patrones Combinados: STRATEGY + FACTORY METHOD");
        System.out.println("================================================================================\n");

        // Escenario 1: Usuario en Colombia pagando con PSE
        System.out.println("▶ Escenario 1: Compra en Colombia con PSE");
        PaymentFactory colombiaFactory = new ColombiaPaymentFactory();
        PaymentStrategy pseStrategy = colombiaFactory.create("PSE");
        Checkout checkoutCol = new Checkout(pseStrategy);
        checkoutCol.process(150000.0);

        // Escenario 2: Usuario en Colombia pagando con Nequi
        System.out.println("▶ Escenario 2: Compra en Colombia con Nequi");
        PaymentStrategy nequiStrategy = colombiaFactory.create("NEQUI");
        Checkout checkoutNequi = new Checkout(nequiStrategy);
        checkoutNequi.process(45000.0);

        // Escenario 3: Usuario en USA pagando con PayPal
        System.out.println("▶ Escenario 3: Compra en USA con PayPal");
        PaymentFactory usaFactory = new UsaPaymentFactory();
        PaymentStrategy paypalStrategy = usaFactory.create("PAYPAL");
        Checkout checkoutUsa = new Checkout(paypalStrategy);
        checkoutUsa.process(89.99);

        // Escenario 4: Usuario en USA pagando con Stripe
        System.out.println("▶ Escenario 4: Compra en USA con Stripe");
        PaymentStrategy stripeStrategy = usaFactory.create("STRIPE");
        Checkout checkoutStripe = new Checkout(stripeStrategy);
        checkoutStripe.process(250.00);

        System.out.println("✓ Verificación del Ejercicio #01 finalizada exitosamente.");
    }
}
