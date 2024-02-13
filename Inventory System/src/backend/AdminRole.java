package backend;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AdminRole {

    private final EmployeeUserDatabase database;

    public AdminRole() {
        this.database = new EmployeeUserDatabase(constants.FileNames.EMPLOYEE_FILENAME);
        this.database.readFromFile();
    }

    public void addEmployee(String employeeId, String Name, String Email, String Address, String PhoneNumber) {
        boolean validInput = true;
        String empty = "";
        if (employeeId.equals(empty) || Name.equals(empty) || Email.equals(empty) || Address.equals(empty) || PhoneNumber.equals(empty)) {
            validInput = false;
        }

        if (database.contains(employeeId)) {
            validInput = false;
            JOptionPane.showMessageDialog(null, "The Employee with id = " + employeeId + " already exists!", "Wrong Inputs", JOptionPane.INFORMATION_MESSAGE);
        }

        if (validInput) {
            EmployeeUser newEmployee = new EmployeeUser(employeeId, Name, Email, Address, PhoneNumber);
            JOptionPane.showMessageDialog(null, "The Employee with id = " + employeeId + " has been successfully added.", "Added Successfully", JOptionPane.INFORMATION_MESSAGE);
            this.database.insertRecord(newEmployee);
        }

    }

    public EmployeeUser[] getListOfEmployees() {

        ArrayList<EmployeeUser> getData = this.database.returnAllRecords();

        EmployeeUser[] dataArray = new EmployeeUser[getData.size()];

        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = getData.get(i);
        }

        return dataArray;
    }

    public void deleteEmployee(String key) {

        if (database.contains(key)) {
            database.deleteRecord(key);
            JOptionPane.showMessageDialog(null, "The Employee with id = " + key + " has been deleted.", "Deleted!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "The Employee with id = " + key + " doesn't exist!", "ID not exist", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void logout() {
        this.database.saveToFile();
    }
}
