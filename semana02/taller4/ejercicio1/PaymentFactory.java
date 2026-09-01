package ejercicio1;

public interface PaymentFactory {

    PaymentStrategy create(String type);

}
