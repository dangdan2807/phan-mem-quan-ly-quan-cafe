package app;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class DialogDatePicker extends JDialog implements ActionListener, ChangeListener {
    private int width = 450, heightPn = 210, widthPn = width - 20;
    private JButton[] button = new JButton[49];
    private String day = "";
    private int month = Calendar.getInstance().get(Calendar.MONTH);
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private JLabel lbToDay, lbMonth;
    private JButton btnPre, btnNext;
    private SpinnerNumberModel spinYearModel;
    private JSpinner spinYear;

    public DialogDatePicker() {
        setTitle("Chọn ngày");
        setSize(width, heightPn);
        setResizable(false);
        setLocationRelativeTo(null);

        createFormDatePicker();
    }

    public void createFormDatePicker() {
        JPanel pnMain = new JPanel();
        pnMain.setBounds(0, 0, widthPn, heightPn);
        pnMain.setLayout(null);

        String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
        JPanel pnDateTable = new JPanel(new GridLayout(7, 7));

        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.WHITE);
            button[x].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    day = button[selection].getActionCommand();
                    dispose();
                }
            });
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.red);
            }
            button[x].setPreferredSize(new Dimension(20, 20));
            button[x].setSize(new Dimension(20, 20));
            pnDateTable.add(button[x]);
        }

        JPanel pnBtn = new JPanel();
        pnBtn.setLayout(null);

        btnPre = new JButton("<");
        btnNext = new JButton(">");

        spinYearModel = new SpinnerNumberModel(year, 1900, null, 1);
        spinYear = new JSpinner(spinYearModel);

        lbToDay = new JLabel("");
        lbMonth = new JLabel("Tháng x");

        JPanel pnToDay = new JPanel();

        int h = 25;
        btnPre.setBounds(0, 0, 41, h);
        lbMonth.setBounds(130, 0, 100, h);
        spinYear.setBounds(230, 0, 70, h);
        btnNext.setBounds(widthPn - 45, 0, 41, h);

        pnBtn.setBounds(5, 0, widthPn, 30);
        pnDateTable.setBounds(5, 30, widthPn, 120);
        pnToDay.setBounds(5, 150, 430, 30);

        pnToDay.add(lbToDay);

        pnBtn.add(btnPre);
        pnBtn.add(lbMonth);
        pnBtn.add(spinYear);
        pnBtn.add(btnNext);

        pnMain.add(pnBtn);
        pnMain.add(pnDateTable);
        pnMain.add(pnToDay);

        displayDate();
        getContentPane().add(pnMain);

        btnNext.addActionListener(this);
        btnPre.addActionListener(this);
        spinYear.addChangeListener(this);
    }

    public static void main(String[] args) {
        new DialogDatePicker().setVisible(true);
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
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object o = e.getSource();
        if (o.equals(spinYear)) {
            displayDate();
        }
    }

    public void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
        SimpleDateFormat sdfToDay = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        int y = (int) spinYear.getValue();
        if (y != year)
            year = y;
        cal.set(year, month, 1);

        long today = Calendar.getInstance().getTimeInMillis();
        lbToDay.setText("Today: " + sdfToDay.format(today));
        lbMonth.setText(sdfMonth.format(cal.getTime()));
        String year = sdfYear.format(cal.getTime());
        spinYear.setValue(Integer.parseInt(year));

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
            button[x].setText("" + day);
        }
    }

    public String setPickedDate() {
        if (day.equals(""))
            return day;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }

    public static String getToDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(cal.getTimeInMillis());
    }
}
