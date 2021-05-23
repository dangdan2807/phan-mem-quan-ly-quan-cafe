package UI.PanelCustom;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class pnDanhMuc extends JPanel implements ActionListener, MouseListener {
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private DefaultTableModel modelTable;
    private JTable table;
    private JButton btnThem, btnXoa, btnXoaTrang, btnSua, btnXem;
    private JTextField txtMa, txtTen;

    public pnDanhMuc() {
        setSize(760, 440);
        setLayout(new BorderLayout(0, 0));

        pnMain = new JPanel();
        pnMain.setLayout(null);
        this.add(pnMain);

        JPanel pnBtn = new JPanel();
        pnBtn.setLayout(null);
        pnBtn.setBounds(0, 0, 467, 36);
        pnMain.add(pnBtn);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(0, 0, 75, 36);
        pnBtn.add(btnThem);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(87, 0, 75, 36);
        pnBtn.add(btnXoa);

        btnXoaTrang = new JButton("Xóa Trắng");
        btnXoaTrang.setBounds(174, 0, 117, 36);
        pnBtn.add(btnXoaTrang);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(303, 0, 75, 36);
        pnBtn.add(btnSua);

        btnXem = new JButton("Xem");
        btnXem.setBounds(390, 0, 75, 36);
        pnBtn.add(btnXem);

        JPanel pnListView = new JPanel();
        pnListView.setBounds(0, 37, 467, 392);
        pnMain.add(pnListView);
        pnListView.setLayout(new BorderLayout(0, 0));

        JScrollPane scp = new JScrollPane();
        pnListView.add(scp);

        String[] cols = { "Mã danh mục", "Tên danh mục" };
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa không cho người dùng nhập trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTable);
        scp.setViewportView(table);

        this.add(pnMain);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(new TitledBorder(null, "Thông tin danh mục "));
        pnInfo.setLayout(null);
        pnInfo.setBounds(467, 37, 282, 392);
        pnMain.add(pnInfo);

        JLabel lbMa = new JLabel("Mã loại: ");
        lbMa.setBounds(10, 17, 88, 20);
        pnInfo.add(lbMa);

        txtMa = new JTextField();
        txtMa.setEditable(false);
        txtMa.setBounds(97, 17, 175, 20);
        pnInfo.add(txtMa);
        txtMa.setColumns(10);

        JLabel lbTen = new JLabel("Tên loại: ");
        lbTen.setBounds(10, 48, 88, 20);
        pnInfo.add(lbTen);

        txtTen = new JTextField();
        txtTen.setBounds(97, 48, 175, 20);
        pnInfo.add(txtTen);
        txtTen.setColumns(10);

        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnSua.addActionListener(this);
        btnXem.addActionListener(this);

        table.addMouseListener(this);
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
}
