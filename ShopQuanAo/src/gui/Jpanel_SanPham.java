package gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import java.awt.GridLayout;

import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bus.BUS_NhaCungCap;
import connectDB.ConnectionManager;
import dao.DAO_NhaCungCap;
import entity.Enum_BangLoaiSanPham;
import entity.Enum_ChatLieu;
import entity.Enum_KichThuoc;
import entity.Enum_Mau;
import entity.Enum_NhanHieu;
import entity.KhuyenMai;
import entity.NhaCungCap;
import entity.SanPham;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Jpanel_SanPham extends JPanel {
	public static String imageName = null;
	ArrayList<SanPham> arrSanPham = new ArrayList<SanPham>();
	private JTextField txtTimKiem;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtGia;
	private JTextField txtSoLuongS;
	private JPanel panelMain;
	private JLabel lblTimLoai;
	private JComboBox cboTimLoai;
	private JLabel lblTimKiem;
	private JLabel lblTimGia;
	private JComboBox cboTimGia;
	private JLabel lblTimMau;
	private JComboBox cboTimNH;
	private JLabel lblTimCL;
	private JComboBox cboTimCL;
	private JPanel pnlLeft;
	private JPanel pnlRight;
	private JPanel pnlAnh;
	private JLabel lblMa;
	private JLabel lblTen;
	private JLabel lblLoai;
	private JLabel lblGia;
	private JLabel lblNCC;
	private JLabel lblKichThuoc;
	private JLabel lblMau;
	private JLabel lblCL;
	private JLabel lblSoLuong;
	private JLabel lblMoTa;
	private JComboBox cboLoai;
	private JComboBox cboMau;
	private JComboBox cboCL;
	private JTextArea txtAMoTa;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnLamMoi;
	ConnectionManager connectionManager = new ConnectionManager();
	private JScrollPane jScrollPane1;
	private JComboBox cboNCC;
	private JLabel lblNhanHieu;
	private JComboBox cboNhanHieu;
	private JTextField txtKichThuoc;
	private JLabel lblM;
	private JTextField txtSoLuongM;
	private JLabel lblL;
	private JTextField txtSoLuongL;
	private JLabel lblXl;
	private JTextField txtSoLuongXL;
	private JLabel lblSoLuongCon;
	private JLabel lblS;
	SanPham sp;
	private JLabel lblAnh;
	private JButton btnLoc;
	private JLabel lblKM;
	private JButton btnUpFile;

	/**
	 * Create the panel.
	 */

	public Jpanel_SanPham() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		panelMain = new JPanel();
		panelMain.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panelMain.setBounds(0, 0, 1646, 52);
		add(panelMain);
		panelMain.setLayout(null);

		lblTimLoai = new JLabel("Loại:");
		lblTimLoai.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimLoai.setBounds(12, 13, 53, 30);
		panelMain.add(lblTimLoai);

		cboTimLoai = new JComboBox();
		cboTimLoai.addItem("Tất cả");
		for (Enum_BangLoaiSanPham loai : Enum_BangLoaiSanPham.values()) {
			cboTimLoai.addItem(loai.getValue());
		}
		cboTimLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimLoai.setBounds(65, 13, 125, 30);
		panelMain.add(cboTimLoai);

		lblTimKiem = new JLabel("Tìm kiếm:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimKiem.setBounds(1075, 13, 96, 30);
		panelMain.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0,
				0, 0)));
		txtTimKiem.setBounds(1170, 13, 200, 30);
		panelMain.add(txtTimKiem);
		txtTimKiem.setBackground(new Color(240, 240, 240));
		txtTimKiem.setColumns(10);

		lblTimGia = new JLabel("Giá:");
		lblTimGia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimGia.setBounds(210, 13, 53, 30);
		panelMain.add(lblTimGia);

		cboTimGia = new JComboBox();
		cboTimGia.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"Dưới 150,000đ", "150,000đ - 300,000đ", "300,000đ - 500,000đ",
				"Trên 500,000đ" }));
		cboTimGia.setBounds(260, 13, 210, 30);
		cboTimGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMain.add(cboTimGia);

		lblTimMau = new JLabel("Nhãn hiệu:");
		lblTimMau.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimMau.setBounds(500, 13, 101, 30);
		panelMain.add(lblTimMau);

		cboTimNH = new JComboBox();
		cboTimNH.addItem("Tất cả");
		for (Enum_NhanHieu nh : Enum_NhanHieu.values()) {
			cboTimNH.addItem(nh.getValue());
		}
		cboTimNH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimNH.setBounds(620, 13, 161, 30);
		panelMain.add(cboTimNH);

		lblTimCL = new JLabel("Chất liệu:");
		lblTimCL.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimCL.setBounds(800, 13, 96, 30);
		panelMain.add(lblTimCL);

		cboTimCL = new JComboBox();
		cboTimCL.addItem("Tất cả");
		for (Enum_ChatLieu cl : Enum_ChatLieu.values()) {
			cboTimCL.addItem(cl.getValue());
		}
		cboTimCL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTimCL.setBounds(900, 13, 161, 30);
		panelMain.add(cboTimCL);

		btnLoc = new JButton("LỌC");
		btnLoc.setBackground(Color.LIGHT_GRAY);
		btnLoc.setIcon(new ImageIcon("IMG_Hinh\\Icon_Loc.png"));
		btnLoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoc.setBounds(1400, 13, 95, 30);
		btnLoc.setFocusPainted(false);
		panelMain.add(btnLoc);

		btnUpFile = new JButton("Thêm");
		btnUpFile.setIcon(new ImageIcon("IMG_Hinh\\upload.png"));
		btnUpFile.setBackground(Color.LIGHT_GRAY);
		btnUpFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpFile.setBounds(1520, 13, 120, 30);
		btnUpFile.setFocusPainted(false);
		;
		panelMain.add(btnUpFile);

		pnlLeft = new JPanel();
		pnlLeft.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnlLeft.setBounds(0, 52, 1154, 923);
		add(pnlLeft);
		pnlLeft.setLayout(new GridLayout(1, 0, 0, 0));

		pnlRight = new JPanel();
		pnlRight.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnlRight.setBounds(1154, 52, 492, 923);
		add(pnlRight);
		pnlRight.setLayout(null);

		pnlAnh = new JPanel();
		pnlAnh.setBounds(192, 13, 108, 120);
		pnlRight.add(pnlAnh);
		pnlAnh.setLayout(null);

		lblAnh = new JLabel("");
		lblAnh.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnh.setBounds(0, 0, 108, 120);
		lblAnh.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		lblAnh.setIcon(new ImageIcon("IMG_Hinh//add.png"));

		pnlAnh.add(lblAnh);

		lblMa = new JLabel("Mã sản phẩm:");
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMa.setBounds(25, 167, 120, 30);
		pnlRight.add(lblMa);

		txtMa = new JTextField();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMa.setBounds(147, 167, 180, 30);
		pnlRight.add(txtMa);
		txtMa.setColumns(10);

		lblTen = new JLabel("Tên sản phẩm:");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTen.setBounds(25, 217, 120, 30);
		pnlRight.add(lblTen);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTen.setColumns(10);
		txtTen.setBounds(147, 217, 180, 30);
		pnlRight.add(txtTen);

		lblLoai = new JLabel("Loại:");
		lblLoai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoai.setBounds(25, 317, 120, 30);
		pnlRight.add(lblLoai);

		lblGia = new JLabel("Giá nhập:");
		lblGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGia.setBounds(25, 267, 120, 30);
		pnlRight.add(lblGia);

		lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNCC.setBounds(25, 417, 120, 30);
		pnlRight.add(lblNCC);

		lblKichThuoc = new JLabel("Kích thước:");
		lblKichThuoc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKichThuoc.setBounds(270, 267, 120, 30);
		pnlRight.add(lblKichThuoc);

		lblMau = new JLabel("Màu sắc:");
		lblMau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMau.setBounds(25, 367, 120, 30);
		pnlRight.add(lblMau);

		lblCL = new JLabel("Chất liệu:");
		lblCL.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCL.setBounds(270, 367, 120, 30);
		pnlRight.add(lblCL);

		lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoLuong.setBounds(25, 467, 120, 30);
		pnlRight.add(lblSoLuong);

		lblMoTa = new JLabel("Mô tả:");
		lblMoTa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMoTa.setBounds(25, 567, 120, 30);
		pnlRight.add(lblMoTa);

		cboLoai = new JComboBox();
		for (Enum_BangLoaiSanPham loai : Enum_BangLoaiSanPham.values()) {
			cboLoai.addItem(loai.getValue());
		}
		cboLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboLoai.setBounds(147, 317, 112, 30);
		pnlRight.add(cboLoai);

		txtGia = new JTextField();
		txtGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGia.setColumns(10);
		txtGia.setBounds(147, 267, 112, 30);
		pnlRight.add(txtGia);

		// Tạo một DocumentFilter để chỉ cho phép số
		AbstractDocument document = (AbstractDocument) txtGia.getDocument();
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

		cboMau = new JComboBox();
		for (Enum_Mau mau : Enum_Mau.values()) {
			cboMau.addItem(mau.getValue());
		}
		cboMau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboMau.setBounds(147, 367, 112, 30);
		pnlRight.add(cboMau);

		cboCL = new JComboBox();
		for (Enum_ChatLieu cl : Enum_ChatLieu.values()) {
			cboCL.addItem(cl.getValue());
		}
		cboCL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboCL.setBounds(365, 367, 123, 30);
		pnlRight.add(cboCL);

		cboNCC = new JComboBox();
		addDataCboNCC();
		cboNCC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboNCC.setBounds(147, 417, 180, 30);
		pnlRight.add(cboNCC);

		lblNhanHieu = new JLabel("Nhãn hiệu:");
		lblNhanHieu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhanHieu.setBounds(270, 317, 120, 30);
		pnlRight.add(lblNhanHieu);

		cboNhanHieu = new JComboBox();
		for (Enum_NhanHieu nh : Enum_NhanHieu.values()) {
			cboNhanHieu.addItem(nh.getValue());
		}
		cboNhanHieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboNhanHieu.setBounds(365, 317, 123, 30);
		pnlRight.add(cboNhanHieu);

		txtSoLuongS = new JTextField("0");
		txtSoLuongS.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongS.setColumns(10);
		txtSoLuongS.setBounds(177, 467, 100, 30);
		pnlRight.add(txtSoLuongS);

		// Tạo một DocumentFilter để chỉ cho phép số
		document = (AbstractDocument) txtSoLuongS.getDocument();
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

		txtAMoTa = new JTextArea();
		txtAMoTa.setBounds(147, 567, 323, 234);
		pnlRight.add(txtAMoTa);

		btnThem = new JButton();
		btnThem.setIcon(new ImageIcon("IMG_Hinh\\them.jpg"));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnThem.setBackground(new Color(77, 176, 61));
		btnThem.setBounds(68, 840, 92, 45);
		pnlRight.add(btnThem);
		btnThem.setFocusPainted(false);

		btnSua = new JButton();
		btnSua.setIcon(new ImageIcon("IMG_Hinh\\sua.jpg"));
		btnSua.setBackground(new Color(242, 223, 48));
		btnSua.setBounds(200, 840, 92, 45);
		pnlRight.add(btnSua);
		btnSua.setFocusPainted(false);

		btnLamMoi = new JButton();
		btnLamMoi.setIcon(new ImageIcon("IMG_Hinh\\reset.jpg"));
		btnLamMoi.setBackground(new Color(0, 191, 255));
		btnLamMoi.setBounds(332, 840, 92, 45);
		pnlRight.add(btnLamMoi);
		btnLamMoi.setFocusPainted(false);

		btnThem.setFocusPainted(false);
		btnSua.setFocusPainted(false);
		btnLamMoi.setFocusPainted(false);

		txtKichThuoc = new JTextField();
		txtKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtKichThuoc.setColumns(10);
		txtKichThuoc.setBounds(365, 267, 100, 30);
		pnlRight.add(txtKichThuoc);

		lblS = new JLabel("S");
		lblS.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblS.setBounds(147, 467, 25, 30);
		pnlRight.add(lblS);

		lblM = new JLabel("M");
		lblM.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblM.setBounds(300, 467, 25, 30);
		pnlRight.add(lblM);

		txtSoLuongM = new JTextField("0");
		txtSoLuongM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongM.setColumns(10);
		txtSoLuongM.setBounds(330, 467, 100, 30);
		pnlRight.add(txtSoLuongM);

		document = (AbstractDocument) txtSoLuongM.getDocument();
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

		lblL = new JLabel("L");
		lblL.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblL.setBounds(147, 517, 25, 30);
		pnlRight.add(lblL);

		txtSoLuongL = new JTextField("0");
		txtSoLuongL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongL.setColumns(10);
		txtSoLuongL.setBounds(177, 517, 100, 30);
		pnlRight.add(txtSoLuongL);

		document = (AbstractDocument) txtSoLuongL.getDocument();
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

		lblXl = new JLabel("XL");
		lblXl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblXl.setBounds(300, 517, 25, 30);
		pnlRight.add(lblXl);

		txtSoLuongXL = new JTextField("0");
		txtSoLuongXL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongXL.setColumns(10);
		txtSoLuongXL.setBounds(330, 517, 100, 30);
		pnlRight.add(txtSoLuongXL);

		document = (AbstractDocument) txtSoLuongXL.getDocument();
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

		lblSoLuongCon = new JLabel("");
		lblSoLuongCon.setForeground(Color.RED);
		lblSoLuongCon.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblSoLuongCon.setBounds(360, 167, 125, 30);
		pnlRight.add(lblSoLuongCon);

		txtMa.setEditable(false);
		txtKichThuoc.setEditable(false);

		lblKM = new JLabel("");
		lblKM.setForeground(Color.RED);
		lblKM.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblKM.setBounds(360, 217, 125, 30);
		pnlRight.add(lblKM);

		fillSanPham(dao.DAO_SanPham.laySP());
		jScrollPane1.getVerticalScrollBar().setUnitIncrement(25);

		// ==================================================================
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					btnThemActionPerformed(e);
					fillSanPham(dao.DAO_SanPham.laySP());
					jScrollPane1.getVerticalScrollBar().setUnitIncrement(25);
				} catch (Exception ea) {
					// TODO: handle exception
					thongbao.thongbao("Vui lòng nhập giá", "");
				}
			}
		});

		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLamMoiActionPerformed(e);
			}
		});

		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSuaActionPerformed(e);
			}

		});

		lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblAnhMouseClicked(evt);
			}
		});

		// txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
		// public void keyReleased(java.awt.event.KeyEvent evt) {
		// txtTimKiemKeyReleased(evt);
		// }
		//
		// });

		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLocActionPerformed(e);
			}
		});

		btnUpFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpLoadActionPerformed(e);
				fillSanPham(dao.DAO_SanPham.laySP());
				jScrollPane1.getVerticalScrollBar().setUnitIncrement(25);
			}
		});

	}

	private void btnUpLoadActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String path = "C:\\Users\\DELL\\Desktop\\UpLoad";
		JFileChooser fileChooser = new JFileChooser(path);
		// Hiển thị hộp thoại chọn file Excel
		int result = fileChooser.showOpenDialog(null);
		// Kiểm tra xem người dùng đã chọn file hay chưa
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			themDSSP(selectedFile);
		}

	}

	private void themDSSP(File file) {

		String tenNCC = cboNCC.getSelectedItem().toString();
		String maNCC = dao.DAO_NhaCungCap.layMaNCCTheoTen(tenNCC);
		NhaCungCap ncc = new NhaCungCap(maNCC);
		ncc.setTenNhaCungCap(tenNCC);
		boolean kiemTra = false;
		try (FileInputStream fis = new FileInputStream(file);
				XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next(); // Bỏ qua dòng tiêu đề

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				// SP
				String ten = cellIterator.next().getStringCellValue().trim();
				String loai = cellIterator.next().getStringCellValue().trim();
				Enum_BangLoaiSanPham loaiSP = Enum_BangLoaiSanPham
						.fromString(loai);
				String cl = cellIterator.next().getStringCellValue().trim();
				Enum_ChatLieu clSP = Enum_ChatLieu.fromString(cl);
				String mau = cellIterator.next().getStringCellValue().trim();
				Enum_Mau mauSP = Enum_Mau.fromString(mau);
				String kt = cellIterator.next().getStringCellValue().trim();
				Enum_KichThuoc ktSP = Enum_KichThuoc.fromString(kt);
				double giaNhap = cellIterator.next().getNumericCellValue();
				int soLuong = (int) cellIterator.next().getNumericCellValue();
				String nh = cellIterator.next().getStringCellValue();
				Enum_NhanHieu nhSP = Enum_NhanHieu.fromString(nh);
				String hinhAnh = cellIterator.next().getStringCellValue()
						.trim();
				SanPham spCuoi = dao.DAO_SanPham.laySPCuoi();
				String maxMaSP;
				if (spCuoi == null) {
					maxMaSP = "SP0000000";
				} else {
					maxMaSP = spCuoi.getMaSanPham();
				}
				if (spCuoi.getTenSanPham().equals(ten)
						&& spCuoi.getLoai() == loaiSP
						&& spCuoi.getChatLieu() == clSP
						&& spCuoi.getMau() == mauSP
						&& spCuoi.getNhanHieu() == nhSP) {
					String maSPKhongSize = maxMaSP.substring(0,
							maxMaSP.length() - 1);
					String maSPMoi = maSPKhongSize + soSize(kt);
					SanPham spMoi = new SanPham(maSPMoi, ten, loaiSP, giaNhap,
							ncc, mauSP, clSP, nhSP, hinhAnh, ktSP, soLuong, "",
							null);
					kiemTra = dao.DAO_SanPham.them(spMoi);
					if (!kiemTra) {
						thongbao.thongbao("Thêm không thành công", "");
						break;
					}
				} else {
					int maxSoSP = Integer.parseInt(maxMaSP.substring(2, 6));
					String maSPMoi = "SP" + String.format("%04d", maxSoSP + 1)
							+ soMau(mau) + soSize(kt);
					SanPham spMoi = new SanPham(maSPMoi, ten, loaiSP, giaNhap,
							ncc, mauSP, clSP, nhSP, hinhAnh, ktSP, soLuong, "",
							null);
					kiemTra = dao.DAO_SanPham.them(spMoi);
					if (!kiemTra) {
						thongbao.thongbao("Thêm không thành công", "");
						break;
					}
				}

			}
			if (kiemTra) {
				thongbao.thongbao("Thêm thành công", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	File f;

	private void lblAnhMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		String path = "IMG_SanPham";
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
			imageName = f.getName(); // LẤY TÊN FILE
			// imageName = f.getAbsolutePath(); // LẤY ĐƯỜNG DẪN
		}
		System.out.println(imageName);
	}

	// Phương thức kiểm tra xem chuỗi có chứa chỉ số hay không
	private static boolean isNumeric(String text) {
		return text.matches("^[0-9,]*$");
	}

	private void btnLocActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String loai = cboTimLoai.getSelectedItem().toString();
		String gia = cboTimGia.getSelectedItem().toString();
		String nh = cboTimNH.getSelectedItem().toString();
		String cl = cboTimCL.getSelectedItem().toString();
		String tuKhoa = txtTimKiem.getText().trim();
		ResultSet rs = dao.DAO_SanPham.timKiem(tuKhoa, loai, gia, nh, cl);
		try {
			if (!rs.isBeforeFirst()) {
				pnlLeft.removeAll();
				pnlLeft.add(jScrollPane1);
				thongbao.thongbao("Không tìm thấy sản phẩm !", "");
			} else {
				fillSanPham(rs);
				jScrollPane1.getVerticalScrollBar().setUnitIncrement(25);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void btnSuaActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		try {
			ganThongTin();
			if (bus.BUS_SanPham.kt_Sua(sanPham)) {
				dao.DAO_SanPham.sua(sanPham);
				fillSanPham(dao.DAO_SanPham.laySP());
				jScrollPane1.getVerticalScrollBar().setUnitIncrement(25);
			}
		} catch (Exception ea) {
			// TODO: handle exception
			thongbao.thongbao("Vui lòng nhập giá", "");
		}

	}

	public SanPham sanPham = new SanPham();

	public void ganThongTin() {
		sanPham.setTenSanPham(txtTen.getText().trim());
		sanPham.setMaSanPham(txtMa.getText().trim());
		sanPham.setGiaNhap(Double.parseDouble(txtGia.getText().trim()
				.replace(",", "")));
		sanPham.setLoai(Enum_BangLoaiSanPham.fromString(cboLoai
				.getSelectedItem().toString()));
		sanPham.setMau(Enum_Mau.fromString(cboMau.getSelectedItem().toString()));
		sanPham.setNhanHieu(Enum_NhanHieu.fromString(cboNhanHieu
				.getSelectedItem().toString()));
		sanPham.setChatLieu(Enum_ChatLieu.fromString(cboCL.getSelectedItem()
				.toString()));
		sanPham.setHinhAnh(imageName);
		if (txtKichThuoc.getText().trim().equals("S")) {
			sanPham.setSoLuong(Integer.parseInt(txtSoLuongS.getText().replace(
					",", "")));
		} else if (txtKichThuoc.getText().trim().equals("M")) {
			sanPham.setSoLuong(Integer.parseInt(txtSoLuongM.getText().replace(
					",", "")));
		} else if (txtKichThuoc.getText().trim().equals("L")) {
			sanPham.setSoLuong(Integer.parseInt(txtSoLuongL.getText().replace(
					",", "")));
		} else if (txtKichThuoc.getText().trim().equals("XL")) {
			sanPham.setSoLuong(Integer.parseInt(txtSoLuongXL.getText().replace(
					",", "")));
		}
	}

	private void btnLamMoiActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		txtMa.setText("");
		txtTen.setText("");
		txtGia.setText("");
		txtKichThuoc.setText("");
		cboLoai.setSelectedIndex(0);
		cboCL.setSelectedIndex(0);
		cboNhanHieu.setSelectedIndex(0);
		cboMau.setSelectedIndex(0);
		cboNhanHieu.setSelectedIndex(0);
		txtSoLuongS.setText("0");
		txtSoLuongM.setText("0");
		txtSoLuongL.setText("0");
		txtSoLuongXL.setText("0");
		txtAMoTa.setText("");
		lblSoLuong.setText("");
		lblAnh.setIcon(new ImageIcon("IMG_Hinh//add.png"));
		txtTen.requestFocus();
		lblKM.setText("");
		lblSoLuongCon.setText("");
		txtSoLuongS.setEditable(true);
		txtSoLuongM.setEditable(true);
		txtSoLuongL.setEditable(true);
		txtSoLuongXL.setEditable(true);
	}

	private void btnThemActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ResultSet rs = dao.DAO_SanPham.laySP();
		System.out.println(rs);
		String maxMaSP = "SP0000000";
		try {
			while (rs.next()) {
				maxMaSP = rs.getString("maSanPham").trim();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// String newMaSP = "";
		int maxSoSP = Integer.parseInt(maxMaSP.substring(2, 6));
		System.out.println(maxMaSP);
		String mau = cboMau.getSelectedItem().toString();
		// String b= cb.getSelectedItem().toString();
		int s = Integer.parseInt(txtSoLuongS.getText().trim());
		int m = Integer.parseInt(txtSoLuongM.getText().trim());
		int l = Integer.parseInt(txtSoLuongL.getText().trim());
		int xl = Integer.parseInt(txtSoLuongXL.getText().trim());

		String ten = txtTen.getText().trim();
		double giaNhap = Double.parseDouble(txtGia.getText().trim()
				.replace(",", ""));

		Enum_BangLoaiSanPham loai = Enum_BangLoaiSanPham.fromString(cboLoai
				.getSelectedItem().toString());
		Enum_Mau mau_2 = Enum_Mau.fromString(mau);
		Enum_NhanHieu nh = Enum_NhanHieu.fromString(cboNhanHieu
				.getSelectedItem().toString());
		Enum_ChatLieu cl = Enum_ChatLieu.fromString(cboCL.getSelectedItem()
				.toString());

		String tenNCC = cboNCC.getSelectedItem().toString();
		String maNCC = dao.DAO_NhaCungCap.layMaNCCTheoTen(tenNCC);
		System.err.println(maNCC);
		NhaCungCap ncc = new NhaCungCap(maNCC);
		ncc.setTenNhaCungCap(tenNCC);
		String moTa = txtAMoTa.getText().trim();
		boolean kiemTra = false;
		if (s > 0) {
			System.out.println("SP" + String.format("%04d", maxSoSP + 1)
					+ soMau(mau) + "1");
			String maSP = "SP" + String.format("%04d", maxSoSP + 1)
					+ soMau(mau) + soSize("S");
			Enum_KichThuoc kt = Enum_KichThuoc.fromString("S");
			sp = new SanPham(maSP, ten, loai, giaNhap, ncc, mau_2, cl, nh,
					imageName, kt, s, moTa, null);
			if (bus.BUS_SanPham.kt_Them(sp)) {
				kiemTra = dao.DAO_SanPham.them(sp);
				save_IMG(f);
			}

		}
		if (m > 0) {
			System.out.println("SP" + String.format("%04d", maxSoSP + 1)
					+ soMau(mau) + "2");
			String maSP = "SP" + String.format("%04d", maxSoSP + 1)
					+ soMau(mau) + soSize("M");
			Enum_KichThuoc kt = Enum_KichThuoc.fromString("M");
			sp = new SanPham(maSP, ten, loai, giaNhap, ncc, mau_2, cl, nh,
					imageName, kt, m, moTa, null);
			if (bus.BUS_SanPham.kt_Them(sp)) {
				kiemTra = dao.DAO_SanPham.them(sp);
				save_IMG(f);
			}

		}
		if (l > 0) {
			System.out.println("SP" + String.format("%04d", maxSoSP + 1)
					+ soMau(mau) + "3");
			String maSP = "SP" + String.format("%04d", maxSoSP + 1)
					+ soMau(mau) + soSize("L");
			Enum_KichThuoc kt = Enum_KichThuoc.fromString("L");
			sp = new SanPham(maSP, ten, loai, giaNhap, ncc, mau_2, cl, nh,
					imageName, kt, l, moTa, null);
			if (bus.BUS_SanPham.kt_Them(sp)) {
				kiemTra = dao.DAO_SanPham.them(sp);
				save_IMG(f);
			}
		}
		if (xl > 0) {
			System.out.println("SP" + String.format("%04d", maxSoSP + 1)
					+ soMau(mau) + "4");
			String maSP = "SP" + String.format("%04d", maxSoSP + 1)
					+ soMau(mau) + soSize("XL");
			Enum_KichThuoc kt = Enum_KichThuoc.fromString("XL");
			sp = new SanPham(maSP, ten, loai, giaNhap, ncc, mau_2, cl, nh,
					imageName, kt, xl, moTa, null);
			if (bus.BUS_SanPham.kt_Them(sp)) {
				kiemTra = dao.DAO_SanPham.them(sp);
				save_IMG(f);
			}
		}
		if (kiemTra) {
			thongbao.thongbao("Thêm thành công", "");
		} else {
			thongbao.thongbao("Thêm không thành công", "");
		}

	}

	public static boolean save_IMG(File file) {
		File dir = new File("IMG_SanPham");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {
			File newfile = new File(dir, file.getName());
			Path source = Paths.get(file.getAbsolutePath());
			Path ok = Paths.get(newfile.getAbsolutePath());
			Files.copy(source, ok, StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void lbl_ImageSP(String ImagePath) {
		ImageIcon icon = new ImageIcon("IMG_SanPham\\" + ImagePath);
		// ImageIcon icon = new ImageIcon(ImagePath);
		Image image = icon.getImage();
		ImageIcon icon1 = new ImageIcon(image.getScaledInstance(
				lblAnh.getWidth(), lblAnh.getHeight(), image.SCALE_SMOOTH));
		lblAnh.setIcon(icon1);
	}

	private String soSize(String size) {
		int so = 0;
		if (size.equalsIgnoreCase("S"))
			so = 1;
		else if (size.equalsIgnoreCase("M"))
			so = 2;
		else if (size.equalsIgnoreCase("L"))
			so = 3;
		else if (size.equalsIgnoreCase("XL"))
			so = 4;
		return String.format("%d", so);
	}

	private String soMau(String mau) {
		int so = 0;
		if (mau.equalsIgnoreCase("Đỏ"))
			so = 1;
		else if (mau.equalsIgnoreCase("Vàng"))
			so = 2;
		else if (mau.equalsIgnoreCase("Cam"))
			so = 3;
		else if (mau.equalsIgnoreCase("Trắng"))
			so = 4;
		else if (mau.equalsIgnoreCase("Đen"))
			so = 5;
		else if (mau.equalsIgnoreCase("Xanh lá"))
			so = 6;
		else if (mau.equalsIgnoreCase("Xanh dương"))
			so = 7;
		else if (mau.equalsIgnoreCase("Xám"))
			so = 8;
		else if (mau.equalsIgnoreCase("Be"))
			so = 9;
		else if (mau.equalsIgnoreCase("Hồng"))
			so = 10;
		return String.format("%02d", so);
	}

	private void addDataCboNCC() {
		ResultSet rs = dao.DAO_NhaCungCap.layNCC();
		try {
			while (rs.next()) {
				cboNCC.addItem(rs.getString("tenNhaCungCap"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void fillSanPham(ResultSet rs) {
		arrSanPham.clear();
		pnlLeft.removeAll();

		try {
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSanPham(rs.getString("maSanPham").trim());
				sp.setTenSanPham(rs.getString("tenSanPham").trim());
				sp.setHinhAnh(rs.getString("hinhAnh").trim());
				sp.setSoLuong(rs.getInt("soLuong"));
				sp.setMoTa(rs.getString("moTa").trim());
				sp.setGiaNhap(rs.getDouble("giaNhap"));

				sp.setLoai(Enum_BangLoaiSanPham.fromString(rs.getString("loai")
						.trim()));
				sp.setChatLieu(Enum_ChatLieu.fromString(rs
						.getString("chatLieu").trim()));
				sp.setMau(Enum_Mau.fromString(rs.getString("mau").trim()));
				sp.setKichThuoc(Enum_KichThuoc.fromString(rs.getString(
						"kichThuoc").trim()));
				sp.setNhanHieu(Enum_NhanHieu.fromString(rs
						.getString("nhanHieu").trim()));

				NhaCungCap ncc = dao.DAO_NhaCungCap.layNCCTheoMa(rs.getString(
						"maNhaCungCap").trim());
				sp.setNhaCC(ncc);

				String maKhuyenMai = rs.getString("maKhuyenMai");
				if (maKhuyenMai != null) {
					KhuyenMai km = new KhuyenMai(maKhuyenMai);
					sp.setKhuyenMai(km);
				} else {
					sp.setKhuyenMai(null);
				}

				arrSanPham.add(sp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel pnlSP = new JPanel();
		int sapXep = arrSanPham.size();
		if (sapXep % 4 == 0) {
			pnlSP.setLayout(new GridLayout(sapXep / 4, 4, 10, 10));
			jScrollPane1 = new JScrollPane(pnlSP);
			jScrollPane1.setViewportView(pnlSP);
		} else {
			pnlSP.setLayout(new GridLayout(sapXep / 4 + 1, 4, 10, 10));
			jScrollPane1 = new JScrollPane(pnlSP);
			jScrollPane1.setViewportView(pnlSP);
		}
		if (arrSanPham != null) {
			JButton[] btn = new JButton[arrSanPham.size()];
			DecimalFormat decimalFormat = new DecimalFormat("#,###");
			for (int i = 0; i < arrSanPham.size(); i++) {
				btn[i] = new JButton();
				btn[i].setFocusPainted(false);
				btn[i].setBackground(Color.WHITE);
				btn[i].setHorizontalTextPosition((int) CENTER_ALIGNMENT);
				btn[i].setCursor(new java.awt.Cursor(
						java.awt.Cursor.HAND_CURSOR));
				btn[i].setFont(new java.awt.Font("Tohoma", 1, 15));
				String a = ("<html><div style=\"text-align: center;margin-top:190px;\">"
						+ String.valueOf(arrSanPham.get(i).getTenSanPham())
						+ "<br>"
						+ decimalFormat.format(arrSanPham.get(i).getGiaBan())
						+ "đ<br>" + "</div></html>");
				btn[i].setText(a);
				btn[i].setPreferredSize(new Dimension(270, 300));
				// btn[i].setToolTipText(a);
				btn[i].setIcon(new javax.swing.ImageIcon("IMG_SanPham//"
						+ arrSanPham.get(i).getHinhAnh()));
				String ma = arrSanPham.get(i).getMaSanPham();
				String ten = arrSanPham.get(i).getTenSanPham();
				Enum_BangLoaiSanPham loai = arrSanPham.get(i).getLoai();
				double gia = arrSanPham.get(i).getGiaNhap();

				String tenNCC = dao.DAO_NhaCungCap.getTenTheoMaSP(arrSanPham
						.get(i).getMaSanPham());

				Enum_KichThuoc kt = arrSanPham.get(i).getKichThuoc();
				Enum_Mau mau = arrSanPham.get(i).getMau();
				Enum_ChatLieu cl = arrSanPham.get(i).getChatLieu();
				Enum_NhanHieu nh = arrSanPham.get(i).getNhanHieu();
				int soLuong = arrSanPham.get(i).getSoLuong();
				String mota = arrSanPham.get(i).getMoTa();
				String anh = arrSanPham.get(i).getHinhAnh();

				int pt;
				if (arrSanPham.get(i).getKhuyenMai() != null) {
					pt = dao.DAO_KhuyenMai.layPhanTramTheoMaKM(arrSanPham
							.get(i).getKhuyenMai().getMaKhuyenMai());
				} else {
					pt = 0;
				}

				btn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						txtAMoTa.setText(mota);
						txtMa.setText(ma);
						txtTen.setText(ten);
						cboLoai.setSelectedItem(loai.getValue());
						txtGia.setText(decimalFormat.format(gia));
						// NhaCungCap nhaCC = new NhaCungCap(ncc);
						// nhaCC.setTenNhaCungCap(dao.DAO_NhaCungCap.layNCCTheoMa(ncc).getTenNhaCungCap().trim());
						if (pt > 0) {
							lblKM.setText("Khuyến mãi " + pt + " %");
						} else {
							lblKM.setText("");
						}
						cboNCC.setSelectedItem(tenNCC);

						// cboKichThuoc.setSelectedItem(kt.getValue());
						txtKichThuoc.setText(kt.getValue());
						cboCL.setSelectedItem(cl.getValue());
						cboNhanHieu.setSelectedItem(nh.getValue());
						cboMau.setSelectedItem(mau.getValue());
						// lblSoLuongCon.setText("");
						// if (soLuong <= 10) {
						// lblSoLuongCon.setText("Còn "
						// + decimalFormat.format(soLuong) + " cái");
						// }
						lblSoLuongCon.setText("Còn "
								+ decimalFormat.format(soLuong) + " cái");
						lbl_ImageSP(anh);
						imageName = anh;

						dongSoLuongKhiChon(ma.substring(ma.length() - 1),
								soLuong);
					}
				});
				pnlSP.add(btn[i]);
				pnlSP.updateUI();
				pnlLeft.add(jScrollPane1);
			}
		}
	}

	private void dongSoLuongKhiChon(String soSize, int soLuong) {
		txtSoLuongS.setEditable(false);
		txtSoLuongM.setEditable(false);
		txtSoLuongL.setEditable(false);
		txtSoLuongXL.setEditable(false);
		txtSoLuongS.setText("0");
		txtSoLuongM.setText("0");
		txtSoLuongL.setText("0");
		txtSoLuongXL.setText("0");
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		if (soSize.equals("1")) {
			txtSoLuongS.setEditable(true);
			txtSoLuongS.setText(decimalFormat.format(soLuong));
		} else if (soSize.equals("2")) {
			txtSoLuongM.setEditable(true);
			txtSoLuongM.setText(decimalFormat.format(soLuong));
		} else if (soSize.equals("3")) {
			txtSoLuongL.setEditable(true);
			txtSoLuongL.setText(decimalFormat.format(soLuong));
		} else if (soSize.equals("4")) {
			txtSoLuongXL.setEditable(true);
			txtSoLuongXL.setText(decimalFormat.format(soLuong));
		}
	}
}
