Objetivo: Diseñar un editor de imágenes donde se puedan aplicar filtros acumulativos en cualquier orden, garantizando que cada acción se pueda deshacer y rehacer de manera individual.  

1. Rol de cada patrónDecorator (Transformación No Destructiva): Aplica filtros visuales (blanco y negro, sepia, etc.) de forma acumulativa envolviendo la imagen original. Su rol es evitar modificar la imagen base o crear una subclase por cada combinación de filtros.  Command (Historial Transaccional): Encapsula cada solicitud de filtro como un objeto independiente (ApplyFilterCommand). Su rol es aislar la ejecución de la acción para poder almacenarla en estructuras de datos (pilas o stacks) y revertirla (Undo).  

2. Cómo interactúan (Multivariable)El usuario solicita un filtro y el sistema instancia un Command específico.  Al llamar a execute(), el comando toma la imagen actual y la envuelve usando el Decorator correspondiente, guardando una referencia del estado anterior.  Este comando se apila en el historial de "ejecutados". Si el usuario hace undo, se extrae el último comando de la pila y se llama a su método undo(), el cual descarta el último envoltorio (wrapper) y restaura la imagen a su estado previo. El comando pasa entonces a la pila de "deshechos" para un posible redo.  

3. Justificación Arquitectónica (Clean Code, SOLID y XP)Open/

Closed Principle (OCP): Si el equipo de diseño UI pide un nuevo filtro de "Pixelado", solo creamos la clase PixelateDecorator. El historial, el editor base y los demás filtros quedan intactos.

Single Responsibility Principle (SRP): El decorador se preocupa exclusivamente por el cálculo de píxeles/renderizado. El comando se preocupa exclusivamente por la gestión del estado en el tiempo (guardar y restaurar).

Test-Driven Development (TDD): Separar el renderizado gráfico de las operaciones de historial nos permite probar la lógica de los botones Deshacer/Rehacer con comandos Mock, sin necesidad de cargar buffers de imágenes reales en memoria, acelerando la integración continua.