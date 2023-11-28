package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.KhachHang;
import gui.thongbao;

public class BUS_KhachHang {
	public static boolean kt_Them(KhachHang KH) {
		ResultSet rs = dao.DAO_KhachHang.layKH();
		try {
			while (rs.next()) {
				if (rs.getString("maKhachHang").matches(KH.getMaKhachHang())) {
					thongbao.thongbao("Mã khách hàng đã dùng", "");
					return false;
				}
				if (rs.getString("sdt").matches(KH.getSdt())) {
					thongbao.thongbao("Số điện thoại đã dùng", "");
					return false;
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BUS_KhachHang.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		if (!(KH.getEmail()
				.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
			thongbao.thongbao("Nhập lại Email", "");
			return false;
		}

		if (!(KH.getSdt().matches("0(3|5|7|8|9)[0-9]{8}"))) {
			thongbao.thongbao("Nhập lại số điện thoại", "");
			return false;
		}

		return true;
	}

	public static boolean kt_Sua(KhachHang KH) {
		ResultSet rs = dao.DAO_KhachHang.layKH();
		try {
			while (rs.next()) {
				if (!KH.getMaKhachHang().equals(rs.getString("maKhachHang").trim())) {
					if (KH.getSdt().equals(rs.getString("sdt"))) {
						thongbao.thongbao("Số điện thoại đã có người dùng", "");
						return false;
					}	
				}

			}
		} catch (SQLException ex) {
			Logger.getLogger(BUS_KhachHang.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		if (!(KH.getEmail()
				.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
			thongbao.thongbao("Nhập lại Email", "");
			return false;
		}

		if (!(KH.getSdt().matches("0(3|5|7|8|9)[0-9]{8}"))) {
			thongbao.thongbao("Nhập lại số điện thoại", "");
			return false;
		}

		return true;
	}

	public static void dodulieu(JTable tbl) {
		DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
		tblModel.setRowCount(0);
		Object obj[] = new Object[4];
		ResultSet rs = dao.DAO_KhachHang.layKH();
		try {
			while (rs.next()) {
				obj[0] = rs.getString("maKhachHang");
				obj[1] = rs.getString("tenKhachHang");
				obj[2] = rs.getString("email");
				obj[3] = rs.getString("sdt");
				tblModel.addRow(obj);
			}
		} catch (SQLException ex) {
			System.out.println("Lỗi đổ table");
		}
	}

	public static void timKiem(JTable tbl, String tukhoa, String bac,
			String gioiTinh) {
		DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
		tblModel.setRowCount(0);
		Object obj[] = new Object[4];
		ResultSet rs = dao.DAO_KhachHang.timKiem(tukhoa, bac, gioiTinh);

		try {
			while (rs.next()) {

				obj[0] = rs.getString("maKhachHang");
				obj[1] = rs.getString("tenKhachHang");
				obj[2] = rs.getString("email");
				obj[3] = rs.getString("sdt");
				tblModel.addRow(obj);

			}
		} catch (SQLException ex) {
			System.out.println("Lỗi đổ table");
		}

	}

}
