/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece;

/**
	*<h1> class PieceS </h1>
	*<p> La classe Cellule permet de créé les pièces de forme s.</p>
 * @author Paul Lebranchu
 */
public class PieceS extends PiecesPuzzle{

	/**
  *<p>  Constructeur de la pièce S: il s'assure que la hauteur de la pièce vale 2 et que sa largeur
	*soit un nombre impair et créé la pièce après avoir redefini les dimensions de
	*son tableau </p>
  */
	public PieceS(int x, int y,int min,int max){
		super(x,y,min,max);
		this.hauteur = 2;
		while(this.largeur%2 !=1){
			this.largeur = min+(int)(Math.random()*(max-min+1));
		}
		this.piece = new int[this.hauteur][this.largeur];
		this.createPiece();
	}

	/**
	*<p>Foncion remplissant la première moitié de la première ligne et la seconde
	*moitié de la deuxième ligne avec des 1, les autres cases sont à zeros<p>
	*/
	@Override
	public void createPiece(){
		for(int i =0; i<this.hauteur;i++){
			for(int j = 0; j<this.largeur;j++){
				if((i==0 && j<=(this.largeur-1)/2)||(i==1 && j>=(this.largeur-1)/2)){
					this.piece[i][j]=1;
				}
			}
		}
	}

}
