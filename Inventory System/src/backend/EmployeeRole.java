package backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        this.productsDatabase = new ProductDatabase(constants.FileNames.PRODUCT_FILENAME);
        this.productsDatabase.readFromFile();

        this.customerProductDatabase = new CustomerProductDatabase(constants.FileNames.CUSTOMERPRODUCT_FILENAME);
        this.customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        
    	boolean validInput = true;
        
        if (quantity < 0) {
            validInput = false;
            JOptionPane.showMessageDialog(null, "Quantity has to be a nonnegative integer, A new product was not added", "Incorrect Input", JOptionPane.WARNING_MESSAGE);
        }

        if (price < 0) {
            validInput = false;
            JOptionPane.showMessageDialog(null, "Price has to be a nonnegative integer, A new product was not added", "Incorrect Input", JOptionPane.WARNING_MESSAGE);
        }

        if (productsDatabase.contains(productID)) {
            validInput = false;
            JOptionPane.showMessageDialog(null, "The Product with id = " + productID + " already exists!", "Prdoduct exists", JOptionPane.INFORMATION_MESSAGE);
        }

        if (validInput) {
            Product newProduct = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
            productsDatabase.insertRecord(newProduct);
            JOptionPane.showMessageDialog(null, "The Product with id = " + productID + " has been added successfully.", "Added", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public Product[] getListOfProducts() {

        ArrayList<Product> getData = this.productsDatabase.returnAllRecords();

        Product[] dataArray = new Product[getData.size()];

        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = getData.get(i);
        }

        return dataArray;
    }

    public CustomerProduct[] getListOfPurchasingOperations() {

        ArrayList<CustomerProduct> getData = this.customerProductDatabase.returnAllRecords();

        CustomerProduct[] dataArray = new CustomerProduct[getData.size()];

        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = getData.get(i);
        }

        return dataArray;
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (this.productsDatabase.contains(productID)) {
            Product p = this.productsDatabase.getRecord(productID);
            if (p.getQuantity() == 0) {
                JOptionPane.showMessageDialog(null, "All items of the product with id = " + productID + " has been finished and no item is left for the customet", "Quantity is not enough", JOptionPane.INFORMATION_MESSAGE);
                return false;
            } else {
                p.buy();
                this.customerProductDatabase.insertRecord(this.customerProductDatabase.createRecordFrom(customerSSN + ',' + productID + ',' + purchaseDate));
                JOptionPane.showMessageDialog(null, "The customer with SSN = " + customerSSN + " has successfully purchased the Product with id = " + productID, "Purchased", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "This Product Doesn't exist", "Incorrect inputs", JOptionPane.INFORMATION_MESSAGE);
        return false; // the product doesn't exist
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        String formattedPurchaseDate = dateFormat.format(purchaseDate);

        if (returnDate.isBefore(purchaseDate) && !returnDate.equals(purchaseDate)) {
            JOptionPane.showMessageDialog(null, "You can't return this item", "Failed", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        if (this.productsDatabase.contains(productID) == false) {
            JOptionPane.showMessageDialog(null, "You can't return this item", "Failed", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        if (this.customerProductDatabase.contains(customerSSN + ',' + productID + ',' + formattedPurchaseDate) == false) {
            JOptionPane.showMessageDialog(null, "You can't return this item", "Failed", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        if (returnDate.isAfter(purchaseDate.plusDays(14))) {
            JOptionPane.showMessageDialog(null, "You can't return this item", "Failed", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        Product p = this.productsDatabase.getRecord(productID);
        p.returnUnit();
        this.customerProductDatabase.deleteRecord(customerSSN + ',' + productID + ',' + formattedPurchaseDate);
        JOptionPane.showMessageDialog(null, "The customer with SSN = " + customerSSN + " should be paid a return price for the returned product", "Returned", JOptionPane.INFORMATION_MESSAGE);
        return p.getPrice();
    }

    public void logout() {
        this.productsDatabase.saveToFile();
        this.customerProductDatabase.saveToFile();
    }
}