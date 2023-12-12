package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectionManager;

import entity.NhaCungCap;
import gui.thongbao;

public class DAO_NhaCungCap {
	public static ResultSet layNCC() {
		String sql = "SELECT * FROM [dbo].[NhaCungCap]";
		return ConnectionManager.getdata(sql);
	}

	public static String layMaNCCTheoTen(String ten) {
		String sql = "SELECT * FROM [dbo].[NhaCungCap] where tenNhaCungCap=N'"
				+ ten + "'";
		ResultSet rs = ConnectionManager.getdata(sql);
		try {
			while (rs.next()) {
				return rs.getString("maNhaCungCap");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	// tìm theo mã
	public static NhaCungCap layNCCTheoMa(String MaNCC) {
		String sql = "SELECT * FROM [dbo].[NhaCungCap] where maNhaCungCap =N'"
				+ MaNCC + "'";
		ResultSet rs = ConnectionManager.getdata(sql);
		try {
			while (rs.next())
				return new NhaCungCap(rs.getString("maNhaCungCap"),
						rs.getString("tenNhaCungCap"), rs.getString("diaChi"),
						rs.getString("sdt"), rs.getString("email"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getTenTheoMaSP(String ma) {
		ResultSet rs = ConnectionManager
				.getdata("select tenNhaCungCap from SanPham join NhaCungCap on SanPham.maNhaCungCap = NhaCungCap.maNhaCungCap where SanPham.maSanPham =N'"
						+ ma + "'");
		String a = "";
		try {
			while (rs.next()) {
				a += rs.getString("tenNhaCungCap").trim();
			}
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	// thêm nhà cung cấp
	public boolean them_ncc(entity.NhaCungCap ncc) {
		String sql = "insert into [dbo].[NhaCungCap]"
				+ " (	[maNhaCungCap] ,[tenNhaCungCap],[diaChi],[email],[sdt] )"
				+ " values (N'" + ncc.getMaNhaCungCap() + "',N'"
				+ ncc.getTenNhaCungCap() + "',N'" + ncc.getDiaChi() + "',N'"
				+ "" + ncc.getEmail() + "',N'" + ncc.getSdt() + "') ";

		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Thêm thành công", "");
			return true;
		} else {
			thongbao.thongbao("Thêm không thành công", "");
			return true;
		}
	}

	// sửa nhà cung cấp
	public static boolean sua_ncc(entity.NhaCungCap ncc) {
		String sql = "UPDATE [dbo].[NhaCungCap] "
				+ "   SET [maNhaCungCap] = N'" + ncc.getMaNhaCungCap() + "' "
				+ "	,[tenNhaCungCap] =N'" + ncc.getTenNhaCungCap() + "' "
				+ "      ,[sdt] = '" + ncc.getSdt() + "'  ,[email] = '"
				+ ncc.getEmail() + "',[diaChi]=N' " + ncc.getDiaChi()
				+ "'   WHERE maNhaCungCap=N'" + ncc.getMaNhaCungCap() + "'";
		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Sửa thành công", "");
			return true;
		} else {
			thongbao.thongbao("Sửa không thành công", "");
			return false;

		}
	}

	// xóa nhà cung cấp
	public static void xoa(String maNCC) {
		String sql = "DELETE FROM [dbo].[NhaCungCap] WHERE maNhaCungCap=N'"
				+ maNCC + "'";
		ConnectionManager.executeTruyVan(sql);
	}

	// tìm theo từ khóa
	public static ResultSet timKiem(String tukhoa) {
		String sql = "select * from NhaCungCap where (diaChi like N'%" + tukhoa
				+ "%' or sdt like N'%" + tukhoa
				+ "%' or tenNhaCungCap like N'%" + tukhoa
				+ "%' or email like N'%" + tukhoa + "%')";
		return ConnectionManager.getdata(sql);
	}

}
