package backend;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CustomerProductDatabase extends Database<CustomerProduct> {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] splitData = line.split(",");
        boolean validFileData = true;
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(splitData[2]);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid data in file... Could not add to database");
            validFileData = false;
        }

        CustomerProduct customerProduct = null;

        if (validFileData) {
            customerProduct = new CustomerProduct(splitData[0], splitData[1], localDate);
        }

        return customerProduct;
    }
    
}