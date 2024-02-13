package frontend;

import backend.AdminRole;
import backend.EmployeeUser;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class ViewEmployeesWindow  extends Frame {
    
    JTable list = new JTable();
    DefaultTableModel model;
    EmployeeUser[] employees;
    
    AdminRole admin;
    
    public ViewEmployeesWindow(AdminRoleWindow adminRoleWindow, AdminRole admin) {
        super("View Employees");
        this.admin = admin;
        
        employees = admin.getListOfEmployees();
        
        Object[] columns = {"Employee ID", "Name", "Email", "Address", "Phone Number"};
        model = new DefaultTableModel();
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	adminRoleWindow.setVisible(true);
            	ViewEmployeesWindow.this.setVisible(false);
            	
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
        updateEmployeeData();
        this.add(scrollPane);   
        
    }
    
    public void updateEmployeeData() {
        
        EmployeeUser[] updatedEmployees = admin.getListOfEmployees();

        
        model.setRowCount(0); // Clears the existing rows in the table

        for (EmployeeUser emp : updatedEmployees) {
            String line = emp.lineRepresentation();
            model.addRow(line.split(","));
        }
    }
    
}
