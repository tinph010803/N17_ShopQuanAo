package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.mail.internet.NewsAddress;
import javax.swing.table.DefaultTableModel;

import org.bridj.ann.Template;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_DoiTra;
import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import dao.DAO_NhanVien;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

public class BUS_HoaDon {

	// xóa dữ liệu trên bảng
	public void DeleteDataTable(DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}

	// đọc dữ liệu lên table Hóa Đơn

	public void docDuLieu_HoaDon(DefaultTableModel model, List<HoaDon> ds) {
		HoaDon hd = new HoaDon();
		DeleteDataTable(model);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#,###");
		String tenKh, sdt;
		for (HoaDon e : ds) {
			if (e.getKhachHang() == null) {
				tenKh = "[khách vãng lai]";
				sdt = "xxx";
			} else {
				tenKh = e.getKhachHang().getTenKhachHang();
				sdt = e.getKhachHang().getSdt();
			}
			List<ChiTietHoaDon> dscthd = DAO_ChiTietHoaDon.layDSCTHDTheoMaHd(e
					.getMaHoaDon().trim());
			hd.setTongTienCanThu(dscthd);
			double tien = hd.getTongTienCanThu();

			model.addRow(new String[] { e.getMaHoaDon().trim(),
					e.getNgay().format(formatter), tenKh, sdt,
					e.getNhanVien().getTenNhanVien().trim(), df.format(tien) });
		}
		;

	}

	public void docDuLieu_CTHD(DefaultTableModel model, int row, String mahd) {
		DeleteDataTable(model);
		DAO_ChiTietHoaDon daocthd = new DAO_ChiTietHoaDon();
		List<ChiTietHoaDon> ds = daocthd.layDSCTHDTheoMaHd(mahd);
		DecimalFormat df = new DecimalFormat("#,###,###");
		ds.forEach(a -> {
			SanPham sp = a.getSanPham();
			model.addRow(new String[] { sp.getTenSanPham(),
					sp.getLoai().getValue(), sp.getMau().getValue(),
					sp.getKichThuoc().getValue(),
					String.valueOf(a.getSoLuong()), df.format(sp.getGiaBan()),
					df.format(a.getKhuyenMai()), df.format(a.getThanhTien()),
					df.format(a.tinhTienThue()),
					df.format(a.tinhTienTheoBac()),
					df.format(a.getTienCuoiCung()) });
		});
	}
}
