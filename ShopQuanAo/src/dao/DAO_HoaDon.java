package dao;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import gui.thongbao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectionManager;

public class DAO_HoaDon {
	public static ResultSet layHD() {
		String sql = "SELECT * FROM [dbo].[HoaDon]";
		return ConnectionManager.getdata(sql);
	}

	public List<HoaDon> layDSHoaDon() {
		ResultSet rs = ConnectionManager.getdata("select * from HoaDon ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate a;
		List<HoaDon> ds = new ArrayList<HoaDon>();
		try {
			while (rs.next()) {
				a = LocalDate.parse(rs.getString("ngay"), formatter);
				KhachHang kh;

				if (rs.getObject("maKhachHang") == null)
					kh = null;
				else
					kh = DAO_KhachHang.layKHTheoma(rs.getString("maKhachHang")
							.trim());
				ds.add(new HoaDon(rs.getString("maHoaDon").trim(), kh, a,
						DAO_NhanVien.layNVTheoMa(rs.getString("maNhanVien")
								.trim()), rs.getDouble("tienKhachDua"), rs
								.getDouble("tongtien")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	public static ResultSet layKHTheoMa(String MaKH) {
		String sql = "SELECT * FROM [dbo].[KhachHang] where MaKhachHang =N'"
				+ MaKH + "'";
		return ConnectionManager.getdata(sql);
	}

	public static HoaDon layHoaDonTheoMa(String ma) {
		ResultSet rs = ConnectionManager
				.getdata("select *from HoaDon Where maHoaDon = '" + ma + "'");
		HoaDon hd = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate a;
		KhachHang kh;
		try {
			while (rs.next()) {
				a = LocalDate.parse(rs.getString("ngay"), formatter);
				if (rs.getObject("maKhachHang") == null)
					kh = null;
				else
					kh = DAO_KhachHang.layKHTheoma(rs.getString("maKhachHang")
							.trim());
				hd = new HoaDon(rs.getString("maHoaDon"), kh, a,
						DAO_NhanVien.layNVTheoMa(rs.getString("maNhanVien")
								.trim()), rs.getDouble("tienKhachDua"),
						rs.getDouble("tongtien"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hd;
	}

	public List<HoaDon> timDSHD(String sql) {
		ResultSet rs = ConnectionManager.getdata(sql);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate a;
		List<HoaDon> ds = new ArrayList<HoaDon>();
		try {
			while (rs.next()) {
				a = LocalDate.parse(rs.getString("ngay"), formatter);
				KhachHang kh;
				if (rs.getObject("maKhachHang") == null)
					kh = null;
				else {
					kh = DAO_KhachHang.layKHTheoma(rs.getString("maKhachHang")
							.trim());
				}
				ds.add(new HoaDon(rs.getString("maHoaDon").trim(), kh, a,
						DAO_NhanVien.layNVTheoMa(rs.getString("maNhanVien")
								.trim()), rs.getDouble("tienKhachDua"), rs
								.getDouble("tongtien")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	public static void them(entity.HoaDon hd) {

		String sql = "INSERT INTO [dbo].[HoaDon] "
				+ "([maHoaDon], [maNhanVien], [maKhachHang], [ngay], [tienKhachDua], [VAT], [tongTien]) "
				+ "  VALUES (?, ?, ?, ?, ?, ?, ?)";
		int kq = 0;

		try {
			ConnectionManager connection = new ConnectionManager(); // Lấy kết
			// nối từ
			// ConnectionManager
			// của bạn
			Connection conn = connection.conn;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, hd.getMaHoaDon());
			preparedStatement.setString(2, hd.getNhanVien().getMaNhanVien());
			if (hd.getKhachHang() != null) {
				preparedStatement.setString(3, hd.getKhachHang()
						.getMaKhachHang());
			} else {
				preparedStatement.setNull(3, java.sql.Types.NCHAR);
			}
			java.util.Date today = new java.util.Date();
			preparedStatement.setDate(4, new java.sql.Date(today.getTime()));
			preparedStatement.setDouble(5, hd.getTienKhachDua());
			preparedStatement.setInt(6, 8);
			preparedStatement.setDouble(7, hd.getTongTienCanThu());

			kq = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (kq > 0) {
			thongbao.thongbao("Thanh toán thành công", "");
		} else {
			thongbao.thongbao("Thanh toán không thành công", "");
		}
	}
}
