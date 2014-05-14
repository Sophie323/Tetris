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
import javax.swing.JPanel;


public class Case extends JPanel {

    public Case() {
        super();
        
        setBackground(Color.white);
        
       
        
    }
    
    public Case(Color c){
        setBackground(c);
    }
    
}

