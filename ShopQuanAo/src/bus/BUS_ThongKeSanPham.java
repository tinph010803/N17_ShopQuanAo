package bus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.pattern.ClassNamePatternConverter;
import org.apache.log4j.varia.StringMatchFilter;

import connectDB.ConnectionManager;
import dao.DAO_HoaDon;
import dao.DAO_SanPham;
import entity.SanPham;

public class BUS_ThongKeSanPham {
	private DAO_SanPham dao_SP = new DAO_SanPham();

	// xóa dữ liệu trên table
	public static void DeleteDataTable(DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}

	// thêm dữ liệu lên table
	public static void docDuLieu_TonKho(DefaultTableModel model,
			List<SanPham> ds) {
		for (SanPham a : ds) {
			model.addRow(new String[] { a.getMaSanPham().trim(),
					a.getTenSanPham().trim(), a.getLoai().getValue().trim(),
					String.valueOf(a.getSoLuong()) });
		}
	}

	public static void docDuLieu_DaBan(DefaultTableModel model, List<String> ds) {
		DeleteDataTable(model);
		DecimalFormat form = new DecimalFormat("#,###");
		for (String a : ds) {
			String[] str = a.split(":");
			int soluong = Integer.valueOf(str[1]);
			double tongtien = Double.valueOf(str[2]);
			SanPham sp = DAO_SanPham.laySanPhamTheoMa(str[0].trim());
			model.addRow(new String[] { sp.getMaSanPham(), sp.getTenSanPham(),
					sp.getLoai().getValue(), String.valueOf(soluong),
					form.format(tongtien) });
		}
	}
	public static void doDuLieuThongKeTheoTG(JTable table, Date ngayBatDau, Date ngayKetThuc) {
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;
	    DecimalFormat decimalFormat = new DecimalFormat("#,###");
	    
	    if (conn != null) {
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            String ngayBD = sdf.format(ngayBatDau);
	            String ngayKT = sdf.format(ngayKetThuc);
	            
	            Statement statement = conn.createStatement();
	            String query = "SELECT h.maKhachHang, kh.tenKhachHang, COUNT(h.maHoaDon) AS soHoaDonDaMua, SUM(h.tongTien) AS tongTienDaMua "
	                        + "FROM HoaDon h "
	                        + "JOIN KhachHang kh ON h.maKhachHang = kh.maKhachHang "
	                        + "WHERE h.ngay BETWEEN '" + ngayBD + "' AND '" + ngayKT + "' "
	                        + "GROUP BY h.maKhachHang, kh.tenKhachHang";
	            ResultSet resultSet = statement.executeQuery(query);

	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            model.setRowCount(0);

	            while (resultSet.next()) {
	                String maKhachHang = resultSet.getString("maKhachHang");
	                String tenKhachHang = resultSet.getString("tenKhachHang");
	                int soHoaDonDaMua = resultSet.getInt("soHoaDonDaMua");
	                double tongTienDaMua = resultSet.getDouble("tongTienDaMua");

	                Object[] rowData = { maKhachHang, tenKhachHang, soHoaDonDaMua, decimalFormat.format(tongTienDaMua) };
	                model.addRow(rowData);
	            }

	            statement.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public static void doDuLieuThongKe(JTable table) {
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;
	    DecimalFormat decimalFormat = new DecimalFormat("#,###");
	    // cái tổng tiền đã mua chưa lấy được từ cái tiền tính giá bán
	    if (conn != null) {
	        try {
	            Statement statement = conn.createStatement();
	            String query = "SELECT h.maKhachHang, kh.tenKhachHang, COUNT(h.maHoaDon) AS soHoaDonDaMua, SUM(h.tongTien) AS tongTienDaMua "
	                        + "FROM HoaDon h "
	                        + "JOIN KhachHang kh ON h.maKhachHang = kh.maKhachHang "
	                        + "GROUP BY h.maKhachHang, kh.tenKhachHang";
	            

	            ResultSet resultSet = statement.executeQuery(query);

	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            model.setRowCount(0);

//	            while (resultSet.next()) {
//	                String maKhachHang = resultSet.getString("maKhachHang");
//	                String tenKhachHang = resultSet.getString("tenKhachHang");
//	                int soHoaDonDaMua = resultSet.getInt("soHoaDonDaMua");
//	                double tongTienDaMua = resultSet.getDouble("tongTienDaMua");
//
//	                Object[] rowData = { maKhachHang, tenKhachHang, soHoaDonDaMua, decimalFormat.format(tongTienDaMua) };
//	                model.addRow(rowData);
//	            }
	            while (resultSet.next()) {
	                String maKhachHang = resultSet.getString("maKhachHang");
	                String tenKhachHang = resultSet.getString("tenKhachHang");
	                int soHoaDonDaMua = resultSet.getInt("soHoaDonDaMua");
	                double tongTienDaMua = resultSet.getDouble("tongTienDaMua");

	                Object[] rowData = { maKhachHang, tenKhachHang, soHoaDonDaMua, decimalFormat.format(tongTienDaMua) };
	                model.addRow(rowData);
	            }


	            statement.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
