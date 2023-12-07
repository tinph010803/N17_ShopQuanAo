package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import connectDB.ConnectionManager;

public class DAO_ThongKe {
	public static int getTongSoHD() {
		int totalProducts = 0;
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;
		try {
			String query = "SELECT COUNT(maHoaDon) AS TotalHoaDon FROM HoaDon";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				totalProducts = resultSet.getInt("TotalHoaDon");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalProducts;
	}

	public static double getTongTienHoaDon() {
		double tongTien = 0;
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;
		try {
			String query = "SELECT SUM(tongTien) AS TongTien FROM HoaDon";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				tongTien = resultSet.getInt("TongTien");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tongTien;
	}

	public static double getTongVAT() {
		float tongVAT = 0;

		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;

		try {
//			String query = "SELECT maHoaDon, SUM(tongTien * VAT/100) AS totalVAT FROM HoaDon GROUP BY maHoaDon";
			String query = "SELECT maHoaDon, SUM(tongTien * 100/108*VAT/100) AS totalVAT FROM HoaDon GROUP BY maHoaDon";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				float vatCuaHoaDon = resultSet.getFloat("totalVAT");
				tongVAT += vatCuaHoaDon;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return tongVAT;
	}

	public static double getTongLoiNhuan() {
		double tongLoiNhuan = 0;

		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;

		try {
			String query = "SELECT SUM(loiNhuanHoaDon) AS tongLoiNhuan FROM ("
					+ "SELECT HoaDon.maHoaDon, "
					+ "SUM(ChiTietHoaDon.tienCuoiCung - (ChiTietHoaDon.soLuong * SanPham.giaNhap)) AS loiNhuanHoaDon "
					+ "FROM HoaDon "
					+ "JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon "
					+ "JOIN SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham "
					+ "GROUP BY HoaDon.maHoaDon) AS LoiNhuanHoaDonTable";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				tongLoiNhuan = resultSet.getDouble("tongLoiNhuan");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return tongLoiNhuan;
	}

	public static int getSoLuongHD(Date ngayBD, Date ngayKT) {
		int soLuong = 0;
		try {
			ConnectionManager connectionManager = new ConnectionManager();
			Connection conn = connectionManager.conn;

			String sqlQuery = "SELECT COUNT(*) AS SoLuong FROM HoaDon WHERE ngay BETWEEN ? AND ?";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setDate(1, new java.sql.Date(ngayBD.getTime()));
			statement.setDate(2, new java.sql.Date(ngayKT.getTime()));

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				soLuong = resultSet.getInt("SoLuong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soLuong;
	}

	public static double getTongTienHD(Date ngayBD, Date ngayKT) {
		double tongTien = 0;
		try {
			ConnectionManager connectionManager = new ConnectionManager();
			Connection conn = connectionManager.conn;

			String sqlQuery = "SELECT SUM(tongTien) AS TongTien FROM HoaDon WHERE ngay BETWEEN ? AND ?";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setDate(1, new java.sql.Date(ngayBD.getTime()));
			statement.setDate(2, new java.sql.Date(ngayKT.getTime()));

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				tongTien = resultSet.getDouble("TongTien");
				DecimalFormat df = new DecimalFormat("#,###");
				// lblTextTongTien.setText(df.format(tongTien));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tongTien;
	}

	public static double getTongVAT(Date ngayBD, Date ngayKT) {
		double tongVAT = 0;
		try {
			ConnectionManager connectionManager = new ConnectionManager();
			Connection conn = connectionManager.conn;
			String sqlQuery = "SELECT maHoaDon, SUM(tongTien * VAT/100) AS TongVAT FROM HoaDon WHERE ngay BETWEEN ? AND ? GROUP BY maHoaDon";

			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setDate(1, new java.sql.Date(ngayBD.getTime()));
			statement.setDate(2, new java.sql.Date(ngayKT.getTime()));

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				tongVAT = resultSet.getDouble("TongVAT");
				DecimalFormat df = new DecimalFormat("#,###");
				// lblTextVAT.setText(df.format(tongVAT));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tongVAT;
	}

	public static double getTongLoiNhuanTheoNgay(Date ngayBD, Date ngayKT) {
		double loiNhuan = 0;

		try {
			ConnectionManager connectionManager = new ConnectionManager();
			Connection conn = connectionManager.conn;
			String sqlQuery = "SELECT SUM(loinhuanHoaDon) AS TongLoiNhuan FROM ( "
					+ "    SELECT HoaDon.maHoaDon, "
					+ "           SUM(ChiTietHoaDon.tienCuoiCung - (ChiTietHoaDon.soLuong * SanPham.giaNhap)) AS loinhuanHoaDon "
					+ "    FROM HoaDon "
					+ "    JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon "
					+ "    JOIN SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham "
					+ "    WHERE HoaDon.ngay BETWEEN ? AND ? "
					+ "    GROUP BY HoaDon.maHoaDon ) AS LoiNhuanHoaDonTable";

			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setDate(1, new java.sql.Date(ngayBD.getTime()));
			statement.setDate(2, new java.sql.Date(ngayKT.getTime()));

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				loiNhuan = resultSet.getDouble("TongLoiNhuan");
				DecimalFormat df = new DecimalFormat("#,###");
				// lblTextVAT.setText(df.format(tongVAT));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loiNhuan;
	}

}
