package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class AdminLogin extends Frame implements ActionListener {

    private final Button loginButton;
    private final Panel mainPanel;
    private final Label usernameLabel;
    private final Label passwordLabel;
    private final InputField usernameInput;
    private final JPasswordField passwordInput;

    private final AdminRoleWindow adminRoleWindow;
    private final Frame parent;

    public AdminLogin(Frame parent) {
        super("Admin Login");
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 500));
        this.parent = parent;
        adminRoleWindow = new AdminRoleWindow(this.parent);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                parent.setVisible(true);
                AdminLogin.this.setVisible(false);

            }
        });

        usernameLabel = new Label("Username");
        passwordLabel = new Label("Password");

        usernameInput = new InputField();
        passwordInput = new JPasswordField();

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
            if (usernameInput.getText().equals(constants.LoginCredentials.ADMIN_USERNAME)
                    && String.valueOf(passwordInput.getPassword()).equals(constants.LoginCredentials.ADMIN_PASSWORD)) {

                usernameInput.setText("");
                passwordInput.setText("");
                this.setVisible(false);
                adminRoleWindow.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password!", "Wrong Inputs", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}
