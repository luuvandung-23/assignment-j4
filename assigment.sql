create database ASM
go
---User
CREATE TABLE	User_s
(
	id_user  NVARCHAR(50) NOT NULL,
	passwor_d	NVARCHAR (50) ,
	email		NVARCHAR(50) ,
	fullname  NVARCHAR(50) ,
	diachi nvarchar(250),
	admi_n bit null,
	trangthai bit,
	CONSTRAINT PK_Users PRIMARY KEY (id_user)
	)

insert into User_s values ('u1','123','dungabc@gmail.com','dung','thaibinh',0,1)
insert into User_s values ('u2','123','dungabc@gmail.com','asd','thaibinh',0,1)
insert into User_s values ('u3','123','dungabc@gmail.com','bang','thaibinh',0,1)
insert into User_s values ('u4','123','dungabc@gmail.com','phuong','thaibinh',0,1)
insert into User_s values ('u5','123','dungabc@gmail.com','viet','thaibinh',0,1)
---danhmuc
CREATE TABLE	Danhmuc
(
	id_dm   NVARCHAR (20) NOT NULL,
	tendm	NVARCHAR (50)  
	CONSTRAINT PK_dm PRIMARY KEY (id_dm),
	)
insert into Danhmuc values ('dm1','kim cuong tim')
insert into Danhmuc values ('dm2','kim cuong xanh')
insert into Danhmuc values ('dm3','kim cuong trang')
----Share
CREATE TABLE	Sanpham
(
	id_sp  nvarchar(20) NOT NULL,
	dm_id nvarchar(20) ,
	tensp	NVARCHAR (50)  NULL,
	gia		float,
	anh nvarchar(250),
	mieuta NVARCHAR(max),
	kg int,
	soluong int,
	trangthai bit,
	CONSTRAINT PK_sanpham PRIMARY KEY (id_sp),
	CONSTRAINT FK_sanpham_danhmuc FOREIGN KEY (dm_id) REFERENCES Danhmuc,
	)
insert into Sanpham values ('sp1','dm1','kimcuongtim','3000','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY13mZqB3OM466H_4PlF1ZXwg31oNCfekveqItLyMRRdnD2Llljy_7fF2nQzSFpPIruP8&usqp=CAU','to ok','30',23,1)
insert into Sanpham values ('sp2','dm2','kimcuongtim','3000','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY13mZqB3OM466H_4PlF1ZXwg31oNCfekveqItLyMRRdnD2Llljy_7fF2nQzSFpPIruP8&usqp=CAU','to ok','30',23,1)
insert into Sanpham values ('sp3','dm3','kimcuongtim','3000','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY13mZqB3OM466H_4PlF1ZXwg31oNCfekveqItLyMRRdnD2Llljy_7fF2nQzSFpPIruP8&usqp=CAU','to ok','30',23,1)
insert into Sanpham values ('sp4','dm1','kimcuongtim','3000','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY13mZqB3OM466H_4PlF1ZXwg31oNCfekveqItLyMRRdnD2Llljy_7fF2nQzSFpPIruP8&usqp=CAU','to ok','30',23,1)
insert into Sanpham values ('sp5','dm2','kimcuongtim','3000','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY13mZqB3OM466H_4PlF1ZXwg31oNCfekveqItLyMRRdnD2Llljy_7fF2nQzSFpPIruP8&usqp=CAU','to ok','30',23,1)
insert into Sanpham values ('sp6','dm3','kimcuongtim','3000','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY13mZqB3OM466H_4PlF1ZXwg31oNCfekveqItLyMRRdnD2Llljy_7fF2nQzSFpPIruP8&usqp=CAU','to ok','30',23,1)
insert into Sanpham values ('sp7','dm1','kimcuongtim','3000','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY13mZqB3OM466H_4PlF1ZXwg31oNCfekveqItLyMRRdnD2Llljy_7fF2nQzSFpPIruP8&usqp=CAU','to ok','30',23,1)
CREATE TABLE hoadon
(
	id_hd  int IDENTITY(1,1) NOT NULL,
	id_user nvarchar(50) ,
	tenkh NVARCHAR (50)  NULL,
	tongtien float,
	ngaytao date,
	trangthai nvarchar(50),
	CONSTRAINT PK_hoadon PRIMARY KEY (id_hd),
	CONSTRAINT FK_hoadon_user FOREIGN KEY (id_user) REFERENCES User_s,
)
insert into hoadon values ('u1','dung',30000,'12/2/2021','danggiao')
insert into hoadon values ('u2','dung',30000,'12/2/2021','danggiao')
insert into hoadon values ('u3','dung',30000,'12/2/2021','danggiao')
insert into hoadon values ('u4','dung',30000,'12/2/2021','danggiao')
insert into hoadon values ('u5','dung',30000,'12/2/2021','danggiao')

CREATE TABLE hoadonct
(
	id_hd int NOT NULL,
	id_sp nvarchar(20),
	soluong int,
	gia float,
	thanhtien float,
	CONSTRAINT FK_hoadonct_hoadon FOREIGN KEY (id_hd) REFERENCES hoadon,
	CONSTRAINT FK_hoadonct_sanpham FOREIGN KEY (id_sp) REFERENCES Sanpham,
	CONSTRAINT PK_hoadonct PRIMARY KEY (id_hd,id_sp),
)
insert into hoadonct values('1','sp1',2,3000,6000)
insert into hoadonct values('2','sp1',2,3000,6000)
insert into hoadonct values('1','sp2',2,3000,6000)
insert into hoadonct values('1','sp3',2,3000,6000)
insert into hoadonct values('2','sp2',2,3000,6000)
