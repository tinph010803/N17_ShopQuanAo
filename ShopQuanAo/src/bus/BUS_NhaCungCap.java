package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectionManager;
import entity.NhaCungCap;
import gui.thongbao;

public class BUS_NhaCungCap {
	public boolean kiemtra_themNCC(String ma,String ten,String sdt,String email, String diaChi) {
		ResultSet rs = dao.DAO_NhaCungCap.layNCC();
		System.err.println("kq  : "+rs);
		try {
			while (rs.next()) {
				System.err.println("1");
				if (rs.getString("MaNhaCungCap").trim().equalsIgnoreCase(ma)) {
					thongbao.thongbao("Mã nhà cung cấp đã dùng", "");
					return false;
				}
				else
					System.err.println("1");
			}
		} catch (SQLException ex) {
			Logger.getLogger(BUS_NhaCungCap.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		if (!(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
			thongbao.thongbao("Nhập lại Email", "");
			return false;
		}
		if(ten.trim().equalsIgnoreCase("")) {
			thongbao.thongbao("Yêu cầu nhập tên ", "");
			return false;
		}
		if(!sdt.matches("0(3|5|7|8|9)[0-9]{8}")) {
			thongbao.thongbao("Yêu cầu nhập đúng số điện thoại", "");
			return false;
		}
		if(diaChi.trim().equalsIgnoreCase("")) {
			thongbao.thongbao("Yêu cầu nhập địa chỉ", "");
			return false;
		}
		return true;
	}
	



	public boolean kiemtra_update(String ten,String sdt,String email, String diaChi) {
		
		if (!(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
			thongbao.thongbao("Nhập lại Email", "");
			return false;
		}
		if(ten.trim().equalsIgnoreCase("")) {
			thongbao.thongbao("Yêu cầu nhập tên ", "");
			return false;
		}
		if(!sdt.matches("0(3|5|7|8|9)[0-9]{8}")) {
			thongbao.thongbao("Yêu cầu nhập đúng số điện thoại", "");
			return false;
		}
		if(diaChi.trim().equalsIgnoreCase("")) {
			thongbao.thongbao("Yêu cầu nhập địa chỉ", "");
			return false;
		}
		return true;
	}
	
	// đọc dữ liệu lên bảng
	public void dodulieu(DefaultTableModel model,ResultSet rs) {
		model.setRowCount(0);
		Object obj[] = new Object[6];
		try {
			int i = 1;
			while (rs.next()) {
				String stt = String.valueOf(i++);
				obj[0] = stt;
				obj[1] = rs.getString("maNhaCungCap");
				obj[2] = rs.getString("tenNhaCungCap");
				obj[3] = rs.getString("diaChi");
				obj[4] = rs.getString("email");
				obj[5] = rs.getString("sdt");
				model.addRow(obj);
			}
			;
		} catch (

		SQLException ex) {
			System.out.println("Lỗi đổ table");
		}
	}
	
	// xóa bảng 
	public void DeleteDataTable(DefaultTableModel model) {
		model.getDataVector().removeAllElements();
	}
	
	
	// tạo mã 
	public static String createMa() {
		
		ResultSet rs= ConnectionManager.getdata("use SFSHOP SELECT top 1  * FROM NhaCungCap ORDER BY maNhaCungCap desc");
		String ma="";
		System.out.println(rs);
		try {
			while(rs.next())
				ma = rs.getString("maNhaCungCap");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ma;
	}
	
	public static String updateMa() {
		String ma=createMa();
        String prefix = ma.replaceAll("[0-9]", "");
        String numberStr = ma.replaceAll("[^0-9]", "");
        
      
        int number = Integer.parseInt(numberStr);
        number+=1;
        String formattedNumber = String.format("%02d", number); 
        String result = prefix.trim() + formattedNumber.trim();
        
        
		return result;
	}
	
	
	
	
//	public void dodulieu2(DefaultTableModel model) {
//		DAO_NhaCungCap dao= new DAO_NhaCungCap();
//		List<NhaCungCap> ls= dao.getdsNhaCungCap();
//		ls.forEach(e-> {
//			model.addRow(new Object[] {e.getmaNhaCungCap(),e.getTenNhaCungCap(),e.getDiaChi(),e.getEmail()});
//		});
//	}
//	
}
