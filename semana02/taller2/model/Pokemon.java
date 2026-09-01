package taller2.model;

import java.util.Objects;

/**
 * Modelo de dominio para representar una entidad Pokémon en el sistema.
 * Aplica principios de Clean Code, encapsulamiento y cohesión funcional.
 */
public class Pokemon {
    private Long id;
    private String nombre;
    private String tipo;
    private int nivel;
    private double poderCombate;
    private String region;
    private boolean legendario;
    private boolean puedeEvolucionar;

    // Constructor por defecto
    public Pokemon() {
    }

    // Constructor completo requerido para los niveles 3 en adelante
    public Pokemon(Long id, String nombre, String tipo, int nivel, double poderCombate, String region, boolean legendario) {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.tipo = tipo;
        this.nivel = nivel;
        this.poderCombate = poderCombate;
        this.region = region;
        this.legendario = legendario;
    }

    // Constructor de conveniencia (Nivel 1 & 2: Nombre y Tipo)
    public Pokemon(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // Constructor de conveniencia (Nivel 1: Nombre y Nivel)
    public Pokemon(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    // Constructor de conveniencia (Nivel 2: Nombre y puedeEvolucionar)
    public Pokemon(String nombre, boolean puedeEvolucionar) {
        this.nombre = nombre;
        this.puedeEvolucionar = puedeEvolucionar;
    }

    // Constructor de conveniencia (Nivel 3: Nombre y poderCombate)
    public Pokemon(String nombre, double poderCombate) {
        this.nombre = nombre;
        this.poderCombate = poderCombate;
    }

    // Constructor de conveniencia (Nivel 3: Nombre, Tipo, Región)
    public Pokemon(String nombre, String tipo, String region) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.region = region;
    }

    // Constructor completo incluyendo puedeEvolucionar
    public Pokemon(Long id, String nombre, String tipo, int nivel, double poderCombate, String region, boolean legendario, boolean puedeEvolucionar) {
        this(id, nombre, tipo, nivel, poderCombate, region, legendario);
        this.puedeEvolucionar = puedeEvolucionar;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getPoderCombate() {
        return poderCombate;
    }

    public void setPoderCombate(double poderCombate) {
        this.poderCombate = poderCombate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isLegendario() {
        return legendario;
    }

    public void setLegendario(boolean legendario) {
        this.legendario = legendario;
    }

    public boolean isPuedeEvolucionar() {
        return puedeEvolucionar;
    }

    public void setPuedeEvolucionar(boolean puedeEvolucionar) {
        this.puedeEvolucionar = puedeEvolucionar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(nombre, pokemon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        if (poderCombate > 0 && nivel > 0) {
            return nombre + "(Nivel: " + nivel + ", PC: " + (int) poderCombate + ")";
        } else if (poderCombate > 0) {
            return nombre + "(" + (int) poderCombate + ")";
        } else if (nivel > 0) {
            return nombre + "(" + nivel + ")";
        } else if (tipo != null) {
            return nombre + "(" + tipo + ")";
        }
        return nombre;
    }
}
