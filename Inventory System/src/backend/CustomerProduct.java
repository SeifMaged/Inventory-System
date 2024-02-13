package backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Record{
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        setCustomerSSN(customerSSN);
        setProductID(productID);
        setPurchaseDate(purchaseDate);
    }
    
    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    
    @Override
    public String lineRepresentation(){
        return (getCustomerSSN() + ',' + getProductID() + ',' + getPurchaseDate());
    }
    
    @Override
    public String getSearchKey(){
        DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("dd-MM-YYYY");
        return(getCustomerSSN() + ',' + getProductID() + ',' + dateFormat.format(getPurchaseDate()));
        
    }
}