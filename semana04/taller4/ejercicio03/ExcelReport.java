package ejercicio03;

public class ExcelReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3. [Formato Excel] Estructurando celdas en hojas .xlsx con estilos de bordes, fórmulas SUM() y tipografía.");
    }

    @Override
    protected void exportFile(String title) {
        System.out.printf("  4. [Exportación Excel] Guardando libro binario '%s.xlsx' con metadatos y gráficos embebidos.%n",
                title.toLowerCase().replace(" ", "_"));
    }

    @Override
    public String getFormatName() {
        return "EXCEL (XLSX)";
    }
}
