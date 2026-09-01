import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SISTEMA DE RESTAURANTE - DEMOSTRACIÓN DE BUILDER + OBSERVER
 * Enfoque: Inmutabilidad estricta, alta cohesión (SRP) y Open/Closed Principle.
 */

// --- ENUMS DE DOMINIO ---
enum Size {
    SMALL, MEDIUM, LARGE
}

enum Meat {
    BEEF, DOUBLE_BEEF, CHICKEN, VEGAN
}

// --- PATRÓN 2: OBSERVER (Desacoplamiento de subsistemas) ---[cite: 2]
interface OrderObserver {
    void onOrderConfirmed(Order order);
}

class KitchenService implements OrderObserver {
    @Override
    public void onOrderConfirmed(Order order) {
        System.out.println("[COCINA] Recibida comanda: " + order.getDetails());
    }
}

class BillingService implements OrderObserver {
    @Override
    public void onOrderConfirmed(Order order) {
        System.out.println("[FACTURACIÓN] Generando recibo para orden. Tamaño: " + order.getSize());
    }
}

class DeliveryService implements OrderObserver {
    @Override
    public void onOrderConfirmed(Order order) {
        System.out.println("[DOMICILIOS] Calculando ruta de entrega para la orden.");
    }
}

// --- CONTEXTO PRINCIPAL Y SUJETO ---
class Order {
    // Estado del negocio y dependencias (Observer)
    private boolean isConfirmed = false;
    private final List<OrderObserver> observers = new ArrayList<>();

    // Propiedades del pedido (Inmutables)
    private final Size size;
    private final Meat meat;
    private final List<String> toppings;
    private final List<String> sides;

    // XP: Constructor privado. Solo el Builder puede crear instancias.
    private Order(OrderBuilder builder) {
        this.size = builder.size;
        this.meat = builder.meat;
        // Copias defensivas para garantizar la inmutabilidad
        this.toppings = Collections.unmodifiableList(new ArrayList<>(builder.toppings));
        this.sides = Collections.unmodifiableList(new ArrayList<>(builder.sides));
    }

    public Size getSize() {
        return size;
    }

    public String getDetails() {
        return String.format("Hamburguesa %s con %s. Extras: %s. Acompañamientos: %s",
                size, meat, toppings, sides);
    }

    // Gestión del Patrón Observer
    public void addObserver(OrderObserver observer) {
        this.observers.add(observer);
    }

    // Acción de dominio que dispara la notificación[cite: 2]
    public void confirm() {
        if (isConfirmed) {
            throw new IllegalStateException("El pedido ya fue confirmado.");
        }
        this.isConfirmed = true;
        System.out.println("\n>>> EVENTO: Sistema confirma la orden <<<");
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.onOrderConfirmed(this);
        }
    }

    // --- PATRÓN 1: BUILDER (Construcción controlada) ---[cite: 2]
    public static class OrderBuilder {
        private Size size;
        private Meat meat;
        private final List<String> toppings = new ArrayList<>();
        private final List<String> sides = new ArrayList<>();

        public OrderBuilder setSize(Size size) {
            this.size = size;
            return this; // Permite interfaz fluida (method chaining)
        }

        public OrderBuilder setMeat(Meat meat) {
            this.meat = meat;
            return this;
        }

        public OrderBuilder addTopping(String... toppings) {
            Collections.addAll(this.toppings, toppings);
            return this;
        }

        public OrderBuilder addSide(String... sides) {
            Collections.addAll(this.sides, sides);
            return this;
        }

        public Order build() {
            // XP: Validación de invariantes antes de nacer. Fallar rápido (Fail-Fast).
            if (this.size == null || this.meat == null) {
                throw new IllegalStateException("Un pedido debe tener al menos tamaño y tipo de carne.");
            }
            return new Order(this);
        }
    }
}

// --- DEMOSTRACIÓN FUNCIONAL ---
public class RestaurantOrderSystem {
    public static void main(String[] args) {
        System.out.println("--- FASE 1: CLIENTE ENSAMBLANDO EL PEDIDO ---");
        // El cliente usa el Builder para configurar su orden de manera fluida y
        // declarativa[cite: 2].
        Order myOrder = new Order.OrderBuilder()
                .setSize(Size.LARGE)
                .setMeat(Meat.DOUBLE_BEEF)
                .addTopping("queso", "lechuga", "tocineta")
                .addSide("papas", "gaseosa")
                .build(); // Retorna un Order inmutable y válido.

        System.out.println("Pedido ensamblado: " + myOrder.getDetails());

        System.out.println("\n--- FASE 2: SISTEMA REGISTRANDO SERVICIOS ---");
        // El sistema adjunta dinámicamente los interesados a la orden[cite: 2].
        myOrder.addObserver(new KitchenService());
        myOrder.addObserver(new BillingService());
        myOrder.addObserver(new DeliveryService());

        // El pedido confirma su estado y notifica a la infraestructura sin
        // conocerla[cite: 2].
        myOrder.confirm();
    }
}