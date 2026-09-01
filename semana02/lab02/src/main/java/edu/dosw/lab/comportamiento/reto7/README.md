# Reto #7: El Rover Explorador de Marte

## 1. Patrón de Diseño
* **Categoría:** Comportamiento
* **Patrón Utilizado:** **Command**

---

## 2. Justificación
El equipo de control en la Tierra debe operar remotamente los módulos físicos del rover *Chibchombo* (`Motor`, `Brazo`, `Cámara`, `Taladro`). Se requiere parametrizar acciones (metros, segundos, profundidad en cm), registrar el nombre del operador que emitió cada orden, mantener una bitácora/historial completo de ejecución y permitir revertir o deshacer (`undo`) cualquier comando individual ejecutado.

El patrón **Command** fue elegido porque:
1. **Encapsula cada solicitud como un objeto independiente:** Convierte las operaciones sobre el rover en objetos de primera clase con sus propios parámetros, operador y estado.
2. **Soporta operaciones reversibles (`undo`):** Cada comando concreto conoce exactamente cómo realizar la acción inversa sobre el receptor para restaurar el estado previo.
3. **Desacopla al invocador del receptor:** La consola de mando o el secuenciador de misiones ([`ControladorMision`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/ControladorMision.java)) ejecuta, encola y deshace órdenes sin conocer la lógica interna de los actuadores del rover ([`RoverChibchombo`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/RoverChibchombo.java)).

---

## 3. Aplicación y Estructura de Clases

| Clase / Archivo | Rol en el Patrón / Sistema |
| :--- | :--- |
| [`ComandoRover.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/ComandoRover.java) | **Command Interface:** Declara los métodos `ejecutar()`, `deshacer()`, `getOperador()`, `getDescripcion()`, `isDeshecho()`. |
| [`BaseComandoRover.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/BaseComandoRover.java) | **Abstract Command:** Clase base que encapsula la referencia al operador, el receptor `RoverChibchombo` y el flag `deshecho`. |
| [`AvanzarMotorCommand.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/AvanzarMotorCommand.java) | **Concrete Command:** Ejecuta avance en metros y su `undo` retrocede los mismos metros. |
| [`RetrocederMotorCommand.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/RetrocederMotorCommand.java) | **Concrete Command:** Ejecuta retroceso en metros y su `undo` avanza los mismos metros. |
| [`GrabarCamaraCommand.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/GrabarCamaraCommand.java) | **Concrete Command:** Inicia grabación por $N$ segundos y su `undo` detiene la cámara. |
| [`PerforarTaladroCommand.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/PerforarTaladroCommand.java) | **Concrete Command:** Perfora $N$ cm y su `undo` retrae el taladro. |
| [`RecogerBrazoCommand.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/RecogerBrazoCommand.java) | **Concrete Command:** Recoge muestra y su `undo` suelta la muestra. |
| [`RoverChibchombo.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/RoverChibchombo.java) | **Receiver:** Contiene la lógica real de hardware de los módulos del rover. |
| [`ControladorMision.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/ControladorMision.java) | **Invoker:** Gestiona la lista de historial, ejecuta comandos y ejecuta `deshacerComando(indice)`. |
| [`Reto7RoverMarte.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto7/Reto7RoverMarte.java) | **Client / Presentation:** Coordina la sesión interactiva con los operadores, simula la misión y presenta el historial. |

---

## 4. Cómo Usarlo (Ejemplo de Código)

```java
// 1. Crear el receptor (Rover) y el invocador (Controlador)
RoverChibchombo rover = new RoverChibchombo();
ControladorMision controlador = new ControladorMision();

// 2. Crear comandos vinculando receptor, operador y parámetros
ComandoRover cmd1 = new AvanzarMotorCommand(rover, "Camila", 12);
ComandoRover cmd2 = new GrabarCamaraCommand(rover, "Camila", 30);
ComandoRover cmd3 = new PerforarTaladroCommand(rover, "Camila", 15);

// 3. Ejecutar a través del invocador
controlador.ejecutarComando(cmd1);
controlador.ejecutarComando(cmd2);
controlador.ejecutarComando(cmd3);

// 4. Deshacer una acción específica por índice (ej. Acción #3)
controlador.deshacerComando(2); // Deshace la perforación del taladro

// 5. Imprimir bitácora con trazabilidad de operadores
controlador.imprimirHistorial();
```
