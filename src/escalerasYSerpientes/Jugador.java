/*
 * Programación Interactiva. 
 * 
 * Autores: Carolain Jimenez Bedoya - 2071368 
 *          Natalia Lopez Osorio  - 2025618
 *          Hernando Lopez Rincón - 2022318
 *          
 * Mini-proyecto 4: Juego Escaleras y serpientes. 
 */

package escalerasYSerpientes;

import java.util.Random;

public class Jugador {
	
	private Casillas posicion;
	private int idJugador; //1)Jugador humano, 2)jugadorSimulado1, 3) jugadorSimulado2
	
	public Jugador(Casillas posicion, int idJugador){
		
		this.posicion =posicion;
		this.idJugador = idJugador;		
	}
	
	
	public int idPosicion(){
		int posicionJugador = posicion.getIdCasilla();
		return posicionJugador;
	}
	
	
	public Casillas getCasilla(){
		return posicion;
	}
	
	public void nuevaPosicion(Casillas nuevaPosicion){
		this.posicion = nuevaPosicion;
		
	}
	
	public int lanzarDado(){
		int valorDado=0;
		
	  	Random aleatorio = new Random();
	  	valorDado = aleatorio.nextInt(6) + 1;
	  	return valorDado;	
	}
	
	public int getIdJugador(){
		return idJugador;
	} 
	

}
