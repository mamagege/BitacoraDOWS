package parte3_patrones.caso13_factory;

/**
 * Fábrica de Reportes: Encapsula la lógica de creación según el formato deseado,
 * desacoplando al cliente de los constructores concretos.
 */
public class ReportFactory {
    public enum ReportFormat {
        PDF, EXCEL, CSV
    }

    public static Report createReport(ReportFormat format) {
        return switch (format) {
            case PDF -> new PdfReport();
            case EXCEL -> new ExcelReport();
            case CSV -> new CsvReport();
        };
    }
}
