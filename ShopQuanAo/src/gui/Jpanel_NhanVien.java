package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.omg.CORBA.VM_NONE;

import com.toedter.calendar.JDateChooser;

import bus.BUS_NhanVien;
import connectDB.ConnectionManager;
import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.interfaces.RSAKey;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;

import java.awt.Rectangle;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Jpanel_NhanVien extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JTextField txt_NgayVaoLam;
	private JTextField txtEmail;
	private JTextField txtCCCD;
	private JTextField txtDiaChi;
	private ConnectionManager connectionManager = new ConnectionManager();
	private BUS_NhanVien busNV = new BUS_NhanVien();
	private DAO_NhanVien daoNV = new DAO_NhanVien();
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtTaiKhoan;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JRadioButton radNam, radNhanVien, radQuanLi;
	private JRadioButton radNu;
	private DAO_TaiKhoan daotk = new DAO_TaiKhoan();
	private JTextField txtmatKhau;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser txtNgayBD;
	private String ma;
	private String ten;
	private String sdt;
	private String email;
	private String cccd;
	private String diaChi;
	private LocalDate ngay;
	private boolean gtBoolean;
	private JButton btnxoaTK;
	private JTextField txtMK2;
	private final JTextArea txtGhiChu = new JTextArea();
	private JComboBox cboGioiTinh;
	private JComboBox cboTK;
	private JDateChooser txtLocNgayVL;
	private JLabel lblThongBao;

	/**
	 * Create the panel.
	 */
	public Jpanel_NhanVien() {
		setBounds(new Rectangle(0, 0, 1999, 969));
		setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0, 0, 0)));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 0, 2, 2, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 1646, 90);
		add(panel);
		panel.setLayout(null);

		JLabel lbl_TimKiem = new JLabel("Tìm kiếm :");
		lbl_TimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_TimKiem.setBounds(1058, 33, 123, 30);
		panel.add(lbl_TimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		txtTimKiem.setBounds(1191, 33, 258, 30);
		panel.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				String tukhoa = txtTimKiem.getText().trim();
				String sql = getDieuKienLoc(tukhoa);
				System.err.println("sql :" + sql);
				busNV.dodulieu(model, connectionManager.getdata(sql));
			}
		});

		
		JLabel lblNewLabel_1 = new JLabel("Giới tính :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(50, 33, 104, 26);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tài khoản :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(243, 33, 81, 26);
		panel.add(lblNewLabel_2);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Nam", "Nữ" }));
		cboGioiTinh.setBounds(131, 33, 96, 26);
		panel.add(cboGioiTinh);

		cboTK = new JComboBox();
		cboTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTK.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Có tài khoản", "Không có tài khoản" }));
		cboTK.setBounds(335, 33, 123, 26);
		panel.add(cboTK);

		txtLocNgayVL = new JDateChooser();
		txtLocNgayVL.setDateFormatString("dd/MM/yyyy");
		txtLocNgayVL.setDate(new java.util.Date());
		txtLocNgayVL.setBounds(670, 33, 200, 26);
		panel.add(txtLocNgayVL);

		JLabel lblLocNgayVL = new JLabel("Làm trước ngày :");
		lblLocNgayVL.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLocNgayVL.setBounds(520, 33, 138, 26);
		panel.add(lblLocNgayVL);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(0, 90, 1646, 335);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaNV.setBounds(278, 109, 114, 30);
		panel_1.add(lblMaNV);

		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHoTen.setBounds(278, 149, 114, 30);
		panel_1.add(lblHoTen);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSDT.setBounds(278, 189, 114, 30);
		panel_1.add(lblSDT);

		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm:");
		lblNgayVaoLam.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayVaoLam.setBounds(278, 229, 114, 30);
		panel_1.add(lblNgayVaoLam);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(693, 109, 80, 30);
		panel_1.add(lblEmail);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGioiTinh.setBounds(693, 149, 80, 30);
		panel_1.add(lblGioiTinh);

		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCCCD.setBounds(693, 189, 80, 30);
		panel_1.add(lblCCCD);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiaChi.setBounds(693, 229, 80, 30);
		panel_1.add(lblDiaChi);

		btnThem = new JButton();
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getThongTin();
				if (busNV.check_themNV(ma, ten, sdt, ngay, email, cccd, diaChi)) {
					Boolean gtBoolean = true;
					if (radNu.isSelected() == true) {
						gtBoolean = false;
					}
					NhanVien nv = new NhanVien(ma, ten, sdt, email, diaChi, cccd, gtBoolean, ngay);
					System.err.println(nv);
					if (daoNV.them(nv)) {
						busNV.dodulieu(model, daoNV.layNV());
					}
				}
				;
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThem.setIcon(new ImageIcon("IMG_Hinh\\them.jpg"));
		btnThem.setBounds(363, 280, 92, 45);
		panel_1.add(btnThem);

		btnSua = new JButton();
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getThongTin();
				System.err.println("Ngay được chọn :" + ngay);
				NhanVien nVien = new NhanVien(ma, ten, sdt, email, diaChi, cccd, gtBoolean, ngay);
				daoNV.sua(nVien);
				busNV.dodulieu(model, daoNV.layNV());
//				System.err.println(getDieuKienLoc());
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSua.setIcon(new ImageIcon("IMG_Hinh\\sua.jpg"));
		btnSua.setBounds(511, 280, 92, 45);
		panel_1.add(btnSua);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(40, 80, 167, 222);
		panel_1.add(panel_2);

		JLabel lblAnh = new JLabel("");
		panel_2.add(lblAnh);

		txtMaNV = new JTextField(busNV.updateMa());
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(403, 109, 200, 30);
		panel_1.add(txtMaNV);

		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(403, 149, 200, 30);
		panel_1.add(txtHoTen);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(403, 189, 200, 30);
		panel_1.add(txtSDT);

		txt_NgayVaoLam = new JTextField();
		txt_NgayVaoLam.setColumns(10);

		panel_1.add(txt_NgayVaoLam);
		txtNgayBD = new JDateChooser();
		txtNgayBD.setDateFormatString("dd/MM/yyyy");
		txtNgayBD.setBounds(403, 229, 200, 30);

		panel_1.add(txtNgayBD);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(783, 109, 197, 30);
		panel_1.add(txtEmail);

		txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(783, 189, 197, 30);
		panel_1.add(txtCCCD);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(783, 229, 197, 30);
		panel_1.add(txtDiaChi);

		btnLamMoi = new JButton();
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				xoaTrang();
			}
		});
		btnLamMoi.setIcon(new ImageIcon("IMG_Hinh\\reset.jpg"));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLamMoi.setBounds(658, 280, 92, 45);
		panel_1.add(btnLamMoi);

		radNam = new JRadioButton("Nam");
		radNam.setSelected(true);
		radNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radNam.setBounds(794, 149, 66, 25);

		panel_1.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radNu.setBounds(884, 149, 66, 25);
		panel_1.add(radNu);

		buttonGroup.add(radNam);
		buttonGroup.add(radNu);
		// =======================================================

		txtMaNV.setEditable(false);

		btnThem.setFocusPainted(false);
		btnSua.setFocusPainted(false);
		btnLamMoi.setFocusPainted(false);

		JLabel lblNewLabel = new JLabel("TÀI KHOẢN ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(1281, 30, 122, 30);
		panel_1.add(lblNewLabel);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setEditable(false);
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBounds(1250, 105, 167, 30);
		panel_1.add(txtTaiKhoan);

		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTaiKhoan.setBounds(1100, 109, 114, 30);
		panel_1.add(lblTaiKhoan);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatKhau.setBounds(1100, 189, 114, 30);
		panel_1.add(lblMatKhau);

		JLabel lblT = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblT.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblT.setBounds(521, 30, 250, 30);
		panel_1.add(lblT);

		txtmatKhau = new JTextField();
		txtmatKhau.setBounds(1250, 189, 167, 30);
		panel_1.add(txtmatKhau);

		JButton btnThemTK = new JButton("Thêm ");
		btnThemTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ma1 = txtmatKhau.getText().trim();
				String ma2 = txtMK2.getText().trim();
				if (busNV.kiemTraMatKhau(ma1, ma2)) {
					String tentk = txtMaNV.getText().trim();
					String ghiChu = txtGhiChu.getText().trim();

					// Nhân viên = true, quản lí = false;
					boolean vt = radNhanVien.isSelected();
					NhanVien a = daoNV.layNVTheoMa(tentk);
					TaiKhoan tk = new TaiKhoan(tentk, ma2, ghiChu, a, vt, LocalDate.now());
					if (daotk.themTaiKhoan(tk)) {
						if (daoNV.layNV() != null)
							busNV.dodulieu(model, daoNV.layNV());
					}
					;

				}
			}
		});
		btnThemTK.setBounds(1238, 304, 85, 21);
		panel_1.add(btnThemTK);

		btnxoaTK = new JButton("Xóa");
		btnxoaTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ma = txtMaNV.getText().trim();
				if (daotk.xoaTaiKhoan(ma)) {
					busNV.dodulieu(model, daoNV.layNV());
				}
			}
		});
		btnxoaTK.setBounds(1344, 304, 85, 21);
		panel_1.add(btnxoaTK);

		JLabel lblMatKhauLan2 = new JLabel("Nhập lại mật khẩu:");
		lblMatKhauLan2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatKhauLan2.setBounds(1100, 229, 150, 30);
		panel_1.add(lblMatKhauLan2);

		txtMK2 = new JTextField();
		txtMK2.setBounds(1250, 229, 167, 30);
		panel_1.add(txtMK2);
		txtGhiChu.setBounds(1467, 149, 167, 110);
		panel_1.add(txtGhiChu);

		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChucVu.setBounds(1100, 149, 80, 30);
		panel_1.add(lblChucVu);

		radNhanVien = new JRadioButton("Nhân Viên ");
		radNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radNhanVien.setBounds(1250, 149, 100, 30);
		radNhanVien.setSelected(true);
		panel_1.add(radNhanVien);

		radQuanLi = new JRadioButton("Quản lý");
		radQuanLi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radQuanLi.setBounds(1356, 149, 80, 30);
		panel_1.add(radQuanLi);
		ButtonGroup groupChucVu = new ButtonGroup();
		groupChucVu.add(radNhanVien);
		groupChucVu.add(radQuanLi);

		lblThongBao = new JLabel();
		lblThongBao.setForeground(new Color(255, 0, 0));
		lblThongBao.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblThongBao.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongBao.setBounds(1281, 60, 137, 25);
		panel_1.add(lblThongBao);
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGhiChu.setBounds(1468, 105, 114, 30);
		panel_1.add(lblGhiChu);
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(10, 426, 1646, 550);
		add(panel_3);

		String row[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Giới tính", "Email", "Ngày vào làm", "Địa chỉ", "Số điện thoại",
				"CCCD", "Tài khoản", "Ghi chú" };
		model = new DefaultTableModel(row, 0);
		panel_3.setLayout(null);
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		table.setSelectionBackground(new java.awt.Color(255, 204, 102));
		
		table.setRowHeight(30);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground((Color.decode("#00c691")));
		table.setFont(new Font("Tohoma", Font.PLAIN, 18));
		
		
		table.getColumnModel().getColumn(4).setPreferredWidth(230);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(10).setPreferredWidth(0);
		table.getColumnModel().getColumn(9).setPreferredWidth(50);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				xoaTrang();

				int row = table.getSelectedRow();
				txtMaNV.setText(table.getValueAt(row, 1).toString().trim());
				txtHoTen.setText(table.getValueAt(row, 2).toString().trim());
				if (table.getValueAt(row, 3).toString().trim() == "Nữ") {
					radNam.setSelected(false);
					radNu.setSelected(true);
				} else {
					radNam.setSelected(true);
					radNu.setSelected(false);
				}

				txtEmail.setText(table.getValueAt(row, 4).toString().trim());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				try {
					txtNgayBD.setDate(dateFormat.parse(table.getValueAt(row, 5).toString().trim()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txtDiaChi.setText(table.getValueAt(row, 6).toString().trim());
				txtSDT.setText(table.getValueAt(row, 7).toString().trim());
				txtCCCD.setText(table.getValueAt(row, 8).toString().trim());
				TaiKhoan tk = daotk.getTKtheoMa(table.getValueAt(row, 1).toString().trim());

				if (tk != null) {
					txtTaiKhoan.setText(tk.getTenTaiKhoan());
					txtmatKhau.setText(tk.getMatKhau());
					txtMK2.setText(tk.getMatKhau());
					txtGhiChu.setText(tk.getGhiChu());
					lblThongBao.setText("");
					if (tk.isVaiTro()) {
						radQuanLi.setSelected(true);
						radNhanVien.setSelected(false);
					}

					else {
						radQuanLi.setSelected(false);
						radNhanVien.setSelected(true);
					}
				} else {
					lblThongBao.setText("Chưa có tài khoản");

				}

			}
		});

		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(new Rectangle(0, 0, 135, 251));
		scrollPane.setBounds(10, 10, 1626, 520);
		panel_3.add(scrollPane);
		// đọc dữ liệu
		ResultSet rs = daoNV.layNV();
		System.err.println("kq đọc dữ liệu : " + rs);
		busNV.dodulieu(model, daoNV.layNV());
		
		cboGioiTinh.addActionListener(this);
		cboTK.addActionListener(this);

	}

	public void xoaTrang() {
		txtMaNV.setText(busNV.updateMa());
		txtHoTen.setText("");
		txtHoTen.requestFocus();
		txt_NgayVaoLam.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
		radNam.setSelected(true);
		radNu.setSelected(false);
		txtSDT.setText("");
		txtTaiKhoan.setText("");
		txtmatKhau.setText("");
		txtDiaChi.setText("");
		txtMK2.setText("");
		txtGhiChu.setText("");

	}

	public void getThongTin() {
		ma = txtMaNV.getText().trim();
		ten = txtHoTen.getText().trim();
		sdt = txtSDT.getText().trim();
		email = txtEmail.getText().trim();
		cccd = txtCCCD.getText().trim();
		diaChi = txtDiaChi.getText();
		ngay = busNV.chuyenDoiKieuNgay(txtNgayBD);
		gtBoolean = true;
		if (radNu.isSelected() == true) {
			gtBoolean = false;
		}
	}

	public String getDieuKienLoc(String tukhoa) {
		String tong = "select * from   NhanVien  left join TaiKhoan  on NhanVien.maNhanVien= TaiKhoan.maNhanVien where ( NhanVien.maNhanVien like N'%" + tukhoa
				+ "%' or NhanVien.tenNhanVien like N'%" + tukhoa + "%' or NhanVien.cccd like N'%" + tukhoa
				+ "%' or NhanVien.sdt like N'%" + tukhoa + "%')";

		if (cboTK.getSelectedItem().toString().equalsIgnoreCase("Không có tài khoản")) {
			tong += "and TaiKhoan.tenTaiKhoan is null ";
		} else if (cboTK.getSelectedItem().toString().equalsIgnoreCase("Có tài khoản")) {
			tong += " and TaiKhoan.tenTaiKhoan is not null ";
		} 		
		
		if (cboGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam")) {
			tong += "and NhanVien.gioiTinh = 1 ";
		} else if (cboGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nữ")) {
			tong += " and NhanVien.gioiTinh = 0 ";
		}

		ngay = busNV.chuyenDoiKieuNgay(txtLocNgayVL);
		System.err.println("ngay :" + ngay);
		tong += "and NhanVien.ngayVaoLam <= '" + ngay + "'";
//		String dk = " and NhanVien.ngayVaoLam <= '" + ngay + "' and ( NhanVien.maNhanVien like N'%" + tukhoa
//				+ "%' or NhanVien.tenNhanVien like N'%" + tukhoa + "%' or NhanVien.cccd like N'%" + tukhoa
//				+ "%' or NhanVien.sdt like N'%" + tukhoa + "%') ";
//		tong += dk;
		return tong;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String tukhoa = txtTimKiem.getText().trim();
		String sql = getDieuKienLoc(tukhoa);
		System.err.println("sql :" + sql);
		Object o = e.getSource();
		if (o.equals(cboGioiTinh)) {
			busNV.dodulieu(model, connectionManager.getdata(sql));
		}
		if (o.equals(cboTK)) {
			busNV.dodulieu(model, connectionManager.getdata(sql));
		}
	}
}
