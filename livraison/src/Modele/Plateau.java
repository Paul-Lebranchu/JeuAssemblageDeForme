package Modele;

/**
 * <h1> Plateau </h1>
 * <p> Classe s'occuppant du Plateau de jeu.
 * @author Marguerite
 */
public class Plateau {
	private int hauteur;
	private int largeur;
	private int[][] tab2D;

	/**
      *Constructeur :  créé un tableau rempli de 0 dont on aura paramètré la
			*hauteur et la largeur
   */
	public Plateau( int hauteur, int largeur) {
		   this.hauteur=hauteur;
    	   this.largeur=largeur;
    	   this.tab2D = new int[hauteur][largeur];
    	   for (int i = 0;i < hauteur; i++) {
	    	   for (int j = 0;j < largeur;j++) {
	    		   this.tab2D[i][j] = 0;
	    	   }
    	   }
	}

	public int getHauteur(){
		return this.hauteur;
	}
	public int getLargeur(){
		return this.largeur;
	}
	public int[][] getTab2(){
		return this.tab2D;
	}
	public void setTab2(int[][] tab2D){
		this.tab2D = tab2D;
	}

	@Override
	public String toString() {
		String affichage= "";
		for (int x=0;x<this.hauteur;x++) {
			affichage+="\n";//retour a la ligne
			for (int y=0;y<this.largeur;y++) {
				affichage+="["+tab2D[x][y]+"]";
				affichage+=" ";
			}
		}
		return affichage;
	}

}
