package gui;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JComboBox;

import bus.BUS_KhuyenMai;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectionManager;
import entity.Enum_BangLoaiSanPham;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.SanPham;
import connectDB.ConnectionManager;
import dao.DAO_KhuyenMai;

public class Jpanel_KhuyenMai extends JPanel implements ActionListener {
	private JTextField txtMaKM;
	private JTextField txtTenKM;
	private JTextField txtPhanTram;
	private DefaultTableModel model1;
	private JTable table1;
	private JTableHeader tbHeader1;
	private JScrollPane scrollPane1;
	private JTextField txtTim;
	private JTextField txtTimKhuyenMai;
	private JPanel panel;
	private JLabel lblKM;
	private JLabel lblMaKM;
	private JLabel lblTenKM;
	private JLabel lblPhanTram;
	private JLabel lblNgayBD;
	private JLabel lblNgayKT;
	private JLabel lblMoTa;
	private JTextArea txtMota;
	private JButton btnTaoKM;
	private JPanel panel_1;
	private JLabel lblSanPham;
	private JPanel panel_2;
	private JLabel lblLoai;
	private JComboBox cboLoai;
	private JLabel lblTen;
	private DefaultTableModel model2;
	private JTable table2;
	private JTableHeader tbHeader2;
	private JScrollPane scrollPane2;
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

		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 805, 975);
		add(panel);
		panel.setLayout(null);

		lblKM = new JLabel("KHUYẾN MÃI");
		lblKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKM.setHorizontalAlignment(SwingConstants.CENTER);
		lblKM.setBounds(381, 25, 172, 36);
		panel.add(lblKM);

		lblMaKM = new JLabel("Mã khuyến mãi:");
		lblMaKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKM.setBounds(12, 100, 177, 36);
		panel.add(lblMaKM);

		lblTenKM = new JLabel("Tên khuyến mãi:");
		lblTenKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenKM.setBounds(12, 165, 177, 36);
		panel.add(lblTenKM);

		lblPhanTram = new JLabel("Phần trăm");
		lblPhanTram.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhanTram.setBounds(435, 165, 90, 36);
		panel.add(lblPhanTram);

		lblNgayBD = new JLabel("Ngày bắt đầu:");
		lblNgayBD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayBD.setBounds(12, 215, 177, 36);
		panel.add(lblNgayBD);
		txtNgayBD = new JDateChooser();
		txtNgayBD.setDateFormatString("dd/MM/yyyy");
		txtNgayBD.setBounds(196, 215, 200, 36);
//		txtNgayBD.setMinSelectableDate(new Date());
		 Calendar currentDate = Calendar.getInstance();
		txtNgayBD.setDate(currentDate.getTime());
		txtNgayBD.setEnabled(false);
		panel.add(txtNgayBD);

		lblNgayKT = new JLabel("Ngày kết thúc:");
		lblNgayKT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayKT.setBounds(435, 215, 177, 36);
		panel.add(lblNgayKT);
		txtNgayKetThuc = new JDateChooser();
		txtNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		txtNgayKetThuc.setBounds(583, 215, 200, 36);
		txtNgayKetThuc.setMinSelectableDate(new Date());
		panel.add(txtNgayKetThuc);

		lblMoTa = new JLabel("Mô tả:");
		lblMoTa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMoTa.setBounds(12, 276, 177, 36);
		panel.add(lblMoTa);

		txtMaKM = new JTextField();
		txtMaKM.setBounds(196, 100, 200, 36);
		panel.add(txtMaKM);
		txtMaKM.setColumns(10);

		txtTenKM = new JTextField();
		txtTenKM.setColumns(10);
		txtTenKM.setBounds(196, 166, 200, 36);
		panel.add(txtTenKM);

		txtPhanTram = new JTextField();
		txtPhanTram.setColumns(10);
		txtPhanTram.setBounds(583, 166, 90, 36);
		panel.add(txtPhanTram);
		JLabel lbl_PhanTram = new JLabel("%");
		lbl_PhanTram.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_PhanTram.setBounds(685, 165, 29, 36);
		panel.add(lbl_PhanTram);

		// Chỉ cho nhập số ở txtPhanTram
		// document = (AbstractDocument) txtPhanTram.getDocument();
		// document.setDocumentFilter(new DocumentFilter() {
		// @Override
		// public void replace(FilterBypass fb, int offset, int length,
		// String text, AttributeSet attrs)
		// throws BadLocationException {
		// if (isNumeric(text) && text.length() <= 2) {
		// super.replace(fb, offset, length, text, attrs);
		// }
		// }
		// });
		// Chỉ cho nhập số (tối đa 2 ký tự) ở txtPhanTram
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
		txtMota.setBounds(196, 271, 277, 98);
		panel.add(txtMota);

		btnReset = new JButton("");
		btnReset.setIcon(new ImageIcon("IMG_Hinh\\reset.jpg"));
		btnReset.setBackground(new Color(0, 191, 255));
		btnReset.setBackground(new Color(0, 0, 255));
		btnReset.setBounds(527, 324, 50, 45);
		panel.add(btnReset);

		btnTaoKM = new JButton("Tạo khuyến mãi");
		btnTaoKM.setBackground(Color.GREEN);
		btnTaoKM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTaoKM.setBounds(589, 320, 172, 49);
		panel.add(btnTaoKM);

		String row2[] = { "Mã khuyến mãi", "Tên khuyến mãi", "Phần trăm",
				"Ngày BĐ", "Ngày KT" };
		model2 = new DefaultTableModel(row2, 0);
		table2 = new JTable(model2);
		table2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				153)));
		tbHeader2 = ((JTable) table2).getTableHeader();
		tbHeader2.setBackground((Color.decode("#00c691")));
		tbHeader2.setForeground(Color.white);
		tbHeader2.setFont(new Font("Tahoma", Font.BOLD, 10));
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBorder(null);
		scrollPane2.setLocation(13, 453);
		scrollPane2.setSize(770, 522);
		panel.add(scrollPane2);
		bus.BUS_KhuyenMai.dodulieu(table2);

		// Chỉnh chiều rộng

		table2.getColumnModel().getColumn(0).setPreferredWidth(150); // Mã
																		// khuyến
																		// mãi
		table2.getColumnModel().getColumn(1).setPreferredWidth(200); // Tên
																		// khuyến
																		// mãi
		// Cài đặt kích thước cho các cột khác

		table2.setRowHeight(30);
		JTableHeader tableHeader = table2.getTableHeader();
		tbHeader2.setFont(new Font("Tohoma", Font.BOLD, 18));
		table2.setFont(new Font("Tohoma", Font.PLAIN, 18));

		panel_1 = new JPanel();
		panel_1.setBounds(805, 77, 859, 898);
		add(panel_1);
		panel_1.setLayout(null);

		String row1[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Loại",
				"Giá bán" };
		model1 = new DefaultTableModel(row1, 0);
		table1 = new JTable(model1);
		table1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				153)));
		tbHeader1 = ((JTable) table1).getTableHeader();
		tbHeader1.setBackground((Color.decode("#00c691")));
		tbHeader1.setForeground(Color.white);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 10));
		scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBorder(null);
		scrollPane1.setLocation(26, 51);
		scrollPane1.setSize(809, 847);
		panel_1.add(scrollPane1);
		bus.BUS_SanPham.dodulieu(table1);

		table1.setRowHeight(30);
		JTableHeader tableHeader1 = table1.getTableHeader();
		tableHeader1.setFont(new Font("Tohoma", Font.BOLD, 18));
		table1.setFont(new Font("Tohoma", Font.PLAIN, 18));

		lblSanPham = new JLabel("SẢN PHẨM");
		lblSanPham.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSanPham.setBounds(28, 13, 172, 36);
		panel_1.add(lblSanPham);

		panel_2 = new JPanel();
		panel_2.setBounds(805, 0, 859, 77);
		add(panel_2);
		panel_2.setLayout(null);

		lblLoai = new JLabel("Loại:");
		lblLoai.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoai.setBounds(12, 30, 56, 26);
		panel_2.add(lblLoai);

		cboLoai = new JComboBox();
		cboLoai.setBounds(80, 30, 100, 26);
		cboLoai.setBackground(new Color(255, 128, 64));
		cboLoai.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"Áo", "Quần", "Váy", "Đầm" }));
		panel_2.add(cboLoai);

		lblTen = new JLabel("Tìm sản phẩm:");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTen.setBounds(460, 30, 130, 26);
		panel_2.add(lblTen);

		txtTim = new JTextField();
		txtTim.setBounds(590, 31, 189, 26);
		txtTim.setColumns(10);
		panel_2.add(txtTim);

		txtMaKM.setEnabled(false);
		btnTaoKM.setFocusPainted(false);
		btnReset.setFocusPainted(false);

		JLabel lblTimKhuyenMai = new JLabel("Tìm:");
		lblTimKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTimKhuyenMai.setBounds(30, 414, 50, 26);
		panel.add(lblTimKhuyenMai);

		txtTimKhuyenMai = new JTextField();
		txtTimKhuyenMai.setColumns(10);
		txtTimKhuyenMai.setBounds(79, 414, 189, 26);
		panel.add(txtTimKhuyenMai);

		JLabel lblTu = new JLabel("Từ");
		lblTu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTu.setBounds(318, 409, 29, 36);
		panel.add(lblTu);

		txtTimNgayBD = new JDateChooser();
		txtTimNgayBD.setDateFormatString("dd/MM/yyyy");
		txtTimNgayBD.setBounds(345, 414, 150, 26);
		panel.add(txtTimNgayBD);

		txtTimNgayKT = new JDateChooser();
		txtTimNgayKT.setDateFormatString("dd/MM/yyyy");
		txtTimNgayKT.setBounds(543, 414, 150, 26);
		panel.add(txtTimNgayKT);

		JLabel lblDen = new JLabel("Đến");
		lblDen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDen.setBounds(507, 414, 50, 26);
		panel.add(lblDen);

		btnTimKM = new JButton("Tìm");
		btnTimKM.setFocusPainted(false);
		btnReset.setBackground(new Color(0, 191, 255));
		btnReset.setBackground(new Color(0, 0, 255));
		btnTimKM.setBounds(705, 414, 67, 26);
		btnTimKM.setContentAreaFilled(false);
		panel.add(btnTimKM);

		
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

		

		table2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableMouseClicked(evt);
			}
		});

		btnTaoKM.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnTaoKMActionPerformed(evt);
					kiemTraVaCapNhatSanPham();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void ganThongTin() {
				ResultSet rs = dao.DAO_KhuyenMai.layKM();
				System.out.println(rs);// Xem có dữ liệu hay không
				String maxMaKM = null;
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
					System.out.println(newMaKM);// Xuất ra xem thử mã KH
				} else {
					newMaKM = "KM" + currentDate + "01";
					System.out.println(newMaKM);
				}
				txtMaKM.setText(newMaKM);
				khuyenMai.setMaKhuyenMai(newMaKM);
				khuyenMai.setTenKhuyenMai(txtTenKM.getText().toString());
				khuyenMai.setPhanTram(Integer.parseInt(txtPhanTram.getText()
						.toString()));
				// Date ngayBD = txtNgayBD.getDate();
				// khuyenMai.setNgayBatDau(ngayBD);
				// Date ngayKT = txtNgayKetThuc.getDate();
				// khuyenMai.setNgayKetThuc(ngayKT);
				// Chuyển đổi từ java.util.Date sang java.time.LocalDate
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
				String ngayKTBienDang = dateFormat.format(ngayKT);

				// Hiển thị ngày và giờ bắt đầu và kết thúc đã chọn
				System.out.println("Ngày và giờ bắt đầu: " + ngayBDBienDang);
				System.out.println("Ngày và giờ kết thúc: " + ngayKTBienDang);

				if (ngayBD.after(ngayKT)) {
					ngayBD = currentDate;
					txtNgayBD.setDate(currentDate);
				}
				ganThongTin();
				boolean kt = bus.BUS_KhuyenMai.kt_Them(khuyenMai);
				if (kt) {
					dao.DAO_KhuyenMai.them(khuyenMai);

					bus.BUS_KhuyenMai.dodulieu(table2);
				}
				int[] selectedRows = table1.getSelectedRows();
				List<String> selectedProductIDs = new ArrayList<>();
				for (int row : selectedRows) {
					String productID = table1.getValueAt(row, 1).toString(); // Lấy
																				// mã
																				// sản
																				// phẩm
					selectedProductIDs.add(productID);
				}

				for (String productID : selectedProductIDs) {
					// Thay thế "productTable" bằng tên bảng sản phẩm trong cơ
					// sở dữ liệu của bạn
					String updateQuery = "UPDATE SanPham SET maKhuyenMai = ? WHERE maSanPham = ?";
					try {
						ConnectionManager connectionManager = new ConnectionManager(); // Tạo
																						// kết
																						// nối
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

				bus.BUS_KhuyenMai.dodulieu(table2);
				bus.BUS_SanPham.dodulieu(table1);

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
		bus.BUS_SanPham.timKiem(table1, tuKhoa, loai);
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
	    // Chuyển đổi từ java.util.Date sang java.sql.Date để sử dụng trong câu truy vấn SQL
	    if (!(ngayBD == null || ngayKT==null || ngayKT.after(ngayBD))) {
	    	sqlNgayBD = new java.sql.Date(ngayBD.getTime());
		    sqlNgayKT = new java.sql.Date(ngayKT.getTime());
		}
	    
	    
		String tuKhoa = txtTimKhuyenMai.getText().trim();
//		bus.BUS_KhuyenMai.timKiem(table2, tuKhoa, phanTram);
		bus.BUS_KhuyenMai.timKiem(table2, tuKhoa, phanTram,sqlNgayBD, sqlNgayKT);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// Tìm kiếm
		String loai = cboLoai.getSelectedItem().toString();
		String tuKhoa = txtTim.getText().trim();
		Object o = e.getSource();
		if (o.equals(cboLoai)) {
			bus.BUS_SanPham.timKiem(table1, tuKhoa, loai);
		}
	}

	public static int vitri;

	// public static String maKM;

	private void tableMouseClicked(MouseEvent evt) {

		if (table2.getSelectedRowCount() == 1) {
			vitri = table2.getSelectedRow();
			String maKM = table2.getValueAt(vitri, 0).toString();
			thongTinChiTiet(maKM);
			hienThiSanPhamTheoKhuyenMai(maKM);
		}

	}

	// private void tableMouseClicked(MouseEvent evt) {
	// if (table2.getSelectedRowCount() == 1) {
	// int vitriMoi = table2.getSelectedRow();
	// String maKMMoi = table2.getValueAt(vitriMoi, 0).toString().trim();
	//
	// // Kiểm tra xem có chọn mã khuyến mãi mới hay không
	// if (!maKMMoi.equals(maKM)) {
	// maKM = maKMMoi;
	// thongTinChiTiet(maKM);
	// hienThiSanPhamTheoKhuyenMai(maKM);
	// }
	// }
	// }

	private void thongTinChiTiet(String maKM) {
		ResultSet rs = dao.DAO_KhuyenMai.layKMTheoMa(maKM);
		// System.err.println( "kq : "+rs);
		try {
			if (rs != null && rs.next()) {
				txtMaKM.setText(rs.getString("maKhuyenMai"));
				txtTenKM.setText(rs.getString("tenKhuyenMai"));

				int phanTram = rs.getInt("phanTram");
				DecimalFormat df = new DecimalFormat("#,##");
				txtPhanTram.setText(df.format(phanTram));

				Date ngayBatDau = rs.getDate("ngayBatDau");
				txtNgayBD.setDate(ngayBatDau);

				Date ngayKetThuc = rs.getDate("ngayKetThuc");
				txtNgayKetThuc.setDate(ngayKetThuc);

				txtMota.setText(rs.getString("moTa"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			kiemTraVaCapNhatSanPham();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		 String query ="SELECT maKhuyenMai FROM KhuyenMai WHERE ngayKetThuc >= CONVERT(DATE, GETDATE()) AND ngayKetThuc < DATEADD(DAY, 1, CONVERT(DATE, GETDATE()));";
		//String query = "SELECT maKhuyenMai FROM KhuyenMai WHERE ngayKetThuc > CONVERT(DATE, GETDATE())";

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
		txtNgayBD.setDate(null);
		txtNgayKetThuc.setDate(null);
		txtTenKM.requestFocus();
		// ganTXTMaKM();

	}

	private void ganTXTMaKM() {
		ResultSet rs = dao.DAO_KhuyenMai.layKM();
		System.out.println(rs);// Xem có dữ liệu hay không
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
			System.out.println(newMaKM);// Xuất ra xem thử mã KH
		} else {
			newMaKM = "KM" + currentDate + "01";
			System.out.println(newMaKM);
		}
		txtMaKM.setText(newMaKM);
	}

	private void hienThiSanPhamTheoKhuyenMai(String maKhuyenMai) {
		// Thực hiện truy vấn SQL để lấy danh sách sản phẩm dựa trên mã khuyến
		// mãi
		String sql = "SELECT * FROM SanPham WHERE maKhuyenMai = ?";
		try (Connection conn = connectionManager.conn;
				PreparedStatement preparedStatement = conn
						.prepareStatement(sql)) {

			preparedStatement.setString(1, maKhuyenMai);

			ResultSet resultSet = preparedStatement.executeQuery();

			// Xóa dữ liệu cũ trong table1
			model1.setRowCount(0);
			int stt = 0;
			// Đổ dữ liệu mới vào table1
			Object obj[] = new Object[5];
			while (resultSet.next()) {
				stt++;
				obj[0] = stt;
				obj[1] = resultSet.getString("maSanPham");
				obj[2] = resultSet.getString("tenSanPham");
				obj[3] = resultSet.getString("loai");
				obj[4] = resultSet.getDouble("giaNhap");
				model1.addRow(obj);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	private boolean validateNgayTimKiem() {
	    Date ngayBD = txtTimNgayBD.getDate();
	    Date ngayKT = txtTimNgayKT.getDate();

	    if (ngayBD == null || ngayKT == null) {
	        thongbao.thongbao("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.", "");
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
	    
	    // Chuyển đổi từ java.util.Date sang java.sql.Date để sử dụng trong câu truy vấn SQL
	    java.sql.Date sqlNgayBD = new java.sql.Date(ngayBD.getTime());
	    java.sql.Date sqlNgayKT = new java.sql.Date(ngayKT.getTime());

	    // Thực hiện truy vấn SQL để lấy danh sách khuyến mãi dựa trên ngày bắt đầu và kết thúc
	    String sql = "SELECT * FROM KhuyenMai WHERE ngayBatDau >= ? AND ngayKetThuc <= ?";
	    try (
	         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

	        preparedStatement.setDate(1, sqlNgayBD);
	        preparedStatement.setDate(2, sqlNgayKT);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Xóa dữ liệu cũ trong table2
	        model2.setRowCount(0);

	        // Đổ dữ liệu mới vào table2
	        Object obj[] = new Object[5];
	        while (resultSet.next()) {
	            obj[0] = resultSet.getString("maKhuyenMai");
	            obj[1] = resultSet.getString("tenKhuyenMai");
	            obj[2] = resultSet.getInt("phanTram");
	            ngayBD = resultSet.getDate("ngayBatDau");
				obj[3] = sdf.format(ngayBD);
				ngayKT=resultSet.getDate("ngayKetThuc");
				obj[4] = sdf.format(ngayKT);
	            model2.addRow(obj);
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

}
