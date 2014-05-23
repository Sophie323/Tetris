/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Observe;


import modele.Grille;
import modele.pieces.Piece;

/**
 *
 * @author Sophie
 */
public interface ObserverGrille {
  public void update(Grille grille);
  public void update(Piece piece);
  
}
