package DAO;

import java.sql.*;

import connectDB.ConnectDB;
import entity.HoaDon;

public class HoaDonDAO {
    private static HoaDonDAO instance = new HoaDonDAO();
    private static ConnectDB db = ConnectDB.getInstance();

    public static HoaDonDAO getInstance() {
        return instance;
    }

    public int getUncheckHoaDonByMaPhong(int maPhong) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int id = -1;
        try {
            db.connect();
            con = ConnectDB.getConnection();

            String sql = "SELECT * FROM dbo.HoaDon hd WHERE hd.maPhong = ? and hd.tinhTrang = 0 ";
            stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, maPhong);

            rs = stmt.executeQuery();
            rs.last();
            int count = 0;
            count = rs.getRow();
            rs.beforeFirst();

            if (count > 0) {
                HoaDon hd = new HoaDon(rs);
                // Date ngayNhanPhong = rs.getDate("ngayNhanPhong");
                // if (!ngayNhanPhong.toString().equals(""))
                // {
                id = hd.getMaHoaDon();
                // }
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
        return id;
    }
}
