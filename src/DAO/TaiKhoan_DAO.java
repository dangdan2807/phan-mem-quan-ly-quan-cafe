package DAO;

import java.sql.*;
import java.util.*;

import connectDB.*;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
    public static ArrayList<TaiKhoan> getAllAccounts() {
        ArrayList<TaiKhoan> accountList = new ArrayList<TaiKhoan>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "SELECT * FROM dbo.TaiKhoan";
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString(1);
                String passWord = rs.getString(2);
                int maNV = rs.getInt(3);
                int loaiTK = rs.getInt(4);
                TaiKhoan tk = new TaiKhoan(userName, passWord, maNV, loaiTK);
                accountList.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }
}
