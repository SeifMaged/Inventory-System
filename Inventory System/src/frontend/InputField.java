package frontend;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;

public class InputField extends JTextField {

    public InputField() {
        this.setEditable(true);
        this.setPreferredSize(new Dimension(200, 50));
        this.setFont(new Font("Times New Roman", Font.BOLD, 20));
    }

}