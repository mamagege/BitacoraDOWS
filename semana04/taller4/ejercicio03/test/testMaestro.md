# Patrones de Diseño

## 1. Iterator

**¿Qué hace?**
Permite recorrer los elementos de una colección (como listas, árboles o tablas hash) secuencialmente sin exponer su representación interna (es decir, sin importar si por debajo usa un Array, un Vector o una Linked List).

**Cómo se implementa en código**
Se compone de una interfaz iteradora (con métodos como `hasNext()` y `next()`) y una colección iterable que retorna una instancia concreta de ese iterador.

**Justificación y Clean Code**
- Respeta el Principio de Responsabilidad Única (SRP) al separar la lógica de recorrido de la colección misma.
- Facilita el polimorfismo, permitiendo tratar distintas estructuras de datos de manera uniforme.

**Ejemplo de Implementación en Java**

```java
import java.util.Iterator;

// Colección personalizada
class BookCollection implements Iterable<String> {
    private String[] books = {"Clean Code", "Design Patterns", "Refactoring"};

    @Override
    public Iterator<String> iterator() {
        return new BookIterator();
    }

    // Iterador Concreto interno
    private class BookIterator implements Iterator<String> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < books.length;
        }

        @Override
        public String next() {
            return books[index++];
        }
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        BookCollection collection = new BookCollection();
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println("Libro: " + iterator.next());
        }
    }
}
```

---

## 2. Composite

**¿Qué hace?**
Permite tratar a los objetos individuales (hojas) y a las composiciones de objetos (contenedores o ramas) de manera uniforme mediante una estructura de árbol jerárquica (parte-todo).

**Cómo se implementa en código**
Se crea una interfaz o clase abstracta común tanto para los elementos simples como para los compuestos. El contenedor contiene una lista de componentes y delega las operaciones recursivamente en sus hijos.

**Justificación y Clean Code**
- Evita que el código cliente tenga que evaluar condicionales de tipo (`if instanceof`) para saber si está operando sobre un elemento simple o sobre un grupo.
- Alineado con el Open/Closed Principle (OCP).

**Ejemplo de Implementación en Java**

```java
import java.util.ArrayList;
import java.util.List;

// Componente común
interface FileSystemItem {
    void showDetails();
}

// Hoja (Objeto individual)
class FileItem implements FileSystemItem {
    private String name;
    public FileItem(String name) { this.name = name; }
    @Override public void showDetails() { System.out.println("Archivo: " + name); }
}

// Composite (Contenedor)
class DirectoryItem implements FileSystemItem {
    private String name;
    private List<FileSystemItem> children = new ArrayList<>();

    public DirectoryItem(String name) { this.name = name; }
    public void addItem(FileSystemItem item) { children.add(item); }

    @Override
    public void showDetails() {
        System.out.println("Directorio: " + name);
        for (FileSystemItem child : children) {
            child.showDetails();
        }
    }
}
```

---

## 3. Builder

**¿Qué hace?**
Separa la construcción de un objeto complejo de su representación final, permitiendo crearlo paso a paso mediante llamadas encadenadas (method chaining).

**Cómo se implementa en código**
Se utiliza una clase estática interna (Builder) dentro del producto final, la cual expone métodos para configurar cada atributo y un método final `build()` que valida invariantes y retorna el objeto inmutable.

**Justificación y Clean Code**
- Elimina el anti-patrón del "Constructor Telescópico" (constructores saturados de parámetros nulos y confusos).
- Garantiza que los objetos nazcan en un estado válido y thread-safe (inmutabilidad).

**Ejemplo de Implementación en Java**

```java
class Car {
    private final String engine;
    private final int seats;

    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.seats = builder.seats;
    }

    public static class CarBuilder {
        private String engine = "V6";
        private int seats = 4;

        public CarBuilder setEngine(String engine) { this.engine = engine; return this; }
        public CarBuilder setSeats(int seats) { this.seats = seats; return this; }

        public Car build() { return new Car(this); }
    }
}
```

---

## 4. Decorator

**¿Qué hace?**
Añade responsabilidades o comportamientos adicionales a un objeto de forma dinámica en tiempo de ejecución, envolviéndolo en clases contenedoras compatibles.

**Cómo se implementa en código**
Una clase abstracta decoradora implementa la misma interfaz del objeto base y mantiene una referencia interna a este, delegando las llamadas y añadiendo lógica antes o después.

**Justificación y Clean Code**
- Previene la explosión combinatoria de subclases (evita crear 2^N clases por cada combinación de características).
- Aplica estrictamente el Open/Closed Principle (OCP).

**Ejemplo de Implementación en Java**

```java
interface Notifier { void send(String msg); }

class BasicNotifier implements Notifier {
    @Override public void send(String msg) { System.out.println("Enviando email: " + msg); }
}

abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrapped;
    public NotifierDecorator(Notifier wrapped) { this.wrapped = wrapped; }
    @Override public void send(String msg) { wrapped.send(msg); }
}

class SlackDecorator extends NotifierDecorator {
    public SlackDecorator(Notifier n) { super(n); }
    @Override public void send(String msg) {
        super.send(msg);
        System.out.println("Enviando también por Slack: " + msg);
    }
}
```

---

## 5. Chain of Responsibility

**¿Qué hace?**
Permite pasar solicitudes a lo largo de una cadena de manejadores (handlers). Cada manejador decide si procesa la solicitud o la pasa al siguiente eslabón.

**Cómo se implementa en código**
Se define una clase abstracta con una referencia al siguiente manejador de la cadena y un método template de control. Las subclases implementan la condición y la lógica de procesamiento específica.

**Justificación y Clean Code**
- Desacopla al emisor de la solicitud de sus múltiples receptores posibles.
- Evita bloques masivos de condicionales anidados (`if-else` / `switch`) y favorece la flexibilidad para reordenar o añadir validadores dinámicamente.

**Ejemplo de Implementación en Java**

```java
abstract class Approver {
    private Approver next;
    public Approver setNext(Approver next) { this.next = next; return next; }

    public void processRequest(int amount) {
        if (canHandle(amount)) {
            execute(amount);
        } else if (next != null) {
            next.processRequest(amount);
        } else {
            System.out.println("Solicitud rechazada: Monto muy alto.");
        }
    }
    protected abstract boolean canHandle(int amount);
    protected abstract void execute(int amount);
}

class Manager extends Approver {
    protected boolean canHandle(int amount) { return amount <= 1000; }
    protected void execute(int amount) { System.out.println("Manager aprobó: " + amount); }
}
```

---

## 6. Adapter

**¿Qué hace?**
Permite que dos interfaces incompatibles trabajen juntas. Actúa como un traductor o "Capa Anticorrupción" entre el código moderno de la aplicación y un sistema legacy o externo.

**Cómo se implementa en código**
El adaptador implementa la interfaz moderna esperada por el cliente y encapsula una instancia del servicio antiguo, traduciendo internamente los tipos de datos y nombres de métodos.

**Justificación y Clean Code**
- Protege el dominio de modificaciones externas y evita contaminar el código limpio con firmas de métodos heredadas, extrañas o incompatibles.

**Ejemplo de Implementación en Java**

```java
// Interfaz moderna que el sistema espera
interface ModernPayment {
    void pay(double dollars);
}

// Servicio Legacy incompatible
class LegacyBank {
    void makeTransfer(int cents) { System.out.println("Pagado en centavos: " + cents); }
}

// Adaptador
class BankAdapter implements ModernPayment {
    private final LegacyBank legacyBank;
    public BankAdapter(LegacyBank legacyBank) { this.legacyBank = legacyBank; }

    @Override
    public void pay(double dollars) {
        int cents = (int) (dollars * 100); // Traducción de datos
        legacyBank.makeTransfer(cents);
    }
}
```
