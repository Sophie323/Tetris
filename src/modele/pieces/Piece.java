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
    
    private int positionX;
    private int positionY;
    protected int largeur;
    protected int longueur;
    
    protected Bloc[][] matrice;

    public Piece(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    /*
    public abstract void rotationPiece();
    public abstract void deplacerPieceLateral();*/
    public void deplacerPieceVertical()
    {
        positionY++;
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
