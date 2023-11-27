package entity;

public enum Enum_Mau {
	DO("Đỏ"),
	VANG("Vàng"),
	CAM("Cam"),
	TRANG("Trắng"),
	DEN("Đen"),
	XANHLA("Xanh lá"),
	XANHDUONG("Xanh dương"),
	XAM("Xám"),
	BE("Be"),
	HONG("Hồng");
	
	
	
	
	private String value;

	Enum_Mau(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Enum_Mau fromString(String value) {
		for (Enum_Mau mau : values()) {
			if (mau.getValue().equalsIgnoreCase(value)) {
				return mau;
			}
		}
		// Trả về giá trị mặc định hoặc xử lý trường hợp không tìm thấy
		throw new IllegalArgumentException("Không có giá trị enum tương ứng cho: " + value);
	}
}
