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
import modele.pieces.*;
import modele.pieces.Barre;
import modele.pieces.Piece;

/**
 *
 * @author p0909863
 */
public class Jeu implements ObservableGrille, Runnable {

    private Grille grille;
    private Piece pieceCourante;
    private Piece pieceSuivante;
    private Piece pieceHold;
    private Piece[] listePieces;
    private int score;
    private int niveau;
    private int pause;
    boolean mep = false;
    private ArrayList<ObserverGrille> listObserver = new ArrayList<ObserverGrille>();

    public Jeu() {
        grille = new Grille(10, 20);
        listePieces = new Piece[7];
        listePieces[0] = new Barre();
        listePieces[1] = new Carre();
        listePieces[2] = new J();
        listePieces[3] = new L();
        listePieces[4] = new S();
        listePieces[5] = new T();
        listePieces[6] = new Z();
        pieceSuivante = genererPiece();
        pieceCourante = genererPiece();
        grille.dessinerPiece(pieceCourante);
        score = 0;
        niveau=1;
        pause = 1000;
    }

    public Grille getGrille() {
        return grille;
    }
      
    
    public int getScore(){
        return score;
    }

    public Piece getPieceSuivante() {
        return pieceSuivante;
    }
    
    

    public void run() {
        while (true) {
            try {
                maj("B");
               
                if (pieceCourante.isBloque()) {
                    if( score-niveau*500>0 && pause-100>0)
                    {
                        niveau++;
                        notifyObserverNiveau(niveau);
                        pause=pause-100;
                    }
                    
                    
                    pieceSuivante.setPositionX((int) (Math.random() * 7));
                    pieceSuivante.setPositionY(0);
                    if (grille.verifierEmplacement(pieceSuivante)) {
                        pieceCourante = pieceSuivante;
                        pieceCourante.setBloque(false);
                        pieceSuivante=genererPiece();
                        notifyObserverSuivant(pieceSuivante);
                        System.out.println(pieceSuivante.toString());
                        grille.dessinerPiece(pieceCourante);
                         notifyObserver(grille);
                        
                    } else {
                        
                        System.out.println("Perdu");
                        //Thread.currentThread().sleep(10000);
                    }
                }
                Thread.currentThread().sleep(pause);
                if (mep) {
                    synchronized (this) {
                        wait();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void remiseAZero(){
        pieceSuivante = genererPiece();
        pieceCourante = genererPiece();
        pieceCourante.setBloque(false);
        grille.dessinerPiece(pieceCourante);
        pieceHold=null;
        score = 0;
        niveau=1;
        pause = 1000;
        grille=new Grille(10,20);
        grille.dessinerPiece(pieceCourante);
       
        notifyObserver(grille);
        notifyObserverSuivant(pieceSuivante);
        notifyObserverHold(pieceHold);
        notifyObserver(score);
        notifyObserverNiveau(niveau);
         mep=false;
       
    }

    public void play() {
        mep = false;
        synchronized (this) {
            notify();

        }
    }

    public synchronized void maj(String action) {
        if (!mep) {
            switch (action) {
                case "B":
                    descendrePiece();
                    if(pieceCourante.isBloque())
                    {
                        int nb_lignes = grille.effacerLigne();
                        CalculScore(nb_lignes);
                    }
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
                case "Space":
                    chutePiece();
                    break;
                case "Hold":
                    hold();
                    break;
                case "RAZ":
                    remiseAZero();
                    break;
            }
            notifyObserver(grille);
        }
    }

    public void mettreEnPause() {
        mep = true;
    }

    public void CalculScore(int nb_lignes) {
        switch (nb_lignes) {
            case 1:
                score += 40;
                break;
            case 2:
                score += 100;
                break;
            case 3:
                score += 300;
                break;
            case 4:
                score += 1000;
                break;
        
        }
        notifyObserver(score);
    }

    public Piece genererPiece() {
        Piece piece;
        piece=listePieces[(int) (Math.random() * 7)];
        System.out.println(piece.getClass().getName());
        if("modele.pieces.T".equals(piece.getClass().getName()))
        {
            
           piece=new T(piece.getLargeur(),piece.getLongueur(),piece.getMatrice());
           
            return piece;
        }
        if("modele.pieces.Barre".equals(piece.getClass().getName()))
        {
           
           piece=new Barre(piece.getLargeur(),piece.getLongueur(),piece.getMatrice());
           
            return piece;
        }
       
        return new Piece(piece.getLargeur(),piece.getLongueur(),piece.getMatrice());
    }
    
    public void hold()
    {
        if(pieceHold!=null)
        {
            pieceHold.setPositionX(pieceCourante.getPositionX());
            pieceHold.setPositionY(pieceCourante.getPositionY());
            grille.effacerPiece(pieceCourante);
            if(grille.verifierEmplacement(pieceHold))
            {
                Piece temp=pieceCourante;
                pieceCourante=pieceHold;
                pieceHold=temp;
            }
        }
        else
        {
            pieceSuivante.setPositionX(pieceCourante.getPositionX());
            pieceSuivante.setPositionY(pieceCourante.getPositionY());
            grille.effacerPiece(pieceCourante);
            if(grille.verifierEmplacement(pieceSuivante))
            {
                Piece temp=pieceCourante;
                pieceCourante=pieceSuivante;
                pieceHold=temp;
                pieceSuivante=genererPiece();
            }
        }
        grille.dessinerPiece(pieceCourante);
        notifyObserver(grille);
        notifyObserverSuivant(pieceSuivante);
        notifyObserverHold(pieceHold);
    }

    public void descendrePiece() {

        if (!pieceCourante.isBloque()) {
            grille.effacerPiece(pieceCourante);
            pieceCourante.deplacerPieceBas();
            if (grille.verifierEmplacement(pieceCourante)) {
                grille.dessinerPiece(pieceCourante);
            } else {
                pieceCourante.deplacerPieceHaut();
                grille.dessinerPiece(pieceCourante);
                pieceCourante.setBloque(true);
                
                System.out.println(score);
            }

        }
    }

    public void deplacerGauche() {

        if (!pieceCourante.isBloque()) {
            grille.effacerPiece(pieceCourante);
            pieceCourante.deplacerPieceGauche();
            if (grille.verifierEmplacement(pieceCourante)) {
                grille.dessinerPiece(pieceCourante);
            } else {
                pieceCourante.deplacerPieceDroite();
                grille.dessinerPiece(pieceCourante);
                grille.effacerLigne();
            }

        }
    }

    public void deplacerDroite() {

        if (!pieceCourante.isBloque()) {
            grille.effacerPiece(pieceCourante);
            pieceCourante.deplacerPieceDroite();
            if (grille.verifierEmplacement(pieceCourante)) {
                grille.dessinerPiece(pieceCourante);
            } else {
                pieceCourante.deplacerPieceGauche();
                grille.dessinerPiece(pieceCourante);
                grille.effacerLigne();
            }

        }
    }

    public void rotationPiece() {

        if (!pieceCourante.isBloque()) {
            grille.effacerPiece(pieceCourante);
            pieceCourante.rotationPiece();
            while (!grille.verifierEmplacement(pieceCourante)) {
                pieceCourante.rotationPiece();
            }
            grille.dessinerPiece(pieceCourante);

            notifyObserver(grille);
        }
    }
    
    public void chutePiece(){
        
        while (!pieceCourante.isBloque()) {
           descendrePiece();
            }
        
    }

    public void addObserver(ObserverGrille obs) {
        this.listObserver.add(obs);
    }

    public void notifyObserver(Grille grille) {
        for (ObserverGrille obs : listObserver) {
            obs.update(grille);
        }
    }
    public void notifyObserverSuivant(Piece piece) {
        for (ObserverGrille obs : listObserver) {
            obs.updateSuivant(pieceSuivante);
        }
    }
     public void notifyObserverHold(Piece piece) {
        for (ObserverGrille obs : listObserver) {
            obs.updateHold(pieceHold);
        }
    }
    public void notifyObserver(int score) {
        for (ObserverGrille obs : listObserver) {
           obs.update(score);
        }
    }
    
      public void notifyObserverNiveau(int niveau) {
        for (ObserverGrille obs : listObserver) {
           obs.updateNiveau(niveau);
        }
    }
    
    public void removeObserver() {
        listObserver = new ArrayList<ObserverGrille>();
    }

}
