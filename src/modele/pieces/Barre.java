/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.pieces;

import java.awt.Color;
import modele.Bloc;

/**
 *
 * @author p0909863
 */

public class Barre extends Piece{
    
    
    
    public Barre ()
    {
        super(0,0);
        matrice = new Bloc[4][1];
        largeur=1;
        longueur=4;
        
        Bloc block = new Bloc(Color.CYAN);
        
        for(int x=0; x<4; x++)
        {
            block.setPositionX(x);
            block.setPositionY(0);
            matrice[x][0]=block;
        }
    }

    public Barre(int largeur, int longueur, Bloc[][] matrice) {
        super(largeur, longueur, matrice);
    }

  
    
    @Override
    public void rotationPiece()
    {
        if(largeur==1)
        {
        positionX--;
        positionY++;
        }
        else{
            positionX++;
            positionY--;
        }
        super.rotationPiece();
        
        
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }
    
    
}
