import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * MOTOR DE RECOMENDACIONES - DEMOSTRACIÓN DE STRATEGY + OBSERVER
 * Enfoque: Diseño Reactivo, Alta Cohesión (SRP) y Cero Acoplamiento Estático.
 */

// --- DOMINIO BASE ---
// DTO inmutable para representar el contenido audiovisual.
class Content {
    private final String title;

    public Content(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

// --- PATRÓN 1: STRATEGY (Algoritmos intercambiables) ---[cite: 2]
// Interfaz que define el contrato de recomendación.
interface RecommendationAlgorithm {
    List<Content> recommend(User user);
}

class GenreStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        // Lógica simulada de filtrado por género[cite: 2]
        return Arrays.asList(new Content("Sci-Fi Movie 1"), new Content("Sci-Fi Movie 2"));
    }
}

class PopularityStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        // Lógica simulada de contenido trending[cite: 2]
        return Arrays.asList(new Content("Top Global Hit"), new Content("Viral Series"));
    }
}

// --- PATRÓN 2: OBSERVER (Desacoplamiento UI/Servicios) ---[cite: 2]
// Contrato para cualquier componente que deba reaccionar a los cambios.
interface PreferenceObserver {
    void onPreferenceChanged(User user);
}

class HomePageComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        System.out.println("[UI Render] Actualizando Home Page con:");
        user.getRecommendations().forEach(c -> System.out.println(" - " + c.getTitle()));
    }
}

class NotificationService implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        System.out.println("[Push Service] Enviando notificación silente para refrescar caché del móvil.");
    }
}

// --- SUJETO OBSERVABLE Y CONTEXTO (El Usuario) ---[cite: 2]
// Actúa como el Context del Strategy y el Subject del Observer.
class User {
    private final String username;
    private RecommendationAlgorithm currentStrategy;
    private final List<PreferenceObserver> observers = new ArrayList<>();

    public User(String username, RecommendationAlgorithm initialStrategy) {
        this.username = username;
        this.currentStrategy = initialStrategy;
    }

    // Observer: Gestión de suscripciones[cite: 2]
    public void addObserver(PreferenceObserver observer) {
        observers.add(observer);
    }

    // Mutador: Cambia el comportamiento en tiempo de ejecución (Strategy) y avisa
    // (Observer)[cite: 2]
    public void changePreference(RecommendationAlgorithm newStrategy) {
        System.out.println("\n>>> EVENTO: " + this.username + " ha cambiado su algoritmo de recomendación.");
        this.currentStrategy = newStrategy;
        notifyObservers();
    }

    // Delega el cálculo al Strategy actual[cite: 2]
    public List<Content> getRecommendations() {
        return currentStrategy.recommend(this);
    }

    private void notifyObservers() {
        for (PreferenceObserver observer : observers) {
            observer.onPreferenceChanged(this); // Pasamos la referencia del usuario
        }
    }
}

// --- DEMOSTRACIÓN FUNCIONAL ---
public class RecommendationSystem {
    public static void main(String[] args) {
        System.out.println("--- INICIALIZANDO PLATAFORMA ---");

        // 1. Configuramos el usuario con una estrategia por defecto
        User user = new User("Alice_Dev", new PopularityStrategy());

        // 2. Conectamos los módulos del sistema (Observers)
        PreferenceObserver homePage = new HomePageComponent();
        PreferenceObserver pushNotificator = new NotificationService();

        user.addObserver(homePage);
        user.addObserver(pushNotificator);

        // 3. Primer renderizado (simulado)
        System.out.println("Carga inicial de Home Page:");
        user.getRecommendations().forEach(c -> System.out.println(" - " + c.getTitle()));

        // 4. El usuario entra a configuración y cambia sus gustos
        // El sistema muta de estrategia y la UI se actualiza sola sin acoplamiento
        user.changePreference(new GenreStrategy());
    }
}