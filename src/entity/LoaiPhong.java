package entity;

public class LoaiPhong {
    private int maLoaiPhong;
    private String tenLoaiPhong;
    private double donGia;

    public int getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(int maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia < 0)
            donGia = 0;
        this.donGia = donGia;
    }

    public LoaiPhong(int maLoaiPhong, String tenLoaiPhong, double donGia) {
        setMaLoaiPhong(maLoaiPhong);
        setTenLoaiPhong(tenLoaiPhong);
        setDonGia(donGia);
    }

    public LoaiPhong() {
        this(0, "Chưa cập nhật", 0);
    }
}
