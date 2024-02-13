package frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class InventorySystem extends Frame implements ActionListener {

    private final Button adminRole;
    private final Button employeeRole;
    private final Panel mainPanel;

    public InventorySystem() {
        super("Inventory System");
        
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 500));

        adminRole = new Button("Admin Role");
        employeeRole = new Button("Employee Role");

        adminRole.addActionListener(this);
        employeeRole.addActionListener(this);

        mainPanel = new Panel(40, 10, 60);
        mainPanel.add(adminRole);
        mainPanel.add(employeeRole);

        this.add(mainPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new InventorySystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminRole) {
            this.setVisible(false);
            new AdminLogin(this);
        } else if (e.getSource() == employeeRole) {
            this.setVisible(false);
            new EmployeeLogin(this);
        }
    }
}
