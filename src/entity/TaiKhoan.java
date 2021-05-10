package entity;

public class TaiKhoan {
    private String userName;
    private String passWord;
    private int maNV;
    private int loaiTK;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(int loaiTK) {
        if (loaiTK != 1 || loaiTK != 0)
            loaiTK = 0;
        this.loaiTK = loaiTK;
    }

    public TaiKhoan(String userName, String passWord, int maNV, int loaiTK) {
        setUserName(userName);
        setPassWord(passWord);
        setMaNV(maNV);
        setLoaiTK(loaiTK);
    }

    public TaiKhoan() {
        this("NV00", "NV00", 0, 0);
    }
}
