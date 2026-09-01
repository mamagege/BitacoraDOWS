README.md: Ejercicio #04 - Builder + Decorator
Objetivo: Diseñar la creación y potenciación dinámica de personajes en un videojuego.

1. Rol de cada patrón
Builder: Se encarga exclusivamente de construir el personaje paso a paso al inicio de la partida. Su propósito es evitar constructores masivos (anti-patrón Telescoping Constructor) y garantizar que el objeto base nazca en un estado válido y consistente.

Decorator: Añade comportamiento y poderes de forma dinámica, envolviendo al personaje base en tiempo de ejecución. Su propósito es mutar las capacidades del personaje sin tocar su código original ni alterar su identidad estructural.

2. Cómo interactúan (Multivariable)
El Builder entra en acción en la ßfase de configuración inicial (antes de jugar): recibe las especificaciones de armadura, arma y habilidad, y ensambla un objeto inmutable.

Durante el juego (runtime), el motor detecta eventos y usa el Decorator para envolver temporalmente al personaje base con modificadores (ej. escudo, velocidad).

Al terminar el efecto del poder, el "wrapper" (envoltorio) simplemente se descarta, y la clase base permanece intacta y funcional.

3. Justificación Clean Code, SOLID y XP
Prevención de Deuda Técnica (Explosión Combinatoria): Resolver 5 poderes combinables mediante herencia tradicional requeriría crear 32 subclases (2^5). Al utilizar Decorator, el sistema se reduce a solo 6 clases modulares y mantenibles.

Single Responsibility Principle (SRP): El Builder tiene una única razón para cambiar (lógica de ensamblaje inicial); los decoradores tienen otra (lógica de bonificadores temporales).

Open/Closed Principle (OCP): En un entorno Ágil, si el Product Owner pide un nuevo poder (ej. Inmortalidad), creamos un ImmortalityDecorator sin riesgo de romper (o siquiera tocar) la clase base ni los demás poderes.

Testabilidad (TDD): Podemos crear pruebas unitarias aisladas para el ShieldDecorator inyectándole un mock básico, sin necesidad de instanciar un personaje complejo a través del Builder.
```
