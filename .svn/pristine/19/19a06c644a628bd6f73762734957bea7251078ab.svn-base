package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import Modele.*;
import piece.*;
/**
 * <h1> AffPlateau </h1>
 * <p> Classe générant les JButton qui simboliseront le Plateau de jeu</p>
 * @author Paul et Olivier
 */
public class AffPlateau extends JPanel /*implements EcouteurModel*/{

	protected int x, y;
	protected int [][] contenu;
	protected JPanel panel;
	protected Plateau plat;
	protected ArrayList<JButton> playButton;
	protected int diffcult;

	/**
	 * <p> Constructeur : prend en argument un Platteau (Platteau a afficher),
	 *un JPanel (où on affichera le Plateau) et un int qui représente la difficulté
	 *selectionner</p>
 */
	public AffPlateau(Plateau platterm, JPanel panel,int difficult){
		this.diffcult = difficult;
		this.plat = platterm;
		this.x = platterm.getHauteur();
		this.y = platterm.getLargeur();
		this.contenu = platterm.getTab2();
		this.panel = panel;
		this.playButton =new ArrayList<JButton>();
		this.creaPlat();
	}

	/**
	 * <p> méthode générant les JButtons symbolisant le Plateau de jeu
	 * dans un premier temps, on génère des gap pour que le plateau soit
	 * centré en fonction de sa taille.
	 * si la case contient un élément d'une pièce(valeur différente de 0)
	 * on lui donne une couleur symbolisant la pièce, sinon, le JButton sera blanc</p>
 */
	public void creaPlat(){
		int tailleCase = 30;
		//gestion du gap (écart avec le bord de la fenetre) en fonction de la difficulté choissis
		int gapVertical;
		int gapHorizontal;
		switch(this.diffcult){
				case 0 :
					gapVertical = 200;
					gapHorizontal = 350;
					break;
				case 1 :
					gapVertical = 220;
					gapHorizontal = 230;
					break;
				case 2 :
					gapVertical = 200;
					gapHorizontal = 125;
					break;
				case 3 :
					gapVertical = 270;
					gapHorizontal = 340;
					break;
				case 4 :
					gapVertical = 220;
					gapHorizontal = 270;
					break;
				case 5 :
					gapVertical = 200;
					gapHorizontal = 250;
					break;
				case 6 :
					gapVertical = 180;
					gapHorizontal = 200;
					break;
				default:
					gapVertical = 200;
					gapHorizontal = 25;
					break;
			}

		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				int val = contenu[i][j];
				//créé un bouton pour chaque case du Plateau de jeu mis en paramètre et le positionne
				JButton bout = new JButton();
				bout.setBounds(gapHorizontal+tailleCase*j,gapVertical+tailleCase*i,tailleCase,tailleCase);
				//si la valeur contenu dans la case du tableau est différente de 0, on met une couleur de fond pour symboliser la pièce
				if(val != 0){
					int valMod = val%8;

					switch(valMod){
						case 0:
							bout.setBackground(new Color(255,255,25));
							break;
						case 1:
							bout.setBackground(new Color(0,230,0));
							break;
						case 2:
							bout.setBackground(new Color(0,230,191));
							break;
						case 3:
							bout.setBackground(new Color(179,0,0));
							break;
						case 4:
							bout.setBackground(new Color(106,0,128));
							break;
						case 5:
							bout.setBackground(new Color(22,22,168));
							break;
						case 6:
							bout.setBackground(new Color(255,0,255));
							break;
						case 7:
							bout.setBackground(new Color(255,42,0));
							break;
					}
				}
				//sinon, on met du blanc comme couleur de fond
				else{
					bout.setBackground(new Color(255,255,255));
				}
				playButton.add(bout);
				panel.add(bout);
			}
		}
	}

	//acceseur de la liste des boutons: à utiliser dans menu pour ajouter les évènement dessus
	public ArrayList<JButton> getListeBouton(){
		return this.playButton;
	}

	/**
	 * <p> méthode qui replace les différents boutons du jeu dans le JPanel, utile pour
	 *le retour à la partie en cours</p>
 */
	public void reaffich(){
		for(JButton b: this.playButton){
			panel.add(b);
		}
	}


	/**
	 * <p> méthode qui met à jour les couleurs des boutons en fonction
	 *du mouvement effectué par l'utilisateur </p>
 */
	public void maj(){
		//cpt = numéro du bouton dans la liste des boutons
		int cpt  = 0;
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				JButton bout  = playButton.get(cpt);
				cpt ++;
				int val = contenu[i][j];
				if(val != 0){
					int valMod = val%8;

					switch(valMod){
						case 0:
							bout.setBackground(new Color(255,255,25));
							break;
						case 1:
							bout.setBackground(new Color(0,230,0));
							break;
						case 2:
							bout.setBackground(new Color(0,230,191));
							break;
						case 3:
							bout.setBackground(new Color(179,0,0));
							break;
						case 4:
							bout.setBackground(new Color(106,0,128));
							break;
						case 5:
							bout.setBackground(new Color(22,22,168));
							break;
						case 6:
							bout.setBackground(new Color(255,0,255));
							break;
						case 7:
							bout.setBackground(new Color(255,42,0));
							break;
					}
				}
				else{
					bout.setBackground(new Color(255,255,255));
				}

			}
		}
	}
	/**
	 * <p> méthode qui affiche un fantome de la pièce sur le JButton qui
	 * se trouve à la position numCase dans la liste des JButton, permet
	 * de visualiser le déplacement de la pièce</p>
 */
	public void pieceFantome(PiecesPuzzle p, int numCase){
		int[][] piece = p.getPiece();

		int cpt = numCase;

		for(int i = 0; i< p.getHauteur();i++){
			for(int j = 0; j< p.getLargeur();j++){
				if(piece[i][j] != 0 && cpt < playButton.size()){
						JButton pieceFant = playButton.get(cpt);
						pieceFant.setBackground(new Color(50,50,50,125));
				}
				cpt++;
			}
			cpt += plat.getLargeur() - p.getLargeur();
		}
	}
}
