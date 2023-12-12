package dao;

import java.sql.ResultSet;

import connectDB.ConnectionManager;

public class DAO_DoiTra {
	public static ResultSet layKH() {
		String sql = "SELECT * FROM [dbo].[KhachHang]";
		return ConnectionManager.getdata(sql);
	}

	public static ResultSet layKHTheoMa(String MaKH) {
		String sql = "SELECT * FROM [dbo].[KhachHang] where MaKhachHang =N'"
				+ MaKH + "'";
		return ConnectionManager.getdata(sql);
	}
}
