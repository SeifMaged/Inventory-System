package frontend;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeRoleWindow extends Frame implements ActionListener {

    backend.EmployeeRole employee = new backend.EmployeeRole();
    Panel buttonsPanel;
    Button addProduct;
    Button viewProducts;
    Button purchaseProduct;
    Button viewPurchasedProduct;
    Button returnProduct;
    Button logout;

    ViewProductsWindow viewProductsWindow = new ViewProductsWindow(this, employee);
    ViewPurchasedProductWindow viewPurchasedProductWindow = new ViewPurchasedProductWindow(this, employee);

    Frame parent;

    public EmployeeRoleWindow(Frame parent) {
        super("Employee Role");
        this.parent = parent;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.setPreferredSize(new Dimension(600, 750));
        buttonsPanel = new Panel();

        addProduct = new Button("Add Product", 60);
        addProduct.addActionListener(this);

        viewProducts = new Button("View Products", 60);
        viewProducts.addActionListener(this);

        purchaseProduct = new Button("Purchase Product", 60);
        purchaseProduct.addActionListener(this);

        viewPurchasedProduct = new Button("View Purchased Products", 60);
        viewPurchasedProduct.addActionListener(this);

        returnProduct = new Button("Return Product", 60);
        returnProduct.addActionListener(this);

        logout = new Button("Logout", 60);
        logout.addActionListener(this);

        buttonsPanel.add(addProduct);
        buttonsPanel.add(viewProducts);
        buttonsPanel.add(purchaseProduct);
        buttonsPanel.add(viewPurchasedProduct);
        buttonsPanel.add(returnProduct);
        buttonsPanel.add(logout);

        this.add(buttonsPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addProduct) {
            this.setVisible(false);
            new AddProductWindow(this, employee);
        }
        if (e.getSource() == viewProducts) {
            this.setVisible(false);
            viewProductsWindow.setVisible(true);
        }
        if (e.getSource() == purchaseProduct) {
            this.setVisible(false);
            new PurchaseProductWindow(this, employee);
        }
        if (e.getSource() == viewPurchasedProduct) {
            this.setVisible(false);
            viewPurchasedProductWindow.setVisible(true);
        }
        if (e.getSource() == returnProduct) {
            this.setVisible(false);
            new ReturnProductWindow(this, employee);
        }
        if (e.getSource() == logout) {
            this.setVisible(false);
            employee.logout();
            parent.setVisible(true);
        }
    }
}
