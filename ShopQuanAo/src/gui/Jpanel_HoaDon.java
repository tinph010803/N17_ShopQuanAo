package gui;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import bus.BUS_HoaDon;
import bus.BUS_NhanVien;
import connectDB.ConnectionManager;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_HoaDon;
import dao.DAO_KhuyenMai;
import dao.DAO_NhanVien;
import dao.DAO_SanPham;
import entity.ChiTietHoaDon;
import entity.Enum_Mau;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.SanPham;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.mail.search.AndTerm;
import javax.swing.JButton;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Jpanel_HoaDon extends JPanel {
	private JTextField txtMaHD;
	private JTextField txtMaKH;
	private JTextField txtSDT;
	private String cl_yellow = "#fcbe00";
	private String cl_green = "#00c691";
	private DefaultTableModel modelHD;
	private JTable tableHD;
	private DefaultTableModel modelCTHD;
	private JTable tableCTHD;
	private JPanel pnl_HoaDon;
	private JPanel panel_left;
	private JLabel lblTimKiem;
	private JLabel lblMaHD;
	private JLabel lblMaKH;
	private JLabel lblSDT;
	private JPanel panel_right;
	private JPanel panel_right_top;
	private JLabel lblHoaDon;
	private JLabel lblChiTietHD;
	private JPanel panel_right_bot;
	private JButton btnChon = null;
	private JDateChooser txtNgayBD;
	private JDateChooser txtNgayKT;
	private int i;
	private LocalDate ngayBD;
	private LocalDate ngayKT;

	private BUS_NhanVien busNV = new BUS_NhanVien();
	private BUS_HoaDon busHD = new BUS_HoaDon();
	private DAO_HoaDon daoHD = new DAO_HoaDon();
	private ConnectionManager connectionManager = new ConnectionManager();
	private JComboBox cboNhanvien;
	// private JLabel lblKqTimKiem;

	private DecimalFormat df = new DecimalFormat("#,###,###");
	private JLabel lblTienThue;
	private JLabel lblTienCanThu;
	private JLabel lblTienKM;
	private JLabel lblTienBac;
	private JLabel lblTbTongTien;
	private JLabel lblTbTienKM;
	private JLabel lblTbTienThue;
	private JLabel lblTbTienBac;
	private JLabel lblTbTienCanThu;
	private JLabel lblKqTimKiem;
	private JButton btnDoiTra;
	private JButton btnIn;
	private JLabel lblNhanVien;
	private JLabel lblNgayBD;
	private JLabel lblKhoanNgay;
	private JLabel lblLoc;
	private JPanel pnl_left_bot;
	private JPanel panel;
	private JLabel lblNgayKT;
	private JButton btnReset;
	private JPanel pnl_rdo;

	/**
	 * Create the panel.
	 */
	public Jpanel_HoaDon(NhanVien nvhientai) {
		setLayout(null);

		pnl_HoaDon = new JPanel();
		pnl_HoaDon.setBounds(0, 0, 1646, 975);
		add(pnl_HoaDon);
		pnl_HoaDon.setLayout(null);

		panel_left = new JPanel();
		panel_left.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0,
				0, 0)));
		panel_left.setBounds(0, 0, 320, 975);
		pnl_HoaDon.add(panel_left);
		panel_left.setLayout(null);

		lblTimKiem = new JLabel("TÌM KIẾM");
		lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTimKiem.setBounds(95, 125, 124, 50);
		panel_left.add(lblTimKiem);
		lblTimKiem.setForeground(Color.decode(cl_yellow));

		txtMaHD = new JTextField();
		txtMaHD.setHorizontalAlignment(SwingConstants.LEADING);
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaHD.setBounds(15, 240, 285, 40);
		panel_left.add(txtMaHD);
		txtMaHD.setColumns(10);

		txtMaKH = new JTextField();
		txtMaKH.setHorizontalAlignment(SwingConstants.LEADING);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(15, 320, 285, 40);
		panel_left.add(txtMaKH);

		txtSDT = new JTextField();
		txtSDT.setHorizontalAlignment(SwingConstants.LEADING);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		txtSDT.setBounds(15, 400, 285, 40);
		panel_left.add(txtSDT);

		lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setFont(lblMaHD.getFont().deriveFont(
				lblMaHD.getFont().getStyle() | Font.BOLD, 20f));
		lblMaHD.setBounds(20, 210, 180, 25);
		panel_left.add(lblMaHD);

		lblMaKH = new JLabel("Tên khách hàng:");
		lblMaKH.setFont(lblMaKH.getFont().deriveFont(
				lblMaKH.getFont().getStyle() | Font.BOLD, 20f));
		lblMaKH.setBounds(20, 290, 233, 25);
		panel_left.add(lblMaKH);

		lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(lblSDT.getFont().deriveFont(
				lblSDT.getFont().getStyle() | Font.BOLD, 20f));
		lblSDT.setBounds(20, 370, 200, 25);
		panel_left.add(lblSDT);

		panel_right = new JPanel();
		panel_right.setBounds(314, 0, 1332, 975);
		pnl_HoaDon.add(panel_right);
		panel_right.setLayout(null);

		panel_right_top = new JPanel();
		panel_right_top.setBounds(10, 0, 1323, 347);
		panel_right.add(panel_right_top);
		panel_right_top.setLayout(null);

		lblHoaDon = new JLabel("HÓA ĐƠN");
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setBounds(560, 16, 211, 26);
		panel_right_top.add(lblHoaDon);
		lblHoaDon.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblHoaDon.setForeground(Color.decode(cl_yellow));

		String row[] = { "Mã hóa đơn", "Ngày lập HĐ", "Tên KH",
				"Số điện thoại", "Nhân viên", "Tổng tiền" };
		modelHD = new DefaultTableModel(row, 0);
		tableHD = new JTable(modelHD);
		tableHD.setRowHeight(30);
		tableHD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				153)));
		JTableHeader tbHeader = tableHD.getTableHeader();
		tbHeader.setBackground((Color.decode("#00c691")));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 10));
		JScrollPane scrollPane = new JScrollPane(tableHD);
		scrollPane.setBorder(null);
		scrollPane.setLocation(30, 58);
		scrollPane.setSize(1281, 277);
		JTableHeader tableHeader = tableHD.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableHD.setFont(new Font("Tohoma", Font.PLAIN, 16));
		panel_right_top.add(scrollPane);

		panel_right_bot = new JPanel();
		panel_right_bot.setBounds(10, 348, 1323, 620);
		tableHD.setRowHeight(30);
		
		TableColumnModel columnModelHD = tableHD.getColumnModel();
		TableColumn tongTien = columnModelHD.getColumn(5);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tongTien.setCellRenderer(rightRenderer);

		lblKqTimKiem = new JLabel("");
		lblKqTimKiem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblKqTimKiem.setForeground(new Color(255, 51, 0));
		lblKqTimKiem.setBounds(90, 15, 150, 30);
		panel_right_top.add(lblKqTimKiem);

		panel_right.add(panel_right_bot);

		String row1[] = { "Tên SP", "Loại", "Màu sắc", "Kích thước",
				"Số lượng", "Giá bán", "Tiền KM", "Thành tiền",
				"Tiền Thuế", "Tiền Bậc", "Tổng Tiền" };
		modelCTHD = new DefaultTableModel(row1, 0);
		tableCTHD = new JTable(modelCTHD);
		tableCTHD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				153)));
		tableCTHD.setRowHeight(30);
		JTableHeader tbHeader1 = tableCTHD.getTableHeader();
		tbHeader1.setBackground((Color.decode("#00c691")));
		tbHeader1.setForeground(Color.white);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 10));
		panel_right_bot.setLayout(null);
		JScrollPane scrollPane1 = new JScrollPane(tableCTHD);
		scrollPane1.setBorder(null);
		scrollPane1.setLocation(26, 46);
		scrollPane1.setSize(1276, 351);
		tbHeader1.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableCTHD.setFont(new Font("Tohoma", Font.PLAIN, 16));
		panel_right_bot.add(scrollPane1);
		
		TableColumnModel columnModelCTHD = tableCTHD.getColumnModel();
		TableColumn soLuong = columnModelCTHD.getColumn(4);
		TableColumn gia = columnModelCTHD.getColumn(5);
		TableColumn khuyenMai = columnModelCTHD.getColumn(6);
		TableColumn thanhTien = columnModelCTHD.getColumn(7);
		TableColumn tienThue = columnModelCTHD.getColumn(8);
		TableColumn tienBac = columnModelCTHD.getColumn(9);
		TableColumn tong = columnModelCTHD.getColumn(10);
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		soLuong.setCellRenderer(rightRenderer);
		gia.setCellRenderer(rightRenderer);
		khuyenMai.setCellRenderer(rightRenderer);
		thanhTien.setCellRenderer(rightRenderer);
		tienThue.setCellRenderer(rightRenderer);
		tienBac.setCellRenderer(rightRenderer);
		tong.setCellRenderer(rightRenderer);

		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTongTien.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongTien.setBounds(100, 420, 100, 25);
		panel_right_bot.add(lblTongTien);

		panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel.setBounds(100, 580, 375, 33);
		panel_right_bot.add(panel);
		panel.setLayout(null);

		lblTienCanThu = new JLabel("Tổng tiền cần thu:");
		lblTienCanThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienCanThu.setBounds(0, 0, 123, 33);
		panel.add(lblTienCanThu);
		lblTienCanThu.setHorizontalAlignment(SwingConstants.LEFT);

		lblTbTienCanThu = new JLabel("");
		lblTbTienCanThu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTbTienCanThu.setBounds(180, 0, 175, 25);
		panel.add(lblTbTienCanThu);

		lblTienThue = new JLabel("Tổng tiền thuế:");
		lblTienThue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienThue.setBounds(100, 500, 128, 25);
		panel_right_bot.add(lblTienThue);
		lblTienThue.setHorizontalAlignment(SwingConstants.LEFT);

		lblChiTietHD = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblChiTietHD.setBounds(549, 10, 211, 26);
		panel_right_bot.add(lblChiTietHD);
		lblChiTietHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTietHD.setForeground(new Color(252, 190, 0));
		lblChiTietHD.setFont(new Font("Tahoma", Font.BOLD, 21));

		lblTienKM = new JLabel("Tổng tiền khuyến mãi:");
		lblTienKM.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienKM.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienKM.setBounds(100, 460, 149, 25);
		panel_right_bot.add(lblTienKM);

		lblTienBac = new JLabel("Tổng tiền bậc:");
		lblTienBac.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienBac.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienBac.setBounds(100, 540, 100, 25);
		panel_right_bot.add(lblTienBac);

		lblTbTongTien = new JLabel("");
		lblTbTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTbTongTien.setBounds(280, 420, 175, 25);
		panel_right_bot.add(lblTbTongTien);

		lblTbTienKM = new JLabel("");
		lblTbTienKM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTbTienKM.setBounds(280, 460, 175, 25);
		panel_right_bot.add(lblTbTienKM);

		lblTbTienThue = new JLabel("");
		lblTbTienThue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTbTienThue.setBounds(280, 500, 175, 25);
		panel_right_bot.add(lblTbTienThue);

		lblTbTienBac = new JLabel("");
		lblTbTienBac.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTbTienBac.setBounds(280, 540, 175, 25);
		panel_right_bot.add(lblTbTienBac);

		pnl_left_bot = new JPanel();
		pnl_left_bot.setBorder(new MatteBorder(2, 0, 0, 2, (Color) new Color(0,
				0, 0)));
		pnl_left_bot.setBounds(0, 488, 320, 487);
		panel_left.add(pnl_left_bot);
		pnl_left_bot.setLayout(null);

		lblLoc = new JLabel("LỌC");
		lblLoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc.setForeground(new Color(252, 190, 0));
		lblLoc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLoc.setBounds(95, 35, 124, 50);
		pnl_left_bot.add(lblLoc);

		lblKhoanNgay = new JLabel("Khoảng ngày:");
		lblKhoanNgay.setFont(lblKhoanNgay.getFont().deriveFont(
				lblKhoanNgay.getFont().getStyle() | Font.BOLD, 20f));
		lblKhoanNgay.setBounds(50, 110, 200, 32);
		pnl_left_bot.add(lblKhoanNgay);

		lblNgayBD = new JLabel("Ngày bắt đầu:");
		lblNgayBD.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgayBD.setBounds(15, 150, 124, 35);
		pnl_left_bot.add(lblNgayBD);

		lblNgayKT = new JLabel("Ngày kết thúc:");
		lblNgayKT.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgayKT.setBounds(15, 190, 124, 35);
		pnl_left_bot.add(lblNgayKT);

		txtNgayBD = new JDateChooser();
		txtNgayBD.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		txtNgayBD.setDateFormatString("dd/MM/yyyy");
		txtNgayBD.setDate(new java.util.Date());
		txtNgayBD.setBounds(138, 150, 162, 35);
		pnl_left_bot.add(txtNgayBD);

		txtNgayKT = new JDateChooser();
		txtNgayKT.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		txtNgayKT.setDateFormatString("dd/MM/yyyy");
		txtNgayKT.setDate(new java.util.Date());
		txtNgayKT.setBounds(138, 190, 162, 35);
		pnl_left_bot.add(txtNgayKT);

		lblNhanVien = new JLabel("Nhân viên:");
		lblNhanVien.setFont(lblNhanVien.getFont().deriveFont(
				lblNhanVien.getFont().getStyle() | Font.BOLD, 20f));
		lblNhanVien.setBounds(50, 245, 175, 32);
		pnl_left_bot.add(lblNhanVien);

		btnIn = new JButton("In");
		btnIn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnIn.setFocusPainted(false);
		btnIn.setBackground(new Color(255, 222, 173));
		btnIn.setBounds(15, 400, 125, 40);
		pnl_left_bot.add(btnIn);

		btnDoiTra = new JButton("Đổi trả");
		btnDoiTra.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnDoiTra.setFocusPainted(false);
		btnDoiTra.setBackground(new Color(255, 160, 122));
		btnDoiTra.setBounds(176, 400, 125, 40);
		pnl_left_bot.add(btnDoiTra);
		ResultSet rs = ConnectionManager
				.getdata("select sum= sum(1) from NhanVien");
		int i = 1;
		try {
			while (rs.next()) {
				i = rs.getInt("sum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String a[] = new String[i + 1];
		// System.err.println(a.length);
		rs = DAO_NhanVien.layNV();
		int j = 1;
		a[0] = "Tất cả";
		try {
			while (rs.next()) {
				a[j] = rs.getString("tenNhanVien").trim();
				j++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.err.println(a);
		cboNhanvien = new JComboBox();
		cboNhanvien.setModel(new DefaultComboBoxModel(a));
		cboNhanvien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboNhanvien.setBounds(15, 285, 285, 35);
		pnl_left_bot.add(cboNhanvien);

		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnReset.setFocusPainted(false);
		btnReset.setBackground(new Color(255, 255, 102));
		btnReset.setBounds(105, 336, 100, 35);
		pnl_left_bot.add(btnReset);
		btnDoiTra.setFocusPainted(false);
		btnIn.setFocusPainted(false);

		pnl_rdo = new JPanel();
		pnl_rdo.setBorder(new MatteBorder(0, 0, 2, 2,
				(Color) new Color(0, 0, 0)));
		pnl_rdo.setBounds(0, 0, 320, 129);
		panel_left.add(pnl_rdo);
		pnl_rdo.setLayout(null);

		JRadioButton rdoHD = new JRadioButton("Hóa đơn");
		rdoHD.setBounds(11, 25, 200, 35);
		pnl_rdo.add(rdoHD);
		rdoHD.setFont(new Font("Tahoma", Font.BOLD, 21));

		JRadioButton rdoHDDT = new JRadioButton("Hóa đơn đổi trả");
		rdoHDDT.setBounds(11, 70, 200, 35);
		pnl_rdo.add(rdoHDDT);
		rdoHDDT.setFont(new Font("Tahoma", Font.BOLD, 21));

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdoHD);
		radioGroup.add(rdoHDDT);
		rdoHD.setSelected(true);
		rdoHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Xử lý khi nhấn vào rdoHD
				pnl_HoaDon.removeAll();
				pnl_HoaDon.setLayout(new BorderLayout());
				pnl_HoaDon.add(new Jpanel_HoaDon(nvhientai)); // Tạo một
																// instance mới
																// của
																// Jpanel_HoaDon
				pnl_HoaDon.revalidate();
				pnl_HoaDon.repaint();
				rdoHD.setSelected(true);
			}
		});

		rdoHDDT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Xử lý khi nhấn vào rdoHDDT
				pnl_HoaDon.removeAll();
				pnl_HoaDon.setLayout(new BorderLayout());
				pnl_HoaDon.add(new Jpanel_HoaDonDoiTra(nvhientai)); // Tạo một
																	// instance
																	// mới của
																	// Jpanel_HoaDonDoiTra
				pnl_HoaDon.revalidate();
				pnl_HoaDon.repaint();
				rdoHDDT.setSelected(true);
			}
		});

		btnDoiTra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnDoiTra.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnDoiTra.setBackground(Color.decode("#ffa500"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				HoaDon hd = null;
				int row = tableHD.getSelectedRow();
				hd = daoHD.layHoaDonTheoMa(tableHD.getValueAt(row, 0).toString()
						.trim());
				// System.err.println(hd);
				if (row != -1) {

					int result = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc chắn muốn đổi trả HÓA ĐƠN này?",
							"Xác nhận Đổi Trả", JOptionPane.YES_NO_OPTION);
					if (result == 0) {
						int day = (int) ChronoUnit.DAYS.between(hd.getNgay(),
								LocalDate.now());

						if (day >= 3) {
							thongbao.thongbao("Hóa đơn này đã quá hạn đổi trả",
									"Thông báo");
						} else {
							// System.out.println(hd);
							pnl_HoaDon.removeAll();
							pnl_HoaDon.setLayout(new BorderLayout());
							pnl_HoaDon.add(new Jpanel_DoiTra(hd, nvhientai));
							pnl_HoaDon.revalidate();
							pnl_HoaDon.repaint();
							btnChon = btnDoiTra;
							tableHD.clearSelection();
						}

					}

				} else if (row == -1) {
					thongbao.thongbao("Yêu cầu chọn hóa đơn đổi trả", "");
				}

				// setLaiMau();
			}
		});

		// đọc dữ liệu lên bảng
		busHD.docDuLieu_HoaDon(modelHD, daoHD.layDSHoaDon());

		// sự kiện

		// sự kiện table
		tableHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tableHD.getSelectedRow();
				String mahd = tableHD.getValueAt(row, 0).toString().trim();
				// System.err.println("ma hoa don CTHD :" + mahd);
				List<ChiTietHoaDon> ds = DAO_ChiTietHoaDon
						.layDSCTHDTheoMaHd(mahd);
				HoaDon hd = new HoaDon();
				busHD.docDuLieu_CTHD(modelCTHD, row, mahd);
				lblTbTongTien.setText(df.format(hd.tinhTongTien(ds)));
				lblTbTienThue.setText(df.format(hd.tinhTongTienThue(ds)));
				lblTbTienKM.setText(df.format(hd.tinhTongTienKM(ds)));
				lblTbTienBac.setText(df.format(hd.tinhTongKhuyeMaiTheoBac(ds)));
				hd.setTongTienCanThu(ds);
				lblTbTienCanThu.setText(df.format(hd.getTongTienCanThu()));
			}
		});

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtMaHD.setText("");
				txtMaKH.setText("");
				txtSDT.setText("");
				txtNgayKT.setDate(new java.util.Date());
				txtNgayBD.setDate(new java.util.Date());
				cboNhanvien.setSelectedItem(a[0]);
				busHD.docDuLieu_HoaDon(modelHD, daoHD.layDSHoaDon());
				lblKqTimKiem.setText("");
				busHD.DeleteDataTable(modelCTHD);

			}
		});

		// sự kiện nút tìm kiếm
		txtMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				timKiem();
			}
		});

		txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				timKiem();
			}
		});

		txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				timKiem();
			}
		});

		// sự kiện lọc
		cboNhanvien.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Loc();
				}
			}
		});

		txtNgayBD.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if ("date".equals(evt.getPropertyName())) {
							Loc();

						}
					}
				});

		txtNgayKT.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if ("date".equals(evt.getPropertyName())) {
							Loc();

						}
					}
				});
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				xuatHoaDon();
			}

		});
	}
	
	public void xuatHoaDon() {
		try {
			DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			Map<String, Object> map = new Hashtable();
			JasperReport report = JasperCompileManager
					.compileReport("src/gui/rp_XuatHoaDon.jrxml");
			if (tableHD.getSelectedRowCount()==1) {
				int viTri = tableHD.getSelectedRow();
				String maHD = tableHD.getValueAt(viTri, 0).toString();
				HoaDon hd = dao.DAO_HoaDon.layHoaDonTheoMa(maHD);
				List<ChiTietHoaDon> dsCTHD = dao.DAO_ChiTietHoaDon.layDSCTHDTheoMaHd(maHD);
				map.put("MaHD", hd.getMaHoaDon());
				if (hd.getKhachHang()!=null) {
					map.put("MaKH", hd.getKhachHang().getMaKhachHang());
				}else{
					map.put("MaKH", "Khách vãng lai");
				}
				
				map.put("NgayBan", d.format(hd.getNgay()));
				map.put("TenNV", hd.getNhanVien().getTenNhanVien());
				map.put("TongThanhTien", hd.tinhTongTien(dsCTHD));
				map.put("TienKM", hd.tinhTongTienKM(dsCTHD));
				map.put("TienThue", hd.tinhTongTienThue(dsCTHD));
				map.put("TienKMBac", hd.tinhTongKhuyeMaiTheoBac(dsCTHD));
				map.put("TienCanThu", hd.getTongTienCanThu());
				map.put("TienKhachDua", hd.getTienKhachDua());
				map.put("TienThua", hd.tinhTienThua(dsCTHD));
				
				
				JasperPrint p = JasperFillManager.fillReport(report,map,connectDB.ConnectionManager.conn);
				JasperViewer.viewReport(p, false);
				JasperExportManager.exportReportToPdfFile(p, "test.pdf");
			}else{
				thongbao.thongbao("Chọn 1 hóa đơn để in hóa đơn", "");
			}

			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void timKiem() {

		String maHD = txtMaHD.getText().trim();
		String maKH = txtMaKH.getText().trim();
		String sdt = txtSDT.getText().trim();

		if (sdt.equals("null") == true || maKH.equals("null") == true) {
			List<HoaDon> ds = daoHD.layDSHoaDon();
			List<HoaDon> ds2 = new ArrayList<>();
			for (HoaDon a : ds) {
				if (a.getKhachHang() == null)
					ds2.add(a);
			}

			busHD.DeleteDataTable(modelHD);
			busHD.DeleteDataTable(modelCTHD);
			lblKqTimKiem.setText(ds2.size() + " kết quả");
			busHD.docDuLieu_HoaDon(modelHD, ds2);
		} else {
			String sql = "select a.* from HoaDon a join KhachHang b on a.maKhachHang=b.maKhachHang \r\n"
					+ "where a.maHoaDon like '%"
					+ maHD
					+ "%' and upper(b.tenKhachHang) like upper(N'%"
					+ maKH
					+ "%') and b.sdt like N'%" + sdt + "%'";
			List<HoaDon> ds = daoHD.timDSHD(sql);

			// System.err.println("\n" + ds + "\n");

			if (maHD.equals(maKH) && maHD.equals(sdt) && maHD.equals("")) {
				busHD.DeleteDataTable(modelHD);
				busHD.DeleteDataTable(modelCTHD);
				lblKqTimKiem.setText("");
				busHD.docDuLieu_HoaDon(modelHD, daoHD.layDSHoaDon());
			} else {
				// System.err.println(sql + "\n");
				if (!ds.isEmpty()) {
					busHD.DeleteDataTable(modelHD);
					busHD.DeleteDataTable(modelCTHD);
					lblKqTimKiem.setText(ds.size() + " kết quả");
					busHD.docDuLieu_HoaDon(modelHD, ds);
				} else {
					busHD.DeleteDataTable(modelHD);
					busHD.DeleteDataTable(modelCTHD);
					lblKqTimKiem.setText(ds.size() + " kết quả");
				}
			}

		}

	}

	public boolean Loc() {
		String selectedValue = cboNhanvien.getSelectedItem().toString();
		LocalDate ngaybd1 = busNV.chuyenDoiKieuNgay(txtNgayBD);
		LocalDate ngaykt1 = busNV.chuyenDoiKieuNgay(txtNgayKT);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (ngaybd1.isAfter(LocalDate.now())) {
			thongbao.thongbao("Chọn lại ngày bắt đầu ", "Chú ý");
			return false;
		}
		if (ngaykt1.isBefore(ngaybd1)) {
			thongbao.thongbao("Chọn lại ngày kết thúc ", "Chú ý");
			return false;
		}

		// Tạo điều kiện tìm kiếm
		String condition = "";
		if (selectedValue != "Tất cả") {
			condition += "NhanVien.tenNhanVien = N'" + selectedValue
					+ "' AND HoaDon.ngay BETWEEN '" + ngaybd1.format(formatter)
					+ "' AND '" + ngaykt1.format(formatter) + "'";
		} else {
			condition += " HoaDon.ngay BETWEEN '" + ngaybd1.format(formatter)
					+ "' AND '" + ngaykt1.format(formatter) + "'";
		}
		// Thực hiện truy vấn SQL với điều kiện tìm kiếm
		String sql = "SELECT * FROM HoaDon INNER JOIN NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien WHERE "
				+ condition;
		List<HoaDon> ds = daoHD.timDSHD(sql);
		if (!ds.isEmpty()) {
			busHD.DeleteDataTable(modelHD);
			busHD.DeleteDataTable(modelCTHD);
			busHD.docDuLieu_HoaDon(modelHD, ds);
		} else {
			busHD.DeleteDataTable(modelHD);
			busHD.DeleteDataTable(modelCTHD);
		}
		lblKqTimKiem.setText(ds.size() + " kết quả");
		return true;
	}
}
