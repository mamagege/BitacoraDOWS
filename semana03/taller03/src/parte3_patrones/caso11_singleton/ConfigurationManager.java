package parte3_patrones.caso11_singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Patrón: SINGLETON (Creacional)
 * ¿Por qué?: Garantiza una única instancia compartida para gestionar la configuración
 * global del sistema, asegurando consistencia y evitando estado desfasado.
 * Implementación: Bill Pugh Singleton Holder (Thread-safe, Lazy, sin overhead de sincronización).
 */
public class ConfigurationManager {
    private final Map<String, String> configurations;

    private ConfigurationManager() {
        this.configurations = new HashMap<>();
        // Carga de configuraciones por defecto
        configurations.put("app.name", "DOSW Enterprise Platform");
        configurations.put("app.environment", "production");
        configurations.put("db.timeout.ms", "3000");
    }

    private static class Holder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    public static ConfigurationManager getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setProperty(String key, String value) {
        configurations.put(key, value);
    }

    public synchronized String getProperty(String key) {
        return configurations.get(key);
    }

    public synchronized String getProperty(String key, String defaultValue) {
        return configurations.getOrDefault(key, defaultValue);
    }

    public synchronized Map<String, String> getAllProperties() {
        return Collections.unmodifiableMap(new HashMap<>(configurations));
    }
}
