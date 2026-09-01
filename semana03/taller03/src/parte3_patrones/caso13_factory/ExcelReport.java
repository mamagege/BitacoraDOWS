package parte3_patrones.caso13_factory;

public class ExcelReport implements Report {
    @Override
    public void export(String title, String content) {
        System.out.printf("[Excel Report] Generando libro .xlsx con celdas formateadas, fórmulas y estilos para: '%s'.%n", title);
    }

    @Override
    public String getFormatName() {
        return "EXCEL (XLSX)";
    }
}
