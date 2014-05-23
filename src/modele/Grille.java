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
         for(int y=0; y<longueur;y++){
            for(int x=0;x<largeur;x++){
                
                grille[y][x]=null;
                
            }
            
        }
    }
    
    
    public void dessinerPiece(Piece p)
    {
      
       
        for( int y=0;y<p.getLongueur();y++)
        {
            for(int x=0;x<p.getLargeur();x++)
            {
                if(p.getMatrice()[y][x]!=null)
                {
                grille[y+p.getPositionY()][x+p.getPositionX()]=p.getMatrice()[y][x];
                }
            }
        }
    }
    
    public void effacerPiece(Piece p)
    {
        for( int y=0;y<p.getLongueur();y++)
        {
            for(int x=0;x<p.getLargeur();x++)
            {
                if(p.getMatrice()[y][x]!=null)
                {
                grille[y+p.getPositionY()][x+p.getPositionX()]=null;
                }
            }
        }
    }
    
    public boolean verifierEmplacement(Piece p)
    {
       for( int y=0;y<p.getLongueur();y++)
        {
            for(int x=0;x<p.getLargeur();x++)
            {
                
                if( 0<=y+p.getPositionY()&& y+p.getPositionY()<longueur && 0<=x+p.getPositionX() &&x+p.getPositionX()<largeur )
                {
                    if( grille[y+p.getPositionY()][x+p.getPositionX()]!=null  && p.getMatrice()[y][x] != null)
                    {return false;}
                }
                else{
                    return false;
                }
               
            }
        } 
       return true;
    }
    
     public boolean verifierLigne(int numLigne)
    {       
        //numLigne : 1ère ligne pleine détectée
        for(int x=0;x<largeur;x++)
        {               
            if(grille[numLigne][x]==null)
            { 
                return false;
            }  
        }
        return true;
    }
     
     public int effacerLigne()
     {
        int nb_lignes=0;
        for(int y=0; y<longueur; y++)
        {
            if(verifierLigne(y))
            {
                nb_lignes++;
                //la ligne pleine détectée est supprimée et on décale la grille vers le bas
                deplacerGrille(y);
            }
        }   
        return nb_lignes;
     }
     
     public void deplacerGrille(int numLigne)
     {
        for(int y=numLigne; y>0; y--)
        {
            
            for(int x=0;x<largeur;x++)
            {               
                grille[y][x]=grille[y-1][x];
                //on rajoute des blocs vides à la 1ère ligne
                
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
