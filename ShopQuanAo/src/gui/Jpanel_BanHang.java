package gui;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import entity.ChiTietHoaDon;
import entity.Enum_BangLoaiSanPham;
import entity.Enum_ChatLieu;
import entity.Enum_KichThuoc;
import entity.Enum_Mau;
import entity.Enum_NhanHieu;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.SanPham;

public class Jpanel_BanHang extends JPanel {
	private String cl_yellow = "#fcbe00";
	private String cl_green = "#00c691";
	public static JTextField txtEmail;
	private static JTextField txtTimSp;
	private static DefaultTableModel modelSP;
	private JTable tableSP;
	private JTextField txtNgayLapHD;
	public static DefaultTableModel modelCTHD;
	private JTable tableCTHD;
	public static JTextField txtTenKH;
	public static JTextField txtSDT;
	private JTextField txtTenNV;
	private JTextField txtMaNV;
	public static JTextField txtMaHD;
	private JPanel panel_left_bot;
	private JLabel lblMaNV;
	private JLabel lblTenNV;
	private JLabel lblNgayLapHD;
	private JButton btnHuyHD;
	private JLabel lblMaHD;
	private JPanel panel_left_top;
	private JLabel lblThongTinKH;
	private JLabel lblMaKH;
	private JLabel lblTenKH;
	private JLabel lblSdt;
	private JLabel lblEmail;
	private JLabel lblSoTien;
	private JButton btnTaoHD;
	private JLabel lblBac;
	private JPanel panel_right_top;
	private JLabel lblSanPham;
	private JLabel lblTimSp;
	private static JLabel lblAnh;
	private JPanel panel_right_bot;
	private JLabel lblTongTien;
	private JButton btnCho;
	private JButton btnDSCho;
	private JButton btnThanhToan;
	private JLabel lblThongTinHD;
	private JButton btnXoa;
	private static JLabel lblTongTienRong;
	private JTableHeader tbHeader1;
	private JScrollPane scrollPane1;
	private static JLabel lblTongKMRong;
	public static JTextField txtMaKH;
	private static JTextField txtSoLuong;
	private JLabel lblSoLuong;
	private JLabel lblTongKM;
	private JLabel lblTongTienThu;
	private JLabel lblTongThue;
	private JLabel lblTongKMTheoBac;
	private JButton btnQuetMa;
	private static JLabel lblTongKMTheoBacRong;
	public static JTextField txtBac;
	public static JTextField txtSoTienDaMua;
	// private ChiTietHoaDon cthd = new ChiTietHoaDon();
	public static ChiTietHoaDon cthd;
	public static KhachHang kh;
	public static NhanVien nhanVien;
	public static List<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
	public static Webcam webcam;
	public static WebcamPanel webcamPanel;


	/**
	 * Create the panel.
	 */
	public Jpanel_BanHang(NhanVien nv) {
		setLayout(null);
		panel_left_bot = new JPanel();
		panel_left_bot.setBounds(0, 535, 315, 440);
		add(panel_left_bot);
		panel_left_bot.setLayout(null);
		panel_left_bot.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 0, 0)));

		dsCTHD.clear();

		lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNV.setBounds(15, 122, 133, 26);
		panel_left_bot.add(lblMaNV);

		lblTenNV = new JLabel("Tên NV:");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenNV.setBounds(15, 182, 133, 26);
		panel_left_bot.add(lblTenNV);

		lblNgayLapHD = new JLabel("Ngày lập HĐ:");
		lblNgayLapHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgayLapHD.setBounds(15, 242, 133, 26);
		panel_left_bot.add(lblNgayLapHD);

		btnHuyHD = new JButton("Hủy hóa đơn");
		btnHuyHD.setForeground(Color.BLACK);
		btnHuyHD.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnHuyHD.setBackground(Color.RED);
		btnHuyHD.setBounds(89, 310, 146, 49);
		panel_left_bot.add(btnHuyHD);

		lblMaHD = new JLabel("Mã HD:");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD.setBounds(15, 62, 133, 26);
		panel_left_bot.add(lblMaHD);

		txtNgayLapHD = new JTextField();
		txtNgayLapHD.setForeground(new Color(0, 0, 0));
		txtNgayLapHD.setHorizontalAlignment(SwingConstants.LEADING);
		txtNgayLapHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgayLapHD.setEditable(false);
		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		txtNgayLapHD.setText(d.format(LocalDate.now()));
		txtNgayLapHD.setBounds(135, 242, 167, 26);
		panel_left_bot.add(txtNgayLapHD);
		txtNgayLapHD.setColumns(10);

		txtTenNV = new JTextField(nv.getTenNhanVien());
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(135, 182, 167, 26);
		panel_left_bot.add(txtTenNV);

		txtMaNV = new JTextField(nv.getMaNhanVien());
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(135, 122, 167, 26);
		panel_left_bot.add(txtMaNV);

		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(135, 62, 167, 26);
		panel_left_bot.add(txtMaHD);

		panel_left_top = new JPanel();
		panel_left_top.setBounds(0, 0, 315, 535);
		add(panel_left_top);
		panel_left_top.setLayout(null);
		panel_left_top.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(
				0, 0, 0)));

		lblThongTinKH = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblThongTinKH.setForeground(new Color(252, 190, 0));
		lblThongTinKH.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblThongTinKH.setBounds(26, 16, 289, 31);
		panel_left_top.add(lblThongTinKH);

		lblMaKH = new JLabel("Mã:");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKH.setBounds(15, 137, 69, 26);
		panel_left_top.add(lblMaKH);

		lblTenKH = new JLabel("Tên:");
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenKH.setBounds(15, 195, 45, 26);
		panel_left_top.add(lblTenKH);

		lblSdt = new JLabel("SDT:");
		lblSdt.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSdt.setBounds(15, 79, 69, 26);
		panel_left_top.add(lblSdt);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(15, 253, 69, 26);
		panel_left_top.add(lblEmail);

		lblSoTien = new JLabel("Số tiền đã mua:");
		lblSoTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoTien.setBounds(15, 364, 146, 26);
		panel_left_top.add(lblSoTien);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(80, 253, 220, 26);
		panel_left_top.add(txtEmail);

		btnTaoHD = new JButton("Tạo hóa đơn");
		btnTaoHD.setForeground(Color.BLACK);
		btnTaoHD.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnTaoHD.setBackground(new Color(50, 205, 50));
		btnTaoHD.setBounds(89, 445, 146, 49);
		panel_left_top.add(btnTaoHD);

		lblBac = new JLabel("Bậc:");
		lblBac.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBac.setBounds(15, 311, 69, 26);
		panel_left_top.add(lblBac);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKH.setHorizontalAlignment(SwingConstants.LEADING);
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(80, 196, 220, 26);
		panel_left_top.add(txtTenKH);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(80, 137, 220, 26);
		panel_left_top.add(txtMaKH);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		txtSDT.setBounds(80, 79, 220, 26);
		panel_left_top.add(txtSDT);

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

		panel_right_top = new JPanel();
		panel_right_top.setLayout(null);
		panel_right_top.setForeground(Color.WHITE);
		panel_right_top.setBorder(new MatteBorder(0, 0, 2, 0,
				(Color) new Color(0, 0, 0)));
		panel_right_top.setBounds(315, 0, 1331, 288);
		add(panel_right_top);

		lblSanPham = new JLabel("SẢN PHẨM");
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPham.setForeground(new Color(252, 190, 0));
		lblSanPham.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblSanPham.setBounds(0, 16, 172, 26);
		panel_right_top.add(lblSanPham);

		String row[] = { "Mã SP", "Tên SP", "Kích thước", "Nhãn hiệu", "Màu",
				"Giá" };
		modelSP = new DefaultTableModel(row, 0);
		tableSP = new JTable(modelSP);
		tableSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				153)));
		JTableHeader tbHeader = tableSP.getTableHeader();
		tbHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableSP.setFont(new Font("Tohoma", Font.PLAIN, 18));
		tbHeader.setBackground((Color.decode("#00c691")));
		tbHeader.setForeground(Color.white);
		JScrollPane scrollPane = new JScrollPane(tableSP);
		scrollPane.setBorder(null);
		scrollPane.setLocation(38, 137);
		scrollPane.setSize(904, 62);
		panel_right_top.add(scrollPane);
		tableSP.setRowHeight(30);

		lblTimSp = new JLabel("Nhập mã sản phẩm:");
		lblTimSp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimSp.setBounds(550, 38, 181, 26);
		panel_right_top.add(lblTimSp);

		txtTimSp = new JTextField();
		txtTimSp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimSp.setColumns(10);
		txtTimSp.setBounds(737, 38, 208, 26);
		panel_right_top.add(txtTimSp);

		lblAnh = new JLabel("");
		lblAnh.setBounds(960, 38, 150, 192);
		panel_right_top.add(lblAnh);

		panel_right_bot = new JPanel();
		panel_right_bot.setBounds(315, 287, 1331, 688);
		add(panel_right_bot);
		panel_right_bot.setLayout(null);

		String row1[] = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá",
				"Tiền khuyến mãi", "Thành tiền" };
		modelCTHD = new DefaultTableModel(row1, 0);
		tableCTHD = new JTable(modelCTHD);
		tableCTHD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				255, 153)));
		tbHeader1 = tableCTHD.getTableHeader();
		tbHeader1.setBackground((Color.decode("#00c691")));
		tbHeader1.setForeground(Color.white);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 18));
		tableCTHD.setFont(new Font("Tohoma", Font.PLAIN, 18));
		tbHeader.setBackground((Color.decode("#00c691")));
		tbHeader.setForeground(Color.white);
		scrollPane1 = new JScrollPane(tableCTHD);
		scrollPane1.setBorder(null);
		scrollPane1.setLocation(41, 80);
		scrollPane1.setSize(1265, 410);
		panel_right_bot.add(scrollPane1);

		tableCTHD.setRowHeight(30);

		TableColumnModel columnModelSP = tableSP.getColumnModel();
		TableColumn giaSP = columnModelSP.getColumn(5);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		giaSP.setCellRenderer(rightRenderer);

		TableColumnModel columnModelCTHD = tableCTHD.getColumnModel();
		TableColumn slCTHD = columnModelCTHD.getColumn(2);
		TableColumn giaCTHD = columnModelCTHD.getColumn(3);
		TableColumn kmCTHD = columnModelCTHD.getColumn(4);
		TableColumn ttCTHD = columnModelCTHD.getColumn(5);
		DefaultTableCellRenderer rightRendererCTHD = new DefaultTableCellRenderer();
		rightRendererCTHD.setHorizontalAlignment(SwingConstants.RIGHT);
		slCTHD.setCellRenderer(rightRendererCTHD);
		giaCTHD.setCellRenderer(rightRendererCTHD);
		kmCTHD.setCellRenderer(rightRendererCTHD);
		ttCTHD.setCellRenderer(rightRendererCTHD);

		lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setForeground(Color.RED);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTongTien.setBounds(38, 500, 190, 30);
		panel_right_bot.add(lblTongTien);

		btnCho = new JButton("Chờ");
		btnCho.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnCho.setBackground(Color.ORANGE);
		btnCho.setBounds(800, 600, 90, 36);
		panel_right_bot.add(btnCho);

		btnDSCho = new JButton("Danh sách chờ");
		btnDSCho.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDSCho.setBackground(Color.CYAN);
		btnDSCho.setBounds(570, 600, 190, 36);
		panel_right_bot.add(btnDSCho);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnThanhToan.setBackground(new Color(50, 205, 50));
		btnThanhToan.setBounds(930, 600, 140, 36);
		panel_right_bot.add(btnThanhToan);

		lblThongTinHD = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblThongTinHD.setBounds(33, 16, 264, 26);
		panel_right_bot.add(lblThongTinHD);
		lblThongTinHD.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblThongTinHD.setForeground(Color.decode(cl_yellow));

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setBounds(1110, 600, 91, 36);
		panel_right_bot.add(btnXoa);

		lblTongTienRong = new JLabel("");
		lblTongTienRong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTienRong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongTienRong.setBounds(280, 500, 200, 30);
		panel_right_bot.add(lblTongTienRong);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setText("1");
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setBounds(1040, 245, 60, 30);
		panel_right_top.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		document = (AbstractDocument) txtSoLuong.getDocument();
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

		// //Khởi tạo webcam
		webcam = webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		// Panel chứa webcam
		webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setBounds(1160, 38, 160, 160);
		panel_right_top.add(webcamPanel);

		btnQuetMa = new JButton("Quét");
		btnQuetMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnQuetMa.setBounds(1193, 205, 97, 30);
		panel_right_top.add(btnQuetMa);

		btnQuetMa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Khi nhấn nút, thực hiện quét mã vạch
				scanBarcode();
			}
		});

		lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setBounds(920, 245, 150, 30);
		panel_right_top.add(lblSoLuong);

		btnQuetMa.setFocusPainted(false);
		btnDSCho.setFocusPainted(false);
		btnCho.setFocusPainted(false);
		btnThanhToan.setFocusPainted(false);
		btnXoa.setFocusPainted(false);

		lblTongKM = new JLabel("Tổng khuyến mãi:");
		lblTongKM.setForeground(Color.RED);
		lblTongKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTongKM.setBounds(38, 535, 190, 30);
		panel_right_bot.add(lblTongKM);

		lblTongKMRong = new JLabel("");
		lblTongKMRong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongKMRong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongKMRong.setBounds(280, 535, 200, 30);
		panel_right_bot.add(lblTongKMRong);

		lblTongThue = new JLabel("Tổng thuế:");
		lblTongThue.setForeground(Color.RED);
		lblTongThue.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTongThue.setBounds(38, 570, 190, 30);
		panel_right_bot.add(lblTongThue);

		lblTongTienThu = new JLabel("Tổng tiền cần thu:");
		lblTongTienThu.setForeground(Color.RED);
		lblTongTienThu.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTongTienThu.setBounds(38, 640, 190, 30);
		panel_right_bot.add(lblTongTienThu);

		lblTongThueRong = new JLabel("");
		lblTongThueRong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongThueRong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongThueRong.setBounds(280, 570, 200, 30);
		panel_right_bot.add(lblTongThueRong);

		lblTongCanThuRong = new JLabel("");
		lblTongCanThuRong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongCanThuRong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongCanThuRong.setBounds(280, 640, 200, 30);
		panel_right_bot.add(lblTongCanThuRong);

		lblTongKMTheoBac = new JLabel("Tổng khuyến mãi theo bậc:");
		lblTongKMTheoBac.setForeground(Color.RED);
		lblTongKMTheoBac.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTongKMTheoBac.setBounds(38, 605, 240, 30);
		panel_right_bot.add(lblTongKMTheoBac);

		lblTongKMTheoBacRong = new JLabel("");
		lblTongKMTheoBacRong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongKMTheoBacRong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongKMTheoBacRong.setBounds(280, 605, 200, 30);
		panel_right_bot.add(lblTongKMTheoBacRong);

		txtMaKH.setEditable(false);
		txtTenKH.setEditable(false);
		txtEmail.setEditable(false);
		txtMaHD.setEditable(false);
		txtMaNV.setEditable(false);
		txtTenNV.setEditable(false);

		// =========================================================
		btnTaoHD.setFocusPainted(false);

		txtBac = new JTextField();
		txtBac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBac.setEditable(false);
		txtBac.setColumns(10);
		txtBac.setBounds(80, 311, 220, 26);
		panel_left_top.add(txtBac);

		txtSoTienDaMua = new JTextField();
		txtSoTienDaMua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoTienDaMua.setEditable(false);
		txtSoTienDaMua.setColumns(10);
		txtSoTienDaMua.setBounds(160, 364, 140, 26);
		panel_left_top.add(txtSoTienDaMua);

		btnHuyHD.setFocusPainted(false);

		btnThanhToan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnThanhToanActionPerformed(e);
			}

		});

		btnDSCho.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnDSChoActionPerformed(e);
			}

		});

		btnCho.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnChoActionPerformed(e);
			}
		});

		txtSDT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layThongTin();
			}
		});

		txtTimSp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				txtTimSpActionPerformed(e);
			}
		});

		txtSoLuong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtSoLuongActionPerformed(e);
			}

		});

		btnTaoHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTaoHDActionPerformed(e);
				txtTimSp.requestFocus();
			}
		});

		btnHuyHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnHuyHDActionPerformed(e);
			}
		});

		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnXoaActionPerformed(e);
			}

		});

		nhanVien = nv;

		tableCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableCTHDMouseClicked(evt);
			}
		});

	}

	private void tableCTHDMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		if (tableCTHD.getSelectedRowCount() == 1) {
			int vitri = tableCTHD.getSelectedRow();
			String maSP = tableCTHD.getValueAt(vitri, 0).toString();
			sp = dao.DAO_SanPham.laySanPhamTheoMa(maSP);
			thongTinChiTiet(sp);
			txtSoLuong.requestFocus();
		}

	}

	private void thongTinChiTiet(SanPham sp) {

		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		modelSP.setRowCount(0);
		modelSP.addRow(new Object[] { sp.getMaSanPham(), sp.getTenSanPham(),
				sp.getKichThuoc().getValue(), sp.getNhanHieu().getValue(),
				sp.getMau().getValue(), decimalFormat.format(sp.getGiaBan()) });
		ImageIcon ii = new ImageIcon(new ImageIcon("IMG_SanPham\\"
				+ sp.getHinhAnh()).getImage().getScaledInstance(
				lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH));
		txtTimSp.setText(sp.getMaSanPham());
		lblAnh.setIcon(ii);

	}

	// Phương thức để thực hiện quét mã vạch
	private static void scanBarcode() {
		// Lấy hình ảnh từ webcam
		BufferedImage image = webcam.getImage();

		// Thực hiện giải mã
		Result result = decodeBarcode(image);

		// Hiển thị kết quả
		if (result != null) {
			txtTimSp.setText(result.getText());
			if (txtMaHD.getText().equals("")) {
				thongbao.thongbao("Chưa tạo hóa đơn !", "");
			} else {
				modelSP.setRowCount(0);
				laySanPham();
			}
		}

	}

	private static Result decodeBarcode(BufferedImage image) {
		// Tạo đối tượng đọc mã vạch
		MultiFormatReader reader = new MultiFormatReader();

		// Tạo đối tượng hình ảnh từ BufferedImage
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		try {
			// Thực hiện giải mã
			return reader.decode(bitmap);
		} catch (NotFoundException e) {
			// Không tìm thấy mã vạch
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static HoaDon hd;

	public static void btnTaoHDActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ResultSet rs = dao.DAO_HoaDon.layHD();
		System.out.println(rs);
		String currentDate = getCurrentDate();
		String maxMaHD = "HD" + currentDate + "0000";
		try {
			while (rs.next()) {
				maxMaHD = rs.getString("maHoaDon").trim();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String newMaHD = "";
		if (maxMaHD.startsWith("HD" + currentDate)) {
			int maxSoHD = extractSerialNumber(maxMaHD);
			newMaHD = "HD" + currentDate + String.format("%04d", maxSoHD + 1);
			System.out.println(newMaHD);
		} else {
			newMaHD = "HD" + currentDate + "0001";
			System.out.println(newMaHD);
		}

		txtMaHD.setText(newMaHD);
		hd = new HoaDon(newMaHD);
		if (txtMaKH.getText().equals("")) {
			kh = null;
		}
		hd.setKhachHang(kh);
		hd.setNhanVien(nhanVien);
		hd.setTongTienCanThu(dsCTHD);

	}

	private void btnDSChoActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (txtMaHD.getText().equals("")) {
			// System.err.println(DanhSachCho.dsHoaDonCho.get(0).getMaHoaDon());
			// System.err.println(DanhSachCho.listDanhSachCTHDCho.get(0).get(0).getSoLuong());
			DanhSachCho frm = new DanhSachCho();
			frm.setVisible(true);
			frm.setLocationRelativeTo(null);
		} else {
			thongbao.thongbao(
					"Hoàn thành hóa đơn này trước khi mở danh sách chờ !", "");
		}
	}

	private void btnChoActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (!txtMaHD.getText().equals("")) {
			int nutbam = JOptionPane.showConfirmDialog(new JFrame(),
					"Bạn chắc chắn chuyển vào danh sách chờ?", "",
					JOptionPane.YES_NO_OPTION);
			if (nutbam == JOptionPane.YES_OPTION) {
				DanhSachCho.dsHoaDonCho.add(hd);
				List<ChiTietHoaDon> dsCTHDPhu = new ArrayList<ChiTietHoaDon>(
						dsCTHD);// Tạo ra để tránh bị mất data khi
								// dsCTHD.clear()
				DanhSachCho.listDanhSachCTHDCho.add(dsCTHDPhu);
				setTatCaRong();
				dsCTHD.clear();
			}
			txtSDT.requestFocus();
		} else {
			thongbao.thongbao("Chưa tạo hóa đơn !", "");
		}
	}

	private void txtTimSpActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (txtMaHD.getText().equals("")) {
			thongbao.thongbao("Chưa tạo hóa đơn !", "");
		} else {
			modelSP.setRowCount(0);
			laySanPham();
		}
	}

	private void txtSoLuongActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (txtTimSp.getText().equals("")) {
			thongbao.thongbao("Chưa chọn sản phẩm !", "");
		} else {
			themSPVaoCTHD();
			modelSP.setRowCount(0);
			lblAnh.setIcon(new ImageIcon());
			txtTimSp.setText("");
			txtTimSp.requestFocus();
			txtSoLuong.setText("1");
		}
	}

	private void btnThanhToanActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (modelCTHD.getRowCount() != 0) {
			hd.setTongTienCanThu(dsCTHD);
			ThanhToan frm = new ThanhToan(hd, dsCTHD);
			frm.setVisible(true);
			setTatCaRong();
		} else {
			thongbao.thongbao("Không thể thanh toán khi chưa có sản phẩm !", "");
		}
	}

	private void btnHuyHDActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (!txtMaHD.getText().equals("")) {
			int nutbam = JOptionPane.showConfirmDialog(new JFrame(),
					"Bạn chắc chắn huy hóa đơn ?", "Hủy",
					JOptionPane.YES_NO_OPTION);
			if (nutbam == JOptionPane.YES_OPTION) {
				setTatCaRong();
				dsCTHD.clear();
			}
			txtSDT.requestFocus();
		} else {
			thongbao.thongbao("Chưa tạo hóa đơn !", "");
		}
	}

	private void btnXoaActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int soRowChon = tableCTHD.getSelectedRowCount();

		if (soRowChon == 1) {
			int row = tableCTHD.getSelectedRow();
			int nutbam = JOptionPane.showConfirmDialog(new JFrame(),
					"Bạn chắc chắn xóa ?", "Xóa", JOptionPane.YES_NO_OPTION);
			if (nutbam == JOptionPane.YES_OPTION) {
				modelCTHD.removeRow(row);
				dsCTHD.remove(row);
				hd.setTongTienCanThu(dsCTHD);
				// Xem tổng tiển có tính đúng không
				System.out.println("-------------------------------------");
				for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
					System.out.println(chiTietHoaDon.getSanPham()
							.getMaSanPham());
					System.out.println(chiTietHoaDon.getSoLuong());
					System.out.println(chiTietHoaDon.getThanhTien());

				}
				System.out.println("-------------------------------------");
				// lblTongTienRong.setText(decimalFormat.format(hd
				// .tinhTongTien(dsCTHD)));
				// lblTongKMRong.setText(decimalFormat.format(hd
				// .tinhTongTienKM(dsCTHD)));
				// lblTongThueRong.setText(decimalFormat.format(hd.tinhTongTienThue(dsCTHD)));
				// lblTongKMTheoBacRong.setText(decimalFormat.format(hd.tinhTongKhuyeMaiTheoBac(dsCTHD)));
				// lblTongCanThuRong.setText(decimalFormat.format(hd.tinhTongTienCanThu(dsCTHD)));
				setTien();
				modelSP.setRowCount(0);
				lblAnh.setIcon(new ImageIcon());
				txtTimSp.setText("");
				txtTimSp.requestFocus();
				txtSoLuong.setText("1");
			}
		} else {
			thongbao.thongbao("Chọn 1 dòng để xóa !", "");
		}

	}

	public static void setTien() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		lblTongTienRong.setText(decimalFormat.format(hd.tinhTongTien(dsCTHD)));
		lblTongKMRong.setText(decimalFormat.format(hd.tinhTongTienKM(dsCTHD)));
		lblTongThueRong.setText(decimalFormat.format(hd
				.tinhTongTienThue(dsCTHD)));
		lblTongKMTheoBacRong.setText(decimalFormat.format(hd
				.tinhTongKhuyeMaiTheoBac(dsCTHD)));
		lblTongCanThuRong.setText(decimalFormat.format(hd.getTongTienCanThu()));
	}

	private void setTatCaRong() {
		txtSDT.setText("");
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtEmail.setText("");
		txtBac.setText("");
		txtSoTienDaMua.setText("");
		txtMaHD.setText("");
		txtTimSp.setText("");
		modelSP.setRowCount(0);
		modelCTHD.setRowCount(0);
		lblAnh.setIcon(new ImageIcon());
		lblTongKMRong.setText("");
		lblTongTienRong.setText("");
		lblTongThueRong.setText("");
		lblTongKMTheoBacRong.setText("");
		lblTongCanThuRong.setText("");
	}

	// Hàm trích xuất số thứ tự từ mã khách hàng
	private static int extractSerialNumber(String idHD) {
		String serialPart = idHD.substring(10).trim(); // Bỏ "KH" + ngày
														// hiện tại
		return Integer.parseInt(serialPart);
	}

	private static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}

	public static String layBacKhachHang(double soTien) {
		if (soTien < 5000000) {
			return "Không có";
		} else if (soTien < 20000000) {
			return "Bạc";
		} else if (soTien < 50000000) {
			return "Vàng";
		} else {
			return "Kim cương";
		}

	}

	private static boolean isNumeric(String text) {
		return text.matches("^[0-9]*$");
	}

	private static SanPham sp;
	public static JLabel lblTongThueRong;
	public static JLabel lblTongCanThuRong;

	private static void laySanPham() {
		String maSP = txtTimSp.getText().trim();
		ResultSet rs = dao.DAO_SanPham.laySPTheoMa(maSP);
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		try {
			if (rs.next()) {
				String tenSP = rs.getString("tenSanPham").trim();
				String kt = rs.getString("kichThuoc").trim();
				String nh = rs.getString("nhanHieu").trim();
				String mau = rs.getString("mau").trim();
				String loai = rs.getString("loai").trim();
				String maNCC = rs.getString("maNhaCungCap").trim();
				String moTa = rs.getString("moTa").trim();
				String cl = rs.getString("chatLieu").trim();
				int soLuong = rs.getInt("soLuong");
				double giaNhap = rs.getDouble("giaNhap");
				String anh = rs.getString("hinhAnh").trim();
				sp = new SanPham();
				String maKM = rs.getString("maKhuyenMai");
				if (maKM != null) {
					int phanTram = dao.DAO_KhuyenMai.layPhanTramTheoMaKM(maKM);
					sp.setKhuyenMai(new KhuyenMai(maKM));
					sp.getKhuyenMai().setPhanTram(phanTram);
				} else {
					sp.setKhuyenMai(null);
				}
				

				sp.setMaSanPham(maSP);
				sp.setTenSanPham(tenSP);
				sp.setLoai(Enum_BangLoaiSanPham.fromString(loai));
				sp.setGiaNhap(giaNhap);
				sp.setNhaCC(new NhaCungCap(maNCC));
				sp.setMau(Enum_Mau.fromString(mau));
				sp.setChatLieu(Enum_ChatLieu.fromString(cl));
				sp.setNhanHieu(Enum_NhanHieu.fromString(nh));
				sp.setHinhAnh(anh);
				sp.setKichThuoc(Enum_KichThuoc.fromString(kt));
				sp.setSoLuong(soLuong);
				sp.setMoTa(moTa);

				modelSP.addRow(new Object[] { maSP, tenSP, kt, nh, mau,
						decimalFormat.format(sp.getGiaBan()) });
				ImageIcon ii = new ImageIcon(new ImageIcon("IMG_SanPham\\"
						+ anh).getImage().getScaledInstance(lblAnh.getWidth(),
						lblAnh.getHeight(), Image.SCALE_SMOOTH));
				lblAnh.setIcon(ii);

				txtSoLuong.requestFocus();
			} else {
				thongbao.thongbao("Không có sản phẩm", "");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int laySoLuongChoTheoMa(String maSP) {
		int soLuongCho = 0;
		for (List<ChiTietHoaDon> listdsCTHD : DanhSachCho.listDanhSachCTHDCho) {
			for (ChiTietHoaDon chiTietHoaDon : listdsCTHD) {
				if (maSP.equals(chiTietHoaDon.getSanPham().getMaSanPham())) {
					System.out.println("sds" + chiTietHoaDon.getSoLuong());
					soLuongCho += chiTietHoaDon.getSoLuong();
				}
			}
		}
		return soLuongCho;
	}

	private void themSPVaoCTHD() {
		int rowC = tableCTHD.getSelectedRowCount();
		if (rowC == 0 || rowC == 1) {
			int soLuong;
			if (txtSoLuong.getText().equals("")
					|| Integer.parseInt(txtSoLuong.getText()) == 0) {
				soLuong = 1;
			} else {
				soLuong = Integer.parseInt(txtSoLuong.getText());
			}

			cthd = new ChiTietHoaDon(sp, hd, soLuong);
			DecimalFormat decimalFormat = new DecimalFormat("#,###");
			int soLuongCho = laySoLuongChoTheoMa(sp.getMaSanPham());
			if (soLuong + soLuongCho > sp.getSoLuong()) {
				thongbao.thongbao("Số lượng sản phẩm không đủ !", "");
			} else {
				int rowCount = modelCTHD.getRowCount();
				int soLuongTrongBang = 0;
				boolean kt = false;
				if (rowCount > 0) {
					for (int i = 0; i < rowCount; i++) {
						String maSP = (String) modelCTHD.getValueAt(i, 0);
						if (sp.getMaSanPham().equals(maSP)) {
							kt = true;
							if (rowC == 0) {
								soLuongTrongBang = (int) modelCTHD.getValueAt(
										i, 2);
								// Lấy số lượng sản phẩm có trong danh sách chờ

								if (soLuong + soLuongTrongBang + soLuongCho > sp
										.getSoLuong()) {
									thongbao.thongbao(
											"Số lượng sản phẩm không đủ !", "");
								} else {
									cthd.setSoLuong(soLuong + soLuongTrongBang);
									cthd.setThanhTien();
									cthd.setKhuyenMai();
									cthd.setTienCuoiCung();

									modelCTHD.setValueAt(cthd.getSoLuong(), i,
											2);
									modelCTHD.setValueAt(decimalFormat
											.format(cthd.getKhuyenMai()), i, 4);
									modelCTHD.setValueAt(decimalFormat
											.format(cthd.getThanhTien()), i, 5);
									for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
										if (chiTietHoaDon.getSanPham()
												.getMaSanPham().equals(maSP)) {
											chiTietHoaDon.setSoLuong(cthd
													.getSoLuong());
											chiTietHoaDon.setThanhTien();
											chiTietHoaDon.setKhuyenMai();
											chiTietHoaDon.setTienCuoiCung();
										}
									}
								}
								break;
							} else {

								cthd.setSoLuong(soLuong);
								cthd.setThanhTien();
								cthd.setKhuyenMai();
								cthd.setTienCuoiCung();

								modelCTHD.setValueAt(cthd.getSoLuong(), i, 2);
								modelCTHD.setValueAt(decimalFormat.format(cthd
										.getKhuyenMai()), i, 4);
								modelCTHD.setValueAt(decimalFormat.format(cthd
										.getThanhTien()), i, 5);
								for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
									if (chiTietHoaDon.getSanPham()
											.getMaSanPham().equals(maSP)) {
										chiTietHoaDon.setSoLuong(cthd
												.getSoLuong());
										chiTietHoaDon.setThanhTien();
										chiTietHoaDon.setKhuyenMai();
										chiTietHoaDon.setTienCuoiCung();

									}
								}
								break;
							}
						}
					}
					if (!kt) {
						cthd.setSoLuong(soLuong);
						cthd.setThanhTien();
						cthd.setKhuyenMai();
						cthd.setTienCuoiCung();
						modelCTHD.addRow(new Object[] { sp.getMaSanPham(),
								sp.getTenSanPham(), soLuong,
								decimalFormat.format(sp.getGiaBan()),
								decimalFormat.format(cthd.getKhuyenMai()),
								decimalFormat.format(cthd.getThanhTien()) });
						dsCTHD.add(cthd);
					}
				} else {
					cthd.setSoLuong(soLuong);
					cthd.setThanhTien();
					cthd.setKhuyenMai();
					cthd.setTienCuoiCung();
					modelCTHD.addRow(new Object[] { sp.getMaSanPham(),
							sp.getTenSanPham(), soLuong,
							decimalFormat.format(sp.getGiaBan()),
							decimalFormat.format(cthd.getKhuyenMai()),
							decimalFormat.format(cthd.getThanhTien()) });
					dsCTHD.add(cthd);
				}

			}
			hd.setTongTienCanThu(dsCTHD);

			// Xem tổng tiển có tính đúng không
			System.out.println("-------------------------------------");
			for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
				System.out.println(chiTietHoaDon.getSanPham().getMaSanPham());
				System.out.println(chiTietHoaDon.getSoLuong());
				System.out.println(chiTietHoaDon.getThanhTien());

			}
			System.out.println("-------------------------------------");
			setTien();
		} else {
			thongbao.thongbao("Chỉ chọn 1 dòng để sửa !", "");
		}

		tableCTHD.clearSelection();

	}

	public static void ganCTHDVaoTable() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		for (ChiTietHoaDon cthd : dsCTHD) {
			modelCTHD.addRow(new Object[] { cthd.getSanPham().getMaSanPham(),
					cthd.getSanPham().getTenSanPham(), cthd.getSoLuong(),
					decimalFormat.format(cthd.getSanPham().getGiaBan()),
					decimalFormat.format(cthd.getKhuyenMai()),
					decimalFormat.format(cthd.getThanhTien()) });
		}
	}

	public static void layThongTin() {
		String sdt = txtSDT.getText();
		ResultSet rs = dao.DAO_KhachHang.layKHTheoSDT(sdt);
		try {
			if (rs.next()) {
				DecimalFormat decimalFormat = new DecimalFormat("#,###");
				String maKH = rs.getString("maKhachHang");
				String tenKH = rs.getString("tenKhachHang");
				String emailKH = rs.getString("email");
				Double soTienKH = rs.getDouble("soTienDaMua");
				String bacKH = layBacKhachHang(soTienKH);
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				System.err.println(maKH);// Kiểm trả có khách hàng không

				kh = new KhachHang(maKH, tenKH, sdt, emailKH, gioiTinh,
						soTienKH);

				txtMaKH.setText(maKH);
				txtTenKH.setText(tenKH);
				txtEmail.setText(emailKH);
				txtBac.setText(bacKH);
				txtSoTienDaMua.setText(decimalFormat.format(soTienKH));
			} else {
				thongbao.thongbao("Chưa có khách hàng này !", "");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
	}
}
