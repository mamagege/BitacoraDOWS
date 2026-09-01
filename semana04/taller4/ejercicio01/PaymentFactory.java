package ejercicio01;

/**
 * Patrón 2: FACTORY METHOD (Creacional)
 * Rol: Define la interfaz para crear objetos PaymentStrategy, delegando a las fábricas
 * concretas regionales la decisión de qué estrategia instanciar según el medio solicitado.
 */
public interface PaymentFactory {
    PaymentStrategy create(String type);
}
