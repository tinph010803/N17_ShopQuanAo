package bus;

import java.text.DecimalFormat;
import java.util.List;

import javax.mail.Address;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.pattern.ClassNamePatternConverter;
import org.apache.log4j.varia.StringMatchFilter;

import dao.DAO_HoaDon;
import dao.DAO_SanPham;
import entity.SanPham;

public class BUS_ThongKeSanPham {
	private DAO_SanPham dao_SP= new DAO_SanPham(); 
	
	//xóa dữ liệu trên table 
	public static void DeleteDataTable(DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}
	
	//thêm dữ liệu lên table 
	public static void docDuLieu_TonKho(DefaultTableModel model, List<SanPham> ds) {
		for (SanPham a : ds) {
			model.addRow(new String[] {
					a.getMaSanPham().trim(),
					a.getTenSanPham().trim(),
					a.getLoai().getValue().trim(),
					String.valueOf(a.getSoLuong())
			});
		}
	}
	
	public static void docDuLieu_DaBan(DefaultTableModel model, List<String> ds) {
		DeleteDataTable(model);
		DecimalFormat form = new DecimalFormat("#,###");
		for (String a : ds) {
			 String[] str = a.split(":");
	        int soluong = Integer.valueOf(str[1]);
	        double tongtien = Double.valueOf(str[2]);
	        SanPham sp= DAO_SanPham.laySanPhamTheoMa(str[0].trim());
			model.addRow(new String[] {
					sp.getMaSanPham(),
					sp.getTenSanPham(),
					sp.getLoai().getValue(),
					String.valueOf(soluong),
					form.format(tongtien)
			});
		}
	}
	


}
