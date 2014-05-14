/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import Observe.ObservableGrille;
import Observe.ObserverGrille;
import java.util.ArrayList;
import modele.pieces.Barre;
import modele.pieces.Piece;

/**
 *
 * @author p0909863
 */
public class Jeu implements ObservableGrille{
    
    private Grille grille;
    private Piece pieceCourante;
    private ArrayList<ObserverGrille> listObserver = new ArrayList<ObserverGrille>();
    public Jeu() {
        grille = new Grille(10,20);
        pieceCourante= new Barre();
    }

    public Grille getGrille() {
        return grille;
    }
    
    
    public void descendrePiece(){
        pieceCourante.deplacerPieceVertical();
        grille.dessinerPiece(pieceCourante);
        notifyObserver(grille);
    }
    
    public void addObserver(ObserverGrille obs) {
    this.listObserver.add(obs);
  }
    
  public void notifyObserver(Grille grille) {
    for(ObserverGrille obs : listObserver)
      obs.update(grille);
  }
  public void removeObserver() {
    listObserver = new ArrayList<ObserverGrille>();
  }  
    
}
