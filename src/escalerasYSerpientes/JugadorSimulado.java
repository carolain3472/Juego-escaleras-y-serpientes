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

public class JugadorSimulado extends Jugador implements Runnable {
	
	private int turno;
	private Tablero tablero;//recurso compartido
	private boolean condicionSeguir=true;

	public JugadorSimulado(Casillas posicion, int idJugador, int turno, Tablero tablero) {
		super(posicion, idJugador);
		// TODO Auto-generated constructor stub
		this.turno=turno;
		this.tablero=tablero;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stu
		
		while(condicionSeguir) {
			tablero.turnos(this);
		}
   	
   		
	}
	
	public int getTurno(){
		return turno;
	}
	
	public void parar(){
		condicionSeguir=false;
	}
	
	

}
