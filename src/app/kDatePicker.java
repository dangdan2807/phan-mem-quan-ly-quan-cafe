package app;

import javax.swing.*;
import java.awt.event.*;
import java.text.*;

public class kDatePicker extends JPanel implements ActionListener {
    private JTextField txt;
    private JButton btn;

    public kDatePicker() {
        setLayout(null);

        txt = new JTextField();
        txt.setBounds(0, 0, 120, 20);
        txt.setEditable(false);
        txt.setText(DialogDatePicker.getToDay());

        btn = new JButton("...");
        btn.setBounds(121, 0, 29, 20);

        this.add(txt);
        this.add(btn);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn)) {
            DialogDatePicker f = new DialogDatePicker();
            f.setModal(true);
            f.setVisible(true);
            String date = f.setPickedDate();
            txt.setText(date);
        }
    }

    public String getValue() {
        return txt.getText();
    }

    public int getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }

    public int getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }

    public int getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }
}
