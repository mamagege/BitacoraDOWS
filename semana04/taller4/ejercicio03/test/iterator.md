# Patrón de Diseño: Iterator

## ¿Qué es?

Es un patrón de diseño de comportamiento que desacopla la lógica de recorrido de una estructura de datos de la representación interna de dicha estructura.

## ¿Qué hace?

Permite recorrer los elementos de una colección (como arreglos, listas, árboles o grafos) de forma secuencial y uniforme mediante una interfaz estándar (`hasNext()`, `next()`), sin exponer los detalles internos de cómo se almacenan los datos.

## ¿Cómo se implementa?

- **Interfaz Iterator**: Define los métodos de navegación (`boolean hasNext()` y `T next()`).
- **Interfaz Iterable (Colección)**: Define un método contrato para obtener el iterador (`Iterator<T> iterator()`).
- **Clases Concretas**: La colección implementa el iterable y retorna una clase interna (o privada) que actúa como el iterador concreto, manteniendo el estado del índice de recorrido.

## ¿Cómo identificarlo (Cuándo usarlo y Smells que resuelve)?

**Code Smells**: Clases de negocio que iteran colecciones usando bucles basados en índices expuestos (`for(int i=0; i<list.size(); i++)`) o que dependen fuertemente de si una estructura es un `ArrayList`, `HashSet` o un arreglo plano.

**Uso ideal**: Cuando quieres ofrecer múltiples formas de recorrer una misma colección (por ejemplo, orden ascendente, descendente o filtrado) sin modificar su código fuente, cumpliendo estrictamente con el Single Responsibility Principle (SRP) y el Open/Closed Principle (OCP). En metodologías XP, facilita la refactorización segura y el aislamiento de responsabilidades.

## Ejemplos de Implementación en Java

### Ejemplo 1: Iterador básico sobre una colección basada en arreglos

```java
import java.util.Iterator;

// Colección personalizada
class ProductCatalog implements Iterable<String> {
    private final String[] products = {"Laptop", "Mouse", "Teclado", "Monitor"};

    @Override
    public Iterator<String> iterator() {
        return new CatalogIterator();
    }

    // Iterador Concreto interno (Encapsula el puntero)
    private class CatalogIterator implements Iterator<String> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < products.length;
        }

        @Override
        public String next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return products[currentIndex++];
        }
    }
}

// Uso
public class Main1 {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        Iterator<String> it = catalog.iterator();
        while (it.hasNext()) {
            System.out.println("Producto: " + it.next());
        }
    }
}
```

### Ejemplo 2: Iterador Inverso (Demostrando OCP y flexibilidad)

```java
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

class TaskList {
    private final List<String> tasks = new ArrayList<>();

    public void addTask(String task) { tasks.add(task); }

    // Fábrica de iterador inverso sin alterar la lista base
    public Iterator<String> reverseIterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<String> {
        private int currentIndex = tasks.size() - 1;

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public String next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return tasks.get(currentIndex--);
        }
    }
}

// Uso
public class Main2 {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        list.addTask("Analizar Requerimientos");
        list.addTask("Escribir Código");
        list.addTask("Ejecutar Pruebas TDD");

        Iterator<String> revIt = list.reverseIterator();
        while (revIt.hasNext()) {
            System.out.println("Paso (Inverso): " + revIt.next());
        }
    }
}
```

### Ejemplo 3: Iterador con Filtro Condicional en tiempo de ejecución

```java
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

class FilteredCollection<T> {
    private final List<T> items;

    public FilteredCollection(T... elements) {
        this.items = Arrays.asList(elements);
    }

    // Retorna un iterador que solo devuelve elementos que cumplan una condición
    public Iterator<T> conditionalIterator(Predicate<T> condition) {
        return new ConditionalIterator(condition);
    }

    private class ConditionalIterator implements Iterator<T> {
        private final Predicate<T> predicate;
        private int index = 0;
        private T nextElement = null;
        private boolean hasNextCalculated = false;

        public ConditionalIterator(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            if (hasNextCalculated) return nextElement != null;
            
            while (index < items.size()) {
                T current = items.get(index++);
                if (predicate.test(current)) {
                    nextElement = current;
                    hasNextCalculated = true;
                    return true;
                }
            }
            nextElement = null;
            hasNextCalculated = true;
            return false;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            hasNextCalculated = false;
            return nextElement;
        }
    }
}

// Uso
public class Main3 {
    public static void main(String[] args) {
        FilteredCollection<Integer> numbers = new FilteredCollection<>(1, 2, 3, 4, 5, 6);
        
        // Iterar solo números pares
        Iterator<Integer> evenIt = numbers.conditionalIterator(n -> n % 2 == 0);
        while (evenIt.hasNext()) {
            System.out.println("Número Par: " + evenIt.next());
        }
    }
}
```
