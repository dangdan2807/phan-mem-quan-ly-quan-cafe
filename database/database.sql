USE master
GO

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

CREATE TABLE LoaiPhong
(
    maLoaiPhong INT IDENTITY PRIMARY KEY,
    tenLoaiPhong NVARCHAR(100) NOT NULL DEFAULT (N'Chưa cập nhật'),
    donGiaGio MONEY NOT NULL DEFAULT(0),
    donGiaNgay MONEY NOT NULL DEFAULT(0),
)
GO

CREATE TABLE Phong
(
    maPhong INT IDENTITY PRIMARY KEY,
    tenPhong NVARCHAR(100) NOT NULL DEFAULT (N'Chưa cập nhật'),
    sucChua INT NOT NULL DEFAULT(1),
    soGiuong INT NOT NULL DEFAULT(1),
    -- trống || đã cho thuê
    tinhTrang NVARCHAR(100) NOT NULL DEFAULT (N'Trống'),
    maLoaiPhong INT NOT NULL,

    FOREIGN KEY (maLoaiPhong) REFERENCES dbo.LoaiPhong (maLoaiPhong)
)
GO

CREATE TABLE KhachHang
(
    maKH INT IDENTITY PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL DEFAULT (N'Chưa cập nhật'),
    cccd NVARCHAR(100) NOT NULL DEFAULT (N'Chưa cập nhật'),
    -- 1. khách việt nam || 0. khách nước ngoài
    loaiKhach INT NOT NULL DEFAULT(1)
)
GO

CREATE TABLE NhanVien
(
    maNV INT IDENTITY PRIMARY KEY,
    tenNV NVARCHAR(100) NOT NULL DEFAULT (N'Chưa cập nhật'),
    cccd NVARCHAR(100) NOT NULL DEFAULT (N'Chưa cập nhật'),
    email NVARCHAR(100) DEFAULT (N'Chưa cập nhật'),
    sdt NVARCHAR(100) DEFAULT (N'Chưa cập nhật'),
    luong MONEY DEFAULT (0)
)
GO

CREATE TABLE TaiKhoan
(
    userName NVARCHAR(100) PRIMARY KEY,
    passWord NVARCHAR(1000) NOT NULL DEFAULT (123456),
    maNV INT NOT NULL,
    -- 1. admin || 0. nhân viên
    loaiTK INT NOT NULL DEFAULT (0),

    FOREIGN KEY (maNV) REFERENCES dbo.NhanVien (maNV)
)
GO

CREATE TABLE LoaiDichVu
(
    maLoaiDV INT IDENTITY PRIMARY KEY,
    tenLoaiDV NVARCHAR(100) NOT NULL DEFAULT (N'Chưa cập nhật'),
)
GO

CREATE TABLE DichVu
(
    maDV INT IDENTITY PRIMARY KEY,
    tenDV NVARCHAR(100) NOT NULL DEFAULT (N'Chưa cập nhật'),
    maLoaiDV INT NOT NULL,
    donGia FLOAT NOT NULL DEFAULT (0),

    FOREIGN KEY (maLoaiDV) REFERENCES dbo.LoaiDichVu (maLoaiDV)
)
GO

CREATE TABLE HoaDon
(
    maHoaDon INT IDENTITY PRIMARY KEY,
    maPhong INT NOT NULL,
    maKH INT NOT NULL,
    maNV INT NOT NULL,
    ngayNhanPhong DATETIME DEFAULT (getdate()),
    ngayTraPhong DATETIME NOT NULL,
    --  0. chưa thanh toán ||  1. đã thanh toán || 2. Chưa nhận phòng
    tinhTrang INT NOT NULL DEFAULT (0),

    FOREIGN KEY (maPhong) REFERENCES dbo.Phong (maPhong),
    FOREIGN KEY (maKH) REFERENCES dbo.KhachHang (maKH),
    FOREIGN KEY (maNV) REFERENCES dbo.NhanVien (maNV)
)
GO

CREATE TABLE ChiTietHoaDon
(
    maCTHoaDon INT IDENTITY NOT NULL,
    maHoaDon INT NOT NULL,
    maDV INT NOT NULL,
    soLuong INT NOT NULL DEFAULT (0),


    FOREIGN KEY (maHoaDon) REFERENCES dbo.HoaDon (maHoaDon),
    FOREIGN KEY (maDV) REFERENCES dbo.DichVu (maDV)
)
GO

-- Insert rows into table 'dbo.NhanVien'
INSERT INTO dbo.NhanVien
    (tenNV, cccd, email, sdt, luong)
-- add more rows here
VALUES(N'Admin', N'123123123', N'admin@email.com', N'123123123', 0)
GO

-- Insert rows into table 'dbo.TaiKhoan'
INSERT INTO dbo.TaiKhoan
-- add more rows here
VALUES('admin', 'admin', 1, 1)
GO

-- Insert rows into table 'dbo.LoaiPhong'
INSERT INTO dbo.LoaiPhong
    (tenLoaiPhong, donGiaGio, donGiaNgay)
-- add more rows here
VALUES
    (N'Thường', 10000, 200000),
    (N'Tiêu Chuẩn', 15000, 250000),
    (N'Thương Gia', 20000, 350000)
GO

-- Insert rows into table 'dbo.Phong'
INSERT INTO dbo.Phong
    (tenPhong, sucChua, soGiuong, tinhTrang, maLoaiPhong)
-- add more rows here
VALUES
    (N'Phòng 101', 2, 1, N'Trống', 3),
    (N'Phòng 102', 2, 1, N'Trống', 3),
    (N'Phòng 103', 2, 2, N'Trống', 3),
    (N'Phòng 201', 2, 1, N'Trống', 2),
    (N'Phòng 202', 2, 2, N'Trống', 2),
    (N'Phòng 203', 4, 2, N'Trống', 2),
    (N'Phòng 301', 2, 1, N'Trống', 1),
    (N'Phòng 302', 2, 2, N'Trống', 1),
    (N'Phòng 303', 4, 2, N'Trống', 1)
GO

-- DROP DATABASE QuanLyKhachSan
-- ngăn lỗi người dùng nhập ' OR 1 = 1 -- '
GO
CREATE PROC UDP_Login
    @username NVARCHAR(100),
    @password NVARCHAR(1000)
AS
BEGIN
    SELECT *
    FROM dbo.TaiKhoan
    WHERE username = @username AND password = @password
END


GO
CREATE PROC UDP_getAccountList
AS
SELECT tk.userName, nv.tenNV, tk.maNV, tk.loaiTK
FROM dbo.TaiKhoan tk JOIN dbo.NhanVien nv ON tk.maNV = nv.maNV

GO
CREATE PROC UDP_getAccountByUsername
    @username NVARCHAR(100)
AS
BEGIN
    SELECT tk.userName, nv.tenNV, tk.maNV, tk.loaiTK
    FROM dbo.TaiKhoan tk JOIN dbo.NhanVien nv ON tk.maNV = nv.maNV
    WHERE tk.userName = @username
END

GO
CREATE PROC UDP_getPhongList
AS
SELECT *
FROM dbo.Phong
