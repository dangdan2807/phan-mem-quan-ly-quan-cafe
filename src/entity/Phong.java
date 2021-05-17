package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Phong {
    private int maPhong;
    private String tenPhong;
    private int sucChua;
    private int soGiuong;
    private String tinhTrang;
    private int maLoaiPhong;

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(int maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public Phong(int maPhong, String tenPhong, int sucChua, int soGiuong, String tinhTrang, int maLoaiPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.sucChua = sucChua;
        this.soGiuong = soGiuong;
        this.tinhTrang = tinhTrang;
        this.maLoaiPhong = maLoaiPhong;
    }

    public Phong(ResultSet rs) throws SQLException {
        this(rs.getInt("maPhong"), rs.getString("tenPhong"), rs.getInt("sucChua"), rs.getInt("soGiuong"),
                rs.getString("tinhTrang"), rs.getInt("maLoaiPhong"));
    }
}
