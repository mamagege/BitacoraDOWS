# ⚖️ Semana 2 - 02: Comparación y Elección de Estructuras de Datos

> **Módulo:** Semana 2 - Estructuras de Datos en Java  
> **Tema:** Diferencias clave entre estructuras similares y criterios de selección.

---

## 1. Array estático vs. ArrayList

Ambas estructuras almacenan elementos de forma indexada, pero sus propósitos son distintos.

| Característica | `Array` (`T[]`) | `ArrayList<T>` |
| :--- | :--- | :--- |
| **Tamaño** | Fijo (inmutable tras declararse). | Dinámico (crece automáticamente). |
| **Tipos de Datos** | Soporta primitivos (`int`, `char`) y Objetos. | Solo soporta Objetos (usa *Wrappers* como `Integer`). |
| **Rendimiento** | Ligeramente más rápido. Menor uso de memoria. | Ligero *overhead* por el redimensionamiento dinámico. |
| **Sintaxis** | Se usa `[]` para instanciar y acceder. | Usa métodos `.add()`, `.get()`, etc. |

**🏆 Cuándo usar cuál:**
* Usa **Array** si conoces el tamaño exacto de antemano y no va a cambiar (ej. los días de la semana, un tablero de ajedrez 8x8).
* Usa **ArrayList** por defecto para casi cualquier lista de dominio o negocio donde no sepas cuántos elementos habrá.

---

## 2. HashMap vs. HashTable (vs. ConcurrentHashMap)

Ambas implementan la interfaz `Map` para guardar pares Clave-Valor, pero difieren fuertemente en concurrencia (hilos).

| Característica | `HashMap` | `HashTable` (Legacy) |
| :--- | :--- | :--- |
| **Thread-Safe** | ❌ No (No seguro en multihilo). | ✅ Sí (Métodos sincronizados). |
| **Rendimiento** | 🚀 Rápido (sin bloqueos de hilo). | 🐢 Lento (bloquea todo el mapa por cada lectura/escritura). |
| **Valores `null`** | Permite **una** clave nula y múltiples valores nulos. | **No** permite claves ni valores nulos (Lanza `NullPointerException`). |

**🏆 Cuándo usar cuál:**
* Usa **HashMap** en el 99% de los casos (aplicaciones de un solo hilo o donde manejas la sincronización manualmente).
* **Nunca uses `HashTable`** en código moderno. Si necesitas seguridad multihilo, usa **`ConcurrentHashMap`**, que es mucho más eficiente porque solo bloquea el segmento del mapa que se está modificando, no todo el mapa.

---

## 3. HashSet vs. TreeSet vs. LinkedHashSet

Las tres implementan la interfaz `Set` (colecciones sin duplicados), pero difieren en el orden.

| Característica | `HashSet` | `LinkedHashSet` | `TreeSet` |
| :--- | :--- | :--- | :--- |
| **Orden** | Ninguno (Aleatorio según el hash). | Orden de Inserción. | Orden Natural o por `Comparator`. |
| **Velocidad** | O(1) - La más rápida. | O(1) - Ligeramente más lenta. | O(log n) - La más lenta. |
| **Valores nulos**| Permite un valor nulo. | Permite un valor nulo. | **No** permite nulos (falla al comparar). |

**🏆 Cuándo usar cuál:**
* Usa **HashSet** si solo te importa verificar existencias (`.contains()`) rápidamente y eliminar duplicados.
* Usa **LinkedHashSet** si necesitas eliminar duplicados pero es vital recordar en qué orden llegaron los datos.
* Usa **TreeSet** si necesitas presentar los datos ordenados alfabéticamente o numéricamente en todo momento.

---

## 4. Gestión de Texto: String vs. StringBuilder vs. StringBuffer

En Java, el manejo de cadenas es crucial para la memoria y el rendimiento.

| Característica | `String` | `StringBuilder` | `StringBuffer` |
| :--- | :--- | :--- | :--- |
| **Mutabilidad** | ❌ Inmutable (crea nuevo objeto al cambiar).| ✅ Mutable (modifica el mismo objeto). | ✅ Mutable (modifica el mismo objeto). |
| **Thread-Safe** | ✅ Sí (por ser inmutable). | ❌ No. | ✅ Sí (sincronizado). |
| **Rendimiento** | Lento en concatenaciones en bucle. | 🚀 Muy rápido. | Intermedio (overhead de sincronización). |

**🏆 Cuándo usar cuál:**
* Usa **String** para textos constantes, literales cortos, o variables que no van a cambiar tras ser creadas.
* Usa **StringBuilder** si necesitas construir un texto dinámicamente, concatenando partes dentro de un bucle (ej. armar un JSON, un reporte HTML o una consulta SQL).
* Usa **StringBuffer** solo si estás construyendo un texto dinámico donde múltiples hilos escriben al mismo tiempo (muy raro).

---

## 💡 Resumen Rápido para el Examen
* **Thread-Safety (Hilos):** Las clases legacy (`HashTable`, `Vector`, `StringBuffer`) están sincronizadas pero son lentas. Las modernas (`HashMap`, `ArrayList`, `StringBuilder`) no lo están pero son rápidas.
* **Orden de los Sets/Maps:** `Hash` = Desordenado y rápido; `Linked` = Orden de inserción; `Tree` = Ordenado por valor (lento).
* **Listas dinámicas:** `ArrayList` es rápido para leer y escribir al final; `LinkedList` es rápido si vas a insertar/eliminar constantemente al inicio o al medio de la lista.