package com.cleanarchitecture.solid;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * REPOSITORIO DE EJEMPLOS DE PRODUCCIÓN: PRINCIPIOS SOLID
 * 
 * Filosofía: Clean Code, XP (Extreme Programming), TDD y Alta Cohesión.
 * Cada escenario muestra el Anti-patrón (MAL) vs. la Solución Refactorizada (BIEN).
 */
public class EjemplosSolid {

    // =========================================================================
    // 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP)
    // Escenario: Facturación y Procesamiento de Órdenes de Compra
    // =========================================================================

    public static class SRP_AntiPattern {
        // ❌ MAL: La clase OrderService maneja cálculo de total, persistencia en BD,
        // generación de formato imprimible/PDF y envío de correo electrónico.
        // Tiene 4 razones distintas para cambiar.
        static class OrderService {
            public void processOrder(String customerEmail, List<BigDecimal> itemPrices) {
                // 1. Lógica de negocio (Cálculo)
                BigDecimal total = BigDecimal.ZERO;
                for (BigDecimal price : itemPrices) {
                    total = total.add(price);
                }

                // 2. Persistencia en Base de Datos
                System.out.println("SQL: INSERT INTO orders (total) VALUES (" + total + ")");

                // 3. Formateo de Factura (Presentación)
                String invoice = "=== INVOICE ===" + "\nTotal: $" + total;

                // 4. Integración con Servidor SMTP (Notificaciones)
                System.out.println("SMTP: Sending email to " + customerEmail + " with body:\n" + invoice);
            }
        }
    }

    public static class SRP_Solution {
        // ✔️ BIEN: Se aíslan las responsabilidades en componentes atómicos y cohesivos.

        public record OrderItem(String id, BigDecimal price) {}
        
        public record Order(String id, String customerEmail, List<OrderItem> items) {
            public BigDecimal calculateSubtotal() {
                return items.stream()
                        .map(OrderItem::price)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            }
        }

        // Responsabilidad 1: Reglas de cálculo financiero
        public static class TaxCalculator {
            private static final BigDecimal TAX_RATE = new BigDecimal("0.18");

            public BigDecimal calculateTax(BigDecimal subtotal) {
                return subtotal.multiply(TAX_RATE);
            }
        }

        // Responsabilidad 2: Contrato de persistencia
        public interface OrderRepository {
            void save(Order order, BigDecimal totalWithTax);
        }

        // Responsabilidad 3: Contrato de notificación
        public interface NotificationService {
            void sendInvoiceNotification(String recipient, Order order, BigDecimal total);
        }

        // Orquestador de aplicación de bajo acoplamiento
        public static class OrderProcessor {
            private final TaxCalculator taxCalculator;
            private final OrderRepository orderRepository;
            private final NotificationService notificationService;

            public OrderProcessor(TaxCalculator taxCalculator,
                                  OrderRepository orderRepository,
                                  NotificationService notificationService) {
                this.taxCalculator = Objects.requireNonNull(taxCalculator);
                this.orderRepository = Objects.requireNonNull(orderRepository);
                this.notificationService = Objects.requireNonNull(notificationService);
            }

            public void process(Order order) {
                BigDecimal subtotal = order.calculateSubtotal();
                BigDecimal tax = taxCalculator.calculateTax(subtotal);
                BigDecimal total = subtotal.add(tax);

                orderRepository.save(order, total);
                notificationService.sendInvoiceNotification(order.customerEmail(), order, total);
            }
        }
    }

    // =========================================================================
    // 2. OPEN/CLOSED PRINCIPLE (OCP)
    // Escenario: Procesamiento de Pasarelas de Pago Multi-canal
    // =========================================================================

    public static class OCP_AntiPattern {
        // ❌ MAL: Cada vez que el negocio acepta un nuevo método de pago (ej. Crypto, ApplePay),
        // debemos abrir y modificar esta clase central con más 'if' o 'switch', arriesgando romper los existentes.
        static class PaymentProcessor {
            public void processPayment(String paymentMethod, BigDecimal amount) {
                if ("CREDIT_CARD".equalsIgnoreCase(paymentMethod)) {
                    System.out.println("Processing Credit Card payment of $" + amount);
                } else if ("PAYPAL".equalsIgnoreCase(paymentMethod)) {
                    System.out.println("Processing PayPal API transaction of $" + amount);
                } else if ("CRYPTO".equalsIgnoreCase(paymentMethod)) {
                    System.out.println("Processing Bitcoin blockchain payment of $" + amount);
                } else {
                    throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
                }
            }
        }
    }

    public static class OCP_Solution {
        // ✔️ BIEN: Abierto a la extensión (nuevas pasarelas), cerrado a la modificación.

        public record PaymentResult(boolean isSuccessful, String transactionId, String message) {}

        // Estrategia polimórfica (Abstracción)
        public interface PaymentGateway {
            boolean supports(String paymentMethod);
            PaymentResult charge(BigDecimal amount);
        }

        public static class CreditCardGateway implements PaymentGateway {
            @Override
            public boolean supports(String paymentMethod) {
                return "CREDIT_CARD".equalsIgnoreCase(paymentMethod);
            }

            @Override
            public PaymentResult charge(BigDecimal amount) {
                return new PaymentResult(true, "CC-" + UUID.randomUUID(), "Credit Card charged successfully: $" + amount);
            }
        }

        public static class PayPalGateway implements PaymentGateway {
            @Override
            public boolean supports(String paymentMethod) {
                return "PAYPAL".equalsIgnoreCase(paymentMethod);
            }

            @Override
            public PaymentResult charge(BigDecimal amount) {
                return new PaymentResult(true, "PP-" + UUID.randomUUID(), "PayPal transfer confirmed: $" + amount);
            }
        }

        public static class CryptoGateway implements PaymentGateway {
            @Override
            public boolean supports(String paymentMethod) {
                return "CRYPTO".equalsIgnoreCase(paymentMethod);
            }

            @Override
            public PaymentResult charge(BigDecimal amount) {
                return new PaymentResult(true, "0x" + UUID.randomUUID().toString().replace("-", ""), "Crypto verified on chain: $" + amount);
            }
        }

        // El motor de pagos no se modifica NUNCA cuando se agrega un nuevo Gateway.
        public static class PaymentEngine {
            private final List<PaymentGateway> gateways;

            public PaymentEngine(List<PaymentGateway> gateways) {
                this.gateways = List.copyOf(gateways);
            }

            public PaymentResult executePayment(String method, BigDecimal amount) {
                return gateways.stream()
                        .filter(g -> g.supports(method))
                        .findFirst()
                        .map(g -> g.charge(amount))
                        .orElseThrow(() -> new IllegalArgumentException("Payment method not supported: " + method));
            }
        }
    }

    // =========================================================================
    // 3. LISKOV SUBSTITUTION PRINCIPLE (LSP)
    // Escenario: Cuentas Bancarias con y sin Capacidad de Retiro
    // =========================================================================

    public static class LSP_AntiPattern {
        // ❌ MAL: FixedTermDepositAccount (Plazo Fijo) hereda de BankAccount pero no permite retiros,
        // lanzando una excepción inesperada que rompe la expectativa del cliente polimórfico.
        static class BankAccount {
            protected BigDecimal balance = BigDecimal.ZERO;

            public void deposit(BigDecimal amount) {
                this.balance = this.balance.add(amount);
            }

            public void withdraw(BigDecimal amount) {
                if (balance.compareTo(amount) < 0) {
                    throw new IllegalStateException("Insufficient funds");
                }
                this.balance = this.balance.subtract(amount);
            }

            public BigDecimal getBalance() { return balance; }
        }

        static class FixedTermDepositAccount extends BankAccount {
            @Override
            public void withdraw(BigDecimal amount) {
                // Viola LSP: rompe el contrato y el comportamiento garantizado por el tipo base
                throw new UnsupportedOperationException("Withdrawals are locked until maturity date!");
            }
        }

        static class BankingClient {
            public void performDailyWithdrawal(BankAccount account) {
                // Si pasamos FixedTermDepositAccount, la aplicación colapsa
                account.withdraw(new BigDecimal("100"));
            }
        }
    }

    public static class LSP_Solution {
        // ✔️ BIEN: Segregamos los contratos para que los subtipos respeten fielmente todas sus garantías.

        public interface Account {
            BigDecimal getBalance();
            void deposit(BigDecimal amount);
        }

        public interface WithdrawableAccount extends Account {
            void withdraw(BigDecimal amount);
        }

        public static class CheckingAccount implements WithdrawableAccount {
            private BigDecimal balance = BigDecimal.ZERO;

            @Override
            public void deposit(BigDecimal amount) {
                balance = balance.add(amount);
            }

            @Override
            public void withdraw(BigDecimal amount) {
                if (balance.compareTo(amount) < 0) {
                    throw new IllegalStateException("Insufficient funds in checking account");
                }
                balance = balance.subtract(amount);
            }

            @Override
            public BigDecimal getBalance() {
                return balance;
            }
        }

        public static class FixedTermDepositAccount implements Account {
            private BigDecimal balance = BigDecimal.ZERO;
            private final LocalDateTime maturityDate;

            public FixedTermDepositAccount(LocalDateTime maturityDate) {
                this.maturityDate = maturityDate;
            }

            @Override
            public void deposit(BigDecimal amount) {
                balance = balance.add(amount);
            }

            @Override
            public BigDecimal getBalance() {
                return balance;
            }

            public boolean isMatured() {
                return LocalDateTime.now().isAfter(maturityDate);
            }
        }

        // El cliente solo exige WithdrawableAccount cuando realmente necesita retirar
        public static class CashDispenserService {
            public void dispenseCash(WithdrawableAccount account, BigDecimal amount) {
                account.withdraw(amount);
                System.out.println("Dispensed: $" + amount + ". New balance: $" + account.getBalance());
            }
        }
    }

    // =========================================================================
    // 4. INTERFACE SEGREGATION PRINCIPLE (ISP)
    // Escenario: Ecosistema de Domótica e IoT (Smart Home)
    // =========================================================================

    public static class ISP_AntiPattern {
        // ❌ MAL: Interfaz monolítica ("gorda"). Obliga a dispositivos simples como una bombilla
        // a implementar métodos de seguridad, cámaras o termostatos que no le corresponden.
        interface SmartDevice {
            void turnOn();
            void turnOff();
            void setTemperature(double celsius);
            void streamLiveVideo();
            void triggerAlarm();
        }

        static class SmartLightBulb implements SmartDevice {
            @Override public void turnOn() { System.out.println("Light turned ON"); }
            @Override public void turnOff() { System.out.println("Light turned OFF"); }
            
            // Métodos forzados innecesarios:
            @Override public void setTemperature(double celsius) { throw new UnsupportedOperationException(); }
            @Override public void streamLiveVideo() { throw new UnsupportedOperationException(); }
            @Override public void triggerAlarm() { throw new UnsupportedOperationException(); }
        }
    }

    public static class ISP_Solution {
        // ✔️ BIEN: Interfaces pequeñas, enfocadas en roles únicos e independientes.

        public interface Switchable {
            void turnOn();
            void turnOff();
            boolean isOn();
        }

        public interface ClimateControllable {
            void setTargetTemperature(double celsius);
            double getCurrentTemperature();
        }

        public interface VideoStreamable {
            byte[] captureFrame();
            void startLiveStream();
        }

        public interface Alarmable {
            void triggerAlarm(String reason);
        }

        // Una bombilla inteligente solo implementa lo que sabe hacer
        public static class SmartLight implements Switchable {
            private boolean active;

            @Override public void turnOn() { this.active = true; }
            @Override public void turnOff() { this.active = false; }
            @Override public boolean isOn() { return this.active; }
        }

        // Un sistema de seguridad compuesto implementa múltiples roles especializados
        public static class SmartSecurityHub implements VideoStreamable, Alarmable {
            @Override
            public byte[] captureFrame() {
                return new byte[]{0x00, 0x7F, 0x33};
            }

            @Override
            public void startLiveStream() {
                System.out.println("Streaming secure video feed...");
            }

            @Override
            public void triggerAlarm(String reason) {
                System.out.println("ALARM TRIGGERED: " + reason);
            }
        }
    }

    // =========================================================================
    // 5. DEPENDENCY INVERSION PRINCIPLE (DIP)
    // Escenario: Registro de Usuarios y Notificaciones del Sistema
    // =========================================================================

    public static class DIP_AntiPattern {
        // ❌ MAL: Las clases de alto nivel dependen de detalles de bajo nivel instanciados con 'new'.
        // Imposible hacer pruebas unitarias sin una base de datos MySQL real y un servidor de email externo.
        static class MySqlUserRepository {
            public void saveUserToDb(String email) {
                System.out.println("Executing raw SQL insert into MySQL for " + email);
            }
        }

        static class TwilioSmsSender {
            public void sendSms(String phone, String text) {
                System.out.println("Calling Twilio REST API for " + phone);
            }
        }

        static class UserRegistrationManager {
            // Fuerte acoplamiento: instancias concretas codificadas a fuego
            private final MySqlUserRepository repository = new MySqlUserRepository();
            private final TwilioSmsSender smsSender = new TwilioSmsSender();

            public void registerUser(String email, String phone) {
                repository.saveUserToDb(email);
                smsSender.sendSms(phone, "Welcome to our platform!");
            }
        }
    }

    public static class DIP_Solution {
        // ✔️ BIEN: El módulo de alto nivel define sus puertos (interfaces), y la infraestructura
        // se conecta mediante inyección de dependencias (IoC / Hexagonal Architecture).

        public record User(String id, String email, String phone) {}

        // Abstracción requerida por el dominio (Puerto de salida)
        public interface UserRepository {
            void save(User user);
            boolean existsByEmail(String email);
        }

        // Abstracción requerida para notificar (Puerto de salida)
        public interface UserNotifier {
            void notifyUserRegistered(User user);
        }

        // Módulo de alto nivel: Lógica de negocio pura sin dependencias técnicas
        public static class UserRegistrationUseCase {
            private final UserRepository userRepository;
            private final UserNotifier userNotifier;

            // Inyección por constructor (Inversion of Control)
            public UserRegistrationUseCase(UserRepository userRepository, UserNotifier userNotifier) {
                this.userRepository = Objects.requireNonNull(userRepository);
                this.userNotifier = Objects.requireNonNull(userNotifier);
            }

            public void register(String email, String phone) {
                if (userRepository.existsByEmail(email)) {
                    throw new IllegalStateException("Email already registered: " + email);
                }

                User newUser = new User(UUID.randomUUID().toString(), email, phone);
                userRepository.save(newUser);
                userNotifier.notifyUserRegistered(newUser);
            }
        }

        // Adaptadores de infraestructura (Detalles de bajo nivel)
        public static class PostgresUserRepository implements UserRepository {
            @Override
            public void save(User user) {
                System.out.println("Persisting user in PostgreSQL: " + user.id());
            }

            @Override
            public boolean existsByEmail(String email) {
                return false;
            }
        }

        public static class MultiChannelNotifier implements UserNotifier {
            @Override
            public void notifyUserRegistered(User user) {
                System.out.println("Pushing notification to user phone: " + user.phone());
            }
        }
    }
}
