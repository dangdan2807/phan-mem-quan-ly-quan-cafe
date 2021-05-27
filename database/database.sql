
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


CREATE TABLE Employees
(
    id int IDENTITY NOT NULL,
    name NVARCHAR(100) NOT NULL DEFAULT(N'Chưa cập nhật'),
    address NVARCHAR(500),
    phone NVARCHAR(100),
    cmnd NVARCHAR(100) NOT NULL,
)

CREATE TABLE Account
(
    Username NVARCHAR(100) PRIMARY KEY,
    PassWord NVARCHAR(1000) NOT NULL DEFAULT(N'0'),
    idEmployee int NOT NULL,
    -- 1. admin || 0. nhân viên
    Type INT NOT NULL DEFAULT(0),

    FOREIGN KEY (idEmployee) REFERENCES dbo.Employees(id) ON DELETE CASCADE,
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


INSERT INTO dbo.Employees
    (name, address, phone, cmnd)
VALUES
    (N'admin', N'hcm', N'123123111', N'1-111-111-111')
    (N'nhanvien1', N'hcm', N'123123112', N'1-111-111-112')

INSERT INTO dbo.Account
    (UserName, [PassWord], idEmployee, [Type])
VALUES
    (N'admin', N'admin', 1, 1),
    (N'nhanvien1', 2, N'nhanvien1', 0)
GO

DECLARE @i INT = 1
WHILE @i <= 10
BEGIN
    INSERT INTO dbo.TableFood
        (name)
    VALUES
        (N'Bàn ' + CAST(@i AS NVARCHAR(100)))
    SET @i = @i + 1
END
GO

-- SELECT * FROM dbo.account where username = N'admin'
-- -- ngăn lỗi người dùng nhập ' OR 1 = 1 -- '
CREATE PROC USP_Login
    @username NVARCHAR(100),
    @password NVARCHAR(1000)
AS
BEGIN
    SELECT *
    FROM dbo.Account
    WHERE username = @username AND password = @password
END
GO

-- EXEC UDP_Login N'admin', N'admin'

CREATE PROC USP_getAccountList
AS
BEGIN
    SELECT *
    FROM dbo.Account
END
GO

CREATE PROC USP_getAccountByUsername
    @username NVARCHAR(100)
AS
BEGIN
    SELECT *
    FROM dbo.Account tk
    WHERE tk.userName = @username
END
GO

CREATE PROC USP_getTableList
AS
BEGIN
    SELECT *
    FROM dbo.TableFood
END
GO