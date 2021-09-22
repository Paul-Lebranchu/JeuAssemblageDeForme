/*
*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece;

/**
 *
  *<h1> class PieceZ </h1>
  *<p> Classe qui s'occupe de la génération des pièces</p>
 * @author Paul Lebranchu
 */


 public class PieceFactory{

   public PiecesPuzzle getPiece(String type,int x, int y,int min, int max){

     PiecesPuzzle piece = null;
     //PieceX lorsque l'on utilise la classe dans le package, piece.PieceX pour les endroits  o ù on importe le package
     if("PieceI".equalsIgnoreCase(type) || "piece.PieceI".equalsIgnoreCase(type)) return new PieceI(x,y,min,max);
     else if("PieceL".equalsIgnoreCase(type) || "piece.PieceL".equalsIgnoreCase(type)) return new PieceL(x,y,min,max);
     else if("PieceO".equalsIgnoreCase(type) || "piece.PieceO".equalsIgnoreCase(type)) return new PieceO(x,y,min,max);
     else if("PieceS".equalsIgnoreCase(type) || "piece.PieceS".equalsIgnoreCase(type)) return new PieceS(x,y,min,max);
     else if("PieceT".equalsIgnoreCase(type) || "piece.PieceT".equalsIgnoreCase(type)) return new PieceT(x,y,min,max);
     else if("PieceZ".equalsIgnoreCase(type) || "piece.PieceZ".equalsIgnoreCase(type)) return new PieceZ(x,y,min,max);

     return piece;
   }
 }
