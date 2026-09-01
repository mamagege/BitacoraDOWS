# Análisis Detallado de Ejemplos: Impacto en Ciclo de Vida Ágil y TDD

---

## 1. Single Responsibility Principle (SRP) — Gestión de Órdenes

### El Problema en un Entorno Ágil
En la versión anti-patrón (`SRP_AntiPattern.OrderService`), una única clase agrupaba cuatro razones de cambio distintas:
1. **Contabilidad:** Si la tasa de impuestos o la fórmula de cálculo cambia.
2. **DBA / Infraestructura:** Si la tabla de base de datos migra o cambia su esquema SQL.
3. **Marketing / Diseño:** Si el formato de la factura cambia a HTML o se agregan campos estéticos.
4. **DevOps / Seguridad:** Si las credenciales SMTP cambian o se pasa a una API REST de correos (ej. SendGrid).

En un equipo con múltiples pares trabajando en paralelo, esta clase generaba **conflictos de merge constantes (*merge hell*)** y alto riesgo de regresiones.

### Cómo la Refactorización Solucionó el Problema
Se segregaron las responsabilidades en componentes cohesivos:
- `Order`: Encapsula el cálculo de subtotal propio del modelo de dominio.
- `TaxCalculator`: Contiene la regla pura de cálculo tributario.
- `OrderRepository`: Abstrae la persistencia.
- `NotificationService`: Abstrae el canal de comunicación.
- `OrderProcessor`: Orquesta el flujo sin conocer detalles técnicos.

### Impacto Directo en TDD (Test-Driven Development)
Probar la versión anterior requería mocks de bases de datos y simulación de sockets SMTP para verificar un simple cálculo de suma. En la solución refactorizada:
```java
// Prueba unitaria pura y ultra rápida (ejecución en milisegundos)
@Test
void shouldCalculateSubtotalCorrectly() {
    Order order = new Order("1", "user@test.com", List.of(new OrderItem("A", new BigDecimal("10.00"))));
    assertEquals(new BigDecimal("10.00"), order.calculateSubtotal());
}
```

---

## 2. Open/Closed Principle (OCP) — Pasarelas de Pago Multi-canal

### El Problema en un Entorno Ágil
El equipo de producto decide soportar **Apple Pay** o **Pagos con Criptomonedas**. En el código anti-patrón (`PaymentProcessor`), el desarrollador tenía que abrir la clase central y añadir otro bloque `else-if`.
- **Riesgo:** Un error de tipeo o una excepción no controlada en el nuevo método de pago podía derribar las transacciones de Tarjeta de Crédito existentes.
- **Rompe el flujo de CI/CD:** Exige re-certificar toda la suite de pagos para cada nuevo conector.

### Cómo la Refactorización Solucionó el Problema
Se aplicó el patrón **Strategy**:
- Se creó la interfaz `PaymentGateway` con métodos `supports()` y `charge()`.
- Para añadir Apple Pay, solo se crea una nueva clase `ApplePayGateway implements PaymentGateway`.
- `PaymentEngine` permanece **cerrado a la modificación**: no cambia una sola línea de su código fuente, pero está **abierto a la extensión** porque recibe la lista de pasarelas polimórficamente.

### Impacto Directo en TDD
Podemos probar el comportamiento de `PaymentEngine` de forma aislada con un `StubPaymentGateway` sin interactuar con pasarelas reales, y probar cada pasarela individualmente en su propio archivo de test:
```java
@Test
void shouldExecuteCorrectGatewayWhenSupported() {
    PaymentGateway mockGateway = mock(PaymentGateway.class);
    when(mockGateway.supports("MOCK_PAY")).thenReturn(true);
    when(mockGateway.charge(any())).thenReturn(new PaymentResult(true, "TX1", "OK"));

    PaymentEngine engine = new PaymentEngine(List.of(mockGateway));
    PaymentResult result = engine.executePayment("MOCK_PAY", BigDecimal.TEN);

    assertTrue(result.isSuccessful());
}
```

---

## 3. Liskov Substitution Principle (LSP) — Cuentas Bancarias y Retiros

### El Problema en un Entorno Ágil
Se creó `FixedTermDepositAccount` (Depósito a Plazo Fijo) heredando de `BankAccount`. Como este tipo de cuenta no permite retiros inmediatos, el desarrollador sobreescribió `withdraw()` lanzando `UnsupportedOperationException`.
- **Falla en Producción:** Cuando el servicio `CashDispenserService` recibe una lista polimórfica de `BankAccount` e intenta iterar para procesar retiros automáticos, la aplicación detona en runtime con un fallo no previsto.

### Cómo la Refactorización Solucionó el Problema
El principio de Liskov nos enseña que **la herencia no es solo por similitud conceptual, sino por compatibilidad de comportamiento y contratos**:
- Se definió la interfaz base `Account` (con `getBalance()` y `deposit()`).
- Se creó la sub-interfaz `WithdrawableAccount` que agrega formalmente la capacidad `withdraw()`.
- `FixedTermDepositAccount` solo implementa `Account`. El compilador de Java previene en tiempo de compilación que alguien intente pasar una cuenta bloqueada a un dispensador de efectivo.

### Impacto Directo en TDD
Las pruebas pueden verificar los invariantes formales de cada contrato sin temor a comportamientos erráticos o excepciones sorpresa:
```java
@Test
void shouldDispenseCashForAnyWithdrawableAccount() {
    WithdrawableAccount account = new CheckingAccount();
    account.deposit(new BigDecimal("100"));
    
    CashDispenserService dispenser = new CashDispenserService();
    assertDoesNotThrow(() -> dispenser.dispenseCash(account, new BigDecimal("50")));
}
```

---

## 4. Interface Segregation Principle (ISP) — Ecosistema Domótica IoT

### El Problema en un Entorno Ágil
Se creó una interfaz unificada `SmartDevice` con métodos para prender, apagar, regular temperatura, transmitir video y sonar alarmas.
- Cuando el equipo de hardware lanzó una **Bombilla Inteligente**, los desarrolladores tuvieron que implementar 3 métodos con `throw new UnsupportedOperationException()`.
- Si el equipo de seguridad cambiaba la firma de `streamLiveVideo()` (ej. agregando un token de cifrado), **las bombillas tenían que recompilarse y re-desplegarse** sin tener ninguna relación con video.

### Cómo la Refactorización Solucionó el Problema
Se descompuso la interfaz monstruo en contratos de rol (*Role Interfaces*):
- `Switchable` (Prender / Apagar)
- `ClimateControllable` (Temperatura)
- `VideoStreamable` (Cámaras)
- `Alarmable` (Sirenas)

La bombilla inteligente implementa únicamente `Switchable`. Un Hub de seguridad avanzado puede implementar `VideoStreamable` y `Alarmable` conjuntamente sin contaminar a otros dispositivos.

### Impacto Directo en TDD
En las pruebas unitarias de un controlador de luces, el Mock solo necesita implementar `Switchable` (3 métodos limpios), reduciendo la complejidad del setup de pruebas al mínimo absoluto.

---

## 5. Dependency Inversion Principle (DIP) — Registro de Usuarios

### El Problema en un Entorno Ágil
En el anti-patrón `UserRegistrationManager`, las clases de bajo nivel (`MySqlUserRepository` y `TwilioSmsSender`) se instanciaban directamente con `new` dentro del constructor o método.
- Si el negocio decidía migrar de Twilio a AWS SNS o de MySQL a PostgreSQL, era necesario modificar la lógica de negocio central.
- **Bloqueo total de TDD:** No se podía probar la regla de validación de usuario sin tener una base de datos MySQL levantada y una cuenta de Twilio con saldo real.

### Cómo la Refactorización Solucionó el Problema
Se aplicó **Inversión de Control (IoC)** y **Arquitectura Hexagonal**:
- El caso de uso (`UserRegistrationUseCase`) solo conoce los contratos de dominio `UserRepository` y `UserNotifier`.
- Las implementaciones técnicas (`PostgresUserRepository`, `TwilioNotifier`) son detalles de infraestructura que se inyectan a través del constructor.

### Impacto Directo en TDD
Permite probar al 100% las reglas de negocio en memoria en menos de 5 milisegundos:
```java
@Test
void shouldRejectRegistrationIfEmailAlreadyExists() {
    UserRepository fakeRepo = mock(UserRepository.class);
    UserNotifier fakeNotifier = mock(UserNotifier.class);
    when(fakeRepo.existsByEmail("test@domain.com")).thenReturn(true);

    UserRegistrationUseCase useCase = new UserRegistrationUseCase(fakeRepo, fakeNotifier);

    assertThrows(IllegalStateException.class, () -> 
        useCase.register("test@domain.com", "+123456789")
    );
    verifyNoInteractions(fakeNotifier); // Garantiza que no se notificó por error
}
```
