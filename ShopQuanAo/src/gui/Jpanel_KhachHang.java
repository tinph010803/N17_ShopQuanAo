package gui;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JSplitPane;

import connectDB.ConnectionManager;
import entity.KhachHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jpanel_KhachHang extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JPanel panel;
	private JComponent pnTieuDe;
	private JLabel lblLocGioiTinh;
	private JLabel lblTimKiem;
	private JComponent lblLoc;
	private JLabel lblBc;
	private JComboBox cboBac;
	private JPanel pnlCenter;
	private JPanel pnlCenterLeft;
	private JScrollPane scrollPane;
	private JPanel pnlCenterRight;
	private JLabel lblTenKH;
	private JLabel lblEmail;
	private JLabel lblGioiTinh;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private JLabel lblSDT;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JTextField txtSTDM;
	ConnectionManager connectionManager = new ConnectionManager();
	public static KhachHang khachHang = new KhachHang();
	private ButtonGroup groupGT;
	private ButtonGroup groupLoc;
	private JComboBox cboGioiTinh;
	private JLabel lblMaKH;
	private JLabel lblSTDM;

	/**
	 * Create the panel.
	 */
	public Jpanel_KhachHang() {
		setBounds(new Rectangle(0, 0, 1646, 975));

		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBounds(0, 0, 1646, 975);
		add(panel);
		panel.setLayout(null);

		pnTieuDe = new JPanel();
		pnTieuDe.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0,
				0)));
		pnTieuDe.setBackground(new Color(255, 255, 237));
		pnTieuDe.setBounds(new Rectangle(0, 0, 1422, 30));
		pnTieuDe.setBounds(0, 0, 1646, 112);
		panel.add(pnTieuDe);
		pnTieuDe.setLayout(null);
		lblLocGioiTinh = new JLabel("Giới tính :");
		lblLocGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLocGioiTinh.setBounds(70, 55, 100, 35);
		pnTieuDe.add(lblLocGioiTinh);

		groupGT = new ButtonGroup();

		lblTimKiem = new JLabel("Tìm kiếm  : ");
		lblTimKiem.setIcon(new ImageIcon(
				"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\search.png"));
		lblTimKiem.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTimKiem.setBounds(885, 50, 130, 35);
		pnTieuDe.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setSelectionColor(new Color(255, 128, 0));
		txtTimKiem.setForeground(Color.BLACK);
		txtTimKiem.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtTimKiem.setColumns(10);
		txtTimKiem.setCaretColor(Color.pink);
		txtTimKiem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0,
				0, 0)));
		txtTimKiem.setBackground(new Color(255, 255, 237));
		txtTimKiem.setBounds(1024, 50, 220, 25);
		pnTieuDe.add(txtTimKiem);

		lblLoc = new JLabel("Lọc ");
		lblLoc.setForeground(new Color(255, 153, 51));
		lblLoc.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLoc.setBounds(333, 10, 65, 35);
		pnTieuDe.add(lblLoc);

		lblBc = new JLabel("Bậc : ");
		lblBc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBc.setBounds(370, 55, 60, 35);
		pnTieuDe.add(lblBc);

		cboBac = new JComboBox();
		cboBac.setBackground(new Color(240, 255, 241));
		cboBac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboBac.setModel(new DefaultComboBoxModel(new String[] { "Bậc", "Bạc",
				"Vàng", "Kim cương" }));
		cboBac.setBounds(445, 55, 145, 35);
		pnTieuDe.add(cboBac);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setBackground(new Color(240, 255, 241));
		cboGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"Nam", "Nữ" }));
		cboGioiTinh.setBounds(193, 55, 145, 35);
		pnTieuDe.add(cboGioiTinh);

		pnlCenter = new JPanel();
		pnlCenter.setBounds(0, 112, 1646, 863);
		panel.add(pnlCenter);
		pnlCenter.setLayout(null);

		pnlCenterLeft = new JPanel();
		pnlCenterLeft.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(
				0, 0, 0)));
		pnlCenterLeft.setBackground(new Color(255, 255, 237));
		pnlCenterLeft.setBounds(0, 0, 1026, 863);
		pnlCenter.add(pnlCenterLeft);

		String row[] = { "Mã khách hàng", "Tên khách hàng", "Email",
				"Số điện thoại" };
		model = new DefaultTableModel(row, 0);
		pnlCenterLeft.setLayout(null);
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		table.setSelectionBackground(new java.awt.Color(255, 204, 102));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(new Rectangle(0, 0, 135, 251));
		scrollPane.setBounds(45, 30, 912, 817);
		pnlCenterLeft.add(scrollPane);
		bus.BUS_KhachHang.dodulieu(table);

		table.setRowHeight(30);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground((Color.decode("#00c691")));
		table.setFont(new Font("Tohoma", Font.PLAIN, 18));

		// Chỉnh chiều rộng
		table.getColumnModel().getColumn(3).setPreferredWidth(70);

		table.getColumnModel().getColumn(1).setPreferredWidth(140);

		table.getColumnModel().getColumn(2).setPreferredWidth(190);

		// set chữ trong cot ở giữa
		// DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		// renderer.setHorizontalAlignment(SwingConstants.CENTER);
		// renderer.setFont(new Font("Arial", Font.BOLD, 50));
		// table.setDefaultRenderer(Object.class, renderer);

		pnlCenterRight = new JPanel();
		pnlCenterRight.setBackground(new Color(255, 255, 178));
		pnlCenterRight.setBounds(1025, 0, 621, 863);
		pnlCenter.add(pnlCenterRight);
		pnlCenterRight.setLayout(null);

		txtMaKH = new JTextField();
		txtMaKH.setSelectionColor(new Color(255, 128, 0));
		txtMaKH.setForeground(Color.BLACK);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaKH.setColumns(10);
		txtMaKH.setCaretColor(Color.RED);
		txtMaKH.setBorder(null);
		txtMaKH.setBackground(new Color(255, 255, 178));
		txtMaKH.setBounds(300, 60, 250, 35);
		pnlCenterRight.add(txtMaKH);

		lblTenKH = new JLabel("Tên khách hàng : ");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenKH.setBounds(80, 130, 165, 35);
		pnlCenterRight.add(lblTenKH);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(80, 270, 165, 35);
		pnlCenterRight.add(lblEmail);

		txtTenKH = new JTextField();
		txtTenKH.setSelectionColor(new Color(255, 128, 0));
		txtTenKH.setForeground(Color.BLACK);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTenKH.setColumns(10);
		txtTenKH.setCaretColor(Color.RED);
		txtTenKH.setBackground(new Color(240, 246, 251));
		txtTenKH.setBounds(300, 130, 250, 35);
		pnlCenterRight.add(txtTenKH);
		
		AbstractDocument document2 = (AbstractDocument) txtTenKH.getDocument();
		document2.setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int length,
					String text, AttributeSet attrs)
					throws BadLocationException {
				if (IsChart(text)) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});

		lblGioiTinh = new JLabel("Giới Tính : ");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGioiTinh.setBounds(80, 200, 165, 35);
		pnlCenterRight.add(lblGioiTinh);

		radNam = new JRadioButton("Nam");
		radNam.setBackground(new Color(255, 255, 178));
		radNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radNam.setSelected(true);
		radNam.setBounds(300, 200, 80, 35);
		pnlCenterRight.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setBackground(new Color(255, 255, 178));
		radNu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radNu.setBounds(420, 200, 80, 35);
		pnlCenterRight.add(radNu);

		groupLoc = new ButtonGroup();
		groupLoc.add(radNam);
		groupLoc.add(radNu);

		lblSDT = new JLabel("Số điện thoại : ");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSDT.setBounds(80, 340, 165, 35);
		pnlCenterRight.add(lblSDT);

		txtEmail = new JTextField();
		txtEmail.setSelectionColor(new Color(255, 128, 0));
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setCaretColor(Color.RED);
		txtEmail.setBackground(new Color(240, 246, 251));
		txtEmail.setBounds(300, 270, 250, 35);
		pnlCenterRight.add(txtEmail);

		txtSDT = new JTextField();
		txtSDT.setSelectionColor(new Color(255, 128, 0));
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSDT.setColumns(10);
		txtSDT.setCaretColor(Color.RED);
		txtSDT.setBackground(new Color(240, 246, 251));
		txtSDT.setBounds(300, 340, 250, 35);
		pnlCenterRight.add(txtSDT);

		// Tạo một DocumentFilter để chỉ cho phép số
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

		btnThem = new JButton("");
		btnThem.setBackground(new Color(51, 255, 255));

		btnThem.setIcon(new ImageIcon("IMG_Hinh\\them.jpg"));
		btnThem.setBackground(new Color(77, 176, 61));
		btnThem.setBounds(90, 560, 92, 45);
		pnlCenterRight.add(btnThem);

		btnSua = new JButton("");
		btnSua.setIcon(new ImageIcon("IMG_Hinh\\sua.jpg"));
		btnSua.setBackground(new Color(242, 223, 48));
		btnSua.setBackground(new Color(255, 128, 64));
		btnSua.setBounds(254, 559, 92, 45);
		pnlCenterRight.add(btnSua);

		btnLamMoi = new JButton("");
		btnLamMoi.setIcon(new ImageIcon("IMG_Hinh\\reset.jpg"));
		btnLamMoi.setBackground(new Color(0, 191, 255));
		btnLamMoi.setBackground(new Color(0, 0, 255));
		btnLamMoi.setBounds(424, 559, 92, 45);
		pnlCenterRight.add(btnLamMoi);

		lblMaKH = new JLabel("Mã khách hàng : ");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaKH.setBounds(80, 60, 165, 35);
		pnlCenterRight.add(lblMaKH);

		lblSTDM = new JLabel("Số tiền đã mua:");
		lblSTDM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSTDM.setBounds(80, 410, 165, 35);
		pnlCenterRight.add(lblSTDM);

		txtSTDM = new JTextField();
		txtSTDM.setSelectionColor(new Color(255, 128, 0));
		txtSTDM.setBorder(null);
		txtSTDM.setForeground(Color.BLACK);
		txtSTDM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSTDM.setColumns(10);
		txtSTDM.setCaretColor(Color.RED);
		txtSTDM.setBackground(new Color(255, 255, 178));
		txtSTDM.setBounds(300, 410, 250, 35);
		pnlCenterRight.add(txtSTDM);

		ganTXTMaKH();

		txtMaKH.setEditable(false);
		txtSTDM.setEditable(false);
		btnThem.setFocusPainted(false);
		btnSua.setFocusPainted(false);
		btnLamMoi.setFocusPainted(false);

		// =========================================================================================

		btnThem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemActionPerformed(evt);
			}
		});

		btnSua.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSuaActionPerformed(evt);
			}
		});

		btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLamMoiActionPerformed(evt);
			}
		});

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableMouseClicked(evt);
			}
		});

		txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtTimKiemKeyReleased(evt);
			}
		});

		cboBac.addActionListener(this);
		cboGioiTinh.addActionListener(this);

	}

	private static boolean isNumeric(String text) {
		return text.matches("^[0-9]*$");
	}
	private static boolean IsChart(String text) {
		return text.matches("[\\p{L} ]*");
	}

	private void ganTXTMaKH() {
		ResultSet rs = dao.DAO_KhachHang.layKH();
		String currentDate = getCurrentDate(); // Ngày hiện tại
		String maxMaKH = "KH" + currentDate + "0000";
		try {
			while (rs.next()) {
				maxMaKH = rs.getString("maKhachHang").trim();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String newMaKH = "";
		if (maxMaKH.startsWith("KH" + currentDate)) {
			int maxSoKH = extractSerialNumber(maxMaKH);
			newMaKH = "KH" + currentDate + String.format("%04d", maxSoKH + 1);
		} else {
			newMaKH = "KH" + currentDate + "0001";
		}
		txtMaKH.setText(newMaKH);
	}

	private void txtTimKiemKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		String bac = cboBac.getSelectedItem().toString();
		String gioiTinh = cboGioiTinh.getSelectedItem().toString();
		String tuKhoa = txtTimKiem.getText().trim();
		bus.BUS_KhachHang.timKiem(table, tuKhoa, bac, gioiTinh);
	}

	private void btnLamMoiActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		txtMaKH.setText("");
		txtTenKH.setText("");
		radNam.setSelected(true);
		txtEmail.setText("");
		txtSDT.setText("");
		txtSTDM.setText("");
		txtTenKH.requestFocus();
		table.clearSelection();
		ganTXTMaKH();
	}

	private void btnSuaActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		ganThongTin();
		if (bus.BUS_KhachHang.kt_Sua(khachHang)) {
			dao.DAO_KhachHang.suaKH(khachHang);
			bus.BUS_KhachHang.dodulieu(table);

		}
	}

	public static int vitri;
	public static String maKH;

	private void tableMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (table.getSelectedRowCount() == 1) {
			vitri = table.getSelectedRow();
			maKH = table.getValueAt(vitri, 0).toString();
			thongTinChiTiet(maKH);

		}

	}

	private void thongTinChiTiet(String maKH) {
		ResultSet rs = dao.DAO_KhachHang.layKHTheoMa(maKH);
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		try {
			if (rs.next()) {
				txtMaKH.setText(rs.getString("maKhachHang"));
				txtTenKH.setText(rs.getString("tenKhachHang"));
				if (rs.getBoolean("gioiTinh") == true) {
					radNam.setSelected(true);
				} else {
					radNu.setSelected(true);
				}
				txtEmail.setText(rs.getString("email"));
				txtSDT.setText(rs.getString("sdt"));

				txtSTDM.setText(decimalFormat.format(rs
						.getDouble("soTienDaMua")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}

	// Hàm trích xuất số thứ tự từ mã khách hàng
	private static int extractSerialNumber(String customerID) {
		String serialPart = customerID.substring(10).trim(); // Bỏ "KH" + ngày
																// hiện tại
		return Integer.parseInt(serialPart);
	}

	private void ganThongTin() {
		khachHang.setMaKhachHang(txtMaKH.getText().trim());
		khachHang.setTenKhachHang(txtTenKH.getText().trim());
		if (radNam.isSelected())
			khachHang.setGioiTinh(true);
		else
			khachHang.setGioiTinh(false);
		khachHang.setEmail(txtEmail.getText().trim());
		khachHang.setSdt(txtSDT.getText().trim());
	}

	private void btnThemActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		ganThongTin();
		boolean kt = bus.BUS_KhachHang.kt_Them(khachHang);
		if (kt) {
			dao.DAO_KhachHang.them(khachHang);
			bus.BUS_KhachHang.dodulieu(table);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// Tìm kiếm
		// TODO Auto-generated method stub
		String bac = cboBac.getSelectedItem().toString();
		String gioiTinh = cboGioiTinh.getSelectedItem().toString();
		String tuKhoa = txtTimKiem.getText().trim();
		Object o = e.getSource();
		if (o.equals(cboBac)) {
			bus.BUS_KhachHang.timKiem(table, tuKhoa, bac, gioiTinh);
		}
		if (o.equals(cboGioiTinh)) {
			bus.BUS_KhachHang.timKiem(table, tuKhoa, bac, gioiTinh);
		}
	}
}
