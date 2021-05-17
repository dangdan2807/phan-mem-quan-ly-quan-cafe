package entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDon {
    private int maHoaDon, maPhong, maKH, maNV;
    private Date ngayNhanPhong, ngayTraPhong;
    private int tinhTrang;

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public Date getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(Date ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public Date getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(Date ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public HoaDon(int maHoaDon, int maPhong, int maKH, int maNV, Date ngayNhanPhong, Date ngayTraPhong, int tinhTrang) {
        this.maHoaDon = maHoaDon;
        this.maPhong = maPhong;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        this.tinhTrang = tinhTrang;
    }

    public HoaDon(ResultSet rs) throws SQLException {
        this(rs.getInt("maHoaDon"), rs.getInt("maPhong"), rs.getInt("maKH"), rs.getInt("maNV"),
                rs.getDate("ngayNhanPhong"), rs.getDate("ngayTraPhong"), rs.getInt("tinhTrang"));
    }

}
