package frontend;

import javax.swing.JFrame;

public class Frame extends JFrame{

    public Frame(String name){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setTitle(name);
    }
    
}