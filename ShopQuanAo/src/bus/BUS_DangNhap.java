package bus;

import java.sql.ResultSet;

public class BUS_DangNhap {
	public static boolean kt_DangNhap(String tk,String mk){
		ResultSet rs = dao.DAO_DangNhap.layTK();
		boolean kt = false;
		try {
			while (rs.next()) {
				if (tk.trim().equals(rs.getString("tenTaiKhoan").trim()) && mk.trim().equals(rs.getString("matKhau").trim())) {
					kt = true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kt;
	}
}
