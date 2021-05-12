package DAO;

import java.sql.*;
import java.util.*;

import connectDB.ConnectDB;
import entity.Phong;

public class PhongDAO {
    private static TaiKhoanDAO instance = new TaiKhoanDAO();
    private static ConnectDB db = ConnectDB.getInstance();
    public static int phongWidth = 95;
    public static int phongHeight = 95;

    public static TaiKhoanDAO getInstance() {
        return instance;
    }

    public ArrayList<Phong> getAllPhong() {
        ArrayList<Phong> PhongList = new ArrayList<Phong>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "EXEC UDP_getPhongList";
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int maPhong = rs.getInt("maPhong");
                String tenPhong = rs.getString("tenPhong");
                int sucChua = rs.getInt("sucChua");
                int soGiuong = rs.getInt("soGiuong");
                String tinhTrang = rs.getString("tinhTrang");
                int maLoaiPhong = rs.getInt("maLoaiPhong");
                Phong p = new Phong(maPhong, tenPhong, sucChua, soGiuong, tinhTrang, maLoaiPhong);
                PhongList.add(p);
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
        return PhongList;
    }
}
