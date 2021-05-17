package entity;

import java.sql.*;

public class ChiTietHoaDon {
    private int maCTHoaDon, maHoaDon, maDV, soLuong;
    private Date ngayGioDat;

    public int getmaCTHoaDon() {
        return maCTHoaDon;
    }

    public void setmaCTHoaDon(int maCTHoaDon) {
        this.maCTHoaDon = maCTHoaDon;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayGioDat() {
        return ngayGioDat;
    }

    public void setNgayGioDat(Date ngayGioDat) {
        this.ngayGioDat = ngayGioDat;
    }

    public ChiTietHoaDon(int maCTHoaDon, int maHoaDon, int maDV, int soLuong, Date ngayGioDat) {
        this.maCTHoaDon = maCTHoaDon;
        this.maHoaDon = maHoaDon;
        this.maDV = maDV;
        this.soLuong = soLuong;
        this.ngayGioDat = ngayGioDat;
    }

    public ChiTietHoaDon(ResultSet rs) throws SQLException {
        this(rs.getInt("maCTHoaDon"), rs.getInt("maHoaDon"), rs.getInt("maDV"), rs.getInt("soLuong"),
                rs.getDate("ngayGioDat"));
    }
}
