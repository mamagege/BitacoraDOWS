# 💻 Semana 2 - 04: Ejemplos Prácticos y Completos de Estructuras de Datos

> **Módulo:** Semana 2 - Estructuras de Datos en Java  
> **Tema:** Implementación en código de todas las estructuras principales con casos de uso aplicados.

---

## 1. Arrays y Listas (Colecciones Indexadas)

### Array Estático (Tamaño Fijo)
Ideal para datos que no cambian de tamaño, como una matriz de adyacencia, coordenadas o configuraciones predefinidas.

```java
// 1. Declaración e inicialización
String[] algoritmosBusqueda = new String[3];
algoritmosBusqueda[0] = "Depth-First Search";
algoritmosBusqueda[1] = "Best-First Search";
algoritmosBusqueda[2] = "A*";

// Sintaxis abreviada
double[] probabilidadesPartido = {2.50, 3.10, 1.85}; // Local, Empate, Visitante

// 2. Iteración clásica
for (int i = 0; i < probabilidadesPartido.length; i++) {
    System.out.println("Cuota: " + probabilidadesPartido[i]);
}
```


## ArrayList (Tamaño Dinámico)
La estructura más usada en el día a día para listas de dominio (bases de datos, resultados de APIs, etc.).Javaimport java.util.ArrayList;

```java

import java.util.List;

List<String> estudiantes = new ArrayList<>();

// 1. Inserción
estudiantes.add("Ana");
estudiantes.add("Carlos");
estudiantes.add(1, "Beatriz"); // Inserta en medio: [Ana, Beatriz, Carlos]

// 2. Lectura y Modificación
String primerEstudiante = estudiantes.get(0);
estudiantes.set(2, "Carlos Alberto"); // Actualiza el índice 2

// 3. Iteración moderna (For-Each)
for (String estudiante : estudiantes) {
    System.out.println("Estudiante registrado: " + estudiante);
}

// 4. Expresiones Lambda (Java 8+)
estudiantes.forEach(est -> System.out.println(est));

```


## 2. Mapas (Diccionarios Clave-Valor)HashMap (Rápido, sin orden)
Excelente para relacionar identificadores únicos con objetos o valores (búsqueda en $O(1)$).Javaimport java.util.HashMap;

```java
import java.util.Map;

// Simulación de puntajes para una plataforma de ranking (ej. ECI RANKS)
Map<String, Integer> plataformaRanks = new HashMap<>();

// 1. Inserción (Put)
plataformaRanks.put("user_789", 1500);
plataformaRanks.put("user_123", 2100);
plataformaRanks.put("user_456", 1850);

// 2. Recuperación Segura
int puntaje = plataformaRanks.getOrDefault("user_999", 0); // Si no existe, devuelve 0

// 3. Iteración de Claves y Valores
for (Map.Entry<String, Integer> entry : plataformaRanks.entrySet()) {
    System.out.println("Usuario: " + entry.getKey() + " | Score: " + entry.getValue());
}

// 4. Eliminar
plataformaRanks.remove("user_789");

```


## TreeMap (Ordenado automáticamente)
Mantiene las claves ordenadas (alfabética o numéricamente, con coste $O(\log n)$).Javaimport java.util.TreeMap;

```java
import java.util.Map;

// Ordenará los usuarios alfabéticamente por su ID automáticamente
Map<String, Integer> rankingOrdenado = new TreeMap<>(plataformaRanks);

```


## 3. Conjuntos (Sets - Sin duplicados)
HashSetIdeal para filtrar datos repetidos rápidamente o verificar existencias cruzadas.Javaimport java.util.HashSet;

```java
import java.util.Set;
import java.util.Arrays;

Set<Integer> idsProcesados = new HashSet<>();

// 1. Inserción
idsProcesados.add(101);
idsProcesados.add(102);
boolean seAgrego = idsProcesados.add(101); // Devuelve false, ya existe

// 2. Filtrar una lista con duplicados en una sola línea
List<String> ips = Arrays.asList("192.168.1.1", "10.0.0.5", "192.168.1.1");
Set<String> ipsUnicas = new HashSet<>(ips); // Resulta en solo 2 elementos

// 3. Verificación O(1)
if (idsProcesados.contains(102)) {
    System.out.println("El ID ya fue validado en el sistema.");
}

```

## 4. Colas y Pilas (Procesamiento secuencial)
Queue (Cola - FIFO) con LinkedListIdeal para sistemas de atención, procesamiento de tickets asíncronos o enrutamiento de solicitudes.

```java
import java.util.LinkedList;
import java.util.Queue;

// Simulación de una cola de llamadas entrantes (ej. Customer Service)
Queue<String> colaLlamadas = new LinkedList<>();

// 1. Encolar (Llegan las llamadas al sistema)
colaLlamadas.offer("Llamada Entrante - Cliente A");
colaLlamadas.offer("Llamada Entrante - Cliente B");

// 2. Ver el próximo sin desencolar
System.out.println("Próximo en ser atendido: " + colaLlamadas.peek());

// 3. Desencolar (Atender y sacar de la fila)
String llamadaActual = colaLlamadas.poll(); // Saca a "Cliente A"

```

## Deque (Pila - LIFO) con ArrayDequeIdeal para historiales, algoritmos de backtracking o la clásica función de "deshacer" (Ctrl+Z).

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<String> historialNavegacion = new ArrayDeque<>();

// 1. Apilar (Push)
historialNavegacion.push("Inicio");
historialNavegacion.push("Perfil de Usuario");
historialNavegacion.push("Configuración de Cuenta");

// 2. Desapilar (Pop - Volver atrás)
String paginaAnterior = historialNavegacion.pop(); // Saca "Configuración de Cuenta"
System.out.println("Volviendo a: " + historialNavegacion.peek()); // Muestra "Perfil de Usuario"

```

## 5. Manipulación de Cadenas Dinámicas

### StringBuilder (Rápido, un solo hilo)
Esencial para construir consultas SQL largas, JSONs o código dinámico dentro de bucles sin colapsar la memoria con objetos inmutables.

```java


// Generación dinámica de una consulta SQL estructurada
String[] columnas = {"id", "nombre", "semestre"};
String tabla = "facultad_sistemas";

StringBuilder sqlBuilder = new StringBuilder("SELECT ");

for (int i = 0; i < columnas.length; i++) {
    sqlBuilder.append(columnas[i]);
    if (i < columnas.length - 1) {
        sqlBuilder.append(", ");
    }
}

sqlBuilder.append(" FROM ").append(tabla).append(";");
String queryFinal = sqlBuilder.toString();
// Resultado final: "SELECT id, nombre, semestre FROM facultad_sistemas;"

```


### StringBuffer (Seguro para hilos / Thread-Safe)

Sintaxis idéntica a StringBuilder, pero con métodos sincronizados. Úsalo solo si múltiples hilos están escribiendo en la misma cadena simultáneamente.

```java

StringBuffer bufferSeguro = new StringBuffer();
bufferSeguro.append("Inicializando log transaccional...\n");
// Varios hilos concurrentes pueden hacer .append() de forma asíncrona sin corromper el texto final

```