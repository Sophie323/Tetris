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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;
import modele.Grille;
import modele.pieces.Piece;

/**
 *
 * @author frederic
 */
public class Vue extends JFrame implements ObserverGrille{

    private TetrisControler controler;
    private JPanel container = new JPanel();
    GridLayout layout = new GridLayout(20, 10);
    GridLayout piece_layout=new GridLayout(5, 5);
    GridLayout gridDroite=new GridLayout(3, 1);
    JPanel grille_pan;
    JPanel piece_suivante;
    
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
        
        //-- Grille de la pièce suivante
        piece_suivante=new JPanel();
        piece_suivante.setPreferredSize(new Dimension(100,100)); 
        piece_suivante.setLayout(piece_layout);
        piece_suivante.setSize(233, 233);
         for (int i = 0; i < 25; i++) {
            JComponent ptest = new Case(Color.white);
            
            piece_suivante.add(ptest);
        }
        
        afficherGrille(null);
        afficherGauche();
       // afficherDroite();
        afficherPieceSuivante(null);
        addKeyListener(new Fleche());
        setContentPane(container);
        
        
        
        droite = new JPanel();
        Border blackl = BorderFactory.createLineBorder(Color.darkGray, 2);
        droite.setPreferredSize(new Dimension(233,700));
        droite.setLayout(gridDroite);
        
        JPanel casep=new JPanel();
        casep.add(piece_suivante);
        JLabel next = new JLabel("next");
        next.setSize(100,100);
        droite.add(next);
        droite.add(casep);
       
        container.setLayout(new BorderLayout());
       // droite.add(piece_suivante);
        
        
        
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
    
    public void afficherPieceSuivante(Piece piece)
    {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                piece_suivante.getComponent(i + j * 5).setBackground(Color.WHITE);
            }
        }
        
         if (piece != null) {
            for (int y = 0; y < piece.getLongueur(); y++) {
                for (int x = 0; x < piece.getLargeur(); x++) {
                    if(piece.getMatrice()[y][x]==null){
                        piece_suivante.getComponent(x + y * 5).setBackground(Color.WHITE);
                    }
                    else{
                        piece_suivante.getComponent(x + y * 5).setBackground(piece.getMatrice()[y][x].getCouleur());
                    }
                    
                }
            }
             
        }
    }
    
    public void afficherDroite(){
        
        droite = new JPanel();
        Border blackl = BorderFactory.createLineBorder(Color.darkGray, 2);
        
        droite.setPreferredSize(new Dimension(233,700));
        droite.setLayout(gridDroite);
     
        
        droite.setBorder(blackl);
        
        JLabel next = new JLabel("next");
        next.setSize(100,100);
        next.setBorder(blackl);
        droite.add(next, BorderLayout.CENTER);
      
    }
    
    public void afficherGauche(){
        gauche = new JPanel();
        JPanel cont = new JPanel();
        JPanel titlePanel = new JPanel();
        JLabel hold = new JLabel("HOLD", SwingConstants.CENTER);
        JPanel scorePanel = new JPanel();
        String sc= getScore();
        JLabel score = new JLabel(sc,SwingConstants.CENTER);
        JLabel titleScore = new JLabel("SCORE",SwingConstants.CENTER);
        
        //affichage du panneau principal
        gauche.setPreferredSize(new Dimension(233,700)); 
        Border blackl = BorderFactory.createLineBorder(Color.darkGray, 2);
        gauche.setBorder(blackl);
        
        //affichage du panneau secondaire
        cont.setPreferredSize(new Dimension(218, 660));
        Border black = BorderFactory.createLineBorder(Color.darkGray, 1);
        cont.setBorder(black);
        
        //affichage du label "hold"
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.setPreferredSize(new Dimension(200,60));
        titlePanel.setLayout(new BorderLayout());       
               
        hold.setFont(new Font("Arial", Font.BOLD, 48));
        hold.setForeground(Color.orange);
       
        //affichage de la pièce mise de côté
        
        //affichage du score
        scorePanel.setBackground(Color.DARK_GRAY);
        scorePanel.setPreferredSize(new Dimension(200,200));
        scorePanel.setLayout(new BorderLayout());       
            
        titleScore.setFont(new Font("Arial", Font.BOLD, 48));
        titleScore.setForeground(Color.orange);
        score.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        score.setForeground(Color.lightGray);
        
        //construction du panel
        titlePanel.add(hold, BorderLayout.CENTER);
        cont.add(titlePanel, BorderLayout.CENTER);
        scorePanel.add(titleScore, BorderLayout.NORTH);
        scorePanel.add(score, BorderLayout.CENTER);
        cont.add(scorePanel, BorderLayout.CENTER);
        gauche.add(cont, BorderLayout.CENTER);
    }
    
    public String getScore(){
        String s="";
        
        
        return s;
    }
    
     public void update(Grille grille)
     {
         afficherGrille(grille);
     }
     public void update(Piece piece)
     {
         afficherPieceSuivante(piece);
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
         if(e.getExtendedKeyCode()==KeyEvent.VK_SPACE)
         {
             controler.control("Space");
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

