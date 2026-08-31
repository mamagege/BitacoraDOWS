package Reto6;

import java.util.HashMap;
import java.util.Map;

public class Comandos {
    public static void ejecutarComando(String comando){
        Map<String, Runnable> acciones = new HashMap<>();

        acciones.put("SALUDAR", () -> System.out.println
                ("¡Saludos, viajero del Tiempo y del código!"));

        acciones.put("DESPEDIR", () -> System.out.println
                ("Hasta la próxima compilación, viajero."));

        acciones.put("CANTAR", () -> System.out.println
                ("La la la... compilando melodías en tiempo real."));

        acciones.put("DANZAR", () -> System.out.println
                ("Girando en modo fiesta."));

        acciones.put("BROMEAR", () -> System.out.println
                ("¿Por qué la RAM rompió con la CPU? Porque necesitaba espacio..."));

        acciones.put("GRITAR", () -> System.out.println
                ("¡¡¡STACK OVERFLOOOOOW!!!"));

        acciones.put("SUSURRAR", () -> System.out.println
                ("psst... revisa el punto y coma de la línea 42."));

        acciones.put("ANALIZAR", () -> System.out.println
                ("Analizando datos... ¡Eres increíble!"));

        Runnable accion = acciones.get(comando);
        if (accion != null) {
            accion.run();
            } else {
                System.out.println("Comando desconocido");
        }
    }
}

