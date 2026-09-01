# Ejercicios de Java 8+ Stream API

Este repositorio contiene las soluciones en clases independientes Java para cada uno de los ejercicios presentados sobre **Java Streams**, expresiones Lambda, referencias a métodos y programación funcional.

---

## 📋 Resumen de los Ejercicios

### 1. [Ejercicio01.java](file:///c:/Users/audiovisuales/Downloads/DOWS/semana02/ejerciciosPresentacion/Ejercicio01.java) - Filtrado Numérico Doble / Condición Compuesta
- **Objetivo**: Obtener números pares estrictamente mayores a 10 de una lista de enteros `[3, 8, 10, 12, 15, 18, 20]`.
- **Técnicas clave**:
  - Encadenamiento de múltiples llamadas a `.filter(...)` para separar la lógica de filtrado.
  - Uso de expresiones booleanas compuestas `(&&)` dentro de un solo `.filter(...)`.
  - Colectado final con `.toList()`.

---

### 2. [Ejercicio02.java](file:///c:/Users/audiovisuales/Downloads/DOWS/semana02/ejerciciosPresentacion/Ejercicio02.java) - Procesamiento de Cadenas, Inspección y Conteo
- **Objetivo**: Filtrar palabras con más de 4 caracteres, convertirlas a mayúsculas, ordenarlas alfabéticamente e inspeccionar o contar el resultado.
- **Técnicas clave**:
  - Transmutación de datos con `.map(String::toUpperCase)`.
  - Ordenamiento natural con `.sorted()`.
  - Depuración sin alterar el flujo usando `.peek(System.out::println)`.
  - Contabilización terminal con `.count()`.

---

### 3. [Ejercicio03.java](file:///c:/Users/audiovisuales/Downloads/DOWS/semana02/ejerciciosPresentacion/Ejercicio03.java) - Filtrado y Proyección sobre Objetos
- **Objetivo**: Obtener una lista ordenada alfabéticamente de los nombres en mayúsculas de los usuarios que están **activos**.
- **Técnicas clave**:
  - Modelo mediante `record Usuario(...)`.
  - Uso de referencias a métodos (`Usuario::active`, `Usuario::name`, `String::toUpperCase`).
  - Proyección de objetos a atributos primitivos/String mediante `.map(...)`.

---

### 4. [Ejercicio04.java](file:///c:/Users/audiovisuales/Downloads/DOWS/semana02/ejerciciosPresentacion/Ejercicio04.java) - Filtrado por Atributo Numérico
- **Objetivo**: Filtrar los usuarios cuya edad sea mayor o igual a 18 años (`age >= 18`) y extraer sus nombres.
- **Técnicas clave**:
  - Condicional dentro del predicado de `.filter(u -> u.age() >= 18)`.
  - Proyección a lista de nombres `List<String>`.

---

### 5. [Ejercicio05.java](file:///c:/Users/audiovisuales/Downloads/DOWS/semana02/ejerciciosPresentacion/Ejercicio05.java) - Validación de Lotes con `anyMatch` y `peek`
- **Objetivo**: Validar si un lote de transacciones bancarias es válido (es válido si **ninguna** transacción fue rechazada/no aprobada).
- **Técnicas clave**:
  - Uso de `.peek(...)` para hacer log/seguimiento del procesamiento en tiempo real.
  - Evaluación booleana eficiente mediante `.anyMatch(...)` para detectar irregularidades.
  - Retorno de validez global negando la presencia de fallos.

---

## 🚀 Cómo ejecutar los ejercicios

Puedes compilar y ejecutar cualquiera de los archivos con Java 16+ desde tu terminal:

```bash
javac Ejercicio01.java
java ejercicios.Ejercicio01
```

O usando el ejecutor directo de código fuente de Java (Java 11+):

```bash
java Ejercicio01.java
```
