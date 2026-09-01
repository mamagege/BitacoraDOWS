# Patrón de Diseño: Chain of Responsibility

## ¿Qué es?

Es un patrón de diseño de comportamiento que permite pasar solicitudes a lo largo de una cadena de manejadores (handlers) encadenados dinámicamente.

## ¿Qué hace?

Cada manejador en la cadena evalúa si puede procesar la solicitud; si puede, la ejecuta; si no, la pasa automáticamente al siguiente eslabón. Esto desacopla por completo al emisor de la solicitud de los múltiples receptores potenciales.

## ¿Cómo se implementa?

- **Handler (Clase Abstracta / Interfaz Base)**: Declara un método para establecer el siguiente eslabón (`setNext()`) y un método central de procesamiento u orquestación.
- **Concrete Handlers (Manejadores Concretos)**: Heredan del handler base, evalúan su propia condición de negocio (`canHandle()`) y deciden si resuelven la tarea o delegan al sucesor.

## ¿Cómo identificarlo (Cuándo usarlo y Smells que resuelve)?

**Code Smells**: Bloques gigantes y anidados de `if-else` o sentencias `switch` que verifican permisos, roles o tipos de datos para saber quién debe ejecutar una operación.

**Uso ideal**: Cuando el sistema necesita procesar una solicitud a través de múltiples filtros, validadores o niveles de aprobación donde la ruta puede cambiar o expandirse en el futuro. Es vital en metodologías Ágiles y XP porque cumple con el Open/Closed Principle (OCP): puedes añadir un nuevo eslabón a la cadena sin tocar una sola línea de código de los validadores existentes.

## Ejemplos de Implementación en Java

### Ejemplo 1: Flujo de Aprobación de Documentos (Inspirado en el Taller - Ejercicio #07)

Modela un documento corporativo que debe pasar por diferentes revisiones jerárquicas (Líder Técnico, Jurídico) antes de ser aprobado o rechazado.

```java
// 1. Handler Base Abstracto
abstract class DocumentHandler {
    private DocumentHandler next;

    public DocumentHandler setNext(DocumentHandler next) {
        this.next = next;
        return this.next; // Fluent interface para encadenar
    }

    public void handle(String docType, double amount) {
        if (canHandle(docType, amount)) {
            process();
        } else if (next != null) {
            System.out.println(" -> [" + this.getClass().getSimpleName() + "] No puede procesar. Derivando...");
            next.handle(docType, amount);
        } else {
            System.out.println(" -> Solicitud rechazada: Ningún eslabón pudo procesarla.");
        }
    }

    protected abstract boolean canHandle(String docType, double amount);
    protected abstract void process();
}

// 2. Concrete Handlers
class TechnicalLeadHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(String docType, double amount) {
        return docType.equals("TECHNICAL") && amount <= 5000;
    }
    @Override
    protected void process() {
        System.out.println("[TechnicalLead] Aprobación técnica concedida.");
    }
}

class LegalHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(String docType, double amount) {
        return docType.equals("CONTRACT");
    }
    @Override
    protected void process() {
        System.out.println("[LegalHandler] Validación de cláusulas legales completada con éxito.");
    }
}

// Uso
public class ChainDemo1 {
    public static void main(String[] args) {
        DocumentHandler chain = new TechnicalLeadHandler();
        chain.setNext(new LegalHandler());

        System.out.println("--- Evaluando Contrato ---");
        chain.handle("CONTRACT", 15000);
    }
}
```

### Ejemplo 2: Sistema de Soporte Técnico por Niveles (Escalabilidad de Tickets)

Un centro de atención al cliente donde los problemas se resuelven por niveles de complejidad creciente (Nivel 1, Nivel 2, Ingeniero Senior).

```java
class SupportTicket {
    private final String issue;
    private final int complexityLevel; // 1: Fácil, 2: Medio, 3: Crítico

    public SupportTicket(String issue, int complexityLevel) {
        this.issue = issue;
        this.complexityLevel = complexityLevel;
    }
    public int getComplexityLevel() { return complexityLevel; }
    public String getIssue() { return issue; }
}

abstract class SupportHandler {
    protected SupportHandler next;
    public void setNext(SupportHandler next) { this.next = next; }
    abstract void handleRequest(SupportTicket ticket);
}

class LevelOneSupport extends SupportHandler {
    @Override
    void handleRequest(SupportTicket ticket) {
        if (ticket.getComplexityLevel() <= 1) {
            System.out.println("[Nivel 1] Ticket resuelto: " + ticket.getIssue());
        } else if (next != null) {
            next.handleRequest(ticket);
        }
    }
}

class SeniorEngineerSupport extends SupportHandler {
    @Override
    void handleRequest(SupportTicket ticket) {
        System.out.println("[Senior] Ticket crítico resuelto mediante depuración profunda: " + ticket.getIssue());
    }
}

// Uso
public class ChainDemo2 {
    public static void main(String[] args) {
        SupportHandler l1 = new LevelOneSupport();
        SupportHandler senior = new SeniorEngineerSupport();
        l1.setNext(senior);

        SupportTicket ticket = new SupportTicket("Error de concurrencia en Base de Datos", 3);
        l1.handleRequest(ticket);
    }
}
```

### Ejemplo 3: Cadena de Filtros de Seguridad HTTP (Middleware)

Valida peticiones web antes de que lleguen al servidor de negocio, evaluando autenticación, límites de tasa (rate limit) y control de IP.

```java
class HttpRequest {
    private boolean isAuthenticated = true;
    private int requestCount = 5;
    private boolean isIpBlocked = false;

    public boolean isAuthenticated() { return isAuthenticated; }
    public int getRequestCount() { return requestCount; }
    public boolean isIpBlocked() { return isIpBlocked; }
}

abstract class HttpFilter {
    private HttpFilter next;
    public HttpFilter setNext(HttpFilter next) { this.next = next; return next; }

    public void execute(HttpRequest req) {
        if (check(req)) {
            if (next != null) next.execute(req);
            else System.out.println("[HTTP Server] Petición aprobada y ejecutada con éxito.");
        } else {
            System.out.println("[Security Alert] Petición bloqueada en el filtro: " + this.getClass().getSimpleName());
        }
    }
    protected abstract boolean check(HttpRequest req);
}

class AuthFilter extends HttpFilter {
    @Override
    protected boolean check(HttpRequest req) { return req.isAuthenticated(); }
}

class RateLimitFilter extends HttpFilter {
    @Override
    protected boolean check(HttpRequest req) { return req.getRequestCount() < 10; }
}

// Uso
public class ChainDemo3 {
    public static void main(String[] args) {
        HttpFilter filterChain = new AuthFilter();
        filterChain.setNext(new RateLimitFilter());

        HttpRequest request = new HttpRequest();
        filterChain.execute(request);
    }
}
```
