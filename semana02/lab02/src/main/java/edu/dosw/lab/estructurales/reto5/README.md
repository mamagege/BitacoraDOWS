# Reto #5: La Moto Personalizada

## 1. Patrón de Diseño
* **Categoría:** Estructural
* **Patrón Utilizado:** **Decorator**

---

## 2. Justificación
El taller *Turbo Andes* personaliza motocicletas agregando dinámicamente mejoras (accesorios, pinturas y complementos) con costos adicionales. Se requiere que el sistema permita combinar libremente cualquier número y tipo de mejoras sobre cualquier moto base sin modificar la clase base ni generar una explosión combinatoria de subclases.

El patrón **Decorator** fue elegido porque:
1. **Extensión dinámica de responsabilidades en tiempo de ejecución:** Permite envolver el objeto moto base recursivamente con capas de mejoras adicionales.
2. **Cumplimiento estricto de Open/Closed Principle (OCP):** La clase [`MotoBase`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/MotoBase.java) permanece inalterada y cerrada a cambios, mientras que el sistema está abierto a nuevas mejoras mediante la creación de nuevas clases decoradoras.
3. **Cálculo acumulativo transparente:** Cada decorador añade su costo y su descripción sobre el resultado acumulado del objeto envuelto.

---

## 3. Aplicación y Estructura de Clases

| Clase / Archivo | Rol en el Patrón / Sistema |
| :--- | :--- |
| [`Moto.java`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/Moto.java) | **Component Interface:** Define el contrato común (`getModelo`, `getPrecioBase`, `getPrecioMejoras`, `getPrecioTotal`, `getDescripcion`, `getMejorasDetalle`). |
| [`MotoBase.java`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/MotoBase.java) | **Concrete Component:** Representa la motocicleta base de fábrica sin modificaciones. |
| [`MejoraMotoDecorator.java`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/MejoraMotoDecorator.java) | **Base Decorator:** Clase abstracta que implementa `Moto` y mantiene la referencia al objeto `Moto` envuelto. |
| [`AccesorioDecorator.java`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/AccesorioDecorator.java) | **Concrete Decorator:** Añade dinámicamente el precio y la descripción de un accesorio, pintura o complemento sobre la moto. |
| [`CatalogoTaller.java`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/CatalogoTaller.java) | **Catalog Service (SRP):** Centraliza la lista de opciones y tarifas del taller, desacoplando los precios del modelo de dominio. |
| [`Reto5MotoPersonalizada.java`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/Reto5MotoPersonalizada.java) | **Client / Presentation:** Coordina la selección interactiva de mejoras y muestra el desglose del costo total. |

---

## 4. Uso de Streams
En [`CatalogoTaller.java`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/CatalogoTaller.java) y [`Reto5MotoPersonalizada.java`](file:///src/main/java/edu/dosw/lab/estructurales/reto5/Reto5MotoPersonalizada.java), la búsqueda de opciones y totalización de colecciones de mejoras se procesa con Streams:
```java
double totalMejoras = mejoras.stream()
                             .mapToDouble(MejoraInfo::getPrecio)
                             .sum();
```

---

## 5. Cómo Usarlo (Ejemplo de Código)

```java
// 1. Instanciar la moto base
Moto moto = new MotoBase("Naked 250", 9800000);

// 2. Decorar con accesorios sucesivos
moto = new AccesorioDecorator(moto, "Escape deportivo", 1400000);
moto = new AccesorioDecorator(moto, "Pintura mate negro", 900000);
moto = new AccesorioDecorator(moto, "Baúl trasero", 550000);

// 3. Obtener valores consolidados
System.out.println("Descripción: " + moto.getDescripcion());
System.out.println("Precio Base: $" + moto.getPrecioBase());
System.out.println("Mejoras:     $" + moto.getPrecioMejoras()); // $2.850.000
System.out.println("Total:       $" + moto.getPrecioTotal());   // $12.650.000
```
