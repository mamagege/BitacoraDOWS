package edu.dosw.lab.comportamiento.reto7;

/**
 * Receiver del patrón Command.
 * Contiene la lógica de negocio y operaciones sobre los módulos del rover Chibchombo.
 */
public class RoverChibchombo {

    public void avanzarMotor(int metros, String operador) {
        System.out.println("Rover avanza " + metros + " m [" + operador + "]");
    }

    public void retrocederMotor(int metros, String operador) {
        System.out.println("Rover retrocede " + metros + " m [" + operador + "]");
    }

    public void recogerBrazo(String operador) {
        System.out.println("Muestra recogida [" + operador + "]");
    }

    public void soltarBrazo(String operador) {
        System.out.println("Muestra soltada [" + operador + "]");
    }

    public void grabarCamara(int segundos, String operador) {
        System.out.println("Cámara grabando " + segundos + " s [" + operador + "]");
    }

    public void detenerCamara(String operador) {
        System.out.println("Cámara detenida [" + operador + "]");
    }

    public void perforarTaladro(int cm, String operador) {
        System.out.println("Taladro perfora " + cm + " cm [" + operador + "]");
    }

    public void retraerTaladro() {
        System.out.println("Acción deshecha: Taladro se retrae.");
    }
}
