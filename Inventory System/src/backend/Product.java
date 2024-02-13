package backend;

public class Product implements Record{

    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {

        setProductID(productID);
        setProductName(productName);
        setManufacturerName(manufacturerName);
        setSupplierName(supplierName);
        setQuantity(quantity);
        setPrice(price);
    }

    public void setProductID(String productID) {

        this.productID = productID;

    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
    
    public void buy() {
        setQuantity(getQuantity() - 1);
    }

    public void returnUnit() {
        setQuantity(getQuantity() + 1);
    }

    public String lineRepresentation() {
        return (productID + ',' + productName + ',' + manufacturerName + ','
                + supplierName + ',' + quantity + ',' + price);
    }

    public String getSearchKey() {
        return this.productID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            this.quantity = 0;
        }
    }
}