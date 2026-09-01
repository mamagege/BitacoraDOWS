//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * RETO FINAL: Pipeline Completo Multietapa
 * (filter · map · sorted · peek · collect)
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de estudiantes con nombre y promedio de notas:
 * 1. Filtrar estudiantes aprobados (nota >= 3.0).
 * 2. Convertir los nombres a mayúsculas.
 * 3. Ordenar por promedio de mayor a menor (descendente).
 * 4. Registrar en consola cada estudiante procesado (peek).
 * 5. Guardar el resultado en una nueva lista.
 * 
 * CONCEPTOS ARQUITECTÓNICOS Y FUNCIONALES CLAVE:
 * 1. Java Records (Java 16+):
 *    - `record Estudiante(String nombre, double nota)` modela datos inmutables
 *      con getters, constructor canónico, `equals()`, `hashCode()` y `toString()`.
 * 
 * 2. Composición de Canalización (Pipeline Composition):
 *    - `filter()` [Intermedia / Stateless]: Reduce el volumen de datos al inicio (Fail-Fast).
 *    - `map()` [Intermedia / Stateless]: Modifica el DTO/Record a nombres en mayúsculas.
 *    - `sorted()` [Intermedia / Stateful]: Requiere buffer completo para ordenar por nota descendente.
 *    - `peek()` [Intermedia / Stateless]: Diagnóstico de observabilidad paso a paso.
 *    - `collect()` [Terminal / Eager]: Materializa la lista final.
 * 
 * 3. Eficiencia:
 *    - Filtrar ANTES de mapear y ordenar minimiza drásticamente el consumo de memoria
 *      y ciclos de comparación de `sorted()`.
 */
public class retoFinal {

    public static void main(String[] args) {
        // Record inmutable local para modelar al estudiante
        record Estudiante(String nombre, double nota) {
            @Override
            public String toString() {
                return nombre + "(" + nota + ")";
            }
        }

        // Fuente de datos
        List<Estudiante> estudiantes = List.of(
                new Estudiante("Ana", 4.5),
                new Estudiante("Carlos", 3.2),
                new Estudiante("Pedro", 2.8),
                new Estudiante("Laura", 4.8),
                new Estudiante("Andres", 3.9),
                new Estudiante("Maria", 2.6)
        );

        // Canalización Funcional Completa:
        List<Estudiante> estudiantesAprobados = estudiantes.stream()
                // Paso 1: Filtrar solo aprobados (nota >= 3.0)
                .filter(e -> e.nota() >= 3.0)
                // Paso 2: Transformar a nuevo Record con nombre en MAYÚSCULAS
                .map(e -> new Estudiante(e.nombre().toUpperCase(), e.nota()))
                // Paso 3: Ordenar descendentemente por nota
                .sorted(Comparator.comparingDouble(Estudiante::nota).reversed())
                // Paso 4: Trazabilidad / Logging intermedio con peek
                .peek(e -> System.out.println("Procesando: " + e.nombre() + " " + e.nota()))
                // Paso 5: Materializar resultado en lista
                .collect(Collectors.toList());

        // Salida final esperada: [LAURA(4.8), ANA(4.5), ANDRES(3.9), CARLOS(3.2)]
        System.out.println("\nLista final: " + estudiantesAprobados);
    }
}
