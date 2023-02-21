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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

public class Casillas extends JLabel {
	
	private static int fichaSize=0;//variables de clase. si se modifica se modifica para todos los objetos de la clase.
	private static int maxFichas = 0;//para alterar estas variables por fuera de la clase 
	public static final File rutaFileJugador1= new File("src/recursosImagenes/Ficha3.png");
	public static final File rutaFileJugador2= new File("src/recursosImagenes/Ficha2.png");
	public static final File rutaFileJugador3= new File("src/recursosImagenes/Ficha1.png");
	private int row; 
	private int col; 
	private int idCasilla; 
	private ArrayList<Integer> jugadoresEnCasilla = new ArrayList();
	private ImageIcon image;
	private Image jugadorHumano,jugadorSimulado1, jugadorSimulado2;
	private BufferedImage humano; 
	
	
	public Casillas(ImageIcon image, int idCasilla, int row, int col) 
	{
		this.row = row;
		this.col = col;
		//this.jugadorHumano = jugadorHumano;
		//this.jugadorSimulado1 = jugadorSimulado1;
		//this.jugadorSimulado2 = jugadorSimulado2;
		

		
		setImage(image, idCasilla);
		this.setBackground(Color.WHITE);
		Dimension size = new Dimension(fichaSize, fichaSize);
		this.setPreferredSize(size);
		this.setBorder(null);
	}
	

	public void setImage(ImageIcon image, int idCasilla)
	{
		this.image=image;
		this.idCasilla = idCasilla;
		setIcon(image);		
		
	}
	
	
	public static void setFichaSizeMaxFichas(int tamano, int numeroFichas)
	{
		//para manipular variables de clase privadas
		//para usarlo como es estatico debo usar el nombre de la clase y luego el metodo 
		maxFichas = numeroFichas;
		fichaSize = tamano;
	}		
	
	public void pintarJugador(int jugador) 
	{
		if(!jugadoresEnCasilla.contains(jugador)){
			jugadoresEnCasilla.add(jugador);		
		}
		setIcon(image);	
		repaint();	
	}
	
	public void quitarJugador(int jugador) 
	{
		if(jugadoresEnCasilla.contains(jugador)){
			jugadoresEnCasilla.remove(jugadoresEnCasilla.indexOf(jugador));
			setIcon(image);	
			repaint();
		}	
			
	}
	
	
	 public void paintComponent(Graphics g)
	 {
		 super.paintComponent(g);
		 try {
			 
			 if(jugadoresEnCasilla.contains(1)){
			BufferedImage humano = ImageIO.read(rutaFileJugador1);
			g.drawImage(humano,10, 5, 20, 20 ,this);
			 }
			
			 if(jugadoresEnCasilla.contains(2)){
			BufferedImage jugadorSimulado1 = ImageIO.read(rutaFileJugador2);
			g.drawImage(jugadorSimulado1, 30, 5, 20, 20 ,this );
			 }
			 
			 if(jugadoresEnCasilla.contains(3)){
			BufferedImage jugadorSimulado2 = ImageIO.read(rutaFileJugador3);
			g.drawImage(jugadorSimulado2, this.getWidth()/2, 25, 20, 20 ,this );
			 }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

	 }
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getIdCasilla() {
		return idCasilla;
	}
	

	public ImageIcon getImage() {
		return image;
	}

}
