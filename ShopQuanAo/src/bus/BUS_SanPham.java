package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.SanPham;
import gui.thongbao;

public class BUS_SanPham {
	public static boolean kt_Them(SanPham SP) {
		ResultSet rs = dao.DAO_SanPham.laySP();
		try {
			while (rs.next()) {
				if (rs.getString("maSanPham").matches(SP.getMaSanPham())) {
					thongbao.thongbao("Mã sản phẩm đã dùng", "");
					return false;
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BUS_SanPham.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		
		if (SP.getTenSanPham().trim().equals("")) {
			return false;
		}

		return true;
	}

	public static boolean kt_Sua(SanPham sp) {
		return true;
	}

	public static void timKiem(JTable tbl, String tukhoa, String loai) {
		DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
		tblModel.setRowCount(0);
		Object obj[] = new Object[5];
		ResultSet rs = dao.DAO_SanPham.timKiem(tukhoa, loai);
		int stt = 0;
		try {
			while (rs != null && rs.next()) {
				stt++;
				obj[0] = stt;
				obj[1] = rs.getString("maSanPham");
				obj[2] = rs.getString("tenSanPham");
				obj[3] = rs.getString("loai");
				obj[4] = rs.getInt("giaNhap");
				tblModel.addRow(obj);
			}
		} catch (SQLException ex) {
			System.out.println("Lỗi đổ table");
		}
	}

	public static void dodulieu(JTable tbl) {
		DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		tblModel.setRowCount(0);
		Object obj[] = new Object[5];
		ResultSet rs = dao.DAO_SanPham.laySP();
		int stt = 0;
		try {
			while (rs.next()) {
				if (rs.getObject("maKhuyenMai") == null) {
					stt++;
					obj[0] = stt;
					obj[1] = rs.getString("maSanPham");
					SanPham sp = new SanPham(rs.getString("maSanPham"));
					obj[2] = rs.getString("tenSanPham");
					obj[3] = rs.getString("loai");
					sp.setGiaNhap(rs.getInt("giaNhap"));
					obj[4] = decimalFormat.format(sp.getGiaBan());
					tblModel.addRow(obj);
				}
			}
		} catch (SQLException ex) {
			System.out.println("Lỗi đổ table");
		}
	}

	public static void timKiemThongKe(JTable tbl, String tukhoa, String loai) {
		DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
		tblModel.setRowCount(0);
		Object obj[] = new Object[5];
		ResultSet rs = dao.DAO_SanPham.timKiemThongKe(tukhoa, loai);
		try {
			while (rs.next()) {
				obj[0] = rs.getString("maSanPham");
				obj[1] = rs.getString("tenSanPham");
				obj[2] = rs.getString("loai");
				obj[3] = rs.getString("soluong");
				obj[4] = rs.getInt("tongTien");
				tblModel.addRow(obj);
			}
		} catch (SQLException ex) {
			System.out.println("Lỗi đổ table");
		}
	}

}
