package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.xml.soap.Node;

import org.omg.CORBA.VM_NONE;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.toedter.calendar.JDateChooser;

import bus.BUS_NhanVien;
import connectDB.ConnectionManager;
import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageProducer;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.security.interfaces.RSAKey;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;

import java.awt.Rectangle;

import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Jpanel_NhanVien extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String imageName = null;
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
	private DefaultTableModel modelNV;
	private JTable tableNV;
	private JScrollPane scrollPaneNV;
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
	private String diaChi,anh;
	private LocalDate ngay;
	private boolean gtBoolean;
	private JButton btnxoaTK;
	private JTextField txtMK2;
	private final JTextArea txtGhiChu = new JTextArea();
	private JComboBox cboGioiTinh;
	private JComboBox cboTK;
	private JDateChooser txtLocNgayVL;
	private JLabel lblThongBao;
	private JLabel lblAnh;
	private JPanel panel_2;

	/**
	 * Create the panel.
	 */
	public Jpanel_NhanVien() {
		setBounds(new Rectangle(0, 0, 1999, 969));
		setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		setLayout(null);

		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(255, 255, 237));
		pnlTop.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pnlTop.setBounds(0, 0, 1646, 90);
		add(pnlTop);
		pnlTop.setLayout(null);

		JLabel lbl_TimKiem = new JLabel("Tìm kiếm :");
		lbl_TimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_TimKiem.setBounds(1058, 33, 123, 35);
		pnlTop.add(lbl_TimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimKiem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0,
				0, 0)));
		txtTimKiem.setBounds(1191, 33, 258, 30);
		pnlTop.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				String tukhoa = txtTimKiem.getText().trim();
				String sql = getDieuKienLoc(tukhoa);
				busNV.dodulieu(modelNV, connectionManager.getdata(sql));
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Giới tính :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(50, 33, 80, 35);
		pnlTop.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tài khoản :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(243, 33, 95, 35);
		pnlTop.add(lblNewLabel_2);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setBackground(new Color(240, 255, 241));
		cboGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"Nam", "Nữ" }));
		cboGioiTinh.setBounds(131, 33, 96, 35);
		pnlTop.add(cboGioiTinh);

		cboTK = new JComboBox();
		cboTK.setBackground(new Color(240, 255, 241));
		cboTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTK.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"Có tài khoản", "Không có tài khoản" }));
		cboTK.setBounds(335, 33, 123, 35);
		pnlTop.add(cboTK);

		txtLocNgayVL = new JDateChooser();
		txtLocNgayVL.getCalendarButton().setFont(
				new Font("Tahoma", Font.PLAIN, 18));
		txtLocNgayVL.setDateFormatString("dd/MM/yyyy");
		txtLocNgayVL.setDate(new java.util.Date());
		txtLocNgayVL.setBounds(660, 33, 180, 35);
		pnlTop.add(txtLocNgayVL);

		JLabel lblLocNgayVL = new JLabel("Làm trước ngày :");
		lblLocNgayVL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLocNgayVL.setBounds(520, 33, 150, 35);
		pnlTop.add(lblLocNgayVL);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(255, 255, 237));
		pnlCenter.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0,
				0)));
		pnlCenter.setBounds(0, 90, 1646, 335);
		add(pnlCenter);
		pnlCenter.setLayout(null);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMaNV.setBounds(270, 90, 130, 30);
		pnlCenter.add(lblMaNV);

		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHoTen.setBounds(270, 180, 130, 30);
		pnlCenter.add(lblHoTen);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSDT.setBounds(270, 135, 130, 30);
		pnlCenter.add(lblSDT);

		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm:");
		lblNgayVaoLam.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNgayVaoLam.setBounds(270, 225, 130, 30);
		pnlCenter.add(lblNgayVaoLam);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmail.setBounds(693, 90, 80, 30);
		pnlCenter.add(lblEmail);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGioiTinh.setBounds(693, 135, 80, 30);
		pnlCenter.add(lblGioiTinh);

		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCCCD.setBounds(693, 180, 80, 30);
		pnlCenter.add(lblCCCD);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiaChi.setBounds(693, 220, 80, 30);
		pnlCenter.add(lblDiaChi);

		btnThem = new JButton();
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getThongTin();
				if (busNV.check_themNV(ma, ten, sdt, ngay, email, cccd, diaChi)) {
					Boolean gtBoolean = true;
					if (radNu.isSelected() == true) {
						gtBoolean = false;
					}
					NhanVien nv = new NhanVien(ma, ten, sdt, email, diaChi,
							cccd, gtBoolean, ngay, imageName);
					if (daoNV.them(nv)) {
						busNV.dodulieu(modelNV, daoNV.layNV());
					}
				}
				;
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThem.setIcon(new ImageIcon("IMG_Hinh\\them.jpg"));
		btnThem.setBounds(410, 280, 92, 45);
		pnlCenter.add(btnThem);

		btnSua = new JButton();
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getThongTin();
				if(!busNV.IsCheckCCCD(ma,cccd)) {
					thongbao.thongbao("Căn Cước công dân đã tồn tại", "Chú ý");
				}
				else {
					NhanVien nVien = new NhanVien(ma, ten, sdt, email, diaChi,
						cccd, gtBoolean, ngay, imageName);
					daoNV.sua(nVien);
					busNV.dodulieu(modelNV, daoNV.layNV());
				}
				
			}
		});
		
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSua.setIcon(new ImageIcon("IMG_Hinh\\sua.jpg"));
		btnSua.setBounds(570, 280, 92, 45);
		pnlCenter.add(btnSua);

		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		panel_2.setBounds(40, 80, 167, 222);
		pnlCenter.add(panel_2);
		panel_2.setLayout(null);

		lblAnh = new JLabel("");
		lblAnh.setBounds(0, 0, 167, 222);
		panel_2.add(lblAnh);

		txtMaNV = new JTextField(busNV.updateMa());
		txtMaNV.setBackground(new Color(255, 255, 237));
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMaNV.setColumns(10);
		txtMaNV.setBorder(null);
		txtMaNV.setBounds(400, 90, 200, 30);
		pnlCenter.add(txtMaNV);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(400, 135, 200, 30);
		pnlCenter.add(txtHoTen);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSDT.setColumns(10);
		txtSDT.setBounds(400, 180, 200, 30);
		pnlCenter.add(txtSDT);

		txt_NgayVaoLam = new JTextField();
		txt_NgayVaoLam.setColumns(10);

		pnlCenter.add(txt_NgayVaoLam);
		txtNgayBD = new JDateChooser();
		txtNgayBD.setDateFormatString("dd/MM/yyyy");
		txtNgayBD.setBounds(400, 225, 200, 30);

		pnlCenter.add(txtNgayBD);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtEmail.setColumns(10);
		txtEmail.setBounds(780, 90, 197, 30);
		pnlCenter.add(txtEmail);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(780, 180, 197, 30);
		pnlCenter.add(txtCCCD);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(780, 220, 197, 30);
		pnlCenter.add(txtDiaChi);

		btnLamMoi = new JButton();
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				xoaTrang();
			}
		});
		btnLamMoi.setIcon(new ImageIcon("IMG_Hinh\\reset.jpg"));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLamMoi.setBounds(720, 280, 92, 45);
		pnlCenter.add(btnLamMoi);

		radNam = new JRadioButton("Nam");
		radNam.setBackground(new Color(255, 255, 237));
		radNam.setSelected(true);
		radNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		radNam.setBounds(780, 135, 66, 30);

		pnlCenter.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setBackground(new Color(255, 255, 237));
		radNu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		radNu.setBounds(870, 135, 66, 30);
		pnlCenter.add(radNu);

		buttonGroup.add(radNam);
		buttonGroup.add(radNu);
		// =======================================================

		txtMaNV.setEditable(false);

		btnThem.setFocusPainted(false);
		btnSua.setFocusPainted(false);
		btnLamMoi.setFocusPainted(false);

		JLabel lblNewLabel = new JLabel("TÀI KHOẢN ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(1281, 30, 122, 30);
		pnlCenter.add(lblNewLabel);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBackground(new Color(255, 255, 237));
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTaiKhoan.setEditable(false);
		txtTaiKhoan.setBorder(null);
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBounds(1250, 90, 167, 30);
		pnlCenter.add(txtTaiKhoan);

		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTaiKhoan.setBounds(1080, 90, 114, 30);
		pnlCenter.add(lblTaiKhoan);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMatKhau.setBounds(1080, 180, 114, 30);
		pnlCenter.add(lblMatKhau);

		JLabel lblT = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblT.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblT.setBounds(521, 30, 250, 30);
		pnlCenter.add(lblT);

		txtmatKhau = new JTextField();
		txtmatKhau.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtmatKhau.setBounds(1250, 180, 167, 30);
		pnlCenter.add(txtmatKhau);

		JButton btnThemTK = new JButton("Thêm ");
		btnThemTK.setBackground(new Color(0, 204, 51));
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
					TaiKhoan tk = new TaiKhoan(tentk, ma2, ghiChu, a, vt,
							LocalDate.now());
					if (daotk.themTaiKhoan(tk)) {
						if (daoNV.layNV() != null)
							busNV.dodulieu(modelNV, daoNV.layNV());
					}
					;

				}
			}
		});
		btnThemTK.setBounds(1238, 290, 85, 30);
		pnlCenter.add(btnThemTK);

		btnxoaTK = new JButton("Xóa");
		btnxoaTK.setBackground(new Color(255, 0, 0));
		btnxoaTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ma = txtMaNV.getText().trim();
				if (daotk.xoaTaiKhoan(ma)) {
					busNV.dodulieu(modelNV, daoNV.layNV());
				}
			}
		});
		btnxoaTK.setBounds(1344, 290, 85, 30);
		pnlCenter.add(btnxoaTK);

		JLabel lblMatKhauLan2 = new JLabel("Nhập lại mật khẩu:");
		lblMatKhauLan2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMatKhauLan2.setBounds(1080, 220, 165, 30);
		pnlCenter.add(lblMatKhauLan2);

		btnThemTK.setFocusPainted(false);
		btnxoaTK.setFocusPainted(false);
		
		txtMK2 = new JTextField();
		txtMK2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMK2.setBounds(1250, 220, 167, 30);
		pnlCenter.add(txtMK2);
		txtGhiChu.setBackground(new Color(237, 255, 241));
		txtGhiChu.setBounds(1467, 132, 167, 127);
		pnlCenter.add(txtGhiChu);

		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblChucVu.setBounds(1080, 135, 80, 30);
		pnlCenter.add(lblChucVu);

		radNhanVien = new JRadioButton("Nhân Viên ");
		radNhanVien.setBackground(new Color(255, 255, 237));
		radNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		radNhanVien.setBounds(1250, 135, 107, 30);
		radNhanVien.setSelected(true);
		pnlCenter.add(radNhanVien);

		radQuanLi = new JRadioButton("Quản lý");
		radQuanLi.setBackground(new Color(255, 255, 237));
		radQuanLi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		radQuanLi.setBounds(1356, 135, 90, 30);
		pnlCenter.add(radQuanLi);
		ButtonGroup groupChucVu = new ButtonGroup();
		groupChucVu.add(radNhanVien);
		groupChucVu.add(radQuanLi);

		lblThongBao = new JLabel();
		lblThongBao.setForeground(new Color(255, 0, 0));
		lblThongBao.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblThongBao.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongBao.setBounds(1281, 60, 137, 25);
		pnlCenter.add(lblThongBao);

		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGhiChu.setBounds(1468, 90, 114, 30);
		pnlCenter.add(lblGhiChu);
		JPanel pnlBot = new JPanel();
		pnlBot.setBackground(new Color(255, 255, 237));
		pnlBot.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnlBot.setBounds(0, 426, 1646, 550);
		add(pnlBot);

		String rowNV[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Giới tính",
				"Email", "Ngày vào làm", "Địa chỉ", "Số điện thoại", "CCCD",
				"Tài khoản", "Ghi chú" };
		modelNV = new DefaultTableModel(rowNV, 0);
		pnlBot.setLayout(null);
		tableNV = new JTable(modelNV);
		tableNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tableNV.setSelectionBackground(new java.awt.Color(255, 204, 102));

		tableNV.setRowHeight(30);

		JTableHeader tableHeader = tableNV.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground((Color.decode("#00c691")));
		tableNV.setFont(new Font("Tohoma", Font.PLAIN, 18));

		tableNV.getColumnModel().getColumn(4).setPreferredWidth(230);
		tableNV.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableNV.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableNV.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableNV.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableNV.getColumnModel().getColumn(2).setPreferredWidth(180);
		tableNV.getColumnModel().getColumn(10).setPreferredWidth(0);
		tableNV.getColumnModel().getColumn(9).setPreferredWidth(50);
		
		AbstractDocument document = (AbstractDocument) txtSDT.getDocument();
		document.setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int length,
					String text, AttributeSet attrs)
					throws BadLocationException {
				if (isNumeric(text)) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});
		AbstractDocument document2 = (AbstractDocument) txtHoTen.getDocument();
		document2.setDocumentFilter(new DocumentFilter() {
			@Override
			 public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	                    throws BadLocationException {
	                if (IsChar(text)) {
	                    super.replace(fb, offset, length, text, attrs);
	                }
	            }
		});
		
		tableNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				xoaTrang();

				int row = tableNV.getSelectedRow();
				String maNV = tableNV.getValueAt(row, 1).toString().trim();
				txtMaNV.setText(maNV);
				txtHoTen.setText(tableNV.getValueAt(row, 2).toString().trim());
				if (tableNV.getValueAt(row, 3).toString().trim() == "Nữ") {
					radNam.setSelected(false);
					radNu.setSelected(true);
				} else {
					radNam.setSelected(true);
					radNu.setSelected(false);
				}

				txtEmail.setText(tableNV.getValueAt(row, 4).toString().trim());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				try {
					txtNgayBD.setDate(dateFormat.parse(tableNV
							.getValueAt(row, 5).toString().trim()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txtDiaChi.setText(tableNV.getValueAt(row, 6).toString().trim());
				txtSDT.setText(tableNV.getValueAt(row, 7).toString().trim());
				txtCCCD.setText(tableNV.getValueAt(row, 8).toString().trim());
				lbl_ImageNV(dao.DAO_NhanVien.layNVTheoMa(maNV).getHinhAnh());
				
				TaiKhoan tk = daotk.getTKtheoMa(tableNV.getValueAt(row, 1)
						.toString().trim());

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
	
		tableNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneNV = new JScrollPane(tableNV);
		scrollPaneNV.setBounds(new Rectangle(0, 0, 135, 251));
		scrollPaneNV.setBounds(10, 10, 1626, 520);
		pnlBot.add(scrollPaneNV);
		// đọc dữ liệu
		ResultSet rs = daoNV.layNV();
		busNV.dodulieu(modelNV, daoNV.layNV());

		cboGioiTinh.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String tukhoa= txtTimKiem.getText().trim();
					String sql = getDieuKienLoc(tukhoa);
					busNV.dodulieu(modelNV, connectionManager.getdata(sql));
				}
			}
		});
		cboTK.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String tukhoa= txtTimKiem.getText().trim();
					String sql = getDieuKienLoc(tukhoa);
					busNV.dodulieu(modelNV, connectionManager.getdata(sql));
				}
			}
		});
		txtLocNgayVL.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if ("date".equals(evt.getPropertyName())) {
							String tukhoa= txtTimKiem.getText().trim();
							String sql = getDieuKienLoc(tukhoa);
							System.err.println(sql);
							busNV.dodulieu(modelNV, connectionManager.getdata(sql));
						}
					}
				});
		lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblAnhMouseClicked(evt);
			}
		});

	}

	File f;

	private void lblAnhMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		String path = "IMG_NhanVien";
		JFileChooser file = new JFileChooser(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"*.Images", "jpg", "gif", "png");
		file.addChoosableFileFilter(filter);
		int resuft = file.showOpenDialog(null);
		if (resuft == JFileChooser.APPROVE_OPTION) {
			f = file.getSelectedFile();
			imageName = f.getAbsolutePath();
			ImageIcon ii = new ImageIcon(new ImageIcon(imageName).getImage()
					.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(),
							Image.SCALE_SMOOTH));
			lblAnh.setIcon(ii);
			String anh = imageName.substring(imageName.lastIndexOf("NV"));	
			lblAnh.setText(anh);
			imageName = f.getName(); // LẤY TÊN FILE
			// imageName = f.getAbsolutePath(); // LẤY ĐƯỜNG DẪN
		}
	}

	public void lbl_ImageNV(String ImagePath) {
		ImageIcon icon = new ImageIcon("IMG_NhanVien\\" + ImagePath);
		// ImageIcon icon = new ImageIcon(ImagePath);
		Image image = icon.getImage();
		ImageIcon icon1 = new ImageIcon(image.getScaledInstance(
				lblAnh.getWidth(), lblAnh.getHeight(), image.SCALE_SMOOTH));
		lblAnh.setIcon(icon1);
		
		if(!ImagePath.equals("null"))
			lblAnh.setText(ImagePath);
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
		lbl_ImageNV("");
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
		imageName= lblAnh.getText();
		 
	}

	public String getDieuKienLoc(String tukhoa) {
		LocalDate ngaybd1 = busNV.chuyenDoiKieuNgay(txtLocNgayVL);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dkngay= formatter.format(ngaybd1);
		String tong = "select * from   NhanVien  left join TaiKhoan  on NhanVien.maNhanVien= TaiKhoan.maNhanVien where ngayVaoLam < '"+dkngay+"' and(  NhanVien.maNhanVien like N'%"
				+ tukhoa
				+ "%' or NhanVien.tenNhanVien like N'%"
				+ tukhoa
				+ "%' or NhanVien.cccd like N'%"
				+ tukhoa
				+ "%' or NhanVien.sdt like N'%" + tukhoa + "%')";

		if (cboTK.getSelectedItem().toString()
				.equalsIgnoreCase("Không có tài khoản")) {
			tong += "and TaiKhoan.tenTaiKhoan is null ";
		} else if (cboTK.getSelectedItem().toString()
				.equalsIgnoreCase("Có tài khoản")) {
			tong += " and TaiKhoan.tenTaiKhoan is not null ";
		}

		if (cboGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam")) {
			tong += "and NhanVien.gioiTinh = 1 ";
		} else if (cboGioiTinh.getSelectedItem().toString()
				.equalsIgnoreCase("Nữ")) {
			tong += " and NhanVien.gioiTinh = 0 ";
		}

		ngay = busNV.chuyenDoiKieuNgay(txtLocNgayVL);
		return tong;
	}
		
	
	private static boolean isNumeric(String text) {
		return text.matches("^[0-9]*$");
	}
	private static boolean IsChar(String text) {
		 return text.matches("[\\p{L} ]*");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
