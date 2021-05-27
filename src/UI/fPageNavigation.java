package UI;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class fPageNavigation extends JFrame implements ActionListener {

    
    public fPageNavigation() {
        setTitle("Điều hướng quản lý");
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new fPageNavigation().setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
