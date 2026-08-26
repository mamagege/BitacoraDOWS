//Taller 1- Juan Diego Gaitan

package ResolucionTaller;

import java.util.List;

/**
 * ============================================================================
 * RETO 15: Verificar ausencia de un elemento con noneMatch()
 * ============================================================================
 * 
 * OBJETIVO:
 * Dada una lista de nombres de usuario, verificar que ninguno tenga el nombre "root".
 * 
 * CONCEPTOS FUNCIONALES CLAVE:
 * 1. Stream.noneMatch(Predicate<T>):
 *    - Tipo: Operación Terminal (Eager / Short-Circuiting / Cortocircuito).
 *    - Complejidad: O(1) si el primer elemento es "root", O(N) si ninguno coincide.
 *    - Retorna `true` únicamente si NINGÚN elemento del stream cumple el predicado.
 * 
 * 2. Referencia a Método de Objeto Arbitrario (`"root"::equals`):
 *    - Equivalente a la lambda `usuario -> "root".equals(usuario)`.
 *    - Ventaja Defensiva: Usar `"root"::equals` es inmune a `NullPointerException`
 *      incluso si la lista contiene elementos `null`.
 */
public class reto15 {

    public static void main(String[] args) {
        List<String> usuarios = List.of(
                "juan", "maria", "admin", "pedro", "soporte"
        );

        // noneMatch evalúa que ningún elemento sea igual a "root"
        boolean esRoot = usuarios.stream()
                .noneMatch("root"::equals);

        // Salida esperada: Ningun usuario es root?: true
        System.out.println("Ningun usuario es root?: " + esRoot);
    }
}
