package entity;

import java.sql.Date;

public class HoaDon {
    private int maHoaDon;
    private int maPhong;
    private int maKH;
    private int maNV;
    private Date ngayNhanPhong;
    private Date ngayTraPhong;
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
        if (tinhTrang < 0 || tinhTrang > 3)
            tinhTrang = 0;
        this.tinhTrang = tinhTrang;
    }

    public HoaDon(int maHoaDon, int maPhong, int maKH, int maNV, Date ngayNhanPhong, Date ngayTraPhong, int tinhTrang) {
        setMaHoaDon(maHoaDon);
        setMaPhong(maPhong);
        setMaKH(maKH);
        setMaNV(maNV);
        setNgayNhanPhong(ngayNhanPhong);
        setNgayTraPhong(ngayTraPhong);
        setTinhTrang(tinhTrang);
    }

    public HoaDon() {
        this(0, 0, 0, 0, new Date(System.currentTimeMillis()), null, 0);
    }
}
