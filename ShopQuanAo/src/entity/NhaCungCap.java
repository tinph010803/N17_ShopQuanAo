package entity;

public class NhaCungCap {
	private String maNhaCungCap,tenNhaCungCap,diaChi,sdt,email;

	
	/*
	 * bỏ set mã nhà cung cấp 
	 */
	
	
	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String diaChi,
			String sdt, String email) {
		setDiaChi(diaChi);
		setEmail(email);
		setSdt(sdt);
		setTenNhaCungCap(tenNhaCungCap);
		this.maNhaCungCap= maNhaCungCap;
	}
	
	public NhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public NhaCungCap() {
		super();
	}

	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	@Override
	public String toString() {
		return "NhaCungCap [maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", diaChi=" + diaChi
				+ ", sdt=" + sdt + ", email=" + email + "]";
	}
	
}
