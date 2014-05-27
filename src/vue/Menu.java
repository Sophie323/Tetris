/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import controleur.TetrisControler;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.Jeu;

/**
 *
 * @author Sophie
 */
public class Menu extends JFrame {
    Jeu jeu;
    TetrisControler controler;
    Vue vue;
    JPanel contenu;
    JPanel menu;
    
    public Menu(Jeu jeu, TetrisControler controler, Vue vue) {
        this.jeu = jeu;
        this.controler = controler;
        this.vue = vue;
        this.contenu=new JPanel();
     (new Thread(jeu)).start();
      
         addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
          
          build();
    }
    
    public void build() 
    {
        setTitle("Tetris");
        setSize(816, 720);
        setResizable(false);
        this.addKeyListener(new Touche());
       
        add(contenu);
        setVisible(true);
      afficherJeu();
        
    }
    
    public void afficherJeu()
    {
      
      JPanel menu=new JPanel();
      menu.setSize(100, 100);
      menu.setBounds(400, 450, 400, 450);
      //contenu.add(menu);
      vue.setBounds(0, 816, 0, 700);
       contenu.add(vue);
       
    }
    
 
    
 
    
    class Touche implements KeyListener{
        @Override
         public void keyReleased(KeyEvent e) {
             System.out.println("key released");
      }
         @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed");
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
          if(e.getExtendedKeyCode()==KeyEvent.VK_H)
         {
             controler.control("Hold");
         }
           if(e.getExtendedKeyCode()==KeyEvent.VK_R)
         {
             controler.control("RAZ");
         }

      }
         @Override
        public void keyTyped(KeyEvent e)
        {
            System.out.println("key typed");
        }
    }
    
    class ClicBouton implements MouseListener{
        @Override
        public void mouseExited(MouseEvent e)
        {
            
        }
         @Override
        public void mouseEntered(MouseEvent e)
        {
            
        }
         @Override
        public void mouseReleased(MouseEvent e)
        {
            
        }
          @Override
        public void mousePressed(MouseEvent e)
        {
            
        }
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
                afficherJeu();
               (new Thread(jeu)).start();
            System.out.println("mouse clicked");
        }
    }
    
    
    
} 
