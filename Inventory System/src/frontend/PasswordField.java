package frontend;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPasswordField;

public class PasswordField extends JPasswordField{

    public PasswordField() {
        this.setEditable(true);
        this.setPreferredSize(new Dimension(200, 50));
        this.setFont(new Font("Times New Roman", Font.BOLD, 20));
    }
}
