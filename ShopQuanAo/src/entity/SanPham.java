package entity;

import java.sql.Date;
import java.time.LocalDate;

public class SanPham {
	private String maSanPham, tenSanPham;
	private Enum_BangLoaiSanPham loai;
	private double giaNhap;
	private NhaCungCap nhaCC;
	private Enum_Mau mau;
	private Enum_ChatLieu chatLieu;
	private Enum_NhanHieu nhanHieu;
	private String hinhAnh;
	private Enum_KichThuoc kichThuoc;
	private int soLuong;
	private String moTa;
	private KhuyenMai khuyenMai;

	public SanPham(String maSanPham, String tenSanPham, Enum_BangLoaiSanPham loai, double giaNhap,
			NhaCungCap nhaCC, Enum_Mau mau, Enum_ChatLieu chatLieu, Enum_NhanHieu nhanHieu, String hinhAnh,
			Enum_KichThuoc kichThuoc, int soLuong, String moTa, KhuyenMai km) {

		setMaSanPham(maSanPham);
		setKhuyenMai(km);
		setChatLieu(chatLieu);
		setGiaNhap(giaNhap);
		setHinhAnh(hinhAnh);
		setKichThuoc(kichThuoc);
		setLoai(loai);
		setMau(mau);
		setMoTa(moTa);
		setNhaCC(nhaCC);
		setNhanHieu(nhanHieu);
		setSoLuong(soLuong);
		setTenSanPham(tenSanPham);

	}

	public SanPham(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public SanPham() {
		super();
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public Enum_BangLoaiSanPham getLoai() {
		return loai;
	}

	public void setLoai(Enum_BangLoaiSanPham loai) {
		this.loai = loai;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public NhaCungCap getNhaCC() {
		return nhaCC;
	}

	public void setNhaCC(NhaCungCap nhaCC) {
		this.nhaCC = nhaCC;
	}

	public Enum_Mau getMau() {
		return mau;
	}

	public void setMau(Enum_Mau mau) {
		this.mau = mau;
	}

	public Enum_ChatLieu getChatLieu() {
		return chatLieu;
	}

	public void setChatLieu(Enum_ChatLieu chatLieu) {
		this.chatLieu = chatLieu;
	}

	public Enum_NhanHieu getNhanHieu() {
		return nhanHieu;
	}

	public void setNhanHieu(Enum_NhanHieu nhanHieu) {
		this.nhanHieu = nhanHieu;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public Enum_KichThuoc getKichThuoc() {
		return kichThuoc;
	}

	public void setKichThuoc(Enum_KichThuoc kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		if (soLuong <= 0)
			this.soLuong = 1;
		else
			this.soLuong = soLuong;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void Create_KhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	
	public boolean check_KhuyenMai() {
		if(this.khuyenMai.getNgayKetThuc().equals(new java.util.Date())) {
			this.khuyenMai = null;
			return false;
		}
		return true;
	}

	public double getGiaBan() {
		return this.giaNhap * 2.5;
	}

	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", loai=" + loai + ", giaNhap="
				+ giaNhap + ", nhaCC=" + nhaCC + ", mau=" + mau + ", chatLieu=" + chatLieu + ", nhanHieu=" + nhanHieu
				+ ", hinhAnh=" + hinhAnh + ", kichThuoc=" + kichThuoc + ", soLuong=" + soLuong + ", moTa=" + moTa
				+ ", khuyenMai=" + khuyenMai + "]";
	}

}
