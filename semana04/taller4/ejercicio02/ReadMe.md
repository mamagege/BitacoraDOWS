README.md: Ejercicio #02 - Observer + Factory Method
Objetivo: Diseñar un sistema de notificaciones donde los pedidos cambian de estado y avisan por distintos canales, construyendo mensajes específicos para cada uno.

1. Rol de cada patrón
Observer: Permite que el emisor (el Pedido) se desacople por completo de los canales de notificación. El pedido actúa como el Subject (sujeto observable) y simplemente avisa a sus suscriptores activos que ocurrió un evento, sin importar si son 1 o 10 canales.

Factory Method: Centraliza y desacopla la construcción del mensaje. Debido a que un Email requiere HTML y un SMS requiere texto plano limitado a 160 caracteres, las fábricas concretas encapsulan esta lógica de formato para que el notificador solo se encargue de "enviar".

2. Cómo interactúan (Multivariable)
Cuando el Pedido cambia de estado (ej. enviado), itera sobre su lista de NotificationObserver activos y llama al método de actualización.

Cada observador concreto (ej. SmsNotifier) reacciona al evento y delega a su propia MessageFactory la tarea de construir el contenido del mensaje.

Una vez la fábrica retorna el Message debidamente formateado, el observador finaliza el flujo simulando el envío.

3. Justificación Arquitectónica (Clean Code, SOLID y XP)
Open/Closed Principle (OCP): En un entorno Ágil, si nos piden integrar notificaciones por WhatsApp en el próximo Sprint, solo creamos un WhatsAppNotifier y un WhatsAppMessageFactory. El código del Pedido no se toca. Cero riesgo de regresión.

Dependency Inversion Principle (DIP): Los notificadores no construyen los mensajes directamente con el operador new. Se inyecta una abstracción MessageFactory en el constructor, lo que es vital para la inyección de dependencias.

Testabilidad (TDD): Separar el formateo (Factory) del envío (Observer) nos permite hacer pruebas unitarias aisladas. Podemos verificar que el EmailMessageFactory genere correctamente el HTML sin tener que instanciar el servicio de envío real.