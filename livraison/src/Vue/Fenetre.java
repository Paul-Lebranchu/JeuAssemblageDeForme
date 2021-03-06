package Vue;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.Applet;
import Modele.*;
import Controlleur.*;

/**
 * <h1> Fenetre </h1>
 * <p> Classe générant le JPanel qui contiendra le jeu
 * @author Paul
 */
public class Fenetre extends JPanel{

  protected Color background;
  protected Menu menu;
  /**
	 * <p> Constructeur: Définis la couleur de fond et créé une instance de
   * la classe Menu et l'affiche</p>
 */
  public Fenetre(){
    super();
    this.setLayout(null);
    this.background = new Color(100, 131, 105);
    this.menu = new Menu(this);
    this.repaint();
  }

  /**
	 * <p> méthode dessinnant le fond de la fenêtre</p>
 */
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    //fond d'écran
    g.setColor(this.background);
    g.fillRect(0,0,this.getWidth(),this.getHeight());
  }
}
