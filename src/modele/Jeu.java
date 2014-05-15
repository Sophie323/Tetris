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
public class Jeu implements ObservableGrille,Runnable{
    
    private Grille grille;
    private Piece pieceCourante;
    private Piece[] listePieces;
  
    boolean mep=false;
    private ArrayList<ObserverGrille> listObserver = new ArrayList<ObserverGrille>();
    
    public Jeu() {
        grille = new Grille(10,20);
        pieceCourante= new L();
        listePieces = new Piece[7];
        listePieces[0] = new Barre();
        listePieces[1] = new Carre();
        listePieces[2] = new J();
        listePieces[3] = new L();
        listePieces[4] = new S();
        listePieces[5] = new T();
        listePieces[6] = new Z();
    }
    
    public Grille getGrille() {
        return grille;
    }
    
 
       public  void run(){
        while(true)
        {
            try {
                maj("B");
                notifyObserver(grille);
                Thread.currentThread().sleep(500);
                if(mep)
                {
                    synchronized(this)
                            {
                    wait();
                            }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public  void play()
    {
        mep=false;
        synchronized(this)
        {
            notify();
            
        }
    }
    public synchronized void maj(String action)
    {
        if(!mep)
        {
      switch(action){
          case "B":
              descendrePiece();
              break;
          case "G":
                  deplacerGauche();
                  break;
              
          case "D":
                  deplacerDroite();
                  break;
          case "H":
                   rotationPiece();
              break;
      }
        }
    }
    public void mettreEnPause()
    {
        mep=true;
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
                grille.effacerLigne();
                genererPiece();         
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
                grille.effacerLigne();              
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
                grille.effacerLigne();
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
