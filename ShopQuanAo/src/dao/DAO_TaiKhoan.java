package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import bus.BUS_NhanVien;
import connectDB.ConnectionManager;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.thongbao;

public class DAO_TaiKhoan {
	 
	public ResultSet getDSNhanVien() {
		String sql = "SELECT * FROM [dbo].[TaiKhoan]";
		return ConnectionManager.getdata(sql);
	}
	
	public TaiKhoan getTKtheoMa(String ma) {
		TaiKhoan tk=null ;
		 
		ResultSet rs= ConnectionManager.getdata("select * from TaiKhoan where tenTaiKhoan= N'"+ma+"'");
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 LocalDate a;
		try {
			while(rs.next()) {	
				a= LocalDate.parse(rs.getString("ngayLap"), formatter);	
				tk = new TaiKhoan(rs.getString("tenTaiKhoan").trim(),rs.getString("matKhau").trim(),rs.getString("ghiChu"), null, rs.getBoolean("vaiTro"),a) ;
			}
			return tk;
		} 
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tk;
	}
}