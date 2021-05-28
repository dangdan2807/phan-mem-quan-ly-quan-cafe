
USE master
GO

create DATABASE QuanLyQuanCafe
GO

USE QuanLyQuanCafe 
GO

-- create table

CREATE TABLE TableFood
(
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(100) NOT NULL DEFAULT(N'Chưa cập nhật'),
    status NVARCHAR(100) NOT NULL DEFAULT(N'Trống'),
)
GO

-- CREATE TABLE Employees
-- (
--     id int IDENTITY NOT NULL PRIMARY KEY,
--     name NVARCHAR(100) NOT NULL DEFAULT(N'Chưa cập nhật'),
--     address NVARCHAR(500),
--     phone NVARCHAR(100),
--     cmnd NVARCHAR(100) NOT NULL
-- )

CREATE TABLE Account
(
    Username NVARCHAR(100) PRIMARY KEY,
    PassWord NVARCHAR(1000) NOT NULL DEFAULT(N'0'),
    DisplayName NVARCHAR(100),
    -- idEmployee int NOT NULL,
    -- 1. admin || 0. nhân viên
    Type INT NOT NULL DEFAULT(0)

    -- FOREIGN KEY (idEmployee) REFERENCES dbo.Employees(id) ON DELETE CASCADE
)
GO

CREATE TABLE ProductCategory --FoodCategory
(
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(100) NOT NULL DEFAULT(N'Chưa cập nhật'),
)
GO

CREATE TABLE Product -- Food
(
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(100) NOT NULL DEFAULT(N'Chưa cập nhật'),
    idCategory INT NOT NULL DEFAULT(1),
    price FLOAT NOT NULL DEFAULT(0),

    FOREIGN KEY (idCategory) REFERENCES dbo.ProductCategory(id)
)
GO

CREATE TABLE Bill
(
    id INT IDENTITY PRIMARY KEY,
    idTable INT NOT NULL,
    DateCheckIn DATETIME NOT NULL DEFAULT (getdate()),
    DateCheckOut DATETIME,
    --  0. chưa thanh toán ||  1. đã thanh toán
    status INT NOT NULL DEFAULT(0),

    FOREIGN KEY (idTable) REFERENCES dbo.TableFood (id),
)
GO

CREATE TABLE BillInfo
(
    id INT IDENTITY NOT NULL,
    idBill INT NOT NULL,
    idProduct INT NOT NULL,
    -- idEmployee INT NOT NULL,
    count INT NOT NULL DEFAULT (0),

    FOREIGN KEY (idBill) REFERENCES dbo.Bill(id),
    FOREIGN KEY (idProduct) REFERENCES dbo.Product(id),
    -- FOREIGN KEY (idEmployee) REFERENCES dbo.Employees(id)
)
GO

-- insert data

-- INSERT INTO dbo.Employees
--     (name, address, phone, cmnd)
-- VALUES
--     (N'admin', N'hcm', N'123123111', N'1-111-111-111'),
--     (N'nhanvien1', N'hcm', N'123123112', N'1-111-111-112')

INSERT INTO dbo.Account
    (UserName, [PassWord], DisplayName, [Type])
VALUES
    (N'admin', N'admin', N'admin', 1),
    (N'nhanvien1', N'nhanvien1', N'nhanvien1', 0)
GO

DECLARE @i INT = 1
WHILE @i <= 20
BEGIN
    INSERT INTO dbo.TableFood
        (name)
    VALUES
        (N'Bàn ' + CAST(@i AS NVARCHAR(100)))
    SET @i = @i + 1
END
GO

update dbo.TableFood
set status = N'Có Người'
WHERE id between 1 and 2
go

INSERT INTO dbo.ProductCategory
    (name)
VALUES
    (N'Chưa phân nhóm'),
    (N'Nước uống đóng chai'),
    (N'Trà'),
    (N'Sinh Tố'),
    (N'Hồng Trà'),
    (N'Topping'),
    (N'Trà Sữa')
GO

INSERT INTO dbo.Product
    (name, idCategory, Price)
VALUES
    (N'Red bull', 2, 12000),
    (N'7-up', 2, 12000),
    (N'Aquafina', 2, 12000),
    (N'Lavie', 2, 12000),
    (N'Mirinda soda kem', 2, 12000),
    (N'Pepsi light', 2, 12000),
    (N'Mirinda xá xị', 2, 12000),
    (N'Moutain dew', 2, 12000),
    (N'Dasani', 2, 12000),
    (N'Trà ô long tea plus', 2, 12000),

    (N'Trà dưa hấu bạc hà', 3, 24000),
    (N'Trà đào cam sả', 3, 30000),
    (N'Trà xoài', 3, 16000),
    (N'Trà bí đao', 3, 16000),
    (N'Lục trà xí muội', 3, 16000),

    (N'Sinh tố kiwi', 4, 22000),
    (N'Sinh tố dâu', 4, 22000),
    (N'Sinh tố đậu xanh', 4, 17000),
    (N'Sinh tố cà rốt', 4, 22000),
    (N'Sinh tố chuối', 4, 17000),
    (N'Sinh tố cà chua', 4, 17000),
    (N'Sinh tố rau má', 4, 12000),

    (N'Hồng trà đào', 5, 22000),
    (N'Hồng trà nhiệt đới', 5, 22000),
    (N'Hồng trà táo', 5, 22000),
    (N'Hồng trà chanh mật ong', 5, 22000),
    (N'Hồng trà japan', 5, 22000),

    (N'Flan chocolate', 6, 7000),
    (N'Flan trứng gà', 6, 7000),
    (N'Sương sáo', 6, 7000),
    (N'Thạch khoai môn', 6, 7000),
    (N'Thạch trái cây', 6, 7000),
    (N'Thạch phô mai', 6, 7000),
    (N'Trân châu trắng', 6, 7000),
    (N'Trân châu đen', 6, 7000),

    (N'Trà sữa matcha', 7, 2200),
    (N'Trà sữa việt quốc', 7, 2200)
GO

INSERT INTO dbo.Bill
    (DateCheckIn, DateCheckOut, idTable, [status])
VALUES
    (getdate(), null, 1, 0),
    (getdate(), null, 2, 0),
    (getdate(), getdate(), 3, 1)
GO

INSERT INTO dbo.BillInfo 
    (idBill, idProduct, [count])
VALUES
    (1, 12, 1),
    (2, 3, 1),
    (2, 5, 1),
    (3, 26, 1)
GO

-- Store procedure
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

CREATE PROC USP_getUncheckBillByTableID
    @id INT
AS
BEGIN
    SELECT *
    FROM dbo.Bill hd
    WHERE hd.idTable = @id
        AND hd.[status] = 0
END
GO

CREATE PROC USP_getListBillInfo
    @id INT
AS
BEGIN
    SELECT *
    FROM dbo.BillInfo bi
    WHERE bi.id =  @id
END
GO

CREATE PROC USP_getBillInfoListByTableID
    @id INT
AS
BEGIN
    SELECT p.name, p.price, bi.[count], p.price * bi.[count] AS totalPrice
    FROM dbo.BillInfo bi, dbo.Bill b, dbo.Product p
    WHERE bi.idBill = b.id AND bi.idProduct = p.id AND b.idTable = @id
        AND b.[status] = 0
END
GO