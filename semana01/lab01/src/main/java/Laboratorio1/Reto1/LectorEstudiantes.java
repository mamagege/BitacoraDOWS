package Reto1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 1: Lector de Estudiantes por Consola
 * ============================================================================
 *
 * RESPONSABILIDAD (Principio SRP):
 * Esta clase tiene una sola responsabilidad: leer los datos de dos estudiantes
 * desde la entrada estándar (`System.in`) y construir los objetos `Estudiante`
 * correspondientes. NO imprime mensajes de presentación, eso lo delega a
 * `MensajeBienvenida`.
 *
 * FLUJO DE ENTRADA:
 * Para cada estudiante se leen en este orden:
 *   1. Nombre     (String)
 *   2. Semestre   (int)
 *   3. Edad       (int)
 *   4. Correo     (String)
 *
 * NOTA TÉCNICA - scanner.nextLine() después de nextInt():
 *   Cuando se llama a `scanner.nextInt()`, el `Scanner` lee el número pero
 *   deja el salto de línea '\n' en el buffer. El `scanner.nextLine()` vacío
 *   en las líneas 25 y 45 consume ese '\n' residual para evitar que la
 *   siguiente llamada `nextLine()` capture una cadena vacía.
 *
 * COLECCIÓN:
 * - `ArrayList<Estudiante>`: almacena los dos objetos creados.
 * - Se expone mediante `getEstudiantes()` para que otras clases la consuman
 *   sin acoplarse a la lógica de lectura.
 */
public class LectorEstudiantes {

    static ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public void leerDatos() {
        Scanner scanner = new Scanner(System.in);

        // ── Estudiante A ──────────────────────────────────────────────────────
        System.out.println("Estudiante A: ");
        String nombreA = scanner.nextLine();

        System.out.println("Semestre: ");
        int semestreA = scanner.nextInt();

        System.out.println("Edad: ");
        int edadA = scanner.nextInt();
        scanner.nextLine(); // Consume el '\n' residual tras nextInt()

        System.out.println("Correo: ");
        String correoA = scanner.nextLine();

        Estudiante estudianteA = new Estudiante(nombreA, edadA, correoA, semestreA);

        // ── Estudiante B ──────────────────────────────────────────────────────
        System.out.println("Estudiante B: ");
        String nombreB = scanner.nextLine();

        System.out.println("Semestre: ");
        int semestreB = scanner.nextInt();

        System.out.println("Edad: ");
        int edadB = scanner.nextInt();
        scanner.nextLine(); // Consume el '\n' residual tras nextInt()

        System.out.println("Correo: ");
        String correoB = scanner.nextLine();

        Estudiante estudianteB = new Estudiante(nombreB, edadB, correoB, semestreB);

        scanner.close();

        estudiantes.add(estudianteA);
        estudiantes.add(estudianteB);
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}
