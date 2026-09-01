# 🎮 SEMANA No 2 — Bitácora Pokémon: Programación Funcional con Java Streams API

> **DOSW COMPANY** — *Taller #2: Ejercicio Pokémon · Programación Funcional*  
> **Escuela Colombiana de Ingeniería Julio Garavito**  
> **Tecnología:** Java 21+ / Java 25 + Streams API + Lambdas & Method References  
> **Ubicación:** `semana02/taller2/`

---

## 👨‍💻 Datos de Entrenador
- **Nombre y Apellido:** Nadia
- **Asignatura:** Desarrollo y Operaciones de Software (DOSW)
- **Entrega:** Individual

---

## 🧭 1. Resumen y Principios Arquitectónicos

Este taller consolida las competencias de **Programación Funcional Declarativa**, manipulando colecciones inmutables, agregaciones complejas y flujos de datos sin recurrir a ciclos imperativos tradicionales (`for`, `while`, `do-while`) ni ordenamientos manuales.

### 🛡️ Buenas Prácticas y Patrones Aplicados:
* **Clean Code:** Nombres con intención clara, métodos pequeños con responsabilidad única, ausencia de efectos secundarios (*side-effects* no deseados).
* **SOLID:**
  * **S (Single Responsibility):** Desacoplamiento estricto entre modelos de dominio (`Pokemon`, `Entrenador`), lógica de negocio y presentación de resultados.
  * **O (Open/Closed):** Extensibilidad mediante interfaces funcionales estándar (`Predicate<T>`, `Function<T, R>`, `Comparator<T>`, `BinaryOperator<T>`).
  * **D (Dependency Inversion):** Los flujos dependen de abstracciones funcionales, no de implementaciones concretas.
* **Patrón Pipeline Funcional:** Separación clara entre operaciones intermedias perezosas (*Lazy*) y operaciones terminales ansiosas (*Eager*).
* **Agile & XP:** Simplicidad radical (KISS), código autocontenido, testing continuo de flujos mediante ejecuciones de consola verificadas.

---

## ⚡ RETOS ESPECIALES (BONOS)

### 1. ⚡ Reto Legendario — Method References con Azúcar Sintáctico (+0.5 pts)
Se utilizó azúcar sintáctico de referencias a métodos (`::`) en lugar de lambdas convencionales a lo largo de múltiples retos:
* **[`Ejercicio01.java`](pokemon/Ejercicio01.java):** `.map(Pokemon::getNombre)` en vez de `p -> p.getNombre()`.
* **[`Ejercicio02.java`](pokemon/Ejercicio02.java):** `.map(String::toUpperCase)` en vez de `s -> s.toUpperCase()`.
* **[`Ejercicio03.java`](pokemon/Ejercicio03.java):** `.reduce(0, Integer::sum)` en vez de `(a, b) -> a + b`.
* **[`Ejercicio04.java`](pokemon/Ejercicio04.java):** `Comparator.comparingInt(Pokemon::getNivel)`.
* **[`Ejercicio08.java`](pokemon/Ejercicio08.java):** `.filter(Pokemon::isPuedeEvolucionar)`.
* **[`Ejercicio10.java`](pokemon/Ejercicio10.java):** `.map(Pokemon::getNombre)`.
* **[`Ejercicio11.java`](pokemon/Ejercicio11.java):** `.mapToDouble(Pokemon::getPoderCombate)`.
* **[`Ejercicio12.java`](pokemon/Ejercicio12.java):** `Comparator.comparingDouble(Pokemon::getPoderCombate)`.
* **[`Ejercicio13.java`](pokemon/Ejercicio13.java):** `groupingBy(Pokemon::getTipo, ..., mapping(Pokemon::getNombre, ...))`.
* **[`Ejercicio14.java`](pokemon/Ejercicio14.java):** `groupingBy(Pokemon::getRegion, ..., mapping(Pokemon::getNombre, ...))`.
* **[`Ejercicio15.java`](pokemon/Ejercicio15.java):** `Comparator.comparingInt(Entrenador::getMedallas)`.
* **[`Ejercicio20.java`](pokemon/Ejercicio20.java):** `groupingBy(Pokemon::getTipo)`, `groupingBy(Pokemon::getRegion)`, `filter(Pokemon::isLegendario)`, `mapToInt(Pokemon::getNivel)`.

---

### 2. ✨ Reto Shiny — Buenas Prácticas de Commits (Conventional Commits) (+0.5 pts)
Estrategia semántica de versionamiento con Git Flow:

```bash
feat: estructuracion inicial de modelos Pokemon y Entrenador
feat: reto pokemon tipo fuego y pokedex gritona (ejercicios 01 y 02)
feat: implementacion de reduccion y comparadores (ejercicios 03 al 05)
feat: desduplicacion, ordenamiento y evoluciones (ejercicios 06 al 08)
feat: manipulacion de objetos complejos y agrupaciones (ejercicios 09 al 14)
feat: alto mando y rankings de entrenadores (ejercicios 15 al 19)
feat: pokedex analitica integral (ejercicio 20)
feat: reto mewtwo integracion funcional total
refactor: optimizacion streams y method references
docs: documentacion tecnica y bitacora en README
```

---

### 3. 🧬 Reto Mewtwo — Pipeline Funcional Integrado (+1.0 pt)
* **Archivo:** [`RetoMewtwo.java`](bonos/RetoMewtwo.java)
* **Objetivo:** Integrar en una **única solución funcional unificada**: `filter()`, `map()`, `sorted()`, `groupingBy()` y `reduce()`.
* **Enunciado:** *"Evaluación Táctica de Élite Regional"*: Filtrar Pokémon competitivos (nivel $\ge 50$), mapear a un registro inmutable `PerfilTactico` calculando su Coeficiente de Combate Efectivo ($PC + Nivel \times 2$), ordenar descendentemente, agrupar por región y reducir funcionalmente (`Collectors.reducing` con `Double::sum`) para totalizar el poder acumulado de cada región.

**Código implementado:**
```java
Map<String, Double> poderTacticoRegional = pokedexGlobal.stream()
        .filter(p -> p.getNivel() >= 50)
        .map(p -> new PerfilTactico(
                p.getNombre(),
                p.getRegion(),
                p.getTipo(),
                p.getPoderCombate() + (p.getNivel() * 2.0)
        ))
        .sorted(Comparator.comparingDouble(PerfilTactico::coeficienteEfectivo).reversed())
        .collect(Collectors.groupingBy(
                PerfilTactico::region,
                Collectors.reducing(0.0, PerfilTactico::coeficienteEfectivo, Double::sum)
        ));
```

**Evidencia de Ejecución:**
```text
📊 Resumen de Poder Táctico por Región (Élite Nivel >= 50):
 - Región Sinnoh   : 1.428,0 pts de poder efectivo acumulado
 - Región Hoenn    : 1.535,0 pts de poder efectivo acumulado
 - Región Johto    : 1.510,0 pts de poder efectivo acumulado
 - Región Kanto    : 1.560,0 pts de poder efectivo acumulado

👑 Campeón Supremo Absoluto (vía reduce directo):
   Mewtwo (Kanto) - Coeficiente: 830,0
```

---

## 📊 2. Catálogo y Matriz de Métodos de Streams Usados

| Método Stream | Categoría | Interfaz Funcional | Complejidad Temporal | Propósito en el Taller |
| :--- | :---: | :---: | :---: | :--- |
| `filter(Predicate<T>)` | Intermedia (Lazy) | `Predicate<T>` | $O(N)$ | Filtrar por tipo, nivel, estado evolutivo, medallas y legendarios. |
| `map(Function<T, R>)` | Intermedia (Lazy) | `Function<T, R>` | $O(N)$ | Proyectar atributos (ej. nombres a mayúsculas, extraer solo nombres). |
| `mapToDouble()` / `mapToInt()` | Intermedia (Lazy) | `ToDoubleFunction<T>` | $O(N)$ | Evita *autoboxing* al calcular sumatorias y promedios numéricos. |
| `sorted()` / `sorted(Comparator)` | Intermedia Stateful | `Comparator<T>` | $O(N \log N)$ | Ordenar alfabéticamente o por criterios múltiples ponderados. |
| `distinct()` | Intermedia Stateful | Basada en `equals/hashCode` | $O(N)$ | Eliminar duplicados conservando el orden de aparición. |
| `limit(long maxSize)` | Intermedia Cortocircuito | Control de flujo | $O(1) \dots O(N)$ | Acotar el flujo para rankings Top 3 y Top 5. |
| `reduce(identity, accumulator)` | Terminal (Folding) | `BinaryOperator<T>` | $O(N)$ | Plegar elementos para calcular sumatorias o acumulación de poder. |
| `max(Comparator<T>)` | Terminal (Reducción) | `Comparator<T>` | $O(N)$ | Encontrar el elemento óptimo (Pokémon Alfa, Campeón Regional). |
| `count()` | Terminal (Agregación) | N/A | $O(N)$ | Contar el número de elementos que superan un umbral. |
| `average()` | Terminal (Agregación) | N/A | $O(N)$ | Obtener el promedio de una secuencia numérica de forma segura (`OptionalDouble`). |
| `collect(groupingBy(...))` | Terminal (Reducción Mutable) | `Collector` | $O(N)$ | Particionar y agrupar colecciones por tipo, región o categorías. |

---

## 🏆 3. Detalle de los 20 Ejercicios de la Bitácora Pokémon

---

### Nivel 1: Entrenador Novato

#### ### Ejercicio 01 — Pokémon Tipo Fuego
* **Archivo:** [`Ejercicio01.java`](pokemon/Ejercicio01.java)
* **Funciones Stream:** `filter()`, `map()`, `toList()`
* **Enunciado:** Dada una lista de Pokémon con nombre y tipo, obtener únicamente aquellos cuyo tipo sea Fuego.

**Código implementado:**
```java
List<String> tipoFuego = pokemones.stream()
        .filter(p -> "Fuego".equalsIgnoreCase(p.getTipo()))
        .map(Pokemon::getNombre)
        .toList();
```
**Captura de ejecución:**
```text
=== Reto #01: Pokémon Tipo Fuego ===
[Charmander, Vulpix, Flareon]
```
**Explicación:** Se aplica `filter` con un predicado sobre el tipo ignorando mayúsculas/minúsculas y `map` con method reference para proyectar el nombre.

---

#### ### Ejercicio 02 — Pokédex Gritona
* **Archivo:** [`Ejercicio02.java`](pokemon/Ejercicio02.java)
* **Funciones Stream:** `map(String::toUpperCase)`, `collect(Collectors.joining())`
* **Enunciado:** Transformar todos los nombres de Pokémon a mayúsculas.

**Código implementado:**
```java
String resultado = pokemones.stream()
        .map(String::toUpperCase)
        .collect(Collectors.joining(", "));
```
**Captura de ejecución:**
```text
=== Reto #02: Pokédex Gritona ===
PIKACHU, CHARMANDER, SQUIRTLE, BULBASAUR
```
**Explicación:** Se transforma cada cadena a mayúsculas de manera inmutable mediante `String::toUpperCase` y se une con delimitador.

---

#### ### Ejercicio 03 — Poder Total del Equipo
* **Archivo:** [`Ejercicio03.java`](pokemon/Ejercicio03.java)
* **Funciones Stream:** `reduce(0, Integer::sum)`
* **Enunciado:** Dada una lista de niveles de Pokémon, calcular la suma total de niveles del equipo.

**Código implementado:**
```java
int sumaTotal = niveles.stream()
        .reduce(0, Integer::sum);
```
**Captura de ejecución:**
```text
=== Reto #03: Poder Total del Equipo ===
Suma total de niveles: 300
```
**Explicación:** `reduce` realiza un plegado acumulativo con identidad 0 y la operación binaria `Integer::sum`.

---

#### ### Ejercicio 04 — Pokémon Alfa
* **Archivo:** [`Ejercicio04.java`](pokemon/Ejercicio04.java)
* **Funciones Stream:** `max(Comparator.comparingInt(Pokemon::getNivel))`
* **Enunciado:** Encontrar el Pokémon con el nivel más alto dentro del equipo.

**Código implementado:**
```java
equipo.stream()
        .max(Comparator.comparingInt(Pokemon::getNivel))
        .ifPresent(alfa -> System.out.println("Pokémon Alfa: " + alfa.getNombre() + " (nivel " + alfa.getNivel() + ")"));
```
**Captura de ejecución:**
```text
Pokémon Alfa: Snorlax (nivel 90)
```
**Explicación:** Evalúa el máximo en tiempo $O(N)$ usando un comparador basado en la propiedad `nivel`.

---

#### ### Ejercicio 05 — Pokémon Legendarios
* **Archivo:** [`Ejercicio05.java`](pokemon/Ejercicio05.java)
* **Funciones Stream:** `filter()`, `count()`, `map()`, `collect()`
* **Enunciado:** Contar cuántos Pokémon del equipo tienen nivel superior a 80 y listar sus nombres.

**Código implementado:**
```java
List<Pokemon> legendarios = equipo.stream()
        .filter(p -> p.getNivel() > 80)
        .toList();
long cantidad = legendarios.stream().count();
```
**Captura de ejecución:**
```text
Pokémon con nivel > 80: 3
(Mewtwo, Dragonite, Mew)
```
**Explicación:** Filtra los elementos con nivel $> 80$ y cuenta las ocurrencias de forma declarativa.

---

### Nivel 2: Entrenador Intermedio

#### ### Ejercicio 06 — Pokédex Sin Duplicados
* **Archivo:** [`Ejercicio06.java`](pokemon/Ejercicio06.java)
* **Funciones Stream:** `distinct()`, `toList()`
* **Enunciado:** Dada una lista de Pokémon con elementos repetidos, generar una nueva colección donde cada Pokémon aparezca una sola vez.

**Código implementado:**
```java
List<String> sinDuplicados = pokemonesConDuplicados.stream()
        .distinct()
        .toList();
```
**Captura de ejecución:**
```text
=== Reto #06: Pokédex Sin Duplicados ===
[Pikachu, Charmander, Squirtle, Mewtwo]
```
**Explicación:** `distinct()` es una operación intermedia stateful que elimina duplicados preservando el orden de inserción original.

---

#### ### Ejercicio 07 — Orden del Profesor Oak
* **Archivo:** [`Ejercicio07.java`](pokemon/Ejercicio07.java)
* **Funciones Stream:** `sorted()`, `toList()`
* **Enunciado:** El Profesor Oak quiere su Pokédex organizada. Ordenar alfabéticamente los nombres de los Pokémon.

**Código implementado:**
```java
List<String> ordenados = pokemones.stream()
        .sorted()
        .toList();
```
**Captura de ejecución:**
```text
=== Reto #07: Orden del Profesor Oak ===
[Abra, Bulbasaur, Charmander, Mewtwo, Pikachu, Squirtle]
```
**Explicación:** Ordena las cadenas según el orden natural alfabético ($O(N \log N)$).

---

#### ### Ejercicio 08 — Evoluciones Preparadas
* **Archivo:** [`Ejercicio08.java`](pokemon/Ejercicio08.java)
* **Funciones Stream:** `filter(Pokemon::isPuedeEvolucionar)`, `map(Pokemon::getNombre)`
* **Enunciado:** Dada una lista de Pokémon que incluye si pueden evolucionar, obtener únicamente los que estén listos para evolucionar.

**Código implementado:**
```java
List<String> listosParaEvolucionar = pokemones.stream()
        .filter(Pokemon::isPuedeEvolucionar)
        .map(Pokemon::getNombre)
        .toList();
```
**Captura de ejecución:**
```text
Listos para evolucionar:
[Pikachu, Charmander, Squirtle]
```
**Explicación:** Filtra con referencia al método booleano `Pokemon::isPuedeEvolucionar` y proyecta el nombre.

---

### Nivel 3: Líder de Gimnasio

#### ### Ejercicio 09 — Equipo Élite
* **Archivo:** [`Ejercicio09.java`](pokemon/Ejercicio09.java)
* **Funciones Stream:** `filter(p -> p.getPoderCombate() > 500)`, `map()`, `toList()`
* **Enunciado:** Mostrar únicamente los Pokémon cuyo poderCombate sea superior a 500.

**Código implementado:**
```java
List<String> elite = equipo.stream()
        .filter(p -> p.getPoderCombate() > 500)
        .map(p -> p.getNombre() + "(" + (int) p.getPoderCombate() + ")")
        .toList();
```
**Captura de ejecución:**
```text
Equipo Élite (PC > 500):
[Mewtwo(680), Dragonite(530), Charizard(610)]
```
**Explicación:** Evalúa la propiedad numérica de combate y genera la representación solicitada.

---

#### ### Ejercicio 10 — Pokédex Compacta
* **Archivo:** [`Ejercicio10.java`](pokemon/Ejercicio10.java)
* **Funciones Stream:** `map(Pokemon::getNombre)`, `collect(Collectors.toList())`
* **Enunciado:** Generar una lista que contenga únicamente los nombres de todos los Pokémon del equipo.

**Código implementado:**
```java
List<String> nombres = equipo.stream()
        .map(Pokemon::getNombre)
        .collect(Collectors.toList());
```
**Captura de ejecución:**
```text
=== Reto #10: Pokédex Compacta ===
[Pikachu, Mewtwo, Dragonite, Squirtle, Gengar, Charizard]
```
**Explicación:** Aplica un mapeo $1:1$ para extraer la propiedad nombre de los objetos del dominio.

---

#### ### Ejercicio 11 — Poder Promedio
* **Archivo:** [`Ejercicio11.java`](pokemon/Ejercicio11.java)
* **Funciones Stream:** `mapToDouble(Pokemon::getPoderCombate)`, `average()`
* **Enunciado:** Calcular el promedio de poderCombate de todos los Pokémon del equipo.

**Código implementado:**
```java
double promedio = equipo.stream()
        .mapToDouble(Pokemon::getPoderCombate)
        .average()
        .orElse(0.0);
```
**Captura de ejecución:**
```text
Poder de combate promedio: 474.17
```
**Explicación:** Convierte el flujo a `DoubleStream` primitivo para calcular el promedio con precisión y sin sobrecosto de *boxing*.

---

#### ### Ejercicio 12 — Campeón Regional
* **Archivo:** [`Ejercicio12.java`](pokemon/Ejercicio12.java)
* **Funciones Stream:** `max(Comparator.comparingDouble(Pokemon::getPoderCombate))`
* **Enunciado:** Obtener el Pokémon con mayor poderCombate de toda la lista.

**Código implementado:**
```java
lista.stream()
        .max(Comparator.comparingDouble(Pokemon::getPoderCombate))
        .ifPresent(c -> System.out.println("Campeón: " + c.getNombre() + " con PC: " + (int) c.getPoderCombate()));
```
**Captura de ejecución:**
```text
Campeón: Mewtwo con PC: 680
```
**Explicación:** Reducción terminal que identifica el objeto con el valor máximo de PC.

---

#### ### Ejercicio 13 — Organizar por Tipo
* **Archivo:** [`Ejercicio13.java`](pokemon/Ejercicio13.java)
* **Funciones Stream:** `collect(Collectors.groupingBy(Pokemon::getTipo, ..., mapping(...)))`
* **Enunciado:** Agrupar todos los Pokémon por su tipo y mostrar el listado por grupo.

**Código implementado:**
```java
Map<String, List<String>> agrupadosPorTipo = pokemones.stream()
        .collect(Collectors.groupingBy(
                Pokemon::getTipo,
                LinkedHashMap::new,
                Collectors.mapping(Pokemon::getNombre, Collectors.toList())
        ));
```
**Captura de ejecución:**
```text
=== Reto #13: Organizar por Tipo ===
Agua: [Squirtle, Psyduck]
Fuego: [Charmander, Vulpix]
Planta: [Bulbasaur]
```
**Explicación:** Utiliza un *downstream collector* `mapping` para agrupar únicamente los nombres por cada clave de tipo.

---

#### ### Ejercicio 14 — Organizar por Región
* **Archivo:** [`Ejercicio14.java`](pokemon/Ejercicio14.java)
* **Funciones Stream:** `collect(Collectors.groupingBy(Pokemon::getRegion, ..., mapping(...)))`
* **Enunciado:** Agrupar los Pokémon según su región de origen.

**Código implementado:**
```java
Map<String, List<String>> agrupadosPorRegion = pokemones.stream()
        .collect(Collectors.groupingBy(
                Pokemon::getRegion,
                LinkedHashMap::new,
                Collectors.mapping(Pokemon::getNombre, Collectors.toList())
        ));
```
**Captura de ejecución:**
```text
=== Reto #14: Organizar por Región ===
Kanto: [Pikachu, Charmander]
Johto: [Chikorita, Totodile]
Hoenn: [Torchic]
Sinnoh: [Piplup]
```
**Explicación:** Segmenta la colección en un mapa asociativo preservando el orden mediante `LinkedHashMap`.

---

### Nivel 4: Alto Mando

#### ### Ejercicio 15 — Maestro de Gimnasios
* **Archivo:** [`Ejercicio15.java`](pokemon/Ejercicio15.java)
* **Funciones Stream:** `max(Comparator.comparingInt(Entrenador::getMedallas))`
* **Enunciado:** Dado un listado de entrenadores con sus medallas, encontrar el entrenador con más medallas.

**Código implementado:**
```java
entrenadores.stream()
        .max(Comparator.comparingInt(Entrenador::getMedallas))
        .ifPresent(campeon -> {
            System.out.println("Campeón de gimnasios: " + campeon.getNombre());
            System.out.println("Medallas obtenidas: " + campeon.getMedallas());
        });
```
**Captura de ejecución:**
```text
Campeón de gimnasios: Gary
Medallas obtenidas: 10
```
**Explicación:** Determina el entrenador con mayor número de medallas obtenidas en sus gimnasios.

---

#### ### Ejercicio 16 — Entrenadores Experimentados
* **Archivo:** [`Ejercicio16.java`](pokemon/Ejercicio16.java)
* **Funciones Stream:** `filter(e -> e.getMedallas() > 5)`, `map()`, `toList()`
* **Enunciado:** Mostrar únicamente los entrenadores que posean más de 5 medallas.

**Código implementado:**
```java
List<String> experimentados = entrenadores.stream()
        .filter(e -> e.getMedallas() > 5)
        .map(e -> e.getNombre() + "(" + e.getMedallas() + ")")
        .toList();
```
**Captura de ejecución:**
```text
Entrenadores con > 5 medallas:
[Ash(8), Brock(6), Gary(10), Dawn(7)]
```
**Explicación:** Filtra según la regla de negocio ($medallas > 5$) y formatea la salida legible.

---

#### ### Ejercicio 17 — Equipo Más Poderoso
* **Archivo:** [`Ejercicio17.java`](pokemon/Ejercicio17.java)
* **Funciones Stream:** `mapToDouble()`, `sum()`, `max()`
* **Enunciado:** Calcular cuál entrenador tiene la suma total de poderCombate más alta entre todos sus Pokémon.

**Código implementado:**
```java
entrenadores.stream()
        .max(Comparator.comparingDouble(e -> e.getEquipo().stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .sum()))
        .ifPresent(p -> System.out.println("Entrenador más poderoso: " + p.getNombre()));
```
**Captura de ejecución:**
```text
Entrenador más poderoso: Gary
Poder acumulado del equipo: 2340
```
**Explicación:** Realiza agregación anidada sobre la lista interna del equipo sumando los PC con `mapToDouble` y `sum()`.

---

### Nivel 5: Campeón de la Liga Pokémon DOSW

#### ### Ejercicio 18 — Top 5 Pokémon Más Fuertes
* **Archivo:** [`Ejercicio18.java`](pokemon/Ejercicio18.java)
* **Funciones Stream:** `sorted(Comparator.reversed())`, `limit(5)`
* **Enunciado:** Generar un ranking de los cinco Pokémon con mayor poderCombate de toda la Pokédex.

**Código implementado:**
```java
pokedex.stream()
        .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
        .limit(5)
        .forEach(p -> System.out.println("#" + ranking.getAndIncrement() + " " + p.getNombre() + " – PC: " + (int) p.getPoderCombate()));
```
**Captura de ejecución:**
```text
=== Reto #18: Top 5 Pokémon Más Fuertes ===
#1 Mewtwo – PC: 680
#2 Charizard – PC: 610
#3 Dragonite – PC: 530
#4 Gengar – PC: 495
#5 Pikachu – PC: 320
```
**Explicación:** Ordena de mayor a menor PC y utiliza `limit(5)` para cortocircuitar el pipeline tras procesar 5 elementos.

---

#### ### Ejercicio 19 — Top 3 Entrenadores
* **Archivo:** [`Ejercicio19.java`](pokemon/Ejercicio19.java)
* **Funciones Stream:** `sorted()` con comparador multi-criterio + `limit(3)`
* **Enunciado:** Generar un ranking de los 3 mejores entrenadores considerando: 1° más medallas, 2° mayor poder acumulado, 3° orden alfabético como criterio de desempate.

**Código implementado:**
```java
Comparator<Entrenador> rankingComparator = Comparator
        .comparingInt(Entrenador::getMedallas).reversed()
        .thenComparing(Comparator.comparingDouble(Entrenador::calcularPoderTotal).reversed())
        .thenComparing(Entrenador::getNombre);

entrenadores.stream()
        .sorted(rankingComparator)
        .limit(3)
        .forEach(e -> ...);
```
**Captura de ejecución:**
```text
=== Reto #19: Top 3 Entrenadores ===
#1 Gary – 10 medallas, PC: 2340
#2 Ash – 8 medallas, PC: 1850
#3 Dawn – 7 medallas, PC: 2100
```
**Explicación:** Se encadenan comparadores de ordenación compuesta (`thenComparing`) garantizando desempates deterministas y limpios.

---

#### ### Ejercicio 20 — Pokédex Analítica
* **Archivo:** [`Ejercicio20.java`](pokemon/Ejercicio20.java)
* **Funciones Stream:** `groupingBy()`, `counting()`, `filter()`, `mapToInt()`, `average()`, `max()`
* **Enunciado:** Construir una estructura que muestre: cantidad de Pokémon por tipo, por región, cantidad de legendarios, promedio de nivel y el Pokémon más fuerte. Todo usando únicamente Streams.

**Código implementado:**
```java
Map<String, Long> porTipo = pokedex.stream().collect(Collectors.groupingBy(Pokemon::getTipo, Collectors.counting()));
Map<String, Long> porRegion = pokedex.stream().collect(Collectors.groupingBy(Pokemon::getRegion, Collectors.counting()));
long legendarios = pokedex.stream().filter(Pokemon::isLegendario).count();
double promedioNivel = pokedex.stream().mapToInt(Pokemon::getNivel).average().orElse(0.0);
Pokemon masFuerte = pokedex.stream().max(Comparator.comparingDouble(Pokemon::getPoderCombate)).orElse(null);
```
**Captura de ejecución:**
```text
=== Reto #20: Pokédex Analítica ===
Por tipo: {Psíquico=2, Dragón=1, Agua=3, Fuego=4}
Por región: {Hoenn=1, Johto=3, Kanto=6}
Legendarios: 3
Promedio niv: 61.4
Más fuerte: Mewtwo (PC: 680)
```
**Explicación:** Suite analítica integral que combina múltiples operaciones terminales sin mutar el conjunto de datos de origen.

---

## 🚀 4. Instrucciones de Compilación y Ejecución

Para compilar y ejecutar todo el conjunto de retos desde la raíz del proyecto:

```bash
# 1. Compilar todas las clases
javac -d out semana02/taller2/model/*.java semana02/taller2/pokemon/*.java semana02/taller2/bonos/*.java

# 2. Ejecutar un ejercicio en específico (ejemplo Ejercicio 19)
java -cp out taller2.pokemon.Ejercicio19

# 3. Ejecutar el Reto Mewtwo
java -cp out taller2.bonos.RetoMewtwo
```
