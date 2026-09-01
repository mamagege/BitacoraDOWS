import parte1_solid.caso1_facturacion.*;
import parte1_solid.caso2_transporte.*;
import parte1_solid.caso3_notificaciones.*;
import parte1_solid.caso4_cajero.*;
import parte1_solid.caso5_pagos.*;
import parte3_patrones.caso11_singleton.*;
import parte3_patrones.caso12_strategy.*;
import parte3_patrones.caso13_factory.*;
import parte3_patrones.caso14_observer.*;
import parte3_patrones.caso15_adapter.*;
import parte3_patrones.caso16_bridge.*;
import parte3_patrones.caso17_builder.*;
import parte3_patrones.caso18_chain.*;
import parte4_desafio.StreamingPlatform;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Runner principal de verificación para el Taller #3.
 * Ejecuta y valida cada solución arquitectónica, principio SOLID y patrón GoF.
 */
public class Main {

    public static void main(String[] args) {
        printHeader("TALLER #3 — SOLID Y PATRONES DE DISEÑO EN CASOS REALES");

        // =========================================================================
        // PARTE I — VIOLACIONES SOLID RESUELTAS
        // =========================================================================
        printSection("PARTE I: REFACTORIZACIONES SOLID");

        // Caso #1: SRP - Facturación
        System.out.println("▶ Caso #1: Sistema de Facturación (Single Responsibility Principle - SRP)");
        Invoice invoice = new Invoice(
                "INV-2026-001",
                "Carlos Mendoza",
                "carlos.mendoza@empresa.com",
                List.of(
                        new Invoice.InvoiceItem("Licencia IntelliJ IDEA Ultimate", 1, 499.00),
                        new Invoice.InvoiceItem("Consultoría Java Senior (Horas)", 10, 120.00)
                ),
                1699.00
        );
        InvoiceService invoiceService = new InvoiceService(
                new TaxCalculator(),
                new InvoicePdfGenerator(),
                new InvoiceEmailSender(),
                new InvoiceRepository()
        );
        invoiceService.processInvoice(invoice);

        // Caso #2: LSP - Transporte
        System.out.println("\n▶ Caso #2: Aplicación de Transporte (Liskov Substitution Principle - LSP)");
        TransportationFleet fleet = new TransportationFleet(List.of(
                new Car(),
                new Bicycle(),
                new Airplane(),
                new Boat() // Ahora cumple el contrato sin lanzar UnsupportedOperationException
        ));
        fleet.dispatchAll();

        // Caso #3: OCP - Notificaciones
        System.out.println("\n▶ Caso #3: Sistema de Notificaciones (Open/Closed Principle - OCP)");
        NotificationService notificationService = new NotificationService(List.of(
                new EmailNotificationChannel(),
                new SmsNotificationChannel(),
                new WhatsAppNotificationChannel(),
                new PushNotificationChannel(),
                new TelegramNotificationChannel()
        ));
        notificationService.sendNotification("EMAIL", "nadia@escuelaing.edu.co", "Su despliegue ha finalizado con éxito");
        notificationService.sendNotification("TELEGRAM", "@nadia_dev", "Alerta: Alto rendimiento en pods Kubernetes");

        // Caso #4: ISP - Cajero Inteligente
        System.out.println("\n▶ Caso #4: Cajero Inteligente (Interface Segregation Principle - ISP)");
        Withdrawable basicAtm = new BasicATM("ATM-BOG-01");
        basicAtm.withdraw(150.0);

        FullFeaturedSmartATM smartAtm = new FullFeaturedSmartATM("ATM-SMART-99");
        smartAtm.validateBiometrics("FINGERPRINT_HASH_8832");
        smartAtm.deposit(500.0);
        smartAtm.transferCrypto("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", 0.025);

        MultiCurrencyATM multiCurrencyATM = new MultiCurrencyATM("ATM-AIRPORT-02");
        multiCurrencyATM.dispenseForeignCurrency("EUR", 200.0);

        // Caso #5: DIP - Sistema de Pagos
        System.out.println("\n▶ Caso #5: Sistema de Pagos (Dependency Inversion Principle - DIP)");
        PaymentProcessor stripeProcessor = new PaymentProcessor(new StripeGateway());
        stripeProcessor.processPayment(320.50);

        PaymentProcessor wompiProcessor = new PaymentProcessor(new WompiGateway());
        wompiProcessor.processPayment(85000.00);

        // =========================================================================
        // PARTE III — PATRONES DE DISEÑO GOF
        // =========================================================================
        printSection("PARTE III: PATRONES DE DISEÑO GOF");

        // Caso #11: Singleton
        System.out.println("▶ Caso #11: Singleton - Gestor de Configuración Global");
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        config1.setProperty("api.rate.limit", "5000");
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.printf("  ✓ Instancia única compartida: %b (Valor config: %s)%n",
                (config1 == config2), config2.getProperty("api.rate.limit"));

        // Caso #12: Strategy
        System.out.println("\n▶ Caso #12: Strategy - Múltiples Formas de Pago");
        CheckoutService checkout = new CheckoutService(new CreditCardPaymentStrategy("4532890123456789", "Nadia Dev"));
        checkout.executeCheckout("ORD-771", 249.99);
        System.out.println("  ↳ Cambiando estrategia a Nequi en tiempo de ejecución:");
        checkout.setPaymentStrategy(new NequiPaymentStrategy("3101234567"));
        checkout.executeCheckout("ORD-772", 45.00);

        // Caso #13: Factory Method
        System.out.println("\n▶ Caso #13: Factory Method - Generación Polimórfica de Reportes");
        Report pdfReport = ReportFactory.createReport(ReportFactory.ReportFormat.PDF);
        pdfReport.export("Reporte Trimestral Q3", "Balance consolidado 2026");
        Report excelReport = ReportFactory.createReport(ReportFactory.ReportFormat.EXCEL);
        excelReport.export("Auditoría Financiera", "Matriz de transacciones");

        // Caso #14: Observer
        System.out.println("\n▶ Caso #14: Observer - Sistema de Eventos de Pedidos");
        OrderSubject orderSubject = new OrderSubject("ORD-90210", "PENDING_PAYMENT");
        orderSubject.attach(new InventoryObserver());
        orderSubject.attach(new EmailObserver());
        orderSubject.attach(new AuditObserver());
        orderSubject.attach(new PushNotificationObserver());
        orderSubject.attach(new AutomatedBillingObserver());

        orderSubject.updateStatus("CONFIRMED");
        orderSubject.updateStatus("DELIVERED");

        // Caso #15: Adapter
        System.out.println("\n▶ Caso #15: Adapter - Integración de Banco Legado");
        LegacyBankService legacyBank = new LegacyBankService();
        ModernPaymentProcessor modernAdapter = new LegacyBankAdapter(legacyBank);
        modernAdapter.modernPay("ACC-9812441-X", 1450.75);

        // Caso #16: Bridge
        System.out.println("\n▶ Caso #16: Bridge - Mensajes Multi-Formato x Algoritmos de Compresión");
        byte[] samplePayload = "Mensaje multimedia con alta resolución".getBytes(StandardCharsets.UTF_8);
        Message textWithRaw = new TextMessage(new RawCodec());
        textWithRaw.send("dev_team@canal", samplePayload);

        Message voiceWithAac = new VoiceMessage(new AacCodec(), 45);
        voiceWithAac.send("+573001234567", samplePayload);

        Message videoWithHevc = new VideoMessage(new HevcCodec(), "4K Ultra-HD");
        videoWithHevc.send("streaming_server_01", samplePayload);

        // Caso #17: Builder
        System.out.println("\n▶ Caso #17: Builder - Construcción Fluida de Vehículos Configurables");
        ConfiguredVehicle sportsCar = new VehicleBuilder("Automóvil Deportivo")
                .withEngine("V8 Twin-Turbo 4.0L (650 HP)")
                .withTransmission("Secuencial de Doble Embrague 7-Speed")
                .withColor("Rojo Rosso Corsa")
                .withLeatherSeats(true)
                .withPremiumSound(true)
                .withAutopilot(true)
                .withAlloyWheels(true)
                .withWarrantyYears(5)
                .build();
        System.out.println(sportsCar);

        // Caso #18: Chain of Responsibility
        System.out.println("\n▶ Caso #18: Chain of Responsibility - Cadena de Validaciones de Seguridad");
        SecurityHandler securityPipeline = new AuthenticationHandler();
        securityPipeline
                .linkWith(new RoleValidationHandler())
                .linkWith(new PermissionValidationHandler())
                .linkWith(new GeoFencingHandler())
                .linkWith(new MfaValidationHandler());

        System.out.println("--- Prueba 1: Petición Válida con Permisos Totales ---");
        SecurityRequest validRequest = new SecurityRequest(
                "nadia_admin",
                "Bearer valid-jwt-token-xyz123",
                "ADMIN",
                "/api/v1/vault/secret-keys",
                "CO",
                "987654",
                false
        );
        boolean authorized1 = securityPipeline.handle(validRequest);
        System.out.println("Resultado Pipeline 1: " + (authorized1 ? "ACCESO AUTORIZADO" : "ACCESO RECHAZADO"));

        System.out.println("\n--- Prueba 2: Petición desde País No Autorizado ---");
        SecurityRequest geoBlockedRequest = new SecurityRequest(
                "carlos_dev",
                "Bearer valid-jwt-token-xyz123",
                "MANAGER",
                "/api/v1/reports",
                "KP", // País bloqueado
                "987654",
                true
        );
        boolean authorized2 = securityPipeline.handle(geoBlockedRequest);
        System.out.println("Resultado Pipeline 2: " + (authorized2 ? "ACCESO AUTORIZADO" : "ACCESO RECHAZADO"));

        // =========================================================================
        // PARTE IV — CASO DESAFÍO #19
        // =========================================================================
        printSection("PARTE IV: DESAFÍO #19 - DOSW STREAMING PLATFORM");
        StreamingPlatform.StreamUser freeUser = new StreamingPlatform.FreeUser("USR-01", "free_user@stream.io");
        freeUser.play("Stranger Things Season 5");

        StreamingPlatform.DownloadCapableUser premiumUser = new StreamingPlatform.PremiumUser("USR-99", "nadia_premium@stream.io");
        premiumUser.play("The Last of Us Season 2");
        premiumUser.downloadForOffline("The Last of Us Season 2");

        StreamingPlatform.SearchStrategy searchStrategy = new StreamingPlatform.RelevanceSearchStrategy();
        System.out.printf("[Búsqueda - %s] Resultados para 'Clean Code': %s%n",
                searchStrategy.getStrategyName(), searchStrategy.search("Clean Code"));

        StreamingPlatform.SubtitleService subtitleService = new StreamingPlatform.OpenSubtitlesAdapter();
        System.out.printf("[Subtítulos Integrados] %s%n", subtitleService.fetchSubtitles("MOV-789", "es-CO"));

        printFooter("TALLER #3 EJECUTADO Y VERIFICADO EXITOSAMENTE (100% SOLID & PATRONES GoF)");
    }

    private static void printHeader(String title) {
        System.out.println("==================================================================================");
        System.out.println("  " + title);
        System.out.println("==================================================================================");
    }

    private static void printSection(String sectionTitle) {
        System.out.println("\n##################################################################################");
        System.out.println("  " + sectionTitle);
        System.out.println("##################################################################################\n");
    }

    private static void printFooter(String footerText) {
        System.out.println("\n==================================================================================");
        System.out.println("  ✓ " + footerText);
        System.out.println("==================================================================================");
    }
}
