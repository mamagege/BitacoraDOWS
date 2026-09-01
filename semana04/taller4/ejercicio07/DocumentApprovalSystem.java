/**
 * SISTEMA DE APROBACIÓN DE DOCUMENTOS - DEMOSTRACIÓN DE CHAIN OF RESPONSIBILITY
 * + STATE
 * Enfoque: Clean Code, SOLID, Cero sentencias switch/if de estado.
 */

// ==========================================
// 1. PATRÓN STATE: Ciclo de vida del Documento
// ==========================================
interface DocumentState {
    void approve(Document doc);

    void reject(Document doc);

    String getName();
}

class DraftState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("[State] Documento enviado a revisión.");
        doc.setState(new InReviewState());
    }

    @Override
    public void reject(Document doc) {
        System.out.println("[State] Un borrador no puede ser rechazado. Se descarta.");
    }

    @Override
    public String getName() {
        return "BORRADOR";
    }
}

class InReviewState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("[State] Revisiones completadas. Documento aprobado de forma final.");
        doc.setState(new ApprovedState());
    }

    @Override
    public void reject(Document doc) {
        System.out.println("[State] Se detectaron errores. Documento rechazado.");
        doc.setState(new RejectedState());
    }

    @Override
    public String getName() {
        return "EN REVISIÓN";
    }
}

class ApprovedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("[State] Ya está aprobado. Sin efecto.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("[State] No se puede rechazar un documento ya aprobado.");
    }

    @Override
    public String getName() {
        return "APROBADO";
    }
}

class RejectedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("[State] No se puede aprobar un documento rechazado.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("[State] Ya está rechazado. Sin efecto.");
    }

    @Override
    public String getName() {
        return "RECHAZADO";
    }
}

// ==========================================
// 2. CONTEXTO: El Documento
// ==========================================
class Document {
    private DocumentState state;
    private final String content;
    private final String type;

    public Document(String content, String type) {
        this.content = content;
        this.type = type;
        this.state = new DraftState(); // Estado inicial
    }

    // XP: Delegación absoluta al estado actual. Cero sentencias if.
    public void approve() {
        state.approve(this);
    }

    public void reject() {
        state.reject(this);
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public String getStatusInfo() {
        return "[Documento: " + content + " | Estado: " + state.getName() + "]";
    }
}

// ==========================================
// 3. PATRÓN CHAIN OF RESPONSIBILITY: Los Revisores
// ==========================================
abstract class DocumentHandler {
    private DocumentHandler next;

    public DocumentHandler setNext(DocumentHandler next) {
        this.next = next;
        return this.next; // Retorna el siguiente para permitir Fluent Interface (encadenamiento)
    }

    public void handle(Document doc) {
        if (canHandle(doc)) {
            process(doc);
        } else if (next != null) {
            System.out.println(" -> [" + this.getClass().getSimpleName() + "] Ignora. Pasando al siguiente...");
            next.handle(doc);
        } else {
            System.out.println(" -> Fin de la cadena.");
        }
    }

    protected abstract boolean canHandle(Document doc);

    protected abstract void process(Document doc);
}

class LeaderHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) {
        // El líder aprueba documentos técnicos
        return doc.getType().equals("TECNICO");
    }

    @Override
    protected void process(Document doc) {
        System.out.println("[LeaderHandler] Revisando factibilidad técnica...");
        doc.approve(); // Da su visto bueno
    }
}

class LegalHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) {
        // Jurídico aprueba contratos
        return doc.getType().equals("CONTRATO");
    }

    @Override
    protected void process(Document doc) {
        System.out.println("[LegalHandler] Revisando cláusulas legales...");
        // Simulamos un fallo legal
        System.out.println("[LegalHandler] ¡Alerta! Cláusula inválida encontrada.");
        doc.reject();
    }
}

// ==========================================
// 4. DEMOSTRACIÓN FUNCIONAL
// ==========================================
public class DocumentApprovalSystem {
    public static void main(String[] args) {
        // 1. Configuramos la cadena (Puede inyectarse via Factory)
        DocumentHandler pipeline = new LeaderHandler();
        pipeline.setNext(new LegalHandler());

        System.out.println("=== CASO 1: DOCUMENTO TÉCNICO ===");
        Document doc1 = new Document("Arquitectura AWS", "TECNICO");
        System.out.println("Inicio: " + doc1.getStatusInfo());

        doc1.approve(); // Autor lo saca de borrador
        pipeline.handle(doc1); // Entra a la cadena
        System.out.println("Resultado: " + doc1.getStatusInfo());

        System.out.println("\n=== CASO 2: CONTRATO LEGAL ===");
        Document doc2 = new Document("Acuerdo de Confidencialidad", "CONTRATO");
        System.out.println("Inicio: " + doc2.getStatusInfo());

        doc2.approve(); // Autor lo saca de borrador
        pipeline.handle(doc2); // Entra a la cadena
        System.out.println("Resultado: " + doc2.getStatusInfo());
    }
}