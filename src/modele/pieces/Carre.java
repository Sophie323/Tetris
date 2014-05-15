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
public class Carre extends Piece{
   
     public Carre ()
    {
        super(0,0);
        matrice = new Bloc[2][2];
        largeur=2;
        longueur=2;

        Bloc block = new Bloc(Color.YELLOW);
        
        for(int x=0; x<2; x++)
        {
            for(int y=0; y<2; y++)
            {
                block.setPositionX(x);
                block.setPositionY(y);
                matrice[x][y]=block;
            }
        }       
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
