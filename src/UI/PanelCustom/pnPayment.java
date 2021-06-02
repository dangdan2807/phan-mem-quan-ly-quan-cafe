package UI.PanelCustom;

import javax.swing.*;
// import java.awt.event.*;
import java.awt.*;

public class pnPayment extends JPanel {

    public pnPayment(int billID, double totalPrice) throws HeadlessException {
        setSize(400, 400);
        this.setLayout(new BorderLayout(0, 0));
        
        JLabel lbTitle = new JLabel("Thanh Toán");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lbTitle, BorderLayout.NORTH);
        
        JPanel pnInfo = new JPanel();
        this.add(pnInfo, BorderLayout.CENTER);
        
        JPanel pnBtn = new JPanel();
        this.add(pnBtn, BorderLayout.SOUTH);
        
        JButton btnPayment = new JButton("Thanh toán");
        pnBtn.add(btnPayment);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalGlue.setPreferredSize(new Dimension(20, 0));
        pnBtn.add(horizontalGlue);
        
        JButton btnCancel = new JButton("Hủy");
        pnBtn.add(btnCancel);
    }

    public static void main(int billID, double totalPrice) {
        new pnPayment(billID, totalPrice).setVisible(true);
    }
}
