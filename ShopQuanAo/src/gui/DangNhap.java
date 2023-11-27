package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.AbstractButton;
import javax.swing.border.MatteBorder;

import bus.BUS_DangNhap;
import connectDB.ConnectionManager;
import entity.NhanVien;

public class DangNhap extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JLabel lblTieuDe;
	private Font font_btn13 = new Font("Arial", Font.BOLD, 13);
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JButton btnDangNhap;
	private AbstractButton btnQuenMatKhau;
	private boolean chon = true;
	private JButton btnHienMK_2;
	private BUS_DangNhap busTK= new BUS_DangNhap();
	ConnectionManager connectionManager= new ConnectionManager();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 440);

		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#00c691"));

		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnLeft = new JPanel();
		pnLeft.setBounds(2, 2, 280, 436);
		contentPane.add(pnLeft);
		pnLeft.setLayout(null);

		JLabel lblBacKground = new JLabel("");
		lblBacKground.setIcon(new ImageIcon("IMG_Hinh\\logo_290x440.png"));
		lblBacKground.setBounds(0, 0, 280, 436);
		pnLeft.add(lblBacKground);

		JPanel pnRight = new JPanel();
		pnRight.setBorder(null);
		pnRight.setBounds(269, 2, 457, 436);
		pnRight.setBackground(Color.decode("#00c691"));
		contentPane.add(pnRight);
		pnRight.setLayout(null);

		lblTieuDe = new JLabel("Đăng Nhập");
		lblTieuDe.setForeground(new Color(255, 255, 255));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setBounds(159, 62, 194, 47);

		pnRight.add(lblTieuDe);

		JButton btnExit = new JButton("");
		btnExit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon("IMG_Hinh\\exit.png"));
		btnExit.setBounds(419, 10, 30, 30);
		pnRight.add(btnExit);

		JLabel lblTaiKhoan = new JLabel("Tài Khoản             :");
		lblTaiKhoan.setIcon(new ImageIcon("IMG_Hinh\\icons8_male_user_32.png"));
		lblTaiKhoan.setFont(font_btn13);
		lblTaiKhoan.setForeground(new Color(255, 255, 255));
		lblTaiKhoan.setBounds(34, 139, 165, 33);
		pnRight.add(lblTaiKhoan);

		txtTaiKhoan = new JTextField("NV01");
		txtTaiKhoan.setSelectedTextColor(new Color(255, 255, 255));
		txtTaiKhoan.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtTaiKhoan.setForeground(new Color(255, 255, 255));
		txtTaiKhoan.setBounds(199, 139, 172, 33);
		txtTaiKhoan.setForeground(Color.WHITE);
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTaiKhoan.setCaretColor(new Color(255, 255, 255));
		txtTaiKhoan.setBackground(Color.decode("#00c691"));
		pnRight.add(txtTaiKhoan);

		JLabel lblMatKhau = new JLabel("Mật Khẩu             :");
		lblMatKhau.setIcon(new ImageIcon("IMG_Hinh\\password_32px.png"));
		lblMatKhau.setForeground(Color.WHITE);
		lblMatKhau.setFont(new Font("Arial", Font.BOLD, 13));
		lblMatKhau.setBounds(34, 245, 165, 33);
		pnRight.add(lblMatKhau);

		txtMatKhau = new JPasswordField("123");
		txtMatKhau.setForeground(Color.WHITE);
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtMatKhau.setBackground(new Color(0, 198, 145));
		txtMatKhau.setBounds(199, 245, 172, 33);
		txtMatKhau.setCaretColor(new Color(255, 255, 255));
		txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 15));
		pnRight.add(txtMatKhau);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBorder(null);
		btnDangNhap.setBounds(42, 355, 157, 35);
		pnRight.add(btnDangNhap);
		btnDangNhap.setBackground(Color.decode("#fcbe00"));
		btnDangNhap.setForeground(Color.white);
		btnDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		btnDangNhap.setFocusPainted(false);
		btnDangNhap.setFont(new Font("Arial", Font.BOLD, 13));
		btnDangNhap.setIcon(new ImageIcon("IMG_Hinh\\login.png"));
//		btnDangNhap.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(txtTaiKhoan.getText().equals("admin") && (txtMatKhau.getText().toString().equals("123"))){
//						TrangChu frm = new TrangChu();
//						frm.setVisible(true);
//						frm.setLocationRelativeTo(null);
//						frm.setVisible(true);
//						frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
//						setVisible(false);
//					
//				}
//			}
//		});
		
		btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDangNhapActionPerformed(evt);
			}
		});
		btnQuenMatKhau = new JButton("Quên mật khẩu");
		btnQuenMatKhau.setIcon(new ImageIcon("IMG_Hinh\\reset.png"));
		btnQuenMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		btnQuenMatKhau.setForeground(Color.white);
		btnQuenMatKhau.setFocusPainted(false);
		btnQuenMatKhau.setBorder(null);
		btnQuenMatKhau.setFont(new Font("Arial", Font.BOLD, 13));
		btnQuenMatKhau.setBackground(Color.decode("#fcbe00"));
		btnQuenMatKhau.setBounds(253, 355, 157, 35);
		pnRight.add(btnQuenMatKhau);
		
		btnHienMK_2 = new JButton("");
		
		btnHienMK_2.setIcon(new ImageIcon("IMG_Hinh\\hide_pw_32_white.png"));
		btnHienMK_2.setBorder(null);
		btnHienMK_2.setBackground(Color.decode("#00c691"));
		btnHienMK_2.setBounds(389, 245, 32, 32);
		pnRight.add(btnHienMK_2);
		btnHienMK_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chon==true) {
					btnHienMK_2.setIcon(new ImageIcon("IMG_Hinh\\hide_pw_32_white_close.png"));
					txtMatKhau.setEchoChar((char )0);
					chon=false;
				}
				else {
					btnHienMK_2.setIcon(new ImageIcon("IMG_Hinh\\hide_pw_32_white.png"));
					txtMatKhau.setEchoChar('*');
					chon=true;
				}
			}
		});
		
		// đăng kí sự kiện chuột
//		btnDangNhap.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				btnDangNhap.setBackground(Color.decode("#fcbe00"));
//				btnDangNhap.setForeground(Color.decode("#f0f6fb"));
//
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//				btnDangNhap.setForeground(Color.decode("#00c691"));
//				btnDangNhap.setBackground(Color.decode("#f0f6fb"));
//			}
//		});
		btnQuenMatKhau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ma = txtTaiKhoan.getText().trim() ;
				JFrame_QuenMatKhau frm = new JFrame_QuenMatKhau(ma);
				frm.setVisible(true);

			}
		});
		
		txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDangNhapActionPerformed(evt);
			}
		});
//		ResultSet a= daoTK.getDSNhanVien();
//		String aString= "chuôi : ";
//		try {
//			while(a.next()) {
//				aString += a.getString("tenTaiKhoan")+"\t";
//				aString += a.getString("matKhau")+"\n";
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println(aString);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangNhapActionPerformed

		String tk= txtTaiKhoan.getText().trim();
		String mk= txtMatKhau.getText();
		NhanVien nv = dao.DAO_NhanVien.layNVTheoMa(tk);
		if(BUS_DangNhap.kt_DangNhap(tk, mk)){
//			thongbao.thongbao("Đăng nhập thành công", "");
			TrangChu frm = new TrangChu(nv,null);
			System.err.println("Tài khoản dn:"+ tk);
			frm.setVisible(true);
			frm.setLocationRelativeTo(null);
			frm.setVisible(true);
			frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(false);
		} else {
			thongbao.thongbao("Tài khoản hoặc mật khẩu không chính xác", "");
		}

	}

	public static DangNhap getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
