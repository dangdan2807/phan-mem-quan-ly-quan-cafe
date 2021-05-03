CREATE DATABASE QuanLyKhachSan
GO

USE QuanLyKhachSan
GO

-- danh mục Phòng
-- Phòng
-- Tài khoản
-- danh mục dịch vụ
-- Dịch Vụ
-- khách hàng
-- Nhân viên
-- Hóa đơn
-- Chi tiết hóa đơn

CREATE TABLE DanhMucPhong
(
    maDanhMucPhong INT IDENTITY PRIMARY KEY,
    tenLoaiPhong NVARCHAR(100) NOT NULL DEFAULT N'Chưa cập nhật',
    donGia FLOAT NOT NULL
)
GO

CREATE TABLE Phong
(
    maPhong INT IDENTITY PRIMARY KEY,
    tenPhong NVARCHAR(100) NOT NULL DEFAULT N'Chưa cập nhật',
    sucChua INT NOT NULL DEFAULT(1),
    soGiuong INT NOT NULL DEFAULT(1),
    -- trống || có người
    tinhTrang NVARCHAR(100) NOT NULL DEFAULT N'Trống',
    maDanhMucPhong INT NOT NULL,

    FOREIGN KEY (maDanhMucPhong) REFERENCES dbo.DanhMucPhong (maDanhMucPhong)
)
GO

CREATE TABLE KhachHang
(
    maKH INT IDENTITY PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL DEFAULT N'Chưa cập nhật',
    cccd NVARCHAR(100) NOT NULL DEFAULT N'Chưa cập nhật',
    -- 1. khách việt nam || 0. khách nước ngoài
    loaiKhach INT NOT NULL DEFAULT 1
)
GO

CREATE TABLE NhanVien
(
    maNV INT IDENTITY PRIMARY KEY,
    tenNV NVARCHAR(100) NOT NULL DEFAULT N'Chưa cập nhật',
    cccd NVARCHAR(100) NOT NULL DEFAULT N'Chưa cập nhật',
    email NVARCHAR(100) DEFAULT N'Chưa cập nhật',
    sdt NVARCHAR(100) DEFAULT N'Chưa cập nhật',
)
GO

CREATE TABLE TaiKhoan
(
    userName NVARCHAR(100) PRIMARY KEY,
    PassWord VARCHAR(1000) NOT NULL DEFAULT 123456,
    maNV INT NOT NULL,
    -- 1. admin || 0. nhân viên
    loaiTK INT NOT NULL DEFAULT 0,

    FOREIGN KEY (maNV) REFERENCES dbo.NhanVien (maNV)
)
GO

CREATE TABLE DanhMucDichVu
(
    maDanhMucDV INT IDENTITY PRIMARY KEY,
    tenDanhMucDV NVARCHAR(100) NOT NULL DEFAULT N'Chưa cập nhật',
)
GO

CREATE TABLE DichVu
(
    maDV INT IDENTITY PRIMARY KEY,
    tenDV NVARCHAR(100) NOT NULL DEFAULT N'Chưa cập nhật',
    maDanhMucDV INT NOT NULL,
    donGia FLOAT NOT NULL DEFAULT 0,

    FOREIGN KEY (maDanhMucDV) REFERENCES dbo.DanhMucDichVu (maDanhMucDV)
)
GO

CREATE TABLE HoaDon
(
    maHoaDon INT IDENTITY PRIMARY KEY,
    maPhong INT NOT NULL,
    maKH INT NOT NULL,
    maNV INT NOT NULL,
    ngayNhanPhong DATETIME DEFAULT getdate(),
    ngayTraPhong DATETIME NOT NULL,
    -- 1. đã thanh toán || 0. chưa thanh toán
    tinhTrang INT NOT NULL DEFAULT 0,

    FOREIGN KEY (maPhong) REFERENCES dbo.Phong (maPhong),
    FOREIGN KEY (maKH) REFERENCES dbo.KhachHang (maKH),
    FOREIGN KEY (maNV) REFERENCES dbo.NhanVien (maNV)
)
GO

CREATE TABLE ChiTietHoaDon
(
    maCTHoaDon INT IDENTITY NOT NULL,
    maHoaDon INT NOT NULL,
    maDV INT,
    soLuong INT NOT NULL DEFAULT 0,

    FOREIGN KEY (maHoaDon) REFERENCES dbo.HoaDon (maHoaDon),
    FOREIGN KEY (maDV) REFERENCES dbo.DichVu (maDV)
)
GO
-- DROP DATABASE QuanLyKhachSan