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

CREATE TABLE DAUSACH
(
	MaDauSach INT auto_increment PRIMARY KEY,
	TenDauSach NVARCHAR(100) NOT NULL,
	MaTheLoai INT NOT NULL
);

alter table DAUSACH 
add constraint DAUSACH_THELOAISACH_FK 
foreign key(MaTheLoai) references THELOAISACH(MaTheLoai);

CREATE TABLE CT_TACGIA
(
	MaDauSach INT ,
	MaTacGia INT NOT NULL ,
	CONSTRAINT PK_CTTACGIA PRIMARY KEY (MaDauSach,MaTacGia)
);

alter table CT_TACGIA 
add constraint CT_TACGIA_DAUSACH_FK 
foreign key(MaDauSach) references DAUSACH(MaDauSach);

alter table CT_TACGIA 
add constraint CT_TACGIA_TACGIA_FK 
foreign key(MaTacGia) references TACGIA(MaTacGia);

CREATE TABLE SACH
(
	MaSach INT auto_increment  PRIMARY KEY,
	MaDauSach INT NOT NULL ,
	NhaXuatBan NVARCHAR(100) NOT NULL,
	NamXuatBan INT NOT NULL,
	SoLuongTon INT NOT NULL DEFAULT 0,
	DonGiaNhap FLOAT NOT NULL DEFAULT 0
);

alter table SACH 
add constraint SACH_DAUSACH_FK 
foreign key(MaDauSach) references DAUSACH(MaDauSach);

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

CREATE TABLE BAOCAOCONGNO
(
	Thang INT NOT NULL,
	Nam INT NOT NULL,
	MaKhachHang INT NOT NULL ,
	NoDau FLOAT NOT NULL DEFAULT 0,
	PhatSinh FLOAT NOT NULL DEFAULT 0,
	NoCuoi FLOAT NOT NULL DEFAULT 0,
	CONSTRAINT PK_CTBAOCAONO PRIMARY KEY (Thang,Nam,MaKhachHang)
);

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