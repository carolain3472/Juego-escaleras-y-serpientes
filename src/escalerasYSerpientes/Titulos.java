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

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Titulos extends JLabel{

		//atributos 
			//metodos

			public Titulos (String texto, int tamano, Color colorFondo) {

				this.setText(texto);
				Font font = new Font(Font.SERIF,Font.BOLD+Font.ITALIC,tamano);
				this.setFont(font);
				this.setBackground(colorFondo);
				this.setForeground(Color.BLACK);
				this.setHorizontalAlignment(JLabel.CENTER);
				this.setOpaque(true);
			}

		

	}


