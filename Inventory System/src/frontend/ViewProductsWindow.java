
package frontend;

import backend.EmployeeRole;
import backend.EmployeeUser;
import backend.Product;

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

public class ViewProductsWindow extends Frame{
    
    JTable list = new JTable();
    DefaultTableModel model;
    Product[] products;
    
    EmployeeRole employee;
    
    public ViewProductsWindow(EmployeeRoleWindow employeeRoleWindow, EmployeeRole employee) {
    	super("View Products");
        this.employee = employee;
        
        products = employee.getListOfProducts();
        
        Object[] columns = {"Product ID", "Product Name", "Manufacturer Name"
        		, "Supplier Name", "Quantity", "Price"};
        model = new DefaultTableModel();
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	employeeRoleWindow.setVisible(true);
            	ViewProductsWindow.this.setVisible(false);
            	
            }
        });
        
        
        this.setBounds(0,0,1200,500);
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
        updateProductTable();
        this.add(scrollPane);   
        
    }
    
    public void updateProductTable() {
        
        Product[] updatedProducts = employee.getListOfProducts();

        
        model.setRowCount(0); // Clears the existing rows in the table

        for (Product p : updatedProducts) {
            String line = p.lineRepresentation();
            model.addRow(line.split(","));
        }
    }
    
}

