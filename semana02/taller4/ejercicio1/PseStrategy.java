package ejercicio1;

public class PseStrategy implements PaymentStrategy{
    @Override
    public void process(double amount) {
        System.out.println("Pagaste con PSE: " + amount);
    }
}
