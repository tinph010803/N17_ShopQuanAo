package entity;

public enum Enum_KichThuoc {
	S("S"),
	M("M"),
	L("L"),
	XL("XL");
	
	
	
	private final String value;

	Enum_KichThuoc(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Enum_KichThuoc fromString(String value) {
		for (Enum_KichThuoc kt : values()) {
			if (kt.getValue().equalsIgnoreCase(value)) {
				return kt;
			}
		}
		// Trả về giá trị mặc định hoặc xử lý trường hợp không tìm thấy
		throw new IllegalArgumentException("Không có giá trị enum tương ứng cho: " + value);
	}
	
}
