package ejercicio03;

import java.util.List;

/**
 * Patrón 1: TEMPLATE METHOD (Comportamiento)
 * Rol: Define el esqueleto del algoritmo de generación de reportes en un método final generate(),
 * garantizando el orden de ejecución de los pasos fijos y delegando los pasos variables a las subclases.
 */
public abstract class ReportGenerator {
    protected List<String> rawData;
    protected String processedDataSummary;

    // Método Plantilla (Template Method) - Invariable y final
    public final void generate(String reportTitle) {
        System.out.printf("=== Iniciando Pipeline de Reporte: '%s' [%s] ===%n", reportTitle, getFormatName());
        fetchData();          // Paso fijo 1: Extracción de datos
        processData();        // Paso fijo 2: Procesamiento y agregación
        applyFormat();        // Paso variable 3: Renderizado según formato
        exportFile(reportTitle); // Paso variable 4: Emisión binaria/archivo
        System.out.printf("=== Reporte '%s' finalizado con éxito ===%n%n", reportTitle);
    }

    // Paso Fijo 1
    private void fetchData() {
        System.out.println("  1. [DB / Data Warehouse] Consultando registros transaccionales...");
        this.rawData = List.of("TRX-001: $1,200.00", "TRX-002: $450.50", "TRX-003: $3,100.00");
    }

    // Paso Fijo 2
    private void processData() {
        System.out.printf("  2. [Analytics Core] Agregando %d registros y calculando totales...%n", rawData.size());
        this.processedDataSummary = "Total Transacciones: 3 | Volumen Total: $4,750.50 USD";
    }

    // Pasos Variables (Hooks / Primitivas abstractas)
    protected abstract void applyFormat();
    protected abstract void exportFile(String title);
    public abstract String getFormatName();
}
