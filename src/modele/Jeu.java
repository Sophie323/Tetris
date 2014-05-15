/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import Observe.ObservableGrille;
import Observe.ObserverGrille;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.pieces.Barre;
import modele.pieces.Piece;
import modele.pieces.*;

/**
 *
 * @author p0909863
 */
public class Jeu implements ObservableGrille{
    
    private Grille grille;
    private Piece pieceCourante;
  
    boolean mep=false;
    private ArrayList<ObserverGrille> listObserver = new ArrayList<ObserverGrille>();
    public Jeu() {
        grille = new Grille(10,20);
        pieceCourante= new T();
    }

    public Grille getGrille() {
        return grille;
    }
    
 
    public void descendrePiece(){
        
        if(!pieceCourante.isBloque())
        {
            grille.effacerPiece(pieceCourante);
            pieceCourante.deplacerPieceBas();
            if(grille.verifierEmplacement(pieceCourante))
            {
                grille.dessinerPiece(pieceCourante);
            }
            else
            {
                pieceCourante.deplacerPieceHaut();
                grille.dessinerPiece(pieceCourante);
                pieceCourante.setBloque(true);
            }
            notifyObserver(grille);
        }
    }
    
    public void deplacerGauche(){
        
        if(!pieceCourante.isBloque())
        {
            grille.effacerPiece(pieceCourante);
            pieceCourante.deplacerPieceGauche();
            if(grille.verifierEmplacement(pieceCourante))
            {
                grille.dessinerPiece(pieceCourante);
            }
            else
            {
                pieceCourante.deplacerPieceDroite();
                grille.dessinerPiece(pieceCourante);
                
            }
            notifyObserver(grille);
        }
    }
    
        public void deplacerDroite(){
          
            if(!pieceCourante.isBloque())
            {
            grille.effacerPiece(pieceCourante);
            pieceCourante.deplacerPieceDroite();
            if(grille.verifierEmplacement(pieceCourante))
            {
                grille.dessinerPiece(pieceCourante);
            }
            else
            {
                pieceCourante.deplacerPieceGauche();
                grille.dessinerPiece(pieceCourante);
                
            }
            notifyObserver(grille);
        }
    }
        public void rotationPiece()
        {
            
            
            if(!pieceCourante.isBloque())
            {
                grille.effacerPiece(pieceCourante);
                pieceCourante.rotationPiece();
                while(!grille.verifierEmplacement(pieceCourante))
                {
                    pieceCourante.rotationPiece();
                }
                grille.dessinerPiece(pieceCourante);
                
                notifyObserver(grille);     
            }
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
