package bus;

import gui.thongbao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;

public class BUS_QuenMatKhau {

	public static boolean checkEmail(String ma, String email) {
		DAO_NhanVien daonv = new DAO_NhanVien();
		if (!email.trim().equals(daonv.layNVTheoMa(ma).getEmail())) {
			// thongbao.thongbao("sai", "");
			return false;
		}
		return true;
	}

	static Random random = new Random();

	static long min = 100000;
	static long max = 999999;
	static long randomNumber = random.nextLong() % (max - min) + max;

	public static void guiEmailXacNhan(String email, String ma) {
		final String username = "sfshop153@gmail.com";
		final String password = "dpnktbjuopndvsqf";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			if (email != null && !email.isEmpty() && checkEmail(ma, email)) {
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email));
				message.setSubject("Xác nhận đổi mật khẩu");

				String content = "Xin chào,\n\n";
				content += "Vui lòng sử dụng mã xác nhận sau đây để đổi mật khẩu:\n";
				content += "Mã xác nhận: " + randomNumber + "\n";
				content += "Trân trọng,\nYour App";

				message.setText(content);

				Transport.send(message);

				// Hiển thị thông báo cho người dùng nếu email đã được gửi thành
				// công

			} else {
				thongbao.thongbao("Địa chỉ Email không hợp lệ.", "");
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			// Hiển thị thông báo lỗi cụ thể nếu gửi email thất bại
			thongbao.thongbao("Có lỗi xảy ra khi gửi email: ",
					"" + e.getMessage());
		}
	}

	public static boolean kiemTraMaXacNhan(JTextComponent txtMaXacNhan) {
		String maXacNhanNhap = txtMaXacNhan.getText(); // Lấy mã xác nhận từ
														// trường nhập liệu
		if (maXacNhanNhap.equals(String.valueOf(randomNumber))) {
			// Mã xác nhận hợp lệ
			return true;
		} else {
			// Mã xác nhận không hợp lệ, hiển thị thông báo cho người dùng
			thongbao.thongbao("Mã xác nhận không chính xác", "");
			return false;
		}
	}

	public static boolean kiemTraMatKhau(String matKhauMoi,
			String xacNhanMatKhauMoi) {
		if (matKhauMoi.matches("^[a-zA-Z0-9]{8,}$")) {
			if (matKhauMoi.trim().equals(xacNhanMatKhauMoi.trim())) {
				return true;
			} else {
				thongbao.thongbao("Hai mật khẩu không giống nhau", "Chú ý");
				return false;
			}
		} else {
			thongbao.thongbao("Mã ít nhất 8 kí tự và không có kí tự đặc biệt",
					"Chú ý!!");
			return false;
		}
	}

	public static boolean kiemTraMKCu(String manv,String matKhauCu) {
		if (matKhauCu.equals(DAO_TaiKhoan.getTKtheoMa(manv).getMatKhau())){
			return true;
		}
			
		else {
			thongbao.thongbao("Mật khẩu Cũ không chính xác!", "");
			return false;
		}
		
	}
}
