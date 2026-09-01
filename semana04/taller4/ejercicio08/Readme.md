Objetivo: Diseñar un sistema donde el cliente configure un pedido complejo paso a paso, y al confirmarse, se disparen procesos asíncronos en múltiples subsistemas sin acoplar el dominio central.  

1. Rol de cada patrónBuilder (Creación Segura): Controla la instanciación caótica. Permite configurar tamaño, carne, pan, acompañamientos y extras paso a paso. Su rol fundamental en Clean Code es garantizar que el Order nazca completo, válido y siendo 100% inmutable, erradicando el anti-patrón Telescoping Constructor (constructores con docenas de parámetros).  Observer (Desacoplamiento de Eventos): Maneja la reacción post-creación. Notifica a los departamentos (Cocina, Facturación, Domicilios) que el pedido ha sido confirmado, aislando al Order de las implementaciones de estos servicios.  


2. Cómo interactúan (Multivariable)En la fase de configuración, el usuario interactúa únicamente con el OrderBuilder, añadiendo los ingredientes deseados.  Al llamar a build(), el Builder valida invariantes y retorna un objeto Order inmutable.  En la fase de ejecución, el sistema invoca order.confirm(). Este único comando muta el estado de negocio interno a "confirmado" y hace que el Order (Sujeto) notifique a todos sus Observers adjuntos. Cada departamento reacciona con su propia lógica de forma independiente.  

3. Justificación Arquitectónica (Clean Code, SOLID y XP)Inmutabilidad y Thread-Safety: Al usar un Builder, el pedido es inmutable una vez construido. En sistemas concurrentes de alta demanda (como una app de delivery), evitar que un hilo modifique los ingredientes mientras otro calcula la factura elimina errores de concurrencia de raíz.

Single Responsibility (SRP) y Open/Closed (OCP): El pedido solo sabe "ser un pedido". No sabe cómo cocinarlo, cobrarlo o rutearlo. Si mañana el Product Owner pide notificar también a un "Sistema de Lealtad (Puntos)", agregamos un LoyaltyServiceObserver sin tocar ni una sola línea de la clase Order.Testabilidad (TDD): Separar la creación de la reacción es vital en XP. Podemos probar que la hamburguesa se arma correctamente (probando el Builder), y luego probar que el evento de confirmación funciona usando un MockObserver, evitando levantar servicios de facturación en las pruebas unitarias.


