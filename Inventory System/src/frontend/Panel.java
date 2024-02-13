package frontend;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Panel extends JPanel {

    public Panel() {
        this(/*Vertical Gap = */ 40, /*Horizontal Gap = */ 10, /*Top Gap = */ 30);
    }

    public Panel(int verticalGap, int horizontalGap, int topGap) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, horizontalGap, verticalGap));
        this.setBorder(new EmptyBorder(topGap, 0, 0, 0));
    }
}
