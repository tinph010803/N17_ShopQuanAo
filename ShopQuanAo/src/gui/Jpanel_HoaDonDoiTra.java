package gui;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import bus.BUS_DoiTra;
import bus.BUS_HoaDon;
import bus.BUS_HoaDonDoiTra;
import bus.BUS_NhanVien;
import connectDB.ConnectionManager;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoaDonDoiTra;
import dao.DAO_HoaDon;
import dao.DAO_HoaDonDoiTra;
import dao.DAO_KhuyenMai;
import dao.DAO_NhanVien;
import dao.DAO_SanPham;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra;
import entity.Enum_Mau;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.SanPham;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.mail.search.AndTerm;
import javax.swing.JButton;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JRadioButton;

public class Jpanel_HoaDonDoiTra extends JPanel {
	private JTextField txtMaHD;
	private JTextField txtMaKH;
	private JTextField txtSDT;
	private String cl_yellow = "#fcbe00";
	private String cl_green = "#00c691";
	private DefaultTableModel modelHDDT;
	private JTable tableHDDT;
	private DefaultTableModel modelCTHDDT;
	private JTable tableCTHDDT;
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
	private BUS_HoaDonDoiTra busDT = new BUS_HoaDonDoiTra();
	private DAO_HoaDonDoiTra daoHDDT = new DAO_HoaDonDoiTra();
	// private BUS_HoaDonDoiTra busHDDT = new BUS_HoaDonDoiTra();
	private ConnectionManager connectionManager = new ConnectionManager();
	private JComboBox cboNhanvien;
	private JLabel lblKqTimKiem;

	/**
	 * Create the panel.
	 */
	public Jpanel_HoaDonDoiTra(NhanVien nvhientai) {
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

		lblMaHD = new JLabel("Mã hóa đơn đổi trả:");
		lblMaHD.setFont(lblMaHD.getFont().deriveFont(
				lblMaHD.getFont().getStyle() | Font.BOLD, 20f));
		lblMaHD.setBounds(20, 210, 199, 25);
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

		lblHoaDon = new JLabel("HÓA ĐƠN ĐỔI TRẢ");
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setBounds(560, 16, 210, 30);
		panel_right_top.add(lblHoaDon);
		lblHoaDon.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblHoaDon.setForeground(Color.decode(cl_yellow));

		String row[] = { "Mã hóa đơn đổi trả", "Mã hóa đơn", "Ngày lập HĐ",
				"Tên KH", "Số điện thoại", "Nhân viên", "Tổng tiền Hoàn Trả" };
		modelHDDT = new DefaultTableModel(row, 0);
		tableHDDT = new JTable(modelHDDT);
		tableHDDT.setRowHeight(30);
		tableHDDT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				255, 153)));
		JTableHeader tbHeader = tableHDDT.getTableHeader();
		tbHeader.setBackground((Color.decode("#00c691")));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 10));
		JScrollPane scrollPane = new JScrollPane(tableHDDT);
		scrollPane.setBorder(null);
		scrollPane.setLocation(30, 58);
		scrollPane.setSize(1281, 277);
		JTableHeader tableHeader = tableHDDT.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableHDDT.setFont(new Font("Tohoma", Font.PLAIN, 16));
		panel_right_top.add(scrollPane);

		TableColumnModel columnModelHDDT = tableHDDT.getColumnModel();
		TableColumn tongTien = columnModelHDDT.getColumn(6);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tongTien.setCellRenderer(rightRenderer);



		lblChiTietHD = new JLabel("CHI TIẾT HÓA ĐƠN ĐỔI TRẢ");
		lblChiTietHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTietHD.setForeground(new Color(252, 190, 0));
		lblChiTietHD.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblChiTietHD.setBounds(510, 370, 320, 30);
		panel_right.add(lblChiTietHD);

		panel_right_bot = new JPanel();
		panel_right_bot.setBounds(10, 348, 1323, 620);
		tableHDDT.setRowHeight(30);

		lblKqTimKiem = new JLabel("");
		lblKqTimKiem.setForeground(new Color(255, 51, 0));
		lblKqTimKiem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblKqTimKiem.setBounds(90, 15, 150, 30);
		panel_right_top.add(lblKqTimKiem);

		panel_right.add(panel_right_bot);

		String row1[] = { "Tên sản phẩm", "Loại", "Màu sắc", "Kích thước",
				"Số lượng", "Thành tiền", "Tiền hoàn trả" };
		modelCTHDDT = new DefaultTableModel(row1, 0);
		tableCTHDDT = new JTable(modelCTHDDT);
		tableCTHDDT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				255, 153)));
		tableCTHDDT.setRowHeight(30);
		JTableHeader tbHeader1 = tableCTHDDT.getTableHeader();
		tbHeader1.setBackground((Color.decode("#00c691")));
		tbHeader1.setForeground(Color.white);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 10));
		panel_right_bot.setLayout(null);
		JScrollPane scrollPane1 = new JScrollPane(tableCTHDDT);
		scrollPane1.setBorder(null);
		scrollPane1.setLocation(32, 66);
		scrollPane1.setSize(1276, 464);
		tbHeader1.setFont(new Font("Tohoma", Font.BOLD, 18));
		tableCTHDDT.setFont(new Font("Tohoma", Font.PLAIN, 16));
		panel_right_bot.add(scrollPane1);
		
		
		
		TableColumnModel columnModelCTHDDT= tableCTHDDT.getColumnModel();
		TableColumn soLuong = columnModelCTHDDT.getColumn(4);
		TableColumn thanhTien = columnModelCTHDDT.getColumn(5);
		TableColumn tongTra = columnModelCTHDDT.getColumn(6);
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		soLuong.setCellRenderer(rightRenderer);
		thanhTien.setCellRenderer(rightRenderer);
		tongTra.setCellRenderer(rightRenderer);
		JPanel pnl_left_bot = new JPanel();
		pnl_left_bot.setBorder(new MatteBorder(2, 0, 0, 2, (Color) new Color(0,
				0, 0)));
		pnl_left_bot.setBounds(0, 488, 320, 487);
		panel_left.add(pnl_left_bot);
		pnl_left_bot.setLayout(null);

		JLabel lblLoc = new JLabel("LỌC");
		lblLoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoc.setForeground(new Color(252, 190, 0));
		lblLoc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLoc.setBounds(95, 35, 124, 50);
		pnl_left_bot.add(lblLoc);

		JLabel lblKhoanNgay = new JLabel("Khoảng ngày:");
		lblKhoanNgay.setFont(lblKhoanNgay.getFont().deriveFont(
				lblKhoanNgay.getFont().getStyle() | Font.BOLD, 20f));
		lblKhoanNgay.setBounds(50, 110, 200, 32);
		pnl_left_bot.add(lblKhoanNgay);

		JLabel lblNgayBD = new JLabel("Ngày bắt đầu:");
		lblNgayBD.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgayBD.setBounds(15, 150, 124, 35);
		pnl_left_bot.add(lblNgayBD);

		JLabel lblNgayKT = new JLabel("Ngày kết thúc:");
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

		JLabel lblNhanVien = new JLabel("Nhân viên:");
		lblNhanVien.setFont(lblNhanVien.getFont().deriveFont(
				lblNhanVien.getFont().getStyle() | Font.BOLD, 20f));
		lblNhanVien.setBounds(50, 245, 175, 32);
		pnl_left_bot.add(lblNhanVien);

		JButton btnIn = new JButton("In");
		btnIn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnIn.setFocusPainted(false);
		btnIn.setBackground(new Color(255, 222, 173));
		btnIn.setBounds(15, 400, 125, 40);
		pnl_left_bot.add(btnIn);

		JButton btnDoiTra = new JButton("Đổi trả");
		btnDoiTra.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnDoiTra.setFocusPainted(false);
		btnDoiTra.setBackground(new Color(244, 221, 212));
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
		cboNhanvien = new JComboBox();
		cboNhanvien.setModel(new DefaultComboBoxModel(a));
		cboNhanvien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboNhanvien.setBounds(15, 285, 285, 35);
		pnl_left_bot.add(cboNhanvien);

		JButton btnLoc = new JButton("Reset");
		btnLoc.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnLoc.setFocusPainted(false);
		btnLoc.setBackground(new Color(255, 255, 102));
		btnLoc.setBounds(105, 336, 100, 35);
		pnl_left_bot.add(btnLoc);
		btnDoiTra.setFocusPainted(false);
		btnIn.setFocusPainted(false);

		JPanel pnl_rdo = new JPanel();
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
		rdoHDDT.setSelected(true);
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

		BUS_HoaDonDoiTra.docDulieu_HDDT(modelHDDT, daoHDDT.getDsHoaDons());

		String sql = "select a.* from HoaDonDoiTra a join HoaDon b on a.maHoaDon=b.maHoaDon join KhachHang c on b.maKhachHang= c.maKhachHang\r\n"
				+ "   where a.maHDDT like '%231124%' and upper(c.tenKhachHang) like upper(N'% %') and c.sdt like N'%0%'";
		List<HoaDonDoiTra> ds = daoHDDT.getDsHDDT_QuerrySQL(sql);
		// System.err.println("ds timkiem :\n"+ds);

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

		tableHDDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tableHDDT.getSelectedRow();
				String mahddt = tableHDDT.getValueAt(row, 0).toString().trim();
				List<ChiTietHoaDonDoiTra> ds = DAO_ChiTietHoaDonDoiTra
						.getDsCTHDDT_theomaHDDT(mahddt);

				BUS_HoaDonDoiTra.docDulieu_CTHDDT(modelCTHDDT, ds);

			}
		});
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

		btnLoc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtMaHD.setText("");
				txtMaKH.setText("");
				txtSDT.setText("");
				txtNgayKT.setDate(new java.util.Date());
				txtNgayBD.setDate(new java.util.Date());
				cboNhanvien.setSelectedItem(a[0]);
				busDT.docDulieu_HDDT(modelHDDT, daoHDDT.getDsHoaDons());
				lblKqTimKiem.setText("");
				busDT.DeleteDataTable(modelCTHDDT);
			}
		});

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

	}

	public void timKiem() {

		String maHDDT = txtMaHD.getText().trim();
		String tenKH = txtMaKH.getText().trim();
		String sdt = txtSDT.getText().trim();

		if (sdt.equals("null") == true || tenKH.equals("null") == true) {
			List<HoaDonDoiTra> ds = daoHDDT.getDsHoaDons();
			List<HoaDonDoiTra> ds2 = new ArrayList<>();
			for (HoaDonDoiTra a : ds) {
				if (a.getHoaDon().getKhachHang() == null)
					ds2.add(a);
			}

			busDT.DeleteDataTable(modelHDDT);
			busDT.DeleteDataTable(modelCTHDDT);
			lblKqTimKiem.setText(ds2.size() + " kết quả");
			busDT.docDulieu_HDDT(modelHDDT, ds2);
		} else {
			String sql = "select a.* from HoaDonDoiTra a join HoaDon b on a.maHoaDon=b.maHoaDon join KhachHang c on b.maKhachHang= c.maKhachHang"
					+ "    where a.maHDDT like '%"
					+ maHDDT
					+ "%' and upper(c.tenKhachHang) like upper(N'%"
					+ tenKH
					+ "%') and c.sdt like N'%" + sdt + "%' ";
			// System.err.println(sql);
			List<HoaDonDoiTra> dshddt = daoHDDT.getDsHDDT_QuerrySQL(sql);

			if (maHDDT.equals(tenKH) && maHDDT.equals(sdt) && maHDDT.equals("")) {
				busDT.DeleteDataTable(modelHDDT);
				busDT.DeleteDataTable(modelCTHDDT);
				lblKqTimKiem.setText("");
				busDT.docDulieu_HDDT(modelHDDT, dshddt);
			} else {
				// System.err.println(sql + "\n");
				if (!dshddt.isEmpty()) {
					busDT.DeleteDataTable(modelHDDT);
					busDT.DeleteDataTable(modelCTHDDT);
					lblKqTimKiem.setText(dshddt.size() + " kết quả");
					busDT.docDulieu_HDDT(modelHDDT, dshddt);
				} else {
					busDT.DeleteDataTable(modelHDDT);
					busDT.DeleteDataTable(modelCTHDDT);
					lblKqTimKiem.setText(dshddt.size() + " kết quả");
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
					+ "' AND HoaDonDoiTra.ngay BETWEEN '"
					+ ngaybd1.format(formatter) + "' AND '"
					+ ngaykt1.format(formatter) + "'";
		} else {
			condition += " HoaDonDoiTra.ngay BETWEEN '"
					+ ngaybd1.format(formatter) + "' AND '"
					+ ngaykt1.format(formatter) + "'";
		}
		// Thực hiện truy vấn SQL với điều kiện tìm kiếm
		String sql = "SELECT HoaDonDoiTra.* FROM HoaDonDoiTra  INNER JOIN NhanVien ON HoaDonDoiTra.maNhanVien = NhanVien.maNhanVien WHERE "
				+ condition;
		// System.err.println(sql);
		List<HoaDonDoiTra> ds = daoHDDT.getDsHDDT_QuerrySQL(sql);
		if (!ds.isEmpty()) {
			busDT.DeleteDataTable(modelHDDT);
			busDT.DeleteDataTable(modelCTHDDT);
			busDT.docDulieu_HDDT(modelHDDT, ds);
		} else {
			busDT.DeleteDataTable(modelHDDT);
			busDT.DeleteDataTable(modelCTHDDT);
		}
		lblKqTimKiem.setText(ds.size() + " kết quả");
		return true;
	}
}
