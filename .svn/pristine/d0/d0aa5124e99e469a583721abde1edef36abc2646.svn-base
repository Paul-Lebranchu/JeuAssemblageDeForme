/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece;

/**
 *<h1> class PieceO </h1>
 *<p> La classe Cellule permet de créé les pièces carrées.</p>
 * @author Paul Lebranchu
 */

public class PieceO extends PiecesPuzzle{

	/**
  *<p>  Constructeur de la pièce O: il s'assure que la hauteur de la pièce soit égale
	*à sa largeur et créé la pièce après avoir redefini les dimensions de
	*son tableau </p>
  */
	public PieceO(int x, int y,int min,int max){
		super(x,y,min,max);
		this.largeur = this.hauteur;
		this.piece = new int[this.hauteur][this.largeur];
		this.createPiece();
	}

	/**
	*<p>Foncion remplissant le tableau de pièce avec des 1<p>
	*/
	@Override
	public void createPiece(){
		for(int i=0;i<this.hauteur;i++){
			for(int j=0;j<this.largeur;j++){
				this.piece[i][j] = 1;
			}
		}
	}
}
