package backend;
public class ProductDatabase extends Database<Product> {

    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] splitData = line.split(",");
        int quantity = 0;
        float price = 0;
        boolean validFileData = true;
        try {
            quantity = Integer.parseInt(splitData[4]);
            price = Float.parseFloat(splitData[5]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid data in file... Could not add to database");
            validFileData = false;
        }

        Product product = null;

        if (validFileData) {
            product = new Product(splitData[0], splitData[1],
                    splitData[2], splitData[3], quantity, price);
        }

        return product;
    }

}