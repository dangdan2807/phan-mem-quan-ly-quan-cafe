package DAO;

import java.sql.*;
import java.util.*;

import connectDB.ConnectDB;

public class TaiKhoanDAO {
    private static TaiKhoanDAO instance = new TaiKhoanDAO();
    private static ConnectDB db = ConnectDB.getInstance();

    public static TaiKhoanDAO getInstance() {
        return instance;
    }

    public ArrayList<Vector<String>> getAllAccounts() {
        ArrayList<Vector<String>> accountList = new ArrayList<Vector<String>>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "EXEC UDP_getAccountList";
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString("userName");
                String tenNV = rs.getString("tenNV");
                String maNV = rs.getString("maNV");
                String loaiTK = rs.getString("loaiTK");
                Vector<String> tk = new Vector<String>();
                tk.add(userName);
                tk.add(tenNV);
                tk.add(maNV);
                tk.add(loaiTK);
                accountList.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accountList;
    }

    public static boolean Login(String userName, String passWord) {
        int count = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "EXEC UDP_Login ? , ?";
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, userName);
            stmt.setString(2, passWord);

            rs = stmt.executeQuery();
            // đến số dòng được trả về
            rs.last();
            count = rs.getRow();
            rs.beforeFirst();
            // cách khác
            // ResultSetMetaData rsMetaData = rs.getMetaData();
            // count = rsMetaData.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count > 0;
    }
}
