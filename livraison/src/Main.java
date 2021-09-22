import Vue.*;
import javax.swing.*;
import java.awt.*;
import Modele.*;

/**
 * <h1> Main </h1>
 * <p> Classe qui lance le programme: il lance le jeu en mode Graphique avec Affichage et
 *une version du jeu console avec Modele.Main.
 * @author Paul et Olivier
 */

public class Main{

	public static void main(String[] args) {

		//permet de lancer une version MVC de l'objet
		Vue.Affichage aff = new Affichage();

		//permet de jouer en console
		Modele.Main terminal = new Modele.Main();
		terminal.exe();

		/*nous pouvons jouer sur la version visuel ou sur la version console mais les
		deux sont indépendante. Cela signifie que vous pouvez jouer sur les deux versions
		en même temps mais que les plateau de jeux seront différent et il sera très dur de vous
		repérer sur la version console*/
	}

}
