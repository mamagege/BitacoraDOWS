package edu.dosw.lab.creacionales.reto2;

import java.util.*;

public class TrajeBuilder {
    private Pieza tela;
    private Pieza saco;
    private Pieza pantalon;
    private final List<Pieza> opcionales = new ArrayList<>();

    public TrajeBuilder conTela(String tipo) {
        if (tipo.equalsIgnoreCase("Lana italiana")) {
            this.tela = new Pieza("Lana ital.", 320000);
        } else {
            this.tela = new Pieza("Paño nacional", 150000);
        }
        return this;
    }

    public TrajeBuilder conSaco(String tipo) {
        if (tipo.equalsIgnoreCase("Cruzado")) {
            this.saco = new Pieza("Cruzado", 250000);
        } else {
            this.saco = new Pieza("Recto", 200000);
        }
        return this;
    }

    public TrajeBuilder conPantalon(String tipo) {
        if (tipo.equalsIgnoreCase("Corte slim")) {
            this.pantalon = new Pieza("Slim", 180000);
        } else {
            this.pantalon = new Pieza("Clásico", 160000);
        }
        return this;
    }

    public TrajeBuilder agregarOpcional(String tipo, String valor) {
        if (tipo.equalsIgnoreCase("Chaleco") && !valor.equalsIgnoreCase("Ninguno")) {
            this.opcionales.add(new Pieza("Chaleco " + valor, 90000));
        }
        if (tipo.equalsIgnoreCase("Forro") && !valor.equalsIgnoreCase("Ninguno")) {
            this.opcionales.add(new Pieza("Forro " + valor, 70000));
        }
        if (tipo.equalsIgnoreCase("Bordado") && !valor.equalsIgnoreCase("Ninguno")) {
            this.opcionales.add(new Pieza("Bordado " + valor, 35000));
        }
        return this;
    }

    public Traje build() {
        if (tela == null || saco == null || pantalon == null) {
            throw new IllegalStateException("El traje debe tener obligatoriamente tela, saco y pantalón.");
        }

        List<Pieza> piezasEnsambladas = new ArrayList<>();
        piezasEnsambladas.add(tela);
        piezasEnsambladas.add(saco);
        piezasEnsambladas.add(pantalon);
        piezasEnsambladas.addAll(opcionales);

        return new Traje(piezasEnsambladas);
    }
}