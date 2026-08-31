package Reto3;

/**
 * ============================================================================
 * LABORATORIO 1 - RETO 3: Descifrador de Mensajes (Inversión con Stream)
 * ============================================================================
 *
 * OBJETIVO:
 * Invertir el orden de caracteres de una cadena de texto de forma funcional.
 * Ej: "hola" → "aloh"
 *
 * CONCEPTOS FUNCIONALES CLAVE:
 *
 * 1. `String.chars()`:
 *    - Retorna un `IntStream` donde cada elemento es el código Unicode (int)
 *      de cada caracter de la cadena. Es la puerta de entrada a la Stream API
 *      para procesar cadenas de texto a nivel de caracteres.
 *
 * 2. `.mapToObj(c -> (char) c)`:
 *    - Convierte cada código Unicode entero de vuelta a un `Character` (Object),
 *      transformando el `IntStream` en un `Stream<Character>`.
 *    - Esto es necesario porque los `Collectors` operan sobre objetos,
 *      no sobre tipos primitivos.
 *
 * 3. `.collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)`:
 *    - Forma avanzada de `collect` de 3 argumentos (supplier, accumulator, combiner):
 *      * `StringBuffer::new`:   Crea un nuevo `StringBuffer` como contenedor mutable.
 *      * `StringBuffer::append` (acumulador): Agrega cada `Character` al buffer.
 *      * `StringBuffer::append` (combinador): Combina buffers parciales en streams paralelos.
 *    - Equivalente más legible: `Collectors.joining()` pero aquí se usa para
 *      obtener directamente un `StringBuffer` (y así poder llamar `.reverse()`).
 *
 * 4. `.reverse()`:
 *    - Método de `StringBuffer` que invierte el contenido en el mismo objeto (mutable).
 *    - Eficiencia: O(N/2) comparado con construcción manual de una cadena invertida.
 *
 * 5. `.toString()`:
 *    - Materializa el `StringBuffer` en un `String` inmutable final.
 */
public class Descifrador {

    /**
     * Descifra (invierte) el mensaje recibido caracter por caracter usando Streams.
     *
     * @param mensaje El texto a invertir.
     * @return El texto con sus caracteres en orden inverso.
     */
    public static String descrifar(String mensaje) {
        return mensaje.chars()
                // Paso 1: Convierte String en IntStream de códigos Unicode
                .mapToObj(c -> (char) c)
                // Paso 2: Convierte cada int a Character → Stream<Character>
                .collect(
                        StringBuffer::new,         // Supplier: crea el contenedor
                        StringBuffer::append,      // Accumulator: agrega cada char
                        StringBuffer::append       // Combiner: merge en parallel streams
                )
                // Paso 3: Invierte el StringBuffer y convierte a String inmutable
                .reverse().toString();
    }
}
