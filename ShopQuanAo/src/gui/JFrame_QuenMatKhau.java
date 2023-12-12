package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import connectDB.ConnectionManager;
import dao.DAO_TaiKhoan;

public class JFrame_QuenMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JTextField txtMaXacNhan;
	private JPasswordField txtMKMoi;
	private JPasswordField txtNLMKMoi;
	private JPanel pnlTieuDe;
	private JLabel lblNewLabel_1;
	private JPanel pnlMain;
	private JLabel lblMaXacNhan;
	private JLabel lblMKMoi;
	private JLabel lblNhaplaiMKM;
	private JLabel lblEmail;
	private JButton btnGuiEmail;
	private JButton btnhienMK;
	private JButton btnHienMK_2;
	private JPanel pnlBottom;
	private JButton btnXacNhan;
	private JButton btnQuayLai;
	private boolean chon = true, chon2 = true;
	ConnectionManager connectionManager = new ConnectionManager();

	public JFrame_QuenMatKhau(String ma) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 402);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(new Color(51, 204, 204));
		pnlTieuDe.setBounds(0, 0, 685, 70);
		getContentPane().add(pnlTieuDe);
		pnlTieuDe.setLayout(null);

		lblNewLabel_1 = new JLabel("Quên mật khẩu ");
		lblNewLabel_1.setForeground(new Color(255, 153, 51));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(254, 15, 215, 40);
		pnlTieuDe.add(lblNewLabel_1);

		pnlMain = new JPanel();
		pnlMain.setBackground(new Color(204, 255, 255));
		pnlMain.setBounds(0, 68, 685, 251);
		getContentPane().add(pnlMain);
		pnlMain.setLayout(null);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(240, 10, 222, 32);
		pnlMain.add(txtEmail);

		lblMaXacNhan = new JLabel("Mã xác nhận :");
		lblMaXacNhan.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMaXacNhan.setBounds(50, 70, 123, 32);
		pnlMain.add(lblMaXacNhan);

		txtMaXacNhan = new JTextField();
		txtMaXacNhan.setColumns(10);
		txtMaXacNhan.setBounds(240, 70, 222, 32);
		pnlMain.add(txtMaXacNhan);

		lblMKMoi = new JLabel("Mật khẩu mới :");
		lblMKMoi.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMKMoi.setBounds(50, 130, 123, 32);
		pnlMain.add(lblMKMoi);

		lblNhaplaiMKM = new JLabel("Nhập lại mật khẩu :");
		lblNhaplaiMKM.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNhaplaiMKM.setBounds(50, 200, 151, 32);
		pnlMain.add(lblNhaplaiMKM);

		btnGuiEmail = new JButton("Gửi ");
		btnGuiEmail.setBackground(new Color(102, 204, 51));
		btnGuiEmail.setFont(new Font("Arial", Font.BOLD, 16));
		btnGuiEmail.setBounds(534, 9, 112, 32);
		btnGuiEmail.setFocusPainted(false);
		pnlMain.add(btnGuiEmail);

		lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmail.setBounds(50, 10, 100, 32);
		pnlMain.add(lblEmail);

		txtMKMoi = new JPasswordField();
		txtMKMoi.setBounds(240, 130, 222, 32);
		pnlMain.add(txtMKMoi);

		txtNLMKMoi = new JPasswordField();
		txtNLMKMoi.setBounds(240, 200, 222, 32);
		pnlMain.add(txtNLMKMoi);
		btnhienMK = new JButton("");
		btnhienMK.setBorder(null);
		btnhienMK.setBackground(new Color(204, 255, 255));
		btnhienMK.setIcon(new ImageIcon(
				"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32.png"));
		btnhienMK.setBounds(482, 130, 32, 32);
		pnlMain.add(btnhienMK);
		btnhienMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chon == true) {
					btnhienMK
							.setIcon(new ImageIcon(
									"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32_close.png"));
					txtMKMoi.setEchoChar((char) 0);
					chon = false;
				} else {
					btnhienMK
							.setIcon(new ImageIcon(
									"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32.png"));
					txtMKMoi.setEchoChar('*');
					chon = true;
				}
			}
		});

		btnHienMK_2 = new JButton("");
		btnHienMK_2.setIcon(new ImageIcon(
				"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32.png"));
		btnHienMK_2.setBorder(null);
		btnHienMK_2.setBackground(new Color(204, 255, 255));
		btnHienMK_2.setBounds(482, 200, 32, 32);
		pnlMain.add(btnHienMK_2);
		btnHienMK_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chon2 == true) {
					btnHienMK_2
							.setIcon(new ImageIcon(
									"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32_close.png"));
					txtNLMKMoi.setEchoChar((char) 0);
					chon2 = false;
				} else {
					btnHienMK_2
							.setIcon(new ImageIcon(
									"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32.png"));
					txtNLMKMoi.setEchoChar('*');
					chon2 = true;
				}
			}
		});

		pnlBottom = new JPanel();
		pnlBottom.setBackground(new Color(204, 255, 255));
		pnlBottom.setBounds(0, 319, 685, 84);
		getContentPane().add(pnlBottom);
		pnlBottom.setLayout(null);

		btnXacNhan = new JButton("Xác nhận ");
		btnXacNhan.setBackground(new Color(102, 153, 255));
		btnXacNhan.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXacNhan.setBounds(190, 10, 103, 45);
		btnXacNhan.setFocusPainted(false);

		pnlBottom.add(btnXacNhan);

		btnQuayLai = new JButton("Quay lại ");
		btnQuayLai.setBackground(new Color(255, 255, 51));
		btnQuayLai.setFont(new Font("Arial", Font.PLAIN, 16));
		btnQuayLai.setFocusPainted(false);
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangNhap dangNhapFrame = new DangNhap(); // Tạo một phiên bản
				JFrame_QuenMatKhau.this.dispose(); // Đóng màn hình khôi phục
													// mật khẩu
			}
		});

		btnQuayLai.setBounds(375, 10, 103, 45);
		pnlBottom.add(btnQuayLai);

		btnGuiEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				if (bus.BUS_QuenMatKhau.checkEmail(ma, email)) {
					thongbao.thongbao("Email xác nhận đã được gửi.", "");
				}
				bus.BUS_QuenMatKhau.guiEmailXacNhan(email, ma);
				;

			}
		});

		btnXacNhan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Lấy mật khẩu mới từ trường txtMKMoi
				String matKhauMoi = new String(txtMKMoi.getPassword());

				// Lấy xác nhận mật khẩu mới từ trường txtNLMKMoi
				String xacNhanMatKhauMoi = new String(txtNLMKMoi.getPassword());

				// Kiểm tra mã xác nhận và mật khẩu mới
				boolean isMaXacNhanHopLe = bus.BUS_QuenMatKhau
						.kiemTraMaXacNhan(txtMaXacNhan);

				boolean isMatKhauMoiHopLe = bus.BUS_QuenMatKhau.kiemTraMatKhau(
						matKhauMoi, xacNhanMatKhauMoi);

				if (isMaXacNhanHopLe && isMatKhauMoiHopLe) {
					// Sau đó, bạn có thể hiển thị thông báo thành công cho
					// người dùng

					JOptionPane.showMessageDialog(JFrame_QuenMatKhau.this,
							"Xác nhận thành công! Mật khẩu đã được thay đổi.");

					// Cuối cùng, bạn có thể chuyển người dùng đến màn hình đăng
					// nhập
					DangNhap dangNhapFrame = new DangNhap();
					JFrame_QuenMatKhau.this.dispose();
					DAO_TaiKhoan daotk = new DAO_TaiKhoan();
					daotk.suaTK(ma, xacNhanMatKhauMoi);

				}
			}
		});

	}
}
