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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;


import misComponentes.Titulos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VistaEscalerasYSerpientes extends JFrame {
	private JPanel panelInicial, panelScroll, areaBotones, areaDados; 
	private Tablero panelTablero;
	private JLabel imagenJuego, imagenFicha;
	private JTextArea turno,lanzar,ficha, informacion;
	private Titulos titulo;
	private JButton dado, salir, inicio, ayuda;
	private Escucha escucha; 
	private String [] botones = { "Volver a jugar", "salir"};
	private String [] botones1 = { "Continuar", "salir"};
	public final Font fuenteBotones= new Font(Font.DIALOG,Font.BOLD+Font.CENTER_BASELINE,20);
	private Jugador humano;
	private JugadorSimulado jugadorVirtual1, jugadorVirtual2;
	private JFrame yoMisma= this;
	private JScrollPane scroll1;
	private boolean ganador;
	//Se inicializa la ventana

	public VistaEscalerasYSerpientes() {

		initGUI();  

		this.setUndecorated(false);
		this.pack();
		this.setBackground(new Color (250, 215, 160 ));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initGUI() {
		//Define el contenedor y layaout

		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contenedor = new GridBagConstraints();	

		//Crear los objetos
		escucha= new Escucha();

		//titulos
		titulo = new Titulos("Escaleras y serpientes",30,new Color (202,200,200));
		titulo.setBorder((new MatteBorder(6, 6, 6, 6,new Color  (0,0,0))));
		contenedor.gridx=0;
		contenedor.gridy=0;
		contenedor.gridwidth=3;
		contenedor.fill= GridBagConstraints.HORIZONTAL;
		add(titulo,contenedor); 

		//turno
		turno = new JTextArea("Es tu turno...");
		turno.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		turno.setPreferredSize((new Dimension(130,40)));
		turno.setBackground(new Color(202,200,200));
		turno.setFont(fuenteBotones);
		turno.setEditable(false);
		turno.setVisible(false);

		//Lanzar
		lanzar = new JTextArea("Lanzar dado");
		lanzar.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		lanzar.setPreferredSize((new Dimension(125,30)));
		lanzar.setBackground(new Color(202,200,200));
		lanzar.setFont(fuenteBotones);
		lanzar.setEditable(false);
		lanzar.setVisible(false);

		//ficha
		ficha = new JTextArea("Tu ficha es:");
		ficha.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		ficha.setPreferredSize((new Dimension(125,30)));
		ficha.setBackground(new Color(202,200,200));
		ficha.setFont(fuenteBotones);
		ficha.setEditable(false);
		ficha.setVisible(false);

		//Informacion

		//panelScroll= new JPanel();
		//panelScroll.setPreferredSize(new Dimension(80,105));
		informacion= new JTextArea();
		informacion.setText("Aquí encontrarás información");
		informacion.setPreferredSize(new Dimension(180,100));
		informacion.setEditable(true);
	
		

		//scroll1 = new JScrollPane(informacion);
		//panelScroll.add(scroll1);

		//panelScroll.setVisible(false);



		//Ficha Jugador HUmano 
		imagenFicha= new JLabel();
		imagenFicha.setPreferredSize(new Dimension(75,75));
		ImageIcon fichaIma = new ImageIcon("src/recursosImagenes/Ficha3Mostrar.png"); 
		imagenFicha.setIcon(fichaIma);
		//te adoro Caro
		imagenFicha.setBackground(null);
		imagenFicha.setVisible(false);


		//Dado

		dado = new JButton ();
		ImageIcon dadoIma = new ImageIcon("src/recursosImagenes/dado.png"); 
		dado.setIcon(dadoIma);
		dado.setPreferredSize(new Dimension(180,180));
		dado.setBackground(null);
		dado.setBorder((null));
		dado.addActionListener(escucha);
		dado.setVisible(false);


		//tablero


		panelTablero =new Tablero(this);
		panelTablero.setPreferredSize(new Dimension(500,500));
		panelTablero.setVisible(false);

		contenedor.gridx=0;
		contenedor.gridy=1 ;
		contenedor.gridwidth=2;
		contenedor.fill= GridBagConstraints.NONE;
		add(panelTablero,contenedor);

		//areaDados

		areaDados = new JPanel();
		areaDados.setPreferredSize(new Dimension(200,500));
		areaDados.setBackground(new Color(136,193,234));
		areaDados.setVisible(true);
		areaDados.setBorder((new MatteBorder(6, 6, 6, 6,new Color (4,173,187))));
		areaDados.add(turno);
		areaDados.add(lanzar);
		areaDados.add(dado);
		areaDados.add(ficha);
		areaDados.add(imagenFicha);
		areaDados.add(informacion);
		areaDados.setVisible(false);
		contenedor.gridx=2;
		contenedor.gridy=1;
		//contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(areaDados,contenedor);


		//Panel Inicial 

		panelInicial = new JPanel();
		panelInicial();
		contenedor.gridx=0;
		contenedor.gridy=1 ;
		contenedor.gridwidth=3;
		contenedor.fill= GridBagConstraints.NONE;
		add(panelInicial,contenedor);



		//Ayuda
		ayuda = new JButton ();
		ayuda = new JButton ("Ayuda");
		ayuda.setPreferredSize(new Dimension(200,80));
		ayuda.addActionListener(escucha);
		ayuda.setBackground(new Color (136,193,234));
		ayuda.setForeground(new Color (0,0,0));

		ayuda.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		ayuda.setFont(fuenteBotones);
		ayuda.setVisible(false);

		contenedor.gridx=2;
		contenedor.gridy=2 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(ayuda,contenedor);

		//Jugar
		inicio = new JButton ();
		ImageIcon inicioBoton= new ImageIcon("src/recursosImagenes/jugar.png"); 
		inicio.setIcon(inicioBoton);
		inicio.setPreferredSize(new Dimension(200,100));
		inicio.setBackground(new Color(241,241,241));
		inicio.setBorder(null);
		//salir.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		inicio.addActionListener(escucha);
		//i.setForeground(new Color (255, 255, 255));

		contenedor.gridx=2;
		contenedor.gridy=2 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(inicio,contenedor);



		//salir
		salir = new JButton ();
		ImageIcon salirIma = new ImageIcon("src/recursosImagenes/salirBoton.jpg"); 
		salir.setIcon(salirIma);
		salir.setPreferredSize(new Dimension(100,100));
		//salir.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		salir.addActionListener(escucha);
		salir.setForeground(new Color (255, 255, 255));

		contenedor.gridx=1;
		contenedor.gridy=2 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(salir,contenedor);

		//Panel botones

		areaBotones = new JPanel();
		areaBotones.setPreferredSize(new Dimension(510,100));
		//areaBotones.setBackground(new Color(202,200,200));


		contenedor.gridx=0;
		contenedor.gridy=2 ;
		contenedor.gridwidth=3;
		contenedor.fill= GridBagConstraints.NONE;
		add(areaBotones,contenedor);

	}

	private class Escucha implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()== salir) {
				String mensaje = "¿Estás seguro que deseas salir del juego?";
				ImageIcon salidaEmergencia= new ImageIcon("src/recursosImagenes/salidaEmergencia.jpg"); 

				int result= JOptionPane.showOptionDialog (null, mensaje, "Resultado", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, salidaEmergencia , botones, botones[0]);

				if(result == JOptionPane.YES_OPTION) {
					yoMisma.setVisible(false);
					PrincipalEscalerasYSerpientes.main(null); //reinicia el juego automaticamente 
					//Teadoro
				}

				if(result == JOptionPane.NO_OPTION) {
					System.exit(0); 
				}
			}


			if(e.getSource()==inicio) {
				panelInicial.setVisible(false);
				areaBotones.setVisible(false);
				inicio.setVisible(false);
				titulo.setVisible(false);

				ImageIcon informativa = new ImageIcon("src/recursosImagenes/info.jpg"); 

				String mensaje2 = " Reglas del Juego:\n"+"Encontrarás un tablero con 2 jugadores además de ti, tu misión\n"+" es llegar a la casilla 100, sin embargo, no es tan fácil, si al lanzar\n"+
						"caes en la cabeza de una serpiente tendrás que bajar a la\n"+"casilla que se indique, pero si caes en una escalera podrás subir\n"+
						"la casilla que se indique.\n"+"El primero en llegar a 100 es el ganador.\n"+"Animo, buena suerte";


				int result= JOptionPane.showOptionDialog (null, mensaje2, "Reglas del juego", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, informativa, botones1, botones1[0]);

				if(result == JOptionPane.YES_OPTION) {
					titulo.setVisible(true);
					areaBotones.setVisible(true);
					ayuda.setVisible(true);
					salir.setVisible(true);
					inicializar();
					pack();
				}
				if(result == JOptionPane.NO_OPTION) {
					System.exit(0); 
				}
				
				
				
			}


			if(e.getSource()==ayuda) {

				ImageIcon informativa = new ImageIcon("src/recursosImagenes/info.jpg"); 

				String mensaje2 = " Reglas del Juego:\n"+"Encontrarás un tablero con 2 jugadores además de ti, tu misión\n"+" es llegar a la casilla 100, sin embargo, no es tan fácil, si al lanzar\n"+
						"caes en la cabeza de una serpiente tendrás que bajar a la\n"+"casilla que se indique, pero si caes en una escalera podrás subir\n"+
						"la casilla que se indique.\n"+"El primero en llegar a 100 es el ganador.\n"+"Animo, buena suerte";
				int result= JOptionPane.showOptionDialog (null, mensaje2, "Reglas del juego", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, informativa, null, null);
			}

			if(e.getSource()==dado){
				humano = panelTablero.getHumano();
				jugadorVirtual1 = panelTablero.getJugadorVirtual1();
				jugadorVirtual2 = panelTablero.getJugadorVirtal2();
				controlFicha(humano);
				desactivarEscucha();
				panelTablero.iniciarJugadoresSimulados();
				textoInfo(humano);
			}
		}

	}


	public void inicializar() {

		turno.setVisible(true);
		lanzar.setVisible(true); 
		ficha.setVisible(true);
		dado.setVisible(true);
		imagenFicha.setVisible(true);
		//panelScroll.setVisible(true);
		panelTablero.setVisible(true);
		areaDados.setVisible(true);
	}

	public void panelInicial() {

		panelInicial.setPreferredSize(new Dimension(510,500));
		imagenJuego= new JLabel();
		ImageIcon icon = new ImageIcon("src/recursosImagenes/principalJuego.png"); 
		imagenJuego.setIcon(icon);
		panelInicial.add(imagenJuego);

	}

	public void controlFicha(Jugador jugador) {
		int valorDado = jugador.lanzarDado();
		ImageIcon dadoIma = new ImageIcon("src/recursosImagenes/"+valorDado+".png"); 
		dado.setIcon(dadoIma);
		panelTablero.moverFicha(jugador, valorDado);
		System.out.print("\n");
		System.out.print(jugador.idPosicion());
		System.out.print("\n");
		System.out.print(jugador.getCasilla().getRow());
		System.out.print(jugador.getCasilla().getCol());
		System.out.print("\n");
		if(validarGanador(jugador)){
      		jugadorVirtual1.parar();
      		jugadorVirtual2.parar();
      
      		System.out.print(1);
      		System.out.print("\n");
			int cualJugador = jugador.getIdJugador(); 
			String mensaje =""; 
			ImageIcon resultado;
			if(cualJugador==1) {
           System.out.print(2);
           System.out.print("\n");

				mensaje = "Felicitaciones, has ganado\n"+"¿Deseas jugar otra vez?";
				resultado= new ImageIcon("src/recursosImagenes/ganaste.jpg");


			}else {
        System.out.print(2);
        System.out.print("\n");

				mensaje = "Lo siento, has perdido\n"+"Ha ganado el jugador simulado"+cualJugador+"¿Deseas jugar otra vez?";
				resultado= new ImageIcon("src/recursosImagenes/perdiste.jpg");



			}
			   System.out.print(3);
			   System.out.print("\n");

			int result= JOptionPane.showOptionDialog (null, mensaje, "Resultado", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, resultado , botones, botones[0]);

			if(result == JOptionPane.YES_OPTION) {
				yoMisma.setVisible(false);
				PrincipalEscalerasYSerpientes.main(null); //reinicia el juego automaticamente 
			}

			if(result == JOptionPane.NO_OPTION) {
				System.exit(0); 
			}
			   System.out.print(3);




		}
		panelTablero.revalidate();
		panelTablero.repaint();

	}

	public void activarEscucha() {

		dado.addActionListener(escucha);
		ImageIcon dadoIma = new ImageIcon("src/recursosImagenes/dado.png"); 
		dado.setIcon(dadoIma);
	}

	public void desactivarEscucha() {
		dado.removeActionListener(escucha);

	}

	public boolean validarGanador(Jugador jugador) {
		ganador=false;
		System.out.print("entró\n");

		if(jugador.idPosicion()==100) {
      System.out.print("cambioBool");
      System.out.print("\n");
			ganador=true;


		}


		return ganador;
	}
	
	public void textoInfo(Jugador jugador) {
		if(jugador.getIdJugador()==1 && panelTablero.getEscaleras()){
			
			informacion.setText(" ");
			String texto= "Que bien, caiste en una\n"+"escalera, subes.";
			informacion.setText(texto);	
		}
		
		if(jugador.getIdJugador()==1 && panelTablero.getSerpientes()){
			
			informacion.setText(" ");
			String texto= "Lo siento, has caido en una\n"+"serpiente,retrocede.";
			informacion.setText(texto);	
		}
		
	}




}