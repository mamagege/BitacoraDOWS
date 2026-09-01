package ejercicio1;

import java.util.Scanner;

public class main {

    public static void main(String[] args){

        /*
         * Una aplicación de e-commerce permite pagar con tarjeta, PSE, Nequi, PayPal y transferencia bancaria.
         * Cada medio tiene una lógica​ distinta pero el flujo de compra es el mismo.
         * Además, según el país del usuario, el sistema construye el proveedor de pago correcto (Colombia → PSE/Nequi, USA → PayPal/Stripe).​
         */


        Scanner sc = new Scanner(System.in);

        System.out.println("Digite su nacionalidad: ");
        String nacionalidad = sc.nextLine();

        System.out.println("Digite su método de pago: ");
        String metodo = sc.nextLine();

        System.out.println("Digite su precio: ");

        double precio = sc.nextDouble();

        if (nacionalidad.equalsIgnoreCase("Colombia")){
            PaymentFactory paymentFactory = new ColombiaPaymentFactory();
            Chekout chekout = new Chekout(paymentFactory.create(metodo));
            chekout.procces(precio);
        }






    }
}
