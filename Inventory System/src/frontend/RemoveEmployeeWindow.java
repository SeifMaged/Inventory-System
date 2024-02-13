package frontend;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RemoveEmployeeWindow extends Frame implements ActionListener {

    private final Panel mainPanel;
    private final Label IDLabel;
    private final InputField IDInput;
    private final Button deleteButton;
    private final AdminRoleWindow adminRoleWindow;

    private final backend.AdminRole admin;

    public RemoveEmployeeWindow(AdminRoleWindow adminRoleWindow, backend.AdminRole admin) {
        super("Remove Employee");
        this.adminRoleWindow = adminRoleWindow;
        this.admin = admin;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	adminRoleWindow.setVisible(true);
            	RemoveEmployeeWindow.this.setVisible(false);
            	
            }
        });
        
        this.setPreferredSize(new Dimension(500, 400));

        mainPanel = new Panel();

        IDLabel = new Label("Employee ID");
        IDInput = new InputField();

        deleteButton = new Button("Remove");
        deleteButton.addActionListener(this);

        mainPanel.add(IDLabel);
        mainPanel.add(IDInput);
        mainPanel.add(deleteButton);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            admin.deleteEmployee(IDInput.getText());
            adminRoleWindow.viewEmployeesWindow.updateEmployeeData();
            this.setVisible(false);
            adminRoleWindow.setVisible(true);
        }
    }

}
