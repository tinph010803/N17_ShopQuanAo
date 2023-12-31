USE [master]
GO
/****** Object:  Database [SFSHOP]    Script Date: 12/10/2023 5:39:02 PM ******/
CREATE DATABASE [SFSHOP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ShopQuanAo_Data', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\sfshop_Log.mdf' , SIZE = 10240KB , MAXSIZE = 1126400KB , FILEGROWTH = 20%)
 LOG ON 
( NAME = N'ShopQuanAo_Log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\sfshop_Log.ldf' , SIZE = 3072KB , MAXSIZE = 512000KB , FILEGROWTH = 1024KB )
GO
ALTER DATABASE [SFSHOP] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SFSHOP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SFSHOP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SFSHOP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SFSHOP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SFSHOP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SFSHOP] SET ARITHABORT OFF 
GO
ALTER DATABASE [SFSHOP] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [SFSHOP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SFSHOP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SFSHOP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SFSHOP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SFSHOP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SFSHOP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SFSHOP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SFSHOP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SFSHOP] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SFSHOP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SFSHOP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SFSHOP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SFSHOP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SFSHOP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SFSHOP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SFSHOP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SFSHOP] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SFSHOP] SET  MULTI_USER 
GO
ALTER DATABASE [SFSHOP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SFSHOP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SFSHOP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SFSHOP] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SFSHOP] SET DELAYED_DURABILITY = DISABLED 
GO
USE [SFSHOP]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHoaDon] [nchar](20) NOT NULL,
	[maSanPham] [nchar](20) NOT NULL,
	[soLuong] [int] NULL,
	[khuyenMai] [float] NULL,
	[thanhTien] [float] NULL,
	[tienCuoiCung] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[maSanPham] ASC,
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ChiTietHoaDonDoiTra]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDonDoiTra](
	[maHDDT] [nchar](20) NOT NULL,
	[maSanPham] [nchar](20) NOT NULL,
	[soLuong] [int] NOT NULL,
	[tienTra] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[maHDDT] ASC,
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [nchar](20) NOT NULL,
	[maNhanVien] [nchar](20) NOT NULL,
	[maKhachHang] [nchar](20) NULL,
	[ngay] [date] NOT NULL,
	[tienKhachDua] [float] NOT NULL,
	[VAT] [int] NOT NULL,
	[tongTien] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDonDoiTra]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonDoiTra](
	[maHDDT] [nchar](20) NOT NULL,
	[ngay] [date] NOT NULL,
	[maHoaDon] [nchar](20) NOT NULL,
	[maNhanVien] [nchar](20) NOT NULL,
	[tongTienTra] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[maHDDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nchar](20) NOT NULL,
	[tenKhachHang] [nvarchar](40) NOT NULL,
	[sdt] [nchar](10) NOT NULL,
	[email] [nvarchar](40) NULL,
	[gioiTinh] [bit] NULL DEFAULT ((1)),
	[soTienDaMua] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[makhuyenMai] [nchar](20) NOT NULL,
	[tenKhuyenMai] [nvarchar](50) NOT NULL,
	[moTa] [nvarchar](500) NULL,
	[ngayBatDau] [date] NULL DEFAULT (getdate()),
	[ngayKetThuc] [date] NULL DEFAULT (getdate()),
	[phanTram] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[makhuyenMai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[maNhaCungCap] [nchar](20) NOT NULL,
	[tenNhaCungCap] [nvarchar](50) NOT NULL,
	[diaChi] [nvarchar](50) NOT NULL,
	[email] [nvarchar](40) NOT NULL,
	[sdt] [nchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhaCungCap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nchar](20) NOT NULL,
	[tenNhanVien] [nvarchar](40) NOT NULL,
	[gioiTinh] [bit] NULL DEFAULT ((1)),
	[email] [nvarchar](70) NOT NULL,
	[ngayVaoLam] [date] NOT NULL,
	[diaChi] [nvarchar](50) NOT NULL,
	[sdt] [nchar](10) NOT NULL,
	[cccd] [nchar](12) NOT NULL,
	[hinhAnh] [nvarchar](max) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSanPham] [nchar](20) NOT NULL,
	[tenSanPham] [nvarchar](50) NOT NULL,
	[loai] [nvarchar](20) NOT NULL,
	[maNhaCungCap] [nchar](20) NOT NULL,
	[moTa] [nvarchar](500) NULL,
	[chatLieu] [nvarchar](30) NOT NULL,
	[mau] [nvarchar](20) NOT NULL,
	[kichThuoc] [char](3) NOT NULL,
	[giaNhap] [float] NOT NULL,
	[soluong] [int] NOT NULL,
	[nhanHieu] [nvarchar](20) NOT NULL,
	[hinhAnh] [nvarchar](max) NOT NULL,
	[maKhuyenMai] [nchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 12/10/2023 5:39:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[tenTaiKhoan] [nchar](20) NOT NULL,
	[matKhau] [nvarchar](20) NOT NULL,
	[ngayLap] [date] NOT NULL,
	[ghiChu] [nvarchar](500) NULL,
	[vaiTro] [bit] NOT NULL,
	[maNhanVien] [nchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[tenTaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312090001      ', N'SP0001071           ', 2, 0, 800000, 864000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312090001      ', N'SP0001072           ', 1, 0, 400000, 432000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100001      ', N'SP0001072           ', 3, 0, 1200000, 1296000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100003      ', N'SP0001072           ', 2, 0, 800000, 864000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100004      ', N'SP0001072           ', 1, 0, 400000, 432000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100006      ', N'SP0001072           ', 2, 0, 800000, 864000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100005      ', N'SP0001073           ', 3, 0, 1200000, 1296000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312090002      ', N'SP0002052           ', 1, 0, 720000, 777600)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312090003      ', N'SP0003052           ', 1, 0, 700000, 756000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312090004      ', N'SP0003052           ', 1, 0, 700000, 756000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312090005      ', N'SP0003052           ', 1, 0, 700000, 756000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100001      ', N'SP0003052           ', 2, 0, 1400000, 1512000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100005      ', N'SP0003053           ', 2, 0, 1400000, 1512000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100006      ', N'SP0004032           ', 1, 0, 700000, 756000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100004      ', N'SP0005042           ', 2, 0, 1380000, 1490400)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100006      ', N'SP0006052           ', 2, 0, 1380000, 1490400)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100005      ', N'SP0006053           ', 2, 0, 1380000, 1490400)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100001      ', N'SP0007091           ', 2, 0, 1400000, 1512000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100004      ', N'SP0007092           ', 2, 0, 1400000, 1512000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100002      ', N'SP0009042           ', 2, 0, 600000, 648000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100002      ', N'SP0012042           ', 2, 0, 550000, 594000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100002      ', N'SP0017042           ', 2, 0, 615000, 664200)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100003      ', N'SP0017042           ', 2, 0, 615000, 664200)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100003      ', N'SP0019042           ', 2, 0, 475000, 513000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100003      ', N'SP0020041           ', 2, 0, 840000, 907200)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100002      ', N'SP0020042           ', 2, 0, 840000, 907200)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100004      ', N'SP0020042           ', 2, 0, 840000, 907200)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) VALUES (N'HD202312100004      ', N'SP0021092           ', 2, 0, 825000, 891000)


INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023120900001   ', N'SP0001071           ', 1, 302400)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000001   ', N'SP0012042           ', 1, 207900)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000001   ', N'SP0020042           ', 1, 317520)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000002   ', N'SP0009042           ', 1, 226800)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000002   ', N'SP0017042           ', 1, 232470)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000003   ', N'SP0007092           ', 1, 476280)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000003   ', N'SP0021092           ', 1, 280665)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000004   ', N'SP0012042           ', 1, 207900)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000005   ', N'SP0001073           ', 1, 302400)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000005   ', N'SP0003053           ', 1, 529200)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000006   ', N'SP0001072           ', 1, 302400)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000006   ', N'SP0007091           ', 1, 529200)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000007   ', N'SP0001072           ', 1, 302400)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000007   ', N'SP0006052           ', 1, 521640)
INSERT [dbo].[ChiTietHoaDonDoiTra] ([maHDDT], [maSanPham], [soLuong], [tienTra]) VALUES (N'HDDT2023121000008   ', N'SP0003052           ', 1, 529200)


INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312090001      ', N'NV01                ', N'KH202312090001      ', CAST(N'2023-12-09' AS Date), 1300000, 8, 1296000)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312090002      ', N'NV01                ', N'KH202312090002      ', CAST(N'2023-12-09' AS Date), 800000, 8, 777600)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312090003      ', N'NV01                ', N'KH202312090003      ', CAST(N'2023-12-09' AS Date), 800000, 8, 756000)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312090004      ', N'NV01                ', N'KH202312090004      ', CAST(N'2023-12-09' AS Date), 800000, 8, 756000)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312090005      ', N'NV01                ', N'KH202312090005      ', CAST(N'2023-12-09' AS Date), 800000, 8, 756000)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312100001      ', N'NV01                ', N'KH202312100001      ', CAST(N'2023-12-10' AS Date), 4500000, 8, 4320000)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312100002      ', N'NV01                ', N'KH202312100002      ', CAST(N'2023-12-10' AS Date), 2900000, 8, 2813400)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312100003      ', N'NV01                ', N'KH202312100003      ', CAST(N'2023-12-10' AS Date), 3000000, 8, 2948400)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312100004      ', N'NV01                ', N'KH202312100004      ', CAST(N'2023-12-10' AS Date), 5300000, 8, 5232600)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312100005      ', N'NV01                ', N'KH202312100005      ', CAST(N'2023-12-10' AS Date), 4300000, 8, 4298400)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) VALUES (N'HD202312100006      ', N'NV01                ', N'KH202312090001      ', CAST(N'2023-12-10' AS Date), 3200000, 8, 3110400)


INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023120900001   ', CAST(N'2023-12-09' AS Date), N'HD202312090001      ', N'NV01                ', 302400)
INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023121000001   ', CAST(N'2023-12-10' AS Date), N'HD202312100002      ', N'NV01                ', 525420)
INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023121000002   ', CAST(N'2023-12-10' AS Date), N'HD202312100002      ', N'NV01                ', 459270)
INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023121000003   ', CAST(N'2023-12-10' AS Date), N'HD202312100004      ', N'NV01                ', 756945)
INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023121000004   ', CAST(N'2023-12-10' AS Date), N'HD202312100002      ', N'NV01                ', 207900)
INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023121000005   ', CAST(N'2023-12-10' AS Date), N'HD202312100005      ', N'NV01                ', 831600)
INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023121000006   ', CAST(N'2023-12-10' AS Date), N'HD202312100001      ', N'NV01                ', 831600)
INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023121000007   ', CAST(N'2023-12-10' AS Date), N'HD202312100006      ', N'NV01                ', 824040)
INSERT [dbo].[HoaDonDoiTra] ([maHDDT], [ngay], [maHoaDon], [maNhanVien], [tongTienTra]) VALUES (N'HDDT2023121000008   ', CAST(N'2023-12-10' AS Date), N'HD202312100001      ', N'NV01                ', 529200)


INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312090001      ', N'Hoàng Khiêm', N'0351241411', N'khiem@gmail.com', 1, 4406400)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312090002      ', N'Lương Tấn Đạt', N'0388884418', N'tandat11@gmail.com', 1, 777600)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312090003      ', N'Trần Hữu Độ', N'0966989156', N'huudo992@gmail.com', 1, 756000)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312090004      ', N'Nguyễn Thanh Hữu', N'0372890765', N'thanhuu1234@gmail.com', 1, 756000)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312090005      ', N'Huỳnh Thị Nga', N'0933548962', N'huynhthinga091@gmail.com', 0, 756000)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312100001      ', N'Nguyễn Bá Huy', N'0399822112', N'nguyenbahuy11@gmail.com', 1, 4320000)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312100002      ', N'Huỳnh Bảo Hân', N'0988199221', N'baohan1998@gmail.com', 0, 2813400)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312100003      ', N'Dương Tuệ Mẫn', N'0988177233', N'manduong2000@gmail.com', 0, 2948400)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312100004      ', N'Vi Tiểu Bảo', N'0300919292', N'tieubaobao99@gmail.com', 1, 5232600)
INSERT [dbo].[KhachHang] ([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) VALUES (N'KH202312100005      ', N'Nguyễn Bá Lộc', N'0992771123', N'balocccc10@gmail.com', 1, 4298400)


INSERT [dbo].[KhuyenMai] ([makhuyenMai], [tenKhuyenMai], [moTa], [ngayBatDau], [ngayKetThuc], [phanTram]) VALUES (N'KM2023121001        ', N'Noel', N'', CAST(N'2023-12-10' AS Date), CAST(N'2023-12-31' AS Date), 20)
INSERT [dbo].[KhuyenMai] ([makhuyenMai], [tenKhuyenMai], [moTa], [ngayBatDau], [ngayKetThuc], [phanTram]) VALUES (N'KM2023121002        ', N'Giáng Sinh', N'', CAST(N'2023-12-10' AS Date), CAST(N'2023-12-31' AS Date), 19)
INSERT [dbo].[KhuyenMai] ([makhuyenMai], [tenKhuyenMai], [moTa], [ngayBatDau], [ngayKetThuc], [phanTram]) VALUES (N'KM2023121003        ', N'Cuối Tuần', N'', CAST(N'2023-12-10' AS Date), CAST(N'2023-12-11' AS Date), 15)
INSERT [dbo].[KhuyenMai] ([makhuyenMai], [tenKhuyenMai], [moTa], [ngayBatDau], [ngayKetThuc], [phanTram]) VALUES (N'KM2023121004        ', N'Khuyến Mãi Của Tháng', N'', CAST(N'2023-12-10' AS Date), CAST(N'2023-12-31' AS Date), 25)


INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC01               ', N'Bad Habits', N'93 Rạch Bùng Binh,Phường 9, Quận 3', N'SUPPORT@DOUBLEBADSTUDIO.COM', N'0931610291')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC02               ', N'Degrey', N'43 Huỳnh Văn Bánh P.17 Q.Phú Nhuận', N'degrey.vn@gmail.com', N'0336311117')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC03               ', N'Gori', N'68/21 Hoàng Diệu, Phường 12, Quận 4. TP HCM', N'info@dosiinvn.com', N'0906880960')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC04               ', N'Sly', N'112/23B Bạch Đằng, Phường 2, Tân Bình, TP HCM', N'info@slyclothing.vn', N'0922811881')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC05               ', N'Mende', N'585/2C Sư Vạn Hạnh, Phường 13, Quận 10, TP. HCM', N'mendeteam2409@gmail.com', N'0785988252')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC06               ', N'Hades', N'69 QUANG TRUNG STREET, GO VAP DISTRICT, HOCHIMINH.', N'contact@hades.vn', N'0387301102')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC07               ', N'Clownz', N'45 Núi Trúc, Ba Đình, HN', N'duong@clownz.vn', N'0586608660')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC08               ', N'Now SaiGon', N'445 Sư Vạn Hạnh, P.12, Q.10.', N'nowsaigon1@gmail.com', N'0933782768')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC09               ', N'High Club', N'280B3 đường Lương Định Của, P.An Phú, TP Thủ Đức', N'support@highclub.vn', N'0904821393')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [diaChi], [email], [sdt]) VALUES (N'NCC10               ', N'Davies', N'K46/60 Cao Thắng, P.Thanh Bình, Q.Hải Châu, TP ĐN', N'davies@gmail.com', N'0899966116')


INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV01                ', N'Phan Thanh Nam', 1, N'phanthanhnam01@gmail.com', CAST(N'2021-11-11' AS Date), N'Long An', N'0369987612', N'079203049817', N'NV01.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV02                ', N'Phan Hữu Tín', 1, N'Tin@gmail.com', CAST(N'2021-12-11' AS Date), N'Bình Tân', N'0979872134', N'079202710928', N'NV02.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV03                ', N'Nguyễn Trọng Tính', 1, N'nguyentrongtinh4803@gmail.com', CAST(N'2021-07-22' AS Date), N'Củ Chi', N'0388922132', N'079203001238', N'NV03.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV04                ', N'Đinh Thanh Tòng', 1, N'Tong@gmail.com', CAST(N'2022-05-05' AS Date), N'Đồng Tháp', N'0399221912', N'079200888199', N'NV04.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV05                ', N'Lê Thanh Tâm', 0, N'lethanhtam080@gmail.com', CAST(N'2022-11-11' AS Date), N'Tp Hồ Chí Minh', N'0989382888', N'079198998172', N'NV05.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV06                ', N'Trương Đình Bảo', 1, N'truongdinhbao221@gmail.com', CAST(N'2022-12-11' AS Date), N'Tp Hồ Chí Minh', N'0357682358', N'079201829101', N'NV06.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV07                ', N'Huỳnh Như Phụng', 0, N'huynhphung803@gmail.com', CAST(N'2023-07-22' AS Date), N'Bình Dương', N'0359835729', N'079300878273', N'NV07.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV08                ', N'Võ Thị Huyền Trang', 0, N'huyentrang3902@gmail.com', CAST(N'2023-01-23' AS Date), N'Tây Nguyên', N'0988112993', N'079303888162', N'NV08.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV09                ', N'Lê Chí Bảo', 1, N'lechibaovv12@gmail.com', CAST(N'2023-02-15' AS Date), N'Bình Dương', N'0921345123', N'079096779192', N'NV09.png')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [email], [ngayVaoLam], [diaChi], [sdt], [cccd], [hinhAnh]) VALUES (N'NV10                ', N'Nguyễn Thị Bảo Ngọc', 0, N'ntbaongoc09221@gmail.com', CAST(N'2023-02-11' AS Date), N'Tp Hồ Chí Minh', N'0330220922', N'079300015437', N'NV10.png')


INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0001071           ', N'B-Rocker Tee', N'Áo', N'NCC01               ', N'', N'Cotton', N'Xanh dương', N'S  ', 160000, 48, N'BADHABITS', N'bhb_4.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0001072           ', N'B-Rocker Tee', N'Áo', N'NCC01               ', N'', N'Cotton', N'Xanh dương', N'M  ', 160000, 41, N'BADHABITS', N'bhb_4.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0001073           ', N'B-Rocker Tee', N'Áo', N'NCC01               ', N'', N'Cotton', N'Xanh dương', N'L  ', 160000, 47, N'BADHABITS', N'bhb_4.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0001074           ', N'B-Rocker Tee', N'Áo', N'NCC01               ', N'', N'Cotton', N'Xanh dương', N'XL ', 160000, 50, N'BADHABITS', N'bhb_4.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0002052           ', N'Zipper Hoodie', N'Áo', N'NCC01               ', N'', N'Nỉ', N'Đen', N'M  ', 288000, 49, N'BADHABITS', N'bhb_5.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0002053           ', N'Zipper Hoodie', N'Áo', N'NCC01               ', N'', N'Nỉ', N'Đen', N'L  ', 288000, 50, N'BADHABITS', N'bhb_5.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0002054           ', N'Zipper Hoodie', N'Áo', N'NCC01               ', N'', N'Nỉ', N'Đen', N'XL ', 288000, 50, N'BADHABITS', N'bhb_5.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0003052           ', N'Explorer Scouts Shirt', N'Áo', N'NCC01               ', N'', N'Dù', N'Đen', N'M  ', 280000, 45, N'BADHABITS', N'bhb_6.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0003053           ', N'Explorer Scouts Shirt', N'Áo', N'NCC01               ', N'', N'Dù', N'Đen', N'L  ', 280000, 48, N'BADHABITS', N'bhb_6.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0003054           ', N'Explorer Scouts Shirt', N'Áo', N'NCC01               ', N'', N'Dù', N'Đen', N'XL ', 280000, 50, N'BADHABITS', N'bhb_6.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0004031           ', N'Explorer Scouts Shirt', N'Áo', N'NCC01               ', N'', N'Dù', N'Cam', N'S  ', 280000, 50, N'BADHABITS', N'bhb_7.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0004032           ', N'Explorer Scouts Shirt', N'Áo', N'NCC01               ', N'', N'Dù', N'Cam', N'M  ', 280000, 49, N'BADHABITS', N'bhb_7.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0004033           ', N'Explorer Scouts Shirt', N'Áo', N'NCC01               ', N'', N'Dù', N'Cam', N'L  ', 280000, 50, N'BADHABITS', N'bhb_7.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0005042           ', N'Bad Destroyed Sweatpants', N'Quần', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'M  ', 276000, 48, N'BADHABITS', N'bhb_8.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0005043           ', N'Bad Destroyed Sweatpants', N'Quần', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'L  ', 276000, 50, N'BADHABITS', N'bhb_8.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0005044           ', N'Bad Destroyed Sweatpants', N'Quần', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'XL ', 276000, 50, N'BADHABITS', N'bhb_8.png', N'KM2023121004        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0006052           ', N'Bad Destroyed Sweatpants', N'Quần', N'NCC01               ', N'', N'Nỉ', N'Đen', N'M  ', 276000, 48, N'BADHABITS', N'bhb_9.png', N'KM2023121004        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0006053           ', N'Bad Destroyed Sweatpants', N'Quần', N'NCC01               ', N'', N'Nỉ', N'Đen', N'L  ', 276000, 48, N'BADHABITS', N'bhb_9.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0006054           ', N'Bad Destroyed Sweatpants', N'Quần', N'NCC01               ', N'', N'Nỉ', N'Đen', N'XL ', 276000, 50, N'BADHABITS', N'bhb_9.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0007091           ', N'Multiple Pocket Pants', N'Quần', N'NCC01               ', N'', N'Dù', N'Be', N'S  ', 280000, 48, N'BADHABITS', N'bhb_10.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0007092           ', N'Multiple Pocket Pants', N'Quần', N'NCC01               ', N'', N'Dù', N'Be', N'M  ', 280000, 48, N'BADHABITS', N'bhb_10.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0007093           ', N'Multiple Pocket Pants', N'Quần', N'NCC01               ', N'', N'Dù', N'Be', N'L  ', 280000, 50, N'BADHABITS', N'bhb_10.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0007094           ', N'Multiple Pocket Pants', N'Quần', N'NCC01               ', N'', N'Dù', N'Be', N'XL ', 280000, 50, N'BADHABITS', N'bhb_10.png', N'KM2023121001        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0008091           ', N'Váy kiểu', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Be', N'S  ', 100000, 50, N'CLOWNZ', N'vay01.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0008092           ', N'Váy kiểu', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Be', N'M  ', 100000, 50, N'CLOWNZ', N'vay01.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0008093           ', N'Váy kiểu', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Be', N'L  ', 100000, 50, N'CLOWNZ', N'vay01.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0008094           ', N'Váy kiểu', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Be', N'XL ', 100000, 50, N'CLOWNZ', N'vay01.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0009041           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Trắng', N'S  ', 120000, 50, N'CLOWNZ', N'vay02_Trang.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0009042           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Trắng', N'M  ', 120000, 48, N'CLOWNZ', N'vay02_Trang.png', N'KM2023121004        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0009043           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Trắng', N'L  ', 120000, 50, N'CLOWNZ', N'vay02_Trang.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0009044           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Trắng', N'XL ', 120000, 50, N'CLOWNZ', N'vay02_Trang.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0010051           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Đen', N'S  ', 120000, 50, N'CLOWNZ', N'vay02_Den.png', N'KM2023121002        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0010052           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Đen', N'M  ', 120000, 50, N'CLOWNZ', N'vay02_Den.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0010053           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Đen', N'L  ', 120000, 50, N'CLOWNZ', N'vay02_Den.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0010054           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Đen', N'XL ', 120000, 50, N'CLOWNZ', N'vay02_Den.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0011101           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Hồng', N'S  ', 120000, 50, N'CLOWNZ', N'vay02_Hong.png', N'KM2023121004        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0011102           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Hồng', N'M  ', 120000, 50, N'CLOWNZ', N'vay02_Hong.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0011103           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Hồng', N'L  ', 120000, 50, N'CLOWNZ', N'vay02_Hong.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0011104           ', N'Váy xòe', N'Váy', N'NCC01               ', N'', N'Cotton', N'Hồng', N'XL ', 120000, 50, N'CLOWNZ', N'vay02_Hong.png', N'KM2023121004        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0012041           ', N'Váy dài', N'Váy', N'NCC01               ', N'', N'Cotton', N'Trắng', N'S  ', 110000, 50, N'CLOWNZ', N'vay03.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0012042           ', N'Váy dài', N'Váy', N'NCC01               ', N'', N'Cotton', N'Trắng', N'M  ', 110000, 48, N'CLOWNZ', N'vay03.png', N'KM2023121004        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0012043           ', N'Váy dài', N'Váy', N'NCC01               ', N'', N'Cotton', N'Trắng', N'L  ', 110000, 50, N'CLOWNZ', N'vay03.png', N'KM2023121004        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0012044           ', N'Váy dài', N'Váy', N'NCC01               ', N'', N'Cotton', N'Trắng', N'XL ', 110000, 50, N'CLOWNZ', N'vay03.png', N'KM2023121004        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0013091           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Be', N'S  ', 130000, 50, N'CLOWNZ', N'vay04_Be.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0013092           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Be', N'M  ', 130000, 50, N'CLOWNZ', N'vay04_Be.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0013093           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Be', N'L  ', 130000, 50, N'CLOWNZ', N'vay04_Be.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0013094           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Be', N'XL ', 130000, 50, N'CLOWNZ', N'vay04_Be.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0014051           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Đen', N'S  ', 130000, 50, N'CLOWNZ', N'vay04_Den.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0014052           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Đen', N'M  ', 130000, 50, N'CLOWNZ', N'vay04_Den.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0014053           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Đen', N'L  ', 130000, 50, N'CLOWNZ', N'vay04_Den.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0014054           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Đen', N'XL ', 130000, 50, N'CLOWNZ', N'vay04_Den.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0015101           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Hồng', N'S  ', 130000, 50, N'CLOWNZ', N'vay04_Hong.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0015102           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Hồng', N'M  ', 130000, 50, N'CLOWNZ', N'vay04_Hong.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0015103           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Hồng', N'L  ', 130000, 50, N'CLOWNZ', N'vay04_Hong.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0015104           ', N'Váy hai dây', N'Váy', N'NCC01               ', N'', N'Nỉ', N'Hồng', N'XL ', 130000, 50, N'CLOWNZ', N'vay04_Hong.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0016041           ', N'Đầm dài', N'Đầm', N'NCC01               ', N'', N'Cotton', N'Trắng', N'S  ', 150000, 50, N'CLOWNZ', N'dam01.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0016042           ', N'Đầm dài', N'Đầm', N'NCC01               ', N'', N'Cotton', N'Trắng', N'M  ', 150000, 50, N'CLOWNZ', N'dam01.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0016043           ', N'Đầm dài', N'Đầm', N'NCC01               ', N'', N'Cotton', N'Trắng', N'L  ', 150000, 50, N'CLOWNZ', N'dam01.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0016044           ', N'Đầm dài', N'Đầm', N'NCC01               ', N'', N'Cotton', N'Trắng', N'XL ', 150000, 50, N'CLOWNZ', N'dam01.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0017041           ', N'Đầm họa tiết', N'Đầm', N'NCC01               ', N'', N'Cotton', N'Trắng', N'S  ', 123000, 50, N'CLOWNZ', N'dam02.png', N'KM2023121003        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0017042           ', N'Đầm họa tiết', N'Đầm', N'NCC01               ', N'', N'Cotton', N'Trắng', N'M  ', 123000, 46, N'CLOWNZ', N'dam02.png', N'KM2023121003        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0017043           ', N'Đầm họa tiết', N'Đầm', N'NCC01               ', N'', N'Cotton', N'Trắng', N'L  ', 123000, 50, N'CLOWNZ', N'dam02.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0017044           ', N'Đầm họa tiết', N'Đầm', N'NCC01               ', N'', N'Cotton', N'Trắng', N'XL ', 123000, 50, N'CLOWNZ', N'dam02.png', N'KM2023121003        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0018051           ', N'Đầm trễ vai', N'Đầm', N'NCC01               ', N'', N'Nỉ', N'Đen', N'S  ', 128000, 50, N'CLOWNZ', N'dam03.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0018052           ', N'Đầm trễ vai', N'Đầm', N'NCC01               ', N'', N'Nỉ', N'Đen', N'M  ', 128000, 50, N'CLOWNZ', N'dam03.png', N'KM2023121003        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0018053           ', N'Đầm trễ vai', N'Đầm', N'NCC01               ', N'', N'Nỉ', N'Đen', N'L  ', 128000, 50, N'CLOWNZ', N'dam03.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0018054           ', N'Đầm trễ vai', N'Đầm', N'NCC01               ', N'', N'Nỉ', N'Đen', N'XL ', 128000, 50, N'CLOWNZ', N'dam03.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0019041           ', N'Đầm công chúa', N'Đầm', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'S  ', 95000, 50, N'CLOWNZ', N'dam04.png', N'KM2023121003        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0019042           ', N'Đầm công chúa', N'Đầm', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'M  ', 95000, 48, N'CLOWNZ', N'dam04.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0019043           ', N'Đầm công chúa', N'Đầm', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'L  ', 95000, 50, N'CLOWNZ', N'dam04.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0019044           ', N'Đầm công chúa', N'Đầm', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'XL ', 95000, 50, N'CLOWNZ', N'dam04.png', N'KM2023121003        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0020041           ', N'Allyson Athlete Jersey', N'Áo', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'S  ', 168000, 48, N'HADES', N'hd_1.png', N'KM2023121003        ')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0020042           ', N'Allyson Athlete Jersey', N'Áo', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'M  ', 168000, 46, N'HADES', N'hd_1.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0020043           ', N'Allyson Athlete Jersey', N'Áo', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'L  ', 168000, 50, N'HADES', N'hd_1.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0020044           ', N'Allyson Athlete Jersey', N'Áo', N'NCC01               ', N'', N'Nỉ', N'Trắng', N'XL ', 168000, 50, N'HADES', N'hd_1.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0021091           ', N'Insurelady Tee', N'Áo', N'NCC01               ', N'', N'Cotton', N'Be', N'S  ', 165000, 50, N'HADES', N'hd_2.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0021092           ', N'Insurelady Tee', N'Áo', N'NCC01               ', N'', N'Cotton', N'Be', N'M  ', 165000, 48, N'HADES', N'hd_2.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0021093           ', N'Insurelady Tee', N'Áo', N'NCC01               ', N'', N'Cotton', N'Be', N'L  ', 165000, 50, N'HADES', N'hd_2.png', NULL)
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loai], [maNhaCungCap], [moTa], [chatLieu], [mau], [kichThuoc], [giaNhap], [soluong], [nhanHieu], [hinhAnh], [maKhuyenMai]) VALUES (N'SP0021094           ', N'Insurelady Tee', N'Áo', N'NCC01               ', N'', N'Cotton', N'Be', N'XL ', 165000, 50, N'HADES', N'hd_2.png', NULL)


INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau], [ngayLap], [ghiChu], [vaiTro], [maNhanVien]) VALUES (N'NV01                ', N'12345678', CAST(N'2021-11-11' AS Date), N' ', 1, N'NV01                ')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau], [ngayLap], [ghiChu], [vaiTro], [maNhanVien]) VALUES (N'NV02                ', N'12345678', CAST(N'2021-12-11' AS Date), N' ', 0, N'NV02                ')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau], [ngayLap], [ghiChu], [vaiTro], [maNhanVien]) VALUES (N'NV03                ', N'12345678', CAST(N'2021-07-22' AS Date), N' ', 1, N'NV03                ')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau], [ngayLap], [ghiChu], [vaiTro], [maNhanVien]) VALUES (N'NV04                ', N'12345678', CAST(N'2022-05-05' AS Date), N' ', 0, N'NV04                ')


ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietHoaDonDoiTra]  WITH CHECK ADD FOREIGN KEY([maHDDT])
REFERENCES [dbo].[HoaDonDoiTra] ([maHDDT])
GO
ALTER TABLE [dbo].[ChiTietHoaDonDoiTra]  WITH CHECK ADD FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDonDoiTra]  WITH CHECK ADD FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[HoaDonDoiTra]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([maKhuyenMai])
REFERENCES [dbo].[KhuyenMai] ([makhuyenMai])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([maNhaCungCap])
REFERENCES [dbo].[NhaCungCap] ([maNhaCungCap])
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
USE [master]
GO
ALTER DATABASE [SFSHOP] SET  READ_WRITE 
GO
