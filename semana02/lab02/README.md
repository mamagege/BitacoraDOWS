# Laboratorio 2 Parte 2 — Hackathon Express: SOLID, Patrones de Diseño y UML
**Asignatura:** Desarrollo Orientado a Software (DOSW)  
**Institución:** Escuela Colombiana de Ingeniería Julio Garavito  

---

## 📌 Tabla de Contenidos y Mapa del Proyecto

| Reto | Nombre del Reto | Categoría / Enfoque | Patrón de Diseño Principal | Documentación Detallada |
| :--- | :--- | :--- | :--- | :--- |
| **#1** | **La Boletería del Cine Astor** | SOLID · Streams · POO | Principios SOLID & Polimorfismo | [Ver README Reto 1](src/main/java/edu/dosw/lab/solid/reto1/README.md) |
| **#2** | **El Sastre a la Medida** | Creacional | **Builder** | [Ver README Reto 2](src/main/java/edu/dosw/lab/creacionales/reto2/README.md) |
| **#3** | **La Fábrica de Instrumentos** | Creacional | **Abstract Factory** | [Ver README Reto 3](src/main/java/edu/dosw/lab/creacionales/reto3/README.md) |
| **#4** | **La Balanza Trucada del Mercado** | Comportamiento | **Strategy** | [Ver README Reto 4](src/main/java/edu/dosw/lab/comportamiento/reto4/README.md) |
| **#5** | **La Moto Personalizada** | Estructural | **Decorator** | [Ver README Reto 5](src/main/java/edu/dosw/lab/estructurales/reto5/README.md) |
| **#6** | **Sala de Urgencias** | Comportamiento | **Chain of Responsibility** | [Ver README Reto 6](src/main/java/edu/dosw/lab/comportamiento/reto6/README.md) |
| **#7** | **El Rover Explorador de Marte** | Comportamiento | **Command** | [Ver README Reto 7](src/main/java/edu/dosw/lab/comportamiento/reto7/README.md) |
| **#8** | **La Academia de Fútbol de los UML** | SOLID · Estructural · UML | **Decorator** & UML de Clases | [Ver README Reto 8](src/main/java/edu/dosw/lab/estructurales/reto8/README.md) |

---

## 📚 Documentación Teórica y UML

* 📖 **Preguntas Teóricas del Laboratorio:** [docs/README.md](docs/README.md) (Polimorfismo, Inmutabilidad, Encapsulamiento, OCP, SRP, Maven, Interfaces vs Clases Abstractas).
* 📐 **Diagrama UML Reto 8:** [docs/uml/reto8uml.png](docs/uml/reto8uml.png).

---

## ⚙️ Estructura y Ejecución del Proyecto

### Requisitos
- **Java JDK 21+** (o compatible con JDK 26)
- **Apache Maven 3.8+**

### Comandos de Compilación y Pruebas
```bash
# Compilar el código fuente
mvn compile

# Ejecutar la suite completa de pruebas unitarias
mvn test

# Empaquetar la aplicación
mvn package
```

### Ejecución Principal
La clase `Application.java` (`edu.dosw.lab.Application`) orquesta la ejecución limpia de cada reto invocando sus métodos estáticos `ejecutar()`:
```bash
mvn exec:java -Dexec.mainClass="edu.dosw.lab.Application"
```
