USE master
GO

-- DROP DATABASE QuanLyQuanCafe
-- GO

CREATE DATABASE QuanLyQuanCafe
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
    PassWord NVARCHAR(1000) NOT NULL DEFAULT(N'123456'),
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
    Discount INT DEFAULT(0),
    totalPrice FLOAT DEFAULT(0),

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
-- password: admin
    (N'admin', N'3335474112287-91-8967-119741474-12831-61', N'Quản lý', 1),
-- password: nhanvien1
    (N'nhanvien1', N'-4-1333-48-1069869112228-26-2515318086', N'Nhân viên 1', 0)
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

INSERT INTO dbo.ProductCategory
    (name)
VALUES
    (N'Cafe'),
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
    (N'Cafe đá', 1, 18000),
    (N'Cafe sữa', 1, 20000),
    (N'Espresso', 1, 24000),
    (N'Latte Macchiato', 1, 24000),
    (N'Cappuccino', 1, 24000),
    (N'Cafe Latte', 1, 24000),
    (N'Cafe Mocha', 1, 24000),
    (N'Americano', 1, 24000),
    (N'Espresso Con Panna', 1, 24000),
    (N'Cappuccino Viennese', 1, 24000),

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

    (N'Topping flan chocolate', 6, 7000),
    (N'Topping flan trứng gà', 6, 7000),
    (N'Topping sương sáo', 6, 7000),
    (N'Topping thạch khoai môn', 6, 7000),
    (N'Topping thạch trái cây', 6, 7000),
    (N'Topping thạch phô mai', 6, 7000),
    (N'Topping trân châu trắng', 6, 7000),
    (N'Topping trân châu đen', 6, 7000),

    (N'Trà sữa matcha', 7, 22000),
    (N'Trà sữa việt quốc', 7, 24000),
    (N'Trà sữa dâu', 7, 25000),
    (N'Trà sữa matcha', 7, 25000)
GO

-- UPDATE dbo.Product SET name = N'Trà sữa matcha 1' , idCategory = 7, price = 24000 WHERE id = 47

-- Store procedure

-- chuyển đổi kí tự có dấu thành không dấu và ngược lại
CREATE FUNCTION [dbo].[fuConvertToUnsign] ( @strInput NVARCHAR(4000) ) 
RETURNS NVARCHAR(4000) 
AS 
BEGIN
    IF @strInput IS NULL RETURN @strInput
    IF @strInput = '' RETURN @strInput
    DECLARE @RT NVARCHAR(4000)
    DECLARE @SIGN_CHARS NCHAR(136)
    DECLARE @UNSIGN_CHARS NCHAR (136)
    SET @SIGN_CHARS = N'ăâđêôơưàảãạáằẳẵặắầẩẫậấèẻẽẹéềểễệế ìỉĩịíòỏõọóồổỗộốờởỡợớùủũụúừửữựứỳỷỹỵý ĂÂĐÊÔƠƯÀẢÃẠÁẰẲẴẶẮẦẨẪẬẤÈẺẼẸÉỀỂỄỆẾÌỈĨỊÍ ÒỎÕỌÓỒỔỖỘỐỜỞỠỢỚÙỦŨỤÚỪỬỮỰỨỲỶỸỴÝ' +NCHAR(272)+ NCHAR(208)
    SET @UNSIGN_CHARS = N'aadeoouaaaaaaaaaaaaaaaeeeeeeeeee iiiiiooooooooooooooouuuuuuuuuuyyyyy AADEOOUAAAAAAAAAAAAAAAEEEEEEEEEEIIIII OOOOOOOOOOOOOOOUUUUUUUUUUYYYYYDD'
    DECLARE @COUNTER INT
    DECLARE @COUNTER1 INT
    SET @COUNTER = 1
    WHILE (@COUNTER <=LEN(@strInput)) BEGIN
        SET @COUNTER1 = 1
        WHILE (@COUNTER1 <=LEN(@SIGN_CHARS)+1) BEGIN
            IF UNICODE(SUBSTRING(@SIGN_CHARS, @COUNTER1,1)) = UNICODE(SUBSTRING(@strInput,@COUNTER ,1) ) BEGIN
                IF @COUNTER=1 SET @strInput = SUBSTRING(@UNSIGN_CHARS, @COUNTER1,1) + SUBSTRING(@strInput, @COUNTER+1,LEN(@strInput)-1) ELSE SET @strInput = SUBSTRING(@strInput, 1, @COUNTER-1) +SUBSTRING(@UNSIGN_CHARS, @COUNTER1,1) + SUBSTRING(@strInput, @COUNTER+1,LEN(@strInput)- @COUNTER)
                BREAK
            END
            SET @COUNTER1 = @COUNTER1 +1
        END
        SET @COUNTER = @COUNTER +1
    END
    SET @strInput = replace(@strInput,' ','-')
    RETURN @strInput
END
GO

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
CREATE PROC USP_getAccountByUsername
    @username NVARCHAR(100)
AS
BEGIN
    SELECT *
    FROM dbo.Account tk
    WHERE tk.userName = @username
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

CREATE PROC USP_getListMenuByTableID
    @id INT
AS
BEGIN
    SELECT p.name, p.price, bi.[count], p.price * bi.[count] AS totalPrice
    FROM dbo.BillInfo bi, dbo.Bill b, dbo.Product p
    WHERE bi.idBill = b.id AND bi.idProduct = p.id AND b.idTable = @id
        AND b.[status] = 0
END
GO

CREATE PROC USP_getListProductByCategoryName
    @name NVARCHAR(100)
AS
BEGIN
    SELECT p.id, p.name, p.idCategory, p.price
    FROM dbo.Product p, dbo.ProductCategory pc
        WHERE p.idCategory = pc.id
        AND pc.name = @name
END
GO

CREATE PROC USP_insertBill
    @idTable INT
AS
BEGIN
    INSERT INTO dbo.Bill
        (DateCheckIn, DateCheckOut, idTable, [status], discount)
    VALUES
        (getdate(), NULL, @idTable, 0, 0)
END
GO

CREATE PROC USP_insertBillInfo
    @idBill INT,
    @idProduct INT,
    @count INT
AS
BEGIN
    DECLARE @isExitsBillInfo INT
    DECLARE @foodCount INT = 1

    SELECT @isExitsBillInfo = b.id , @foodCount = b.count
    FROM dbo.BillInfo b
    WHERE idBill = @idBill
        AND idProduct = @idProduct

    IF(@isExitsBillInfo > 0)
    BEGIN
        DECLARE @newCount INT = @foodCount + @count
        IF(@newCount > 0)
        BEGIN
            UPDATE dbo.BillInfo
            SET [count] = @newCount
            WHERE idBill = @idBill
                AND idProduct = @idProduct
        END
        ELSE
        BEGIN
            DELETE FROM dbo.BillInfo
            WHERE idBill = @idBill
                AND idProduct = @idProduct
        END
    END
    ELSE
    BEGIN
        INSERT INTO dbo.BillInfo
            (idBill, idProduct, [count])
        VALUES
            (@idBill, @idProduct, @count)
    END
END
GO

CREATE PROC USP_getProductByProductName 
    @productName NVARCHAR(100)
AS
BEGIN
    SELECT *
    FROM dbo.product p
    WHERE p.Name = @productName
END
GO

CREATE PROC USP_getListProductByProductName
    @productName NVARCHAR(100)
AS
BEGIN   
    DECLARE @name NVARCHAR(102) = N'%' + @productName + N'%'
    SELECT p.id, p.idCategory, p.name, p.price
    FROM dbo.product p, dbo.ProductCategory c
    WHERE p.idCategory = c.id
        AND p.name like @name
END
GO

CREATE TRIGGER UTG_UpdateBillInfo
ON dbo.BillInfo FOR INSERT, UPDATE
AS
    BEGIN
    DECLARE @idBill INT, @idTable INT

    SELECT @idBill = idBill
    FROM inserted

    SELECT @idTable = idTable
    FROM dbo.Bill
    WHERE id = @idBill
        AND status = 0

    DECLARE @count INT
    SELECT @count = count(*)
    FROM dbo.BillInfo
    WHERE idBill = @idBill

    IF(@count > 0)
    UPDATE dbo.TableFood 
        SET status = N'Có người'
        WHERE id = @idTable
    ELSE
    UPDATE dbo.TableFood 
        SET status = N'Trống'
        WHERE id = @idTable
END
GO

CREATE TRIGGER UTG_UpdateBill
ON dbo.Bill FOR UPDATE
AS
    BEGIN
    DECLARE @idTable INT, @idBill INT, @count INT

    SELECT @idBill = id
    FROM inserted

    SELECT @idTable = idTable
    FROM dbo.Bill
    WHERE id = @idBill

    SELECT @count = count(*)
    FROM dbo.Bill
    WHERE id = @idBill
        AND status = 0

    IF (@count = 0)
        UPDATE dbo.TableFood 
        SET status = N'Trống'
        WHERE id = @idTable
END
GO

CREATE PROC USP_SwitchTable
    @idTable1 INT,
    @idTable2 INT
AS
BEGIN
    DECLARE @idFirstBill INT
    DECLARE @idSecondBill INT
    DECLARE @isFirstTableEmpty INT = 1
    DECLARE @isSecondTableEmpty INT = 1

    SELECT @idSecondBill = id
    FROM dbo.Bill
    WHERE id = @idTable2 AND [status] = 0

    SELECT @idFirstBill = id
    FROM dbo.Bill
    WHERE id = @idTable1 AND [status] = 0

    IF(@idFirstBill IS NULL)
    BEGIN
        INSERT INTO dbo.Bill
            (DateCheckIn, DateCheckOut, Status, idTable, discount)
        VALUES
            (getdate(), NULL, 0, @idTable1, 0)

        SELECT @idFirstBill = MAX(id)
        FROM dbo.Bill
        WHERE idTable = @idTable1 AND [status] = 0
        -- chưa xóa bill của bàn cũ
    END

    SELECT @isFirstTableEmpty = Count(*)
    FROM dbo.BillInfo
    WHERE idBill = @idFirstBill

    IF(@idSecondBill IS NULL)
    BEGIN
        INSERT INTO dbo.Bill
            (DateCheckIn, DateCheckOut, Status, idTable, discount)
        VALUES
            (getdate(), NULL, 0, @idTable2, 0)

        SELECT @idSecondBill = MAX(id)
        FROM dbo.Bill
        WHERE idTable = @idTable2 AND [status] = 0
        -- chưa xóa bill của bàn cũ
    END

    SELECT @isSecondTableEmpty = Count(*)
    FROM dbo.BillInfo
    WHERE idBill = @idSecondBill

    SELECT id
    INTO IDBillInfoTable
    FROM dbo.BillInfo
    WHERE idBill = @idSecondBill

    UPDATE dbo.BillInfo 
    SET idBill = @idSecondBill 
    WHERE idBill = @idFirstBill

    UPDATE dbo.BillInfo
    SET idBill = @idFirstBill
    WHERE id IN (
        SELECT *
        FROM dbo.IDBillInfoTable
    )

    DROP TABLE dbo.IDBillInfoTable

    IF (@isFirstTableEmpty = 0) 
        UPDATE dbo.TableFood 
        SET status = N'Trống'
        WHERE id = @idTable2

    IF (@isSecondTableEmpty = 0) 
        UPDATE dbo.TableFood 
        SET status = N'Trống'
        WHERE id = @idTable1
END 
GO

CREATE PROC UPS_updateAccount
    @username NVARCHAR(100),
    @displayName NVARCHAR(100),
    @password NVARCHAR(100),
    @newPassword NVARCHAR(100)
AS
BEGIN
    DECLARE @isRightsPass INT
    SELECT @isRightsPass = count(*)
    FROM dbo.Account a
    WHERE a.Username = @username
        AND a.Password = @password

    IF(@isRightsPass = 1)
        BEGIN
        IF(@newPassword IS NULL OR @newPassword = '')
        BEGIN
            UPDATE dbo.Account
                SET DisplayName = @DisplayName
                WHERE username = @username
        END
        
        ELSE
        BEGIN
            UPDATE dbo.Account
                SET DisplayName = @DisplayName,
                    [PassWord] = @PassWord
                WHERE username = @username
        END
    END
END
GO

CREATE PROC USP_getListProduct
AS
BEGIN
    SELECT p.id, p.name, p.price, pc.name AS CategoryName
    FROM dbo.Product p, dbo.ProductCategory pc
    WHERE p.idCategory = pc.id
END
GO

CREATE PROC USP_searchProductByCategoryNameAndProductName
    @ProductName NVARCHAR(100),
    @CategoryName NVARCHAR(100)
AS
BEGIN
    DECLARE @PName NVARCHAR(100) = N'%' + @ProductName + N'%';
    SELECT p.id, p.name, p.price, pc.name AS CategoryName
    FROM dbo.Product p, dbo.ProductCategory pc
    WHERE p.idCategory = pc.id
        AND pc.name = @CategoryName
        AND dbo.fuConvertToUnsign(p.name) like dbo.fuConvertToUnsign(@PName)
END
GO

CREATE PROC USP_searchProductByCategoryName
    @CategoryName NVARCHAR(100)
AS
BEGIN
    SELECT p.id, p.name, p.price, pc.name AS CategoryName
    FROM dbo.Product p, dbo.ProductCategory pc
    WHERE p.idCategory = pc.id
        AND pc.name = @CategoryName
END
GO

CREATE PROC USP_searchProductByProductName
    @ProductName NVARCHAR(100)
AS
BEGIN
    DECLARE @Pname NVARCHAR(100) = N'%' + @ProductName + '%'
    SELECT p.id, p.name, p.price, pc.name AS CategoryName
    FROM dbo.Product p, dbo.ProductCategory pc
    WHERE p.idCategory = pc.id
        AND dbo.fuConvertToUnsign(p.name) like dbo.fuConvertToUnsign(@PName)
END
GO

CREATE TRIGGER UTG_deleteBillInfo
ON dbo.BillInfo FOR DELETE
AS
    BEGIN
        DECLARE @idBillInfo INT
        DECLARE @idBill INT

        SELECT @idBillInfo = d.id, @idBill = d.idBill
        FROM deleted d

        DECLARE @idTable INT
        SELECT @idTable = idTable
        FROM dbo.Bill
        WHERE id = @idBill

        DECLARE @count INT = 0
        SELECT @count = count(*) 
        FROM dbo.BillInfo bi, dbo.Bill b
        WHERE bi.idBill = b.id
            AND b.id = @idBill
            AND b.status = 0

        IF(@count = 0)
            UPDATE dbo.TableFood
            Set status = N'Trống'
            WHERE id = @idTable
    END
GO

CREATE PROC USP_deleteBillByTableID
    @tableID INT
AS
BEGIN
    DECLARE @billCount INT
    SELECT @billCount = count(*)
    FROM dbo.Bill b
    WHERE b.idTable = @tableID

    IF @billCount > 0
    BEGIN
        DELETE FROM dbo.BillInfo 
        WHERE idBill IN (
            SELECT b.id
            FROM dbo.Bill b
            WHERE b.idTable = @tableID
        )
    END

    DELETE FROM dbo.Bill
    WHERE idTable = @tableID
END
GO  

CREATE PROC USP_getListBillByDateAndPage
    @dateCheckIn DATE,
    @dateCheckOut DATE,
    @page int
AS
BEGIN
    DECLARE @pageRows int = 30
    DECLARE @selectRows int = @pageRows * @page
    DECLARE @exceptRows int = (@page - 1) * @pageRows

    ;WITH billShow AS (
    SELECT b.id, t.name, b.totalPrice, b.DateCheckIn, b.DateCheckOut, b.Discount
    FROM dbo.Bill b, dbo.TableFood t
    WHERE b.DateCheckIn >= @dateCheckIn
        AND b.DateCheckOut <= @dateCheckOut --DATEADD(day, 1, @dateCheckOut)
        AND b.[status] = 1
        AND t.id = b.idTable
    )
    -- c1
    -- SELECT TOP (@selectRows) *
    -- FROM billShow
    -- EXCEPT
    -- SELECT TOP (@exceptRows) *
    -- FROM billShow

    -- c2
    SELECT TOP (@selectRows) *
    FROM BillShow
    WHERE id NOT IN (
        SELECT TOP (@exceptRows) id
        FROM BillShow
    )
END
GO

CREATE PROC USP_getNumBillByDate
    @dateCheckIn DATE,
    @dateCheckOut DATE
AS
    BEGIN
        SELECT COUNT(*)
        FROM dbo.Bill b, dbo.TableFood t
        WHERE b.DateCheckIn >= @dateCheckIn
            AND b.DateCheckOut < DATEADD(day, 1, @dateCheckOut)
            AND b.[status] = 1
            AND t.id = b.idTable
    END
GO