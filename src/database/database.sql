CREATE DATABASE QuanLyKhachSan
GO

USE QuanLyKhachSan
GO

-- danh mục Phòng
-- Phòng
-- Account
-- danh mục dịch vụ
-- Dịch Vụ
-- Hóa đơn
-- Chi tiết hóa đơn

CREATE TABLE DanhMucPhong
(
    maDanhMucPhong INT IDENTITY PRIMARY KEY,
    tenLoaiPhong NVARCHAR(100) NOT NULL DEFAULT N'Chưa đặt tên',
    donGia FLOAT NOT NULL
)
GO

CREATE TABLE Phong
(
    maPhong INT IDENTITY PRIMARY KEY,
    tenPhong NVARCHAR(100) NOT NULL DEFAULT N'Phòng chưa đặt tên',
    sucChua INT NOT NULL DEFAULT(1),
    soGiuong INT NOT NULL DEFAULT(1),
    -- trống || có người
    tinhTrang NVARCHAR(100) NOT NULL DEFAULT N'Trống',
    maDanhMucPhong INT NOT NULL,

    FOREIGN KEY (maDanhMucPhong) REFERENCES dbo.DanhMucPhong (maDanhMucPhong)
)
GO

CREATE TABLE Account
(
    UserName NVARCHAR(100) PRIMARY KEY,
    displayName NVARCHAR(100) NOT NULL DEFAULT N'Chưa đặt tên',
    PassWord VARCHAR(1000) NOT NULL DEFAULT 123456,
    -- 1. admin || 0. nhân viên
    loaiTK INT NOT NULL DEFAULT 0
)
GO

CREATE TABLE DanhMucDichVu
(
    maDanhMucDV INT IDENTITY PRIMARY KEY,
    tenDanhMucDV NVARCHAR(100) NOT NULL DEFAULT N'Chưa đặt tên',
)
GO

CREATE TABLE DichVu
(
    maDV INT IDENTITY PRIMARY KEY,
    tenDV NVARCHAR(100) NOT NULL DEFAULT N'chưa đặt tên',
    maDanhMucDV INT NOT NULL,
    donGia FLOAT NOT NULL DEFAULT 0,

    FOREIGN KEY (maDanhMucDV) REFERENCES dbo.DanhMucDichVu (maDanhMucDV)
)
GO

CREATE TABLE HoaDon
(
    maHoaDon INT IDENTITY PRIMARY KEY,
    maPhong INT NOT NULL,
    ngayNhanPhong DATETIME DEFAULT getdate(),
    ngayTraPhong DATETIME NOT NULL,
    -- 1. đã thanh toán || 0. chưa thanh toán
    tinhTrang INT NOT NULL DEFAULT 0,

    FOREIGN KEY (maPhong) REFERENCES dbo.Phong (maPhong)
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