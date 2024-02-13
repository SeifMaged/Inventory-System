package frontend;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class AddEmployeeWindow extends Frame implements ActionListener {

    private final Panel mainPanel;
    private final Label IDLabel;
    private final Label nameLabel;
    private final Label emailLabel;
    private final Label addressLabel;
    private final Label phoneNumberLabel;
    private final InputField IDInput;
    private final InputField nameInput;
    private final InputField emailInput;
    private final InputField addressInput;
    private final InputField phoneNumberInput;
    private final Button addButton;
    private final AdminRoleWindow adminRoleWindow;

    private final backend.AdminRole admin;

    public AddEmployeeWindow(AdminRoleWindow adminRoleWindow, backend.AdminRole admin) {
        super("Add Employee");
        this.adminRoleWindow = adminRoleWindow;
        this.admin = admin;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                adminRoleWindow.setVisible(true);
                AddEmployeeWindow.this.setVisible(false);

            }
        });

        this.setPreferredSize(new Dimension(600, 750));
        mainPanel = new Panel();

        IDLabel = new Label("Employee ID");
        nameLabel = new Label("Name");
        emailLabel = new Label("E-Mail");
        addressLabel = new Label("Address");
        phoneNumberLabel = new Label("Phone Number");

        IDInput = new InputField();
        nameInput = new InputField();
        emailInput = new InputField();
        addressInput = new InputField();
        phoneNumberInput = new InputField();

        addButton = new Button("Add");
        addButton.addActionListener(this);

        mainPanel.add(IDLabel);
        mainPanel.add(IDInput);

        mainPanel.add(nameLabel);
        mainPanel.add(nameInput);

        mainPanel.add(emailLabel);
        mainPanel.add(emailInput);

        mainPanel.add(addressLabel);
        mainPanel.add(addressInput);

        mainPanel.add(phoneNumberLabel);
        mainPanel.add(phoneNumberInput);

        mainPanel.add(addButton);
        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {

            String id = IDInput.getText();
            String name = nameInput.getText();
            String email = emailInput.getText();
            String address = addressInput.getText();
            String phoneNumber = phoneNumberInput.getText();
            String empty = "";

            if (id.equals(empty) || name.equals(empty) || email.equals(empty) || address.equals(empty) || phoneNumber.equals(empty)) {
                JOptionPane.showMessageDialog(null, "Some fields are empty", "Wrong Inputs", JOptionPane.INFORMATION_MESSAGE);
            } else {
                admin.addEmployee(id, name, email, address, phoneNumber);
                adminRoleWindow.viewEmployeesWindow.updateEmployeeData();
                adminRoleWindow.setVisible(true);
                this.setVisible(false);
            }

        }

    }

}
