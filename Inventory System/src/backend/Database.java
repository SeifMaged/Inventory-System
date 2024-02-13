package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Database<Thing extends Record> {

    protected ArrayList<Thing> records;
    protected String filename;

    public Database(String filename) {
        this.filename = filename;
        records = new ArrayList<>();
    }

    public void readFromFile() {

        try {

            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String data;
            while (scanner.hasNextLine()) {

                data = scanner.nextLine();
                Thing t = createRecordFrom(data);
                if (t != null) {
                    records.add(t);
                } else {
                    System.out.println("Invalid Data.");
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file.");
        }
    }

    public abstract Thing createRecordFrom(String line);

    public ArrayList<Thing> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        return getRecord(key) != null;
    }

    public Thing getRecord(String key) {
        for (Thing t : this.records) {
            if (key.equals(t.getSearchKey())) {
                return t;
            }
        }
        return null;
    }

    public void insertRecord(Thing record) {
        if (record != null) {
            records.add(record);
        }
    }

    public void deleteRecord(String key) {
        Thing thing = getRecord(key);
        records.remove(records.indexOf(thing));

    }

    public void saveToFile() {
        String s;
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }

        for (Thing t : records) {
            s = t.lineRepresentation();
            s += "\n";
            try {
                myWriter.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            myWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
