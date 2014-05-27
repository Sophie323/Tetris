package vue;


import Observe.ObserverGrille;
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
public class Vue extends JPanel implements ObserverGrille {

    private TetrisControler controler;
    // private JPanel container = new JPanel();
    GridLayout layout = new GridLayout(20, 10);
    GridLayout piece_layout = new GridLayout(4, 4);
    GridLayout gridDroite = new GridLayout(3, 1);
    JPanel grille_pan;
    JPanel piece_suivante;
    JPanel piece_cote;
    JLabel score;
    JPanel gauche;
    JPanel droite;
    JPanel centre;
    JLabel niveau;

    public Vue(TetrisControler controleur, Grille jeu, Piece pieceSuivante) {
        super();
        this.controler = controleur;

        build(jeu, pieceSuivante);

    }

    public void build(Grille jeu, Piece pieceSuivante) {

        //setTitle("Tetris");
        //setSize(816, 700);
        //setResizable(false);
        //--Grille Centrale
        centre = new JPanel();
        centre.setPreferredSize(new Dimension(350, 700));
        grille_pan = new JPanel();
        grille_pan.setPreferredSize(new Dimension(350, 680));
        grille_pan.setLayout(layout);
        Border blackline = BorderFactory.createLineBorder(Color.darkGray, 1);

        for (int i = 0; i < 200; i++) {
            JComponent ptest = new Case(Color.lightGray);
            ptest.setBorder(blackline);
            grille_pan.add(ptest);
        }

        //-- Grille de la pièce suivant     
        afficherDroite();
        afficherGauche();
        afficherGrille(jeu);
        afficherPieceSuivante(pieceSuivante);
        afficherPieceHold(null);

        //addKeyListener(new Fleche());
        //  setContentPane(container);
        this.setLayout(new BorderLayout());

        centre.add(grille_pan);
        this.add(centre, BorderLayout.CENTER);
        this.add(gauche, BorderLayout.WEST);
        this.add(droite, BorderLayout.EAST);
        this.setVisible(true);
    }

    public void afficherGrille(Grille grille) {

        if (grille != null) {
            for (int y = 0; y < grille.getLongueur(); y++) {
                for (int x = 0; x < grille.getLargeur(); x++) {
                    if (grille.getGrille()[y][x] == null) {
                        grille_pan.getComponent(x + y * 10).setBackground(Color.lightGray);
                    } else {
                        grille_pan.getComponent(x + y * 10).setBackground(grille.getGrille()[y][x].getCouleur());
                    }

                }
            }

        }
    }

    public void afficherPieceSuivante(Piece piece) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                piece_suivante.getComponent(i + j * 4).setBackground(Color.DARK_GRAY);
            }
        }

        if (piece != null) {
            int positiony = 0;
            int positionx = 0;
            if (piece.getLongueur() < 3) {
                positiony = 1;
            }

            if (piece.getLargeur() < 3) {
                positionx = 1;
            }
            for (int y = 0; y < piece.getLongueur(); y++) {
                for (int x = 0; x < piece.getLargeur(); x++) {
                    if (piece.getMatrice()[y][x] == null) {
                        piece_suivante.getComponent(x + positionx + (y + positiony) * 4).setBackground(Color.DARK_GRAY);
                    } else {

                        piece_suivante.getComponent(x + positionx + (y + positiony) * 4).setBackground(piece.getMatrice()[y][x].getCouleur());
                    }
                }
            }
       }
    }

    public void afficherPieceHold(Piece piece) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                piece_cote.getComponent(i + j * 4).setBackground(Color.DARK_GRAY);
            }
        }

        if (piece != null) {

            int positiony = 0;
            int positionx = 0;
            if (piece.getLongueur() < 3) {
                positiony = 1;
            }

            if (piece.getLargeur() < 3) {
                positionx = 1;
            }
            for (int y = 0; y < piece.getLongueur(); y++) {
                for (int x = 0; x < piece.getLargeur(); x++) {
                    if (piece.getMatrice()[y][x] == null) {
                        piece_cote.getComponent(x + positionx + (y + positiony) * 4).setBackground(Color.DARK_GRAY);
                    } else {

                        piece_cote.getComponent(x + positionx + (y + positiony) * 4).setBackground(piece.getMatrice()[y][x].getCouleur());
                    }

                }
            }

        }
    }

    public void afficherDroite() {

        droite = new JPanel();
        JPanel cont = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel casevide = new JPanel();
        JLabel next = new JLabel("NEXT", SwingConstants.CENTER);

        //affichage du panneau principal
        droite.setPreferredSize(new Dimension(233, 700));
        Border blackl = BorderFactory.createLineBorder(Color.darkGray, 2);
        droite.setBorder(blackl);

        //affichage du panneau secondaire
        cont.setPreferredSize(new Dimension(218, 660));
        Border black = BorderFactory.createLineBorder(Color.darkGray, 1);
        cont.setBorder(black);

        //affichage du label "next"
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.setPreferredSize(new Dimension(200, 60));
        titlePanel.setLayout(new BorderLayout());

        next.setFont(new Font("Arial", Font.BOLD, 48));
        next.setForeground(Color.orange);       
        
        //affichage de la pièce suivante
        piece_suivante = new JPanel();
        piece_suivante.setPreferredSize(new Dimension(200, 200));
        piece_suivante.setLayout(piece_layout);

        for (int i = 0; i < 16; i++) {
            JComponent ptest = new Case(Color.darkGray, Color.darkGray);

            piece_suivante.add(ptest);
        }

        titlePanel.add(next);
        cont.add(titlePanel);
        casevide.setPreferredSize(new Dimension(200, 200));
        casevide.add(piece_suivante);
        cont.add(casevide);
        droite.add(cont, BorderLayout.CENTER);
    }

    public void afficherGauche() {
        gauche = new JPanel();
        JPanel cont = new JPanel();
        JPanel titlePanel = new JPanel();
        JLabel hold = new JLabel("HOLD", SwingConstants.CENTER);
        JPanel casevide = new JPanel();
        JPanel scorePanel = new JPanel();
        JPanel niveauPanel = new JPanel();
        int s = 0;
        int sc = 0;

        //affichage du panneau principal
        gauche.setPreferredSize(new Dimension(233, 700));
        Border blackl = BorderFactory.createLineBorder(Color.darkGray, 2);
        gauche.setBorder(blackl);

        //affichage du panneau secondaire
        cont.setPreferredSize(new Dimension(218, 660));
        Border black = BorderFactory.createLineBorder(Color.darkGray, 1);
        cont.setBorder(black);

        //affichage du label "hold"
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.setPreferredSize(new Dimension(200, 60));
        titlePanel.setLayout(new BorderLayout());

        hold.setFont(new Font("Arial", Font.BOLD, 48));
        hold.setForeground(Color.orange);

        //affichage de la pièce mise de côté
        piece_cote = new JPanel();
        piece_cote.setPreferredSize(new Dimension(200, 200));
        piece_cote.setLayout(piece_layout);

        for (int i = 0; i < 16; i++) {
            JComponent ptest = new Case(Color.darkGray, Color.darkGray);

            piece_cote.add(ptest);
        }

        //affichage du score et du niveau
        scorePanel.setBackground(Color.DARK_GRAY);
        scorePanel.setPreferredSize(new Dimension(200, 100));
        scorePanel.setLayout(new BorderLayout());
        JLabel titleScore = new JLabel("SCORE", SwingConstants.CENTER);
        score = new JLabel("0",SwingConstants.CENTER);
        JLabel titleNiveau = new JLabel("NIVEAU", SwingConstants.CENTER);
        niveau = new JLabel("1",SwingConstants.CENTER);
        titleScore.setFont(new Font("Arial", Font.BOLD, 48));
        titleScore.setForeground(Color.orange);
        titleNiveau.setFont(new Font("Arial", Font.BOLD, 48));
        titleNiveau.setForeground(Color.orange);
        score.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
        score.setForeground(Color.lightGray);
        niveau.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
        niveau.setForeground(Color.lightGray);

        
        niveauPanel.setBackground(Color.DARK_GRAY);
        niveauPanel.setPreferredSize(new Dimension(200, 100));
        niveauPanel.setLayout(new BorderLayout());
        //construction du panel
        titlePanel.add(hold, BorderLayout.CENTER);
        cont.add(titlePanel, BorderLayout.CENTER);
        casevide.setPreferredSize(new Dimension(200, 200));
        casevide.add(piece_cote);
        cont.add(casevide);
        scorePanel.add(titleScore, BorderLayout.NORTH);
        scorePanel.add(score, BorderLayout.CENTER);
        niveauPanel.add(titleNiveau,BorderLayout.NORTH);
        niveauPanel.add(niveau,BorderLayout.CENTER);
        //scorePanel.add(titleNiveau, BorderLayout.SOUTH);
        //scorePanel.add(niveau,, BorderLayout.CENTER)
        cont.add(scorePanel, BorderLayout.CENTER);
        cont.add(niveauPanel, BorderLayout.SOUTH);
        gauche.add(cont, BorderLayout.CENTER);
    }

    public void afficherScore(int score) {
       Integer i=(Integer)score;
        this.score.setText(i.toString());
    }

    public void afficherNiveau(int niveau) {
        Integer i=(Integer)niveau;
        this.niveau.setText(i.toString());
    }

    public void update(Grille grille) {
        afficherGrille(grille);
    }

    public void updateSuivant(Piece piece) {
        afficherPieceSuivante(piece);
    }

    public void updateHold(Piece piece) {
        afficherPieceHold(piece);
    }

    public void update(int score) {
        afficherScore(score);
    }

    public void updateNiveau(int niveau) {
        afficherNiveau(niveau);
    }

    
}
