
USE master
GO

CREATE DATABASE QuanLyQuanCafe
GO

USE QuanLyQuanCafe 
GO

CREATE TABLE TableFood
(
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(100) NOT NULL DEFAULT(N'Chưa cập nhật'),
    status NVARCHAR(100) NOT NULL DEFAULT(N'Trống'),
)
GO

CREATE TABLE Account
(
    UserName NVARCHAR(100) PRIMARY KEY,
    PassWord NVARCHAR(1000) NOT NULL DEFAULT(N'0'),
    DisplayName NVARCHAR(1000) NOT NULL  DEFAULT(N'Người dùng'),
    -- 1. admin || 0. nhân viên
    Type INT NOT NULL DEFAULT(0),
)
GO

CREATE TABLE FoodCategory
(
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(100) NOT NULL DEFAULT(N'Chưa cập nhật'),
)
GO

CREATE TABLE Food
(
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(100) NOT NULL DEFAULT(N'Chưa cập nhật'),
    idCategory INT NOT NULL,
    price FLOAT NOT NULL DEFAULT(0),

    FOREIGN KEY (idCategory) REFERENCES dbo.FoodCategory(id)
)
GO

CREATE TABLE Bill
(
    id INT IDENTITY PRIMARY KEY,
    idTable INT NOT NULL,
    DateCheckIn DATETIME NOT NULL DEFAULT (getdate()),
    DateCheckOut DATETIME,
    --  0. chưa thanh toán ||  1. đã thanh toán || 2. Chưa nhận phòng
    status INT NOT NULL DEFAULT(0),

    FOREIGN KEY (idTable) REFERENCES dbo.TableFood (id),
)
GO

CREATE TABLE BillInfo
(
    id INT IDENTITY NOT NULL,
    idBill INT NOT NULL,
    idFood INT NOT NULL,
    count INT NOT NULL DEFAULT (0),
    ngayGioDat datetime NOT NULL DEFAULT (getdate()),

    FOREIGN KEY (idBill) REFERENCES dbo.Bill(id),
    FOREIGN KEY (idFood) REFERENCES dbo.FooD(id)
)
GO


-- -- Insert rows into table 'dbo.TaiKhoan'
-- INSERT INTO dbo.TaiKhoan
--     (userName, password, maNV, loaiTK)
-- VALUES
--     ('admin', 'admin', 1, 1),
--     ('nguyenvanhung', 'nguyenvanhung', 2, 0),
--     ('tranvanminh', 'tranvanminh', 3, 0),
--     ('buithihoa', 'buithihoa', 4, 0)
-- GO

-- -- Insert rows into table 'dbo.LoaiPhong'
-- INSERT INTO dbo.LoaiPhong
--     (tenLoaiPhong, donGiaGio, donGiaNgay)
-- -- add more rows here
-- VALUES
--     (N'Thường', 10000, 200000),
--     (N'Tiêu Chuẩn', 15000, 250000),
--     (N'Thương Gia', 20000, 350000)
-- GO

-- -- insert data
-- -- Insert rows into table 'dbo.Phong'
-- DECLARE @i INT = 1, @tang INT = 1, @phong INT = 1, @tinhTrang NVARCHAR(100), @maLoaiPhong INT = 1, 
-- @maphong NVARCHAR(100)
-- WHILE @i <= 30 BEGIN
--     IF(@i % 5 = 0) BEGIN
--         SET @tinhTrang = N'Đã thuê';
--     END ELSE BEGIN
--         SET @tinhTrang = N'Trống';
--     END
--     IF(@i % 3 = 0 OR @i % 5 = 0)
--     BEGIN
--         SET @maLoaiPhong = 1
--     END
--     ELSE IF( @i % 2 = 0 OR @i % 7 = 0)
--     BEGIN
--         SET @maLoaiPhong = 2
--     END
--     ELSE
--     BEGIN
--         SET @maLoaiPhong = 3
--     END
--     IF(@i % 10 = 0)
--     BEGIN
--         SET @maphong = N'';
--     END
--     ELSE BEGIN
--         SET @maphong = N'0';
--     END
--     INSERT INTO dbo.Phong
--         ( tenPhong, sucChua, soGiuong, tinhTrang, maLoaiPhong )
--     VALUES
--         ( N'Phòng ' + CAST(@tang AS NVARCHAR(10)) + @maphong + CAST(@phong AS NVARCHAR(10)), 2, 1, @tinhTrang, @maLoaiPhong)
--     SET @i = @i + 1
--     SET @phong = @phong + 1
--     IF((@i -1) % 10 = 0) BEGIN
--         SET @tang = @tang + 1
--         SET @phong = 1
--     END
-- END 
-- GO

-- -- Insert rows into table 'dbo.LoaiDichVu'
-- INSERT INTO dbo.LoaiDichVu
--     (tenLoaiDV)
-- -- add more rows here
-- VALUES
--     (N'Cơ bản'),
--     (N'Cao cấp'),
--     (N'Khác')
-- GO

-- -- Insert rows into table 'dbo.DichVu'
-- INSERT INTO dbo.DichVu
--     (tenDV, donGia, maLoaiDV)
-- VALUES
--     (N'Gửi xe', 5000, 1), -- cơ bản
--     (N'Rửa xe', 30000, 1), -- cơ bản
--     (N'Thức ăn tại phòng', 30000, 1), -- cơ bản
--     (N'Giặt, ủi là', 20000, 1), -- cơ bản
--     (N'Xe đưa đón sân bay', 150000, 1), -- cơ bản
--     (N'Cho thuê xe tự lái', 120000, 1), -- cơ bản
--     (N'Trông trẻ', 30000, 1), -- cơ bản
--     (N'Chăm sóc thú cưng', 50000, 1), -- cơ bản
--     (N'Spa', 300000, 2), -- cao cấp
--     (N'Đánh golf, tennis', 200000, 2) -- cao cấp
-- GO

-- -- Insert rows into table 'dbo.KhachHang'
-- INSERT INTO dbo.KhachHang
--     (tenKH, cccd, loaiKhach)
-- VALUES
--     (N'Chí Phèo', N'123123123', 1),
--     (N'Xuân Tóc Đỏ', N'123123123', 1),
--     (N'Lão Hạc', N'123123123', 1),
--     (N'John Wick', N'123123123', 0),
--     (N'Tony Stark', N'123123123', 0)
-- GO

-- -- Insert rows into table 'dbo.HoaDon'
-- INSERT INTO dbo.HoaDon
--     (maKH, maNV, maPhong, ngayNhanPhong, ngayTraPhong, tinhTrang)
-- VALUES
--     (1, 1, 1, getdate(), null, 0),
--     (2, 2, 2, getdate(), null, 0),
--     (3, 3, 3, getdate(), null, 1)
-- go

-- -- select * from dbo.HoaDon

-- -- Insert rows into table 'dbo.ChiTietHoaDon'
-- INSERT INTO dbo.ChiTietHoaDon
--     (maHoaDon, maDV, ngayGioDat, soLuong)
-- VALUES
--     (1, 1, getdate(), 1),
--     (1, 3, getdate(), 1),
--     (1, 5, getdate(), 1),
--     (1, 6, getdate(), 1),
--     (2, 3, getdate(), 1),
--     (2, 7, getdate(), 1),
--     (3, 4, getdate(), 1)
-- go


-- -- DROP DATABASE QuanLyKhachSan
-- -- ngăn lỗi người dùng nhập ' OR 1 = 1 -- '
-- CREATE PROC UDP_Login
--     @username NVARCHAR(100),
--     @password NVARCHAR(1000)
-- AS
-- BEGIN
--     SELECT *
--     FROM dbo.TaiKhoan
--     WHERE username = @username AND password = @password
-- END

-- GO
-- CREATE PROC UDP_getAccountList
-- AS
-- SELECT tk.userName, nv.tenNV, tk.maNV, tk.loaiTK
-- FROM dbo.TaiKhoan tk JOIN dbo.NhanVien nv ON tk.maNV = nv.maNV

-- GO
-- CREATE PROC UDP_getAccountByUsername
--     @username NVARCHAR(100)
-- AS
-- BEGIN
--     SELECT tk.userName, nv.tenNV, tk.maNV, tk.loaiTK
--     FROM dbo.TaiKhoan tk JOIN dbo.NhanVien nv ON tk.maNV = nv.maNV
--     WHERE tk.userName = @username
-- END

-- GO
-- CREATE PROC UDP_getPhongList
-- AS
-- SELECT *
-- FROM dbo.Phong
