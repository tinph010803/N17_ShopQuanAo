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

	public static TaiKhoan getTKtheoMa(String ma) {
		TaiKhoan tk = null;
		ConnectionManager con = new ConnectionManager();
		ResultSet rs = ConnectionManager
				.getdata("select * from TaiKhoan where tenTaiKhoan= N'" + ma
						+ "'");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate a;
		try {
			while (rs.next()) {
				a = LocalDate.parse(rs.getString("ngayLap"), formatter);
				tk = new TaiKhoan(rs.getString("tenTaiKhoan").trim(), rs
						.getString("matKhau").trim(), rs.getString("ghiChu"),
						null, rs.getBoolean("vaiTro"), a);
			}
			return tk;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tk;
	}

	public boolean themTaiKhoan(TaiKhoan a) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int i = 1;
		if (a.isVaiTro())
			i = 0;
		String sql = "insert into TaiKhoan (tenTaiKhoan, matKhau, ngayLap,ghiChu,vaiTro,maNhanVien) values (N'"
				+ a.getTenTaiKhoan().trim()
				+ "',N'"
				+ a.getMatKhau()
				+ "','"
				+ a.getNgayLap().format(formatter)
				+ "',N'"
				+ a.getGhiChu()
				+ "'," + i + ",N'" + a.getTenTaiKhoan() + "')";

		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Thêm thành công", "");
			return true;
		} else {
			thongbao.thongbao("Thêm thất bại", "");
			return false;
		}
	}

	public boolean xoaTaiKhoan(String ma) {
		int kq = ConnectionManager
				.executeTruyVan("delete from TaiKhoan where tenTaiKhoan= N'"
						+ ma + "'");
		if (kq > 0) {
			thongbao.thongbao("Xóa thành công ", "");
			return true;
		} else {
			thongbao.thongbao("Xóa thất bại ", "");
			return false;
		}
	}

	public boolean suaTK(String ma, String mkMoi) {
		String sql = "Update TaiKhoan set matKhau = N'" + mkMoi
				+ "' where tenTaiKhoan = '" + ma + "'";
		// ResultSet rs = ConnectionManager.getdata(sql);
		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Sửa thành công", "");
			return true;
		} else {
			thongbao.thongbao("Sửa thất bại", "");
			return false;
		}

	}

}