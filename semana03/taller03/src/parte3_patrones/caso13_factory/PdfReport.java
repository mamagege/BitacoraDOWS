package parte3_patrones.caso13_factory;

public class PdfReport implements Report {
    @Override
    public void export(String title, String content) {
        System.out.printf("[PDF Report] Compilando vectorialmente documento PDF: '%s'. Páginas con footer y firma criptográfica.%n", title);
    }

    @Override
    public String getFormatName() {
        return "PDF";
    }
}
