import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Calendar;
import java.sql.*;

public class ChamCong extends JFrame implements ActionListener, ChangeListener {
    private int width = 450, heightPn = 210, widthPn = width - 20;
    private JButton[] button = new JButton[49];
    private String day = "";
    private int month = Calendar.getInstance().get(Calendar.MONTH);
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private JButton btnPre, btnNext, btnCancel, btnSubmit;
    private SpinnerNumberModel spinYearModel;
    private JSpinner spinYear;
    private int check = 0, viTri = -1;
    private JLabel lbMonth, lbYear, lbThu, lbNgayThang, lbToDay;
    private String blueColor = "#3f51b5";
    private String whiteColor = "#fafafa";

    public ChamCong() {
        setTitle("Chọn ngày");
        setSize(447, 240);
        setResizable(false);
        setLocationRelativeTo(null);

        createFormDatePicker();
    }

    public void createFormDatePicker() {
        JPanel pnMain = new JPanel();
        pnMain.setBounds(0, 0, widthPn, heightPn);
        pnMain.setBackground(Color.decode(whiteColor));
        pnMain.setLayout(null);

        JPanel pnShowTime = new JPanel();
        pnShowTime.setBounds(0, 0, 100, 202);
        pnShowTime.setBackground(Color.decode(blueColor));
        pnMain.add(pnShowTime);
        pnShowTime.setLayout(null);

        lbYear = new JLabel("year");
        lbYear.setFont(new Font("Dialog", Font.BOLD, 15));
        lbYear.setBounds(12, 12, 107, 16);
        lbYear.setForeground(Color.decode("#aeb5df"));
        pnShowTime.add(lbYear);

        lbThu = new JLabel("thứ");
        lbThu.setFont(new Font("Dialog", Font.BOLD, 18));
        lbThu.setBounds(12, 40, 107, 25);
        lbThu.setForeground(Color.WHITE);
        pnShowTime.add(lbThu);

        lbNgayThang = new JLabel("tháng ngày");
        lbNgayThang.setFont(new Font("Dialog", Font.BOLD, 18));
        lbNgayThang.setBounds(12, 68, 107, 25);
        lbNgayThang.setForeground(Color.WHITE);
        pnShowTime.add(lbNgayThang);

        String[] header = { "S", "M", "T", "W", "T", "F", "S" };
        JPanel pnDateTable = new JPanel(new GridLayout(7, 7));
        pnDateTable.setBackground(Color.decode(whiteColor));

        for (int i = 0; i < button.length; i++) {
            final int selection = i;
            button[i] = new JButton();
            button[i].setFocusPainted(false);
            button[i].setBackground(Color.WHITE);
            if (i < 7) {
                button[i].setText(header[i]);
                button[i].setEnabled(false);
                button[i].setForeground(Color.decode(blueColor));
            } else {
                button[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        if (viTri != -1) {
                            button[viTri].setBackground(Color.decode(whiteColor));
                            button[viTri].setForeground(Color.BLACK);
                        }
                        viTri = selection;
                        button[selection].setBackground(Color.decode(blueColor));
                        button[selection].setForeground(Color.decode(whiteColor));
                        int day = Integer.parseInt(button[selection].getText());
                        displayShowDate(day);
                    }
                });
            }
            button[i].setBorder(null);
            button[i].setPreferredSize(new Dimension(20, 20));
            button[i].setBackground(Color.decode(whiteColor));
            pnDateTable.add(button[i]);
        }

        JPanel pnBtn = new JPanel();
        pnBtn.setLayout(null);
        pnBtn.setBackground(Color.decode(whiteColor));

        btnPre = new JButton("<");
        btnNext = new JButton(">");

        spinYearModel = new SpinnerNumberModel(year, 1900, null, 1);
        spinYear = new JSpinner(spinYearModel);
        lbMonth = new JLabel("tháng");
        lbMonth.setHorizontalAlignment(SwingConstants.CENTER);
        lbMonth.setFont(new Font("Dialog", Font.BOLD, 14));

        int h = 25;
        btnPre.setBounds(3, 0, 41, h);
        btnPre.setBackground(Color.decode(whiteColor));
        btnPre.setForeground(Color.decode(blueColor));
        btnPre.setBorder(null);
        lbMonth.setBounds(62, 2, 105, 25);
        spinYear.setBounds(165, 2, 70, h);
        btnNext.setBounds(287, 0, 41, h);
        btnNext.setBackground(Color.decode(whiteColor));
        btnNext.setForeground(Color.decode(blueColor));
        btnNext.setBorder(null);

        pnBtn.setBounds(101, 0, 330, 30);
        pnDateTable.setBounds(101, 30, 330, 140);

        pnBtn.add(btnPre);
        pnBtn.add(lbMonth);
        pnBtn.add(spinYear);
        pnBtn.add(btnNext);

        pnMain.add(pnBtn);
        pnMain.add(pnDateTable);

        JPanel pnSubmit = new JPanel();
        pnSubmit.setBackground(Color.decode(whiteColor));
        pnSubmit.setLayout(null);
        pnSubmit.setBounds(101, 172, 330, 30);
        pnMain.add(pnSubmit);

        btnSubmit = new JButton("OK");
        btnSubmit.setBounds(268, 0, 60, 26);
        btnSubmit.setBackground(Color.decode(whiteColor));
        btnSubmit.setForeground(Color.decode(blueColor));
        btnSubmit.setBorder(null);
        pnSubmit.add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(182, 0, 74, 26);
        btnCancel.setBackground(Color.decode(whiteColor));
        btnCancel.setForeground(Color.decode(blueColor));
        btnCancel.setBorder(null);
        pnSubmit.add(btnCancel);

        lbToDay = new JLabel("Today: ");
        lbToDay.setBounds(10, 6, 162, 14);
        pnSubmit.add(lbToDay);

        displayDate();
        showToDay();
        getContentPane().add(pnMain);

        btnNext.addActionListener(this);
        btnPre.addActionListener(this);
        btnSubmit.addActionListener(this);
        btnCancel.addActionListener(this);
        spinYear.addChangeListener(this);
    }

    public static void main(String[] args) {
        new ChamCong().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnPre)) {
            month--;
            displayDate();
        } else if (o.equals(btnNext)) {
            month++;
            displayDate();
        } else if (o.equals(btnSubmit)) {
            check = 1;
            dispose();
        } else if (o.equals(btnCancel)) {
            check = 0;
            dispose();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object o = e.getSource();
        if (o.equals(spinYear)) {
            displayDate();
        }
    }

    // thay đổi lịch theo tháng năm
    public void displayDate() {
        for (int i = 7; i < button.length; i++)
            button[i].setText("");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
        Calendar cal = Calendar.getInstance();
        int y = (int) spinYear.getValue();
        if (y != year)
            year = y;

        cal.set(year, month, 1);

        lbMonth.setText(sdfMonth.format(cal.getTime()));
        spinYear.setValue(year);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < 6 + dayOfWeek; i++) {
            button[i].setEnabled(false);
        }
        for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
            button[i].setText("" + day);
            button[i].setEnabled(true);
        }
        for (int i = 6 + dayOfWeek + daysInMonth; i < button.length; i++) {
            button[i].setEnabled(false);
        }
    }

    // hiện ngày hiện tại
    public void showToDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        lbNgayThang.setText(sdf.format(cal.getTime()));
        sdf = new SimpleDateFormat("E");

        lbThu.setText(sdf.format(cal.getTime()) + ",");
        sdf = new SimpleDateFormat("yyyy");

        lbYear.setText(sdf.format(cal.getTime()));
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        lbToDay.setText("Today: " + sdf.format(cal.getTime()));
    }

    // hiện ngày đã chọn
    public void displayShowDate(int day) {
        Calendar cal = Calendar.getInstance();
        int y = (int) spinYear.getValue();
        if (y != year)
            year = y;
        cal.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        lbNgayThang.setText(sdf.format(cal.getTime()));

        sdf = new SimpleDateFormat("E");
        lbThu.setText(sdf.format(cal.getTime()) + ",");

        sdf = new SimpleDateFormat("yyyy");
        lbYear.setText(sdf.format(cal.getTime()));
    }

    // lấy ngày chọn từ lịch
    private String getPickedDate() {
        if (day.equals(""))
            return day;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }

    // lấy ngày chọn từ lịch
    public Date getDate() {
        if (day.equals(""))
            day = "0";
        Calendar cal = Calendar.getInstance();
        int date = Integer.parseInt(day);
        cal.set(year, month, date);
        return (Date) cal.getTime();
    }

    // lấy ngày hiện tại dạng string
    public static String getToDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(cal.getTimeInMillis());
    }

    // trả về ngày được chọn
    public String getValueString() {
        String re = "";
        if (check == 1)
            re = getPickedDate();
        return re;
    }
}
