package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectionManager;
import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;
import entity.TaiKhoan;
import gui.thongbao;

public class BUS_NhanVien {
	private ConnectionManager connectionManager = new ConnectionManager();
	private DAO_NhanVien daonv= new DAO_NhanVien();
	private DAO_TaiKhoan daotk = new DAO_TaiKhoan();
	
	// đọc dữ liệu lên bảng
	public void dodulieu(DefaultTableModel model,ResultSet rs) {
			model.setRowCount(0);
			boolean gt= true;
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date ngayDate;
			Object obj[] = new Object[11];
			try {
				int i = 1;
				
				while (rs.next()) {
					String stt = String.valueOf(i++);
					obj[0] = stt;
					obj[1] = rs.getString("maNhanVien").trim();
					obj[2] = rs.getString("tenNhanVien");
					gt= rs.getBoolean("gioiTinh");
					if(gt)
						obj[3] = "Nam";
					else 
						obj[3] = "Nữ";
					obj[4] = rs.getString("email");
					
					ngayDate = rs.getDate("ngayVaoLam");	
					obj[5] = newDateFormat.format(ngayDate);
					obj[6] = rs.getString("diaChi");
					obj[7] = rs.getString("sdt");
					obj[8] = rs.getString("cccd");
					TaiKhoan a = daotk.getTKtheoMa(rs.getString("maNhanVien").trim());
					if(a == null)
						obj[9]="Không";
					else {
						System.err.println("TkTD :" +a);
						obj[9]= "Có";	
						obj[10]= a.getGhiChu();
						
					}
						
				
					model.addRow(obj);
					
				}
				;
			} catch (

			SQLException ex) {
				System.out.println("Lỗi đổ table");
			}
		}
		
	public String getMa() {
		String ma="";
		ResultSet rs= ConnectionManager.getdata("SELECT top 1*FROM NhanVien ORDER BY maNhanVien desc");
		try {
			while(rs.next()) {
				ma+= rs.getString("maNhanVien");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ma;
	}
	
	public String updateMa() {
		String ma=getMa();
		 String prefix = ma.replaceAll("[0-9]", "");
	        String numberStr = ma.replaceAll("[^0-9]", "");
	        
	      
	        int number = Integer.parseInt(numberStr);
	        number+=1;
	        String formattedNumber = String.format("%02d", number); 
	        String result = prefix.trim() + formattedNumber.trim();
	     return result;
	}
	
	public boolean check_themNV( String ma, String ten, String sdt, LocalDate ngay,String email,String cccd,String diaChi) {
	 ResultSet rs= daonv.layNV();
	 try {
		while(rs.next()) {
			 if(ma.trim().equalsIgnoreCase(rs.getString("maNhanVien").trim())) {
				 thongbao.thongbao("Mã đã được dùng", ""); 
				 return false; 
			 }
			
		 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		if(ten.trim().equalsIgnoreCase("")) {
			thongbao.thongbao("Yêu cầu nhập tên ", "");
			return false;
		}
		if(!sdt.matches("0(3|5|7|8|9)[0-9]{8}")) {
			thongbao.thongbao("Yêu cầu nhập đúng số điện thoại", "");
			return false;
		}
		if (!(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
			thongbao.thongbao("Nhập lại Email", "");
			return false;
		}
		if(diaChi.trim().equalsIgnoreCase("")) {
			thongbao.thongbao("Yêu cầu nhập địa chỉ", "");
			return false;
		}
		
		if(!cccd.trim().matches("\\d{12}")) {
			thongbao.thongbao("Yêu cầu nhập đúng cccd", "");
			return false;
		}
		if(ngay.isBefore(LocalDate.now())) {
			thongbao.thongbao("Ngay không hợp lệ ", "");
			return false;
		}
		return true;
	}
	
	 public LocalDate chuyenDoiKieuNgay(JDateChooser dateChooser) {
	        Date selectedDate = dateChooser.getDate();
	        if (selectedDate != null) {
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(selectedDate);
	            return LocalDate.of(
	                calendar.get(Calendar.YEAR),
	                calendar.get(Calendar.MONTH) + 1, // Tháng trong Java bắt đầu từ 0
	                calendar.get(Calendar.DAY_OF_MONTH)
	            );
	        } else {
	            return null;
	        }
	    }
	 
	 
	 public LocalDate chuyenDatesangLocalDate(Date date) {
	 
	        Instant instant = date.toInstant();
	        
	       
	        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
	        
	        return localDate;
	    }
	
	 public boolean kiemTraMatKhau(String ma1, String ma2) {
		 if (ma1.matches("^[a-zA-Z0-9]{8,}$")) {
			 if(ma1.trim().equals(ma2.trim())) {
				 return true;
			 }
			 else {
				 thongbao.thongbao("Hai mật khẩu không giống nhau", "Chú ý");
				 return false;
			 }
		 }
		 else {
			 thongbao.thongbao("Mã ít nhất 8 kí tự và không có kí tự đặc biệt", "Chú ý!!");
			 return false;
		 }
	 }
}
