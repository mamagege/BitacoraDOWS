# 🔀 Semana 1 - 02: Gestión de Ramas, Merge, Rebase y Conflictos

> **Módulo:** Semana 1 - Git & Version Control  
> **Tema:** Trabajo con ramas, estrategias de integración (Merge vs Rebase), resolución de conflictos y Cherry-Pick.

---

## 🌱 1. Gestión Básica de Ramas (Branching)

Las ramas te permiten trabajar en funcionalidades aisladas sin afectar el código de producción (`main`).

```bash
# Listar todas las ramas locales (la activa tiene un *)
git branch

# Listar ramas locales y remotas
git branch -a

# Crear una nueva rama
git branch <nombre-rama>

# Cambiar de rama (Comando moderno)
git switch <nombre-rama>
# Alternativa clásica: git checkout <nombre-rama>

# Crear y cambiar a la rama en un solo paso
git switch -c <nombre-rama>
# Alternativa clásica: git checkout -b <nombre-rama>

# Eliminar una rama local de forma segura (Git avisa si hay cambios sin fusionar)
git branch -d <nombre-rama>

# Forzar la eliminación de una rama local (CUIDADO: pierdes cambios no fusionados)
git branch -D <nombre-rama>

```


## 🤝 2. Estrategias de Fusión: Git Merge
El comando git merge une el historial de dos ramas bifurcadas. Importante: Siempre debes estar posicionado en la rama receptora (ej. main o develop) antes de ejecutar el merge.

A. Fast-Forward Merge
Ocurre cuando la rama receptora no ha tenido nuevos commits desde que se creó la nueva rama. Git simplemente avanza el puntero de la rama base hacia adelante. No crea un nuevo commit de fusión.

B. 3-Way Merge (Fusión a Tres Bandas)
Ocurre cuando ambas ramas tienen nuevos commits. Git crea un nuevo commit de merge que tiene dos padres, uniendo ambos historiales.

```bash
# Estando en 'main', fusionar los cambios de 'feature-login'
git merge feature-login

# Forzar la creación de un commit de merge (Incluso si era posible un Fast-Forward)
# Muy útil en entornos corporativos para registrar que existió una feature branch
git merge --no-ff feature-login

```


## 🏗️ 3. Reescribir el Historial: Git Rebase
git rebase toma los commits de tu rama actual y los "re-aplica" uno por uno sobre la cima de otra rama (ej. main).

Ventaja: Crea un historial completamente lineal, limpio y fácil de leer (evita el exceso de commits de merge).

Regla de Oro: NUNCA hagas rebase de una rama pública que compartas con otros desarrolladores (como main o develop). Úsalo solo en tus ramas locales de características (feature).

```bash
# Actualizar mi rama actual con los últimos cambios de 'main' usando Rebase
git rebase main

# Si hay un conflicto durante el rebase, lo resuelves y luego:
git add .
git rebase --continue

# Para cancelar un rebase en curso y volver al estado anterior
git rebase --abort

```


## ⚔️ 4. Resolución de Conflictos
Un conflicto ocurre cuando dos ramas modifican la misma línea del mismo archivo, o cuando una rama modifica un archivo que la otra eliminó. Git detiene el proceso de Merge o Rebase y te pide resolverlo manualmente.

Anatomía de un Conflicto en el Código
Git inyecta marcadores de conflicto en tus archivos:

```bash
<<<<<<< HEAD
System.out.println("Cálculo de saldo actual"); (Tu código en la rama actual)
=======
System.out.println("Cálculo de balance final"); (El código de la rama que estás fusionando)
>>>>>>> feature-balance
Pasos para resolverlo (Flujo de Examen):
Abre los archivos en estado de conflicto (tu IDE los resaltará).

Borra los marcadores (<<<<<<<, =======, >>>>>>>).

Deja únicamente el código final que debe quedar (puedes combinar ambos, elegir uno, o escribir algo nuevo).

Guarda el archivo.

Ejecuta los siguientes comandos:

```bash
# 1. Marca el conflicto como resuelto añadiéndolo al staging area
git add archivo_resuelto.java

# 2. Termina el proceso:
# Si estabas en un MERGE:
git commit -m "fix: resolver conflicto de merge en balance"

# Si estabas en un REBASE:
git rebase --continue

```

```bash
# Abortar un Merge en caso de pánico (restaura el estado antes de intentar fusionar)
git merge --abort
```


## 🍒 5. Git Cherry-Pick (Extracción Quirúrgica)
Permite copiar un commit específico de cualquier otra rama y aplicarlo en tu rama actual, sin tener que fusionar toda la rama.

```bash
# Aplicar un commit específico mediante su hash (ej. 7a5f9c0)
git cherry-pick 7a5f9c0

# Si hay conflictos, los resuelves, haces 'git add' y luego:
git cherry-pick --continue

```


## 💡 Key Takeaways para Examen
La diferencia principal: Merge respeta el historial original y cronológico (creando nodos de unión). Rebase reescribe el historial para que parezca que todo se desarrolló de forma secuencial y lineal.

Contexto de Rebase: Si la pregunta del examen menciona "historial lineal limpio", la respuesta es Rebase. Si menciona "mantener la trazabilidad de cuándo se integró una rama", la respuesta es Merge (con --no-ff).

Flujo de Rebase ante conflictos: Nunca haces git commit para resolver un conflicto de rebase; haces git add y luego git rebase --continue.