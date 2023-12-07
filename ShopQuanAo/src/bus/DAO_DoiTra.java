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

	

//	 public static void sua(entity.KhachHang KH) {
//	 String sql = "UPDATE [dbo].[KhachHang] "
//	 + "   SET [MaKhachHang] = N'"+KH.getMaKH()+"' "
//	 + "      ,[MaLoaiKH] ="+KH.getLoaiKH().getMaLoaiKH()+" "
//	 + "      ,[TenKhachHang] =N'"+KH.getTenKH()+"' "
//	 + "      ,[Tuoi] = "+KH.getTuoi()+" "
//	 + "      ,[GioiTinh] = '"+KH.isGioiTinh()+"' "
//	 + "      ,[SoDienThoai] = '"+KH.getSDT()+"' "
//	 + "      ,[SoCCCD] = '"+KH.getCCCD()+"' "
//	 + " WHERE MaKhachHang=N'"+KH.getMaKH()+"'";
//	 int kq = connection.executeTruyVan(sql);
//	 if (kq > 0) {
//	 thongbao.thongbao("Sửa thành công", "");
//	 } else {
//	 thongbao.thongbao("Sửa không thành công", "");
//	 }
//	 }
	
//	public static void xoa(String MaNV) {
//        String sql = "DELETE FROM [dbo].[NhanVien] WHERE MaNhanVien=N'" + MaNV + "'";
//        connection.executeTruyVan(sql);
//    }

//	public static ResultSet timKiem(String tukhoa) {
//		String sql = "select *from KhachHang where TenKhachHang like N'%"
//				+ tukhoa + "%' or MaKhachHang like N'%" + tukhoa
//				+ "%' or SoDienThoai like N'%" + tukhoa
//				+ "%' or SoCCCD like N'%" + tukhoa + "%'";
//		return connection.getdata(sql);
//	}
}
