package frontend;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminRoleWindow extends Frame implements ActionListener {

    private final backend.AdminRole admin = new backend.AdminRole();
    private final Panel buttonsPanel;
    private final Button addEmployee;
    private final Button viewEmployees;
    private final Button removeEmployee;
    private final Button logout;
    protected final ViewEmployeesWindow viewEmployeesWindow = new ViewEmployeesWindow(this, admin);

    private final Frame parent;

    public AdminRoleWindow(Frame parent) {
        super("Admin Role");
        this.parent = parent;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.setPreferredSize(new Dimension(600, 750));
        buttonsPanel = new Panel();

        addEmployee = new Button("Add Employee");
        addEmployee.addActionListener(this);

        viewEmployees = new Button("View Employees");
        viewEmployees.addActionListener(this);

        removeEmployee = new Button("Remove Employee");
        removeEmployee.addActionListener(this);

        logout = new Button("Logout");
        logout.addActionListener(this);

        buttonsPanel.add(addEmployee);
        buttonsPanel.add(viewEmployees);
        buttonsPanel.add(removeEmployee);
        buttonsPanel.add(logout);

        this.add(buttonsPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEmployee) {
            this.setVisible(false);
            new AddEmployeeWindow(this, admin);
        }
        if (e.getSource() == viewEmployees) {
            this.setVisible(false);
            viewEmployeesWindow.setVisible(true);
        }
        if (e.getSource() == removeEmployee) {
            this.setVisible(false);
            new RemoveEmployeeWindow(this, admin);
        }
        if (e.getSource() == logout) {
            this.setVisible(false);
            admin.logout();
            parent.setVisible(true);
        }
    }
}
