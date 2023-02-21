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
import javax.imageio.ImageIO;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Tablero extends JPanel {
	
	public static final String rutaFile= "src/recursosImagenes/tableroJuego.png";
	private int fichaSize = 50;
	private int gridSize = 10;//tamaño de la matriz
	private BufferedImage bufferImage = null;
	private Casillas[][] tablero = new Casillas[gridSize][gridSize];
	private Jugador humano;
	private JugadorSimulado jugadorVirtual1, jugadorVirtual2;
	private VistaEscalerasYSerpientes vista;
	//variables control hilos 
	private int turno; //variable de control de turno hilos
	private Lock bloqueo = new ReentrantLock(); //manejo de sincronizacion
	private Condition esperarTurno = bloqueo.newCondition(); //manejo de sincronizacion	
	private Thread hilo;
	private boolean escalera,serpientes;
	
	public Tablero(VistaEscalerasYSerpientes vista){
		try {
			

			this.vista = vista;
			bufferImage = ImageIO.read(new File(rutaFile));
			Casillas.setFichaSizeMaxFichas(fichaSize, gridSize*gridSize);    
		    
		    
		    cargarCasillas();
		   escalera=false; 
		   serpientes=false;
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cargarCasillas(){
		
		this.setPreferredSize(new Dimension(500,500));
	    this.setLayout(new GridBagLayout());
	    GridBagConstraints contenedorTablero = new GridBagConstraints();	
		
		int id=100;
		
		for(int row=0; row<gridSize; row++) 
		{
			
				if(id==100 || id==80 || id==60 || id == 40 || id==20){
					
					for(int col=0; col<gridSize; col++){
					int x = col*fichaSize;
					int y = row*fichaSize;
					
			
					BufferedImage subImage = bufferImage.getSubimage(x, y, fichaSize, fichaSize);
					ImageIcon casillaImage = new ImageIcon(subImage);
					tablero[row][col] = new Casillas(casillaImage,id, row, col);
					
					contenedorTablero.gridx=col;
					contenedorTablero.gridy=row;
					contenedorTablero.gridwidth=1;
					contenedorTablero.gridheight = 1;	
					contenedorTablero.fill= GridBagConstraints.NONE;
					add(tablero[row][col],contenedorTablero); 	
									
					//this.add(tablero[row][col] );
					id--;
				
					}
					
					
				}else if(id==90 || id==70 || id==50 || id == 30 || id==10){
					
					for(int col=gridSize-1; col>=0; col--){
					int x = col*fichaSize;
					int y = row*fichaSize;
		
			
					BufferedImage subImage = bufferImage.getSubimage(x, y, fichaSize, fichaSize);
					ImageIcon casillaImage = new ImageIcon(subImage);
					tablero[row][col] = new Casillas(casillaImage,id ,row, col);
					
					contenedorTablero.gridx=col;
					contenedorTablero.gridy=row;
					contenedorTablero.gridwidth=1;
					contenedorTablero.gridheight = 1;	
					contenedorTablero.fill= GridBagConstraints.NONE;
					add(tablero[row][col],contenedorTablero); 
					//this.add(tablero[row][col]);
					if(id==1){
						//creacion jugadores
						humano = new Jugador(tablero[row][col],1);
						jugadorVirtual1 = new JugadorSimulado(tablero[row][col],2,1,this);
						jugadorVirtual2 = new JugadorSimulado(tablero[row][col], 3, 2, this);
						//añade jugadores a la primera casilla 
						tablero[row][col].pintarJugador(1);
						tablero[row][col].pintarJugador(2);
						tablero[row][col].pintarJugador(3);
					} 
					
					id--;

					}	
									
				}
				
				
			}
			
		}
		
		public void iniciarJugadoresSimulados() {
		// TODO Auto-generated method stub
		
		  turno=1;//inicia el jugadorVirtual 1
		  // iniciar hilos
		  
		  ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();
		  ejecutorSubprocesos.execute(jugadorVirtual1); 
		  ejecutorSubprocesos.execute(jugadorVirtual2);
		  ejecutorSubprocesos.shutdown();
	}
	
	//método a sincronizar - condition es el turno 
	public void turnos(JugadorSimulado jugadorSimulado) {
		//bloquear la clase
		bloqueo.lock();
		try {
			//validar condición de ejecucion para el hilo
			while(jugadorSimulado.getTurno() !=turno) { //turno= 1 le toca a jugador 1 y turno=2 le toca a jugador 2
				//dormir al jugador porque no es su turno
				esperarTurno.await();	  
			}

			//ejecutar tarea, variar condición de ejecucion, desbloquear el objeto
			Thread.sleep(3000);
			System.out.print("hilo va a mover la ficha");
			System.out.print("\n");
			vista.controlFicha(jugadorSimulado);
			System.out.print("hilo movio la ficha");
			turno++;
			esperarTurno.signalAll();	
	

		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			bloqueo.unlock();
			if(turno==3) {
				vista.activarEscucha();
			}
		}
		
		
	}
	
		

	public Jugador getHumano() {
		return humano;
	}

	public JugadorSimulado getJugadorVirtual1() {
		return jugadorVirtual1;
	}

	public JugadorSimulado getJugadorVirtal2() {
		return jugadorVirtual2;
	}

		public void moverFicha(Jugador jugador, int valorDado){
			//borrado
			Casillas inicial = jugador.getCasilla();
			inicial = tablero[inicial.getRow()][inicial.getCol()];
			int idInicial = tablero[inicial.getRow()][inicial.getCol()].getIdCasilla();
			inicial.quitarJugador(jugador.getIdJugador());
			
			//
			int idFinal = idInicial+valorDado;
			idFinal = determinarCasillaFinal(idFinal);
		
			if(idFinal >=100){
				idFinal=100;
				jugador.nuevaPosicion(tablero[0][0]);
				tablero[0][0].pintarJugador(jugador.getIdJugador());
			
				
			}else{
				for(int row=0; row<gridSize; row++){
					for(int col=0; col<gridSize; col++){
						
										
						if(tablero[row][col].getIdCasilla()==idFinal){
							Casillas casillaNueva= tablero[row][col];
							casillaNueva.pintarJugador(jugador.getIdJugador());	
							jugador.nuevaPosicion(casillaNueva);
									
						}
					
					}
				}
			}
								
		}
		
		public int determinarCasillaFinal(int idFinal) {
			
			int nuevoIdFinal = idFinal;
			
			//Escaleras
			
			if(idFinal == 8) {
				escalera=true;
				nuevoIdFinal = 28;
				
			}
			
			if(idFinal == 15) {
				escalera=true;
				nuevoIdFinal = 47;
				
			}
			
			if(idFinal == 21) {
				escalera=true;
				nuevoIdFinal = 42;
			}
			
			if(idFinal == 31) {
				escalera=true;
				nuevoIdFinal = 72;
			}
			
			if(idFinal == 55) {
				escalera=true;
				nuevoIdFinal = 65;
			}
			
			if(idFinal == 71) {
				escalera=true;
				nuevoIdFinal = 91;
			}
			
			if(idFinal == 78) {
				escalera=true;
				nuevoIdFinal = 98;
			}
			
			//Serpientes
			
			if(idFinal == 16) {
				serpientes=true;
				nuevoIdFinal = 6;
			}
			
			if(idFinal == 52) {
				serpientes=true;
				nuevoIdFinal = 29;
			}
			
			if(idFinal == 77) {
				serpientes=true;
				nuevoIdFinal = 17;
			}
			
			if(idFinal == 82) {
				serpientes=true;
				nuevoIdFinal = 61;
			}
			
			if(idFinal == 93) {
				serpientes=true;
				nuevoIdFinal = 67;
			}		
			
			if(idFinal == 95) {
				serpientes=true;
				nuevoIdFinal = 84;
			}
			
			if(idFinal == 99) {
				serpientes=true;
				nuevoIdFinal = 62;
			}
			
			return nuevoIdFinal;
		
		}
		
		public Casillas[][] getCasillas(){
			return tablero;
		}
		
		public boolean getEscaleras() {
			return escalera;
			
		}
		public boolean getSerpientes() {
			return serpientes;
			
		}
		
		
	}
	//
	
	
	
	
	
	