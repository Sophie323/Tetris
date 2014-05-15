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
public class J extends Piece{
   
     public J ()
    {
        super(0,0);
        matrice = new Bloc[3][2];
        largeur=2;
        longueur=3;

        Bloc block = new Bloc(Color.MAGENTA);
        
        for(int x=0; x<3; x++)
        {
                block.setPositionX(x);
                block.setPositionY(1);
                matrice[x][1]=block;      
        }
        block.setPositionX(2);
        block.setPositionY(0);
        matrice[2][0]=block;     
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