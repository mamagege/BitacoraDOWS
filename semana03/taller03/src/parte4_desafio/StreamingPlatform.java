package parte4_desafio;

import java.util.List;

/**
 * Caso #19: Plataforma DOSW Streaming
 * Demostración de arquitectura integrada aplicando múltiples principios SOLID y patrones de diseño GoF:
 * - Strategy: Algoritmos de recomendación y búsqueda intercambiables.
 * - Observer: Notificación asíncrona por múltiples canales de eventos de streaming.
 * - Adapter + DIP: Integración desacoplada con APIs externas (Subtítulos, Pagos).
 * - LSP + ISP: Jerarquía de usuarios segregada y completamente sustituible.
 */
public class StreamingPlatform {

    // --- Estrategias de Búsqueda (Strategy) ---
    public interface SearchStrategy {
        List<String> search(String query);
        String getStrategyName();
    }

    public static class PopularitySearchStrategy implements SearchStrategy {
        @Override
        public List<String> search(String query) {
            return List.of("1. Breaking Bad (Rating: 9.5)", "2. Interstellar (Rating: 8.9)");
        }
        @Override
        public String getStrategyName() { return "Top Popularidad"; }
    }

    public static class RelevanceSearchStrategy implements SearchStrategy {
        @Override
        public List<String> search(String query) {
            return List.of("1. Documental Clean Code (Coincidencia: 99%)", "2. Tutorial Java 21 (Coincidencia: 95%)");
        }
        @Override
        public String getStrategyName() { return "Relevancia Semántica"; }
    }

    // --- Estrategias de Recomendación (Strategy) ---
    public interface RecommendationStrategy {
        List<String> recommend(String userId);
    }

    public static class CollaborativeRecommendationStrategy implements RecommendationStrategy {
        @Override
        public List<String> recommend(String userId) {
            return List.of("Inception", "The Matrix", "Cyberpunk: Edgerunners");
        }
    }

    // --- Adaptador de Subtítulos Externos (Adapter + DIP) ---
    public interface SubtitleService {
        String fetchSubtitles(String contentId, String language);
    }

    // API Externa Legada de Subtítulos
    public static class ExternalOpenSubtitlesApi {
        public byte[] downloadSrtFile(int movieId, String langIso) {
            return ("[SRT Subtitle Stream for movie " + movieId + " in " + langIso + "]").getBytes();
        }
    }

    // Adaptador
    public static class OpenSubtitlesAdapter implements SubtitleService {
        private final ExternalOpenSubtitlesApi api = new ExternalOpenSubtitlesApi();

        @Override
        public String fetchSubtitles(String contentId, String language) {
            byte[] srt = api.downloadSrtFile(contentId.hashCode(), language);
            return new String(srt);
        }
    }

    // --- Usuarios Segregados (ISP / LSP) ---
    public interface StreamUser {
        String getId();
        String getEmail();
        void play(String contentTitle);
    }

    public interface DownloadCapableUser extends StreamUser {
        void downloadForOffline(String contentTitle);
    }

    public static class FreeUser implements StreamUser {
        private final String id;
        private final String email;

        public FreeUser(String id, String email) {
            this.id = id;
            this.email = email;
        }

        @Override
        public String getId() { return id; }
        @Override
        public String getEmail() { return email; }

        @Override
        public void play(String contentTitle) {
            System.out.printf("[FreeUser - %s] Reproduciendo '%s' con anuncios publicitarios (720p).%n", email, contentTitle);
        }
    }

    public static class PremiumUser implements DownloadCapableUser {
        private final String id;
        private final String email;

        public PremiumUser(String id, String email) {
            this.id = id;
            this.email = email;
        }

        @Override
        public String getId() { return id; }
        @Override
        public String getEmail() { return email; }

        @Override
        public void play(String contentTitle) {
            System.out.printf("[PremiumUser - %s] Reproduciendo '%s' en 4K HDR Dolby Atmos sin interrupciones.%n", email, contentTitle);
        }

        @Override
        public void downloadForOffline(String contentTitle) {
            System.out.printf("[PremiumUser - %s] Descargando '%s' en almacenamiento local encriptado para ver sin conexión.%n", email, contentTitle);
        }
    }
}
