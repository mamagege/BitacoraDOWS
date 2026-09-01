package ejercicio03;

public class CsvReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3. [Formato CSV] Formateando líneas en texto plano delimitado por punto y coma (;) con encoding UTF-8.");
    }

    @Override
    protected void exportFile(String title) {
        System.out.printf("  4. [Exportación CSV] Escribiendo archivo ligero '%s.csv' para procesamiento batch.%n",
                title.toLowerCase().replace(" ", "_"));
    }

    @Override
    public String getFormatName() {
        return "CSV";
    }
}
