/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece;

/**
 *<h1> class PieceI </h1>
 *<p> La classe Cellule permet de créé les pièces en forme de I.</p>
 * @author Paul Lebranchu
 */

public class PieceI extends PiecesPuzzle{

	/**
	*<p>Constructeur de la pièce I: il s'assure que la largeur de la pièce vale 1
	*et créé la pièce<p>
	*/
	public PieceI(int x, int y,int min,int max){
		super(x,y,min,max);
		this.largeur =1;
		this.createPiece();
	}

	/**
	*<p>Foncion remplissant le tableau de pièce avec des 1<p>
	*/
	@Override
	public void createPiece(){
		for(int i =0; i<this.hauteur;i++){
			this.piece[i][0]=1;
		}
	}

}
