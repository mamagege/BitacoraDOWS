package ejercicio1;

public class NequiStrategy implements PaymentStrategy {


    @Override
    public void process(double amount) {
        System.out.println("Pagaste con Nequi: " + amount);
    }
}
