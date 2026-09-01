# Patrón de Diseño: Decorator

## ¿Qué es?

Es un patrón de diseño estructural que permite añadir funcionalidades dinámicas a un objeto envolviéndolo en clases contenedoras especializadas (wrappers), sin alterar su estructura ni su clase base.

## ¿Qué hace?

Permite extender el comportamiento de un objeto en tiempo de ejecución de forma flexible. En lugar de crear una jerarquía masiva de subclases para cada combinación posible de características, el decorador envuelve al objeto base delegando las llamadas y añadiendo lógica antes o después.

## ¿Cómo se implementa?

- **Component (Interfaz Base)**: Define el contrato común tanto para el objeto base como para los decoradores.
- **Concrete Component (Objeto Base)**: La clase principal que implementa la interfaz y realiza el trabajo fundamental.
- **Decorator (Clase Abstracta Decoradora)**: Implementa la misma interfaz y contiene un campo de tipo `Component` para mantener la referencia al objeto envuelto.
- **Concrete Decorators (Decoradores Concretos)**: Heredan del decorador abstracto y sobreescriben los métodos para añadir comportamiento adicional antes o después de invocar al objeto envuelto.

## ¿Cómo identificarlo (Cuándo usarlo y Smells que resuelve)?

**Code Smells**: La explosión combinatoria de subclases (tener que crear 2^N clases para combinar N características opcionales) y el uso incorrecto de herencia profunda para añadir responsabilidades menores.

**Uso ideal**: Cuando necesitas añadir o remover responsabilidades de objetos de forma dinámica y transparente sin afectar a otras instancias, cumpliendo estrictamente con el Open/Closed Principle (OCP) y el Single Responsibility Principle (SRP).

## Ejemplos de Implementación en Java

### Ejemplo 1: Potenciadores de Personajes de Videojuegos (Buffs Temporales)

Este es el escenario clásico del taller de patrones combinados (Builder + Decorator) donde un personaje base recibe modificaciones de estadísticas en tiempo de ejecución.

```java
// 1. Component
interface GameCharacter {
    String getDescription();
    int getDefense();
}

// 2. Concrete Component
class BasicWarrior implements GameCharacter {
    @Override
    public String getDescription() { return "Guerrero Base"; }
    @Override
    public int getDefense() { return 10; }
}

// 3. Decorator Abstracto
abstract class CharacterDecorator implements GameCharacter {
    protected final GameCharacter wrapped;
    public CharacterDecorator(GameCharacter wrapped) { this.wrapped = wrapped; }
    @Override
    public String getDescription() { return wrapped.getDescription(); }
    @Override
    public int getDefense() { return wrapped.getDefense(); }
}

// 4. Concrete Decorator: Escudo de Hielo
class IceShieldDecorator extends CharacterDecorator {
    public IceShieldDecorator(GameCharacter wrapped) { super(wrapped); }
    @Override
    public String getDescription() { return super.getDescription() + " + [Escudo de Hielo]"; }
    @Override
    public int getDefense() { return super.getDefense() + 25; }
}

// Uso
public class DecoratorDemo1 {
    public static void main(String[] args) {
        GameCharacter warrior = new BasicWarrior();
        GameCharacter buffedWarrior = new IceShieldDecorator(warrior);
        
        System.out.println(buffedWarrior.getDescription());
        System.out.println("Defensa Total: " + buffedWarrior.getDefense());
    }
}
```

### Ejemplo 2: Personalización de Café (Cálculo de Precios y Descripciones)

Ideal para comercio electrónico donde un producto base (café) acumula ingredientes adicionales (leche, caramelo, chocolate) modificando su costo y descripción de manera dinámica.

```java
// 1. Component
interface Coffee {
    String getDescription();
    double cost();
}

// 2. Concrete Component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() { return "Café Solo"; }
    @Override
    public double cost() { return 2.00; }
}

// 3. Abstract Decorator
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) { this.decoratedCoffee = coffee; }
    @Override
    public String getDescription() { return decoratedCoffee.getDescription(); }
    @Override
    public double cost() { return decoratedCoffee.cost(); }
}

// 4. Concrete Decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    @Override
    public String getDescription() { return super.getDescription() + ", Leche"; }
    @Override
    public double cost() { return super.cost() + 0.50; }
}

class ChocolateDecorator extends CoffeeDecorator {
    public ChocolateDecorator(Coffee coffee) { super(coffee); }
    @Override
    public String getDescription() { return super.getDescription() + ", Chocolate"; }
    @Override
    public double cost() { return super.cost() + 0.75; }
}

// Uso
public class DecoratorDemo2 {
    public static void main(String[] args) {
        Coffee myCoffee = new SimpleCoffee();
        myCoffee = new MilkDecorator(myCoffee);
        myCoffee = new ChocolateDecorator(myCoffee);

        System.out.println("Pedido: " + myCoffee.getDescription());
        System.out.println("Costo Total: $" + myCoffee.cost());
    }
}
```

### Ejemplo 3: Capas de Encriptación y Compresión en un Sistema de Archivos

Permite envolver un componente de escritura de datos para aplicar compresión o cifrado de manera acumulativa y transparente al cliente.

```java
// 1. Component
interface DataSource {
    void writeData(String data);
    String readData();
}

// 2. Concrete Component
class FileDataSource implements DataSource {
    private String memoryData;
    @Override
    public void writeData(String data) { this.memoryData = data; }
    @Override
    public String readData() { return memoryData; }
}

// 3. Abstract Decorator
abstract class DataSourceDecorator implements DataSource {
    protected final DataSource wrappee;
    public DataSourceDecorator(DataSource source) { this.wrappee = source; }
    @Override
    public void writeData(String data) { wrappee.writeData(data); }
    @Override
    public String readData() { return wrappee.readData(); }
}

// 4. Concrete Decorator: Encriptación Simulada
class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource source) { super(source); }
    @Override
    public void writeData(String data) {
        String encrypted = "ENC(" + data + ")";
        super.writeData(encrypted);
    }
    @Override
    public String readData() {
        String data = super.readData();
        return data.replace("ENC(", "").replace(")", ""); // Desencripta
    }
}

// Uso
public class DecoratorDemo3 {
    public static void main(String[] args) {
        DataSource source = new FileDataSource();
        DataSource encryptedSource = new EncryptionDecorator(source);

        encryptedSource.writeData("Password123");
        System.out.println("Datos en crudo simulados en archivo: " + source.readData());
        System.out.println("Datos leídos a través del Decorator: " + encryptedSource.readData());
    }
}
```
