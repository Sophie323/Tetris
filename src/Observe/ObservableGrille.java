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
public interface ObservableGrille {
  public void addObserver(ObserverGrille obs);
  public void removeObserver();
  public void notifyObserver(Grille grille);
  public void notifyObserverSuivant(Piece piece);
  public void notifyObserverHold(Piece piece);
}
