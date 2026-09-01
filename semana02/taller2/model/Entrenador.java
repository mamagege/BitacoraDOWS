package taller2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Modelo de dominio para representar a un Entrenador Pokémon.
 * Gestiona su identidad, medallas acumuladas y equipo Pokémon.
 */
public class Entrenador {
    private Long id;
    private String nombre;
    private int medallas;
    private List<Pokemon> equipo;

    public Entrenador() {
        this.equipo = new ArrayList<>();
    }

    public Entrenador(Long id, String nombre, int medallas, List<Pokemon> equipo) {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre, "El nombre del entrenador no puede ser nulo");
        this.medallas = medallas;
        this.equipo = (equipo != null) ? new ArrayList<>(equipo) : new ArrayList<>();
    }

    public Entrenador(String nombre, int medallas) {
        this(null, nombre, medallas, new ArrayList<>());
    }

    public Entrenador(String nombre, int medallas, List<Pokemon> equipo) {
        this(null, nombre, medallas, equipo);
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

    public int getMedallas() {
        return medallas;
    }

    public void setMedallas(int medallas) {
        this.medallas = medallas;
    }

    public List<Pokemon> getEquipo() {
        return Collections.unmodifiableList(equipo);
    }

    public void setEquipo(List<Pokemon> equipo) {
        this.equipo = (equipo != null) ? new ArrayList<>(equipo) : new ArrayList<>();
    }

    public void agregarPokemon(Pokemon pokemon) {
        if (pokemon != null) {
            this.equipo.add(pokemon);
        }
    }

    /**
     * Calcula la sumatoria del poder de combate (PC) de todo su equipo Pokémon usando Streams.
     */
    public double calcularPoderTotal() {
        return this.equipo.stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrenador that = (Entrenador) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return nombre + "(" + medallas + " medallas, PC: " + (int) calcularPoderTotal() + ")";
    }
}
