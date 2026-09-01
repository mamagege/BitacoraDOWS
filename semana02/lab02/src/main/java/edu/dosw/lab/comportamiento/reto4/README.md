# Reto #4: La Balanza Trucada del Mercado

## 1. Patrón de Diseño
* **Categoría:** Comportamiento
* **Patrón Utilizado:** **Strategy**

---

## 2. Justificación
La plaza de mercado requería un sistema transparente para convertir de forma bidireccional cualquier unidad de peso soportada (`Gramo`, `Libra`, `Arroba`, `Kilogramo`) a cualquier otra unidad con su factor real, utilizando el **Kilogramo (kg)** como unidad base de conversión de referencia ($1\text{ kg} = 1000\text{ g}$, $1\text{ kg} = 2.2046\text{ lb}$, $1\text{ kg} = 0.08\text{ @}$).

El patrón **Strategy** fue elegido porque:
1. **Encapsula cada algoritmo de conversión en una clase independiente:** Evita estructuras condicionales anidadas (`if-else` o `switch`) dispersas por el código.
2. **Cumple con el principio Open/Closed (OCP):** Permite incorporar nuevas unidades de medida (ej. *Onza*, *Tonelada*) simplemente creando una nueva clase que implemente `EstrategiaUnidad`, sin modificar ninguna clase existente.
3. **Intercambiabilidad en tiempo de ejecución:** Cualquier pesaje puede combinar dinámicamente cualquier par de estrategias de origen y destino.

---

## 3. Aplicación y Estructura de Clases

| Clase / Archivo | Rol en el Patrón / Sistema |
| :--- | :--- |
| [`EstrategiaUnidad.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto4/EstrategiaUnidad.java) | **Strategy Interface:** Define el contrato de conversión bidireccional (`aKilogramos`, `desdeKilogramos`, `getNombre`, `getCodigo`). |
| [`Gramo.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto4/Gramo.java) | **Concrete Strategy:** Implementa la conversión para gramos ($1\text{ kg} = 1000\text{ g}$). |
| [`Libra.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto4/Libra.java) | **Concrete Strategy:** Implementa la conversión para libras ($1\text{ kg} = 2.2046\text{ lb}$). |
| [`Arroba.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto4/Arroba.java) | **Concrete Strategy:** Implementa la conversión para arrobas ($1\text{ kg} = 0.08\text{ @}$). |
| [`Kilogramo.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto4/Kilogramo.java) | **Concrete Strategy:** Unidad base pivote ($1\text{ kg} = 1\text{ kg}$). |
| [`Pesaje.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto4/Pesaje.java) | **Context:** Mantiene la cantidad original y las referencias a las estrategias de origen y destino, calculando la equivalencia y el peso en kg. |
| [`Reto4BalanzaTrucada.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto4/Reto4BalanzaTrucada.java) | **Client / Presentation:** Captura los pesajes en la sesión y usa Streams para sumar el total en kg. |

---

## 4. Uso de Streams
En [`Reto4BalanzaTrucada.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto4/Reto4BalanzaTrucada.java), la sumatoria total de los pesajes convertidos a kilogramos se realiza con Streams:
```java
double totalKg = pesajes.stream()
                        .mapToDouble(Pesaje::aKilogramos)
                        .sum();
```

---

## 5. Cómo Usarlo (Ejemplo de Código)

```java
// 1. Definir estrategias de origen y destino
EstrategiaUnidad origen = new Libra();
EstrategiaUnidad destino = new Kilogramo();

// 2. Crear el objeto de pesaje
Pesaje pesaje = new Pesaje(40.0, origen, destino);

// 3. Ejecutar conversión
double resultado = pesaje.calcularConversion(); // 40 lb -> 18.1438 kg
System.out.println(pesaje.getCantidadOriginal() + " " + origen.getCodigo() + " = " + resultado + " " + destino.getCodigo());
```
