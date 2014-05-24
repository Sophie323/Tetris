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
public class Piece {
    
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

    public Piece(int largeur, int longueur, Bloc[][] matrice) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.matrice = matrice.clone();
    }
    
    
       

    public boolean isBloque() {
        return bloque;
    }

    public void setBloque(boolean bloque) {
        this.bloque = bloque;
    }
    
      
            
        
    
    
    @Override
    public String toString()
    {
        String s="";
        for(int i=0;i<longueur;i++)
        {
            for(int j=0;j<largeur;j++)
            {
                if(matrice[i][j]!=null)
                {
                    s+=1;
                }
                else
                {
                    s+=0;
                }
            }
            s+="\n";
        }
        return s;
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

    public void setMatrice(Bloc[][] matrice) {
        this.matrice = matrice;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
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
