package app;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.util.*;
import DAO.*;

public class pnTaiKhoan extends JPanel implements ActionListener, MouseListener{

    private kPnDataListView pnTaiKhoan;
    private JPanel pnMain;
    private JButton btnTimTK;
    private JTextField txtTimTK, txtUserName, txtMaNVTK, txtTenNVTK;
    private JPasswordField txtPassWord;
    private JComboBox<String> cboLoaiTK;
    private JTable tableTK;
    int widthPn = 770, heightPn = 500;
    TaiKhoanDAO tkDao = new TaiKhoanDAO();

    public pnTaiKhoan() {
        setLayout(null);
        setSize(widthPn, heightPn);
        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, widthPn, heightPn);
        this.add(pnMain);

        String[] cols = { "Username", "Tên nhân viên", "Mã nhân viên", "Loại tài khoản" };
        pnTaiKhoan = new kPnDataListView(cols);
        pnTaiKhoan.setBounds(0, 0, pnTaiKhoan.getWidth(), pnTaiKhoan.getHeight());
        pnMain.add(pnTaiKhoan);

        JPanel pnSearchTK = new JPanel();
        pnSearchTK.setLayout(null);
        pnSearchTK.setBounds(467, 0, 282, 36);
        pnMain.add(pnSearchTK);

        btnTimTK = new JButton("Tìm");
        btnTimTK.setBounds(192, 0, 89, 36);
        pnSearchTK.add(btnTimTK);

        txtTimTK = new JTextField();
        txtTimTK.setBounds(2, 15, 185, 20);
        pnSearchTK.add(txtTimTK);
        txtTimTK.setColumns(10);

        JLabel lbTimTK = new JLabel("Tên nhân viên: ");
        lbTimTK.setBounds(2, 0, 100, 14);
        pnSearchTK.add(lbTimTK);

        JPanel pnInfoTK = new JPanel();
        pnInfoTK.setBorder(new TitledBorder(null, "Thông tin tài khoản"));
        pnInfoTK.setLayout(null);
        pnInfoTK.setBounds(467, 37, 282, 393);
        pnMain.add(pnInfoTK);

        JLabel lbUserName = new JLabel("Username:");
        lbUserName.setBounds(10, 17, 92, 20);
        pnInfoTK.add(lbUserName);

        txtUserName = new JTextField();
        txtUserName.setBounds(100, 17, 172, 20);
        pnInfoTK.add(txtUserName);
        txtUserName.setColumns(10);

        JLabel lbPassWord = new JLabel("Password:");
        lbPassWord.setBounds(10, 48, 92, 20);
        pnInfoTK.add(lbPassWord);

        txtPassWord = new JPasswordField();
        txtPassWord.setBounds(100, 48, 172, 20);
        pnInfoTK.add(txtPassWord);
        txtPassWord.setColumns(10);

        JLabel lbMaNVTK = new JLabel("Mã NV:");
        lbMaNVTK.setBounds(12, 80, 92, 16);
        pnInfoTK.add(lbMaNVTK);

        txtMaNVTK = new JTextField();
        txtMaNVTK.setBounds(100, 78, 172, 20);
        pnInfoTK.add(txtMaNVTK);
        txtMaNVTK.setColumns(10);

        JLabel lbLoaiTK = new JLabel("Loại TK:");
        lbLoaiTK.setBounds(10, 136, 92, 16);
        pnInfoTK.add(lbLoaiTK);

        cboLoaiTK = new JComboBox<String>();
        cboLoaiTK.setBounds(100, 134, 172, 20);
        cboLoaiTK.addItem("Nhân viên");
        cboLoaiTK.addItem("Admin");
        pnInfoTK.add(cboLoaiTK);

        JLabel lbTenNVTK = new JLabel("Tên NV:");
        lbTenNVTK.setBounds(10, 108, 55, 16);
        pnInfoTK.add(lbTenNVTK);

        txtTenNVTK = new JTextField();
        txtTenNVTK.setBounds(100, 106, 172, 20);
        pnInfoTK.add(txtTenNVTK);
        txtTenNVTK.setColumns(10);

        JButton btnResetPass = new JButton("Đặt lại mật khẩu");
        btnResetPass.setBounds(134, 166, 136, 26);
        pnInfoTK.add(btnResetPass);

        docDSTaiKhoan();
        tableTK = pnTaiKhoan.getTable();
        tableTK.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    private void docDSTaiKhoan() {
        ArrayList<Vector<String>> accountList = tkDao.getAllAccounts();
        for (int i = 0; i < accountList.size(); i++) {
            Vector<String> tk = accountList.get(i);
            DefaultTableModel modelTK = pnTaiKhoan.getDefaultTableModel();
            String loaiTK = Integer.parseInt(tk.get(3)) == 1 ? "Admin" : "Nhân viên";
            modelTK.addRow(new Object[] { tk.get(0), tk.get(1), tk.get(2), loaiTK });
        }
    }
}
