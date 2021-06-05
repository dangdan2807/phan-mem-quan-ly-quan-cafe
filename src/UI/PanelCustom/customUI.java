package UI.PanelCustom;

import javax.swing.*;
import java.awt.*;

public class customUI {
    private static customUI instance;

    public static customUI getInstance() {
        if (instance == null)
            instance = new customUI();
        return instance;
    }

    public void setCustomBtn(JButton btn) {
        btn.setBackground(Color.decode("#d0e1fd"));
        btn.setForeground(Color.decode("#1a66e3"));
    }

    public void setCustomBtnHover(JButton btn) {
        btn.setBackground(Color.decode("#73cdf5"));
        btn.setForeground(Color.WHITE);
    }

    public void setCustomLbTitle(JLabel lb) {
        lb.setFont(new Font("Dialog", Font.BOLD, 24));
        lb.setForeground(Color.decode("#1a66e3"));
    }
}
