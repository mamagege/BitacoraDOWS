package edu.dosw.lab;

import edu.dosw.lab.comportamiento.reto6.*;
import edu.dosw.lab.comportamiento.reto7.*;
import edu.dosw.lab.estructurales.reto5.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RetosTest {

    @Test
    @DisplayName("Reto 5: Patrón Decorator añade mejoras y calcula precio total con catálogo y constructores simplificados")
    void testReto5Decorator() {
        Moto moto = new MotoBase("Naked 250");
        assertEquals(9_800_000.0, moto.getPrecioTotal());
        assertEquals(0, moto.getMejoras().size());

        moto = new AccesorioDecorator(moto, "Escape deportivo");
        moto = new AccesorioDecorator(moto, "Pintura mate negro");
        moto = new AccesorioDecorator(moto, "Baúl trasero");

        assertEquals(2_850_000.0, moto.getPrecioMejoras());
        assertEquals(12_650_000.0, moto.getPrecioTotal());
        assertEquals(3, moto.getMejoras().size());
        assertTrue(moto.getDescripcion().contains("Escape deportivo"));
        assertTrue(moto.getDescripcion().contains("Baúl trasero"));
    }

    @Test
    @DisplayName("Reto 5: Ejecución con Scanner simulado")
    void testReto5Scanner() {
        Scanner scanner = new Scanner("1, 2, 4\n");
        assertDoesNotThrow(() -> Reto5MotoPersonalizada.ejecutarConScanner(scanner));
    }

    @Test
    @DisplayName("Reto 6: Patrón Chain of Responsibility atiende según nivel y prioridad")
    void testReto6ChainOfResponsibility() {
        ManejadorAtencion cadena = new EnfermeroHandler();
        cadena.setSiguiente(new MedicoGeneralHandler())
              .setSiguiente(new EspecialistaHandler());

        Paciente p1 = new Paciente("P1", "Dolor de garganta", NivelGravedad.LEVE, Prioridad.BAJA);
        Paciente p2 = new Paciente("P2", "Fractura de muñeca", NivelGravedad.MODERADO, Prioridad.MEDIA);
        Paciente p3 = new Paciente("P3", "Dolor en el pecho", NivelGravedad.GRAVE, Prioridad.ALTA);
        Paciente p4 = new Paciente("P4", "Paro cardiaco", NivelGravedad.CRITICO, Prioridad.ALTA);

        cadena.atender(p1);
        cadena.atender(p2);
        cadena.atender(p3);
        cadena.atender(p4);

        assertEquals("Enfermero", p1.getAtendidoPor());
        assertEquals("Médico General", p2.getAtendidoPor());
        assertEquals("Especialista", p3.getAtendidoPor());
        assertTrue(p4.isRemitido());
        assertNull(p4.getAtendidoPor());
    }

    @Test
    @DisplayName("Reto 6: Ejecución con Scanner simulado")
    void testReto6Scanner() {
        String input = "2\nGripe\nLeve\nBaja\nFractura\nModerado\nMedia\n";
        Scanner scanner = new Scanner(input);
        assertDoesNotThrow(() -> Reto6SalaUrgencias.ejecutarConScanner(scanner));
    }

    @Test
    @DisplayName("Reto 7: Patrón Command ejecuta y revierte acciones")
    void testReto7Command() {
        RoverChibchombo rover = new RoverChibchombo();
        ControladorMision controlador = new ControladorMision();

        ComandoRover c1 = new AvanzarMotorCommand(rover, "Camila", 12);
        ComandoRover c2 = new GrabarCamaraCommand(rover, "Camila", 30);
        ComandoRover c3 = new PerforarTaladroCommand(rover, "Camila", 15);

        controlador.enviarComando(c1);
        controlador.enviarComando(c2);
        controlador.enviarComando(c3);

        assertEquals(3, controlador.getHistorial().size());
        assertFalse(c3.isDeshecho());

        controlador.deshacerComando(3);
        assertTrue(c3.isDeshecho());
    }

    @Test
    @DisplayName("Reto 7: Ejecución con Scanner simulado")
    void testReto7Scanner() {
        String input = "2\nCamila\n1\n10\nJulián\n5\n3\n";
        Scanner scanner = new Scanner(input);
        assertDoesNotThrow(() -> Reto7RoverMarte.ejecutarConScanner(scanner));
    }
}
