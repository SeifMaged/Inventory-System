package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class EmployeeLogin extends Frame implements ActionListener {

    private final Button loginButton;
    private final Panel mainPanel;
    private final Label usernameLabel;
    private final Label passwordLabel;
    private final InputField usernameInput;
    private final PasswordField passwordInput;
    
    protected final EmployeeRoleWindow employeeRoleWindow;
    private final Frame parent;

    public EmployeeLogin(Frame parent) {
        super("Employee Login");
        this.parent = parent;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 500));
        employeeRoleWindow = new EmployeeRoleWindow(this.parent);
        
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	parent.setVisible(true);
            	EmployeeLogin.this.setVisible(false);
            	
            }
        });

        usernameLabel = new Label("Username");
        passwordLabel = new Label("Password");

        usernameInput = new InputField();
        passwordInput = new PasswordField();

        loginButton = new Button("Login");
        loginButton.addActionListener(this);

        mainPanel = new Panel();
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameInput);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordInput);
        mainPanel.add(loginButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (usernameInput.getText().equals(constants.LoginCredentials.EMPLOYEE_USERNAME)
                    && String.valueOf(passwordInput.getPassword()).equals(constants.LoginCredentials.EMPLOYEE_PASSWORD)) {
            	
            	usernameInput.setText("");
                passwordInput.setText("");
            	employeeRoleWindow.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password!", "Wrong Inputs", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

}
