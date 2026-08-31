# 📚 Bitácora de Estudio: Desarrollo y Operaciones de Ciclos en Java (2026)

Bienvenido a la bitácora principal de apuntes, guía de comandos, talleres y laboratorios para el curso **Desarrollo y Operaciones de Ciclos en Java**. Este repositorio está estructurado para permitir búsquedas ultrarrápidas y navegación por hipervínculos durante las evaluaciones y sesiones de estudio.

---

## 🔍 Guía de Búsqueda Rápida para Exámenes

Si necesitas ubicar un comando, parámetro o mensaje de error específico durante un examen:

* **VS Code / IntelliJ IDEA:** Presiona `Ctrl + Shift + F` (o `Cmd + Shift + F` en macOS) para realizar una búsqueda global en todos los archivos.
* **Búsqueda por Terminal:**
  ```bash
  grep -rnw './' -e "palabra_clave_o_error"
  ```

---

## 📌 Estructura y Contenidos de la Bitácora

### 🔹 1. Control de Versiones: Git, Git Merge y Git Flow
* 📖 [01. Comandos Base e Inicialización](semana01/git/git-commands.md) — `git init`, `add`, `commit`, `status`, `log`, `.gitignore` e historial.
* 🔀 [02. Gestión de Ramas: Git Merge vs. Rebase](semana01/git/git-rebase-merge.md) — `git branch`, `switch`, `checkout`, `merge`, `rebase` y resolución de conflictos.
* 🌊 [03. Flujos de Trabajo con Git Flow](semana01/git/git-gitflow.md) — Convención de ramas (`main`, `develop`, `feature`, `release`, `hotfix`) y comandos `git-flow`.
* ⚡ [Cheat Sheet: Referencia Rápida de Git](semana01/git/git-resume.md) — Comandos esenciales y soluciones a emergencias.

---

### 🔹 2. Estructuras de Datos en Java
* 🧱 [01. Conceptos y Complejidad Big O](semana01/estructurasDatos/listado.md) — Arrays, ArrayList, LinkedList, Stack, Queue, HashSet, HashMap, TreeMap.
* ⚖️ [02. Comparaciones y Elección de Estructuras](semana01/estructurasDatos/comparaciones.md) — HashMap vs. HashTable, Array vs. ArrayList, String vs. StringBuilder.
* 💻 [03. Sintaxis y Operaciones Clave](semana01/estructurasDatos/sintaxis.md) — Cheat sheet con métodos fundamentales.
* 🧪 [04. Ejemplos Prácticos Aplicados](semana01/estructurasDatos/ejemplos.md) — Casos de uso reales comentados paso a paso.

---

### 🔹 3. Programación Funcional y Java Streams API
* 🧠 [01. Fundamentos de Programación Funcional](semana01/programacionFuncional/01-fundamentos-programacion-funcional.md) — Paradigma declarativo, funciones puras, inmutabilidad, transparencia referencial, interfaces funcionales (`Predicate`, `Function`, `Consumer`, etc.).
* ⚡ [02. Expresiones Lambda y Streams API](semana01/programacionFuncional/02-streams-y-lambdas.md) — Sintaxis lambda, referencias a métodos (`::`), evaluación perezosa (*Lazy Evaluation*), fusión de bucles, cortocircuito y paralelismo (`ForkJoinPool`).
* 📖 [03. Catálogo Maestro de Funciones de Stream (Top 27)](semana01/programacionFuncional/03-funciones-stream-referencia.md) — Referencia de los métodos más usados ordenados por relevancia (`filter`, `map`, `collect`, `flatMap`, `reduce`, `anyMatch`, `groupingBy`, etc.).
* 💻 [04. Ejemplos Prácticos y Retos Resueltos](semana01/programacionFuncional/04-ejemplos-y-ejercicios.md) — Implementaciones completas con Java Records, agregaciones financieras, filtros de e-commerce y batería de retos con soluciones.

---

### 🔹 4. Talleres y Laboratorios Prácticos
* 🛠️ [Taller 01: Resolución y Guía de 16 Retos de Java Streams](semana01/taller01/README.md) — Resolución documentada paso a paso de los 16 retos (`filter`, `forEach`, `map`, `reduce`, `collect`, `peek`, `sorted`, `distinct`, `limit`, `skip`, `min`, `max`, `anyMatch`, `allMatch`, `noneMatch` y Reto Final).
