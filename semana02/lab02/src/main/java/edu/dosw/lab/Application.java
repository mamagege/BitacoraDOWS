package edu.dosw.lab;

import edu.dosw.lab.comportamiento.reto6.Reto6SalaUrgencias;
import edu.dosw.lab.comportamiento.reto7.Reto7RoverMarte;
import edu.dosw.lab.estructurales.reto5.Reto5MotoPersonalizada;

public class Application {

	public static void main(String[] args){
		edu.dosw.lab.solid.reto1.Reto1BoleteriaAstor.ejecutar();
		edu.dosw.lab.creacionales.reto2.Reto2SastreMedida.ejecutar();
		edu.dosw.lab.creacionales.reto3.Reto3FabricaInstrumentos.ejecutar();
		edu.dosw.lab.comportamiento.reto4.Reto4BalanzaTrucada.ejecutar();
		Reto5MotoPersonalizada.ejecutar();
		Reto6SalaUrgencias.ejecutar();
		Reto7RoverMarte.ejecutar();

	}
}
