package parte3_patrones.caso13_factory;

/**
 * Patrón: FACTORY METHOD (Creacional)
 * Producto abstracto: Define la interfaz de los reportes generados.
 */
public interface Report {
    void export(String title, String content);
    String getFormatName();
}
