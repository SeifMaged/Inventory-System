package frontend;

import backend.EmployeeRole;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class AddProductWindow extends Frame implements ActionListener {

    private final Panel mainPanel;
    private final Label IDLabel;
    private final Label nameLabel;
    private final Label manufacturerNameLabel;
    private final Label supplierNameLabel;
    private final Label quantityLabel;
    private final Label priceLabel;
    private final InputField IDInput;
    private final InputField nameInput;
    private final InputField manufacturerNameInput;
    private final InputField supplierNameInput;
    private final InputField quantityInput;
    private final InputField priceInput;
    private final Button addButton;
    private final EmployeeRoleWindow employeeRoleWindow;

    private final backend.EmployeeRole employee;

    public AddProductWindow(EmployeeRoleWindow employeeRoleWindow, EmployeeRole employee) {
        super("Add Product");

        this.employeeRoleWindow = employeeRoleWindow;
        this.employee = employee;
        this.setPreferredSize(new Dimension(600, 800));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                employeeRoleWindow.setVisible(true);
                AddProductWindow.this.setVisible(false);

            }
        });

        
        mainPanel = new Panel();

        IDLabel = new Label("Product ID");
        nameLabel = new Label("Product's Name");
        manufacturerNameLabel = new Label("Manufacturer's Name");
        supplierNameLabel = new Label("Supplier's Name");
        quantityLabel = new Label("Quantity");
        priceLabel = new Label("Price");

        IDInput = new InputField();
        nameInput = new InputField();
        manufacturerNameInput = new InputField();
        supplierNameInput = new InputField();
        quantityInput = new InputField();
        priceInput = new InputField();

        addButton = new Button("Add");
        addButton.addActionListener(this);

        mainPanel.add(IDLabel);
        mainPanel.add(IDInput);

        mainPanel.add(nameLabel);
        mainPanel.add(nameInput);

        mainPanel.add(manufacturerNameLabel);
        mainPanel.add(manufacturerNameInput);

        mainPanel.add(supplierNameLabel);
        mainPanel.add(supplierNameInput);

        mainPanel.add(quantityLabel);
        mainPanel.add(quantityInput);

        mainPanel.add(priceLabel);
        mainPanel.add(priceInput);

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
            String manufacturer = manufacturerNameInput.getText();
            String supplier = supplierNameInput.getText();
            String quantityString = quantityInput.getText();
            String priceString = priceInput.getText();
            if (id.equals("") || name.equals("") || manufacturer.equals("") || supplier.equals("")
                    || quantityString.equals("") || priceString.equals("")) {

                JOptionPane.showMessageDialog(null, "Some fields are Empty!",
                        "Incorrect Input", JOptionPane.WARNING_MESSAGE);
            } else {

                boolean validNumbers = true;
                int quantity = 0;
                float price = 0;
                try {
                    quantity = Integer.parseInt(quantityString);
                    price = Float.parseFloat(priceString);
                } catch (NumberFormatException numFormatException) {
                    validNumbers = false;
                }

                if (!validNumbers) {
                    JOptionPane.showMessageDialog(null, "You must enter valid numerical values in \"Quantity\" and \"Price\" fields!",
                            "Incorrect Input", JOptionPane.WARNING_MESSAGE);
                } else {
                    employee.addProduct(id, name, manufacturer, supplier, quantity, price);
                    employeeRoleWindow.viewProductsWindow.updateProductTable();

                    employeeRoleWindow.setVisible(true);
                    this.setVisible(false);
                }
            }

        }

    }

}
