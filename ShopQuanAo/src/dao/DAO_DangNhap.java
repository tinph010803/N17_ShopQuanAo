package dao;

import java.sql.ResultSet;

import connectDB.ConnectionManager;

public class DAO_DangNhap {
	public static ResultSet layTKTheoMa(String TK) {
		String sql = "SELECT * FROM [dbo].[TaiKhoan] where tenTaiKhoan =N'"
				+ TK + "'";
		return ConnectionManager.getdata(sql);
	}

	public static ResultSet layTK() {

		String sql = "SELECT  * FROM [dbo].[TaiKhoan]";
		return ConnectionManager.getdata(sql);
	}
}
