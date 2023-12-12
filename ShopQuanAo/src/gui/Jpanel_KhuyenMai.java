package gui;

import javax.swing.JPanel;

import java.awt.Checkbox;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
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
import javax.swing.JComboBox;

import bus.BUS_KhuyenMai;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectionManager;
import entity.ChiTietHoaDon;
import entity.Enum_BangLoaiSanPham;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.SanPham;
import connectDB.ConnectionManager;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_KhuyenMai;
import dao.DAO_SanPham;

public class Jpanel_KhuyenMai extends JPanel implements ActionListener {
	private JTextField txtMaKM;
	private JTextField txtTenKM;
	private JTextField txtPhanTram;
	private DefaultTableModel modelSP;
	private JTable tableSP;
	private JTableHeader tbHeaderSP;
	private JScrollPane scrollPaneSP;
	private JTextField txtTim;
	private JTextField txtTimKhuyenMai;
	private JPanel pnlLeft;
	private JLabel lblKM;
	private JLabel lblMaKM;
	private JLabel lblTenKM;
	private JLabel lblPhanTram;
	private JLabel lblNgayBD;
	private JLabel lblNgayKT;
	private JLabel lblMoTa;
	private JTextArea txtMota;
	private JButton btnTaoKM;
	private JPanel pnlRight_Bot;
	private JLabel lblSanPham;
	private JPanel pnlRight_Top;
	private JLabel lblLoai;
	private JComboBox cboLoai;
	private JLabel lblTen;
	private DefaultTableModel modelKM;
	private JTable tableKM;
	private JTableHeader tbHeaderKM;
	private JScrollPane scrollPaneKM;
	private JDateChooser txtNgayBD;
	private JDateChooser txtNgayKetThuc;
	ConnectionManager connectionManager = new ConnectionManager();
	private AbstractDocument document;
	private JButton btnReset;
	private JButton btnTimKM;
	private JDateChooser txtTimNgayBD;
	private JDateChooser txtTimNgayKT;
	public static SanPham sanPham = new SanPham();
	public static KhuyenMai khuyenMai = new KhuyenMai();

	/**
	 * Create the panel.
	 */
	public Jpanel_KhuyenMai() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		pnlLeft = new JPanel();
		pnlLeft.setBackground(new Color(255, 255, 237));
		pnlLeft.setBorder(new MatteBorder(0, 1, 0, 1,
				(Color) new Color(0, 0, 0)));
		pnlLeft.setBounds(0, 0, 805, 975);
		add(pnlLeft);
		pnlLeft.setLayout(null);

		lblKM = new JLabel("KHUYẾN MÃI");
		lblKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKM.setHorizontalAlignment(SwingConstants.CENTER);
		lblKM.setBounds(310, 25, 172, 36);
		pnlLeft.add(lblKM);

		lblMaKM = new JLabel("Mã khuyến mãi:");
		lblMaKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKM.setBounds(12, 100, 177, 35);
		pnlLeft.add(lblMaKM);

		lblTenKM = new JLabel("Tên khuyến mãi:");
		lblTenKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenKM.setBounds(12, 165, 177, 35);
		pnlLeft.add(lblTenKM);

		lblPhanTram = new JLabel("Phần trăm:");
		lblPhanTram.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhanTram.setBounds(435, 165, 120, 35);
		pnlLeft.add(lblPhanTram);

		lblNgayBD = new JLabel("Ngày bắt đầu:");
		lblNgayBD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgayBD.setBounds(12, 215, 177, 35);
		pnlLeft.add(lblNgayBD);
		txtNgayBD = new JDateChooser();
		txtNgayBD.setDateFormatString("dd/MM/yyyy");
		txtNgayBD.setBounds(196, 215, 200, 35);
		Calendar currentDate = Calendar.getInstance();
		txtNgayBD.setDate(currentDate.getTime());
		txtNgayBD.setEnabled(false);
		pnlLeft.add(txtNgayBD);

		lblNgayKT = new JLabel("Ngày kết thúc:");
		lblNgayKT.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgayKT.setBounds(435, 215, 144, 35);
		pnlLeft.add(lblNgayKT);
		txtNgayKetThuc = new JDateChooser();
		txtNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		txtNgayKetThuc.setBounds(583, 215, 200, 35);
		txtNgayKetThuc.setMinSelectableDate(new Date());
		pnlLeft.add(txtNgayKetThuc);

		lblMoTa = new JLabel("Mô tả:");
		lblMoTa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMoTa.setBounds(12, 276, 177, 35);
		pnlLeft.add(lblMoTa);

		txtMaKM = new JTextField();
		txtMaKM.setBackground(new Color(255, 255, 237));
		txtMaKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaKM.setBounds(196, 100, 200, 35);
		txtMaKM.setBorder(null);
		pnlLeft.add(txtMaKM);
		txtMaKM.setColumns(10);

		txtTenKM = new JTextField();
		txtTenKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTenKM.setColumns(10);
		txtTenKM.setBounds(196, 166, 200, 35);
		pnlLeft.add(txtTenKM);

		txtPhanTram = new JTextField();
		txtPhanTram.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPhanTram.setColumns(10);
		txtPhanTram.setBounds(583, 166, 90, 35);
		pnlLeft.add(txtPhanTram);
		JLabel lbl_PhanTram = new JLabel("%");
		lbl_PhanTram.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_PhanTram.setBounds(685, 165, 29, 36);
		pnlLeft.add(lbl_PhanTram);

		document = (AbstractDocument) txtPhanTram.getDocument();
		document.setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int length,
					String text, AttributeSet attrs)
					throws BadLocationException {
				if (isNumeric(text)
						&& (fb.getDocument().getLength() + text.length() - length) <= 2) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});

		txtMota = new JTextArea();
		txtMota.setBackground(new Color(237, 255, 241));
		txtMota.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtMota.setBounds(196, 271, 277, 98);
		pnlLeft.add(txtMota);

		btnReset = new JButton("");
		btnReset.setIcon(new ImageIcon("IMG_Hinh\\reset.jpg"));
		btnReset.setBackground(new Color(0, 191, 255));
		btnReset.setBackground(new Color(0, 0, 255));
		btnReset.setBounds(527, 324, 50, 45);
		pnlLeft.add(btnReset);

		btnTaoKM = new JButton("Tạo khuyến mãi");
		btnTaoKM.setBackground(Color.GREEN);
		btnTaoKM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTaoKM.setBounds(589, 324, 172, 45);
		pnlLeft.add(btnTaoKM);

		String rowKM[] = { "Mã khuyến mãi", "Tên khuyến mãi", "Phần trăm",
				"Ngày BĐ", "Ngày KT" };
		modelKM = new DefaultTableModel(rowKM, 0);
		tableKM = new JTable(modelKM);
		tableKM.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				153)));
		tbHeaderKM = ((JTable) tableKM).getTableHeader();
		tbHeaderKM.setBackground((Color.decode("#00c691")));
		tbHeaderKM.setForeground(Color.white);
		tbHeaderKM.setFont(new Font("Tahoma", Font.BOLD, 10));
		scrollPaneKM = new JScrollPane(tableKM);
		scrollPaneKM.setBorder(null);
		scrollPaneKM.setLocation(13, 453);
		scrollPaneKM.setSize(770, 522);
		pnlLeft.add(scrollPaneKM);
		bus.BUS_KhuyenMai.dodulieu(tableKM);

		// Chỉnh chiều rộng

		tableKM.getColumnModel().getColumn(0).setPreferredWidth(150); // Mã
																		// khuyến
																		// mãi
		tableKM.getColumnModel().getColumn(1).setPreferredWidth(200); // Tên
																		// khuyến
																		// mãi
		// Cài đặt kích thước cho các cột khác

		tableKM.setRowHeight(30);
		JTableHeader tableHeader = tableKM.getTableHeader();
		tbHeaderKM.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableKM.setFont(new Font("Tohoma", Font.PLAIN, 18));

		pnlRight_Bot = new JPanel();
		pnlRight_Bot.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0,
				0, 0)));
		pnlRight_Bot.setBackground(new Color(255, 255, 237));
		pnlRight_Bot.setBounds(805, 77, 859, 898);
		add(pnlRight_Bot);
		pnlRight_Bot.setLayout(null);

		String rowSP[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Loại",
				"Giá bán" };
		modelSP = new DefaultTableModel(rowSP, 0);
		tableSP = new JTable(modelSP);
		tableSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				153)));
		tbHeaderSP = ((JTable) tableSP).getTableHeader();
		tbHeaderSP.setBackground((Color.decode("#00c691")));
		tbHeaderSP.setForeground(Color.white);
		tbHeaderSP.setFont(new Font("Tahoma", Font.BOLD, 10));
		scrollPaneSP = new JScrollPane(tableSP);
		scrollPaneSP.setBorder(null);
		scrollPaneSP.setLocation(26, 51);
		scrollPaneSP.setSize(809, 847);
		pnlRight_Bot.add(scrollPaneSP);
		bus.BUS_KhuyenMai
				.dodulieu_SanPham(
						tableSP,
						DAO_SanPham
								.getDsSanPham_Querry("select * from SanPham  where SanPham.maKhuyenMai  is null"));

		tableSP.setRowHeight(30);
		JTableHeader tableHeader1 = tableSP.getTableHeader();
		tableHeader1.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableSP.setFont(new Font("Tohoma", Font.PLAIN, 18));

		TableColumnModel columnModel = tableSP.getColumnModel();
		TableColumn giaBan = columnModel.getColumn(4);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		giaBan.setCellRenderer(rightRenderer);

		lblSanPham = new JLabel("SẢN PHẨM");
		lblSanPham.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSanPham.setBounds(28, 13, 172, 36);
		pnlRight_Bot.add(lblSanPham);

		pnlRight_Top = new JPanel();
		pnlRight_Top.setBackground(new Color(255, 255, 237));
		pnlRight_Top.setBounds(805, 0, 859, 77);
		add(pnlRight_Top);
		pnlRight_Top.setLayout(null);

		lblLoai = new JLabel("Loại:");
		lblLoai.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLoai.setBounds(26, 25, 56, 35);
		pnlRight_Top.add(lblLoai);

		cboLoai = new JComboBox();
		cboLoai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cboLoai.setBounds(80, 25, 100, 35);
		cboLoai.setBackground(new Color(240, 255, 241));
		cboLoai.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"Áo", "Quần", "Váy", "Đầm" }));
		pnlRight_Top.add(cboLoai);

		lblTen = new JLabel("Tìm sản phẩm:");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTen.setBounds(450, 25, 140, 35);
		pnlRight_Top.add(lblTen);

		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTim.setBounds(590, 25, 189, 35);
		txtTim.setColumns(10);
		pnlRight_Top.add(txtTim);

		txtMaKM.setEnabled(false);
		btnTaoKM.setFocusPainted(false);
		btnReset.setFocusPainted(false);

		JLabel lblTimKhuyenMai = new JLabel("Tìm:");
		lblTimKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTimKhuyenMai.setBounds(30, 414, 50, 26);
		pnlLeft.add(lblTimKhuyenMai);

		txtTimKhuyenMai = new JTextField();
		txtTimKhuyenMai.setColumns(10);
		txtTimKhuyenMai.setBounds(79, 414, 189, 26);
		pnlLeft.add(txtTimKhuyenMai);

		JLabel lblTu = new JLabel("Từ");
		lblTu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTu.setBounds(318, 409, 29, 36);
		pnlLeft.add(lblTu);

		txtTimNgayBD = new JDateChooser();
		txtTimNgayBD.setDateFormatString("dd/MM/yyyy");
		txtTimNgayBD.setBounds(345, 414, 150, 26);
		pnlLeft.add(txtTimNgayBD);

		txtTimNgayKT = new JDateChooser();
		txtTimNgayKT.setDateFormatString("dd/MM/yyyy");
		txtTimNgayKT.setBounds(543, 414, 150, 26);
		pnlLeft.add(txtTimNgayKT);
		txtTimNgayBD.setDate(new java.util.Date());
		txtTimNgayKT.setDate(new java.util.Date());
		JLabel lblDen = new JLabel("Đến");
		lblDen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDen.setBounds(507, 414, 50, 26);
		pnlLeft.add(lblDen);

		btnTimKM = new JButton("Tìm");
		btnTimKM.setBackground(new Color(204, 255, 255));
		btnTimKM.setFocusPainted(false);
		btnReset.setBackground(new Color(0, 191, 255));
		btnReset.setBackground(new Color(0, 0, 255));
		btnTimKM.setBounds(705, 414, 67, 26);
		btnTimKM.setContentAreaFilled(false);
		pnlLeft.add(btnTimKM);

		tableKM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tableKM.getSelectedRow();
				String maKM = tableKM.getValueAt(row, 0).toString().trim();
				thongTinChiTiet(maKM);
				hienThiSanPham(maKM);

			}
		});

		btnReset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLamMoiActionPerformed(evt);
			}
		});
		cboLoai.addActionListener(this);
		txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtTimKiemKeyReleased(evt);
			}
		});
		txtTimKhuyenMai.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtTimKiemKMKeyReleased(evt);
			}
		});

		btnTimKM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Gọi hàm để thực hiện tìm kiếm khuyến mãi
				timKiemKhuyenMai();
			}
		});

		btnTaoKM.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String pt= txtPhanTram.getText();
				if(pt.equals(""))
					thongbao.thongbao("Yêu cầu nhập phần trăm", "Chú ý");
				
				else {
					
					try {
					btnTaoKMActionPerformed(evt);
					kiemTraVaCapNhatSanPham();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
			}

			private void ganThongTin() {
				ResultSet rs = dao.DAO_KhuyenMai.layKM();
				String maxMaKM = null;
				String mota = txtMaKM.getText().trim();
				try {
					while (rs.next()) {
						maxMaKM = rs.getString("maKhuyenMai");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String newMaKM = "";
				String currentDate = getCurrentDate(); // Ngày hiện tại
				if (maxMaKM != null && maxMaKM.startsWith("KM" + currentDate)) {
					int maxSoKM = extractSerialNumber(maxMaKM);
					newMaKM = "KM" + currentDate
							+ String.format("%02d", maxSoKM + 1);
				} else {
					newMaKM = "KM" + currentDate + "01";
				}
				txtMaKM.setText(newMaKM);
				khuyenMai.setMoTa(mota);
				khuyenMai.setMaKhuyenMai(newMaKM);
				khuyenMai.setTenKhuyenMai(txtTenKM.getText().toString());
				khuyenMai.setPhanTram(Integer.parseInt(txtPhanTram.getText()
						.toString()));
				LocalDate ngayBD = txtNgayBD.getDate().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();
				khuyenMai.setNgayBatDau(ngayBD);

				LocalDate ngayKT = txtNgayKetThuc.getDate().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();
				khuyenMai.setNgayKetThuc(ngayKT);
			}

			private void btnTaoKMActionPerformed(ActionEvent evt)
					throws SQLException {
				// Lấy ngày hiện tại
				Date currentDate = new Date();

				// Lấy ngày bắt đầu và ngày kết thúc từ JDateChooser
				Date ngayBD = txtNgayBD.getDate();
				Date ngayKT = txtNgayKetThuc.getDate();

				// Định dạng lại ngày và giờ để hiển thị
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm:ss");
				String ngayBDBienDang = dateFormat.format(ngayBD);
				try {
						String ngayKTBienDang = dateFormat.format(ngayKT);
				} catch (Exception e) {
					// TODO: handle exception
					thongbao.thongbao("Yêu cầu Nhập đúng định dạng ngày dd/mm/yyyy", "Chú ý ");
					return;
				}
			

				if (ngayBD.after(ngayKT)) {
					ngayBD = currentDate;
					txtNgayBD.setDate(currentDate);
				}
				int[] selectedRows = tableSP.getSelectedRows();
				List<String> selectedProductIDs = new ArrayList<>();
				for (int row : selectedRows) {
					String productID = tableSP.getValueAt(row, 1).toString(); 
					selectedProductIDs.add(productID);
				}
				if(selectedProductIDs.size()==0) {
					thongbao.thongbao("Chọn sản phẩm khuyến mãi", "chú ý");
					return;
				}
				ganThongTin();
				boolean kt = bus.BUS_KhuyenMai.kt_Them(khuyenMai);
				if (kt) {
					dao.DAO_KhuyenMai.them(khuyenMai);

					bus.BUS_KhuyenMai.dodulieu(tableKM);
				}
			
					
				
				for (String productID : selectedProductIDs) {
					// Thay thế "productTable" bằng tên bảng sản phẩm trong cơ
					// sở dữ liệu của bạn
					String updateQuery = "UPDATE SanPham SET maKhuyenMai = ? WHERE maSanPham = ?";
					try {
						ConnectionManager connectionManager = new ConnectionManager(); 
						Connection conn = connectionManager.conn;
						PreparedStatement preparedStatement = conn
								.prepareStatement(updateQuery);
						preparedStatement.setString(1, txtMaKM.getText());
						preparedStatement.setString(2, productID);
						preparedStatement.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				bus.BUS_KhuyenMai.dodulieu(tableKM);
				bus.BUS_KhuyenMai.dodulieu_SanPham(
						tableSP,
						DAO_SanPham
								.getDsSanPham_Querry("select * from SanPham  where SanPham.maKhuyenMai  is null"));

				kiemTraVaCapNhatSanPham();
			}
		});

	}

	private int extractSerialNumber(String customerID) {
		String serialPart = customerID.substring(10).trim();
		return Integer.parseInt(serialPart);
	}

	private String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}

	private void txtTimKiemKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		String loai = cboLoai.getSelectedItem().toString();
		String tuKhoa = txtTim.getText().trim();
		// bus.BUS_KhachHang.timKiem(table, tuKhoa, bac, gioiTinh);
		bus.BUS_SanPham.timKiem(tableSP, tuKhoa, loai);
	}

	private void txtTimKiemKMKeyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		String phanTram = txtPhanTram.getText().trim(); // nếu đưa int vào thì
														// khi nhập vào sẽ bị
														// lỗi String ""

		// Lấy ngày bắt đầu và kết thúc từ JDateChooser
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date ngayBD = txtTimNgayBD.getDate();
		Date ngayKT = txtTimNgayKT.getDate();
		java.sql.Date sqlNgayBD = null;
		java.sql.Date sqlNgayKT = null;
		// Chuyển đổi từ java.util.Date sang java.sql.Date để sử dụng trong câu
		// truy vấn SQL
		if (!(ngayBD == null || ngayKT == null || ngayKT.before(ngayBD))) {
			sqlNgayBD = new java.sql.Date(ngayBD.getTime());
			sqlNgayKT = new java.sql.Date(ngayKT.getTime());
		}

		String tuKhoa = txtTimKhuyenMai.getText().trim();
		// bus.BUS_KhuyenMai.timKiem(table2, tuKhoa, phanTram);
		bus.BUS_KhuyenMai.timKiem(tableKM, tuKhoa, phanTram, sqlNgayBD,
				sqlNgayKT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Tìm kiếm
		String loai = cboLoai.getSelectedItem().toString();
		String tuKhoa = txtTim.getText().trim();
		Object o = e.getSource();
		if (o.equals(cboLoai)) {
			bus.BUS_SanPham.timKiem(tableSP, tuKhoa, loai);
		}
	}

	public static int vitri;

	// public static String maKM;

	private void tableMouseClicked(MouseEvent evt) {

		if (tableKM.getSelectedRowCount() == 1) {
			vitri = tableKM.getSelectedRow();
			String maKM = tableKM.getValueAt(vitri, 0).toString();
			thongTinChiTiet(maKM);
			// hienThiSanPhamTheoKhuyenMai(maKM);
		}

	}

	private void thongTinChiTiet(String ma) {

		KhuyenMai km = DAO_KhuyenMai.layKhuyenMaiTheoMa(ma);
		txtMaKM.setText(km.getMaKhuyenMai().trim());
		txtTenKM.setText(km.getTenKhuyenMai().trim());
		txtPhanTram.setText(km.getPhanTram() + "");
		txtMota.setText(km.getMoTa());
		Date datebd = Date.from(km.getNgayBatDau()
				.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date datekt = Date.from(km.getNgayKetThuc()
				.atStartOfDay(ZoneId.systemDefault()).toInstant());
		txtNgayBD.setDate(datebd);
		txtNgayKetThuc.setDate(datekt);

	}

	private static boolean isNumeric(String text) {
		return text.matches("^[0-9]*$");
	}

	private void kiemTraVaCapNhatSanPham() throws SQLException {
		// Kiểm tra kết nối trước khi sử dụng
		if (connectionManager == null || connectionManager.conn == null
				|| connectionManager.conn.isClosed()) {
			connectionManager = new ConnectionManager(); // Mở kết nối mới nếu
															// cần
		}

		Connection conn = connectionManager.conn;
		String query = "SELECT maKhuyenMai FROM KhuyenMai WHERE ngayKetThuc >= CONVERT(DATE, GETDATE()) AND ngayKetThuc < DATEADD(DAY, 1, CONVERT(DATE, GETDATE()));";
		// String query =
		// "SELECT maKhuyenMai FROM KhuyenMai WHERE ngayKetThuc > CONVERT(DATE, GETDATE())";

		try (PreparedStatement statement = conn.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			// Duyệt qua danh sách mã khuyến mãi hết hạn
			while (resultSet.next()) {
				String maKhuyenMai = resultSet.getString("maKhuyenMai");

				// Cập nhật trường maKhuyenMai của các sản phẩm liên quan thành
				// null
				String updateQuery = "UPDATE SanPham SET maKhuyenMai = NULL WHERE maKhuyenMai = ?";

				try (PreparedStatement updateStatement = conn
						.prepareStatement(updateQuery)) {
					updateStatement.setString(1, maKhuyenMai);
					updateStatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void btnLamMoiActionPerformed(ActionEvent evt) {
		txtMaKM.setText("");
		txtTenKM.setText("");
		txtPhanTram.setText("");
		txtNgayBD.setDate(new java.util.Date());
		txtNgayKetThuc.setDate(new java.util.Date());
		txtTenKM.requestFocus();
		txtTimKhuyenMai.setText("");
		txtTimNgayBD.setDate(new java.util.Date());
		txtTimNgayKT.setDate(new java.util.Date());
		bus.BUS_KhuyenMai.dodulieu(tableKM);
		bus.BUS_KhuyenMai
				.dodulieu_SanPham(
						tableSP,
						DAO_SanPham
								.getDsSanPham_Querry("select * from SanPham  where SanPham.maKhuyenMai  is null"));
		// ganTXTMaKM();

	}

	private void ganTXTMaKM() {
		ResultSet rs = dao.DAO_KhuyenMai.layKM();
		String currentDate = getCurrentDate(); // Ngày hiện tại
		String maxMaKM = "KM" + currentDate + "00";
		try {
			while (rs.next()) {
				maxMaKM = rs.getString("maKhuyenMai");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String newMaKM = "";

		if (maxMaKM != null && maxMaKM.startsWith("KM" + currentDate)) {
			int maxSoKM = extractSerialNumber(maxMaKM);
			newMaKM = "KM" + currentDate + String.format("%02d", maxSoKM + 1);
		} else {
			newMaKM = "KM" + currentDate + "01";
		}
		txtMaKM.setText(newMaKM);
	}

	private void hienThiSanPham(String maKhuyenMai) {
		String sql = "select * from SanPham \n where SanPham.maKhuyenMai= '"
				+ maKhuyenMai + "'";
		List<SanPham> dsSP = DAO_SanPham.getDsSanPham_Querry(sql);
		BUS_KhuyenMai.dodulieu_SanPham(tableSP, dsSP);
	}

	private boolean validateNgayTimKiem() {
		Date ngayBD = txtTimNgayBD.getDate();
		Date ngayKT = txtTimNgayKT.getDate();

		if (ngayBD == null || ngayKT == null) {
			thongbao.thongbao(
					"Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.", "");
			return false;
		}

		if (ngayBD.after(ngayKT)) {

			thongbao.thongbao("Ngày kết thúc không thể trước ngày bắt đầu.", "");
			return false;
		}

		return true;
	}

	private void timKiemKhuyenMai() {
		Connection conn = connectionManager.conn;
		if (!validateNgayTimKiem()) {
			return; // Dừng nếu ngày không hợp lệ
		}
		// Lấy ngày bắt đầu và kết thúc từ JDateChooser
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date ngayBD = txtTimNgayBD.getDate();
		Date ngayKT = txtTimNgayKT.getDate();

		// Chuyển đổi từ java.util.Date sang java.sql.Date để sử dụng trong câu
		// truy vấn SQL
		java.sql.Date sqlNgayBD = new java.sql.Date(ngayBD.getTime());
		java.sql.Date sqlNgayKT = new java.sql.Date(ngayKT.getTime());
		String tuKhoa = txtTimKhuyenMai.getText().trim();
		// Thực hiện truy vấn SQL để lấy danh sách khuyến mãi dựa trên ngày bắt
		// đầu và kết thúc
		String sql = "SELECT * FROM KhuyenMai WHERE (ngayBatDau >= ? AND ngayKetThuc <= ?) and (tenKhuyenMai LIKE N'%"
				+ tuKhoa + "%' OR maKhuyenMai LIKE N'%" + tuKhoa + "%')";
		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			preparedStatement.setDate(1, sqlNgayBD);
			preparedStatement.setDate(2, sqlNgayKT);

			ResultSet resultSet = preparedStatement.executeQuery();

			// Xóa dữ liệu cũ trong table2
			modelKM.setRowCount(0);

			// Đổ dữ liệu mới vào table2
			Object obj[] = new Object[5];
			while (resultSet.next()) {
				obj[0] = resultSet.getString("maKhuyenMai");
				obj[1] = resultSet.getString("tenKhuyenMai");
				obj[2] = resultSet.getInt("phanTram");
				ngayBD = resultSet.getDate("ngayBatDau");
				obj[3] = sdf.format(ngayBD);
				ngayKT = resultSet.getDate("ngayKetThuc");
				obj[4] = sdf.format(ngayKT);
				modelKM.addRow(obj);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
