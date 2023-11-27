package entity;

import java.time.LocalDate;

public class TaiKhoan {
	private String tenTaiKhoan, matKhau, ghiChu;
	private LocalDate ngayLap;
	private NhanVien nhanVien;
	private boolean vaiTro;

	/*
	 * bỏ set tên tài khoản 
	 * bỏ set ngày lập , mặc định ngày hôm nay
	 */

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	
	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String tenTaiKhoan, String matKhau, String ghiChu, NhanVien nhanVien, boolean vaiTro, LocalDate ngayLap) {
		setGhiChu(ghiChu);
		setMatKhau(matKhau);
		setNhanVien(nhanVien);
		setVaiTro(vaiTro);
		setTenTaiKhoan(tenTaiKhoan);
		setNgayLap(ngayLap);
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public boolean isVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(boolean vaiTro) {
		this.vaiTro = vaiTro;
	}

	public boolean doiMatKhau(String mk) {
		this.matKhau = mk;
		return true;
	}

	@Override
	public String toString() {
		return "TaiKhoan [tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", ghiChu=" + ghiChu + ", ngayLap="
				+ ngayLap + ", nhanVien=" + nhanVien + ", vaiTro=" + vaiTro + "]";
	}

	
	
}