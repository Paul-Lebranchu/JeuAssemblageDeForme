package piece;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Main {


    public static void main (String[] args){

    PieceFactory factor = new PieceFactory();

  	PiecesPuzzle p2 = factor.getPiece("PieceL",0,1,3,6) ;
  	PiecesPuzzle p3 = factor.getPiece("PieceO",1,1,2,4) ;
  	PiecesPuzzle p4 = factor.getPiece("PieceZ",2,1,4,6) ;
  	PiecesPuzzle p5 = factor.getPiece("PieceS",2,2,5,7) ;
  	PiecesPuzzle p6 = factor.getPiece("PieceL",2,3,3,6) ;
  	PiecesPuzzle p7 = factor.getPiece("PieceT",3,3,4,5) ;

    System.out.println(p2);
    System.out.println("");
    p2.rotation();
    System.out.println("");
    System.out.println(p2);
    p2.rotation();
    System.out.println("");
    System.out.println(p2);
    p2.rotation();
    System.out.println("");
    System.out.println(p2);
    p2.rotation();
    System.out.println("");
    System.out.println(p2);
    p2.deplacement(4,4);

    System.out.println(p3);
    System.out.println("");
    p3.deplacement(4,4);

    System.out.println(p4);
    System.out.println("");
    p4.rotation();
    System.out.println("");
    System.out.println(p4);
    p4.rotation();
    System.out.println("");
    System.out.println(p4);
    p4.rotation();
    System.out.println("");
    System.out.println(p4);
    p4.rotation();
    System.out.println("");
    System.out.println(p4);
    p4.deplacement(4,4);

    System.out.println(p5);
    System.out.println("");
    p5.rotation();
    System.out.println("");
    System.out.println(p5);
    p5.rotation();
    System.out.println("");
    System.out.println(p5);
    p5.rotation();
    System.out.println("");
    System.out.println(p5);
    p5.rotation();
    System.out.println("");
    System.out.println(p5);
    p5.deplacement(4,4);

    System.out.println(p6);
    System.out.println("");
    p6.rotation();
    System.out.println("");
    System.out.println(p6);
    p6.rotation();
    System.out.println("");
    System.out.println(p6);
    p6.rotation();
    System.out.println("");
    System.out.println(p6);
    p6.rotation();
    System.out.println("");
    System.out.println(p6);
    p6.deplacement(4,4);

    System.out.println(p7);
    p7.rotation();
    System.out.println("");
    System.out.println(p7);
    p7.rotation();
    System.out.println("");
    System.out.println(p7);
    p7.rotation();
    System.out.println("");
    System.out.println(p7);
    p7.rotation();
    System.out.println("");
    System.out.println(p7);
    p7.deplacement(4,4);


   	}

}
