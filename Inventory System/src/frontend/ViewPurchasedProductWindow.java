
package frontend;

import backend.CustomerProduct;
import backend.EmployeeRole;
import backend.EmployeeUser;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ViewPurchasedProductWindow extends Frame{
    
    JTable list = new JTable();
    DefaultTableModel model;
    CustomerProduct[] purchasedProducts;
    
    EmployeeRole employee;
    
    public ViewPurchasedProductWindow(EmployeeRoleWindow employeeRoleWindow, EmployeeRole employee) {
    	super("View Products");
        this.employee = employee;
        
        purchasedProducts = employee.getListOfPurchasingOperations();
        
        Object[] columns = {"Customer SSN", "Product ID", "Purchase Date"};
        model = new DefaultTableModel();
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	employeeRoleWindow.setVisible(true);
            	ViewPurchasedProductWindow.this.setVisible(false);
            	
            }
        });
        
        
        this.setBounds(0,0,1000,500);
        model.setColumnIdentifiers(columns);
        list.setModel(model);
        list.setDefaultEditor(Object.class, null); // makes the table cells non-editable
        
        // Centers cell text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < list.getColumnCount(); i++) {
            list.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        list.setBackground(Color.white);
        list.setForeground(Color.black);
        list.setGridColor(Color.black);
        list.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        list.setRowHeight(30);
        list.setAutoCreateRowSorter(true);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setForeground(Color.blue);
        scrollPane.setBackground(Color.white);
        scrollPane.setBounds(10, 10, 10, 10);
        
        this.setLocationRelativeTo(null);
        updatePurchases();
        this.add(scrollPane);   
        
    }
    
    public void updatePurchases() {
        
        CustomerProduct[] updatedPurchases = employee.getListOfPurchasingOperations();

        
        model.setRowCount(0); // Clears the existing rows in the table

        String line;
        String[] splitInput;
        for (CustomerProduct c : updatedPurchases) {
            line = c.lineRepresentation();
            splitInput = line.split(",");
            splitInput[2] = c.getSearchKey().split(",")[2]; // gets the date in dd-mm-yyyy format
            model.addRow(splitInput);
        }
    }
    
}

