# Reto #1: La Boletería del Cine Astor

## 1. Enfoque y Paradigma
* **Categoría:** Principios SOLID, POO Avanzada y Java Streams.
* **Problema:** Doña Marta administraba la boletería en hojas sueltas cometiendo errores en cobros, descuentos por espectador y precios de confitería. Se requería un sistema confiable, extensible e inmutable.

---

## 2. Aplicación de Principios SOLID

### • Single Responsibility Principle (SRP)
Cada clase posee una única responsabilidad bien delimitada:
* [`Item`](file:///src/main/java/edu/dosw/lab/solid/reto1/Item.java): Modela exclusivamente los datos inmutables de los productos (nombre y precio base).
* [`Espectador`](file:///src/main/java/edu/dosw/lab/solid/reto1/Espectador.java) y subclases: Encapsulan exclusivamente la regla de cálculo del porcentaje de descuento correspondiente al tipo de espectador.
* [`Orden`](file:///src/main/java/edu/dosw/lab/solid/reto1/Orden.java): Administra la agregación de ítems y la liquidación financiera (cálculo de subtotal, descuento total y total a pagar).
* [`Reto1BoleteriaAstor`](file:///src/main/java/edu/dosw/lab/solid/reto1/Reto1BoleteriaAstor.java): Se encarga de la interacción con la consola (I/O) y la renderización de la factura final.

### • Open/Closed Principle (OCP)
El sistema está abierto a la extensión pero cerrado a la modificación. La jerarquía de `Espectador` permite añadir nuevos tipos de cliente (ej. *Docente*, *Afiliado VIP*) creando una nueva clase derivada que implemente `calcularDescuento(double subtotal)` sin modificar la clase `Orden` ni recurrir a estructuras condicionales (`if-else` o `switch`).

### • Liskov Substitution Principle (LSP)
Cualquier subclase de `Espectador` ([`General`](file:///src/main/java/edu/dosw/lab/solid/reto1/General.java), [`Estudiante`](file:///src/main/java/edu/dosw/lab/solid/reto1/Estudiante.java), [`TerceraEdad`](file:///src/main/java/edu/dosw/lab/solid/reto1/TerceraEdad.java)) puede ser inyectada a `Orden` sin alterar el comportamiento correcto ni quebrar el contrato del método `calcularDescuento()`.

### • Interface Segregation Principle (ISP) / Abstracciones Específicas
El contrato `Espectador` contiene únicamente el método esencial `calcularDescuento()`, evitando obligar a las clases a implementar métodos innecesarios.

### • Dependency Inversion Principle (DIP)
`Orden` depende de la abstracción `Espectador` y no de implementaciones concretas como `Estudiante` o `General`.

---

## 3. Conceptos de POO Avanzada y Streams

* **Polimorfismo:** Se aplica mediante el método abstracto `calcularDescuento(double subtotal)` en `Espectador`, implementado de forma específica por cada tipo de cliente (0% para General, 15% para Estudiante, 25% para Tercera Edad).
* **Inmutabilidad:** La clase `Item` define sus campos `nombre` y `precio` como `private final` sin métodos setters, garantizando que las tarifas de boletas y confitería no cambien tras su creación.
* **Encapsulamiento:** Atributos protegidos bajo visibilidad `private` con acceso exclusivo mediante getters.
* **Java Streams:**
  * **Cálculo de subtotal:** `items.stream().mapToDouble(Item::getPrecio).reduce(0, Double::sum)` en `Orden`.
  * **Resumen de factura:** `orden.getItems().stream().collect(Collectors.groupingBy(Item::getNombre, Collectors.counting()))` para agrupar y totalizar ítems.

---

## 4. Estructura de Clases y Roles

| Clase / Archivo | Rol en la Solución |
| :--- | :--- |
| [`Espectador.java`](file:///src/main/java/edu/dosw/lab/solid/reto1/Espectador.java) | Abstracción base para tipos de espectador con regla de descuento polimórfica. |
| [`General.java`](file:///src/main/java/edu/dosw/lab/solid/reto1/General.java) | Espectador estándar (0% descuento). |
| [`Estudiante.java`](file:///src/main/java/edu/dosw/lab/solid/reto1/Estudiante.java) | Espectador estudiantil (15% descuento). |
| [`TerceraEdad.java`](file:///src/main/java/edu/dosw/lab/solid/reto1/TerceraEdad.java) | Espectador de la tercera edad (25% descuento). |
| [`Item.java`](file:///src/main/java/edu/dosw/lab/solid/reto1/Item.java) | Modelo inmutable de boletas y confitería. |
| [`Orden.java`](file:///src/main/java/edu/dosw/lab/solid/reto1/Orden.java) | Entidad de negocio que acumula ítems y procesa totales con Streams. |
| [`Reto1BoleteriaAstor.java`](file:///src/main/java/edu/dosw/lab/solid/reto1/Reto1BoleteriaAstor.java) | Controlador de consola para captura de datos y renderizado de factura. |

---

## 5. Cómo Usarlo (Ejemplo de Código)

```java
// 1. Instanciar el tipo de espectador
Espectador espectador = new Estudiante();

// 2. Crear la orden e incorporar ítems inmutables
Orden orden = new Orden(espectador);
Item boleta3D = new Item("Boleta 3D", 22000);
Item gaseosa = new Item("Gaseosa", 4500);

orden.agregarItem(boleta3D, 2);
orden.agregarItem(gaseosa, 2);

// 3. Liquidar y obtener resultados
double subtotal = orden.calcularSubtotal();   // 53.000
double descuento = orden.calcularDescuento(); // 15% de 53.000 = 7.950
double total = orden.calcularTotal();         // 45.050
```
