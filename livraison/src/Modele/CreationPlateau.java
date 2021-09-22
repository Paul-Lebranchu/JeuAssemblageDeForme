/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.util.ArrayList;
import java.util.HashMap;
import piece.*;
import java.lang.reflect.Constructor;
import java.io.*;

import Controlleur.*;
/**
 * <h1> CréationPlateau </h1>
 * <p> Classe s'occuppant de la création du Plateau de jeu et des diverses action de jeu.
 * @author Paul et Raphaelle
 */


public class CreationPlateau extends AbstractModelEcoutable{
    //liste contenant les pièces
    protected ArrayList<PiecesPuzzle> pieces;
    //nombre de piece
    protected int nbPiece;
    //difficulté du jeu
    protected int difficult;
    //plateau de jeu
    protected Plateau plat;
    //taille minimum des pièces
    protected int min;
    //taille max des pièces
    protected int max;

   /**
      *Constructeur :  initialise la liste des écouteurs, la liste des pièces,
      * créé un Plateau de jeu en fonction de la difficulté mise en paramètre,
      *génère les pièces du jeu et remet l'identifiant des pièces à 1 pour les partie
      *suivantes
   */
   public CreationPlateau(int difficult){

      this.ecouteurs = new ArrayList<>();//ajout test Event
      this.pieces = new ArrayList<PiecesPuzzle>();
      this.difficult = difficult;

        //la difficulté choisis par le joueur définit le nombre de pièce et la taille des pièces
        //dans le mode personnalisé, les pièces peuvent faire toutes les tailles possibles
        switch(this.difficult){
          case 0: this.nbPiece = 4;
                  this.min = 2;
                  this.max = 4;
                  this.plat = new Plateau(8,8);
            break;
          case 1: this.nbPiece = 6;
                  this.min = 3;
                  this.max = 5;
                  this.plat = new Plateau(10,15);
            break;
          case 2: this.nbPiece = 8;
                  this.min = 4;
                  this.max = 6;
                  this.plat = new Plateau(12,24);
            break;
          default: this.nbPiece = this.difficult;
                   this.min = 3;
                   this.max = this.difficult;
                   this.plat = new Plateau(2*this.nbPiece,3*this.nbPiece);
        }

        this.generePiece();

        //réinitiilalisation de la var static qui donne le numero de pièce
        PiecesPuzzle  p = pieces.get(0);
        p.id = 1;
    }


  /**
    *<p> méthode générant les pièces du plateau en utilisant PieceFactory pour
    *générer des pièces plus facilement et les place dans le tableau après leur création
    *</p>
  */
  public void generePiece(){
    try{
      //tableau contenant les différente classe de piece existante
      Class[]tabClass = {PieceO.class,PieceI.class,PieceL.class,PieceS.class,PieceZ.class,PieceT.class};

      //création d'une instance de PieceFactory et d'une instance de type PiecePuzzle pour créé les pièces du jeu
      PieceFactory factory = new PieceFactory();

      //on créé des pièces en choissant leur type avec un numéro tiré au hazard entre 1 et 6
      //chacun numéro correspond à un type de pièce
      //on fait ça pour chaque pièce du puzzle et on ajoute la pièce à la liste
      int x=0;
      int y=0;
      int random;

      for(int i=0;i<this.nbPiece;i++){

        random = (int)(Math.random()*tabClass.length);
        //création de la pièce et ajout de la pièce à  la liste des pièce
        Class c = tabClass[random];
        PiecesPuzzle p = factory.getPiece(c.getCanonicalName(),x,y,min,max);

        //tant que la pièce rentrera en collision avec des pièces déjà existante, on change ses valeurs de positions initial
        this.pieces.add(p);

        System.out.println(p.getClass().getName());
        while(this.collisionPiece(p)){
          if(x < this.plat.getHauteur()- p.getHauteur()){
            x  = p.getX()+1;
          }
          else{
            x = 0;
            if(y < this.plat.getLargeur()- p.getLargeur()){
              y = p.getY()+1;
            }
            else{
              y = 0;
            }
          }
          p.deplacement(x,y);
        }

        //une fois que la pièce à une position valide, on l'écrit
        this.ecrirePiece(p);
        System.out.println(this.plat);
      }
    }catch (Exception e) {
      System.err.println(e);
    }
  }
    /**
    *<p> méthode qui inscrit les pièces dans le tableau en marquant leur position par un
    *entier correspondant à leur identifiant
    *</p>
    */
    public int[][] ecrirePiece(PiecesPuzzle p){
      int[][] res  = this.plat.getTab2();
      for (int j = 0; j < p.getHauteur();j++){
        for (int k = 0; k <  p.getLargeur() ;k++){
          if(p.getPiece()[j][k]==1){
            res[j+p.getX()][k+p.getY()] = p.getPiece()[j][k]*(p.getId());
          }
        }
      }
      return res;
    }

    /**
    *<p> méthode qui efface les pièces du plateau, elle est utilisée pour
    *marquer les déplacement ou les rotation
    *</p>
    */
    public int[][] effacerPiece(PiecesPuzzle p){
      int[][] res  = this.plat.getTab2();
      for (int j = 0; j < p.getHauteur();j++){
        for (int k = 0; k <  p.getLargeur() ;k++){
          if(p.getPiece()[j][k]==1){
            res[j+p.getX()][k+p.getY()] = 0 ;
          }
        }
      }
      return res;
    }

    /**
    *<p> méthode qui déplace une pièce: dans un premier temps, elle efface la pièce
    *puis elle vérifie si le déplacement est valide: si le mouvement est valide,
    *on effectue le déplacement, sinon on remet la pièce à son emplacement d'origine
    *et explique l'erreur dans la console. Cette méthode renvoie un boolean
    *qui indique si le déplacement peut avoir lieu.
    *</p>
    */
    public boolean deplacer(PiecesPuzzle p, int x, int y){

      //test vérifiant que la pièce sera dans le tableau après le déplacement
      if(x<=this.plat.getHauteur()-p.getHauteur() && y<=this.plat.getLargeur()-p.getLargeur()){

        //valeur de récupération pour annuler le déplacement en cas de collision
        int oldX= p.getX();
        int oldY= p.getY();

        this.effacerPiece(p);
        p.deplacement(x,y);

        //test des collisions :pas très efficace pour le moment
        if(this.collisionPiece(p)){
          System.out.println("déplacement impossible: vous êtes entré en collision avec une autre pièce, soyons prudent svp");
          p.deplacement(oldX,oldY);
          this.ecrirePiece(p);
          //mise à jour du modèle
          this.mouvPiece();
          return false;
        }

        else{
          this.ecrirePiece(p);
          this.mouvPiece();
          return true;
        }

      }else{
        System.out.println("déplacement invalide : vous tenté de mettre la pièce hors du plateau");
        this.mouvPiece();
        return false;
      }

    }


    /**
    *<p> méthode qui effectue une rotation de la pièce: elle fonctionne comme
    *le déplacement. Quand la rotation ne peut pas avoir lieu, la pièce effectue trois rotations
    *pour récupérer sa place d'origine.Cette méthode renvoie un boolean
    *qui indique si la rotation peut avoir lieu.
    *</p>
    */
    public boolean rotation(PiecesPuzzle p){

      this.effacerPiece(p);
      p.rotation();
      //test est dans Plateau
      if(p.getX()<=this.plat.getHauteur()-p.getHauteur() && p.getY()<=this.plat.getLargeur()-p.getLargeur()){

        //tant que la rotation échoue, on la retente en la déclant vers le centre de la grille
        if(collisionPiece(p)){
          p.rotation();
          p.rotation();
          p.rotation();
          System.out.println("rotation impossible dans ce contexte : collision avec une autre pièce");
          this.ecrirePiece(p);
          this.mouvPiece();
          return false;
        }
        else{
          System.out.println("rotation effectuée");
          this.ecrirePiece(p);
          this.mouvPiece();
          return true;
        }
      }else{
        p.rotation();
        p.rotation();
        p.rotation();
        System.out.println("rotation impossible dans ce contexte : vous sortez du plateau de jeu");
        this.ecrirePiece(p);
        this.mouvPiece();
        return false;
      }
    }


    //collision des pièce, auteur : raphaelle
    /**<p>cette méthode nous permet de parcourir le plateau en verifiant si oui ou non la pièce que nous voulons y placer peux y etre placé.
  	 * ainsi, on cherche sur la largeur et la hauteur de la pièce si la pièce et le plateau contiennent ou non un 1.
  	 * Si tout deux contiennent un 1 (ou une valeur différente de 0 pour le tableau) alors on retourne vrai sinon la pièce peut se poser.
    *</p>
    */
  	public Boolean collisionPiece(PiecesPuzzle piece){
  		//il faut parcourir le plateau là ou l'on veut poser la pièce donc de x, y a getlargeur gethauteur
  		int[][] plateau = this.plat.getTab2();
  		int[][] laPiece = piece.getPiece();

  		for(int i= 0; i< piece.getHauteur(); i++){
  			for(int j= 0; j < piece.getLargeur(); j++){
  				if(plateau[i + piece.getX()][j + piece.getY()] !=0 && laPiece[i][j] != 0){
  					return true;
  				}
  			}
  		}

  		return false;
  	}

    //accesseurs
    public Plateau getPlateau(){
      return this.plat;
    }

    public ArrayList<PiecesPuzzle> getListePiece(){
      return this.pieces;
    }

    /**
      *<p> méthode qui calcule le score obtenu par le joueur à la fin de la partie.
      *cette méthode calcule l'air du rectangle minimal entourant la forme créé
      *par l'utilisateur. pour faire ça, on récupère la première et la dernière
      *ligne/colonne contenant un chiffre et on valucle l'air trouvé puis on fait
      *l'aire du plateau - l'aire de la forme créée et on renvoi le score sous la forme
      *d'un int
      *</p>
    */
    public int gestionDesScores(){

       ArrayList<Integer> liste = new ArrayList<Integer>();

       int[][] plateauCopie = this.plat.getTab2();
       int hauteurMin =10000000;
       int hauteurMax =0;
       int largeurMin =10000000;
       int largeurMax = 0;
       int airePlateau = this.plat.getLargeur()*this.plat.getHauteur();
       int result;

       //parcours des lignes du plateau pour trouver largeur max et min
       //max
       for (int[] ligne : plateauCopie){
         for (int i=0 ; i<ligne.length ; i++){
           if (ligne[i] != 0 && i >largeurMax){
             largeurMax = i;
           }
         }
       }
       //min
       for (int[] ligne : plateauCopie){
         for (int i= ligne.length-1 ; i >=0 ; i--){
           if (ligne[i] != 0 && i <largeurMin){
             largeurMin = i;
           }
         }
       }

       //parcour des colonnes du plateau pour trouver la hauteur max et min
       //max
       for(int i=0; i< this.plat.getHauteur(); i++){
         for(int j=0; j< this.plat.getLargeur(); j++){
           if(plateauCopie[i][j] != 0 && i>hauteurMax){
             hauteurMax = i;
           }
         }
       }
       //min
       for(int i=this.plat.getHauteur()-1; i>=0; i--){
         for(int j=this.plat.getLargeur()-1; j >= 0; j--){
           if(plateauCopie[i][j] != 0 && i<hauteurMin){
             hauteurMin = i;
           }
         }
       }

       liste.add(largeurMax);
       liste.add(largeurMin);

       liste.add(hauteurMax);
       liste.add(hauteurMin);

       //le résultat correspond à l'aire du plateau à la quelle on aura retiré l'aire de la forme assemblé par le joueur
       result = airePlateau -(liste.get(0)-liste.get(1))*(liste.get(2)-liste.get(3));

       return result;
     }

     /**
      *<p> méthode qui sauvegarde le score obtenu par le joueur dans un fichier texte
      *en prenant en paramètre le message à enregistré
      *</p>
    */
     public void sauvegardeRes(String res){
      try{
        //selection du fichier, 2nd paramètre = true pour qu'on efface pas ce qui a déjà été écrit dans le fichier
        FileOutputStream fichierScore = new FileOutputStream("score.txt",true);
        fichierScore.write(res.getBytes());
      }
      catch(Exception e){
        System.err.println(e);
      }
     }

    /**
      *<p> méthode qui gère la fin de partie: dans un premier temps, on calul le score
      *et on l'affiche en console. On sauvegarde ensuite le score obtenu par le joueur
      *et affiche le score de l'IA qui a joué contre lui. Cette méthode prend le
      *pseudo du joueur en paramètre
      *</p>
    */
     public void finDePartie(String pseudo){
       //calcul du score
       int res  = this.gestionDesScores();
       System.out.println("La partie est terminé! Votre scores est de : "+this.gestionDesScores());
       //sauvegarde score fichier txt
       String resText = (pseudo + " difficulté : " + this.difficult + " score : " + res + " , score de l'IA affonté : " + this.intelArtif() );
       sauvegardeRes(resText+"\n");
       System.out.println("Le score de l'IA à laquelle vous étiez opposé est de : "+this.intelArtif());

     }

     @Override
	   public void mouvPiece(){
		     for(EcouteurModel ecout : this.ecouteurs){
			      ecout.modelMiseAJour(this);
		}
	}

 /** *<h1> méthode qui calcule le score de l'IA</h1>
 *<p>La méthode intelArtif est construite sur une boucle for, pour parcourir la liste des pièces que l'on veut placer.</p>
 * <p>3 boucle if pour tout les 3 pièces, ces dernières se placent soit à gauche soit en bas soit en diagonal de la pièce precedement placée.</p>
 * <p>1 boucle while pour chaue boucle if qui rèpète un décalage sur la gauche, bas ou diagonal en fonction de la première boucle if puis après
 * 20 itération en fonction de la suivante boucle if et ainsi de suite, si on dépasse 60 itération, la fonction affiche une erreur de boucle infini.</p>
 * /*/
	public String intelArtif(){

		this.plat = new Plateau( this.plat.getHauteur(), this.plat.getLargeur());
		System.out.println(this.plat);
		int paireImpaire = 0;

		for(int i=0;i<this.pieces.size();i++){
			PiecesPuzzle p = pieces.get(i);
			int x=0;
			int y=0;
			int parcourt=0;
			p.deplacement(x,y);
			if(paireImpaire%3 == 0){
				paireImpaire =1;
				while(this.collisionPiece(p)){
					parcourt+=1;
					if(parcourt <20){
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX();
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY()+1;
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else if(parcourt <40){
						if(parcourt==20){
							x=0;
							y=0;
						}
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX()+1;
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY()+1;
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else if(parcourt <60){
						if(parcourt==40){
							x=0;
							y=0;
						}
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX()+1;
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY();
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else{
						return "erreur, boucle infini";
					}
				}parcourt =0;
			}else if(paireImpaire%3 == 1){
				paireImpaire =2;
				while(this.collisionPiece(p)){
					parcourt+=1;
					if(parcourt <20){
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX()+1;
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY();
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else if(parcourt <40){
						if(parcourt==20){
							x=0;
							y=0;
						}
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX();
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY()+1;
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else if(parcourt <60){
						if(parcourt==40){
							x=0;
							y=0;
						}
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX()+1;
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY()+1;
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else{
						return "erreur, boucle infini";
					}
				}parcourt=0;
			}else{
				paireImpaire = 0;
				while(this.collisionPiece(p)){
					parcourt+=1;
					if(parcourt <20){
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX()+1;
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY()+1;
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else if(parcourt <40){
						if(parcourt==20){
							x=0;
							y=0;
						}
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX()+1;
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY();
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else if(parcourt <60){
						if(parcourt==40){
							x=0;
							y=0;
						}
						if(x < this.plat.getHauteur() - p.getHauteur() - 1){
							x = p.getX();
						}else{
							x = 0;
						}if(y < this.plat.getLargeur() - p.getLargeur() - 1){
							y = p.getY()+1;
						}else{
							y = 0;
						}
						p.deplacement(x,y);
					}else{
						return "erreur, boucle infini";
					}
				}parcourt =0;
			}
			ecrirePiece(p);
		}
		System.out.println(this.plat);
		return String.valueOf(gestionDesScores());
	}
}
