package entity;

public class ChiTietHoaDonDoiTra {
	private SanPham sanPham;
	private HoaDonDoiTra hoaDonDoiTra;
	private int soLuong;
	private double tienTra;
	public SanPham getSanPham() {
		return sanPham;
	}
	
	
	public ChiTietHoaDonDoiTra(SanPham sanPham, HoaDonDoiTra hoaDonDoiTra, int soLuong, double tienTra) {
		super();
		this.sanPham = sanPham;
		this.hoaDonDoiTra = hoaDonDoiTra;
		this.soLuong = soLuong;
		this.tienTra = tienTra;
	}


	public double getTienTra() {
		return tienTra;
	}


	public void setTienTra(double tienTra) {
		this.tienTra = tienTra;
	}


	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public HoaDonDoiTra getHoaDonDoiTra() {
		return hoaDonDoiTra;
	}
	public void setHoaDonDoiTra(HoaDonDoiTra hoaDonDoiTra) {
		this.hoaDonDoiTra = hoaDonDoiTra;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public ChiTietHoaDonDoiTra() {
		super();
	}
	public ChiTietHoaDonDoiTra(SanPham sanPham, HoaDonDoiTra hoaDonDoiTra,
			int soLuong) {
		super();
		this.sanPham = sanPham;
		this.hoaDonDoiTra = hoaDonDoiTra;
		this.soLuong = soLuong;
	}
	
	// tính thành tiền của mỗi sản phẩm 
		public double tinhThanhTien() {
			return sanPham.getGiaBan() * this.soLuong;
		}
		
		// tính tiền khuyến mãi mỗi sản phẩm
		public double tinhTienKhuyenMai() {
			if(this.sanPham.getKhuyenMai()==null)
				return 0;
			return tinhThanhTien() * this.sanPham.getKhuyenMai().getPhanTram();
		}
		
		public double tinhTienHoanTra() {
			return (tinhThanhTien()-tinhTienKhuyenMai()) *70/100;
		}


		@Override
		public String toString() {
			return "ChiTietHoaDonDoiTra [sanPham=" + sanPham.getTenSanPham() + ", hoaDonDoiTra=" + hoaDonDoiTra.getMaHDDT() + ", soLuong=" + soLuong
					+ ", tienTra=" + tienTra + "] \n";
		}
		
	
}
