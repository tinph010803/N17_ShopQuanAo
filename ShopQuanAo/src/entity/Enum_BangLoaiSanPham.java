package entity;

public enum Enum_BangLoaiSanPham {
	AO("Áo"), QUAN("Quần"), VAY("Váy"), DAM("Đầm");


	private final String value;

	Enum_BangLoaiSanPham(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	

	public static Enum_BangLoaiSanPham fromString(String value) {
		for (Enum_BangLoaiSanPham loai : values()) {
			if (loai.getValue().equalsIgnoreCase(value)) {
				return loai;
			}
		}
		// Trả về giá trị mặc định hoặc xử lý trường hợp không tìm thấy
		throw new IllegalArgumentException("Không có giá trị enum tương ứng cho: " + value);
	}
}
