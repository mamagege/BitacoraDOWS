# Reto #3: La Fábrica de Instrumentos

## 1. Patrón de Diseño
* **Categoría:** Creacional
* **Patrón Utilizado:** **Abstract Factory**

---

## 2. Justificación
La fábrica *Armonía Andina* produce instrumentos musicales organizados en tres familias (`Cuerda`, `Viento`, `Percusión`) y tres gamas de manufactura (`Estudiante`, `Profesional`, `Vintage`). Cada gama impone características transversales consistentes: materiales, afinación (440 Hz o 442 Hz) y factor multiplicador sobre el precio base ($\times 1.0$, $\times 3.0$, $\times 5.0$).

El patrón **Abstract Factory** fue elegido porque:
1. **Garantiza la compatibilidad y consistencia de familias de productos:** Cada fábrica concreta asegura que todos los instrumentos creados bajo esa gama compartan los parámetros de calidad, factor y afinación correctos.
2. **Aísla al cliente del código de creación:** El cliente ([`Pedido`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/Pedido.java) y [`Reto3FabricaInstrumentos`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/Reto3FabricaInstrumentos.java)) interactúa únicamente con la interfaz abstracta `FabricaInstrumentos` e `Instrumento`, sin acoplarse a clases concretas.
3. **Facilita la incorporación de nuevas gamas (OCP):** Agregar una gama (ej. *Custom Shop*) solo requiere implementar una nueva fábrica concreta sin alterar las existentes.

---

## 3. Aplicación y Estructura de Clases

| Clase / Archivo | Rol en el Patrón / Sistema |
| :--- | :--- |
| [`FabricaInstrumentos.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/FabricaInstrumentos.java) | **Abstract Factory:** Interfaz que define las operaciones de creación para cada familia (`crearCuerda`, `crearViento`, `crearPercusion`). |
| [`FabricaEstudiante.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/FabricaEstudiante.java) | **Concrete Factory:** Crea instrumentos de gama Estudiante (Afinación: 440 Hz, Factor: $\times 1.0$). |
| [`FabricaProfesional.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/FabricaProfesional.java) | **Concrete Factory:** Crea instrumentos de gama Profesional (Afinación: 440 Hz, Factor: $\times 3.0$). |
| [`FabricaVintage.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/FabricaVintage.java) | **Concrete Factory:** Crea instrumentos de gama Vintage (Afinación: 442 Hz, Factor: $\times 5.0$). |
| [`Instrumento.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/Instrumento.java) | **Product:** Modela el instrumento con su modelo, familia, gama, afinación y calcula el precio final (`precioBase * factor`). |
| [`CatalogoPrecio.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/CatalogoPrecio.java) | **Service / Lookup:** Centraliza las tarifas base de los modelos (ej. Violín: $1.600.000, Saxofón: $2.500.000). |
| [`Pedido.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/Pedido.java) | **Domain Aggregate:** Colecciona los instrumentos solicitados y procesa totales con Streams. |
| [`Reto3FabricaInstrumentos.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/Reto3FabricaInstrumentos.java) | **Client / Presentation:** Controla la sesión, selecciona la fábrica polimórficamente y muestra el desglose del pedido. |

---

## 4. Uso de Streams
En [`Pedido.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto3/Pedido.java), el cálculo total a pagar se procesa mediante:
```java
public double calcularTotal() {
    return instrumentos.stream()
                        .mapToDouble(Instrumento::calcularPrecio)
                        .sum();
}
```

---

## 5. Cómo Usarlo (Ejemplo de Código)

```java
// 1. Instanciar la fábrica abstracta según la gama deseada
FabricaInstrumentos fabrica = new FabricaProfesional();

// 2. Crear instrumentos de distintas familias mediante la fábrica
Instrumento violin = fabrica.crearCuerda("Violín");
Instrumento saxofon = new FabricaVintage().crearViento("Saxofón");

// 3. Agregar a un pedido y totalizar
Pedido pedido = new Pedido();
pedido.agregarInstrumento(violin);
pedido.agregarInstrumento(saxofon);

System.out.println("Total pedido: $" + pedido.calcularTotal());
```
