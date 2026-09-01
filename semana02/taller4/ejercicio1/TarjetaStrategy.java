package ejercicio1;

class TarjetaStrategy implements PaymentStrategy{

    @Override
    public void process(double amount){
        System.out.println("Pagaste con tarjeta" + amount);
    }

}