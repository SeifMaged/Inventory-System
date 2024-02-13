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

public class ReturnProductWindow extends Frame implements ActionListener {

    private final Panel mainPanel;

    private final Label customerSSNLabel;
    private final Label productIDLabel;
    private final Label purchaseDateLabel;
    private final Label returnDateLabel;

    private final InputField customerSSNInput;
    private final InputField productIDInput;

    com.toedter.calendar.JDateChooser purchaseDate;
    com.toedter.calendar.JDateChooser returnDate;

    private final Button returnButton;
    private final EmployeeRoleWindow employeeRoleWindow;
    String purchase;
    String returnItem;

    private final backend.EmployeeRole employee;

    public ReturnProductWindow(EmployeeRoleWindow employeeRoleWindow, EmployeeRole employee) {
        super("Return Product");
        this.employeeRoleWindow = employeeRoleWindow;
        this.employee = employee;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                employeeRoleWindow.setVisible(true);
                ReturnProductWindow.this.setVisible(false);

            }
        });

        this.setPreferredSize(new Dimension(600, 600));
        mainPanel = new Panel();

        customerSSNLabel = new Label("Customer SSN");
        productIDLabel = new Label("Product ID");
        purchaseDateLabel = new Label("Purchase Date");
        returnDateLabel = new Label("Return Date");

        customerSSNInput = new InputField();
        productIDInput = new InputField();

        purchaseDate = new com.toedter.calendar.JDateChooser();
        purchaseDate.setPreferredSize(new Dimension(200, 50));

        returnDate = new com.toedter.calendar.JDateChooser();
        returnDate.setPreferredSize(new Dimension(200, 50));

        returnButton = new Button("Return", 60);
        returnButton.addActionListener(this);

        mainPanel.add(customerSSNLabel);
        mainPanel.add(customerSSNInput);

        mainPanel.add(productIDLabel);
        mainPanel.add(productIDInput);

        mainPanel.add(purchaseDateLabel);
        mainPanel.add(purchaseDate);

        mainPanel.add(returnDateLabel);
        mainPanel.add(returnDate);

        mainPanel.add(returnButton);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            String SSN = customerSSNInput.getText();
            String ID = productIDInput.getText();

            if (SSN.equals("") || ID.equals("") || purchaseDate.getDate() == null || returnDate.getDate() == null) {

                JOptionPane.showMessageDialog(null, "Some fields are Empty!",
                        "Incorrect Input", JOptionPane.WARNING_MESSAGE);
            } else {

                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");

                LocalDate DateOfPurchase;
                purchase = dcn.format(purchaseDate.getDate());
                String[] date1 = purchase.split("-");
                DateOfPurchase = LocalDate.of(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]));

                LocalDate DateOfReturn;
                returnItem = dcn.format(returnDate.getDate());
                String[] date = returnItem.split("-");
                DateOfReturn = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));

                employee.returnProduct(SSN, ID, DateOfPurchase, DateOfReturn);
                employeeRoleWindow.viewPurchasedProductWindow.updatePurchases();
                employeeRoleWindow.viewProductsWindow.updateProductTable();

                employeeRoleWindow.setVisible(true);
                this.setVisible(false);
            }
        }
    }

}
