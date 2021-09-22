package piece;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *<h1> class PiecePuzzle </h1>
 *<p> La classe PiecesPuzzle est une classe abstraite qui nous permettra de
 * créé les différentes pièce du jeu d'assemblage.</p>
 * @author Paul Lebranchu
 */



public abstract class PiecesPuzzle implements InterfacePieces{

    /**
    *<p>  type int position sur l'axe x du plateau</p>
    */
    protected int x;
    /**
    *<p>  type int position sur l'axe y du plateau </p>
    */
    protected int y;

    protected int min;

    protected int max;
    /**
    *<p>  type int définit la largeur de la pièce</p>
    */
    protected int largeur;
    /**
    *<p>  type int définit la hauteur de la pièce</p>
    */
    protected int hauteur;

    /**
    *<p>  type int[][] définit la forme de la pièce</p>
    */
    protected int[][] piece;

    /**
    *<p>  type satic int nombre qui permet de définir la couleur de la pièce</p>
    */
    public static int id = 1;

    protected int trueId;



    /**
    *<p>  Constructeur des pièce: il définit la position de la pièce dans le plateau, sa hauteur,
    *sa largeur, sa forme et sa couleur </p>
    */
    public PiecesPuzzle(int x, int y, int min,int max){
		this.x = x;
        this.y = y;
        this.min = min;
        this.max =max;
        this.hauteur = min+(int)(Math.random()*(max-min+1));// math.random génère nombre entre 0.0 et 1.0 -> multiple par max -min et ajoute le min pour obtenir un nombre compris entre 2 et 5 -> http://info.clg.qc.ca/java/elements/generer-nombres-aleatoires
        this.largeur = min+(int)(Math.random()*(max-min+1));

        this.piece = new int[this.hauteur][this.largeur];

        this.trueId = PiecesPuzzle.id++;

    }

    public String toString(){
      String chaine = "Voici le tableau représentant notre "+ this.getClass().getName()+": \n";
      String tab = "";
      for (int i = 0; i < this.hauteur; i++){
        for (int j = 0; j < this.largeur; j++){
          tab += this.piece[i][j];
        }
        tab +="\n";
      }
      String res = chaine + tab;
      return res;
    }

    /**
    *<p>  fonction permettant de faire tourner la pièce</p>
    */
    public final void rotation(){
      int larg = this.hauteur;
      int longu = this.largeur;

      int[][] tmp = new int[longu][larg];

      for(int i = 0; i < larg; i++){
        for(int j = 0; j < longu; j++){
          tmp[j][i] = this.piece[i][longu-1-j];
        }
      }

      this.setPiece(tmp);
      this.setLargeur(larg);
      this.setLongueur(longu);
    }

    /**
    *<p>  fonction permettant de faire bougerla pièce</p>
    */
    public final void deplacement(int x,int y){
      this.setX(x);
      this.setY(y);
    }

    /**
    *<p>  fonction abstraite permettant de donner une forme à la pièce:
    *elle rempli le tableau de la pièce avec des 0 et des 1, elle doit être redéfinis
    *pour chaque forme de la pièce</p>
    */
    abstract void createPiece();

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getHauteur() {
        return this.hauteur;
    }

    public int[][] getPiece() {
        return this.piece;
    }

    public int getMin(){
      return this.min;
    }
    public int getMax(){
      return this.max;
    }
    public void setPiece(int[][] newpiece){
      this.piece = newpiece;
    }
    public void setLargeur(int newLargeur) {
        this.largeur = newLargeur;
    }

    public void setLongueur(int newhauteur) {
        this.hauteur = newhauteur;
    }

    public void setX(int x){
      this.x = x;
    }

    public void setY(int y){
      this.y = y;
    }

    public int getId(){
        return this.trueId;
    }
}
