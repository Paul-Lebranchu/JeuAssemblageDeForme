/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece;

/**
 *<h1> class PieceL </h1>
 *<p> La classe Cellule permet de créé les pièces en forme de L.</p>
 * @author Paul Lebranchu
 */

public class PieceL extends PiecesPuzzle{

	/**
  *<p>  Constructeur de la pièce L: appelle la fonction qui créé la pièce </p>
  */
	public PieceL(int x, int y,int min,int max){
		super(x,y,min,max);
		this.createPiece();
	}

	/**
	*<p>Foncion remplissant la première ligne et la dernière colonne avec des 1, les autres cases restent à 0<p>
	*/
	@Override
	public void createPiece(){
		for(int i =0; i<this.hauteur;i++){
			for(int j = 0; j<this.largeur;j++){
				if(i==0 || j==this.largeur-1){
					this.piece[i][j]=1;
				}
			}
		}
	}

}
