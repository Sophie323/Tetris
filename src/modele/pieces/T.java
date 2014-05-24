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
public class T extends Piece{
    
    public T ()
    {
        super(0,0);
        matrice = new Bloc[2][3];
        largeur=3;
        longueur=2;

        Bloc block = new Bloc(Color.BLUE);
        
        for(int y=0; y<3; y++)
        {
            block.setPositionX(1);
            block.setPositionY(y);
            matrice[1][y]=block;
        } 
        block.setPositionX(0);
        block.setPositionY(1);
        matrice[0][1]=block;
    }

    public T(int largeur, int longueur, Bloc[][] matrice) {
        super(largeur, longueur, matrice);
    }
    
    
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    
    @Override
    public void rotationPiece()
    {
        if(largeur==2 && matrice[0][0]!=null)
        {
            positionX--;
            positionY++;
        }
        else if(largeur==3 &&  matrice[0][0]!=null){
            positionY--;
            
        }
        else if(largeur==3 &&  matrice[0][0]==null){
            positionX++;
            
        }
        super.rotationPiece();
        
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