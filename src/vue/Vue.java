package vue;

/**
 *
 * @author p0909863
 */
import Observe.ObserverGrille;
import Observe.ObserverTemps;
import controleur.TetrisControler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;
import modele.Grille;

/**
 *
 * @author frederic
 */
public class Vue extends JFrame implements ObserverGrille, ObserverTemps{

    private TetrisControler controler;
    private JPanel container = new JPanel();
    GridLayout layout = new GridLayout(20, 10);
    JPanel grille_pan;
    JPanel gauche;
    JPanel droite;

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

        JMenuItem mi = new JMenuItem("Nouvelle Partie");
        
        JMenuItem min = new JMenuItem("Pause");

        //ItemListener i = new Item
        m.add(mi);
        m.add(min);
        jm.add(m);
        setJMenuBar(jm);

        setTitle("Tetris");
        setSize(816, 700);
        setResizable(false);
        
        //--Grille Centrale
        grille_pan = new JPanel();
        grille_pan.setPreferredSize(new Dimension(350,700)); 
        grille_pan.setLayout(layout);

        Border blackline = BorderFactory.createLineBorder(Color.darkGray, 1);

        for (int i = 0; i < 200; i++) {
            JComponent ptest = new Case(Color.lightGray);
            ptest.setBorder(blackline);
            grille_pan.add(ptest);
        }
        
        
        afficherGrille(null);
        afficherGauche();
        afficherDroite();
        addKeyListener(new Fleche());
        setContentPane(container);
        
        container.setLayout(new BorderLayout());
        
        container.add(grille_pan, BorderLayout.CENTER);
        container.add(gauche, BorderLayout.WEST);
        container.add(droite, BorderLayout.EAST);
        setVisible(true);
        
        
    }

    public void afficherGrille(Grille grille) {
        
        if (grille != null) {
            for (int y = 0; y < grille.getLongueur(); y++) {
                for (int x = 0; x < grille.getLargeur(); x++) {
                    if(grille.getGrille()[y][x]==null){
                        grille_pan.getComponent(x + y * 10).setBackground(Color.lightGray);
                    }
                    else{
                        grille_pan.getComponent(x + y * 10).setBackground(grille.getGrille()[y][x].getCouleur());
                    }
                    
                }
            }
             
        }
    }
    
    public void afficherDroite(){
        
        droite = new JPanel();
        Border blackl = BorderFactory.createLineBorder(Color.darkGray, 2);
        
        droite.setPreferredSize(new Dimension(233,700)); 
        droite.setLayout(layout);
        
        droite.setBorder(blackl);
        
        JLabel next = new JLabel("next");
        next.setSize(100,100);
        next.setBorder(blackl);
        droite.add(next, BorderLayout.CENTER);
    }
    
    public void afficherGauche(){
        gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(233,700)); 
        gauche.setLayout(layout);
        Border blackl = BorderFactory.createLineBorder(Color.darkGray, 2);
        gauche.setBorder(blackl);
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

