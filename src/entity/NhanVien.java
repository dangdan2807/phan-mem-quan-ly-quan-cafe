package entity;

public class NhanVien {
    private int maNV;
    private String tenNV;
    private String cccd;
    private String email;
    private String sdt;
    private double luong;

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        if (luong <= 0)
            luong = 0;
        this.luong = luong;
    }

    public NhanVien(int maNV, String tenNV, String cccd, String email, String sdt, double luong) {
        setMaNV(maNV);
        setTenNV(tenNV);
        setCccd(cccd);
        setEmail(email);
        setSdt(sdt);
        setLuong(luong);
    }

    public NhanVien() {
        this(0, "Chưa cập nhật", "Chưa cập nhật", "Chưa cập nhật", "Chưa cập nhật", 0);
    }

}
