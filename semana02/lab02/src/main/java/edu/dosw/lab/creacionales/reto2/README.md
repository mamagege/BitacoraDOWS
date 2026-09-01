# Reto #2: El Sastre a la Medida

## 1. Patrón de Diseño
* **Categoría:** Creacional
* **Patrón Utilizado:** **Builder**

---

## 2. Justificación
Un sastre confecciona trajes a la medida donde cada prenda es un objeto compuesto (`Traje`) estructurado por partes obligatorias (tela, saco, pantalón) y múltiples componentes opcionales (chaleco, forro, bordado). 

El patrón **Builder** fue elegido porque:
1. **Desacopla el proceso de construcción paso a paso** de la representación interna final del producto.
2. **Evita constructores sobrecargados (telescópicos)** con múltiples parámetros `null` o booleanos confusos.
3. **Garantiza la consistencia e integridad del objeto:** El método `build()` valida que todas las partes obligatorias hayan sido seleccionadas antes de permitir la instanciación de `Traje`. Si falta alguna pieza obligatoria, lanza una excepción de estado ilegal (`IllegalStateException`).

---

## 3. Aplicación y Estructura de Clases

| Clase / Archivo | Rol en el Patrón / Sistema |
| :--- | :--- |
| [`TrajeBuilder.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto2/TrajeBuilder.java) | **Builder:** Provee una API fluida (`conTela`, `conSaco`, `conPantalon`, `agregarOpcional`) para configurar paso a paso la prenda y valida las reglas obligatorias en `build()`. |
| [`Traje.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto2/Traje.java) | **Product:** Objeto complejo final que almacena las piezas seleccionadas y calcula el valor total delegando a Java Streams. |
| [`Pieza.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto2/Pieza.java) | **Component Model:** Clase inmutable que representa cada pieza individual con su nombre y precio. |
| [`Reto2SastreMedida.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto2/Reto2SastreMedida.java) | **Client / Presentation:** Coordina la entrada de datos del usuario, invoca los métodos del builder y presenta el resumen formateado del traje. |

---

## 4. Uso de Streams
En la clase [`Traje.java`](file:///src/main/java/edu/dosw/lab/creacionales/reto2/Traje.java), la sumatoria del precio de todas las piezas se realiza de manera funcional:
```java
public int getPrecioTotal() {
    return piezas.stream()
                 .mapToInt(Pieza::getPrecio)
                 .sum();
}
```

---

## 5. Cómo Usarlo (Ejemplo de Código)

```java
// Construcción fluida y segura del traje
Traje traje = new TrajeBuilder()
    .conTela("Lana italiana", 320000)
    .conSaco("Cruzado", 250000)
    .conPantalon("Corte slim", 180000)
    .agregarOpcional("Chaleco", "Clásico", 90000)
    .agregarOpcional("Bordado", "Iniciales", 35000)
    .build();

System.out.println("Total: $" + traje.getPrecioTotal()); // $875.000
```