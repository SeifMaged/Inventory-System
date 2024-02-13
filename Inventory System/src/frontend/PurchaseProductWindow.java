package frontend;

import backend.EmployeeRole;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class PurchaseProductWindow extends Frame implements ActionListener {

    private final Panel mainPanel;

    private final Label customerSSNLabel;
    private final Label productIDLabel;
    private final Label purchaseDateLabel;

    private final InputField customerSSNInput;
    private final InputField productIDInput;

    private final com.toedter.calendar.JDateChooser purchaseDate;

    private final Button purchaseButton;
    private final EmployeeRoleWindow employeeRoleWindow;
    String purchase;

    private final backend.EmployeeRole employee;

    public PurchaseProductWindow(EmployeeRoleWindow employeeRoleWindow, EmployeeRole employee) {
        super("Purchase Product");
        this.employeeRoleWindow = employeeRoleWindow;
        this.employee = employee;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                employeeRoleWindow.setVisible(true);
                PurchaseProductWindow.this.setVisible(false);

            }
        });

        this.setPreferredSize(new Dimension(600, 500));
        mainPanel = new Panel();

        customerSSNLabel = new Label("Customer SSN");
        productIDLabel = new Label("Product ID");
        purchaseDateLabel = new Label("Purchase Date");

        customerSSNInput = new InputField();
        productIDInput = new InputField();

        purchaseDate = new com.toedter.calendar.JDateChooser();
        purchaseDate.setPreferredSize(new Dimension(200, 50));

        purchaseButton = new Button("Purchase", 60);
        purchaseButton.addActionListener(this);

        mainPanel.add(customerSSNLabel);
        mainPanel.add(customerSSNInput);

        mainPanel.add(productIDLabel);
        mainPanel.add(productIDInput);

        mainPanel.add(purchaseDateLabel);
        mainPanel.add(purchaseDate);

        mainPanel.add(purchaseButton);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == purchaseButton) {
            String SSN = customerSSNInput.getText();
            String ID = productIDInput.getText();

            if (SSN.equals("") || ID.equals("") || purchaseDate.getDate() == null) {

                JOptionPane.showMessageDialog(null, "Some fields are Empty!",
                        "Incorrect Input", JOptionPane.WARNING_MESSAGE);
            } else {

                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate DateOfPurchase;
                purchase = dcn.format(purchaseDate.getDate());
                String[] date = purchase.split("-");
                DateOfPurchase = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));

                employee.purchaseProduct(SSN, ID, DateOfPurchase);
                employeeRoleWindow.viewPurchasedProductWindow.updatePurchases();
                employeeRoleWindow.viewProductsWindow.updateProductTable();

                employeeRoleWindow.setVisible(true);
                this.setVisible(false);
            }
        }
    }
}
