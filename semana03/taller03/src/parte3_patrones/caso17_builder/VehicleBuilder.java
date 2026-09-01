package parte3_patrones.caso17_builder;

import java.util.Objects;

/**
 * Patrón: BUILDER (Creacional)
 * ¿Por qué?: Resuelve el antipatrón Telescoping Constructor, permitiendo la
 * parametrización fluida de más de 15 atributos opcionales con validación atómica final.
 */
public class VehicleBuilder {
    private final String vehicleType;
    private String engine = "2.0L Turbo 4-Cilindros";
    private String transmission = "Automática 8-Velocidades";
    private String color = "Blanco Glaciar";
    private boolean hasGps = false;
    private boolean hasPremiumSound = false;
    private boolean hasRoadsideAssistance = false;
    private boolean hasLeatherSeats = false;
    private boolean hasSunroof = false;
    private boolean hasAirConditioning = true;
    private boolean hasAutopilot = false;
    private boolean hasDashcam = false;
    private boolean hasParkingSensors = false;
    private boolean hasAlloyWheels = true;
    private int warrantyYears = 3;

    public VehicleBuilder(String vehicleType) {
        this.vehicleType = Objects.requireNonNull(vehicleType, "El tipo de vehículo es obligatorio");
    }

    public VehicleBuilder withEngine(String engine) {
        this.engine = Objects.requireNonNull(engine);
        return this;
    }

    public VehicleBuilder withTransmission(String transmission) {
        this.transmission = Objects.requireNonNull(transmission);
        return this;
    }

    public VehicleBuilder withColor(String color) {
        this.color = Objects.requireNonNull(color);
        return this;
    }

    public VehicleBuilder withGps(boolean hasGps) {
        this.hasGps = hasGps;
        return this;
    }

    public VehicleBuilder withPremiumSound(boolean hasPremiumSound) {
        this.hasPremiumSound = hasPremiumSound;
        return this;
    }

    public VehicleBuilder withRoadsideAssistance(boolean hasRoadsideAssistance) {
        this.hasRoadsideAssistance = hasRoadsideAssistance;
        return this;
    }

    public VehicleBuilder withLeatherSeats(boolean hasLeatherSeats) {
        this.hasLeatherSeats = hasLeatherSeats;
        return this;
    }

    public VehicleBuilder withSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
        return this;
    }

    public VehicleBuilder withAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
        return this;
    }

    public VehicleBuilder withAutopilot(boolean hasAutopilot) {
        this.hasAutopilot = hasAutopilot;
        return this;
    }

    public VehicleBuilder withDashcam(boolean hasDashcam) {
        this.hasDashcam = hasDashcam;
        return this;
    }

    public VehicleBuilder withParkingSensors(boolean hasParkingSensors) {
        this.hasParkingSensors = hasParkingSensors;
        return this;
    }

    public VehicleBuilder withAlloyWheels(boolean hasAlloyWheels) {
        this.hasAlloyWheels = hasAlloyWheels;
        return this;
    }

    public VehicleBuilder withWarrantyYears(int warrantyYears) {
        if (warrantyYears < 1 || warrantyYears > 10) {
            throw new IllegalArgumentException("La garantía debe ser entre 1 y 10 años");
        }
        this.warrantyYears = warrantyYears;
        return this;
    }

    public ConfiguredVehicle build() {
        // Validaciones de negocio cruzadas
        if (hasAutopilot && !hasParkingSensors) {
            this.hasParkingSensors = true; // Autopilot requiere sensores obligatoriamente
        }
        return new ConfiguredVehicle(
                vehicleType, engine, transmission, color,
                hasGps, hasPremiumSound, hasRoadsideAssistance, hasLeatherSeats, hasSunroof,
                hasAirConditioning, hasAutopilot, hasDashcam, hasParkingSensors, hasAlloyWheels,
                warrantyYears
        );
    }
}
