# 💻 04: Ejemplos Prácticos y Ejercicios Resueltos de Java Streams

> **Módulo:** Programación Funcional en Java  
> **Tema:** Implementaciones en código de extremo a extremo, casos de uso de producción y batería de ejercicios resueltos con modelos inmutables (`Records`).

---

## 🧱 1. Modelos de Dominio de Referencia (Java Records)

Utilizamos **Java Records** (Java 16+) para garantizar inmutabilidad estricta (*Immutable State*), `equals()`, `hashCode()` y `toString()` automáticos sin código repetitivo.

```java
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// 1. Categoría de Productos
public enum Categoria {
    TECNOLOGIA, HOGAR, MODA, ALIMENTOS, LIBROS
}

// 2. Estado de la Transacción
public enum EstadoTransaccion {
    COMPLETADA, PENDIENTE, RECHAZADA, REEMBOLSADA
}

// 3. Producto Inmutable
public record Producto(
    String id,
    String nombre,
    Categoria categoria,
    BigDecimal precio,
    int stock
) {}

// 4. Ítem de una Orden
public record ItemPedido(
    Producto producto,
    int cantidad
) {
    public BigDecimal getSubtotal() {
        return producto.precio().multiply(BigDecimal.valueOf(cantidad));
    }
}

// 5. Orden o Pedido de un Cliente
public record Pedido(
    String idPedido,
    String clienteId,
    LocalDate fecha,
    List<ItemPedido> items,
    boolean pagado
) {
    public BigDecimal getTotalPedido() {
        return items.stream()
            .map(ItemPedido::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

// 6. Cliente del Sistema
public record Cliente(
    String id,
    String nombre,
    String email,
    String ciudad,
    int edad,
    boolean vip
) {}

// 7. Transacción Financiera
public record Transaccion(
    String id,
    String cuentaOrigen,
    String cuentaDestino,
    BigDecimal monto,
    EstadoTransaccion estado,
    LocalDate fecha
) {}
```

---

## 🚀 2. Ejemplos Prácticos por Patrón y Caso de Uso

---

### Caso 1: Filtrado, Transformación, Ordenamiento y Paginación
**Objetivo:** Obtener los nombres en mayúsculas de los productos de `TECNOLOGIA` con precio mayor a $100, ordenados de forma descendente por precio, paginando la segunda página (2 elementos por página).

```java
import java.math.BigDecimal;
import java.util.List;
import java.util.Comparator;

public class EjemploPaginacionYFiltro {
    public static void main(String[] args) {
        List<Producto> catalogo = List.of(
            new Producto("P1", "Laptop Pro", Categoria.TECNOLOGIA, new BigDecimal("1200.00"), 15),
            new Producto("P2", "Mouse Inalámbrico", Categoria.TECNOLOGIA, new BigDecimal("25.00"), 50),
            new Producto("P3", "Monitor 4K", Categoria.TECNOLOGIA, new BigDecimal("450.00"), 8),
            new Producto("P4", "Teclado Mecánico", Categoria.TECNOLOGIA, new BigDecimal("110.00"), 20),
            new Producto("P5", "Silla Ergonómica", Categoria.HOGAR, new BigDecimal("250.00"), 5),
            new Producto("P6", "Smartphone Flagship", Categoria.TECNOLOGIA, new BigDecimal("950.00"), 12)
        );

        int pagina = 2;
        int tamanioPagina = 2;

        List<String> paginaTecnologia = catalogo.stream()
            // 1. Filtrar solo tecnología con precio > 100
            .filter(p -> p.categoria() == Categoria.TECNOLOGIA)
            .filter(p -> p.precio().compareTo(new BigDecimal("100.00")) > 0)
            // 2. Ordenar descendentemente por precio
            .sorted(Comparator.comparing(Producto::precio).reversed())
            // 3. Paginar: saltar elementos de la página anterior y limitar
            .skip((long) (pagina - 1) * tamanioPagina)
            .limit(tamanioPagina)
            // 4. Mapear a nombre en mayúsculas
            .map(p -> p.nombre().toUpperCase())
            // 5. Recolectar a lista inmutable
            .toList();

        // Salida esperada en página 2: ["MONITOR 4K", "TECLADO MECÁNICO"]
        paginaTecnologia.forEach(System.out::println);
    }
}
```

---

### Caso 2: Aplanamiento de Jerarquías con `flatMap` y Desduplicación con `distinct`
**Objetivo:** Extraer todas las categorías únicas de los productos que han sido comprados en un conjunto de órdenes finalizadas.

```java
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EjemploFlatMapJerarquias {
    public static void main(String[] args) {
        Producto laptop = new Producto("P1", "Laptop", Categoria.TECNOLOGIA, new BigDecimal("1000"), 10);
        Producto cafe = new Producto("P2", "Café Arábica", Categoria.ALIMENTOS, new BigDecimal("15"), 100);
        Producto libro = new Producto("P3", "Clean Code", Categoria.LIBROS, new BigDecimal("40"), 30);

        List<Pedido> pedidos = List.of(
            new Pedido("PED-1", "CLI-1", LocalDate.now(), List.of(new ItemPedido(laptop, 1), new ItemPedido(cafe, 2)), true),
            new Pedido("PED-2", "CLI-2", LocalDate.now(), List.of(new ItemPedido(cafe, 1), new ItemPedido(libro, 3)), true),
            new Pedido("PED-3", "CLI-3", LocalDate.now(), List.of(new ItemPedido(laptop, 2)), false) // No pagado
        );

        // Aplanar: Pedido -> List<ItemPedido> -> Producto -> Categoria
        Set<Categoria> categoriasCompradas = pedidos.stream()
            .filter(Pedido::pagado) // Solo pedidos pagados
            .flatMap(pedido -> pedido.items().stream()) // Aplana a Stream<ItemPedido>
            .map(item -> item.producto().categoria())   // Extrae Categoria
            .collect(Collectors.toSet());               // Set elimina duplicados automáticamente

        // Salida: [TECNOLOGIA, ALIMENTOS, LIBROS]
        System.out.println("Categorías compradas: " + categoriasCompradas);
    }
}
```

---

### Caso 3: Agrupación Multinivel (`groupingBy`) y Estadísticas Numéricas
**Objetivo:** Agrupar clientes por ciudad y, para cada ciudad, calcular el promedio de edad y la cantidad de clientes VIP.

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EjemploGroupingByAvanzado {
    public static void main(String[] args) {
        List<Cliente> clientes = List.of(
            new Cliente("C1", "Carlos", "carlos@mail.com", "Bogotá", 28, true),
            new Cliente("C2", "Ana", "ana@mail.com", "Medellín", 34, true),
            new Cliente("C3", "Pedro", "pedro@mail.com", "Bogotá", 22, false),
            new Cliente("C4", "Sofía", "sofia@mail.com", "Bogotá", 30, true),
            new Cliente("C5", "Mateo", "mateo@mail.com", "Medellín", 45, false),
            new Cliente("C6", "Laura", "laura@mail.com", "Cali", 29, true)
        );

        // 1. Agrupar por ciudad y calcular el promedio de edad
        Map<String, Double> promedioEdadPorCiudad = clientes.stream()
            .collect(Collectors.groupingBy(
                Cliente::ciudad,
                Collectors.averagingInt(Cliente::edad)
            ));

        System.out.println("Promedio edad por ciudad: " + promedioEdadPorCiudad);

        // 2. Agrupar por ciudad y contar clientes VIP
        Map<String, Long> conteoVipPorCiudad = clientes.stream()
            .filter(Cliente::vip)
            .collect(Collectors.groupingBy(
                Cliente::ciudad,
                Collectors.counting()
            ));

        System.out.println("Total VIPs por ciudad: " + conteoVipPorCiudad);
    }
}
```

---

### Caso 4: Reducción Financiera con `reduce` y Validación Cortocircuitada
**Objetivo:** Auditar una lista de transacciones bancarias: verificar si existe fraude (`anyMatch`), verificar que todas superen el monto mínimo (`allMatch`), y sumar el saldo total de transferencias completadas.

```java
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EjemploAuditoriaFinanciera {
    public static void main(String[] args) {
        List<Transaccion> transacciones = List.of(
            new Transaccion("TX-1", "CTA-01", "CTA-02", new BigDecimal("500.00"), EstadoTransaccion.COMPLETADA, LocalDate.now()),
            new Transaccion("TX-2", "CTA-03", "CTA-04", new BigDecimal("15000.00"), EstadoTransaccion.PENDIENTE, LocalDate.now()),
            new Transaccion("TX-3", "CTA-01", "CTA-05", new BigDecimal("250.50"), EstadoTransaccion.COMPLETADA, LocalDate.now()),
            new Transaccion("TX-4", "CTA-06", "CTA-02", new BigDecimal("99000.00"), EstadoTransaccion.RECHAZADA, LocalDate.now())
        );

        // 1. ¿Existe alguna transacción sospechosa de más de $50,000? (Cortocircuito)
        boolean alertaFraude = transacciones.stream()
            .anyMatch(tx -> tx.monto().compareTo(new BigDecimal("50000.00")) > 0);

        System.out.println("¿Alerta de fraude activada?: " + alertaFraude); // true

        // 2. ¿Todas las transacciones tienen montos estrictamente positivos?
        boolean integridadValida = transacciones.stream()
            .allMatch(tx -> tx.monto().compareTo(BigDecimal.ZERO) > 0);

        System.out.println("¿Integridad correcta?: " + integridadValida); // true

        // 3. Sumar el total de transacciones COMPLETADAS con reduce
        BigDecimal totalCompletado = transacciones.stream()
            .filter(tx -> tx.estado() == EstadoTransaccion.COMPLETADA)
            .map(Transaccion::monto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total transacciones completadas: $" + totalCompletado); // $750.50
    }
}
```

---

### Caso 5: Concatenación y Formateo con `joining`
**Objetivo:** Generar un encabezado formateado CSV o lista legible de correos para una campaña de marketing.

```java
import java.util.List;
import java.util.stream.Collectors;

public class EjemploJoiningEmails {
    public static void main(String[] args) {
        List<Cliente> destinatarios = List.of(
            new Cliente("C1", "Carlos", "carlos@empresa.com", "Bogotá", 28, true),
            new Cliente("C2", "Ana", "ana@empresa.com", "Medellín", 34, true),
            new Cliente("C3", "Sofía", "sofia@empresa.com", "Bogotá", 30, true)
        );

        String listaDistribucion = destinatarios.stream()
            .map(Cliente::email)
            .collect(Collectors.joining("; ", "Para: [", "]"));

        // Salida: Para: [carlos@empresa.com; ana@empresa.com; sofia@empresa.com]
        System.out.println(listaDistribucion);
    }
}
```

---

## 🎯 3. Batería de Ejercicios y Retos con Solución

---

### 📝 Reto 1: Identificar al Cliente con Mayor Volumen de Compras
* **Problema:** Dada una lista de pedidos completados (`pagado == true`), identifica qué cliente ha generado la mayor cantidad de dinero total acumulado. Retorna un `Optional<Map.Entry<String, BigDecimal>>`.

```java
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Reto1TopCliente {
    public static Optional<Map.Entry<String, BigDecimal>> obtenerClienteTop(List<Pedido> pedidos) {
        return pedidos.stream()
            .filter(Pedido::pagado)
            // Agrupar por ID de cliente y sumar el total de sus pedidos
            .collect(Collectors.groupingBy(
                Pedido::clienteId,
                Collectors.reducing(
                    BigDecimal.ZERO,
                    Pedido::getTotalPedido,
                    BigDecimal::add
                )
            ))
            .entrySet()
            .stream()
            // Encontrar el valor máximo según el BigDecimal acumulado
            .max(Map.Entry.comparingByValue());
    }

    public static void main(String[] args) {
        Producto p1 = new Producto("P1", "Monitor", Categoria.TECNOLOGIA, new BigDecimal("300"), 10);
        Producto p2 = new Producto("P2", "Mouse", Categoria.TECNOLOGIA, new BigDecimal("50"), 10);

        List<Pedido> pedidos = List.of(
            new Pedido("PED-1", "CLI-A", LocalDate.now(), List.of(new ItemPedido(p1, 2)), true), // $600
            new Pedido("PED-2", "CLI-B", LocalDate.now(), List.of(new ItemPedido(p2, 1)), true), // $50
            new Pedido("PED-3", "CLI-A", LocalDate.now(), List.of(new ItemPedido(p2, 3)), true)  // $150 -> Total CLI-A = $750
        );

        obtenerClienteTop(pedidos).ifPresent(entry -> 
            System.out.println("Cliente Top: " + entry.getKey() + " con compras por: $" + entry.getValue())
        );
    }
}
```

---

### 📝 Reto 2: Top 2 Productos Más Caros por Categoría
* **Problema:** Dado un inventario de productos, retorna un mapa donde la clave sea la `Categoria` y el valor sea una lista con los **2 productos más caros** de dicha categoría.

```java
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Reto2TopNPorCategoria {
    public static Map<Categoria, List<Producto>> top2ProductosPorCategoria(List<Producto> inventario) {
        return inventario.stream()
            .collect(Collectors.groupingBy(
                Producto::categoria,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    lista -> lista.stream()
                        .sorted(Comparator.comparing(Producto::precio).reversed())
                        .limit(2)
                        .toList()
                )
            ));
    }

    public static void main(String[] args) {
        List<Producto> inventario = List.of(
            new Producto("1", "PC Gamer", Categoria.TECNOLOGIA, new BigDecimal("2000"), 5),
            new Producto("2", "Mousepad", Categoria.TECNOLOGIA, new BigDecimal("20"), 50),
            new Producto("3", "Laptop", Categoria.TECNOLOGIA, new BigDecimal("1500"), 10),
            new Producto("4", "Sofa", Categoria.HOGAR, new BigDecimal("800"), 2),
            new Producto("5", "Lámpara", Categoria.HOGAR, new BigDecimal("45"), 15),
            new Producto("6", "Cama King", Categoria.HOGAR, new BigDecimal("1200"), 3)
        );

        Map<Categoria, List<Producto>> resultado = top2ProductosPorCategoria(inventario);

        resultado.forEach((categoria, prods) -> {
            System.out.println("=== " + categoria + " ===");
            prods.forEach(p -> System.out.println(" - " + p.nombre() + ": $" + p.precio()));
        });
    }
}
```

---

### 📝 Reto 3: Frecuencia y Tokenización de Palabras en Logs
* **Problema:** Dada una lista de líneas de log de servidor, extrae todas las palabras, ignora las de longitud menor a 4 letras, pásalas a minúsculas y obtén el conteo de frecuencia de cada palabra ordenado descendentemente.

```java
import java.util.*;
import java.util.stream.Collectors;

public class Reto3FrecuenciaPalabras {
    public static void main(String[] args) {
        List<String> logs = List.of(
            "ERROR 500 Database connection timeout in postgres cluster",
            "WARN 404 Endpoint not found for user request",
            "ERROR 500 Database query deadlocked during transaction update",
            "INFO 200 Database connection pool restored successfully"
        );

        Map<String, Long> frecuenciaPalabras = logs.stream()
            // 1. Dividir cada línea por espacios en blanco usando flatMap
            .flatMap(linea -> Arrays.stream(linea.split("\\s+")))
            // 2. Normalizar a minúsculas
            .map(String::toLowerCase)
            // 3. Filtrar palabras de longitud >= 4 caracteres
            .filter(palabra -> palabra.length() >= 4)
            // 4. Agrupar por palabra y contar ocurrencias
            .collect(Collectors.groupingBy(
                palabra -> palabra,
                Collectors.counting()
            ));

        // 5. Imprimir ordenado por mayor frecuencia
        frecuenciaPalabras.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " veces"));
    }
}
```

---

### 📝 Reto 4: Partición de Inventario y Detección de Reabastecimiento Crítico
* **Problema:** Divide el inventario en dos grupos (`Map<Boolean, List<Producto>>`): aquellos que necesitan reabastecimiento urgente (`stock < 10`) y los que tienen stock saludable. Luego genera una alerta consolidada.

```java
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Reto4ParticionStock {
    public static void main(String[] args) {
        List<Producto> inventario = List.of(
            new Producto("1", "Disco SSD 1TB", Categoria.TECNOLOGIA, new BigDecimal("90"), 3),
            new Producto("2", "Memoria RAM 16GB", Categoria.TECNOLOGIA, new BigDecimal("65"), 2),
            new Producto("3", "Cable HDMI 2.1", Categoria.TECNOLOGIA, new BigDecimal("15"), 45),
            new Producto("4", "Webcam 1080p", Categoria.TECNOLOGIA, new BigDecimal("55"), 18)
        );

        // Particionar por condición booleana stock < 10
        Map<Boolean, List<Producto>> particion = inventario.stream()
            .collect(Collectors.partitioningBy(p -> p.stock() < 10));

        List<Producto> reordenUrgente = particion.get(true);
        List<Producto> stockOptimo = particion.get(false);

        System.out.println("🚨 PRODUCTOS QUE REQUIEREN COMPRA URGENTE:");
        reordenUrgente.forEach(p -> System.out.println(" - " + p.nombre() + " (Quedan solo " + p.stock() + " unidades)"));

        System.out.println("\n✅ PRODUCTOS CON STOCK SALUDABLE: " + stockOptimo.size() + " referencias.");
    }
}
```

---

## 💡 Resumen de Buenas Prácticas de Rendimiento y Código Limpio

1. **Evita el *Autoboxing* innecesario:** Usa `mapToInt()`, `mapToDouble()` o `mapToLong()` cuando vayas a sumar o promediar números en vez de `.map().reduce()`.
2. **Usa `toList()` nativo (Java 16+):** Es más rápido y genera listas inmutables libres de mutaciones accidentales.
3. **Prefiere `findFirst()` o `anyMatch()` para cortar temprano:** No proceses listas completas si solo buscas validar la existencia de un elemento.
4. **Composición de Comparators:** Encadena `Comparator.comparing(...).thenComparing(...)` para ordenamientos multinivel limpios y legibles.
