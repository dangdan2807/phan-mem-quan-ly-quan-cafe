// Author : Pham Dang Dan
// Date   : April 23, 2021

package UI.PanelCustom;

import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.sql.Date;

public class kDatePicker extends JPanel implements ActionListener {
    private JTextField txt;
    private JButton btn;
    private int widthDefault = 150;
    DialogDatePicker f = new DialogDatePicker();
    ImageIcon calenderIcon = new ImageIcon("data/images/calender_16.png");

    public kDatePicker() {
        setLayout(null);

        createGUI();
    }

    public kDatePicker(int width) {
        setLayout(null);
        setBounds(0, 0, width, 20);
        widthDefault = width;
        createGUI();
    }

    private void createGUI() {
        txt = new JTextField();
        txt.setBounds(0, 0, widthDefault - 30, 20);
        txt.setEditable(false);
        txt.setText(DialogDatePicker.getToDay());

        btn = new JButton(calenderIcon);
        btn.setBounds(widthDefault - 30, 0, 29, 20);

        this.add(txt);
        this.add(btn);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn)) {
            f.setModal(true);
            f.setVisible(true);
            String date = f.getPickedDate();
            txt.setText(date);
            if (txt.getText().equals("")) {
                txt.setText(DialogDatePicker.getToDay());
            }
        }
    }

    // lấy giá trị từ textField
    public String getValue() {
        return txt.getText();
    }

    // set giá trị về ngày hiện tại
    public void setValueToDay() {
        txt.setText(DialogDatePicker.getToDay());
    }

    // cập nhật giá trị với input java.sql.Date
    public void setValue(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        txt.setText(sdf.format(date));
    }

    // cập nhật giá trị với input String
    public void setValue(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date2 = Date.valueOf(sdf.format(date));
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        txt.setText(sdf.format(date2));
    }

    // lấy ra giá trị ngày dưới dạng java.sql.Date
    public Date getFullDate() throws ParseException {
        String strDate = txt.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf.parse(strDate);
        Date sqlDate = new Date(date.getTime());
        return sqlDate;
    }

    // lấy ra ngày
    public int getDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }

    // lấy ra tháng
    public int getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }

    // lấy ra năm
    public int getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }
}
