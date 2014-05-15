/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.pieces;

import modele.Bloc;

/**
 *
 * @author p0909863
 */
public abstract class Piece {
    
    protected int positionX;
    protected int positionY;
    protected int largeur;
    protected int longueur;
    private boolean bloque;
    
    protected Bloc[][] matrice;

    public Piece(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.bloque=false;
    }

    public boolean isBloque() {
        return bloque;
    }

    public void setBloque(boolean bloque) {
        this.bloque = bloque;
    }
    
    
    
    /*
    public abstract void rotationPiece();
    public abstract void deplacerPieceLateral();*/
    public void deplacerPieceBas()
    {
        positionY++;
    }
    public void deplacerPieceHaut()
    {
        positionY--;
    }
    public void deplacerPieceGauche()
    {
        positionX--;
    }
    public void deplacerPieceDroite()
    {
        positionX++;
    }
    
    public void rotationPiece()
    {
        int height=largeur;
        int width=longueur;
        Bloc[][] piece=new Bloc[height][width];
        for(int i=0;i<largeur;i++)
        {
            for(int j=0;j<longueur;j++)
            {
                piece[i][j]=matrice[longueur-1-j][i];
            }
        }
        largeur=width;
        longueur=height;
        matrice=piece;
        
        
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public Bloc[][] getMatrice() {
        return matrice;
    }
    
    
    
    
}
