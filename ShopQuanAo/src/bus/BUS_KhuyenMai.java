package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectionManager;
import entity.KhuyenMai;
import entity.SanPham;
import gui.thongbao;

public class BUS_KhuyenMai {
	ConnectionManager connectionManager = new ConnectionManager();
	public static boolean kt_Them(KhuyenMai KM) {
		ResultSet rs = dao.DAO_KhuyenMai.layKM();
		try{
			while (rs.next()) {
				if (rs.getString("maKhuyenMai").matches(KM.getMaKhuyenMai())) {
					thongbao.thongbao("Mã khuyến mãi đã dùng", "");
					return false;
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BUS_KhachHang.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		
		return true;
	}
	
	
public static void DeleteDataTable(DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}

	public static void dodulieu(JTable tbl) {
		DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date ngayBD,ngayKT;
		DeleteDataTable(tblModel);
		Object obj[] = new Object[6];
		ResultSet rs = dao.DAO_KhuyenMai.layKM();
		try {
			while (rs.next()) {
				obj[0] = rs.getString("maKhuyenMai");
				obj[1] = rs.getString("tenKhuyenMai");
//				obj[2] = rs.getString("phanTram");
				obj[2] = rs.getInt("phanTram");
				ngayBD = rs.getDate("ngayBatDau");
				obj[3] = sdf.format(ngayBD);
				ngayKT=rs.getDate("ngayKetThuc");
				obj[4] = sdf.format(ngayKT);
				tblModel.addRow(obj);
			}
		} catch (SQLException ex) {
			System.out.println("Lỗi đổ table");
		}
	}
		
	public static void timKiem(JTable tbl, String tukhoa, String phanTram,  java.sql.Date sqlNgayBD,  java.sql.Date sqlNgayKT) {
		DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date ngayBD,ngayKT;
		DeleteDataTable(tblModel);
		tblModel.setRowCount(0);
		Object obj[] = new Object[5];
		ResultSet rs = dao.DAO_KhuyenMai.timKiem(tukhoa, phanTram,sqlNgayBD,sqlNgayKT);
		try {
			while (rs != null && rs.next()) {
				obj[0] = rs.getString("maKhuyenMai");
				obj[1] = rs.getString("tenKhuyenMai");
				obj[2] = rs.getInt("phanTram");
				ngayBD = rs.getDate("ngayBatDau");
				obj[3] = sdf.format(ngayBD);
				ngayKT=rs.getDate("ngayKetThuc");
				obj[4] = sdf.format(ngayKT);
				tblModel.addRow(obj);
			}
		} catch (SQLException ex) {
			System.out.println("Lỗi đổ table");
		}
	}
	public static void dodulieu_SanPham(JTable tbl,List<SanPham> ds) {
	    DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
	    DeleteDataTable(tblModel);
	    int i=1;
	   for (SanPham sp : ds) {
		    
		tblModel.addRow(new String[] {
				String.valueOf(i),
				sp.getMaSanPham().trim(),
				sp.getTenSanPham().trim(),
				sp.getLoai().getValue(),
				String.valueOf(sp.getGiaBan())
				
		});
		i++;
	}
	   
	}
}
