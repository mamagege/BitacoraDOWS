# Desglose Formal y Detección de Code Smells en Principios SOLID

---

## 1. Single Responsibility Principle (SRP) - Principio de Responsabilidad Única

### Definición Formal
> *"A module should be responsible to one, and only one, actor."* — Robert C. Martin  
> *(Un módulo debe ser responsable ante un único actor o usuario de negocio).*

### En Palabras Simples
Una clase solo debe tener **un motivo para cambiar**. No debe mezclar reglas de negocio, persistencia de datos y presentación en el mismo lugar. Cada clase resuelve una sola tarea conceptual del sistema.

### Code Smells para Identificar su Violación
1. **God Classes / Clases Monstruo:** Clases de más de 400 líneas que acumulan docenas de métodos no relacionados (ej. `UserManager` que valida passwords, genera tokens JWT, guarda en SQL y envía emails transaccionales).
2. **Métodos con conjunciones ("Y", "O"):** Nombres de métodos como `calculateTaxAndSaveInvoice()` o `parseXmlAndSendToApi()`.
3. **Múltiples razones de cambio de actores diferentes:** Si un cambio solicitado por el departamento de Contabilidad y un cambio solicitado por el departamento de Seguridad requieren editar el mismo archivo.
4. **Imports dispares:** Una clase de dominio importando `java.sql.*`, `javax.mail.*` y `com.fasterxml.jackson.*`.

---

## 2. Open/Closed Principle (OCP) - Principio de Abierto / Cerrado

### Definición Formal
> *"Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification."* — Bertrand Meyer

### En Palabras Simples
Debes poder **agregar nuevas funcionalidades sin modificar el código fuente existente**. Esto se logra mediante abstracciones (interfaces y clases abstractas) y polimorfismo, permitiendo conectar nuevas implementaciones sin tocar la lógica central que ya fue probada.

### Code Smells para Identificar su Violación
1. **Proliferación de bloques `if-else` o `switch` sobre tipos:**
   ```java
   switch (paymentType) {
       case "CREDIT_CARD": ...
       case "PAYPAL": ...
       case "CRYPTO": ... // Modificar aquí cada vez que se agrega un medio de pago
   }
   ```
2. **Uso de `instanceof` para bifurcar flujo de negocio:** Comprobar tipos en tiempo de ejecución para decidir qué lógica ejecutar en lugar de invocar un método polimórfico.
3. **Miedo al Deploy:** Tener que recompilar y volver a certificar la clase troncal del sistema cada vez que entra un nuevo requerimiento menor.

---

## 3. Liskov Substitution Principle (LSP) - Principio de Sustitución de Liskov

### Definición Formal
> *"Let $\Phi(x)$ be a property provable about objects $x$ of type $T$. Then $\Phi(y)$ should be true for objects $y$ of type $S$ where $S$ is a subtype of $T$."* — Barbara Liskov

### En Palabras Simples
Si el código espera una clase base o interfaz, **debe poder usar cualquier subclase sin saberlo y sin que el programa falle o rompa sus expectativas de comportamiento**. Las subclases no deben debilitar las precondiciones ni fortalecer las postcondiciones del contrato base.

### Code Smells para Identificar su Violación
1. **Métodos que lanzan `UnsupportedOperationException` o `NotImplementedException`:** Subclases que heredan un método y lo dejan vacío o lanzan excepción porque "no aplica" a su naturaleza (el clásico `Square` heredando de `Rectangle`, o `Penguin` heredando de `Bird` con método `fly()`).
2. **Validaciones defensivas de tipo por el cliente:**
   ```java
   if (account instanceof ReadOnlyAccount) {
       // Evitar llamar a withdraw() para no reventar la app
   }
   ```
3. **Efectos colaterales inesperados:** La subclase muta un estado global o rompe una invariante que la clase base prometía mantener.

---

## 4. Interface Segregation Principle (ISP) - Principio de Segregación de Interfaces

### Definición Formal
> *"Clients should not be forced to depend upon interfaces that they do not use."* — Robert C. Martin

### En Palabras Simples
Es preferible tener **muchas interfaces pequeñas, atómicas y especializadas (roles)** que una sola interfaz "gorda" y monolítica. Ningún cliente debe verse forzado a implementar métodos que no necesita.

### Code Smells para Identificar su Violación
1. **Interfaces "Gordas" (*Fat Interfaces*):** Interfaces con 20 o 30 métodos que intentan abarcar múltiples capacidades del sistema.
2. **Implementaciones "Fantasma":** Clases que implementan una interfaz pero dejan métodos con cuerpos vacíos (`{ /* No-op */ }`) o retornan `null` / `false` por defecto.
3. **Acoplamiento innecesario en tests:** Crear mocks gigantescos donde tienes que simular 15 métodos que tu prueba ni siquiera toca solo para satisfacer la firma de la interfaz.

---

## 5. Dependency Inversion Principle (DIP) - Principio de Inversión de Dependencias

### Definición Formal
> *"High-level modules should not depend on low-level modules. Both should depend on abstractions.*  
> *Abstractions should not depend on details. Details should depend on abstractions."* — Robert C. Martin

### En Palabras Simples
La **lógica de negocio esencial (alto nivel)** nunca debe depender de librerías técnicas, frameworks o bases de datos (bajo nivel). Ambos deben depender de **interfaces**. Las capas externas son conectores intercambiables (Arquitectura Hexagonal / Puertos y Adaptadores).

### Code Smells para Identificar su Violación
1. **El operador `new` en servicios o entidades de dominio:** Instanciar directamente repositorios, clientes HTTP o librerías dentro de la lógica del negocio (`private EmailService email = new SendGridEmailService();`).
2. **Uso de singletons estáticos en la lógica central:** Llamadas a `DatabaseConnection.getInstance().query()` directamente en el flujo de negocio.
3. **Imposibilidad de realizar pruebas unitarias puras:** Necesitar levantar una base de datos Postgres en Docker o conectarse a Internet para probar una simple regla aritmética de cálculo de descuentos.
