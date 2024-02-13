package backend;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Ahmed Mahmoud
 */
public class Lab4 {

    public static void getStartedAsADMIN(AdminRole Admin) {

        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1.Add a new Employee");
        System.out.println("2.Remove an existing Employee");
        System.out.println("3.Show the Employee List");
        String role = sc.nextLine();

        if (role.equals("1")) {
            System.out.print("Employee's ID : ");
            String employeeID = sc.nextLine();
            System.out.print("Employee's Name : ");
            String name = sc.nextLine();
            System.out.print("Employee's Email : ");
            String email = sc.nextLine();
            System.out.print("Employee's Address : ");
            String address = sc.nextLine();
            System.out.print("Employee's Phone Number : ");
            String phoneNumber = sc.nextLine();

            Admin.addEmployee(employeeID, name, email, address, phoneNumber);
        } else if (role.equals("2")) {
            System.out.print("Employee's ID : ");
            String employeeID = sc.nextLine();

            Admin.deleteEmployee(employeeID);
        } else if (role.equals("3")) {
            EmployeeUser[] employees = Admin.getListOfEmployees();

            for (EmployeeUser employee : employees) {
                System.out.println(employee.lineRepresentation());
            }
        } else {
            System.out.println("Unvalid input");
        }

        System.out.println("What do you want to do?");
        System.out.println("1.Choose another option");
        System.out.println("2.Exit");
        role = sc.nextLine();

        if (role.equals("1")) {
            Admin.logout();
            getStartedAsADMIN(Admin);
        } else if (role.equals("2")) {
            Admin.logout();
        }
    }

    public static void getStartedAsEMPLOYEE(EmployeeRole Employee) {

        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1.Add Product");
        System.out.println("2.Show List of products");
        String role = sc.nextLine();

        if (role.equals("1")) {
            System.out.print("Product's ID : ");
            String productID = sc.nextLine();
            System.out.print("Product's Name : ");
            String productName = sc.nextLine();
            System.out.print("Manufacturer's Name : ");
            String manufacturerName = sc.nextLine();
            System.out.print("Supplier's Name : ");
            String suplierName = sc.nextLine();
            System.out.print("Quantity : ");
            String quantity = sc.nextLine();
            int Quantity = Integer.parseInt(quantity);
            System.out.print("Price : ");
            String price = sc.nextLine();
            float Price = Float.parseFloat(price);

            Employee.addProduct(productID, productName, manufacturerName, suplierName, Quantity, Price);
        } else if (role.equals("2")) {
            Product[] listOfProducts = Employee.getListOfProducts();

            for (Product p : listOfProducts) {
                System.out.println(p.lineRepresentation());
            }
        } else {
            System.out.println("Unvalid input");
        }

        System.out.println("What do you want to do?");
        System.out.println("1.Choose another option");
        System.out.println("2.Exit");
        role = sc.nextLine();

        if (role.equals("1")) {
            Employee.logout();
            getStartedAsEMPLOYEE(Employee);
        } else if (role.equals("2")) {
            Employee.logout();
        }
    }

    public static void getStartedAsCUSTOMER(EmployeeRole Employee) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1.Purchase Product");
        System.out.println("2.Return a Product");
        System.out.println("3.Show List of purchased products");
        String role = sc.nextLine();

        if (role.equals("1")) {
            System.out.print("Customer's SSN : ");
            String customerSSN = sc.nextLine();
            System.out.print("Product's ID : ");
            String productID = sc.nextLine();

            Employee.purchaseProduct(customerSSN, productID, LocalDate.now());
        } 
        else if (role.equals("2")) {
            System.out.print("Customer's SSN : ");
            String customerSSN = sc.nextLine();
            System.out.print("Product's ID : ");
            String productID = sc.nextLine();
            System.out.print("Purchase Date (YYYY-MM-DD) : ");
            String purchase = sc.nextLine();
            String[] date = purchase.split("-");
            LocalDate purchaseDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
            
            double price = Employee.returnProduct(customerSSN, productID, purchaseDate, LocalDate.now());
            
            if(price == -1){
                System.out.println("You Can't Return this Item");
            }
            else{
                System.out.println("You have returned this item Successfly");
            }
        } 
        else if (role.equals("3")) {
            CustomerProduct[] listOfPurchasedProducts = Employee.getListOfPurchasingOperations();

            for (CustomerProduct p : listOfPurchasedProducts) {
                System.out.println(p.lineRepresentation());
            }
        } else {
            System.out.println("Unvalid input");
        }

        System.out.println("What do you want to do?");
        System.out.println("1.Choose another option");
        System.out.println("2.Exit");
        role = sc.nextLine();

        if (role.equals("1")) {
            Employee.logout();
            getStartedAsCUSTOMER(Employee);
        } else if (role.equals("2")) {
            Employee.logout();
        }
    }

//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Choose your role");
//        System.out.println("1.Admin");
//        System.out.println("2.Employee");
//        System.out.println("3.Customer");
//        String role = sc.nextLine();
//
//        if (role.equals("1")) {
//            AdminRole Admin = new AdminRole();
//            getStartedAsADMIN(Admin);
//        } else if (role.equals("2")) {
//            EmployeeRole Employee = new EmployeeRole();
//            getStartedAsEMPLOYEE(Employee);
//        } else if (role.equals("3")) {
//            EmployeeRole Employee = new EmployeeRole();
//            getStartedAsCUSTOMER(Employee);
//        } else {
//            System.out.println("Unvalid input");
//            System.out.println("Please try again");
//        }
//
//    }
}