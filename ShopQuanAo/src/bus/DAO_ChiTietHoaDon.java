package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gui.thongbao;
import connectDB.ConnectionManager;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class DAO_ChiTietHoaDon {
	public static void them(entity.ChiTietHoaDon cthd){
		 String sql = "INSERT INTO [dbo].[ChiTietHoaDon] "
		 + "([maSanPham], [maHoaDon], [soLuong], [khuyenMai], [thanhTien], [tienCuoiCung]) "
		 + "  VALUES "
		 + "(N'" + cthd.getSanPham().getMaSanPham() + "', "
		 + "N'" + cthd.getHoaDon().getMaHoaDon() + "', "
		 + cthd.getSoLuong() + ","
		 + cthd.getKhuyenMai() + ","
		 + cthd.getThanhTien() + ","
		 + cthd.getTienCuoiCung() +")";
		ConnectionManager.executeTruyVan(sql);
		 
//		int kq = ConnectionManager.executeTruyVan(sql);

//		if (kq > 0) {
//			thongbao.thongbao("Thêm thành công", "");
//		} else {
//			thongbao.thongbao("Thêm không thành công", "");
//		}
	}
	
private ConnectionManager connectionManager = new ConnectionManager();
	
	public static List<ChiTietHoaDon> layDSCTHDTheoMaHd(String maHD){
		DAO_SanPham daosp=new DAO_SanPham();
		DAO_HoaDon daohd= new DAO_HoaDon();
		List<ChiTietHoaDon> ds= new ArrayList<>();
		ResultSet rs= ConnectionManager.getdata("select * from ChiTietHoaDon where ChiTietHoaDon.maHoaDon = '"+maHD+"'");
		try {
			while(rs.next()) {
				String masp= rs.getString("maSanPham").trim();
				SanPham sp= daosp.laySanPhamTheoMa(masp);
				
				HoaDon hd= daohd.layHoaDonTheoMa(maHD.trim());
				
				int sl= rs.getInt("soLuong");
				Double tien = rs.getDouble("tienCuoiCung");
				ChiTietHoaDon a= new ChiTietHoaDon(sp, hd, sl,tien);
//				System.err.println(" CTHD :" +a);
				ds.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	
	public static ChiTietHoaDon layCTHDtheoMaSp(String maHD, String masp){
		DAO_SanPham daosp=new DAO_SanPham();
		DAO_HoaDon daohd= new DAO_HoaDon();
		ChiTietHoaDon cthd =null;
		ResultSet rs= ConnectionManager.getdata("select * from ChiTietHoaDon where ChiTietHoaDon.maHoaDon = '"+maHD+"' and ChiTietHoaDon.maSanPham='"+masp+"'");
		try {
			while(rs.next()) {
				String ma= rs.getString("maSanPham").trim();
				SanPham sp= daosp.laySanPhamTheoMa(ma);
				
				HoaDon hd= daohd.layHoaDonTheoMa(maHD.trim());
				
				int sl= rs.getInt("soLuong");
				Double tien = rs.getDouble("tienCuoiCung");
				cthd= new ChiTietHoaDon(sp, hd, sl,tien);
//			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cthd;
	}
}
