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
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JPanel panel;
	private JComponent pnTieuDe;
	private JLabel lblTimKiem;
	private JComponent lblNCC;
	private JPanel pnCenter;
	private JPanel pnCenterLeft;
	private JScrollPane scrollPane;
	private JPanel pnCenterRight;
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
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBounds(0, 0, 1646, 975);
		add(panel);
		panel.setLayout(null);

		pnTieuDe = new JPanel();
		pnTieuDe.setBackground(new Color(240, 248, 255));
		pnTieuDe.setBounds(new Rectangle(0, 0, 1422, 30));
		pnTieuDe.setBounds(0, 0, 1646, 112);
		panel.add(pnTieuDe);
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
		txtTimKiem.setBackground(new Color(240, 246, 251));
		txtTimKiem.setBounds(177, 50, 208, 23);
		pnTieuDe.add(txtTimKiem);
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tukhoa = txtTimKiem.getText();
				ResultSet rs = daoncc.timKiem(tukhoa);
				System.err.println("kq tu khoa : " + rs);
				busncc.dodulieu(model, rs);

			}
		});

		lblNCC = new JLabel("Nhà cung cấp");
		lblNCC.setForeground(new Color(255, 153, 51));
		lblNCC.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNCC.setBounds(820, 10, 299, 50);
		pnTieuDe.add(lblNCC);

		pnCenter = new JPanel();
		pnCenter.setBounds(0, 112, 1646, 863);
		panel.add(pnCenter);
		pnCenter.setLayout(null);

		pnCenterLeft = new JPanel();
		pnCenterLeft.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		pnCenterLeft.setBackground(new Color(255, 255, 204));
		pnCenterLeft.setBounds(0, 0, 1026, 863);
		pnCenter.add(pnCenterLeft);

		String row[] = { "STT", "Mã nhà cung cấp", "Tên nhà cung cấp",
				"Địa chỉ", "Email", "Số điện thoại" };
		model = new DefaultTableModel(row, 0);
		pnCenterLeft.setLayout(null);
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		table.setSelectionBackground(new java.awt.Color(255, 204, 102));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(new Rectangle(0, 0, 135, 251));
		scrollPane.setBounds(42, 20, 912, 817);
		pnCenterLeft.add(scrollPane);
		
		
		table.setRowHeight(30);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground((Color.decode("#00c691")));
		table.setFont(new Font("Tohoma", Font.PLAIN, 18));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				txtMaNCC.setText(table.getValueAt(row, 1).toString().trim());
				txtTenNCC.setText(table.getValueAt(row, 2).toString().trim());
				txtDiaChi.setText(table.getValueAt(row, 3).toString().trim());
				txtEmail.setText(table.getValueAt(row, 4).toString().trim());
				txtSDT.setText(table.getValueAt(row, 5).toString().trim());

			}
		});

		pnCenterRight = new JPanel();
		pnCenterRight.setBackground(new Color(255, 255, 153));
		pnCenterRight.setBounds(1025, 0, 621, 863);
		pnCenter.add(pnCenterRight);
		pnCenterRight.setLayout(null);

		txtMaNCC = new JTextField(bus.BUS_NhaCungCap.updateMa());

		txtMaNCC.setEditable(false);
		txtMaNCC.setSelectionColor(new Color(255, 128, 0));
		txtMaNCC.setForeground(Color.BLACK);
		txtMaNCC.setFont(new Font("Arial", Font.ITALIC, 15));
		txtMaNCC.setColumns(10);
		txtMaNCC.setCaretColor(Color.RED);
		txtMaNCC.setBorder(null);
		txtMaNCC.setBackground(new Color(240, 246, 251));
		txtMaNCC.setBounds(317, 60, 211, 23);
		pnCenterRight.add(txtMaNCC);

		lblTenNCC = new JLabel("Tên NCC : ");
		lblTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenNCC.setBounds(81, 130, 154, 23);
		pnCenterRight.add(lblTenNCC);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(81, 270, 154, 23);
		pnCenterRight.add(lblEmail);

		txtTenNCC = new JTextField();
		txtTenNCC.setSelectionColor(new Color(255, 128, 0));
		txtTenNCC.setForeground(Color.BLACK);
		txtTenNCC.setFont(new Font("Arial", Font.ITALIC, 15));
		txtTenNCC.setColumns(10);
		txtTenNCC.setCaretColor(Color.RED);
		txtTenNCC.setBackground(new Color(240, 246, 251));
		txtTenNCC.setBounds(317, 130, 211, 23);
		pnCenterRight.add(txtTenNCC);

		lblSDT = new JLabel("Số điện thoại : ");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDT.setBounds(81, 340, 154, 23);
		pnCenterRight.add(lblSDT);

		txtEmail = new JTextField();
		txtEmail.setSelectionColor(new Color(255, 128, 0));
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Arial", Font.ITALIC, 15));
		txtEmail.setColumns(10);
		txtEmail.setCaretColor(Color.RED);
		txtEmail.setBackground(new Color(240, 246, 251));
		txtEmail.setBounds(317, 270, 211, 23);
		pnCenterRight.add(txtEmail);

		txtSDT = new JTextField();
		txtSDT.setSelectionColor(new Color(255, 128, 0));
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setFont(new Font("Arial", Font.ITALIC, 15));
		txtSDT.setColumns(10);
		txtSDT.setCaretColor(Color.RED);
		txtSDT.setBackground(new Color(240, 246, 251));
		txtSDT.setBounds(317, 340, 211, 23);
		pnCenterRight.add(txtSDT);

		btnThem = new JButton();
		btnThem.setBackground(new Color(51, 255, 255));
		btnThem.setIcon(new ImageIcon("IMG_Hinh\\them.jpg"));
		btnThem.setBounds(140, 500, 92, 45);
		pnCenterRight.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ma = txtMaNCC.getText();
				String ten = txtTenNCC.getText();
				String sdt = txtSDT.getText();
				String email = txtEmail.getText();
				String diachi = txtDiaChi.getText();
				if (busncc.kiemtra_themNCC(ma, ten, sdt, email, diachi) == true) {

					NhaCungCap a = new NhaCungCap(ma, ten, diachi, sdt, email);
					busncc.DeleteDataTable(model);

					if (daoncc.them_ncc(a)) {
						busncc.dodulieu(model, daoncc.layNCC());

					}

				}
			}

		});

		btnSua = new JButton();
		btnSua.setIcon(new ImageIcon("IMG_Hinh\\sua.jpg"));
		btnSua.setBackground(new Color(255, 128, 64));
		btnSua.setBounds(300, 500, 92, 45);
		pnCenterRight.add(btnSua);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ma = txtMaNCC.getText();
				String ten = txtTenNCC.getText();
				String sdt = txtSDT.getText();
				String email = txtEmail.getText();
				String diachi = txtDiaChi.getText();
				NhaCungCap a = daoncc.layNCCTheoMa(ma.trim());
				System.err.println(a);
				if (a == null) {
					thongbao.thongbao("sai", "");
				}
				if (busncc.kiemtra_update(ten, sdt, email, diachi)) {
					a.setDiaChi(diachi);
					a.setEmail(email);
					a.setSdt(sdt);
					a.setTenNhaCungCap(ten);
					System.err.println(a);
					if (daoncc.sua_ncc(a)) {
						busncc.dodulieu(model, daoncc.layNCC());
					}
				}

			}

		});
		btnLamMoi = new JButton();
		btnLamMoi.setIcon(new ImageIcon("IMG_Hinh\\reset.jpg"));
		btnLamMoi.setBounds(460, 500, 92, 45);
		pnCenterRight.add(btnLamMoi);

		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaNCC.setText(bus.BUS_NhaCungCap.updateMa());
				txtTenNCC.setText("");
				txtTimKiem.requestFocus();
				txtDiaChi.setText("");
				txtEmail.setText("");
				txtSDT.setText("");
			}
		});

		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp : ");
		lblMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaNCC.setBounds(81, 60, 154, 23);
		pnCenterRight.add(lblMaNCC);

		lblDiaChi = new JLabel("Địa chỉ :");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiaChi.setBounds(81, 200, 154, 23);
		pnCenterRight.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setSelectionColor(new Color(255, 128, 0));
		txtDiaChi.setForeground(Color.BLACK);
		txtDiaChi.setFont(new Font("Arial", Font.ITALIC, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setCaretColor(Color.RED);
		txtDiaChi.setBackground(new Color(240, 246, 251));
		txtDiaChi.setBounds(317, 200, 211, 23);
		pnCenterRight.add(txtDiaChi);

		btnThem.setFocusPainted(false);
		btnSua.setFocusPainted(false);
		btnLamMoi.setFocusPainted(false);
		BUS_NhaCungCap a = new BUS_NhaCungCap();
		a.dodulieu(model, daoncc.layNCC());

		// ResultSet aResultSet =
		// ConnectionManager.getdata("SELECT * FROM [dbo].[NhaCungCap]");
		// System.out.println(aResultSet);
	}
}
