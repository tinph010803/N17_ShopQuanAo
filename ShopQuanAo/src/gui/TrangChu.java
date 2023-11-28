package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.SystemColor;

import javax.swing.JPasswordField;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import connectDB.ConnectionManager;
import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

public class TrangChu extends JFrame implements ActionListener {

	/**
	 * Launch the application.
	 */

	ConnectionManager connectionManager = new ConnectionManager();
	private JPanel contentPane;
	private JLabel lblTime;
	private JButton btnSanPham;
	private JButton btnKhachHang;
	private JPanel pnlMain;
	private JButton btnChon = null;
	private JButton btnHoaDon;
	private JPanel panel;
	private JPanel pnlMenu;
	private JLabel lblTenShop;
	private JPanel pnlHead;
	private JButton btnDangXuat;
	private JButton btnCaNhan;
	private JLabel lblTenNV;
	private JLabel lblVaiTro;
	private JButton btnTrangChu;
	private JButton btnBanHang;
	private JButton btnNhanVien;
	private JButton btnTroGiup;
	private JButton btnKhuyenMai;
	private JLabel lblNen;
	private JButton btnNCC;
	private JMenuBar menuBarThongKe;
	private JMenuItem menuDoanhThu;
	private JMenuItem menuSanPham;
	private JMenuItem menuKhachHang;
	private JMenu menuThongKe;
	private JPanel pnlThongKe;
	private String tenNhanVien;
//	private DAO_NhanVien daonv;


	void showtime() {
		new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date d = new Date();
				SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
				SimpleDateFormat D = new SimpleDateFormat("dd/MM/yyyy");
				lblTime.setText(s.format(d) + "-" + D.format(d));
			}
		}).start();
	}

	public TrangChu(NhanVien nv, TaiKhoan vt) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 1700, 1020);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		showtime();
	

		panel = new JPanel();
		panel.setBounds(5, 5, 1910, 1045);
		panel.setForeground(new Color(220, 20, 60));
		panel.setBackground(SystemColor.window);
		contentPane.add(panel);
		panel.setLayout(null);

		pnlHead = new JPanel();
		pnlHead.setBorder(new MatteBorder(0, 0, 1, 0,
				(Color) new Color(0, 0, 0)));
		pnlHead.setBackground(new Color(0, 250, 154));
		pnlHead.setBounds(0, 0, 1910, 70);
		panel.add(pnlHead);
		pnlHead.setLayout(null);

		lblTenShop = new JLabel("Street Fashion Shop");
		lblTenShop.setBounds(619, 13, 562, 35);
		lblTenShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenShop.setForeground(new Color(210, 105, 30));
		lblTenShop.setBackground(Color.ORANGE);
		lblTenShop.setFont(new Font("Tahoma", Font.BOLD, 35));
		pnlHead.add(lblTenShop);

		pnlMenu = new JPanel();
		pnlMenu.setBorder(new MatteBorder(0, 0, 0, 1,
				(Color) new Color(0, 0, 0)));
		pnlMenu.setBackground(new Color(0, 250, 154));
		pnlMenu.setBounds(0, 0, 264, 1045);
		panel.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDangXuat.setBackground(Color.decode("#ffa500"));
		
		btnDangXuat.setBounds(12, 150, 111, 35);
		pnlMenu.add(btnDangXuat);

		btnCaNhan = new JButton("Cá nhân");
		btnCaNhan.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnCaNhan.setBackground(Color.decode("#ffa500"));
		btnCaNhan.setBounds(141, 150, 111, 35);
		pnlMenu.add(btnCaNhan);

		lblTenNV = new JLabel("");
		lblTenNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenNV.setBounds(47, 210, 170, 30);
		pnlMenu.add(lblTenNV);
		
//		System.err.println("mã:"+nv.getMaNhanVien());
		lblTenNV.setText(nv.getTenNhanVien()); 
		
		
		lblVaiTro = new JLabel("");
		lblVaiTro.setHorizontalAlignment(SwingConstants.CENTER);
		lblVaiTro.setForeground(new Color(0, 0, 205));
		lblVaiTro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVaiTro.setBounds(55, 250, 154, 30);
		pnlMenu.add(lblVaiTro);
		DAO_TaiKhoan daotk= new DAO_TaiKhoan();
		
		if(daotk.getTKtheoMa(nv.getMaNhanVien()).isVaiTro()){
			lblVaiTro.setText("QUẢN LÝ");
		}else {
			lblVaiTro.setText("NHÂN VIÊN");
		}

		btnTrangChu = new JButton("Trang chủ");
		btnTrangChu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTrangChu.setBackground(new Color(255, 165, 0));
		btnTrangChu.setFont(new Font("Tahoma", Font.BOLD, 21));
		pnlMenu.add(btnTrangChu);

		btnBanHang = new JButton("Quản lý bán hàng");
		btnBanHang.setBackground(new Color(255, 165, 0));
		btnBanHang.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBanHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBanHang.setBounds(0, 369, 264, 54);
		pnlMenu.add(btnBanHang);

		btnKhachHang = new JButton("Quản lý khách hàng");
		btnKhachHang.setBounds(0, 436, 264, 54);
		btnKhachHang.setBackground(new Color(255, 165, 0));
		btnKhachHang.setFont(new Font("Tahoma", Font.BOLD, 21));
		pnlMenu.add(btnKhachHang);

		btnSanPham = new JButton("Quản lý sản phẩm");
		btnSanPham.setBounds(0, 503, 264, 54);
		btnSanPham.setBackground(new Color(255, 165, 0));
		btnSanPham.setFont(new Font("Tahoma", Font.BOLD, 21));
		pnlMenu.add(btnSanPham);

		btnHoaDon = new JButton("Quản lý hóa đơn");
		btnHoaDon.setBounds(0, 570, 264, 54);
		btnHoaDon.setBackground(new Color(255, 165, 0));
		btnHoaDon.setFont(new Font("Tahoma", Font.BOLD, 21));
		pnlMenu.add(btnHoaDon);

		btnNhanVien = new JButton("Quản lý nhân viên");
		btnNhanVien.setBounds(0, 637, 264, 54);
		btnNhanVien.setBackground(new Color(255, 165, 0));
		btnNhanVien.setFont(new Font("Tahoma", Font.BOLD, 21));
		pnlMenu.add(btnNhanVien);

		btnTroGiup = new JButton("Trợ giúp");
		btnTroGiup.setBackground(new Color(255, 165, 0));
		btnTroGiup.setFont(new Font("Tahoma", Font.BOLD, 21));
		pnlMenu.add(btnTroGiup);

		lblTime = new JLabel();
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(12, 1010, 229, 25);
		pnlMenu.add(lblTime);

		pnlMain = new JPanel();
		pnlMain.setBounds(263, 70, 1646, 975);
		pnlMain.setBackground(Color.decode("#FAEBD7"));
		panel.add(pnlMain);
		pnlMain.setLayout(null);

		lblNen = new JLabel("");
		lblNen.setBounds(0, 0, 1646, 975);
		ImageIcon ii = new ImageIcon(new ImageIcon("IMG_Hinh//Nen_TrangChu.jpg").getImage()
				.getScaledInstance(lblNen.getWidth(), lblNen.getHeight(),
						Image.SCALE_SMOOTH));
		lblNen.setIcon(ii);
		pnlMain.add(lblNen);

		btnKhuyenMai = new JButton("Quản lý khuyến mãi");
		btnKhuyenMai.setBounds(0, 771, 264, 54);
		btnKhuyenMai.setBackground(new Color(255, 165, 0));
		btnKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 21));
		pnlMenu.add(btnKhuyenMai);

		btnNCC = new JButton("Quản lý nhà cung cấp");

		btnNCC.setFocusPainted(false);
		btnNCC.setBounds(0, 838, 264, 54);
		btnNCC.setBackground(new Color(255, 165, 0));
		btnNCC.setFont(new Font("Tahoma", Font.BOLD, 21));
		pnlMenu.add(btnNCC);

		// =================================================================================

		btnTrangChu.setFocusPainted(false);
		btnBanHang.setFocusPainted(false);
		btnKhachHang.setFocusPainted(false);
		btnSanPham.setFocusPainted(false);
		btnHoaDon.setFocusPainted(false);
		btnNhanVien.setFocusPainted(false);
		btnKhuyenMai.setFocusPainted(false);
		btnTroGiup.setFocusPainted(false);
		btnDangXuat.setFocusPainted(false);
		btnCaNhan.setFocusPainted(false);
		btnNCC.setFocusPainted(false);

		// =================================================================================
		btnTrangChu.setBackground(Color.decode("#DA81F5"));
		btnChon = btnTrangChu;

		pnlThongKe = new JPanel();
		pnlThongKe.setBounds(0, 704, 263, 53);
		pnlMenu.add(pnlThongKe);
		pnlThongKe.setLayout(null);

		menuBarThongKe = new JMenuBar();
		menuBarThongKe.setBounds(0, 0, 264, 53);
		pnlThongKe.add(menuBarThongKe);
//		menuBarThongKe.setBackground(Color.decode("#ffa500"));

		menuThongKe = new JMenu("      Quản lý thống kê");
		menuThongKe.setFont(new Font("Tahoma", Font.BOLD, 21));
//		menuThongKe.setBackground(new Color(0, 250, 154));
		menuBarThongKe.add(menuThongKe);

		menuThongKe.setPreferredSize(new Dimension(menuBarThongKe.getWidth(),
				menuBarThongKe.getHeight()));

		menuDoanhThu = new JMenuItem("Thống kê doanh thu");
		menuThongKe.add(menuDoanhThu);

		menuSanPham = new JMenuItem("Thống kê sản phẩm");
		menuThongKe.add(menuSanPham);

		menuKhachHang = new JMenuItem("Thống kê khách hàng");
		menuThongKe.add(menuKhachHang);

		// =====================================================================================

		System.err.println(nv.getMaNhanVien());
		if (daotk.getTKtheoMa(nv.getMaNhanVien()).isVaiTro()) {
			btnTrangChu.setVisible(true);
			btnTrangChu.setBounds(0, 302, 264, 54);
			btnBanHang.setVisible(true);
			btnKhachHang.setVisible(true);
			btnSanPham.setVisible(true);
			btnHoaDon.setVisible(true);
			btnNhanVien.setVisible(true);
			menuBarThongKe.setVisible(true);
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setVisible(true);
			btnNCC.setVisible(true);
			btnTroGiup.setVisible(true);
			btnTroGiup.setBounds(0, 905, 264, 54);
		} else {
			btnTrangChu.setVisible(true);
			btnBanHang.setVisible(true);
			btnKhachHang.setVisible(true);
			btnSanPham.setVisible(true);
			btnHoaDon.setVisible(true);
			btnNhanVien.setVisible(false);
			menuBarThongKe.setVisible(false);
			pnlThongKe.setBackground(new Color(0, 250, 154));
			btnKhuyenMai.setVisible(false);
			btnNCC.setVisible(false);
			btnTroGiup.setVisible(true);
			btnTrangChu.setBounds(0, 362, 264, 54);
			btnTroGiup.setBounds(0, 817, 264, 54);
			btnBanHang.setBounds(0, 453, 264, 54);
			btnKhachHang.setBounds(0, 544, 264, 54);
			btnSanPham.setBounds(0, 635, 264, 54);
			btnHoaDon.setBounds(0, 726, 264, 54);
			
		}

		btnDangXuat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null,
						"Bạn có chắc chắn muốn đăng xuất?",
						"Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
						Jpanel_BanHang.webcam.close();
					}
					dispose();

					DangNhap quayLai = DangNhap.getInstance();

					if (quayLai != null) {
						quayLai.setVisible(true); // Hiển thị cửa sổ Đăng Nhập
					} else {
						// Nếu cửa sổ Đăng Nhập chưa được tạo, bạn có thể tạo nó
						// và hiển thị ở đây
						quayLai = new DangNhap();
						quayLai.setVisible(true);
					}
				}
			}
		});
		
		btnTrangChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnTrangChu.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnTrangChu.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(lblNen);
				pnlMain.revalidate();
				pnlMain.repaint();
				btnChon = btnTrangChu;
				
				setLaiMau();
			}
		});
		
		btnBanHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnBanHang.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnBanHang.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new Jpanel_BanHang(nv));
				System.out.println(nv.getMaNhanVien());
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnBanHang;
				
				setLaiMau();
			}
		});

		btnKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnKhachHang.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnKhachHang.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new Jpanel_KhachHang());
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnKhachHang;
				
				setLaiMau();
			}
		});

		btnSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnSanPham.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnSanPham.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new Jpanel_SanPham());
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnSanPham;
				
				setLaiMau();
			}
		});

		
		btnHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnHoaDon.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnHoaDon.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new Jpanel_HoaDon(nv));
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnHoaDon;
				
				setLaiMau();
			}
		});
		
		btnNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnNhanVien.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnNhanVien.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new Jpanel_NhanVien());
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnNhanVien;
				
				setLaiMau();
			}
		});
		
		btnKhuyenMai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnKhuyenMai.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnKhuyenMai.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new Jpanel_KhuyenMai());
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnKhuyenMai;
				
				setLaiMau();
			}
		});
		
		btnNCC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnNCC.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnNCC.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new Jpanel_NhaCungCap());
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnNCC;
				
				setLaiMau();
			}
		});
		
		btnTroGiup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnTroGiup.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnTroGiup.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new Jpanel_TroGiup());
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnSanPham;
				
				setLaiMau();
			}
		});
		
		btnCaNhan.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnCaNhan.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnCaNhan.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlMain.removeAll();
				pnlMain.setLayout(new BorderLayout());
				pnlMain.add(new ThongTinNV(nv,vt));
				pnlMain.revalidate();
		        pnlMain.repaint();
		        btnChon = btnCaNhan;
				
				setLaiMau();
			}
		});
		// ==========================================================================================

		menuKhachHang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMenuInterface(new Jpanel_ThongKeKhachHang());
			}
		});

		menuDoanhThu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMenuInterface(new Jpanel_ThongKeDoanhThu());
			}
		});

		menuSanPham.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setMenuInterface(new Jpanel_ThongKeSanPham());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
//		menuBarThongKe.setVisible(false);
//		menuThongKe.setVisible(false);
//		
		
	}

	private void setMenuInterface(JPanel panel) {
		btnSanPham.setBackground(Color.decode("#ffa500"));
		btnTrangChu.setBackground(Color.decode("#ffa500"));
		btnTroGiup.setBackground(Color.decode("#ffa500"));
		btnKhachHang.setBackground(Color.decode("#ffa500"));
		btnKhuyenMai.setBackground(Color.decode("#ffa500"));
		btnHoaDon.setBackground(Color.decode("#ffa500"));
		btnBanHang.setBackground(Color.decode("#ffa500"));
		btnNCC.setBackground(Color.decode("#ffa500"));
		btnNhanVien.setBackground(Color.decode("#ffa500"));
		menuBarThongKe.setBackground(Color.decode("#DA81F5"));
		
		
		pnlMain.removeAll();
		pnlMain.setLayout(new BorderLayout());
		pnlMain.add(panel);
		pnlMain.revalidate();
		pnlMain.repaint();
		
		if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
			Jpanel_BanHang.webcam.close();
		}
	}
	

	private void setLaiMau() {
		if (btnChon == btnTrangChu) {
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnNCC.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		} else if (btnChon == btnBanHang) {
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnNCC.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
		} else if (btnChon == btnKhachHang) {
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnNCC.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		} else if (btnChon == btnSanPham) {
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnNCC.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		} else if (btnChon == btnHoaDon) {
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		} else if (btnChon == btnNhanVien) {
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnNCC.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		} else if (btnChon == btnTroGiup) {
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnNCC.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		} else if (btnChon == btnKhuyenMai) {
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnNCC.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		} else if (btnChon == btnNCC) {
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnCaNhan.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		} else if (btnChon == btnCaNhan) {
			btnBanHang.setBackground(Color.decode("#ffa500"));
			btnKhachHang.setBackground(Color.decode("#ffa500"));
			btnSanPham.setBackground(Color.decode("#ffa500"));
			btnHoaDon.setBackground(Color.decode("#ffa500"));
			btnNhanVien.setBackground(Color.decode("#ffa500"));
			menuBarThongKe.setBackground(Color.decode("#ffa500"));
			btnTrangChu.setBackground(Color.decode("#ffa500"));
			btnTroGiup.setBackground(Color.decode("#ffa500"));
			btnKhuyenMai.setBackground(Color.decode("#ffa500"));
			btnNCC.setBackground(Color.decode("#ffa500"));
			if (Jpanel_BanHang.webcam != null && Jpanel_BanHang.webcam.isOpen()) {
				Jpanel_BanHang.webcam.close();
			}
		}
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
