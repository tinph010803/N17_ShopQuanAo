package entity;

import java.time.LocalDate;

public class NhanVien {
	private String maNhanVien,tenNhanVien,sdt,email,diaChi,cccd;
	private boolean gioiTinh;
	private LocalDate ngayVaoLam;
	String hinhAnh;
	
	/*
	 * bỏ set mã nhân viên 
	 */
	public NhanVien(String maNhanVien, String tenNhanVien, String sdt,
			String email, String diaChi, String cccd, boolean gioiTinh,
			LocalDate ngayVaoLam,String hinhAnh) {
		
		this.maNhanVien = maNhanVien;
		setTenNhanVien(tenNhanVien);
		setSdt(sdt);
		setEmail(email);
		setGioiTinh(gioiTinh);
		setNgayVaoLam(ngayVaoLam);
		setDiaChi(diaChi);
		setCccd(cccd);
		setHinhAnh(hinhAnh);
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}
	public NhanVien() {
		super();
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien="
				+ tenNhanVien + ", sdt=" + sdt + ", email=" + email
				+ ", diaChi=" + diaChi + ", cccd=" + cccd + ", gioiTinh="
				+ gioiTinh + ", ngayVaoLam=" + ngayVaoLam + "]";
	}
	
}	
