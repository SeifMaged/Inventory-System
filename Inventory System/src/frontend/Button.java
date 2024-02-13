package frontend;

import java.awt.*;
import javax.swing.JButton;

public class Button extends JButton{
    
    public Button(String name)
    {
        this.setText(name);
        this.setFocusable(false);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Times New Roman", Font.BOLD, 23));
        this.setPreferredSize(new Dimension(400, 100));
    }
    
    public Button(String name, int preferredHeight) {
    	
    	this(name);
    	if(preferredHeight <= 0)
    		preferredHeight = 10; 
        this.setPreferredSize(new Dimension(400, preferredHeight));
    }
    
}