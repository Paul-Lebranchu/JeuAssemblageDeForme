package Vue;

//gestion des images
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

import piece.*;
import Modele.*;
import Controlleur.*;

/**
 * <h1> Menu </h1>
 * <p> Classe générant le Menu, les parties et les event
 * @author Paul et Raphaelle
 */

public class Menu extends JPanel implements EcouteurModel{


	//instance du Modele
	protected CreationPlateau plateau;

	//pièce selectionné
	protected PiecesPuzzle piece;

	//les boutons
	protected JButton facile;
	protected JButton medium;
	protected JButton difficile;
	protected JButton personnalise;
	protected JButton retourMenu;
	protected JButton finPartie;
	protected JButton partieEnCours;
	protected JButton control;

	//liste des boutons du Jeu
	ArrayList<JButton> caseJeu;

	//les textes
	protected JLabel txt;
	protected JLabel controlleur;
	protected JLabel ennonce;
	protected JLabel erreur;
	protected JLabel partie;
	protected JLabel score;
	protected JLabel scoreIA;
	protected JTextField custom;
	protected JLabel errorCharg;

	//la fenêtre de jeu
	protected JPanel fen;

	//boolean permettant de savoir si la partie est fini ou non
	protected boolean fin;

	//les différentes images
	protected ImageIcon imageBouton;
	protected ImageIcon imagePanneau;

	//afficheurPlateau
	protected AffPlateau plataff;

	//couleur de fond
	protected Color back = new Color(100, 131, 105);

	//paramètre police
	protected Font welcome = new Font("Arial", Font.ITALIC + Font.BOLD, 20);


	/**
	 * <p> Constructeur: Définis l'ensemble des boutons/ JPanel
	 * Le constructeur prend un JPanel en argument:  le Jpanel où seront affiché
	 * les éléments de cette classe. On effectue un try : si il réussi,la vue
	 * sera doté d'image derrière les JButton/JLabel, sinon, on affiche le jeu
	 * sans image et on ajoute un message préventif lorsque l'on génère le menu.
	 * Ensuite on initialise la position des différents éléments, leur méthode associées et les affiche si
	 * on le désire. On finit en initialisant la fin de partie sur true vu qu'aucune
	 * partie n'a été créé pour le moment.
 	*/
	public Menu(JPanel fen){

		this.fen   = fen;

		//création des images de fond
		try{

			BufferedImage planche = ImageIO.read(new File("lib/image/planche.jpg"));
			this.imageBouton = new ImageIcon(planche);

			BufferedImage panneau = ImageIO.read(new File("lib/image/panneau.jpg"));
			this.imagePanneau = new ImageIcon(panneau);

			//initialisation du titre panneau principal du menu
			this.txt = new JLabel(imagePanneau,JLabel.CENTER);

			//initialisation des boutons de créations de partie
			this.facile = new JButton(imageBouton);
			this.medium = new JButton(imageBouton);
			this.difficile = new JButton(imageBouton);
			this.personnalise = new JButton(imageBouton);

			//initialisation des boutons de navigation
			this.retourMenu = new JButton(imageBouton);
			this.partieEnCours = new JButton(imageBouton);
			this.control = new JButton(imageBouton);

			//bouton de fin de partie
			this.finPartie = new JButton(imageBouton);

			//Affichage des controlles du jeu
			this.controlleur = new JLabel(imagePanneau,JLabel.CENTER);

			//Affichage relatif à la partie: mode de difficulté et score (score s'affiche quand fin de partie)
			this.partie = new JLabel(imageBouton);
			this.score = new JLabel(imageBouton);
			this.scoreIA = new JLabel(imageBouton);


		}catch(IOException e) {
			//affichage de l'erreur en console et sur la vue
			System.out.println("vous jouez à une version du jeu où les images sont absentes");

			this.errorCharg = new JLabel();
			this.errorCharg.setText("<html><center>Vous êtes sur une version sans images du jeu. <br> Il y a eu un problème lors du chargement de vos images <br> Veuillez contactez l'équipe technique pour que nous resolvions ce problème.</center></html>");
			this.errorCharg.setForeground(Color.red);
			this.errorCharg.setBounds(250,00,900,60);
			this.fen.add(this.errorCharg);

			//creation du jeu sans les images
			this.txt = new JLabel();

			this.facile = new JButton();
			this.medium = new JButton();
			this.difficile = new JButton();
			this.personnalise = new JButton();

			this.retourMenu = new JButton();
			this.partieEnCours = new JButton();
			this.control = new JButton();

			this.finPartie = new JButton();

			this.controlleur = new JLabel();

			this.partie = new JLabel();
			this.score = new JLabel();
			this.scoreIA = new JLabel();

		}

		//partie code commun au mode avec/sans image
		//gestion du panneau de bienvenue
		this.txt.setText("<html><br><br><center>Bienvenue dans le menu de selection des niveaux! <br> Veuillez choisir votre difficulté : <br> facile (0), medium (1), difficile (2) ou custom (de 3 à 6)</center></html>");
		this.txt.setIconTextGap( - 900 +150);//900 = valeur brut de la largeur de l'image panneau
		this.txt.setBounds(50,0,900,220);
		this.txt.setFont(welcome);
		this.fen.add(this.txt);

		//gestion du bouton mode facile
		//text +police
		this.facile.setText("facile");
		this.facile.setFont(welcome);
		//place du texte sur l'image
		this.facile.setIconTextGap( - 270 +100);//270 = valeur brut de la largeur de l'image bouton
		//Place du boutton dans le Jpanel
		this.facile.setBounds(111,240,270,111);
		this.facile.setBackground(back);

		//ajout du bouton a la fentre + activation de sa méthode lorsqu'on clique dessus
		this.fen.add(this.facile);
		this.creaFacile();

		//gestion du bouton mode medium
		this.medium.setBounds(111,370,270,111);
		this.medium.setText("medium");
		this.medium.setFont(welcome);
		this.medium.setIconTextGap( - 270 +85);
		this.medium.setBackground(back);

		this.fen.add(this.medium);
		this.creaMedium();

		//gestion du bouton mode difiicile
		this.difficile.setBounds(111,510,270,111);
		this.difficile.setText("difficile");
		this.difficile.setFont(welcome);
		this.difficile.setIconTextGap( - 270 +85);
		this.difficile.setBackground(back);

		this.fen.add(this.difficile);
		this.creaDur();

		//gestion du bouton mode personnalisé
		this.personnalise.setBounds(400,240,270,111);
		this.personnalise.setText("personnalisée");
		this.personnalise.setFont(welcome);
		this.personnalise.setIconTextGap( - 270 +50);
		this.personnalise.setBackground(back);

		this.fen.add(this.personnalise);
		this.creaPerso();

		//partie concenant le bouton pour les parties personnalisées
		this.ennonce = new JLabel("custom");
		this.ennonce.setFont(welcome);
		this.ennonce.setBounds(700,240,200,80);
		this.fen.add(this.ennonce);

		this.custom = new JTextField();
		this.custom.setBounds(700,290,270,20);
		this.fen.add(this.custom);

		//gestion du bouton de retour au menu
		this.retourMenu.setBounds(70,600,270,111);
		this.retourMenu.setText("Retour au menu");
		this.retourMenu.setFont(welcome);
		this.retourMenu.setIconTextGap( - 270 +40);
		this.retourMenu.setBackground(back);

		this.retourAuMenu();

		//gestion du bouton de retour à la partie en cours
		this.partieEnCours.setBounds(400,510,270,111);
		this.partieEnCours.setText("Retour à la partie");
		this.partieEnCours.setFont(welcome);
		this.partieEnCours.setIconTextGap( - 270 +30);
		this.partieEnCours.setBackground(back);

		this.enCours();

		//gestion du bouton de fin de partie
		this.finPartie.setBounds(650,600,270,111);
		this.finPartie.setText("Fin de partie");
		this.finPartie.setFont(welcome);
		this.finPartie.setIconTextGap( - 270 +75);
		this.finPartie.setBackground(back);

		this.finDePartie();

		//gestion du bouton menant au control du jeu
		this.control.setBounds(400,370,270,111);
		this.control.setText("Control");
		this.control.setFont(welcome);
		this.control.setIconTextGap( - 270 +90);
		this.control.setBackground(back);
		this.fen.add(this.control);

		this.commande();

		//gestion du panneau indiquant comment jouer au jeu
		this.controlleur.setText("<html><center><br><br><br>Voici les commandes du jeu! <br> <u>selection piece :</u> clique gauche souris  <br> Lorsqu'une pièce est sélectionnée :  <br> <u> déplacement pièce :</u> clique droit souris <br> <u> rotation piece :</u> barre d'espace </center></html>");
		this.controlleur.setIconTextGap( - 900 +220);
		this.controlleur.setBounds(50,200,900,220);
		this.controlleur.setFont(welcome);

		//panneau affichant le mode dans jeu dans une partie
		this.partie.setBounds(350,20,270,111);
		this.partie.setFont(welcome);

		//panneau affichant les résultat du joueur et de l'IA à la fin d'une partie
		this.score.setBounds(350,600,270,111);
		this.score.setFont(welcome);
		this.score.setIconTextGap( - 270+40);

		this.scoreIA.setBounds(650,600,270,111);
		this.scoreIA.setFont(welcome);
		this.scoreIA.setIconTextGap( - 270+40);

		//initialisation du boolean fin de partie
		this.fin = true;
	}
	/**
		*<p> méthode liant la création d'une partie de niveau facile au JButton facile </p>
	*/
	public void creaFacile(){

		this.facile.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				creaPlat(0,"facile",( - 260+85)); //260 correspond à la largeur de l'image du bouton, nous plaçons la valeur brut pour que le jeu marche même sans l'image
			}
		});

	}

	/**
		*<p> méthode liant la création d'une partie de niveau medium au JButton medium </p>
	*/
	public void creaMedium(){
		this.medium.addMouseListener(new MouseAdapter(){

			@Override
			public void mousePressed(MouseEvent e){
				creaPlat(1,"medium",( - 260 +85));
			}
		});

	}

	/**
		*<p> méthode liant la création d'une partie de niveau difficile au JButton difficle </p>
	*/
	public void creaDur(){
		this.difficile.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				creaPlat(2,"difficile",( - 260+85));
			}
		});

	}

	/**
		*<p> méthode liant la création d'une partie de niveau personnalisé au JButton personnalisé:
		* l'utilisateur doit saisir son niveau de difficulté et on lui renvoie un message d'erreur si
		* la valeur saisie dans le TextArea n'est pas correctes (message affiché sur la Vue)</p>
	*/
	public void creaPerso(){
		this.personnalise.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){

				try{

					int difficult = Integer.parseInt(custom.getText());

					/*si l'on rentre une des difficultés prédéfénis on applique la
					création du plateau comme pour les trois classes au dessus*/
					if(difficult  == 3 ){
						creaPlat(3,"personnaliséé : 3",( - 260+30));
					}
					else if(difficult  == 4 ){
						creaPlat(4,"personnaliséé : 4",( - 260 +30));
					}
					else if(difficult  == 5 ){
						creaPlat(5,"personnaliséé : 5",( - 260 +30));
					}
					else if(difficult  == 6 ){
						creaPlat(6,"personnaliséé : 6",( - 260 +30));
					}
					else{
						/*si on met un int ne correspondant pas à une difficultée
						prédéfini, on ajoute du texte dans le JLabel erreur et on
						l'affiche pour signaler à l'utilisateur qu'il a commis une erreur*/
						erreur = new JLabel("Rentrez un nombre compris entre 3 et 6");
						erreur.setForeground(Color.red);
						erreur.setBounds(700,330,800,20);
						fen.add(erreur);
						fen.repaint();
					}

					fen.repaint();

				}catch (Exception ex) {
					/*si on reçoit une exception (tableau vide, data de type non int), on fait
					comme  quand on a un int qui ne correspond pas à une difficulté prédéfini*/
    			System.err.println(ex);
					erreur = new JLabel("Rentrez un nombre compris entre 3 et 6");
					erreur.setForeground(Color.red);
					erreur.setBounds(700,330,800,20);
					fen.add(erreur);
					fen.repaint();
  			}
			}
		});

	}

	/**
		*<p> méthode faisant en sorte que le bouton retour au menu réaffiche le menu
		* en ajoutant un bouton Retour à la partie sur la partie n'est pas fini</p>
	*/
	public void retourAuMenu(){
		this.retourMenu.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//supprime tout puis réaffiche élément du menu
				fen.removeAll();
				fen.add(txt);
				fen.add(facile);
				fen.add(medium);
				fen.add(difficile);
				fen.add(personnalise);
				fen.add(ennonce);
				fen.add(control);
				fen.add(custom);
				//si la partie est pas fini, ajoute un bouton qui permet d'y retourner
				if(fin == false){
					fen.add(partieEnCours);
				}
				//déselectionne la pièce en cours au cas où la personne recréée une partie directement dans le menu
				piece = null;
				fen.repaint();
			}
		});
	}


	/**
		*<p> méthode qui affiche les control du jeu lorsque l'on appuie sur le bouton control dans le menu</p>
	*/
	public void commande(){
		this.control.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//supprime tout puis réaffiche élément du menu
				fen.removeAll();
				partie.setText("Control");
				partie.setIconTextGap(- 260+85);
				fen.add(partie);
				fen.add(controlleur);
				fen.add(retourMenu);
				fen.repaint();
			}
		});

	}




	/**
		*<p> méthode définissant la fin de la partie (lié au bouton fin de partie sur
		*l'écran de jeu): elle demandera à l'utilisateur de rentrer son pseudo puis sauvegardera
		*et affichera son score ainsi que le score de l'IA sur la fenêtre de jeu</p>
	*/
	public void finDePartie(){
		this.finPartie.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				fin = true;
				//calcul du score +  affichage dans un Jlabel
				int scoreF = plateau.gestionDesScores();
				String scoreIAF = plateau.intelArtif();
				//demande à l'utilisateur de rentrer un pseudo pour les scores
				String pseudo;
				pseudo = JOptionPane.showInputDialog(null, "Rentrez votre pseudo pour rentrer dans l'histoire!");
				//termine la partie et retire le bouton fin de partie
				plateau.finDePartie(pseudo);
				fen.remove(finPartie);
				//ajoute les JLabel avec le score du joueur et le score de l'ia
				score.setText("score : "+scoreF);
				scoreIA.setText("score IA : "+scoreIAF);
				fen.add(score);
				fen.add(scoreIA);
				fen.repaint();
			}
		});
	}


	/**
		*<p> méthode permettant de retourner sur la partie en cours lorsque l'on est retoruné sur le menu
		*sans avoir déclaré avoir fini la partie</p>
	*/
	public void enCours(){
		this.partieEnCours.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				fen.removeAll();
				fen.add(partie);
				fen.add(retourMenu);
				fen.add(finPartie);
				plataff.reaffich();
				fen.repaint();

			}
		});
	}

	/**
		*<p> méthode de création de partie, prend en argument le niveau de dificulté choisis
		*par l'utilisateur, un String qui écrira le niveau de difficulté sur l'écran de jeu
		*et un gap pour centrer le texte sur la plaque de jeu. Cette méthode instancie le modèle (plateau)
		*et l'affiche avec plataff. Elle ajoute ensuite les écouteurs sur le modèle et met en place les fonctions
		*de jeu (selection/déplacament/rotation/fantome piece)</p>
	*/
	public void creaPlat(int niveau,String txt, int gap){
		//boolean permettant de savoir si partie finie remis à false
		fin =false;
		//efface la fenetre
		fen.removeAll();
		//création + affichage du plateau
		plateau  = new CreationPlateau(niveau);
		plataff = new AffPlateau(plateau.getPlateau(), fen , niveau);
		//réglage des zones de texte et bouton de gestion de menu
		partie.setText(txt);
		partie.setIconTextGap(gap);
		fen.add(partie);
		fen.add(retourMenu);
		fen.add(finPartie);
		//raffraichissement de la fenêtre
		fen.repaint();
		//ajout des boutons de jeu dans la liste des boutons jouable
		caseJeu = plataff.getListeBouton();
		plateau.ajoutEcouteur(this);
		//ajout des événèment aux différentes cases:
		this.selectPiece();
		this.deplacement();
		this.rotation();
		this.fantomePiece();

	}


	//fonction lié au action de jeu

	/**
		*<p>On ajoute des évenment sur tout les boutons du plateau: si la case où
		* on a cliqué contient une pièce, on récupère la pièce et la stocke pour
		* pouvoir effectué des actions dessus. La selection de la pièce s'effectue
		*avec un clique gauche </p>
	*/
	public void selectPiece(){
		for(JButton bout: caseJeu){
			bout.addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent e){
					//récupère le numéro de la case où on a cliqué
					int caseClique  = caseJeu.indexOf(e.getSource());
					//i  et j sont la place de la classe dans la grille de jeu
					int i = caseClique/plateau.getPlateau().getLargeur();
					int j = caseClique%plateau.getPlateau().getLargeur();

					if(plateau.getPlateau().getTab2()[i][j]!=0 && e.getButton() == 1){//clique gauche
						int numPiece = plateau.getPlateau().getTab2()[i][j];
						System.out.println(numPiece);
						piece = plateau.getListePiece().get(numPiece-1);
					}
				}
			});
		}
	}
	/**
		*<p>Lorsqu'une pièce est sectionné, on peut la faire tourner en
		*appuyant sur la barre d'espace, on effectue en simultanée un test
		*(la méthode roation du plateau renvoi un boolean) et si la rotation
		*à echouer, on affiche un message d'erreur via showMessageDialog </p>
	*/
	public void rotation(){
		for(JButton bout: caseJeu){
			bout.addKeyListener(new KeyListener(){

				@Override
        public void keyTyped(KeyEvent e) {}

				@Override
				public void keyPressed(KeyEvent e){
					if(piece != null && e.getKeyCode() == 32){//barre d'espace

						//effectue la rotation en lançant plateau.rotation(piece) + si la fonction return false, envoie un message d'erreur
						if(plateau.rotation(piece)==false){
							JOptionPane.showMessageDialog(null, "votre pièce ne peut pas effectuer de rotation(collision pièce ou sortie du plateau)");
						}
						modelMiseAJour(this);
					}
				}

				@Override
        public void keyReleased(KeyEvent e){}
			});
		}
	}

	/**
		*<p>On ajoute un événènement à tout les boutons du plateau de jeu: si une pièce
		*est selectionné, on peut la déplacer sur la case sélectionner en effectuant un clique
		*droit sur la case, on teste ensuite si le déplacement est possible. Soit on affiche un message d'erreur,
		*soit on déplace la pièce</p>
	*/
	public void deplacement(){
		for(JButton bout: caseJeu){
			bout.addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent e){
					if(piece != null && e.getButton() == 3){//clique gauche
						int caseClique  = caseJeu.indexOf(e.getSource());

						int i = caseClique/plateau.getPlateau().getLargeur();
						int j = caseClique%plateau.getPlateau().getLargeur();

						//déplacement de la pièce  + gestion erreur
						if(plateau.deplacer(piece,i,j)==false){
							JOptionPane.showMessageDialog(null, "votre pièce ne peut pas se déplcaer ici,(collision pièce ou sortie du plateau)!");

						}
						else{
							//reinitialisation de la pièce à null après un déplacement réussi
							piece = null;
						}
						modelMiseAJour(this);
					}
				}
			});
		}
	}
	/**
		*<p>On ajoute un événènement à tout les boutons du plateau de jeu: si une pièce
		*est selectionné,lorsque l'on passe la souris sur une case de plateau, on voit le fantome
		*de la pièce apparaitre sur le Plateau: ce fantome représente la nouvelle place de la pièce
		*si l'on effectue un déplacement, cette méthode permet de faciliter l'experience du joueur/p>
	*/
	public void fantomePiece(){
		for(JButton bout: caseJeu){
			bout.addMouseListener(new MouseAdapter(){
				//affiche le fantome de la pièce lorsque l'on entre sur la case pour voir où on va poser la pièce
				@Override
				public void mouseEntered(MouseEvent e){
					if(piece != null){
						//met à jour le modèle pour effacer les traces de la pièce fantome
						plataff.maj();
						//selectionne la case survolé et aplkique la méthode pieceFantome de plataff
						int caseSurvol = caseJeu.indexOf(e.getSource());
						plataff.pieceFantome(piece,caseSurvol);

						fen.repaint();
					}
				}
			});
		}
	}

	@Override
  public void modelMiseAJour(Object source){
		caseJeu = plataff.getListeBouton();

		//mise à jour de la vue
		plataff.maj();
		fen.repaint();
  }
}
