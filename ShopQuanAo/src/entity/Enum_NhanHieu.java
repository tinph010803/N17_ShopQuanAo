package entity;

public enum Enum_NhanHieu {
	BADHABITS("BADHABITS"),
	NOWSAIGON("NOWSAIGON"),
	DEGREY("DEGREY"),
	GORI("GORI"),
	SLY("SLY"),
	MENDE("MENDE"),
	HADES("HADES"),
	CLOWNZ("CLOWNZ"),
	DAVIES("DAVIES"),
	HIGHCLUB("HIGHCLUB");
	
	
	private String value;

	Enum_NhanHieu(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Enum_NhanHieu fromString(String value) {
		for (Enum_NhanHieu nh : values()) {
			if (nh.getValue().equalsIgnoreCase(value)) {
				return nh;
			}
		}
		// Trả về giá trị mặc định hoặc xử lý trường hợp không tìm thấy
		throw new IllegalArgumentException("Không có giá trị enum tương ứng cho: " + value);
	}
	
}
