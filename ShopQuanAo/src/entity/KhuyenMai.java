package entity;

import java.time.LocalDate;
import java.util.Date;

public class KhuyenMai {
	private String maKhuyenMai, tenKhuyenMai, moTa;
	private int phanTram;
	private LocalDate ngayBatDau, ngayKetThuc;

	/*
	 * ngày bắt đầu phải trc hnay , mặc định ngày hôm nay ngày kết thúc phải sau
	 * ngày bắt đầu , mặc định = ngày bắt đầu
	 */

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public KhuyenMai(String maKhuyenMai) {
		super();
		this.maKhuyenMai = maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}


	public int getPhanTram() {
		return phanTram;
	}

	public void setPhanTram(int phanTram) {
		this.phanTram = phanTram;
	}

	public LocalDate getNgayBatDau() {

		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		
			this.ngayBatDau = ngayBatDau;
		

	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc= ngayKetThuc;
	}

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String moTa, int phanTram,
			LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		setMoTa(moTa);
		setNgayBatDau(ngayBatDau);
		setNgayKetThuc(ngayKetThuc);
		setPhanTram(phanTram);
		setTenKhuyenMai(tenKhuyenMai);
	}

	public KhuyenMai() {
		super();
	}

	@Override
	public String toString() {
		return "KhuyenMai [maKhuyenMai=" + maKhuyenMai + ", tenKhuyenMai=" + tenKhuyenMai + ", moTa=" + moTa+" phanTram=" + phanTram + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc="
				+ ngayKetThuc + "]";
	}

}
