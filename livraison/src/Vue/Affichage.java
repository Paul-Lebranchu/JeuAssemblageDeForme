package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Modele.*;

/**
 * <h1> Affichage </h1>
 * <p> Classe générant le JFrame où se trouvera le jeu
 * @author Olivier
 */
public class Affichage extends JFrame{
	/**
		*constructeur :  créé une instance de la classe Fenetre qui
		*sera affiché dans le JFrame  + réglagle du JFrame
	*/
	protected Fenetre fen;

	public Affichage(){

		this.setTitle("WIP Jeu d'assemblage de forme");
		this.fen = new Fenetre();
		this.setPreferredSize(new Dimension(1000,750));
		this.setResizable(true);
		this.setContentPane(fen);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
