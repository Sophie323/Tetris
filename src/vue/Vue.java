package vue;

/**
 *
 * @author p0909863
 */
import Observe.ObserverGrille;
import Observe.ObserverTemps;
import controleur.TetrisControler;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;

import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;
import modele.Grille;

/**
 *
 * @author frederic
 */
public class Vue extends JFrame implements ObserverGrille, ObserverTemps{

    private TetrisControler controler;
    GridLayout layout = new GridLayout(20, 10);
    JPanel grille_pan;

    public Vue(TetrisControler controleur) {
        super();
        this.controler = controleur;
        build();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

    }

    public void build() {

        //JMenu jm = new JMenu();
        JMenuBar jm = new JMenuBar();

        JMenu m = new JMenu("Jeu");

        JMenuItem mi = new JMenuItem("Partie");

        //ItemListener i = new Item
        m.add(mi);
        jm.add(m);
        setJMenuBar(jm);

        setTitle("Ma première fenêtre");
        setSize(300, 600);
        setResizable(false);

        grille_pan = afficherGrille(null);
        addKeyListener(new Fleche());
        setContentPane(grille_pan);
        setVisible(true);
        
    }

    public JPanel afficherGrille(Grille grille) {
        JPanel pan = new JPanel();
        GridLayout layout = new GridLayout(20, 10);
        pan.setLayout(layout);

        Border blackline = BorderFactory.createLineBorder(Color.black, 1);

        for (int i = 0; i < 200; i++) {
            JComponent ptest = new Case(Color.white);
            ptest.setBorder(blackline);
            pan.add(ptest);
        }
        if (grille != null) {
            for (int y = 0; y < grille.getLongueur(); y++) {
                for (int x = 0; x < grille.getLargeur(); x++) {
                    if(grille.getGrille()[y][x]==null){
                        pan.getComponent(x + y * 10).setBackground(Color.WHITE);
                    }
                    else{
                        pan.getComponent(x + y * 10).setBackground(grille.getGrille()[y][x].getCouleur());
                    }
                    
                }
            }
             
        }
       // pan.getComponent((x-1) * 10 + (y-1)).setBackground(Color.red);
        //pan.getComponentAt(10, 10).setBackground(Color.red);

        pan.setBorder(blackline);
        grille_pan=pan;
        setContentPane(grille_pan);
        grille_pan.repaint();
        validate();
        //setContentPane(grille_pan);
        return pan;
    }
    
     public void update(Grille grille)
     {
         afficherGrille(grille);
     }
     
     public void update()
     {
      controler.control("B");   
     }
class Fleche implements KeyListener{ 
    public void keyReleased(KeyEvent e) {
                
  }
    public void keyPressed(KeyEvent e) {
     if(e.getExtendedKeyCode()==KeyEvent.VK_DOWN)
     { controler.control("B");}
     if(e.getExtendedKeyCode()==KeyEvent.VK_LEFT)
     {
         controler.control("G");
     }
      if(e.getExtendedKeyCode()==KeyEvent.VK_RIGHT)
     {
         controler.control("D");
     }
     if(e.getExtendedKeyCode()==KeyEvent.VK_UP)
     {
         controler.control("H");
     }
     if(e.getExtendedKeyCode()==KeyEvent.VK_P)
     {
         controler.control("Pause");
     }
      if(e.getExtendedKeyCode()==KeyEvent.VK_J)
     {
         controler.control("Jouer");
     }
      
        
                    
  }
    public void keyTyped(KeyEvent e) {
      //On affiche le chiffre en plus dans le label
       
  }
}
}

