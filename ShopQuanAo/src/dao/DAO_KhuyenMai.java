package dao;

//package dao;
//
//import gui.thongbao;
//
//import java.sql.ResultSet;
//
//import connectDB.ConnectionManager;
//
//public class DAO_KhuyenMai {
//ConnectionManager connectionManager = new ConnectionManager();
//	public static ResultSet layKM() {
//		String sql = "SELECT * FROM [dbo].[KhuyenMai]";
//		return ConnectionManager.getdata(sql);
//	}
//
//	public static ResultSet layKMTheoMa(String maKhuyenMai) {
//		String sql = "SELECT * FROM [dbo].[KhuyenMai] where maKhuyenMai=N'"
//				+ maKhuyenMai + "'";
//		return ConnectionManager.getdata(sql);
//	}
//
//	public static void them(entity.KhuyenMai KM) {
//		String sql =  "INSERT INTO [dbo].[KhuyenMai] "
//		        + "           ([maKhuyenMai] "
//		        + "           ,[tenKhuyenMai] "
//		        + "           ,[moTa] "
//		        + "           ,[ngayBatDau] "
//		        + "           ,[ngayKetThuc] "
//		        + "           ,[phanTram]) "
//		        + "     VALUES "
//		        + "           (N'" + KM.getMaKhuyenMai() + "' "
//		        + "           ,N'" + KM.getTenKhuyenMai() + "' "
//		        + "           ,N'" + KM.getMoTa() + "' "
//		        + "           ,'" + KM.getNgayBatDau()+ "' "  // Thêm dấu ngoặc ' cho ngày
//		        + "           ,'" + KM.getNgayKetThuc()+ "' "  // Thêm dấu ngoặc ' cho ngày
//		        + "           ,N'" + KM.getPhanTram() + "' )";
//
//        int kq = ConnectionManager.executeTruyVan(sql);
//        if (kq > 0) {
//            thongbao.thongbao("Thêm thành công", "");
//        } else {
//            thongbao.thongbao("Thêm không thành công", "");
//        }
//    }
//
//}
import entity.KhuyenMai;
import gui.thongbao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bus.BUS_KhuyenMai;
import connectDB.ConnectionManager;

public class DAO_KhuyenMai {
	public static ResultSet layKM() {
		String sql = "SELECT * FROM [dbo].[KhuyenMai]";
		return ConnectionManager.getdata(sql);
	}

	public static KhuyenMai layKhuyenMaiTheoMa(String ma) {
		KhuyenMai km = null;
		ResultSet rs = ConnectionManager
				.getdata("select *from KhuyenMai where maKhuyenMai='" + ma
						+ "'");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ngayBD, ngayKT;

		try {
			while (rs.next()) {
				ngayBD = LocalDate.parse(rs.getString("ngayBatDau"), formatter);
				ngayKT = LocalDate
						.parse(rs.getString("ngayKetThuc"), formatter);
				km = new KhuyenMai(rs.getString("maKhuyenMai").trim(), rs
						.getString("tenKhuyenMai").trim(), rs.getString("moTa")
						.trim(), rs.getInt("phanTram"), ngayBD, ngayKT);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return km;
	}

	public static ResultSet layKMTheoMa(String MaKM) {
		String sql = "SELECT * FROM [dbo].[KhuyenMai] where maKhuyenMai = N'"
				+ MaKM + "'";
		return ConnectionManager.getdata(sql);
	}

	public static int layPhanTramTheoMaKM(String MaKM) {
		String sql = "SELECT * FROM [dbo].[KhuyenMai] where maKhuyenMai =N'"
				+ MaKM + "'";
		ResultSet rs = ConnectionManager.getdata(sql);
		int phanTram = 0;
		try {
			while (rs != null && rs.next()) {
				phanTram = rs.getInt("phanTram");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phanTram;
	}

	public static void them(entity.KhuyenMai KM) {
		String sql = "INSERT INTO [dbo].[KhuyenMai] "
				+ "([maKhuyenMai], [tenKhuyenMai], [moTa], [ngayBatDau], [ngayKetThuc], [phanTram]) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			ConnectionManager connectionManager = new ConnectionManager(); // Tạo
																			// kết
																			// nối
			Connection conn = connectionManager.conn;

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, KM.getMaKhuyenMai());
			pstmt.setString(2, KM.getTenKhuyenMai());
			pstmt.setString(3, KM.getMoTa());
			pstmt.setDate(4, java.sql.Date.valueOf(KM.getNgayBatDau()));
			pstmt.setDate(5, java.sql.Date.valueOf(KM.getNgayKetThuc()));

			pstmt.setInt(6, KM.getPhanTram());

			int kq = pstmt.executeUpdate();
			if (kq > 0) {
				thongbao.thongbao("Thêm thành công", "");
			} else {
				thongbao.thongbao("Thêm không thành công", "");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			thongbao.thongbao("Lỗi khi thêm dữ liệu", e.getMessage());
		}
	}

	public static ResultSet timKiem(String tukhoa, String phanTram,
			java.sql.Date sqlNgayBD, java.sql.Date sqlNgayKT) {
		String sql = "SELECT * FROM KhuyenMai WHERE (tenKhuyenMai LIKE N'%"
				+ tukhoa + "%' OR maKhuyenMai LIKE N'%" + tukhoa
				+ "%' OR ngayBatDau LIKE N'%" + tukhoa
				+ "%' OR ngayKetThuc LIKE N'%" + tukhoa + "%')";

		if (sqlNgayBD != null && sqlNgayKT != null) {
			sql += "and ngayBatDau >=" + "'" + sqlNgayBD + "'"
					+ "and ngayKetThuc <=" + "'" + sqlNgayKT + "'";
		}

		if (phanTram.equals(0)) {
			// Nếu phần trăm được cung cấp, thêm điều kiện vào câu truy vấn
			sql += " AND phanTram = " + phanTram;
		}

		return ConnectionManager.getdata(sql);
	}

}
