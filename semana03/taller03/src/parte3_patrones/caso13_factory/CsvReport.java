package parte3_patrones.caso13_factory;

public class CsvReport implements Report {
    @Override
    public void export(String title, String content) {
        System.out.printf("[CSV Report] Exportando archivo plano delimitado por comas para: '%s'.%n", title);
    }

    @Override
    public String getFormatName() {
        return "CSV";
    }
}
