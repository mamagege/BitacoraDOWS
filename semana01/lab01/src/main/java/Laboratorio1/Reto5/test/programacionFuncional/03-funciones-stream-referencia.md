# 📖 03: Catálogo Maestro de Funciones de Java Stream (Top 27)

> **Módulo:** Programación Funcional en Java  
> **Tema:** Referencia técnica completa de las funciones más utilizadas de `java.util.stream.Stream`, ordenadas por relevancia y frecuencia de uso en la industria.

---

## 📊 1. Tabla Resumen y Clasificación de Métodos

| # | Método | Tipo | Naturaleza | Complejidad | Propósito Principal |
| :-: | :--- | :--- | :--- | :--- | :--- |
| **1** | `filter()` | Intermedia | Stateless | $O(n)$ | Descarta elementos que no cumplen un predicado booleano. |
| **2** | `map()` | Intermedia | Stateless | $O(n)$ | Transforma cada elemento de tipo $T$ a tipo $R$ (1 a 1). |
| **3** | `collect()` | Terminal | Reducción Mutable | $O(n)$ | Acumula elementos en colecciones complejas (`List`, `Map`, `Set`). |
| **4** | `toList()` | Terminal | Reducción Directa | $O(n)$ | Retorna una lista inmutable (Java 16+). |
| **5** | `forEach()` | Terminal | Efecto Secundario | $O(n)$ | Ejecuta una acción final sobre cada elemento. |
| **6** | `flatMap()` | Intermedia | Stateless | $O(n \cdot m)$ | Aplana estructuras anidadas (transforma 1 a $N$ streams). |
| **7** | `findFirst()` | Terminal | Cortocircuito | $O(1) \dots O(n)$ | Obtiene el primer elemento envuelto en `Optional`. |
| **8** | `findAny()` | Terminal | Cortocircuito | $O(1) \dots O(n)$ | Obtiene cualquier elemento (ideal en streams paralelos). |
| **9** | `anyMatch()` | Terminal | Cortocircuito | $O(1) \dots O(n)$ | Retorna `true` si al menos un elemento cumple el predicado. |
| **10** | `allMatch()` | Terminal | Cortocircuito | $O(1) \dots O(n)$ | Retorna `true` solo si todos los elementos cumplen el predicado. |
| **11** | `noneMatch()` | Terminal | Cortocircuito | $O(1) \dots O(n)$ | Retorna `true` si ningún elemento cumple el predicado. |
| **12** | `reduce()` | Terminal | Reducción Inmutable| $O(n)$ | Pliega el stream a un único valor acumulado mediante un operador binario. |
| **13** | `count()` | Terminal | Reducción | $O(n)$ o $O(1)$ | Retorna la cantidad total de elementos en el stream. |
| **14** | `sorted()` | Intermedia | Stateful | $O(n \log n)$ | Ordena los elementos (orden natural o `Comparator`). |
| **15** | `distinct()` | Intermedia | Stateful | $O(n)$ | Elimina duplicados basándose en `equals()` y `hashCode()`. |
| **16** | `limit()` | Intermedia | Cortocircuito | $O(k)$ | Trunca el stream a un tamaño máximo de $k$ elementos. |
| **17** | `skip()` | Intermedia | Stateful | $O(k)$ | Descarta los primeros $n$ elementos del stream. |
| **18** | `peek()` | Intermedia | Stateless | $O(n)$ | Inspecciona elementos sin modificarlos (depuración). |
| **19** | `min()` | Terminal | Reducción | $O(n)$ | Retorna el elemento mínimo según un `Comparator`. |
| **20** | `max()` | Terminal | Reducción | $O(n)$ | Retorna el elemento máximo según un `Comparator`. |
| **21** | `mapToInt/Long/Double()` | Intermedia | Primitiva | $O(n)$ | Convierte a stream numérico primitivo evitando autoboxing. |
| **22** | `toArray()` | Terminal | Materialización | $O(n)$ | Convierte el stream a un arreglo tradicional `T[]`. |
| **23** | `takeWhile()` | Intermedia | Cortocircuito | $O(k)$ | Toma elementos mientras se cumpla la condición y se detiene (Java 9+). |
| **24** | `dropWhile()` | Intermedia | Stateful | $O(n)$ | Descarta elementos iniciales mientras se cumpla la condición (Java 9+). |
| **25** | `Stream.ofNullable()` | Creacional | Estática | $O(1)$ | Crea un stream seguro evitando `NullPointerException` (Java 9+). |
| **26** | `Stream.iterate()` | Creacional | Infinita/Acotada | $O(n)$ | Genera secuencias numéricas o de estados evaluadas de forma perezosa. |
| **27** | `mapMulti()` | Intermedia | Stateless | $O(n)$ | Alternativa de alto rendimiento a `flatMap` sin crear sub-streams (Java 16+). |

---

## 🔍 2. Detalle Exhaustivo de las 27 Funciones de Stream

---

### 1. `filter(Predicate<? super T> predicate)`
* **Tipo:** Intermedia (Lazy / Stateless).
* **Firma:** `Stream<T> filter(Predicate<? super T> predicate)`
* **¿Qué hace?** Evalúa cada elemento con una condición booleana. Si devuelve `true`, el elemento continúa por el pipeline; si es `false`, se descarta.
* **¿Para qué y cuándo usarlo?** Para extraer subconjuntos de datos según reglas de negocio (ej. usuarios activos, pagos mayores a $100, pedidos completados).
```java
List<Empleado> seniors = empleados.stream()
    .filter(emp -> emp.getExperienciaAnios() >= 5)
    .toList();
```

---

### 2. `map(Function<? super T, ? extends R> mapper)`
* **Tipo:** Intermedia (Lazy / Stateless).
* **Firma:** `<R> Stream<R> map(Function<? super T, ? extends R> mapper)`
* **¿Qué hace?** Aplica una transformación $1:1$ a cada elemento del flujo, convirtiendo un objeto de tipo $T$ a uno de tipo $R$.
* **¿Para qué y cuándo usarlo?** Para extraer atributos (ej. de `Usuario` a `String email`), transformar DTOs a entidades o realizar cálculos matemáticos por elemento.
```java
List<String> emails = usuarios.stream()
    .map(Usuario::getEmail)
    .map(String::toLowerCase)
    .toList();
```

---

### 3. `collect(Collector<? super T, A, R> collector)`
* **Tipo:** Terminal (Eager / Reducción Mutable).
* **Firma:** `<R, A> R collect(Collector<? super T, A, R> collector)`
* **¿Qué hace?** Empaqueta y acumula todos los elementos procesados en una estructura de datos destino (listas, sets, mapas, agrupaciones multinivel o cadenas).
* **¿Para qué y cuándo usarlo?** Es el método rey para materializar el resultado de un Stream en estructuras de Java Collections (`Collectors.toSet()`, `Collectors.groupingBy()`, etc.).
```java
Map<Categoria, List<Producto>> productosPorCategoria = inventario.stream()
    .collect(Collectors.groupingBy(Producto::getCategoria));
```

---

### 4. `toList()` (Java 16+)
* **Tipo:** Terminal (Eager).
* **Firma:** `List<T> toList()`
* **¿Qué hace?** Atajo directo para empaquetar los elementos en una **lista inmutable**.
* **¿Para qué y cuándo usarlo?** Reemplaza a `.collect(Collectors.toList())` cuando se busca una lista de solo lectura, con sintaxis más limpia y mejor rendimiento en memoria.
```java
List<String> nombresInmutables = clientes.stream()
    .map(Cliente::getNombre)
    .toList(); // Retorna List inmutable (Java 16+)
```

---

### 5. `forEach(Consumer<? super T> action)`
* **Tipo:** Terminal (Eager / Efecto Secundario).
* **Firma:** `void forEach(Consumer<? super T> action)`
* **¿Qué hace?** Itera cada elemento de la canalización y ejecuta una acción terminal que no retorna valor.
* **¿Para qué y cuándo usarlo?** Úsalo exclusivamente para interactuar con sistemas externos al final de un pipeline (imprimir por consola, enviar mensajes a un socket o registrar en un logger).
```java
pedidosPendientes.stream()
    .forEach(pedido -> log.info("Notificando despacho de orden: {}", pedido.getId()));
```

---

### 6. `flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)`
* **Tipo:** Intermedia (Lazy / Stateless).
* **Firma:** `<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)`
* **¿Qué hace?** Transforma cada elemento en un sub-stream y luego **aplana** todos los sub-streams resultantes en un único `Stream` continuo (relación $1:N$).
* **¿Para qué y cuándo usarlo?** Cuando tienes estructuras anidadas (ej. una lista de órdenes donde cada orden tiene una lista de ítems) y deseas procesar todos los ítems individuales a nivel global.
```java
// Estructura: List<Orden> -> cada Orden tiene List<Item>
List<Item> todosLosItems = ordenes.stream()
    .flatMap(orden -> orden.getItems().stream())
    .toList();
```

---

### 7. `findFirst()`
* **Tipo:** Terminal (Eager / Cortocircuito).
* **Firma:** `Optional<T> findFirst()`
* **¿Qué hace?** Retorna el primer elemento que alcanza el final del pipeline dentro de un contenedor `Optional<T>`. Se detiene de inmediato.
* **¿Para qué y cuándo usarlo?** Para búsquedas deterministas donde el orden de encuentro importa (ej. buscar el primer usuario disponible con ID único).
```java
Optional<Usuario> admin = usuarios.stream()
    .filter(Usuario::isAdmin)
    .findFirst();
```

---

### 8. `findAny()`
* **Tipo:** Terminal (Eager / Cortocircuito).
* **Firma:** `Optional<T> findAny()`
* **¿Qué hace?** Retorna cualquier elemento que coincida con el criterio sin garantizar el orden de aparición.
* **¿Para qué y cuándo usarlo?** En streams paralelos (`parallelStream()`), ya que es significativamente más rápido que `findFirst()` al permitir que el primer hilo que encuentre un resultado gane la carrera.
```java
Optional<Servidor> nodoDisponible = cluster.parallelStream()
    .filter(Servidor::isLibre)
    .findAny();
```

---

### 9. `anyMatch(Predicate<? super T> predicate)`
* **Tipo:** Terminal (Eager / Cortocircuito).
* **Firma:** `boolean anyMatch(Predicate<? super T> predicate)`
* **¿Qué hace?** Evalúa si **al menos un elemento** del stream satisface el predicado. En cuanto encuentra uno, detiene el procesamiento y retorna `true`.
* **¿Para qué y cuándo usarlo?** Para validaciones rápidas de existencia (ej. saber si existe al menos una factura impaga en una cuenta).
```java
boolean tieneFacturaVencida = facturas.stream()
    .anyMatch(Factura::isVencida);
```

---

### 10. `allMatch(Predicate<? super T> predicate)`
* **Tipo:** Terminal (Eager / Cortocircuito).
* **Firma:** `boolean allMatch(Predicate<? super T> predicate)`
* **¿Qué hace?** Retorna `true` si **todos los elementos** cumplen la condición. Si encuentra uno que devuelva `false`, corta de inmediato y retorna `false`.
* **¿Para qué y cuándo usarlo?** Para validaciones de integridad en lotes (ej. verificar que todos los estudiantes de un curso estén aprobados).
```java
boolean todosAprobados = estudiantes.stream()
    .allMatch(e -> e.getNotaFinal() >= 3.0);
```

---

### 11. `noneMatch(Predicate<? super T> predicate)`
* **Tipo:** Terminal (Eager / Cortocircuito).
* **Firma:** `boolean noneMatch(Predicate<? super T> predicate)`
* **¿Qué hace?** Retorna `true` únicamente si **ningún elemento** cumple el predicado. Si alguno lo cumple, retorna `false` de inmediato.
* **¿Para qué y cuándo usarlo?** Para certificar la ausencia de condiciones de riesgo (ej. certificar que ningún archivo subido contenga virus).
```java
boolean sistemaSeguro = archivosSubidos.stream()
    .noneMatch(Archivo::isMalicioso);
```

---

### 12. `reduce(T identity, BinaryOperator<T> accumulator)`
* **Tipo:** Terminal (Eager / Reducción Inmutable).
* **Firma:** `T reduce(T identity, BinaryOperator<T> accumulator)`
* **¿Qué hace?** Combina todos los elementos del flujo en un único valor acumulado mediante una función asociativa, partiendo de un valor identidad base.
* **¿Para qué y cuándo usarlo?** Para calcular sumatorias, multiplicaciones, concatenaciones o combinar objetos complejos en un único valor agregado.
```java
BigDecimal totalFacturado = pagos.stream()
    .map(Pago::getMonto)
    .reduce(BigDecimal.ZERO, BigDecimal::add);
```

---

### 13. `count()`
* **Tipo:** Terminal (Eager / Reducción).
* **Firma:** `long count()`
* **¿Qué hace?** Devuelve el número total de elementos presentes en el Stream después de aplicar los filtros.
* **¿Para qué y cuándo usarlo?** Para métricas y conteos de registros filtrados.
```java
long totalErroresCriticos = logsServidor.stream()
    .filter(log -> log.getNivel().equals("FATAL"))
    .count();
```

---

### 14. `sorted()` y `sorted(Comparator<? super T> comparator)`
* **Tipo:** Intermedia (Lazy / Stateful - Con Estado).
* **Firma:** `Stream<T> sorted(Comparator<? super T> comparator)`
* **¿Qué hace?** Ordena los elementos según su orden natural o usando un `Comparator` personalizado.
* **¿Para qué y cuándo usarlo?** Para ordenar reportes, rankings y listas de visualización. *Advertencia:* Requiere almacenar todo el stream en memoria antes de emitir elementos ($O(n \log n)$).
```java
List<Producto> masCarosPrimero = productos.stream()
    .sorted(Comparator.comparing(Producto::getPrecio).reversed())
    .toList();
```

---

### 15. `distinct()`
* **Tipo:** Intermedia (Lazy / Stateful).
* **Firma:** `Stream<T> distinct()`
* **¿Qué hace?** Filtra y elimina elementos repetidos basándose en la implementación de `equals()` y `hashCode()`.
* **¿Para qué y cuándo usarlo?** Para desduplicar identificadores, direcciones IP o valores repetidos en un flujo.
```java
List<String> tagsUnicos = articulos.stream()
    .flatMap(art -> art.getTags().stream())
    .distinct()
    .toList();
```

---

### 16. `limit(long maxSize)`
* **Tipo:** Intermedia (Lazy / Cortocircuito / Stateful).
* **Firma:** `Stream<T> limit(long maxSize)`
* **¿Qué hace?** Trunca el flujo para no emitir más de `maxSize` elementos.
* **¿Para qué y cuándo usarlo?** Para paginación (tamaño de página) o para obtener el "Top $N$" de un ranking.
```java
List<Usuario> top3Puntajes = jugadores.stream()
    .sorted(Comparator.comparing(Usuario::getPuntaje).reversed())
    .limit(3)
    .toList();
```

---

### 17. `skip(long n)`
* **Tipo:** Intermedia (Lazy / Stateful).
* **Firma:** `Stream<T> skip(long n)`
* **¿Qué hace?** Omite o descarta los primeros `n` elementos del stream y emite los restantes.
* **¿Para qué y cuándo usarlo?** Combinado con `limit()` para implementar **paginación de datos** (`.skip((pagina - 1) * pageSize).limit(pageSize)`).
```java
int pagina = 2;
int tamanioPagina = 10;

List<Auditoria> pagina2 = auditorias.stream()
    .skip((long) (pagina - 1) * tamanioPagina)
    .limit(tamanioPagina)
    .toList();
```

---

### 18. `peek(Consumer<? super T> action)`
* **Tipo:** Intermedia (Lazy / Stateless).
* **Firma:** `Stream<T> peek(Consumer<? super T> action)`
* **¿Qué hace?** Realiza una acción de sólo lectura sobre cada elemento a medida que pasa por el pipeline sin consumirlo ni mutarlo.
* **¿Para qué y cuándo usarlo?** Exclusivamente para **depuración (*Debugging*)** o métricas de trazabilidad interna en desarrollo.
```java
List<String> codigosValidados = codigos.stream()
    .filter(c -> c.startsWith("PROD-"))
    .peek(c -> System.out.println("Paso filtro: " + c)) // Depuración
    .map(String::trim)
    .toList();
```

---

### 19. `min(Comparator<? super T> comparator)`
* **Tipo:** Terminal (Eager / Reducción).
* **Firma:** `Optional<T> min(Comparator<? super T> comparator)`
* **¿Qué hace?** Encuentra el elemento con el menor valor según el `Comparator` provisto.
* **¿Para qué y cuándo usarlo?** Para obtener el registro más bajo (ej. el producto más económico o la transacción más antigua).
```java
Optional<Producto> productoMasBarato = catalogo.stream()
    .min(Comparator.comparing(Producto::getPrecio));
```

---

### 20. `max(Comparator<? super T> comparator)`
* **Tipo:** Terminal (Eager / Reducción).
* **Firma:** `Optional<T> max(Comparator<? super T> comparator)`
* **¿Qué hace?** Encuentra el elemento con el mayor valor según el `Comparator` provisto.
* **¿Para qué y cuándo usarlo?** Para obtener el pico más alto (ej. el salario máximo o el mayor puntaje).
```java
Optional<Empleado> mejorPagado = planilla.stream()
    .max(Comparator.comparing(Empleado::getSalario));
```

---

### 21. `mapToInt()`, `mapToLong()`, `mapToDouble()`
* **Tipo:** Intermedia (Lazy / Stateless).
* **Firma:** `IntStream mapToInt(ToIntFunction<? super T> mapper)`
* **¿Qué hace?** Transforma el stream de objetos a un stream primitivo especializado (`IntStream`, `LongStream`, `DoubleStream`).
* **¿Para qué y cuándo usarlo?** Para evitar el costo de memoria del empaquetado (*Autoboxing*) y acceder a métodos matemáticos directos como `.sum()`, `.average()`, `.summaryStatistics()`.
```java
double promedioNotas = estudiantes.stream()
    .mapToDouble(Estudiante::getNotaFinal)
    .average()
    .orElse(0.0);
```

---

### 22. `toArray(IntFunction<A[]> generator)`
* **Tipo:** Terminal (Eager / Materialización).
* **Firma:** `<A> A[] toArray(IntFunction<A[]> generator)`
* **¿Qué hace?** Convierte el resultado del stream en un array tradicional de Java fuertemente tipado.
* **¿Para qué y cuándo usarlo?** Para interoperar con librerías legadas o APIs que requieren arrays `T[]` nativos.
```java
String[] arrayNombres = listaNombres.stream()
    .filter(n -> n.length() > 3)
    .toArray(String[]::new);
```

---

### 23. `takeWhile(Predicate<? super T> predicate)` (Java 9+)
* **Tipo:** Intermedia (Lazy / Cortocircuito).
* **Firma:** `Stream<T> takeWhile(Predicate<? super T> predicate)`
* **¿Qué hace?** Deja pasar elementos mientras la condición sea `true`. En el **primer elemento** que devuelva `false`, corta el stream por completo (incluso si hay elementos válidos más adelante).
* **¿Para qué y cuándo usarlo?** Ideal para flujos de datos que ya se encuentran ordenados (ej. obtener logs de hoy hasta topar con un log de ayer).
```java
// Suponiendo una lista ordenada ascendentemente por puntaje:
List<Integer> notasMenoresA5 = notasOrdenadas.stream()
    .takeWhile(nota -> nota < 5)
    .toList();
```

---

### 24. `dropWhile(Predicate<? super T> predicate)` (Java 9+)
* **Tipo:** Intermedia (Lazy / Stateful).
* **Firma:** `Stream<T> dropWhile(Predicate<? super T> predicate)`
* **¿Qué hace?** Descarta elementos mientras la condición sea `true`. A partir del **primer elemento** que devuelva `false`, emite ese elemento y **todos los restantes** sin importar su valor.
* **¿Para qué y cuándo usarlo?** Para saltear encabezados o secuencias iniciales predecibles en flujos ordenados.
```java
List<Integer> notasAprobadas = notasOrdenadas.stream()
    .dropWhile(nota -> nota < 3) // Salta todos los reprobados iniciales
    .toList();
```

---

### 25. `Stream.ofNullable(T t)` (Java 9+)
* **Tipo:** Creacional (Estática / Stateless).
* **Firma:** `static <T> Stream<T> ofNullable(T t)`
* **¿Qué hace?** Retorna un stream con un único elemento si el objeto no es nulo, o un `Stream.empty()` si el objeto es `null`.
* **¿Para qué y cuándo usarlo?** Para prevenir `NullPointerException` al crear streams a partir de valores opcionales o datos potencialmente nulos.
```java
String configuracion = obtenerConfiguracionOpcional(); // Puede ser null
List<String> configs = Stream.ofNullable(configuracion)
    .map(String::toUpperCase)
    .toList();
```

---

### 26. `Stream.iterate(seed, predicate, hasNext)` (Java 9+)
* **Tipo:** Creacional (Estática / Lazy).
* **Firma:** `static <T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)`
* **¿Qué hace?** Genera un stream secuencial ordenado partiendo de una semilla (`seed`), aplicando una condición de parada y una función de avance (equivalente a un bucle `for(int i=0; i<10; i++)`).
* **¿Para qué y cuándo usarlo?** Para generar secuencias matemáticas, fechas consecutivas o rangos con saltos personalizados.
```java
// Genera números pares del 0 al 20: [0, 2, 4, 6, ..., 20]
List<Integer> pares = Stream.iterate(0, n -> n <= 20, n -> n + 2)
    .toList();
```

---

### 27. `mapMulti(BiConsumer<? super T, ? super Consumer<R>> mapper)` (Java 16+)
* **Tipo:** Intermedia (Lazy / Stateless).
* **Firma:** `<R> Stream<R> mapMulti(BiConsumer<? super T, ? super Consumer<R>> mapper)`
* **¿Qué hace?** Reemplaza cada elemento del stream por 0, 1 o múltiples elementos, emitiéndolos directamente a un consumidor sin la penalización de crear instancias de `Stream` intermedias como lo hace `flatMap`.
* **¿Para qué y cuándo usarlo?** En escenarios de altísimo rendimiento donde `flatMap` genera demasiada presión sobre el recolector de basura (*Garbage Collector*).
```java
List<Integer> numerosDuplicadosYTriplicados = List.of(1, 2, 3).stream()
    .<Integer>mapMulti((numero, downstream) -> {
        downstream.accept(numero * 2);
        downstream.accept(numero * 3);
    })
    .toList(); // Resultado: [2, 3, 4, 6, 6, 9]
```

---

## 🎁 3. Anexo: Operaciones Avanzadas con `Collectors`

Dentro del método terminal `.collect()`, la clase de utilidad `java.util.stream.Collectors` provee los acumuladores más potentes:

1. **`Collectors.groupingBy(Function classifier)`**: Agrupa elementos en un `Map<K, List<V>>`.
2. **`Collectors.partitioningBy(Predicate predicate)`**: Divide la colección en dos grupos exactos en un `Map<Boolean, List<V>>` (`true` y `false`).
3. **`Collectors.toMap(keyMapper, valueMapper)`**: Transforma una lista de entidades en un `Map<K, V>`.
4. **`Collectors.joining(delimiter, prefix, suffix)`**: Concatena cadenas con delimitadores y prefijos/sufijos en una sola línea.
5. **`Collectors.summarizingDouble/Int/Long()`**: Calcula en una sola pasada el conteo, suma, mínimo, promedio y máximo (`DoubleSummaryStatistics`).
