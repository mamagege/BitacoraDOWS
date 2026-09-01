# 🧱 Semana 2 - 01: Conceptos y Complejidad de Estructuras de Datos

> **Módulo:** Semana 2 - Estructuras de Datos en Java  
> **Tema:** Características, propiedades y complejidad (Big O) de las principales estructuras.

---

## 📌 1. Estructuras Lineales

### 1.1 Arrays (Arreglos Estáticos)
Colección de elementos del mismo tipo almacenados en posiciones contiguas de memoria con un tamaño fijo.
* **Propiedades:** Tamaño inmutable tras su creación. Acceso directo por índice.
* **Ventajas:** Extremadamente rápidos para leer datos si se conoce el índice. Bajo consumo de memoria extra.
* **Desventajas:** No pueden redimensionarse. Insertar o eliminar en el medio es costoso (requiere desplazar elementos).
* **Complejidad (Big O):**
  | Operación | Complejidad |
  | :--- | :--- |
  | Acceso por índice | O(1) |
  | Búsqueda (sin ordenar) | O(n) |
  | Inserción / Eliminación | O(n) |

### 1.2 ArrayList (Arreglos Dinámicos)
Implementación de la interfaz `List` respaldada por un array dinámico que crece automáticamente.
* **Propiedades:** Permite duplicados y valores `null`. Mantiene el orden de inserción.
* **Ventajas:** Redimensionamiento automático. Excelente para lectura y adición al final.
* **Desventajas:** Lentos al insertar/eliminar en el medio o al inicio, ya que debe desplazar todos los elementos a la derecha.
* **Complejidad (Big O):**
  | Operación | Complejidad |
  | :--- | :--- |
  | Acceso por índice | O(1) |
  | Búsqueda | O(n) |
  | Inserción al final | O(1) (Amortizado) |
  | Inserción / Eliminación en el medio | O(n) |

### 1.3 LinkedList (Listas Enlazadas)
Implementación de `List` y `Deque`. Cada elemento (nodo) guarda el dato y un puntero al nodo anterior y siguiente (lista doblemente enlazada).
* **Propiedades:** No usa memoria contigua. 
* **Ventajas:** Inserciones y eliminaciones muy rápidas si ya se tiene la referencia del nodo.
* **Desventajas:** Alto consumo de memoria (por los punteros). Acceso secuencial muy lento (no hay índices reales).
* **Complejidad (Big O):**
  | Operación | Complejidad |
  | :--- | :--- |
  | Acceso por índice | O(n) |
  | Inserción / Eliminación (con referencia) | O(1) |

---

## 🥞 2. Pilas y Colas

### 2.1 Stack (Pila)
Estructura basada en el principio **LIFO** (Last In, First Out). El último en entrar es el primero en salir.
* **Propiedades:** En Java, la clase heredada `Stack` es considerada obsoleta (legacy); se recomienda usar `Deque` (ej. `ArrayDeque`).
* **Ventajas:** Perfecta para rastrear estados previos (ej. deshacer, historial del navegador, recursividad).
* **Complejidad (Big O):**
  | Operación | Complejidad |
  | :--- | :--- |
  | Push (Insertar) / Pop (Eliminar) | O(1) |
  | Peek (Ver el tope) | O(1) |

### 2.2 Queue (Cola)
Estructura basada en el principio **FIFO** (First In, First Out). El primero en entrar es el primero en salir.
* **Propiedades:** Ideal para procesamiento asíncrono, buffers y colas de impresión.
* **Complejidad (Big O):**
  | Operación | Complejidad |
  | :--- | :--- |
  | Enqueue (Insertar al final) | O(1) |
  | Dequeue (Eliminar al inicio) | O(1) |

---

## 🔑 3. Estructuras Basadas en Hash (Tablas Hash)

### 3.1 HashSet (Conjuntos)
Implementación de la interfaz `Set` respaldada internamente por un HashMap. 
* **Propiedades:** **NO permite elementos duplicados**. NO garantiza ningún orden de los elementos. Permite un valor `null`.
* **Ventajas:** Búsquedas, inserciones y eliminaciones ultrarrápidas. Ideal para operaciones matemáticas de conjuntos (unión, intersección) o eliminar duplicados de una lista.
* **Complejidad (Big O - Caso Promedio):**
  | Operación | Complejidad |
  | :--- | :--- |
  | Inserción / Eliminación | O(1) |
  | Búsqueda (`contains`) | O(1) |

### 3.2 HashMap (Mapas Clave-Valor)
Almacena datos en pares **Clave-Valor** (`Key-Value`). Usa una función *hash* sobre la clave para determinar la ubicación en memoria.
* **Propiedades:** Las claves deben ser únicas (si se repite, se sobreescribe el valor). No mantiene el orden.
* **Ventajas:** Acceso directo e instantáneo a un valor si se conoce su clave. Muy eficiente.
* **Desventajas:** Puede sufrir "colisiones" (cuando dos claves generan el mismo hash), lo que en el peor de los casos degrada la búsqueda a O(log n) en Java 8+.
* **Complejidad (Big O - Caso Promedio):**
  | Operación | Complejidad |
  | :--- | :--- |
  | Inserción (`put`) / Eliminación | O(1) |
  | Búsqueda por Clave (`get`) | O(1) |

---

## 🌲 4. Estructuras Basadas en Árboles (Ordenadas)

### 4.1 TreeSet / TreeMap
Implementaciones respaldadas por un Árbol Rojo-Negro (Red-Black Tree, un tipo de árbol binario de búsqueda autobalanceado).
* **Propiedades:** Mantienen los elementos (o claves, en el caso del Map) **ordenados de forma natural** (ej. orden alfabético o numérico) o mediante un `Comparator` personalizado.
* **Ventajas:** Los datos siempre están ordenados. Excelente para consultas de rangos (ej. "dame todos los números entre 10 y 50").
* **Desventajas:** Operaciones más lentas que sus contrapartes `Hash`, ya que debe rebalancear el árbol al insertar/eliminar.
* **Complejidad (Big O):**
  | Operación | Complejidad |
  | :--- | :--- |
  | Inserción / Eliminación | O(log n) |
  | Búsqueda | O(log n) |

---

## 💡 Key Takeaways para Examen

* **¿Búsqueda rápida y sin orden?** Usa `HashMap` o `HashSet` (O(1)).
* **¿Datos ordenados permanentemente?** Usa `TreeMap` o `TreeSet` (O(log n)).
* **¿Acceso constante por índice?** Usa `ArrayList` o `Array` normal (O(1)).
* **¿Insertar/eliminar masivamente en los extremos?** Usa `LinkedList` o `ArrayDeque` (O(1)).