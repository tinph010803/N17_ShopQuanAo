package bus;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.DAO_KhachHang;
import entity.ChiTietHoaDonDoiTra;
import entity.HoaDonDoiTra;
import entity.SanPham;

public class BUS_HoaDonDoiTra {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static DecimalFormat df = new DecimalFormat("#,###");
	
	public static void DeleteDataTable(DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}
	
	
	public static void docDulieu_HDDT(DefaultTableModel model,List<HoaDonDoiTra>ds) {
		DeleteDataTable(model);
		
		ds.forEach(e->{
			String sdt,tenKh;
			if(e.getHoaDon().getKhachHang()==null) {
				tenKh= "[khách vãng lai]";
				sdt="xxx";
			}
			else {
				tenKh= e.getHoaDon().getKhachHang().getTenKhachHang();
				sdt= e.getHoaDon().getKhachHang().getSdt();
			}
			model.addRow(new String[] {
					e.getMaHDDT().trim(),
					e.getHoaDon().getMaHoaDon().trim(),
					e.getNgay().format(formatter),
					tenKh,
					sdt,
					e.getNhanVien().getTenNhanVien(),
					df.format(e.getTongTienHoanTra())});
		});
	}
	
	public static void docDulieu_CTHDDT(DefaultTableModel model,List<ChiTietHoaDonDoiTra>ds) {
		DeleteDataTable(model);
		ds.forEach(e->{
			SanPham sp=e.getSanPham();
			model.addRow(new String[] {
					sp.getTenSanPham().trim(),
					sp.getLoai().getValue(),
					sp.getMau().getValue(),
					sp.getKichThuoc().getValue(),
					df.format(e.getSoLuong()),
					df.format(e.getTienTra()*100/70),
					df.format(e.getTienTra()),
			});
		});
	}
	
}
