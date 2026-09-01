# Reto #8: La Academia de Fútbol de los UML

## 1. Patrón de Diseño y Arquitectura
* **Categoría:** Estructural
* **Patrón Utilizado:** **Decorator**
* **Diagrama UML Exportado:** Ubicado en [`docs/uml/reto8uml.png`](file:///docs/uml/reto8uml.png).

---

## 2. Justificación del Patrón de Diseño
La academia de fútbol *ECI FC* requiere gestionar jugadores de distintas posiciones fijas (`Arquero`, `Defensa`, `Delantero`) y enriquecerlos con **atributos dinámicos y contextuales** que no todos los jugadores poseen al mismo tiempo:
* Posición secundaria
* País de origen
* Historial de lesiones
* Valor de mercado

El patrón **Decorator** fue elegido porque:
1. **Evita la explosión combinatoria de subclases:** Usar herencia fija requeriría clases como `DelanteroConPaisOrigenYValorDeMercado`, lo cual es inescalable.
2. **Permite asignar y remover atributos dinámicamente en tiempo de ejecución:** Cualquier jugador base puede recibir uno o varios decoradores encadenados.
3. **Mantiene intacto el contrato base del jugador:** Toda la jerarquía decorada sigue respondiendo a la interfaz común `IJugador`.

---

## 3. Estructura y Roles del Diagrama UML

### • Dominio del Jugador y Decoradores
* **`IJugador` (Component Interface):** Declara los métodos de interacción deportiva (`patear()`, `entrenar()`) y de consulta de datos del jugador.
* **`Jugador` (Abstract Component):** Implementa atributos protegidos y encapsulados con getters y setters (nombre, edad, dorsal, posición, pie hábil, peso, altura, estado físico, categoría).
* **`Arquero`, `Defensa`, `Delantero` (Concrete Components):** Subclases que implementan el comportamiento polimórfico según la demarcación en la cancha.
* **`JugadorDecorator` (Base Decorator):** Clase abstracta que implementa `IJugador` y delega las llamadas al objeto envuelto `IJugador`.
* **`PosicionSecundariaDecorator`, `PaisOrigenDecorator`, `HistorialLesionesDecorator`, `ValorMercadoDecorator` (Concrete Decorators):** Añaden cada atributo dinámico de forma modular.

### • Roles de Gestión e Interacción
* **`Entrenador`:** Dirige y evalúa jugadores mediante una relación de asociación **1 Entrenador $\leftrightarrow$ $N$ Jugadores**. Métodos: `dirigir(IJugador)`, `evaluar(IJugador)`, `planearSesion(IJugador)`.
* **`Hincha`:** Interactúa con los deportistas y el cuerpo técnico. Métodos: `animar(IJugador)`, `pedirAutografo(Entrenador)`, `publicarFoto(IJugador)`.

---

## 4. Análisis de Principios SOLID en el Diseño

| Principio | Dónde y Cómo se Aplica en la Solución |
| :--- | :--- |
| **S - Single Responsibility Principle (SRP)** | • `Jugador` se enfoca únicamente en el estado y rendimiento atlético.<br>• `Entrenador` asume exclusivamente la dirección táctica y evaluación.<br>• `Hincha` modela la afición y soporte público.<br>• Cada decorador gestiona un único atributo dinámico. |
| **O - Open/Closed Principle (OCP)** | La clase base `Jugador` está **cerrada a modificación** pero **abierta a extensión**. Nuevos atributos dinámicos (ej. *Cláusula de Rescisión*) se añaden creando nuevos decoradores sin alterar el código existente. |
| **L - Liskov Substitution Principle (LSP)** | Cualquier objeto `Arquero`, `Defensa`, `Delantero` o cualquier versión decorada con `JugadorDecorator` puede sustituir a `IJugador` en las operaciones del entrenador o hincha sin generar anomalías. |
| **I - Interface Segregation Principle (ISP)** | Las interfaces y métodos están segregados según el rol del actor, evitando interfaces sobrecargadas. |
| **D - Dependency Inversion Principle (DIP)** | Tanto el `Entrenador` como el `Hincha` dependen de la abstracción `IJugador` y no de subclases o decoradores específicos. |

---

## 5. Vista del Diagrama UML

El diagrama de clases completo modelando la herencia, polimorfismo, asociaciones y decoradores se encuentra en:
👉 **[Ver Diagrama UML PNG](../../../../../../docs/uml/reto8uml.png)**
