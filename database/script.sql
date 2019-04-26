create schema BookStoreManagement;
use BookStoreManagement;

CREATE TABLE THELOAISACH
(
	MaTheLoai INT auto_increment PRIMARY KEY,
	TenTheLoai NVARCHAR(100) NOT NULL
);

CREATE TABLE TACGIA
(
	MaTacGia INT auto_increment PRIMARY KEY,
	TenTacGia NVARCHAR(100) NOT NULL
);

CREATE TABLE CT_TACGIA
(
	MaSach INT ,
	MaTacGia INT NOT NULL ,
	CONSTRAINT PK_CTTACGIA PRIMARY KEY (MaSach,MaTacGia)
);

CREATE TABLE SACH
(
	MaSach INT auto_increment  PRIMARY KEY,
    TenSach NVARCHAR(100),
    MaTheLoai int not null,
	NhaXuatBan NVARCHAR(100) NOT NULL,
	NamXuatBan INT NOT NULL,
	SoLuongTon INT NOT NULL DEFAULT 0,
	DonGiaNhap FLOAT NOT NULL DEFAULT 0
);

alter table SACH 
add constraint SACH_THELOAISACH_FK 
foreign key(MaTheLoai) references THELOAISACH(MaTheLoai);

alter table CT_TACGIA 
add constraint CT_TACGIA_DAUSACH_FK 
foreign key(MaSach) references SACH(MaSach);

alter table CT_TACGIA 
add constraint CT_TACGIA_TACGIA_FK 
foreign key(MaTacGia) references TACGIA(MaTacGia);

CREATE TABLE PHIEUNHAPSACH
(
	SoPhieuNhap INT auto_increment PRIMARY KEY,
	NgayLap DATE NOT NULL DEFAULT NOW(),
	TongTien FLOAT DEFAULT 0
);

CREATE TABLE CT_PHIEUNHAPSACH
(
	SoPhieuNhap INT NOT NULL ,
	MaSach INT NOT NULL ,
	SoLuongNhap INT NOT NULL DEFAULT 0,
	DonGiaNhap FLOAT NOT NULL DEFAULT 0,
	ThanhTien FLOAT NOT NULL DEFAULT 0,	
	CONSTRAINT PK_CTPHIEUNHAPSACH PRIMARY KEY (SoPhieuNhap,MaSach)
);

alter table CT_PHIEUNHAPSACH 
add constraint CT_PHIEUNHAPSACH_PHIEUNHAPSACH_FK 
foreign key(SoPhieuNhap) references PHIEUNHAPSACH(SoPhieuNhap);

alter table CT_PHIEUNHAPSACH 
add constraint CT_PHIEUNHAPSACH_SACH_FK 
foreign key(MaSach) references SACH(MaSach);

CREATE TABLE KHACHHANG
(
	MaKhachHang INT auto_increment PRIMARY KEY,
	TenKhachHang NVARCHAR(100) NOT NULL DEFAULT ' ',
	DiaChi NVARCHAR(200)NOT NULL DEFAULT ' ',
	SoDienThoai VARCHAR(11)NOT NULL DEFAULT ' ',
	Email VARCHAR(100)NOT NULL DEFAULT ' ',
	SoTienNo FLOAT NOT NULL DEFAULT 0
);

CREATE TABLE HOADON
(
	SoHoaDon INT auto_increment PRIMARY KEY,
	MaKhachHang INT NOT NULL ,
	NgayLap DATE NOT NULL DEFAULT NOW(),
	TongTien FLOAT NOT NULL DEFAULT 0,
	ThanhToan float NOT NULL DEFAULT 0,
	ConLai FLOAT NOT NULL DEFAULT 0
);

alter table HOADON 
add constraint HOADON_KHACHHANG_FK 
foreign key(MaKhachHang) references KHACHHANG(MaKhachHang);

CREATE TABLE CT_HOADON
(
	SoHoaDon INT NOT NULL ,
	MaSach INT NOT NULL ,
	SoLuong INT NOT NULL DEFAULT 0,
	DonGiaBan FLOAT NOT NULL DEFAULT 0,
	ThanhTien FLOAT DEFAULT 0,
	CONSTRAINT PK_CTHD PRIMARY KEY(SoHoaDon,MaSach)
);

alter table CT_HOADON 
add constraint CT_HOADON_HOADON_FK 
foreign key(SoHoaDon) references HOADON(SoHoaDon);

alter table CT_HOADON 
add constraint CT_HOADON_SACH_FK 
foreign key(MaSach) references SACH(MaSach);

CREATE TABLE PHIEUTHUTIEN
(
	SoPhieuThu INT auto_increment PRIMARY KEY,
	MaKhachHang INT NOT NULL ,
	NgayLap DATE NOT NULL DEFAULT NOW(),
	SoTienThu FLOAT NOT NULL DEFAULT 0
);

alter table PHIEUTHUTIEN 
add constraint PHIEUTHUTIEN_KHACHHANG_FK 
foreign key(MaKhachHang) references KHACHHANG(MaKhachHang);

CREATE TABLE BAOCAOTON
(
	Thang INT NOT NULL ,
	Nam INT NOT NULL  ,
	MaSach INT NOT NULL ,
	TonDau INT NOT NULL DEFAULT 0,
	PhatSinh INT NOT NULL DEFAULT 0,
	TonCuoi INT NOT NULL DEFAULT 0,
	CONSTRAINT PK_ReportCountInfo PRIMARY KEY( Thang,Nam,MaSach)
);

alter table BAOCAOTON 
add constraint BAOCAOTON_SACH_FK 
foreign key(MaSach) references SACH(MaSach);

alter table BAOCAOCONGNO 
add constraint BAOCAOCONGNO_KHACHHANG_FK 
foreign key(MaKhachHang) references KHACHHANG(MaKhachHang);

CREATE TABLE NGUOIDUNG
(
	TenDangNhap VARCHAR(100) NOT NULL DEFAULT '' PRIMARY KEY,
	TenHienThi NVARCHAR(100),
	MatKhau VARCHAR(100) NOT NULL DEFAULT '',
    LoaiTaiKhoan int  /* 0:staff, 1:admin */
);
insert into NGUOIDUNG values ("admin","admin","admin",1);

DELIMITER $$
create procedure USP_Login(userName VARCHAR(100),passWord VARCHAR(100))
BEGIN
select * from BookStoreManagement.NGUOIDUNG where TenDangNhap=userName and MatKhau=passWord;
END; $$
DELIMITER ;

DELIMITER $$
create procedure USP_GetAccountByUsername(userName VARCHAR(100))
BEGIN
select * from BookStoreManagement.NGUOIDUNG where TenDangNhap=userName;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetCategory()
BEGIN
select * from THELOAISACH;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddCategory(categoryName VARCHAR(100))
BEGIN
if((select count(*) from THELOAISACH where TenTheLoai=categoryname)=0)
then
    insert THELOAISACH(TenTheLoai) values(categoryName);
    end if;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetAuthor()
BEGIN
select * from TACGIA;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddAuthor(author VARCHAR(100))
BEGIN
	insert TACGIA(TenTacGia) values(author);
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetAuthorByBook(bookID int)
BEGIN
select TACGIA.MaTacGia,TACGIA.TenTacGia from TACGIA,CT_TACGIA,SACH
where TACGIA.MaTacGia=CT_TACGIA.MaTacGia and CT_TACGIA.MaSach=SACH.MaSach and SACH.MaSach=bookID;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetCategoryByBook(bookID int)
BEGIN
select THELOAISACH.MaTheLoai,THELOAISACH.TenTheLoai from THELOAISACH,SACH
where THELOAISACH.MaTheLoai=SACH.MaTheLoai and SACH.MaSach=bookID;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddBook(name NVARCHAR(100),categoryID int, publishCompany nvarchar(200),publishYear int)
BEGIN
	insert SACH(TenSach,MaTheLoai,NhaXuatBan,NamXuatBan,SoLuongTon,DonGiaNhap)
    values(name,categoryID,publishCompany,publishYear,0,0);
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddBookAuthor(authorID int)
BEGIN
	declare bookID int;
    set bookID=(select max(MaSach) from SACH);
	insert CT_TACGIA values(bookID,authorID);
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetCategoryByID(categoryID int)
BEGIN
	select* from THELOAISACH where MaTheLoai=categoryID;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetBook()
BEGIN
	select* from SACH;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddImportBook(dateInput date ,money float)
BEGIN
	insert PHIEUNHAPSACH(NgayLap,TongTien)values(dateInput,money);
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddImportBookInfo(bookID int ,countt int, price float,total float)
BEGIN
	declare id int;
    set id=(select max(SoPhieuNhap) from PHIEUNHAPSACH);
	insert CT_PHIEUNHAPSACH values(id,bookID,countt,price,total);
    update SACH set SoLuongTon=SoLuongTon+countt,DonGiaNhap=price where MaSach=bookID;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddCustomer(name NVARCHAR(100),phone varchar(100),email varchar(100),address nvarchar(100))
BEGIN
	insert KHACHHANG(TenKhachHang,DiaChi,SoDienThoai,Email,SoTienNo) 
    values(name,address,phone,email,0);
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetCustomer()
BEGIN
select * from KHACHHANG;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddBill(dateInput date ,money float,moneyReceive float, moneyChange float, customerID int)
BEGIN
	insert HOADON(MaKhachHang,NgayLap,TongTien,ThanhToan,ConLai)
    values(customerID,dateInput,money,moneyReceive,moneyChange);
    update KHACHHANG set SoTienNo=SoTienNo+moneyChange where MaKhachHang=customerID;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_AddBillInfo(bookID int ,countt int, price float,total float)
BEGIN
	declare id int;
    set id=(select max(SoHoaDon) from HOADON);
	insert CT_HOADON values(id,bookID,countt,price,total);
    update SACH set SoLuongTon=SoLuongTon-countt where MaSach=bookID;
END; $$
DELIMITER ;
