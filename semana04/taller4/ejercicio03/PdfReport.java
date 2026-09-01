package ejercicio03;

public class PdfReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3. [Formato PDF] Generando documento vectorial con logo, encabezados corporativos y márgenes.");
    }

    @Override
    protected void exportFile(String title) {
        System.out.printf("  4. [Exportación PDF] Guardando stream binario '%s.pdf' con compresión de fuentes y firma digital.%n",
                title.toLowerCase().replace(" ", "_"));
    }

    @Override
    public String getFormatName() {
        return "PDF";
    }
}
