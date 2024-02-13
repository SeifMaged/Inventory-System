package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel {

    public Label(String name) {
        this.setText(name);
        this.setOpaque(true);
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setBackground(Color.GREEN);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.setPreferredSize(new Dimension(200, 50));
    }

}
