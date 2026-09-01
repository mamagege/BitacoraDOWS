# 🚀 Guía Práctica de Apache Maven (Cheat Sheet & Setup)

> **Apache Maven** es la herramienta estándar de automatización de compilación, empaquetado y gestión de dependencias para el ecosistema Java y la JVM.

---

## 1. ¿Qué es Maven y para qué sirve?

Maven gestiona el ciclo de vida completo de un proyecto Java basándose en el concepto de **Project Object Model (POM)**:
- **Gestión de dependencias:** Descarga automáticamente librerías externas (JARs) y sus dependencias transitivas desde repositorios centrales (Maven Central).
- **Estandarización de estructura:** Todos los proyectos Maven siguen la misma convención de directorios.
- **Ciclo de compilación y pruebas:** Compila, corre pruebas unitarias/integración y genera ejecutables (`.jar`, `.war`).

### 📁 Estructura Estándar de Carpetas
```text
mi-proyecto/
├── pom.xml                   # Archivo central de configuración
└── src/
    ├── main/
    │   ├── java/             # Código fuente de producción (.java)
    │   └── resources/        # Archivos de configuración (.properties, .yml, .sql)
    └── test/
        ├── java/             # Código de pruebas unitarias/TDD
        └── resources/        # Recursos exclusivos de prueba
```

---

## 2. Estructura Básica del `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- Coordenadas únicas de tu proyecto (GAV) -->
    <groupId>com.empresa.proyecto</groupId>
    <artifactId>mi-aplicacion</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.10.0</junit.version>
    </properties>

    <!-- Librerías requeridas -->
    <dependencies>
        <!-- Ejemplo: JUnit 5 para pruebas unitarias / TDD -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin para compilar código Java moderno -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## 3. Comandos Esenciales de Terminal (CLI)

Ejecuta estos comandos en la raíz donde reside tu `pom.xml`:

| Comando | Acción |
| :--- | :--- |
| `mvn clean` | Borra el directorio `/target` (archivos compilados previos). |
| `mvn compile` | Compila las clases de producción en `src/main/java`. |
| `mvn test` | Ejecuta las pruebas unitarias en `src/test/java`. |
| `mvn package` | Compila, prueba y empaqueta el proyecto en un `.jar` o `.war`. |
| `mvn install` | Empaqueta e instala el `.jar` en tu repositorio local (`~/.m2/repository`). |
| `mvn clean package -DskipTests` | Empaqueta saltándose las pruebas (útil para builds rápidos). |
| `mvn dependency:tree` | Muestra el árbol completo de dependencias y conflictos. |

> 💡 **Nota sobre el Maven Wrapper (`mvnw`):** Si el proyecto incluye `./mvnw` (Linux/Mac) o `mvnw.cmd` (Windows), puedes usarlo directamente sin instalar Maven en el sistema operativo:
> ```powershell
> .\mvnw clean test
> ```

---

## 4. Configuración en IntelliJ IDEA

IntelliJ viene con soporte nativo de Maven preinstalado:

1. **Abrir Proyecto:**
   - Ve a `File` ➔ `Open...` ➔ Selecciona el archivo `pom.xml` o la carpeta raíz del proyecto.
   - Elige **"Open as Project"**.
2. **Ventana de Herramientas de Maven:**
   - En la barra lateral derecha, haz clic en el icono **Maven** (letra 'M').
   - Ahí puedes ver el ciclo de vida (`clean`, `test`, `package`), plugins y dependencias.
3. **Recargar / Sincronizar Cambios:**
   - Cada vez que edites el `pom.xml`, aparecerá un icono flotante con dos flechas azules 🔄 (o presiona `Ctrl + Shift + O` / botón derecho en el `pom.xml` ➔ `Maven` ➔ `Reload project`).
4. **Configuración de JDK en IntelliJ:**
   - Ve a `File` ➔ `Project Structure...` (o `Ctrl + Alt + Shift + S`) ➔ En **Project SDK**, asegúrate de que coincida con la versión configurada en tu `pom.xml` (ej. Java 17 / 21).

---

## 5. Configuración en Visual Studio Code (VS Code)

Para trabajar de forma fluida con Maven en VS Code:

1. **Instalar Extensiones Oficiales de Microsoft:**
   - Instala el pack: **Extension Pack for Java** (incluye *Language Support for Java*, *Debugger for Java*, *Test Runner for Java* y *Maven for Java*).
2. **Abrir el Proyecto:**
   - Abre la carpeta que contiene el `pom.xml` (`File` ➔ `Open Folder...`).
   - VS Code detectará automáticamente el archivo `pom.xml` e iniciará el Language Server de Java.
3. **Panel de Maven en VS Code:**
   - En la barra lateral izquierda (Explorador), verás una sección llamada **MAVEN**.
   - Haz clic en tu proyecto para desplegar los ciclos de vida (`Lifecycle`), plugins y ejecutar `clean`, `test`, o `package` con un solo clic.
4. **Agregar Dependencias Rápidamente:**
   - Presiona `Ctrl + Shift + P` ➔ Escribe `Maven: Add a dependency...` ➔ Escribe el nombre de la librería (ej. `lombok`, `junit`) y selecciónala.

---

## 6. Solución de Problemas Comunes (Troubleshooting)

- **Error de versión de Java (*Unsupported class file major version*):**
  - Revisa que tu variable de entorno `JAVA_HOME` apunte a la misma versión declarada en `<maven.compiler.source>`.
- **Dependencias corruptas o no encontradas:**
  - Fuerza la actualización con:
    ```bash
    mvn clean install -U
    ```
  - O elimina manualmente la carpeta conflictiva dentro de `C:\Users\<TuUsuario>\.m2\repository`.
