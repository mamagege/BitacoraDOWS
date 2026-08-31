# 📖 Semana 1 - 01: Comandos Base, Ciclo de Vida e Inicialización

> **Módulo:** Semana 1 - Git & Version Control  
> **Tema:** Comandos base, estados de archivos, inspección del historial y `.gitignore`.

---

## 📌 1. Arquitectura de Git y Ciclo de Vida de los Archivos

Git gestiona el código mediante 3 áreas locales principales y 4 estados de archivos.

### Las 3 Áreas de Git
1. **Working Directory (Directorio de Trabajo):** Tus archivos locales en disco donde editas código real.
2. **Staging Area / Index (Área de Preparación):** Zona intermedia donde organizas los cambios exactos que formarán parte del próximo commit.
3. **Repository / Git Directory (`.git`):** Base de datos local donde Git almacena las instantáneas (*commits*) inmutables del proyecto.

### Los 4 Estados de un Archivo
* **Untracked (No rastreado):** Archivo nuevo en el directorio de trabajo que Git aún no sigue.
* **Unmodified (Sin modificar):** Archivo rastreado que coincide exactamente con la versión del último commit.
* **Modified (Modificado):** Archivo rastreado que ha sufrido cambios locales pero aún no se ha subido a Staging.
* **Staged (Preparado):** Archivo modificado listo para ser incluido en la próxima instantánea (*commit*).

---

## ⚡ 2. Configuración Inicial (`git config`)

Comandos para inicializar el entorno local de trabajo:

```bash
# Configuración global del autor
git config --global user.name "Tu Nombre"
git config --global user.email "tu_correo@ejemplo.com"

# Establecer el nombre de la rama principal por defecto
git config --global init.defaultBranch main

# Configurar el editor predeterminado (ej. VS Code)
git config --global core.editor "code --wait"

# Consultar la configuración activa y su procedencia
git config --list --show-origin



```


## 🚀 3. Inicialización y Clonación de Repositorios


```bash

# Inicializar un nuevo repositorio en el directorio actual
git init

# Inicializar definiendo el nombre de la rama principal
git init -b main

# Clonar un repositorio remoto mediante HTTPS o SSH
git clone [https://github.com/usuario/repositorio.git](https://github.com/usuario/repositorio.git)

# Clonar dentro de una carpeta específica
git clone [https://github.com/usuario/repositorio.git](https://github.com/usuario/repositorio.git) mi-proyecto


```


## 📝 4. Ciclo Básico de Trabajo: Status, Add y Commit

```bash
# Consultar el estado del directorio de trabajo y del staging area
git status

# Consultar el estado en formato compacto
git status -s

# Mover cambios al Staging Area
git add archivo.java           # Añade un archivo específico
git add src/                   # Añade un directorio completo
git add .                      # Añade todos los cambios de la carpeta actual y subcarpetas
git add -A                     # Añade todo el árbol de trabajo (modificados, borrados y nuevos)

# Confirmar cambios organizados en Staging (Commit)
git commit -m "feat: implementar servicios de autenticación"

# Formato de mensaje multilínea (Título + Descripción corta)
git commit -m "feat: agregar entidad Usuario" -m "- Campos: id, email y password\n- Validaciones iniciales integradas"

# Atajo: Add + Commit directo para archivos YA RASTREADOS (No incluye archivos Untracked)
git commit -am "fix: corregir NullPointerException en cálculo de saldo"


```




## 🔍 5. Inspección del Historial y Comparaciones

### Historial de Commits (git log)
```bash
# Visualizar historial completo detallado
git log

# Historial resumido (un commit por línea)
git log --oneline

# Historial gráfico con estructura de ramas
git log --oneline --graph --all

# Limitar el historial a los últimos N commits
git log -n 5 --oneline

# Ver cambios de un archivo específico a lo largo del tiempo
git log -p src/Main.java

# Filtrar commits por autor o fecha
git log --author="Nombre"
git log --since="2026-08-01" --until="2026-08-24"


### Inspección de Diferencias (git diff)

```bash
# Compara el Working Directory contra el Staging Area (¿Qué cambié y NO he agregado?)
git diff

# Compara el Staging Area contra el último Commit/HEAD (¿Qué agregué que voy a commitear?)
git diff --staged
# (equivalente):
git diff --cached

# Compara las diferencias entre 2 commits específicos
git diff <hash_commit_A> <hash_commit_B>

# Compara las diferencias entre 2 ramas
git diff main develop

```


## 🛡️ 6. Configuración de .gitignore
El archivo .gitignore indica a Git qué archivos o carpetas debe omitir (archivos compilados, configuraciones de IDE, datos sensibles).

Reglas de Sintaxis

```
# Ignorar archivo específico
secretos.env

# Ignorar carpetas completas
target/
build/
.idea/
.vscode/

# Ignorar por extensión de archivo
*.class
*.jar
*.log

# Excepción a una regla previa (!)
!configuracion-importante.log

Plantilla Recomendada para Java (Maven / Gradle / IDEs)

# Archivos compilados de Java
*.class

# Empaquetados
*.jar
*.war
*.ear

# Directorios de compilación / Build
target/
build/
out/

# Entornos e IDEs (IntelliJ, Eclipse, VS Code)
.idea/
*.iml
*.ipr
*.iws
.project
.classpath
.settings/
.vscode/

# Logs y bases de datos locales
*.log
*.sqlite

# Archivos de sistema operativo
.DS_Store
Thumbs.db

```

## ⏪ 7. Modificación y Deshacer Cambios Básicos

```bash
# Modificar el mensaje del último commit (antes de hacer push)
git commit --amend -m "feat: mensaje del commit corregido"

# Incluir un archivo olvidado en el último commit sin cambiar el mensaje
git add archivo_olvidado.java
git commit --amend --no-edit

# Quitar un archivo del Staging Area (mantiene las modificaciones locales en disco)
git restore --staged archivo.java
# Sintaxis clásica equivalente:
git reset HEAD archivo.java

# Descartar TODOS los cambios locales no guardados de un archivo (IRREVERSIBLE)
git restore archivo.java
# Sintaxis clásica equivalente:
git checkout -- archivo.java

# Eliminar un archivo del rastreo de Git pero MANTENERLO en tu disco local
git rm --cached .env
💡 Key Takeaways para Examen
git diff vs git diff --staged: git diff evalúa lo que está en tu editor local y aún no has pasado a Staging. git diff --staged evalúa lo que ya pasaste a Staging mediante git add y está listo para ser commiteado.

git commit -am no rastrea archivos nuevos: Solo funciona si los archivos modificados ya estaban en el historial de Git. Si creas un archivo .java nuevo, debes hacer git add explícito.

git restore --staged no borra código: Retira el archivo del próximo commit pero conserva el código modificado en tu editor. Por el contrario, git restore (sin --staged) sobreescribe tu código local destruyendo los cambios no guardados. 