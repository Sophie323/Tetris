/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.awt.Color;

/**
 *
 * @author p0909863
 */
public class Bloc {
    
    private int positionX;
    private int positionY;
    private Color couleur;
    
      public Bloc(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.couleur = Color.white;
    }

    public Bloc(Color couleur) {
        this.couleur = couleur;
    }

    public Bloc(int positionX, int positionY, Color couleur) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.couleur = couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Color getCouleur() {
        return couleur;
    }
    
    
    
    
}
