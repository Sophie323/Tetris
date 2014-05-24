/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

/**
 *
 * @author p0909863
 */
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Case extends JPanel {

    public Case() {
        super();
        
        setBackground(Color.white);
        
       
        
    }
    
    public Case(Color c){
        Border black=BorderFactory.createLineBorder(Color.darkGray, 1);
        //setBorder(black);
        setBackground(c);
    }
    public Case(Color fond,Color bordure)
    {
        Border border=BorderFactory.createLineBorder(bordure, 1);
        setBorder(border);
        setBackground(fond);
    }
    
}

