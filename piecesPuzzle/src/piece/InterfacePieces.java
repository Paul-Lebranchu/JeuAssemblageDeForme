/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece;

/**
 *<h1> interface InterfacePieces </h1>
 *<p> Une interface qui permet de créé différent type de pièces
 *qui devront pouvoir se déplacer et qui pourront effectuer une rotation.</p>
 * @author Paul Lebranchu
 */
public interface InterfacePieces {

  void rotation();
  void deplacement(int x,int y);
}
