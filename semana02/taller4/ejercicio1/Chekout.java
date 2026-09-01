package ejercicio1;

public class Chekout {

    private PaymentStrategy paymentStrategy;

    public Chekout(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void procces(double amount){
        paymentStrategy.process(amount);
    }
}
