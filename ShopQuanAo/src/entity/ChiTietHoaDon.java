package entity;

import javax.swing.JOptionPane;

public class ChiTietHoaDon {
	private SanPham sanPham;
	private HoaDon hoaDon;
	private int soLuong;
	private double khuyenMai;
	private double thanhTien;
	private double tienCuoiCung;

	public ChiTietHoaDon(SanPham sanPham, HoaDon hoaDon, int soLuong,
			double tienCuoiCung) {
		super();
		setHoaDon(hoaDon);
		setSanPham(sanPham);
		setSoLuong(soLuong);
		setThanhTien();
		setKhuyenMai();
		setTienCuoiCung();
	}

	public ChiTietHoaDon(SanPham sanPham, HoaDon hoaDon, int soLuong) {
		super();
		setHoaDon(hoaDon);
		setSanPham(sanPham);
		setSoLuong(soLuong);
		setThanhTien();
		setKhuyenMai();
		setTienCuoiCung();
	}

	public ChiTietHoaDon() {
		super();
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		if (soLuong <= 0) {
			JOptionPane.showMessageDialog(null, "Yêu cầu nhập số lượng");
		}
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [sanPham=" + sanPham + ", hoaDon=" + hoaDon
				+ ", soLuong=" + soLuong + "]";
	}

	public void setThanhTien() {
		this.thanhTien = sanPham.getGiaBan() * this.soLuong;
	}

	// tính tiền khuyến mãi mỗi sản phẩm
	public void setKhuyenMai() {
		KhuyenMai km = this.sanPham.getKhuyenMai();
		if (km != null) {
			this.khuyenMai = getThanhTien() * km.getPhanTram() / 100;
		} else {
			this.khuyenMai = 0;
		}

	}

	// tính thành tiền của mỗi sản phẩm
	public double getThanhTien() {
		return this.thanhTien;
	}

	public double getKhuyenMai() {
		return this.khuyenMai;
	}

	public double tinhTienThue() {
		return (getThanhTien() - getKhuyenMai()) * this.getHoaDon().getVAT()
				/ 100;
	}

	public double tinhTienTheoBac() {
		if (getHoaDon().getKhachHang() == null) {
			return 0;
		} else {
			return (getThanhTien() - getKhuyenMai() + tinhTienThue())
					* this.getHoaDon().getKhachHang().getPhanTramGiamGia();
		}
	}

	public void setTienCuoiCung() {
		this.tienCuoiCung = getThanhTien() - getKhuyenMai() + tinhTienThue()
				- tinhTienTheoBac();
		System.out.println(tienCuoiCung);
	}

	public double getTienCuoiCung() {
		return tienCuoiCung;
	}
}
