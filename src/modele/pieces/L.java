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
public class L extends Piece{
    
    public L ()
    {
        super(0,0);
        matrice = new Bloc[3][2];
        largeur=2;
        longueur=3;

        Bloc block = new Bloc(Color.ORANGE);
        
        for(int x=0; x<3; x++)
        {
            block.setPositionX(x);
            block.setPositionY(0);
            matrice[x][0]=block;
        } 
        block.setPositionX(2);
        block.setPositionY(1);
        matrice[2][1]=block;   
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