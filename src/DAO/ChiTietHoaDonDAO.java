package DAO;

import java.util.*;
import java.sql.*;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;

public class ChiTietHoaDonDAO {
    private static ChiTietHoaDonDAO instance = new ChiTietHoaDonDAO();
    private static ConnectDB db = ConnectDB.getInstance();

    public static ChiTietHoaDonDAO getInstance() {
        return instance;
    }

    public ArrayList<ChiTietHoaDon> getListChiTietHoaDonByMaCTHoaDon(int maHD) {
        ArrayList<ChiTietHoaDon> dataList = new ArrayList<ChiTietHoaDon>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            db.connect();
            con = ConnectDB.getConnection();

            String sql = "SELECT * FROM dbo.ChiTietHoaDon ct WHERE ct.maCTHoaDon = ?";
            stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, maHD);

            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon(rs);
                dataList.add(cthd);
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
        return dataList;
    }
}
