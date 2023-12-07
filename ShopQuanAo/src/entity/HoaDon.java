package entity;

import java.time.LocalDate;
import java.util.List;

public class HoaDon {
	private String maHoaDon;
	private KhachHang khachHang;
	private LocalDate ngay;
	private NhanVien nhanVien;
	private double tienKhachDua;
	private final int VAT = 8;
	private double tongTienCanThu; 
	
	
	public HoaDon(String maHoaDon, KhachHang khachHang, LocalDate ngay, NhanVien nhanVien,
			double tienKhachDua) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngay= LocalDate.now();
		this.tienKhachDua=tienKhachDua;
		setKhachHang(khachHang);
		setNhanVien(nhanVien);
	}
	

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngay= LocalDate.now();
	}


	public HoaDon(String maHoaDon, KhachHang khachHang, LocalDate ngay,
			NhanVien nhanVien, double tienKhachDua, double tongTienCanThu) {
		super();
		this.maHoaDon = maHoaDon;
		this.khachHang = khachHang;
		this.ngay = ngay;
		this.nhanVien = nhanVien;
		this.tienKhachDua = tienKhachDua;
		this.tongTienCanThu = tongTienCanThu;
	}


	public HoaDon() {
		super();
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public LocalDate getNgay() {
		return ngay;
	}
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public double getTienKhachDua() {
		return tienKhachDua;
	}
	
	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}


	public double tinhTongTien(List<ChiTietHoaDon> dsCTHD){
		double tongTien = 0.0;
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			tongTien += chiTietHoaDon.getThanhTien();
		}
		return tongTien;
	}
	
	public double tinhTongTienKM(List<ChiTietHoaDon> dsCTHD){
		double tongTienKM = 0.0;
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			tongTienKM += chiTietHoaDon.getKhuyenMai();
		}
		return tongTienKM;
	}
	
	public double tinhTongTienThue(List<ChiTietHoaDon> dsCTHD){
		return (tinhTongTien(dsCTHD) - tinhTongTienKM(dsCTHD)) * VAT / 100;
	}
	
//	public double tinhTongTienCanThuChuaTinhBac(List<ChiTietHoaDon> dsCTHD){
//		return tinhTongTien(dsCTHD)-tinhTongTienKM(dsCTHD)+tinhTongTienThue(dsCTHD);
//	}
	
	public double tinhTongKhuyeMaiTheoBac(List<ChiTietHoaDon> dsCTHD){
//		if (getKhachHang() != null) {
//			double soTien = getKhachHang().getSoTienDaMua();
//			if (soTien >= 5000000 && soTien < 20000000) {
//				return tinhTongTienCanThuChuaTinhBac(dsCTHD)*0.1;
//			}
//			if (soTien >= 20000000 && soTien < 50000000) {
//				return tinhTongTienCanThuChuaTinhBac(dsCTHD)*0.15;
//			}
//			if (soTien >= 50000000) {
//				return tinhTongTienCanThuChuaTinhBac(dsCTHD)*0.2;
//			}
//		}
//		return 0;
		
		double tongTienKMTheoBac = 0.0;
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			tongTienKMTheoBac += chiTietHoaDon.tinhTienTheoBac();
		}
		return tongTienKMTheoBac;
	}
	
	public int getVAT() {
		return VAT;
	}


	public double getTongTienCanThu(){
		return this.tongTienCanThu;
	}
		
	public void setTongTienCanThu(List<ChiTietHoaDon> dsCTHD){
//		this.tongTienCanThu = tinhTongTienCanThuChuaTinhBac(dsCTHD) - tinhTongKhuyeMaiTheoBac(dsCTHD);
		double tong = 0.0;
		for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
			tong += chiTietHoaDon.getTienCuoiCung();
			System.out.println(tong);
		}
		this.tongTienCanThu = tong;
	}
	
	public double tinhTienThua(List<ChiTietHoaDon> dsCTHD){
		return this.tienKhachDua - this.tongTienCanThu;
	}
	
	
}
