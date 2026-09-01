# Patrón de Diseño: Builder

## ¿Qué es?

Es un patrón de diseño creacional que permite construir objetos complejos paso a paso, desacoplando el proceso de creación de la representación final del objeto.

## ¿Qué hace?

Permite instanciar objetos complejos utilizando llamadas encadenadas (method chaining), asegurando que el producto final nazca completo, válido y 100% inmutable.

## ¿Cómo se implementa?

- **Clase Producto**: Define campos inmutables (`final`) y un constructor privado que recibe el Builder.
- **Clase Builder (Estática Interna)**: Posee los mismos atributos del producto y métodos fluidos que retornan `this`.
- **Método `build()`**: Valida las reglas de negocio e invariantes antes de retornar la instancia final del objeto.

## ¿Cómo identificarlo (Cuándo usarlo y Smells que resuelve)?

**Code Smells**: El "Constructor Telescópico" (clases con múltiples constructores sobrecargados con gran cantidad de parámetros booleanos o nulos) y objetos mutables que cambian de estado tras su creación.

**Uso ideal**: Cuando un objeto requiere configuraciones extensas con múltiples parámetros opcionales. En metodologías XP y TDD, garantiza que los objetos de prueba se construyan de manera limpia, expresiva y sin estados inválidos.

## Ejemplos de Implementación en Java

### Ejemplo 1: Configuración de Hardware de una Computadora (Inmutabilidad)

```java
class Computer {
    private final String cpu;
    private final int ramGB;
    private final boolean hasGpu;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ramGB = builder.ramGB;
        this.hasGpu = builder.hasGpu;
    }

    public static class Builder {
        private String cpu = "Intel i5"; // Valores por defecto
        private int ramGB = 8;
        private boolean hasGpu = false;

        public Builder setCpu(String cpu) { this.cpu = cpu; return this; }
        public Builder setRamGB(int ramGB) { this.ramGB = ramGB; return this; }
        public Builder setHasGpu(boolean hasGpu) { this.hasGpu = hasGpu; return this; }

        public Computer build() {
            return new Computer(this);
        }
    }
}

// Uso
public class Main1 {
    public static void main(String[] args) {
        Computer pc = new Computer.Builder()
                .setCpu("AMD Ryzen 7")
                .setRamGB(32)
                .setHasGpu(true)
                .build();
    }
}
```

### Ejemplo 2: Generador de Consultas SQL (Query Builder Dinámico)

```java
class SqlQuery {
    private final String table;
    private final String conditions;
    private final int limit;

    private SqlQuery(Builder builder) {
        this.table = builder.table;
        this.conditions = builder.conditions;
        this.limit = builder.limit;
    }

    public String getSql() {
        return "SELECT * FROM " + table + 
               (conditions != null ? " WHERE " + conditions : "") + 
               (limit > 0 ? " LIMIT " + limit : "");
    }

    public static class Builder {
        private final String table; // Obligatorio
        private String conditions = null;
        private int limit = 0;

        public Builder(String table) { this.table = table; }

        public Builder where(String conditions) { this.conditions = conditions; return this; }
        public Builder limit(int limit) { this.limit = limit; return this; }

        public SqlQuery build() {
            if (table == null || table.isEmpty()) throw new IllegalStateException("Tabla requerida");
            return new SqlQuery(this);
        }
    }
}

// Uso
public class Main2 {
    public static void main(String[] args) {
        SqlQuery query = new SqlQuery.Builder("users")
                .where("active = 1")
                .limit(10)
                .build();
        System.out.println(query.getSql());
    }
}
```

### Ejemplo 3: Creación de Personaje de Videojuego con Validación de Invariantes

```java
class GameCharacter {
    private final String name;
    private final String weapon;
    private final int health;

    private GameCharacter(Builder builder) {
        this.name = builder.name;
        this.weapon = builder.weapon;
        this.health = builder.health;
    }

    public static class Builder {
        private final String name;
        private String weapon = "Espada Corta";
        private int health = 100;

        public Builder(String name) { this.name = name; }

        public Builder setWeapon(String weapon) { this.weapon = weapon; return this; }
        public Builder setHealth(int health) { this.health = health; return this; }

        public GameCharacter build() {
            // Validación de invariantes (Fail-Fast)
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("El personaje debe tener un nombre válido.");
            }
            return new GameCharacter(this);
        }
    }
}

// Uso
public class Main3 {
    public static void main(String[] args) {
        GameCharacter hero = new GameCharacter.Builder("Aragorn")
                .setWeapon("Andúril")
                .setHealth(150)
                .build();
    }
}
```
