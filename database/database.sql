
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
    ngayNhanPhong DATETIME NOT NULL DEFAULT (getdate()),
    ngayTraPhong DATETIME,
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
    ngayGioDat datetime NOT NULL DEFAULT (getdate()),

    FOREIGN KEY (maHoaDon) REFERENCES dbo.HoaDon (maHoaDon),
    FOREIGN KEY (maDV) REFERENCES dbo.DichVu (maDV)
)
GO


-- Insert rows into table 'dbo.NhanVien'
INSERT INTO dbo.NhanVien
    (tenNV, cccd, email, sdt, luong)
-- add more rows here
VALUES
    (N'Admin', N'123123123', N'admin@email.com', N'123123123', 1),
    (N'Nguyễn Văn Hưng', N'123456123', N'nguyenvanhung@gmail.com', N'03xxxxxxxx', 3000000),
    (N'Trần Văn Minh', N'133456123', N'tranvanminh@gmail.com', N'07xxxxxxxx', 2600000),
    (N'Bùi Thị Hoa', N'183456123', N'buithihoa@gmail.com', N'05xxxxxxxx', 1800000)
GO

-- Insert rows into table 'dbo.TaiKhoan'
INSERT INTO dbo.TaiKhoan
    (userName, password, maNV, loaiTK)
VALUES
    ('admin', 'admin', 1, 1),
    ('nguyenvanhung', 'nguyenvanhung', 2, 0),
    ('tranvanminh', 'tranvanminh', 3, 0),
    ('buithihoa', 'buithihoa', 4, 0)
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

-- insert data
-- Insert rows into table 'dbo.Phong'
DECLARE @i INT = 1, @tang INT = 1, @phong INT = 1, @tinhTrang NVARCHAR(100), @maLoaiPhong INT = 1, 
@maphong NVARCHAR(100)
WHILE @i <= 30 BEGIN
    IF(@i % 5 = 0) BEGIN
        SET @tinhTrang = N'Đã thuê';
    END ELSE BEGIN
        SET @tinhTrang = N'Trống';
    END
    IF(@i % 3 = 0 OR @i % 5 = 0)
    BEGIN
        SET @maLoaiPhong = 1
    END
    ELSE IF( @i % 2 = 0 OR @i % 7 = 0)
    BEGIN
        SET @maLoaiPhong = 2
    END
    ELSE
    BEGIN
        SET @maLoaiPhong = 3
    END
    IF(@i % 10 = 0)
    BEGIN
        SET @maphong = N'';
    END
    ELSE BEGIN
        SET @maphong = N'0';
    END
    INSERT INTO dbo.Phong
        ( tenPhong, sucChua, soGiuong, tinhTrang, maLoaiPhong )
    VALUES
        ( N'Phòng ' + CAST(@tang AS NVARCHAR(10)) + @maphong + CAST(@phong AS NVARCHAR(10)), 2, 1, @tinhTrang, @maLoaiPhong)
    SET @i = @i + 1
    SET @phong = @phong + 1
    IF((@i -1) % 10 = 0) BEGIN
        SET @tang = @tang + 1
        SET @phong = 1
    END
END 
GO

-- Insert rows into table 'dbo.LoaiDichVu'
INSERT INTO dbo.LoaiDichVu
    (tenLoaiDV)
-- add more rows here
VALUES
    (N'Cơ bản'),
    (N'Cao cấp'),
    (N'Khác')
GO

-- Insert rows into table 'dbo.DichVu'
INSERT INTO dbo.DichVu
    (tenDV, donGia, maLoaiDV)
VALUES
    (N'Gửi xe', 5000, 1), -- cơ bản
    (N'Rửa xe', 30000, 1), -- cơ bản
    (N'Thức ăn tại phòng', 30000, 1), -- cơ bản
    (N'Giặt, ủi là', 20000, 1), -- cơ bản
    (N'Xe đưa đón sân bay', 150000, 1), -- cơ bản
    (N'Cho thuê xe tự lái', 120000, 1), -- cơ bản
    (N'Trông trẻ', 30000, 1), -- cơ bản
    (N'Chăm sóc thú cưng', 50000, 1), -- cơ bản
    (N'Spa', 300000, 2), -- cao cấp
    (N'Đánh golf, tennis', 200000, 2) -- cao cấp
GO

-- Insert rows into table 'dbo.KhachHang'
INSERT INTO dbo.KhachHang
    (tenKH, cccd, loaiKhach)
VALUES
    (N'Chí Phèo', N'123123123', 1),
    (N'Xuân Tóc Đỏ', N'123123123', 1),
    (N'Lão Hạc', N'123123123', 1),
    (N'John Wick', N'123123123', 0),
    (N'Tony Stark', N'123123123', 0)
GO

-- Insert rows into table 'dbo.HoaDon'
INSERT INTO dbo.HoaDon
    (maKH, maNV, maPhong, ngayNhanPhong, ngayTraPhong, tinhTrang)
VALUES
    (1, 1, 1, getdate(), null, 0),
    (2, 2, 2, getdate(), null, 0),
    (3, 3, 3, getdate(), null, 1)
go

-- select * from dbo.HoaDon

-- Insert rows into table 'dbo.ChiTietHoaDon'
INSERT INTO dbo.ChiTietHoaDon
    (maHoaDon, maDV, ngayGioDat, soLuong)
VALUES
    (1, 1, getdate(), 1),
    (1, 3, getdate(), 1),
    (1, 5, getdate(), 1),
    (1, 6, getdate(), 1),
    (2, 3, getdate(), 1),
    (2, 7, getdate(), 1),
    (3, 4, getdate(), 1)
go


-- DROP DATABASE QuanLyKhachSan
-- ngăn lỗi người dùng nhập ' OR 1 = 1 -- '
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
