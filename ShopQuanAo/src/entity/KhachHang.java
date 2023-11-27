package entity;

public class KhachHang {
	private String maKhachHang,tenKhachHang,sdt,email;
	private double soTienDaMua;
	private boolean gioiTinh;
	
	/*
	 * 
	 * bỏ set số tiền đã mua , mặc định bằng 0 
	 */
	
	public KhachHang(String maKhachHang, String tenKhachHang, String sdt,
			String email, boolean gioiTinh, double soTienDaMua) {
		setMaKhachHang(maKhachHang);
		setEmail(email);
		setSdt(sdt);
		setTenKhachHang(tenKhachHang);
		setGioiTinh(gioiTinh);
		this.soTienDaMua = soTienDaMua;
	}
	
	public KhachHang(String maKhachHang, String tenKhachHang, String sdt,
			String email, boolean gioiTinh) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.email = email;
		this.gioiTinh = gioiTinh;
	}

	public KhachHang() {
		super();
	}

	public KhachHang(String maKhachHang) {
		super();
		setMaKhachHang(maKhachHang);
	}

	

	public String getMaKhachHang() {
		return maKhachHang;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
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
	public double getSoTienDaMua() {
		return soTienDaMua;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maKhachHang == null) ? 0 : maKhachHang.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		if (maKhachHang == null) {
			if (other.maKhachHang != null)
				return false;
		} else if (!maKhachHang.equals(other.maKhachHang))
			return false;
		return true;
	}
	
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public boolean capNhatSoTienDaMua(double tien) {
		if (tien>0) {
			this.soTienDaMua+= tien;
			return true;
		}
		return false;
	}
	
	public double getPhanTramGiamGia() {
		if(soTienDaMua >= 5000000 )
			return 0.1;
		else if(soTienDaMua>=20000000)
			return 0.15;
		else if(soTienDaMua >= 50000000)
			return 0.2;
		else 
			return 0;
	}
	
	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang="
				+ tenKhachHang + ", sdt=" + sdt + ", email=" + email
				+ ", soTienDaMua=" + soTienDaMua + "]";
	}
}
