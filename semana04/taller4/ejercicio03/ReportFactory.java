package ejercicio03;

/**
 * Patrón 2: FACTORY METHOD (Creacional)
 * Rol: Construye la instancia polimórfica adecuada de ReportGenerator según el tipo solicitado.
 */
public class ReportFactory {
    public enum Format {
        PDF, EXCEL, CSV
    }

    public static ReportGenerator createReport(Format format) {
        if (format == null) {
            throw new IllegalArgumentException("El formato de reporte no puede ser nulo");
        }
        return switch (format) {
            case PDF -> new PdfReport();
            case EXCEL -> new ExcelReport();
            case CSV -> new CsvReport();
        };
    }

    public static ReportGenerator createReport(String formatString) {
        if (formatString == null) {
            throw new IllegalArgumentException("El nombre del formato no puede ser nulo");
        }
        return switch (formatString.trim().toUpperCase()) {
            case "PDF" -> new PdfReport();
            case "EXCEL", "XLSX" -> new ExcelReport();
            case "CSV" -> new CsvReport();
            default -> throw new IllegalArgumentException("Formato de reporte no soportado: " + formatString);
        };
    }
}
