package entity;

import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HoaDonDoiTra {
	private String maHDDT;
	private HoaDon hoaDon;
	private LocalDate ngay;
	private NhanVien nhanVien;
	private double tongTienHoanTra;
	
	
	
	public HoaDonDoiTra(String maHDDT, HoaDon hoaDon, LocalDate ngay, NhanVien nhanVien, double tongTienHoanTra) {
		super();
		this.maHDDT = maHDDT;
		this.hoaDon = hoaDon;
		this.ngay = ngay;
		this.nhanVien = nhanVien;
		this.tongTienHoanTra = tongTienHoanTra;
	}


	public double getTongTienHoanTra() {
		return tongTienHoanTra;
	}


	public void setTongTienHoanTra(double tongTienHoanTra) {
		this.tongTienHoanTra = tongTienHoanTra;
	}


	public String getMaHDDT() {
		return maHDDT;
	}


	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		if (hoaDon != null) {
			this.hoaDon = hoaDon;
		} else
			JOptionPane.showMessageDialog(null, "Yêu cầu chọn hóa đơn");
	}

	public LocalDate getNgay() {
		return ngay;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		if (nhanVien != null)
			this.nhanVien = nhanVien;
		else
			JOptionPane.showMessageDialog(null, "Yêu cầu chọn nhân viên");
	}

	public HoaDonDoiTra(String maHDDT, HoaDon hoaDon, NhanVien nhanVien) {
	
		this.maHDDT = maHDDT;
		setHoaDon(hoaDon);
		this.ngay = LocalDate.now();
		setNhanVien(nhanVien);
		}

	public HoaDonDoiTra() {
		super();
	}


	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#,###");
		return "HoaDonDoiTra [maHDDT=" + maHDDT + ", hoaDon=" + hoaDon.getMaHoaDon() + ", ngay=" + ngay + ", nhanVien=" + nhanVien.getTenNhanVien()
				+ ", tongTienHoanTra=" +df.format(tongTienHoanTra) + "]\n";
	}



	
}
