package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import connectDB.ConnectionManager;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoaDonDoiTra;
import dao.DAO_HoaDon;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra;
import entity.SanPham;

public class BUS_DoiTra {

	private ConnectionManager connectionManager = new ConnectionManager();
	private DecimalFormat df = new DecimalFormat("#,###,###");

	public void DeleteDataTable(DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}

	public void docDuLieu_CTHD(DefaultTableModel model, String mahd) {
		DeleteDataTable(model);
		DAO_ChiTietHoaDon daocthd = new DAO_ChiTietHoaDon();
		List<ChiTietHoaDon> ds = daocthd.layDSCTHDTheoMaHd(mahd);
		List<ChiTietHoaDonDoiTra> dsCTTHDDT_DaDoi = DAO_ChiTietHoaDonDoiTra
				.getDsCTHDDT_theoMaHoaDon(mahd);
		DecimalFormat df = new DecimalFormat("#,###,###");
		int soluongdoi;
		for (ChiTietHoaDon a : ds) {
			SanPham sp = a.getSanPham();
			soluongdoi = a.getSoLuong();
			for (ChiTietHoaDonDoiTra e : dsCTTHDDT_DaDoi) {
				if (a.getSanPham().getMaSanPham()
						.equals(e.getSanPham().getMaSanPham())) {
					soluongdoi = a.getSoLuong() - e.getSoLuong();
				}
			}

			model.addRow(new String[] { sp.getMaSanPham(), sp.getTenSanPham(),
					sp.getLoai().getValue(), sp.getMau().getValue(),
					sp.getKichThuoc().getValue(),
					String.valueOf(a.getSoLuong()), String.valueOf(soluongdoi),
					df.format(sp.getGiaBan()), df.format(a.getKhuyenMai()),
					df.format(a.getThanhTien()), df.format(a.tinhTienThue()),
					df.format(a.tinhTienTheoBac()),
					df.format(a.getTienCuoiCung()) });

		}
		;
	}

	public void docDuLieu_CTHDDT(DefaultTableModel model,
			List<ChiTietHoaDonDoiTra> ds) {
		DeleteDataTable(model);

		ds.forEach(e -> {
			Double tien = e.getTienTra() * 100 / 70;
			SanPham sp = e.getSanPham();
			model.addRow(new String[] { sp.getMaSanPham().trim(),
					sp.getTenSanPham(), sp.getLoai().getValue(),
					sp.getMau().getValue(), sp.getKichThuoc().getValue(),
					String.valueOf(e.getSoLuong()), df.format(sp.getGiaBan()),
					df.format(e.tinhThanhTien()), df.format(tien),
					df.format(e.getTienTra()) });

		});
	}

	public String getMa() {
		;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String ngayhientai = LocalDate.now().format(formatter);
		String sql = "SELECT top 1* FROM HoaDonDoiTra where maHDDT like '%"
				+ ngayhientai + "%' ORDER BY maHDDT desc";
		String ma = "";
		ResultSet rs = ConnectionManager.getdata(sql);

		try {
			if (!rs.next()) {
				ma += "HDDT" + ngayhientai + "0000";
			} else {
				ma += rs.getString("maHDDT").trim();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ma;

	}

	public String updateMa() {
		String ma = getMa();
		String pdau = ma.substring(0, 13);
		String psau = ma.substring(13);
		int count = Integer.valueOf(psau) + 1;
		return pdau + String.format("%04d", count);
	}
}
