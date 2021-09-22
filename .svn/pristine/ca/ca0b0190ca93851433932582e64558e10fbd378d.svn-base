package Modele;

import java.util.Scanner;
import piece.*;
import java.util.ArrayList;

/**
 * <h1> Modele.Main </h1>
 * <p> Classe générant le jeu en mode console.
 * @author Paul
 */
public class Main {
	/**
      *méthode executant le programme en version console: D'abord on demande à l'utilisateur
			*de choisir une difficulté de jeu, puis on lui génère un plateau et lui demande
			*les actons qu'il souhaite effectué, une fois qu'il a fini de jouer, on lui demande
			*de rentrer son pseudo puis on lui demande s'il veut rejouer ou non
   */
	public static void exe() {

		int rejouer = 1;

		while(rejouer != 0){

			System.out.println("Voici les différentes difficultés de notre jeu:");
			System.out.println("");
			System.out.println(".facile : nombre de pièce = 4 , taille minimale des pièce :2x1; taille maximale: 4*4");
			System.out.println(".moyen : nombre de pièce = 6 , taille minimale des pièce :3x1; taille maximale: 5*5");
			System.out.println(".difficile : nombre de pièce = 8 , taille minimale des pièce :4x1; taille maximale: 6*6");
			System.out.println(".personnalisé : nombre de pièce = à vous de choisir (doit être superieur à 3), taille minimale des pièce :2x1; taille maximale: 6*6 [nombre de piece max = 6] ");

			Scanner scan = new Scanner(System.in);
			System.out.println("");
			System.out.println("Choissisez votre niveau de difficulté: 0 = facile ; 1 = moyen ; 2 = difficile ; 3-6 = personnalisée ");

			int difficult = scan.nextInt();

			while(difficult < 0 || difficult  >6){
				if(difficult < 0){
					System.out.println("");
					System.out.println("Veuillez rentrer un nombre superieur à 0  s'il vous plait.");
				}
				if(difficult > 9){
					System.out.println("");
					System.out.println("Pour des soucis de lisibilité, veuillez rentrer un nombre inferieur à 9 s'il vous plait.");
				}
				difficult = scan.nextInt();
			}

			CreationPlateau crea = new CreationPlateau(difficult);
			System.out.println(crea.getPlateau());

			System.out.println("");
			System.out.println("Vous pouvez maintenant commencer à jouer:");

			int coup = 0;

			while(coup != -1){
				System.out.println("");
				System.out.println("Taper 0 pour déplacer une pièce, 1 pour faire tourner une pièce, -1 pour terminer la partie");

				coup = scan.nextInt();

				if(coup == 0){
					System.out.println("");
					System.out.println("Selectionner la pièce que vous souhaitez déplacer (entrer son numéro)");
					int num = -1;

					while(num <= 0 || num > crea.getListePiece().size()){

						num = scan.nextInt();

						if(num <= 0 ||num > crea.getListePiece().size() ){
							System.out.println("");
							System.out.println("choissisez un numéro de pièce valide s'il vous plait");
						}
						else{
							PiecesPuzzle p = crea.getListePiece().get(num-1);
							System.out.println("");
							System.out.println("Rentrez la coordonées x (ligne) du bord superieur gauche du rectangle représentant le nouvel emplacement de votre pièce");
							int x = scan.nextInt();
							System.out.println("");
							System.out.println("Rentrez la coordonées y (colonne) du bord superieur gauche du rectangle représentant le nouvel emplacement de votre pièce");
							int y = scan.nextInt();

							crea.deplacer(p,x,y);
						}
					}
				}

				else if(coup == 1){
					System.out.println("");
					System.out.println("Selectionner la pièce que vous souhaite faire tourner (entrer son numéro)");
					int num = -1;

					while(num <= 0 || num > crea.getListePiece().size()){

						num = scan.nextInt();

						if(num <= 0 ||num > crea.getListePiece().size() ){
							System.out.println("");
							System.out.println("choissisez un numéro de pièce valide s'il vous plait");
						}
						else{
							PiecesPuzzle p = crea.getListePiece().get(num-1);
							crea.rotation(p);
						}
					}
				}

				else if(coup == -1){
					System.out.println("");
					System.out.println("Entrez votre pseudo pour rentrer dans l'histoire!");
					Scanner joueur = new Scanner(System.in);
					String pseudo = joueur.nextLine();
					crea.finDePartie(pseudo);
				}

				else{
					System.out.println("");
					System.out.println("attention, la commande que vous venez de saisir est invalide!");
				}

				System.out.println(crea.getPlateau());
			}

			Scanner replay = new Scanner(System.in);
			System.out.println("");
			System.out.println("voulez-vous rejouer? 0 = non; autre symbole pour continuer");
			rejouer = replay.nextInt();
		}
	}
}
