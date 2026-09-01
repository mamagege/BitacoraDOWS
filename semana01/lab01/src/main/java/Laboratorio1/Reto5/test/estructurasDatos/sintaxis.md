## 💻 PARTE 3: Sintaxis y Operaciones Clave (Cheat Sheet)

### 1. Listas (`ArrayList` y `LinkedList`)

```java
import java.util.ArrayList;
import java.util.List;

List<String> lista = new ArrayList<>();
lista.add("Java");           // Inserta al final
lista.add(0, "Python");      // Inserta en índice
lista.get(0);                // Lee elemento
lista.set(0, "C++");         // Reemplaza
lista.remove(0);             // Elimina por índice
lista.size();                // Tamaño (int)

```

## 2. Mapas (HashMap y TreeMap)

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> edades = new HashMap<>();
edades.put("Juan", 20);          // Inserta/Actualiza
edades.get("Juan");              // Retorna 20
edades.getOrDefault("Ana", 0);   // Retorna 0 si no existe
edades.containsKey("Juan");      // true / false

// Iterar
for (Map.Entry<String, Integer> entry : edades.entrySet()) {
    entry.getKey();
    entry.getValue();
}

```

## 3. Conjuntos (HashSet y TreeSet)

```java
import java.util.HashSet;
import java.util.Set;

Set<Integer> numeros = new HashSet<>();
numeros.add(10);                 // Devuelve true
numeros.add(10);                 // Ignorado (duplicado)
numeros.contains(10);            // Búsqueda ultra rápida

```


## 4. Pilas (Deque) - LIFO

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<String> pila = new ArrayDeque<>();
pila.push("Plato 1");            // Agrega al tope
pila.peek();                     // Mira el tope SIN borrarlo
pila.pop();                      // Saca y devuelve el tope

```

## 5. Colas (Queue) - FIFO

```java
import java.util.Queue;
import java.util.LinkedList;

Queue<String> cola = new LinkedList<>();
cola.offer("Cliente 1");         // Agrega al final
cola.peek();                     // Mira el primero en la fila
cola.poll();                     // Saca y devuelve el primero

```

## 6. Cadenas Dinámicas (StringBuilder)

```java
StringBuilder sb = new StringBuilder();
sb.append("Hola").append(" Java");
sb.insert(5, "Mundo ");
String textoFinal = sb.toString(); 

```

## 💡 Key Takeaways Generales para Examen

* **Primitivos en Colecciones:** No puedes hacer `List<int>`. Tienes que usar `Integer`, `Double`, `Boolean`, `Character`.

* **Igualdad de Strings:** NUNCA compares Strings con `==`. Usa siempre `cadena1.equals(cadena2)`.

* **Sintaxis de longitud:**
    * Arrays: array.length (propiedad)
    * Colecciones (List, Set, Map): lista.size() (método)
    * Strings: texto.length() (método)