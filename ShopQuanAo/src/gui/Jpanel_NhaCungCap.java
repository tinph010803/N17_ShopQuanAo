package gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bus.BUS_NhaCungCap;
import connectDB.ConnectionManager;
import dao.DAO_NhaCungCap;
import entity.NhaCungCap;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;

import java.awt.FlowLayout;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JSplitPane;

import java.util.Date;
import java.util.List;

public class Jpanel_NhaCungCap extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private DefaultTableModel modelNCC;
	private JTable tableNCC;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JPanel panel_NhaCungCap;
	private JComponent pnTieuDe;
	private JLabel lblTimKiem;
	private JComponent lblNCC;
	private JPanel pnlCenter;
	private JPanel pnlCenterLeft;
	private JScrollPane scrollPaneNCC;
	private JPanel pnlCenterRight;
	private JLabel lblTenNCC;
	private JLabel lblEmail;
	private JLabel lblSDT;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JTextField txtDiaChi;
	ConnectionManager connectionManager = new ConnectionManager();
	private JLabel lblDiaChi;
	private BUS_NhaCungCap busncc = new BUS_NhaCungCap();
	private DAO_NhaCungCap daoncc = new DAO_NhaCungCap();

	/**
	 * Create the panel.
	 */
	public Jpanel_NhaCungCap() {
		setBounds(new Rectangle(0, 0, 1646, 975));

		setLayout(null);
		panel_NhaCungCap = new JPanel();
		panel_NhaCungCap.setBackground(new Color(255, 255, 0));
		panel_NhaCungCap.setBounds(0, 0, 1646, 975);
		add(panel_NhaCungCap);
		panel_NhaCungCap.setLayout(null);

		pnTieuDe = new JPanel();
		pnTieuDe.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0,
				0)));
		pnTieuDe.setBackground(new Color(255, 255, 237));
		pnTieuDe.setBounds(new Rectangle(0, 0, 1422, 30));
		pnTieuDe.setBounds(0, 0, 1646, 112);
		panel_NhaCungCap.add(pnTieuDe);
		pnTieuDe.setLayout(null);

		lblTimKiem = new JLabel("Tìm kiếm  : ");
		lblTimKiem.setIcon(new ImageIcon(
				"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\search.png"));
		lblTimKiem.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTimKiem.setBounds(33, 48, 134, 26);
		pnTieuDe.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setSelectionColor(new Color(255, 128, 0));
		txtTimKiem.setForeground(Color.BLACK);
		txtTimKiem.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtTimKiem.setColumns(10);
		txtTimKiem.setCaretColor(Color.RED);
		txtTimKiem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0,
				0, 0)));
		txtTimKiem.setBackground(new Color(255, 255, 237));
		txtTimKiem.setBounds(177, 50, 208, 23);
		pnTieuDe.add(txtTimKiem);
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tukhoa = txtTimKiem.getText();
				ResultSet rs = daoncc.timKiem(tukhoa);
				busncc.dodulieu(modelNCC, rs);

			}
		});

		lblNCC = new JLabel("Nhà cung cấp");
		lblNCC.setForeground(new Color(255, 153, 51));
		lblNCC.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNCC.setBounds(730, 10, 299, 50);
		pnTieuDe.add(lblNCC);

		pnlCenter = new JPanel();
		pnlCenter.setBounds(0, 112, 1646, 863);
		panel_NhaCungCap.add(pnlCenter);
		pnlCenter.setLayout(null);

		pnlCenterLeft = new JPanel();
		pnlCenterLeft.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(
				0, 0, 0)));
		pnlCenterLeft.setBackground(new Color(255, 255, 237));
		pnlCenterLeft.setBounds(0, 0, 1026, 863);
		pnlCenter.add(pnlCenterLeft);

		String rowNCC[] = { "STT", "Mã nhà cung cấp", "Tên nhà cung cấp",
				"Địa chỉ", "Email", "Số điện thoại" };
		modelNCC = new DefaultTableModel(rowNCC, 0);
		pnlCenterLeft.setLayout(null);
		tableNCC = new JTable(modelNCC);
		tableNCC.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableNCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tableNCC.setSelectionBackground(new java.awt.Color(255, 204, 102));
		scrollPaneNCC = new JScrollPane(tableNCC);
		scrollPaneNCC.setBounds(new Rectangle(0, 0, 135, 251));
		scrollPaneNCC.setBounds(42, 20, 912, 817);
		pnlCenterLeft.add(scrollPaneNCC);

		tableNCC.setRowHeight(30);
		JTableHeader tableHeader = tableNCC.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground((Color.decode("#00c691")));
		tableNCC.setFont(new Font("Tohoma", Font.PLAIN, 18));

		tableNCC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tableNCC.getSelectedRow();
				txtMaNCC.setText(tableNCC.getValueAt(row, 1).toString().trim());
				txtTenNCC
						.setText(tableNCC.getValueAt(row, 2).toString().trim());
				txtDiaChi
						.setText(tableNCC.getValueAt(row, 3).toString().trim());
				txtEmail.setText(tableNCC.getValueAt(row, 4).toString().trim());
				txtSDT.setText(tableNCC.getValueAt(row, 5).toString().trim());

			}
		});

		pnlCenterRight = new JPanel();
		pnlCenterRight.setBackground(new Color(255, 255, 178));
		pnlCenterRight.setBounds(1025, 0, 621, 863);
		pnlCenter.add(pnlCenterRight);
		pnlCenterRight.setLayout(null);

		txtMaNCC = new JTextField(bus.BUS_NhaCungCap.updateMa());

		txtMaNCC.setEditable(false);
		txtMaNCC.setSelectionColor(new Color(255, 128, 0));
		txtMaNCC.setForeground(Color.BLACK);
		txtMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaNCC.setColumns(10);
		txtMaNCC.setCaretColor(Color.RED);
		txtMaNCC.setBorder(null);
		txtMaNCC.setBackground(new Color(255, 255, 178));
		txtMaNCC.setBounds(310, 60, 250, 35);
		pnlCenterRight.add(txtMaNCC);

		lblTenNCC = new JLabel("Tên NCC : ");
		lblTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenNCC.setBounds(80, 130, 175, 35);
		pnlCenterRight.add(lblTenNCC);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(80, 270, 175, 35);
		pnlCenterRight.add(lblEmail);

		txtTenNCC = new JTextField();
		txtTenNCC.setSelectionColor(new Color(255, 128, 0));
		txtTenNCC.setForeground(Color.BLACK);
		txtTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTenNCC.setColumns(10);
		txtTenNCC.setCaretColor(Color.RED);
		txtTenNCC.setBackground(new Color(240, 246, 251));
		txtTenNCC.setBounds(310, 130, 250, 35);
		pnlCenterRight.add(txtTenNCC);

		lblSDT = new JLabel("Số điện thoại : ");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSDT.setBounds(80, 340, 175, 35);
		pnlCenterRight.add(lblSDT);

		txtEmail = new JTextField();
		txtEmail.setSelectionColor(new Color(255, 128, 0));
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setCaretColor(Color.RED);
		txtEmail.setBackground(new Color(240, 246, 251));
		txtEmail.setBounds(310, 270, 250, 35);
		pnlCenterRight.add(txtEmail);

		txtSDT = new JTextField();
		txtSDT.setSelectionColor(new Color(255, 128, 0));
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSDT.setColumns(10);
		txtSDT.setCaretColor(Color.RED);
		txtSDT.setBackground(new Color(240, 246, 251));
		txtSDT.setBounds(310, 340, 250, 35);
		pnlCenterRight.add(txtSDT);

		btnThem = new JButton();
		btnThem.setBackground(new Color(51, 255, 255));
		btnThem.setIcon(new ImageIcon("IMG_Hinh\\them.jpg"));
		btnThem.setBounds(120, 500, 92, 45);
		pnlCenterRight.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ma = txtMaNCC.getText();
				String ten = txtTenNCC.getText();
				String sdt = txtSDT.getText();
				String email = txtEmail.getText();
				String diachi = txtDiaChi.getText();
				if (busncc.kiemtra_themNCC(ma, ten, sdt, email, diachi) == true) {

					NhaCungCap a = new NhaCungCap(ma, ten, diachi, sdt, email);
					busncc.DeleteDataTable(modelNCC);

					if (daoncc.them_ncc(a)) {
						busncc.dodulieu(modelNCC, daoncc.layNCC());

					}

				}
			}

		});

		btnSua = new JButton();
		btnSua.setIcon(new ImageIcon("IMG_Hinh\\sua.jpg"));
		btnSua.setBackground(new Color(255, 128, 64));
		btnSua.setBounds(280, 500, 92, 45);
		pnlCenterRight.add(btnSua);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ma = txtMaNCC.getText();
				String ten = txtTenNCC.getText();
				String sdt = txtSDT.getText();
				String email = txtEmail.getText();
				String diachi = txtDiaChi.getText();
				NhaCungCap a = daoncc.layNCCTheoMa(ma.trim());
				if (a == null) {
					thongbao.thongbao("sai", "");
				}
				if (busncc.kiemtra_update(ten, sdt, email, diachi)) {
					a.setDiaChi(diachi);
					a.setEmail(email);
					a.setSdt(sdt);
					a.setTenNhaCungCap(ten);
					if (daoncc.sua_ncc(a)) {
						busncc.dodulieu(modelNCC, daoncc.layNCC());
					}
				}

			}

		});
		btnLamMoi = new JButton();
		btnLamMoi.setIcon(new ImageIcon("IMG_Hinh\\reset.jpg"));
		btnLamMoi.setBounds(440, 500, 92, 45);
		pnlCenterRight.add(btnLamMoi);

		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaNCC.setText(bus.BUS_NhaCungCap.updateMa());
				txtTenNCC.setText("");
				txtTimKiem.requestFocus();
				txtDiaChi.setText("");
				txtEmail.setText("");
				txtSDT.setText("");
				tableNCC.clearSelection();
			}
		});

		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp : ");
		lblMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaNCC.setBounds(80, 60, 175, 35);
		pnlCenterRight.add(lblMaNCC);

		lblDiaChi = new JLabel("Địa chỉ :");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiaChi.setBounds(80, 200, 175, 35);
		pnlCenterRight.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setSelectionColor(new Color(255, 128, 0));
		txtDiaChi.setForeground(Color.BLACK);
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDiaChi.setColumns(10);
		txtDiaChi.setCaretColor(Color.RED);
		txtDiaChi.setBackground(new Color(240, 246, 251));
		txtDiaChi.setBounds(310, 200, 250, 35);
		pnlCenterRight.add(txtDiaChi);

		btnThem.setFocusPainted(false);
		btnSua.setFocusPainted(false);
		btnLamMoi.setFocusPainted(false);
		BUS_NhaCungCap a = new BUS_NhaCungCap();
		a.dodulieu(modelNCC, daoncc.layNCC());
	}
}
