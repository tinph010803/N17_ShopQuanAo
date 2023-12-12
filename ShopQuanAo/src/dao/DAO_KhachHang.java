package dao;

import entity.KhachHang;
import gui.thongbao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectionManager;

public class DAO_KhachHang {
	public static ResultSet layKH() {
		String sql = "SELECT * FROM [dbo].[KhachHang]";
		return ConnectionManager.getdata(sql);
	}

	public static ResultSet layKHTheoMa(String MaKH) {
		String sql = "SELECT * FROM [dbo].[KhachHang] where maKhachHang =N'"
				+ MaKH + "'";
		return ConnectionManager.getdata(sql);
	}

	public static KhachHang layKHTheoma(String MaKH) {
		String sql = "SELECT * FROM [dbo].[KhachHang] where maKhachHang =N'"
				+ MaKH + "'";
		ResultSet rs = ConnectionManager.getdata(sql);
		KhachHang kh = null;
		try {
			while (rs.next()) {
				return new KhachHang(rs.getString("maKhachHang").trim(), rs
						.getString("tenKhachHang").trim(), rs.getString("sdt")
						.trim(), rs.getString("email").trim(),
						rs.getBoolean("gioiTinh"), rs.getDouble("soTienDaMua"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet layKHTheoSDT(String sdt) {
		String sql = "SELECT * FROM [dbo].[KhachHang] where sdt =N'" + sdt
				+ "'";
		return ConnectionManager.getdata(sql);
	}

	public static void them(entity.KhachHang KH) {
		String sql = "INSERT INTO [dbo].[KhachHang] "
				+ "([maKhachHang], [tenKhachHang], [sdt], [email], [gioiTinh], [soTienDaMua]) "
				+ "  VALUES " + "(N'" + KH.getMaKhachHang() + "', " + "N'"
				+ KH.getTenKhachHang() + "', " + "N'" + KH.getSdt() + "', "
				+ "N'" + KH.getEmail() + "', " + (KH.isGioiTinh() ? 1 : 0)
				+ ",0) ";
		int kq = ConnectionManager.executeTruyVan(sql);

		if (kq > 0) {
			thongbao.thongbao("Thêm thành công", "");
		} else {
			thongbao.thongbao("Thêm không thành công", "");
		}
	}

	public static void suaKH(entity.KhachHang KH) {
		String sql = "UPDATE [dbo].[KhachHang] " + "   SET [maKhachHang] = N'"
				+ KH.getMaKhachHang() + "' " + "      ,[tenKhachHang] =N'"
				+ KH.getTenKhachHang() + "' " + "      ,[gioiTinh] = '"
				+ KH.isGioiTinh() + "' " + "      ,[sdt] = '" + KH.getSdt()
				+ "' " + "      ,[email] = '" + KH.getEmail() + "' "
				+ " WHERE maKhachHang=N'" + KH.getMaKhachHang() + "'";
		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Sửa thành công", "");
		} else {
			thongbao.thongbao("Sửa không thành công", "");
		}
	}

	public static void capNhatSTDM(entity.KhachHang KH) {
		String sql = "UPDATE [dbo].[KhachHang] " + "   SET [soTienDaMua] = "
				+ KH.getSoTienDaMua() + " " + " WHERE maKhachHang=N'"
				+ KH.getMaKhachHang() + "'";
		ConnectionManager.executeTruyVan(sql);

	}

	public static ResultSet timKiem(String tukhoa, String bac, String gioiTinh) {

		String sql = "SELECT * FROM KhachHang WHERE (tenKhachHang LIKE N'%"
				+ tukhoa + "%' OR maKhachHang LIKE N'%" + tukhoa
				+ "%' OR sdt LIKE N'%" + tukhoa + "%' OR email LIKE N'%"
				+ tukhoa + "%')";

		String bacCondition = "";
		if (bac.trim().equalsIgnoreCase("Bạc")) {
			bacCondition = "soTienDaMua >= 5000000 AND soTienDaMua < 20000000";
		} else if (bac.trim().equalsIgnoreCase("Vàng")) {
			bacCondition = "soTienDaMua >= 20000000 AND soTienDaMua < 50000000";
		} else if (bac.trim().equalsIgnoreCase("Kim cương")) {
			bacCondition = "soTienDaMua >= 50000000";
		}

		if (!bacCondition.isEmpty()) {
			sql += " AND " + bacCondition;
		}

		String gioiTinhCondition = "";
		if (gioiTinh.trim().equalsIgnoreCase("Tất cả")) {
			// Không cần điều kiện
		} else if (gioiTinh.trim().equalsIgnoreCase("Nam")) {
			gioiTinhCondition = "gioiTinh = 1";
		} else if (gioiTinh.trim().equalsIgnoreCase("Nữ")) {
			gioiTinhCondition = "gioiTinh = 0";
		}

		if (!gioiTinhCondition.isEmpty()) {
			sql += " AND " + gioiTinhCondition;
		}

		return ConnectionManager.getdata(sql);
	}

	public static void truTienHDDT(String maKH, double sotientru) {
		KhachHang kh = DAO_KhachHang.layKHTheoma(maKH);
		Double tienHienCo = kh.getSoTienDaMua();
		Double tienDaTru = tienHienCo - sotientru;
		String sql = "UPDATE [dbo].[KhachHang] " + "   SET [soTienDaMua] = "
				+ tienDaTru + " " + " WHERE maKhachHang=N'" + maKH + "'";
	}
}
