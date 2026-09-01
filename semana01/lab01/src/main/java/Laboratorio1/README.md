## 🧠 Preguntas Teóricas

### 🔀 Git y control de versiones

<details open>
<summary><strong>01. ¿Cuál es la diferencia entre <code>git merge</code> y <code>git rebase</code>?</strong></summary>

<br>

- **`git merge`**: preserva el historial completo de desarrollo creando un nuevo commit que combina las ramas, sin alterar los commits existentes.
- **`git rebase`**: reescribe la historia reproduciendo los commits de una rama sobre otra, generando una narrativa lineal, pero modificando los hash SHA de los commits.

📎 *Fuente: [DataCamp – Git Merge vs Git Rebase](https://www.datacamp.com/es/blog/git-merge-vs-git-rebase)*

</details>

<details open>
<summary><strong>02. Si dos ramas modifican la misma línea de un archivo, ¿qué sucede al hacer merge?</strong></summary>

<br>

Cuando ambas ramas modifican la misma parte del mismo archivo, Git no puede determinar automáticamente qué versión conservar. En ese caso, detiene el proceso justo antes de crear el commit de fusión para que el conflicto se resuelva manualmente.

📎 *Fuente: [Atlassian – Using Branches: Git Merge](https://www.atlassian.com/es/git/tutorials/using-branches/git-merge)*

</details>

<details open>
<summary><strong>03. ¿Cómo puedes ver gráficamente el historial de merges y ramas en consola?</strong></summary>

<br>

Con el comando:

```bash
git log --oneline --graph --all --decorate
```

| Flag | Función |
|------|---------|
| `--oneline` | Muestra cada commit en una sola línea |
| `--graph` | Representa el historial como un gráfico de ramas |
| `--all` | Incluye todas las ramas del repositorio |
| `--decorate` | Agrega nombres de ramas y etiquetas a cada commit |

📎 *Fuente: [GeeksforGeeks – Visualizing Branch Topology in Git](https://www.geeksforgeeks.org/git/how-to-visualizing-branch-topology-in-git/)*

</details>

<details open>
<summary><strong>04. Explica la diferencia entre un <code>commit</code> y un <code>push</code></strong></summary>

<br>

- **Commit**: guarda de forma local los cambios realizados en los archivos del repositorio.
- **Push**: envía y actualiza esos commits en una rama remota, haciéndolos visibles para el resto del equipo.

📎 *Fuente: [Cloud Infrastructure Services – Git Commit vs Push](https://cloudinfrastructureservices.co.uk/git-commit-vs-push-whats-the-difference-tutorial/)*

</details>

<details open>
<summary><strong>05. ¿Para qué sirven <code>git stash</code> y <code>git stash pop</code>?</strong></summary>

<br>

- **`git stash`**: guarda temporalmente los cambios sin confirmar del directorio de trabajo, dejándolo limpio para poder cambiar de tarea o de rama.
- **`git stash pop`**: recupera los cambios guardados y los elimina automáticamente de la pila de stash una vez aplicados.

📎 *Fuente: [KeepCoding – Qué es git stash pop](https://keepcoding.io/blog/que-es-git-stash-pop-y-su-funcion/)*

</details>

---

### ☕ Java y Programación Funcional

<details open>
<summary><strong>06. ¿Qué diferencia hay entre <code>HashMap</code> y <code>HashTable</code>?</strong></summary>

<br>

| | HashMap | HashTable |
|---|---------|-----------|
| **Sincronización** | No sincronizado | Sincronizado |
| **Concurrencia** | Varios hilos pueden acceder simultáneamente | Solo un hilo accede a la vez (bloqueo) |
| **Rendimiento** | Mayor (sin overhead de sincronización) | Menor en entornos multihilo |

📎 *Fuente: [BeginnersBook – Difference between HashMap and HashTable](https://beginnersbook.com/2014/06/difference-between-hashmap-and-hashtable/)*

</details>

<details open>
<summary><strong>07. ¿Qué ventajas tiene <code>Collectors.toMap()</code> frente a un bucle tradicional para llenar un mapa?</strong></summary>

<br>

Facilita la programación funcional mediante la API de Streams de Java, maneja de forma nativa la concurrencia y el procesamiento en paralelo, y reduce significativamente la cantidad de líneas de código necesarias frente a un bucle `for` tradicional.

</details>

<details open>
<summary><strong>08. Si usas <code>List</code> con objetos y aplicas <code>stream().map()</code>, ¿qué tipo de operación estás haciendo?</strong></summary>

<br>

Una operación de **transformación**: cada elemento de la lista se convierte en un objeto o valor nuevo, generando un nuevo `Stream` sin modificar la colección original.

📎 *Fuente: [Ciberaula – Streams en Java](https://www.ciberaula.com/cursos/java/streams_java.php)*

</details>

<details open>
<summary><strong>09. ¿Qué hace <code>stream().filter()</code> y qué retorna?</strong></summary>

<br>

Actúa como un colador: recorre la colección y selecciona únicamente los elementos que cumplen una condición específica, devolviendo un nuevo `Stream` que solo contiene los elementos que pasaron dicha condición.

📎 *Fuente: [KeepCoding – Qué hace stream filter en Java](https://keepcoding.io/blog/que-hace-stream-filter-en-java/)*

</details>

---

### 🌿 Flujo de trabajo con ramas (Git Flow)

<details open>
<summary><strong>10. Describe el paso a paso para crear una rama desde <code>develop</code> para una funcionalidad nueva</strong></summary>

<br>

```bash
# 1. Ubicarte en la rama develop
git checkout develop

# 2. Actualizar develop con los últimos cambios remotos
git pull origin develop

# 3. Crear y cambiar a la nueva rama de funcionalidad
git checkout -b feature/nombre-de-la-funcionalidad

# 4. Trabajar en los cambios y confirmarlos
git add .
git commit -m "feat: descripción breve del cambio"

# 5. Subir la rama al repositorio remoto
git push origin feature/nombre-de-la-funcionalidad

# 6. Abrir un Pull Request hacia develop para revisión e integración
```

Este flujo asegura que la rama `develop` se mantenga siempre estable y actualizada antes de derivar nuevo trabajo desde ella.

</details>

<details open>
<summary><strong>11. ¿Cuál es la diferencia entre <code>git branch</code> y <code>git checkout -b</code>?</strong></summary>

<br>

- **`git branch nombre-rama`**: únicamente **crea** la nueva rama, pero el usuario permanece en la rama actual; es necesario ejecutar `git checkout nombre-rama` por separado para moverse a ella.
- **`git checkout -b nombre-rama`**: es un atajo que combina ambas acciones en un solo comando — **crea la rama y cambia (switch) a ella automáticamente**.

En términos equivalentes:

```bash
git branch feature/x && git checkout feature/x
# es lo mismo que:
git checkout -b feature/x
```

</details>

<details open>
<summary><strong>12. ¿Por qué se recomienda usar ramas <code>feature/</code> en lugar de trabajar directamente en <code>main</code>?</strong></summary>

<br>

- **Aislamiento del código estable**: `main` refleja siempre una versión funcional o lista para producción; el desarrollo en curso no debe comprometer esa estabilidad.
- **Revisión de código**: trabajar en `feature/` permite abrir un Pull Request antes de integrar los cambios, habilitando revisión por pares y control de calidad.
- **Desarrollo paralelo**: distintos integrantes del equipo pueden trabajar en funcionalidades independientes sin generar conflictos entre sí.
- **Rollback sencillo**: si una funcionalidad falla o se descarta, basta con eliminar o abandonar la rama, sin afectar el historial de `main`.
- **Integración con CI/CD**: los pipelines de integración continua pueden validar cada rama antes de permitir su fusión, evitando que código roto llegue a producción.

</details>

