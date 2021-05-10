package entity;

public class DichVu {
    private int maDV;
    private String tenDV;
    private int maLoaiDV;
    private double donGia;

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getMaLoaiDV() {
        return maLoaiDV;
    }

    public void setMaLoaiDV(int maLoaiDV) {
        this.maLoaiDV = maLoaiDV;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if(donGia < 0.0)
            donGia = 0.0;
        this.donGia = donGia;
    }

    public DichVu(int maDV, String tenDV, int maLoaiDV, double donGia) {
        setMaDV(maDV);
        setTenDV(tenDV);
        setMaLoaiDV(maLoaiDV);
        setDonGia(donGia);
    }

    public DichVu() {
        this(0, "Chưa cập nhật", 0, 0);
    }

}
