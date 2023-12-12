package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.portable.CustomValue;

import com.toedter.calendar.JDateChooser;

import bus.BUS_NhanVien;
import connectDB.ConnectionManager;
import entity.NhanVien;
import gui.thongbao;

public class DAO_NhanVien {
	public static ResultSet layNV() {
		String sql = "SELECT * FROM [dbo].[NhanVien]";
		return ConnectionManager.getdata(sql);
	}
	
	public static List<NhanVien> getDSNV(){
		ResultSet rs = ConnectionManager
				.getdata("select * from NhanVien ");
		List<NhanVien> ds= new ArrayList<>();
		try {
			while(rs.next()) {
				NhanVien nv= layNVTheoMa(rs.getString("maNhanVien").trim());
				ds.add(nv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	public static NhanVien layNVTheoMa(String ma) {
		ResultSet rs = ConnectionManager
				.getdata("select * from NhanVien where maNhanVien =N'" + ma
						+ "'");
		NhanVien nv = null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate a;
		try {
			while (rs.next()) {
				a = LocalDate.parse(rs.getString("ngayVaoLam"), formatter);
				nv = new NhanVien(rs.getString("maNhanVien"),
						rs.getString("tenNhanVien"), rs.getString("sdt"),
						rs.getString("email"), rs.getString("diaChi"),
						rs.getString("cccd"), rs.getBoolean("gioiTinh"), a,
						rs.getString("hinhAnh"));
			}
			return nv;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv;
	}

	public boolean them(NhanVien NV) {
		int i = 1;
		if (!NV.isGioiTinh()) {
			i = 0;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String sql = "insert into NhanVien ( maNhanVien , tenNhanVien,gioiTinh,email, ngayVaoLam,diaChi,sdt,cccd,hinhAnh ) values ( N'"
				+ NV.getMaNhanVien()
				+ "',N'"
				+ NV.getTenNhanVien()
				+ "','"
				+ String.valueOf(i)
				+ "',N'"
				+ NV.getEmail()
				+ "','"
				+ NV.getNgayVaoLam().format(formatter)
				+ "',N'"
				+ NV.getDiaChi()
				+ "','"
				+ NV.getSdt()
				+ "','"
				+ NV.getCccd()
				+ "','" + NV.getHinhAnh() + "')";
		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Thêm thành công", "");
			return true;
		} else {
			thongbao.thongbao("Thêm không thành công", "");
			return false;
		}
	}

	public boolean sua(NhanVien NV) {
		String sql = " update NhanVien set tenNhanVien = N'"
				+ NV.getTenNhanVien() + "',gioiTinh = '" + NV.isGioiTinh()
				+ "',email = N'" + NV.getEmail() + "',ngayVaoLam = '"
				+ NV.getNgayVaoLam() + "',diaChi = N'" + NV.getDiaChi()
				+ "' ,sdt = '" + NV.getSdt() + "',cccd='" + NV.getCccd()
				+ "',hinhAnh='" + NV.getHinhAnh() + "' where maNhanVien = '"
				+ NV.getMaNhanVien() + "'";
		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Sửa thành công", "");
			return true;
		} else {
			thongbao.thongbao("Sửa không thành công", "");
			return false;
		}
	}

	// tìm theo từ khóa
	public static ResultSet timKiem(String tukhoa) {
		String sql = "select *from NhaCungCap where TenNhaCungCap like N'%"
				+ tukhoa + "%' or MaNhaCungCap like N'%" + tukhoa
				+ "%' or tenNhaCungCap like N'%" + tukhoa
				+ "%' or email like N'%" + tukhoa + "%'";
		return ConnectionManager.getdata(sql);
	}
	
	
}
