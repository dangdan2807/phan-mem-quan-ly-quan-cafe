package entity;

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
        if (sucChua <= 0)
            sucChua = 0;
        this.sucChua = sucChua;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        if (soGiuong <= 0)
            soGiuong = 1;
        this.soGiuong = soGiuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        if (!(tinhTrang.equalsIgnoreCase("Trống")) || !(tinhTrang.equalsIgnoreCase("Đã cho thuê")))
            tinhTrang = "Trống";
        this.tinhTrang = tinhTrang;
    }

    public int getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(int maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public Phong(int maPhong, String tenPhong, int sucChua, int soGiuong, String tinhTrang, int maLoaiPhong) {
        setMaPhong(maPhong);
        setTenPhong(tenPhong);
        setSoGiuong(soGiuong);
        setSucChua(sucChua);
        setTinhTrang(tinhTrang);
        setMaLoaiPhong(maLoaiPhong);
    }

    public Phong() {
        this(0, "Chưa cập nhật", 1, 1, "Trống", 0);
    }
}
