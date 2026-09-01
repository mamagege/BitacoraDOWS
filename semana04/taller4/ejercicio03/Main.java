package ejercicio03;

/**
 * Demostración de ejecución para el Ejercicio #03: Sistema de Reportes Empresariales.
 * Patrones combinados: Template Method + Factory Method.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  EJERCICIO #03: SISTEMA DE REPORTES EMPRESARIALES");
        System.out.println("  Patrones Combinados: TEMPLATE METHOD + FACTORY METHOD");
        System.out.println("================================================================================\n");

        // 1. Reporte PDF
        ReportGenerator pdfGen = ReportFactory.createReport(ReportFactory.Format.PDF);
        pdfGen.generate("Balance Financiero Anual 2026");

        // 2. Reporte Excel
        ReportGenerator excelGen = ReportFactory.createReport(ReportFactory.Format.EXCEL);
        excelGen.generate("Auditoría de Inventarios Q3");

        // 3. Reporte CSV
        ReportGenerator csvGen = ReportFactory.createReport("CSV");
        csvGen.generate("Exportación Transaccional para Data Lake");

        System.out.println("✓ Verificación del Ejercicio #03 finalizada exitosamente.");
    }
}
