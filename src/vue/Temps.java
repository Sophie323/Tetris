/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import Observe.ObservableTemps;
import Observe.ObserverGrille;
import Observe.ObserverTemps;
import java.util.ArrayList;
import modele.Grille;

/**
 *
 * @author Sophie
 */
public class Temps implements Runnable,ObservableTemps{
    
    long debut;
    long fin;
     private ArrayList<ObserverTemps> listObserver = new ArrayList<ObserverTemps>();
    
    public Temps(){
        debut=System.currentTimeMillis();
        fin=System.currentTimeMillis();
    }
    
    @Override
    public void run(){
        while(true){
            
        while(fin-debut<100000)
        {
            fin=System.currentTimeMillis();
        }
        notifyObserver();
             debut=fin;   
        }
    }
    public void addObserver(ObserverTemps obs) {
    this.listObserver.add(obs);
  }
    
  public void notifyObserver() {
    for(ObserverTemps obs : listObserver)
      obs.update();
  }
  public void removeObserver() {
    listObserver = new ArrayList<ObserverTemps>();
  }  
}
