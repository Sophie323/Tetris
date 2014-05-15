/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.awt.Color;
import modele.pieces.Piece;

/**
 *
 * @author p0909863
 */
public class Grille {
    
    private Bloc[][] grille;
    private int largeur;
    private int longueur;

    public Grille(int largeur, int longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.grille=new Bloc[this.longueur][this.largeur];
    }
    
    
    public void dessinerPiece(Piece p)
    {
        Bloc blanc=new Bloc(Color.white);
        for(int y=0; y<longueur;y++){
            for(int x=0;x<largeur;x++){
                
                grille[y][x]=null;
                
            }
            
        }
        for( int y=0;y<p.getLongueur();y++)
        {
            for(int x=0;x<p.getLargeur();x++)
            {
                grille[y+p.getPositionY()][x+p.getPositionX()]=p.getMatrice()[y][x];
            }
        }
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public Bloc[][] getGrille() {
        return grille;
    }
    
    
    
    
}
