package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectionManager;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.SanPham;
import gui.thongbao;

public class DAO_ChiTietHoaDonDoiTra {
	public static boolean themDsChiTietHoaDon(ChiTietHoaDonDoiTra cthddt) {
		
		String sql =" insert into ChiTietHoaDonDoiTra (maHDDT, maSanPham,soLuong,tienTra) values ('"+cthddt.getHoaDonDoiTra().getMaHDDT()+"','"+cthddt.getSanPham().getMaSanPham()+"','"+cthddt.getSoLuong()+"','"+cthddt.getTienTra()+"')";
		System.err.println(sql);
		int kq= ConnectionManager.executeTruyVan(sql);
		if(kq>0)
		{
//		
			return true;
		}
	
		return false;
	}
	public static List<ChiTietHoaDonDoiTra> getDsCTHDDT_theomaHDDT(String maHDDT){
		DAO_SanPham daosp=new DAO_SanPham();
		DAO_HoaDon daohd= new DAO_HoaDon();
		
		List<ChiTietHoaDonDoiTra> ds= new ArrayList<>();
		
		ResultSet rs= ConnectionManager.getdata("select * from ChiTietHoaDonDoiTra where ChiTietHoaDonDoiTra.maHDDT = '"+maHDDT+"'");
		try {
			while(rs.next()) {
				String masp= rs.getString("maSanPham").trim();
				SanPham sp= daosp.laySanPhamTheoMa(masp);
				HoaDonDoiTra hddt= DAO_HoaDonDoiTra.getHDDT_TheoMa(maHDDT);
				int sl= rs.getInt("soLuong");
				Double tien = rs.getDouble("tienTra");
				ChiTietHoaDonDoiTra cthddt= new ChiTietHoaDonDoiTra(sp, hddt, sl, tien);
//				
				ds.add(cthddt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public static List<ChiTietHoaDonDoiTra> getDsCTHDDT_theoMaHoaDon(String maHD){
		DAO_SanPham daosp=new DAO_SanPham();
		DAO_HoaDon daohd= new DAO_HoaDon();
		
		List<ChiTietHoaDonDoiTra> ds= new ArrayList<>();
		ResultSet rs= ConnectionManager.getdata("select c.* from HoaDonDoiTra a join HoaDon b on a.maHoaDon= b.maHoaDon join ChiTietHoaDonDoiTra c on a.maHDDT= c.maHDDT\r\n"
				+ "where b.maHoaDon='"+maHD+"'");
		try {
			while(rs.next()) {
				String masp= rs.getString("maSanPham").trim();
				SanPham sp= daosp.laySanPhamTheoMa(masp);
				HoaDonDoiTra hddt= DAO_HoaDonDoiTra.getHDDT_TheoMa(maHD);
				int sl= rs.getInt("soLuong");
				Double tien = rs.getDouble("tienTra");
				ChiTietHoaDonDoiTra cthddt= new ChiTietHoaDonDoiTra(sp, hddt, sl, tien);
//				
				ds.add(cthddt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	
}	
