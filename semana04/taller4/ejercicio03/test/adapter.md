# Patrón de Diseño: Adapter

## ¿Qué es?

Es un patrón de diseño estructural que permite que objetos con interfaces incompatibles puedan colaborar y trabajar juntos.

## ¿Qué hace?

Actúa como un traductor o "puente" (Capa Anticorrupción - ACL) entre el código moderno de la aplicación y un sistema legacy, librería de terceros o API externa cuyas firmas de métodos y tipos de datos son incompatibles con los esperados por el dominio.

## ¿Cómo se implementa?

- **Target (Interfaz Objetivo)**: La interfaz moderna que el cliente espera utilizar.
- **Adaptee (Adaptado)**: La clase existente o servicio heredado que tiene la lógica funcional pero con una interfaz incompatible.
- **Adapter (Adaptador)**: Clase que implementa la interfaz Target y contiene internamente una instancia del Adaptee. Traduce las llamadas y los tipos de datos de un formato a otro.

## ¿Cómo identificarlo (Cuándo usarlo y Code Smells que resuelve)?

**Code Smells**: Código de dominio contaminado con llamadas a librerías externas o métodos legados extraños (`executeTransaction`, `getDataInCents`), y sentencias de adaptación dispersas por toda la lógica de negocio.

**Uso ideal**: Cuando integras sistemas externos que no puedes modificar, o cuando refactorizas código heredado y necesitas mantener compatibilidad temporal. Alineado con XP y Clean Code al aislar el acoplamiento externo y respetar estrictamente el Single Responsibility Principle (SRP).

## Ejemplos de Implementación en Java

### Ejemplo 1: Integración con Sistema Bancario Antiguo (Inspirado en el Taller - Ejercicio #05)

Adapta un servicio bancario legacy que opera con montos en centavos e inicializaciones complejas a una interfaz moderna de procesamiento de pagos.

```java
// 1. Target (Interfaz moderna)
interface PaymentProcessor {
    void pay(double amount);
}

// 2. Adaptee (Servicio Legacy incompatible)
class LegacyBankService {
    public void executeTransaction(String account, int cents) {
        System.out.println("[LegacyBank] Ejecutando transacción en cuenta " + account + por " + cents + " centavos.");
    }
}

// 3. Adapter (Traductor)
class LegacyBankAdapter implements PaymentProcessor {
    private final LegacyBankService legacyService;
    private final String accountId;

    public LegacyBankAdapter(LegacyBankService legacyService, String accountId) {
        this.legacyService = legacyService;
        this.accountId = accountId;
    }

    @Override
    public void pay(double amount) {
        // Traducción de datos: double (dólares) a int (centavos)[cite: 2]
        int cents = (int) Math.round(amount * 100);
        System.out.println("[Adapter] Traduciendo $" + amount + " a " + cents + " centavos.");
        legacyService.executeTransaction(accountId, cents);
    }
}

// Uso
public class AdapterDemo1 {
    public static void main(String[] args) {
        PaymentProcessor processor = new LegacyBankAdapter(new LegacyBankService(), "ACC-12345");
        processor.pay(150.75);
    }
}
```

### Ejemplo 2: Adaptador de Proveedor de Analíticas (XML a Formato Moderno JSON/DTO)

Permite integrar una librería de analíticas antigua que solo procesa estructuras XML a un sistema moderno que procesa objetos de transferencia de datos limpios.

```java
// 1. Target
interface AnalyticsService {
    void sendEvent(String eventName, int value);
}

// 2. Adaptee (Librería antigua basada en XML)
class LegacyXmlAnalytics {
    public void sendXmlData(String xmlPayload) {
        System.out.println("[LegacyAnalytics] Enviando XML crudo: " + xmlPayload);
    }
}

// 3. Adapter
class XmlAnalyticsAdapter implements AnalyticsService {
    private final LegacyXmlAnalytics legacyAnalytics;

    public XmlAnalyticsAdapter(LegacyXmlAnalytics legacyAnalytics) {
        this.legacyAnalytics = legacyAnalytics;
    }

    @Override
    public void sendEvent(String eventName, int value) {
        // Traduce los parámetros limpios al formato XML que exige el adaptee
        String xmlPayload = "<event><name>" + eventName + "</name><val>" + value + "</val></event>";
        legacyAnalytics.sendXmlData(xmlPayload);
    }
}

// Uso
public class AdapterDemo2 {
    public static void main(String[] args) {
        AnalyticsService analytics = new XmlAnalyticsAdapter(new LegacyXmlAnalytics());
        analytics.sendEvent("USER_LOGIN", 1);
    }
}
```

### Ejemplo 3: Adaptador de Pasarela de Pago de Terceros (Stripe vs Custom Interface)

Integra un SDK de terceros con métodos de llamada propios a la interfaz estándar de pagos de nuestra plataforma de e-commerce.

```java
// 1. Target
interface ModernGateway {
    boolean makeCharge(String cardToken, double totalUSD);
}

// 2. Adaptee (SDK de Stripe con métodos distintos)
class StripeThirdPartySdk {
    public void chargeInCents(String token, long centsAmount) {
        System.out.println("[Stripe SDK] Cobro exitoso de " + centsAmount + " centavos con token " + token);
    }
}

// 3. Adapter
class StripeAdapter implements ModernGateway {
    private final StripeThirdPartySdk stripeSdk;

    public StripeAdapter(StripeThirdPartySdk stripeSdk) {
        this.stripeSdk = stripeSdk;
    }

    @Override
    public boolean makeCharge(String cardToken, double totalUSD) {
        try {
            long cents = (long) (totalUSD * 100);
            stripeSdk.chargeInCents(cardToken, cents);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

// Uso
public class AdapterDemo3 {
    public static void main(String[] args) {
        ModernGateway gateway = new StripeAdapter(new StripeThirdPartySdk());
        gateway.makeCharge("tok_19zYtK2eZvKYlo2C", 99.99);
    }
}
```
