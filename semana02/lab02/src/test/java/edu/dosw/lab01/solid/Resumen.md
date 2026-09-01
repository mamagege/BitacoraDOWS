# 📋 SOLID Cheat Sheet & Guía de Supervivencia Ágil

> *"La simplicidad es el arte de maximizar la cantidad de trabajo no realizado."* — Principio Ágil #10

---

## ⚡ Resumen Ejecutivo en 1 Sola Frase

| Principio | Resumen en 1 Sola Frase |
| :--- | :--- |
| **S - Single Responsibility** | *Una clase debe tener una única razón para cambiar y responder a un solo actor.* |
| **O - Open / Closed** | *El código debe estar abierto para extenderse con nuevas funciones, pero cerrado a la modificación de lo ya probado.* |
| **L - Liskov Substitution** | *Cualquier subclase debe poder sustituir a su clase base sin alterar la correctitud del programa.* |
| **I - Interface Segregation** | *Crea interfaces pequeñas y específicas; ningún cliente debe depender de métodos que no utiliza.* |
| **D - Dependency Inversion** | *Depende de abstracciones e interfaces, jamás de implementaciones concretas o detalles de infraestructura.* |

---

## 🎯 Heurísticas Rápidas de Detección

```
¿Tienes un switch sobre tipos de negocio?           ➡️  Aplica OCP (Strategy / Polimorfismo)
¿Lanzas UnsupportedOperationException al heredar?   ➡️  Aplica LSP (Segrega tipos o usa composición)
¿Tu clase hace cálculo + SQL + HTTP + Email?       ➡️  Aplica SRP (Extrae servicios atómicos)
¿Tu interfaz tiene métodos vacíos en la subclase?   ➡️  Aplica ISP (Divide en interfaces de rol)
¿Usas 'new' para crear dependencias en el dominio?  ➡️  Aplica DIP (Inyecta por constructor)
```

---

## ⚖️ El Balance entre SOLID y el Sobre-diseño (Anti-Overengineering)

Los principios SOLID son **guías de diseño, no dogmas religiosos**. Aplicarlos prematuramente a sistemas triviales produce sobre-arquitectura (*Overengineering*), incrementando la complejidad accidental sin aportar valor real.

### 1. La Regla de Tres (Rule of Three) y YAGNI
> *"You Aren't Gonna Need It" (No lo vas a necesitar todavía).*
- No crees 5 capas de abstracción para una funcionalidad que solo tiene una implementación y pocas probabilidades de cambiar.
- **Primera vez:** Escribe la solución más simple posible (KISS).
- **Segunda vez:** Si hay similitud, tolera la duplicación momentánea.
- **Tercera vez:** Ahora sí, refactoriza hacia una abstracción (OCP/Strategy/Factory).

### 2. Composición sobre Herencia
- El principio LSP suele violarse por abusar de la **herencia de clases**.
- Prefiere componer objetos pequeños que implementan interfaces pequeñas (**ISP + Composición**) en lugar de construir jerarquías profundas de 4 niveles de herencia.

### 3. Inyección de Dependencias Pragmática
- Invertir dependencias (**DIP**) no significa crear una interfaz para cada `CustomerHelper` si nunca existirá otra implementación y no agrega valor de aislamiento en pruebas.
- Crea abstracciones en los **límites del sistema** (Base de datos, APIs de terceros, reloj del sistema, generadores aleatorios, sistemas de archivos).

---

## 🧭 Resumen Visual de Arquitectura Limpia

```
+-----------------------------------------------------------------+
|                    INFRAESTRUCTURA / UI                         |
|   (PostgreSQL, Spring MVC, REST Controllers, AWS S3, Twilio)   |
|                               |                                 |
|                               v  (Implementa)                   |
|   +---------------------------------------------------------+   |
|   |                  CAPA DE APLICACIÓN                     |   |
|   |         (Casos de Uso / Orquestadores / DTOs)           |   |
|   |                           |                             |   |
|   |                           v  (Usa)                      |   |
|   |   +-------------------------------------------------+   |   |
|   |   |                 DOMINIO PURO                    |   |   |
|   |   |        (Entidades, Reglas, Interfaces /         |   |   |
|   |   |            Puertos: SOLID en acción)            |   |   |
|   |   +-------------------------------------------------+   |   |
|   +---------------------------------------------------------+   |
+-----------------------------------------------------------------+
          Regla de Dependencia: Las flechas siempre apuntan 
                     hacia adentro (Hacia el Dominio)
```

---

## 💡 Mantra Diario para el Desarrollador Ágil
1. **Haz que funcione** (Haz pasar el test).
2. **Hazlo correcto** (Aplica SOLID y Clean Code).
3. **Hazlo rápido** (Optimiza solo si los perfiles de rendimiento lo exigen).
