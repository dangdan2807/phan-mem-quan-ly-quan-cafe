package entity;

public class ChiTietHoaDon {
    private int maCTHoaDon;
    private int maHoaDon;
    private int maDV;
    private int soLuong;

    public int getMaCTHoaDon() {
        return maCTHoaDon;
    }

    public void setMaCTHoaDon(int maCTHoaDon) {
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
        if(soLuong <= 0)
            soLuong = 1;
        this.soLuong = soLuong;
    }

    public ChiTietHoaDon(int maCTHoaDon, int maHoaDon, int maDV, int soLuong) {
        setMaCTHoaDon(maCTHoaDon);
        setMaHoaDon(maHoaDon);
        setMaDV(maDV);
        setSoLuong(soLuong);
    }

    public ChiTietHoaDon() {
        this(0, 0, 0, 1);
    }
}
