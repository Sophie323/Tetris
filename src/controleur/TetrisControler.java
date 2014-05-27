/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import java.util.ArrayList;
import modele.Jeu;

/**
 *
 * @author Sophie
 */
public class TetrisControler {
  public Jeu jeu;
  protected ArrayList<String> listeEvenement = new ArrayList<String>();
  
  public TetrisControler(Jeu jeu){
  this.jeu = jeu;
    
    this.listeEvenement.add("H");
    this.listeEvenement.add("B");
    this.listeEvenement.add("D");
    this.listeEvenement.add("G");
    this.listeEvenement.add("Pause");
    this.listeEvenement.add("Jouer");
    this.listeEvenement.add("Hold");
    this.listeEvenement.add("Space");
    this.listeEvenement.add("RAZ");
   }
  
  public void control(String touche) {
    //On notifie le modèle d'une action si le contrôle est bon
    //--------------------------------------------------------
    
    //Si l'opérateur est dans la liste
      
      
      if(this.listeEvenement.contains(touche)){
          if("Pause".equals(touche))
          {
              jeu.mettreEnPause();
          }
          if("Jouer".equals(touche)){
            jeu.play();
            
        }
          else{
              jeu.maj(touche);
          }
         
      }
      
      /*
    if(this.listeEvenement.contains(touche)){
      //Si l'opérateur est = 
      if("B".equals(touche))
      {
          this.jeu.descendrePiece();
      } //On ordonne au modèle d'afficher le résultat
      if("D".equals(touche))
      { this.jeu.deplacerDroite();}
      if("G".equals(touche))
      { this.jeu.deplacerGauche();}
      if("H".equals(touche))
      { this.jeu.rotationPiece();}
      
    }*/
   }
}
