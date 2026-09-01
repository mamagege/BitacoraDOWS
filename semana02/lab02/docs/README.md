
#Respuestas preguntas teóricas

1 ¿Qué ventaja ofrece el polimorfismo en el diseño de clases frente al uso de múltiples condicionales para determinar el comportamiento de un objeto?
- El polimorfismo permite que diferentes clases respondan a un mismo mensaje con su propia lógica. Esto elimina bloques largos de código condicional, lo que hace que el sistema sea más fácil de mantener y de extender con nuevas funciones.

2 ¿Por qué una clase inmutable puede mejorar la seguridad en un sistema?
- Una clase inmutable mejora la seguridad de un sistema porque sus datos no pueden cambiar después de crearse. Esto evita modificaciones por error, ataques de cambio de estado y problemas al usar varios procesos a la vez.

3 ¿Qué problema podría aparecer en un sistema si los atributos de las clases se mantienen públicos en lugar de privados con getters y setters controlados?
- Mantener los atributos públicos expone el estado interno del objeto. Esto rompe el principio de encapsulamiento, permitiendo que cualquier parte del código modifique los datos sin control, lo que genera errores difíciles de rastrear, acoplamiento fuerte y falta de flexibilidad para cambiar la lógica interna en el futuro.

4 Según el principio Abierto/Cerrado, ¿cómo deberíamos modificar el sistema si queremos añadir una nueva funcionalidad sin alterar el código existente?
- Según el Principio Abierto/Cerrado, modifica el sistema usando abstracciones, interfaces o clases abstractas. Crea código nuevo que implementa estas reglas en lugar de tocar el código viejo. Esto permite extender el comportamiento sin riesgo de romper lo que ya funciona.

5 ¿Por qué es importante que una clase cumpla con el Principio de Única Responsabilidad? Da un ejemplo donde se vulnere.
- Es importante porque reduce el acoplamiento y facilita el mantenimiento, asegurando que una clase tenga un solo motivo para cambiar. Se vulnera cuando una classe Usuario maneja la lógica de negocio (validar datos personales) y al mismo tiempo incluye métodos para conectarse a la base de datos o enviar correos electrónicos.

6 ¿Qué es y para qué usamos el pom.xml?
- Es el archivo de configuración central (Project Object Model) en proyectos de Java basados en Maven. Se utiliza para administrar automáticamente las dependencias (librerías externas), definir las versiones del proyecto y configurar los plugins necesarios para compilar, testear y empaquetar la aplicación.

7 ¿Qué diferencia hay entre mvn compile, mvn package y mvn install?
- mvn compile traduce el código fuente a bytecode (.class). mvn package ejecuta la compilación y además agrupa el código en un formato distribuible (como un archivo .jar o .war). mvn install realiza los pasos anteriores y adicionalmente copia ese paquete en el repositorio local (carpeta .m2) para que otros proyectos en tu máquina puedan usarlo como dependencia.

8 ¿Qué diferencia existe entre una interfaz y una clase abstracta?
- Una interfaz define que se debe hacer sin mantener estado, permitiendo que una clase implemente múltiples interfaces a la vez. Una clase abstracta permite definir tanto el contrato como comportamiento base y mantener estado osea los atributos, pero limita a las clases hijas a la herencia simple.
