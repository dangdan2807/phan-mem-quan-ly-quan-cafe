package entity;

public class KhachHang {
    private int maKH;
    private String tenKH;
    private String cccd;
    private int loaiKhach;

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public int getLoaiKhach() {
        return loaiKhach;
    }

    public void setLoaiKhach(int loaiKhach) {
        this.loaiKhach = loaiKhach;
    }

    public KhachHang(int maKH, String tenKH, String cccd, int loaiKhach) {
        setMaKH(maKH);
        setTenKH(tenKH);
        setCccd(cccd);
        setLoaiKhach(loaiKhach);
    }

    public KhachHang() {
        this(0, "Chưa cập nhật", "Chưa cập nhật", 0);
    }
}
