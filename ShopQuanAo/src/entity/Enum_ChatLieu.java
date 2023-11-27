package entity;

public enum Enum_ChatLieu {
	COTTON("Cotton"),
	KAKI("Kaki"),
	JEAN("Jean"),
	NI("Nỉ"),
	LEN("Len"),
	LUA("Lụa"),
	DU("Dù"),
	NHUNGTAM("Nhung tăm");
	
	
	
	private final String value;

	Enum_ChatLieu(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Enum_ChatLieu fromString(String value) {
		for (Enum_ChatLieu cl : values()) {
			if (cl.getValue().equalsIgnoreCase(value)) {
				return cl;
			}
		}
		// Trả về giá trị mặc định hoặc xử lý trường hợp không tìm thấy
		throw new IllegalArgumentException("Không có giá trị enum tương ứng cho: " + value);
	}
}
