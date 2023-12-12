package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectionManager;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import gui.thongbao;

public class DAO_HoaDonDoiTra {

	public static boolean themHoaDonDoiTra(HoaDonDoiTra hddt) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String sql = "insert into HoaDonDoiTra ( maHDDT,ngay,maHoaDon,maNhanVien,tongTienTra) values ('"
				+ hddt.getMaHDDT().trim()
				+ "','"
				+ hddt.getNgay().format(formatter)
				+ "','"
				+ hddt.getHoaDon().getMaHoaDon().trim()
				+ "','"
				+ hddt.getNhanVien().getMaNhanVien().trim()
				+ "' ,'"
				+ hddt.getTongTienHoanTra() + "')";
		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Đổi trả thành công", "Thông báo");
			return true;
		}
		thongbao.thongbao("Đổi trả thành không thành công", "Thông báo");
		return false;

	}

	public List<HoaDonDoiTra> getDsHoaDons() {
		ResultSet rs = ConnectionManager.getdata("select * from HoaDonDoiTra ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate a;
		List<HoaDonDoiTra> ds = new ArrayList<HoaDonDoiTra>();
		try {
			while (rs.next()) {
				a = LocalDate.parse(rs.getString("ngay"), formatter);

				ds.add(new HoaDonDoiTra(rs.getString("maHDDT").trim(),
						DAO_HoaDon.layHoaDonTheoMa(rs.getNString("maHoaDon")),
						a, DAO_NhanVien.layNVTheoMa(rs.getString("maNhanVien")
								.trim()), rs.getDouble("tongtientra")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	public static HoaDonDoiTra getHDDT_TheoMa(String ma) {
		ResultSet rs = ConnectionManager
				.getdata("select * from HoaDonDoiTra where maHDDT = '" + ma
						+ "'");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate a;
		try {
			while (rs.next()) {
				a = LocalDate.parse(rs.getString("ngay"), formatter);

				return new HoaDonDoiTra(rs.getString("maHDDT").trim(),
						DAO_HoaDon.layHoaDonTheoMa(rs.getNString("maHoaDon")),
						a, DAO_NhanVien.layNVTheoMa(rs.getString("maNhanVien")
								.trim()), rs.getDouble("tongtientra"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// mới thêm
	public List<HoaDonDoiTra> getDsHDDT_QuerrySQL(String sql) {
		ResultSet rs = ConnectionManager.getdata(sql);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate a;
		List<HoaDonDoiTra> ds = new ArrayList<HoaDonDoiTra>();
		try {
			while (rs.next()) {
				a = LocalDate.parse(rs.getString("ngay"), formatter);

				ds.add(new HoaDonDoiTra(rs.getString("maHDDT").trim(),
						DAO_HoaDon.layHoaDonTheoMa(rs.getNString("maHoaDon")),
						a, DAO_NhanVien.layNVTheoMa(rs.getString("maNhanVien")
								.trim()), rs.getDouble("tongtientra")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
}
