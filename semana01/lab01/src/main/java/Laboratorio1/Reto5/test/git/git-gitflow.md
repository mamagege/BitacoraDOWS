content = """# 🌊 Semana 1 - 03: Metodología Git Flow

> **Módulo:** Semana 1 - Git & Version Control  
> **Tema:** Flujo de trabajo estándar para equipos, convención de ramas y uso de la extensión `git-flow`.

---

## 🏗️ 1. ¿Qué es Git Flow?

Es un modelo de ramificación estructurado creado por Vincent Driessen. Define un conjunto estricto de reglas sobre qué ramas existen y cuándo deben interactuar, ideal para proyectos con ciclos de lanzamiento programados (como aplicaciones SaaS financieras o plataformas de alto tráfico).

---

## 🌳 2. Tipos de Ramas en Git Flow

### Ramas Principales (Larga Duración)
Nunca se eliminan y contienen el historial principal del proyecto.
* **`main` (o `master`):** Contiene **exclusivamente** el código en producción. Cada commit aquí suele estar etiquetado (tag) con un número de versión (ej. `v1.0.0`).
* **`develop`:** La rama de integración principal. Contiene las funcionalidades más recientes para el próximo lanzamiento. Nace de `main`.

### Ramas de Apoyo (Efímeras / Temporales)
Tienen un ciclo de vida corto; se crean para un propósito y se eliminan al finalizar.
* **`feature/` (Características):** Para desarrollar nuevas funcionalidades.
  * Nacen de: `develop`
  * Se fusionan en: `develop`
* **`release/` (Lanzamientos):** Para preparar el paso a producción (pruebas finales, corrección de bugs menores, metadatos).
  * Nacen de: `develop`
  * Se fusionan en: `main` y `develop`
* **`hotfix/` (Parches urgentes):** Para solucionar errores críticos directamente en producción sin esperar al próximo ciclo.
  * Nacen de: `main`
  * Se fusionan en: `main` y `develop`

---

## ⚙️ 3. Comandos de la Extensión `git flow`

En lugar de hacer los merge y branch manualmente, la CLI de Git Flow automatiza el proceso.

### Inicialización
```bash
# Inicializa el repositorio con la estructura Git Flow. 
# Te preguntará por los prefijos (puedes dejar los predeterminados dando Enter).
git flow init

```

Trabajando con Features (Nuevas Características)

```bash
# Iniciar una característica (ej: módulo de autenticación para ECI RANKS)
# Esto crea la rama 'feature/auth-eci-ranks' basada en 'develop' y cambia a ella.
git flow feature start auth-eci-ranks

# ... haces tus commits normalmente (git add, git commit) ...

# Finalizar la característica
# Esto fusiona 'feature/auth-eci-ranks' en 'develop', elimina la rama y cambia a 'develop'.
git flow feature finish auth-eci-ranks

```

## Trabajando con Releases (Preparando Producción)

```bash

# Iniciar la preparación de una versión (ej: v1.2.0)
# Crea 'release/1.2.0' basada en 'develop'
git flow release start 1.2.0

# ... pruebas de QA, arreglos menores ...

# Finalizar el Release
# Fusiona en 'main', crea un TAG de versión, fusiona en 'develop' y elimina la rama.
git flow release finish 1.2.0

```

## Trabajando con Hotfixes (Emergencias en Producción)

```bash

# Ocurre un error crítico en el cálculo de transacciones del SaaS.
# Crea 'hotfix/1.2.1' basada directamente en 'main'
git flow hotfix start 1.2.1

# ... aplicas el parche, git add, git commit ...

# Finalizar el Hotfix
# Fusiona en 'main' (y tag 1.2.1), fusiona en 'develop' para que no se pierda el arreglo, y elimina la rama.
git flow hotfix finish 1.2.1

```


## 🧠 4. Git Flow de forma manual (Sin la extensión)
Si en el examen te piden simular un Hotfix sin usar los comandos de git flow, el proceso equivalente es:

```bash
# 1. Crear rama hotfix desde main
git switch -c hotfix/1.2.1 main

# 2. Hacer el commit del arreglo
git commit -am "fix: resolver caída en cálculo de facturación"

# 3. Fusionar en main y etiquetar
git switch main
git merge --no-ff hotfix/1.2.1
git tag -a v1.2.1 -m "Hotfix 1.2.1"

# 4. Fusionar en develop (¡CRÍTICO no olvidar este paso!)
git switch develop
git merge --no-ff hotfix/1.2.1

# 5. Limpiar rama
git branch -d hotfix/1.2.1

```

## 💡 Key Takeaways para Examen
El ciclo de vida de un Hotfix: A diferencia de las features o releases, el hotfix es la única rama que nace de main. Al finalizar, debe obligatoriamente ir hacia main Y hacia develop para no perder el parche en futuros despliegues.

git flow release finish: Es el comando que más acciones ejecuta por debajo: 1 merge a main, 1 creación de tag, 1 merge a develop y eliminación de rama local.

¿Cuándo NO usar Git Flow? En entornos de Integración/Despliegue Continuo (CI/CD) ultrarrápidos donde se despliega a producción varias veces al día, Git Flow suele ser muy burocrático. Ahí se prefiere GitHub Flow (solo main y ramas efímeras).


