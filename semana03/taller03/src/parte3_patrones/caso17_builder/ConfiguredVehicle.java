package parte3_patrones.caso17_builder;

/**
 * Producto inmutable construido mediante el patrón Builder.
 */
public record ConfiguredVehicle(
        String vehicleType,
        String engine,
        String transmission,
        String color,
        boolean hasGps,
        boolean hasPremiumSound,
        boolean hasRoadsideAssistance,
        boolean hasLeatherSeats,
        boolean hasSunroof,
        boolean hasAirConditioning,
        boolean hasAutopilot,
        boolean hasDashcam,
        boolean hasParkingSensors,
        boolean hasAlloyWheels,
        int warrantyYears
) {
    @Override
    public String toString() {
        return """
                ConfiguredVehicle {
                  Tipo: %s | Motor: %s | Transmisión: %s | Color: %s
                  Opcionales: [GPS: %s, Sonido Premium: %s, Asistencia Carretera: %s, Cuero: %s, Sunroof: %s, AC: %s, Autopilot: %s]
                  Sensores y Rines: [Dashcam: %s, Sensores Parqueo: %s, Rines Aleación: %s]
                  Garantía: %d años
                }""".formatted(
                vehicleType, engine, transmission, color,
                hasGps, hasPremiumSound, hasRoadsideAssistance, hasLeatherSeats, hasSunroof, hasAirConditioning, hasAutopilot,
                hasDashcam, hasParkingSensors, hasAlloyWheels, warrantyYears
        );
    }
}
