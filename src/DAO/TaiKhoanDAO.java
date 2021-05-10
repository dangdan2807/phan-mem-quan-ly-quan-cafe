package DAO;

import java.sql.*;
import java.util.*;

import connectDB.*;

public class TaiKhoanDAO {
    private static TaiKhoanDAO instance = new TaiKhoanDAO();

    public static TaiKhoanDAO getInstance() {
        return instance;
    }

    public static ArrayList<Vector<String>> getAllAccounts() {
        ArrayList<Vector<String>> accountList = new ArrayList<Vector<String>>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();

            String sql = "select tk.userName, nv.tenNV, tk.maNV, tk.loaiTK from dbo.TaiKhoan tk join dbo.NhanVien nv on tk.maNV = nv.maNV";
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString(1);
                String tenNV = rs.getString(2);
                String maNV = rs.getString(3);
                String loaiTK = rs.getString(4);

                Vector<String> tk = new Vector<String>();
                tk.add(userName);
                tk.add(tenNV);
                tk.add(maNV);
                tk.add(loaiTK);
                accountList.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.getInstance().disconnect();
        return accountList;
    }

    public static boolean Login(String username, String password) {
        int count = 0;
        ConnectDB db = ConnectDB.getInstance();
        try {
            db.connect();
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT * FROM dbo.TaiKhoan as tk" + " WHERE tk.userName = '" + username
                    + "'AND tk.passWord = '" + password + "'";
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            count = rs.getRow();
            // cách khác
            // ResultSetMetaData rsMetaData = rs.getMetaData();
            // count = rsMetaData.getColumnCount();
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnect();
        return count > 0;
    }
}
