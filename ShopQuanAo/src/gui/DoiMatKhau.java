package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dao.DAO_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;

public class DoiMatKhau extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtMKCu;
	private JPasswordField txtMKMoi;
	private JPasswordField txtXacNhanMKMoi;
	private JButton btnhienMK;
	private boolean chon1 = true, chon = true;
	private NhanVien nv;
	private TaiKhoan vt;

	/**
	 * Create the frame.
	 */
	public DoiMatKhau(String manv) {
		setBounds(100, 100, 554, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(new Color(51, 204, 204));
		pnlTieuDe.setBounds(0, 0, 531, 56);
		contentPane.add(pnlTieuDe);
		pnlTieuDe.setLayout(null);

		JLabel lblDoiMatKhau = new JLabel("Đổi Mật Khẩu");
		lblDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDoiMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoiMatKhau.setBounds(190, 15, 160, 35);
		pnlTieuDe.add(lblDoiMatKhau);

		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(0, 55, 531, 164);
		pnlMain.setBackground(new Color(204, 255, 255));
		contentPane.add(pnlMain);
		pnlMain.setLayout(null);
		btnhienMK = new JButton("");
		btnhienMK.setBorder(null);
		btnhienMK.setBackground(new Color(204, 255, 255));
		btnhienMK.setIcon(new ImageIcon(
				"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32.png"));
		btnhienMK.setBounds(484, 20, 32, 30);
		pnlMain.add(btnhienMK);
		btnhienMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chon == true) {
					txtMKCu.setEchoChar((char) 0);
					chon = false;
				} else {
					txtMKCu.setEchoChar('*');
					chon = true;
				}
			}
		});

		JLabel lblMKCu = new JLabel("Mật khẩu cũ:");
		lblMKCu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMKCu.setBounds(60, 25, 112, 20);
		pnlMain.add(lblMKCu);

		JLabel lblMKMoi = new JLabel("Mật khẩu mới:");
		lblMKMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMKMoi.setBounds(60, 75, 122, 20);
		pnlMain.add(lblMKMoi);

		JLabel lblXacNhanMKMoi = new JLabel("Xác nhận lại mật khẩu:");
		lblXacNhanMKMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblXacNhanMKMoi.setBounds(60, 125, 188, 20);
		pnlMain.add(lblXacNhanMKMoi);

		txtMKCu = new JPasswordField();
		txtMKCu.setBounds(265, 20, 200, 30);
		pnlMain.add(txtMKCu);
		txtMKCu.setColumns(10);

		txtMKMoi = new JPasswordField();
		txtMKMoi.setColumns(10);
		txtMKMoi.setBounds(265, 70, 200, 30);
		pnlMain.add(txtMKMoi);

		txtXacNhanMKMoi = new JPasswordField();
		txtXacNhanMKMoi.setColumns(10);
		txtXacNhanMKMoi.setBounds(265, 120, 200, 30);
		pnlMain.add(txtXacNhanMKMoi);

		JButton btnHienMkmoi1 = new JButton("");

		btnHienMkmoi1.setBorder(null);
		btnHienMkmoi1.setBackground(new Color(204, 255, 255));
		btnHienMkmoi1.setBounds(484, 70, 32, 30);
		pnlMain.add(btnHienMkmoi1);
		btnHienMkmoi1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (chon1 == true) {
					txtMKMoi.setEchoChar((char) 0);
					chon1 = false;
				} else {
					txtMKMoi.setEchoChar('*');
					chon1 = true;
				}
			}
		});

		JButton btnHienMKmoi2 = new JButton("");
		btnHienMKmoi2.setBorder(null);
		btnHienMKmoi2.setBackground(new Color(204, 255, 255));
		btnHienMKmoi2.setBounds(484, 120, 32, 30);
		pnlMain.add(btnHienMKmoi2);
		btnHienMKmoi2.addActionListener(new ActionListener() {
			boolean chon2 = true;

			public void actionPerformed(ActionEvent e) {
				if (chon2 == true) {
					btnHienMKmoi2
							.setIcon(new ImageIcon(
									"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32_close.png"));
					txtXacNhanMKMoi.setEchoChar((char) 0);
					chon2 = false;
				} else {
					btnHienMKmoi2
							.setIcon(new ImageIcon(
									"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\hide_pw_32.png"));
					txtXacNhanMKMoi.setEchoChar('*');
					chon2 = true;
				}
			}
		});

		JPanel pnlBot = new JPanel();
		pnlBot.setBounds(0, 219, 531, 71);
		pnlBot.setBackground(new Color(204, 255, 255));
		contentPane.add(pnlBot);
		pnlBot.setLayout(null);

		JButton btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setBounds(307, 16, 115, 35);
		btnQuayLai.setBackground(new Color(255, 255, 51));
		pnlBot.add(btnQuayLai);

		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setBounds(120, 16, 115, 35);
		btnXacNhan.setBackground(new Color(102, 153, 255));
		pnlBot.add(btnXacNhan);

		btnQuayLai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DoiMatKhau.this.dispose();
			}
		});

		btnXacNhan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Lấy mật khẩu mới từ trường txtMKMoi
				String matKhauCu = new String(txtMKCu.getPassword());

				// Lấy xác nhận mật khẩu mới từ trường txtNLMKMoi
				String matKhauMoi = new String(txtMKMoi.getPassword());
				String xacNhanMKMoi = new String(txtXacNhanMKMoi.getPassword());

				// Kiểm tra mật khẩu mới
				boolean isMatKhauMoiHopLe = bus.BUS_QuenMatKhau.kiemTraMatKhau(
						matKhauMoi, xacNhanMKMoi);

				if (bus.BUS_QuenMatKhau.kiemTraMKCu(manv, matKhauCu)
						&& isMatKhauMoiHopLe) {
					// Sau đó, bạn có thể hiển thị thông báo thành công cho
					// người dùng

					JOptionPane.showMessageDialog(DoiMatKhau.this,
							"Đổi mật khẩu thành công!");

					// Cuối cùng, bạn có thể chuyển người dùng đến màn hình đăng
					// nhập
					DangNhap dangNhapFrame = new DangNhap();
					DoiMatKhau.this.dispose();
					DAO_TaiKhoan daotk = new DAO_TaiKhoan();
					daotk.suaTK(manv, xacNhanMKMoi);

				}
			}
		});
	}
}
