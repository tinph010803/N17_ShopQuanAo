package gui;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import connectDB.ConnectionManager;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.general.DefaultPieDataset;

import bus.BUS_ThongKeSanPham;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import dao.DAO_SanPham;
import entity.SanPham;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Jpanel_TKSP_TonKho extends JPanel {
	private DefaultTableModel model;
	private JComponent tbHeader;
	private JTable table;
	private Container pnlTKKH;
	private JTextField txtTim;
	private JPanel pnlThongKe;
	private JPanel pnlChinh;
	private JPanel pnlHeader;
	private JRadioButton rdoTKTonKho;
	private JRadioButton rdoTKSp;
	private JComboBox cboLoai;
	private JPanel pnlCenter;
	private JPanel pnlLeft;
	private JPanel pnlRight;
	private JPanel pnlRightTonKho;
	private JButton btnXuatHD;
	private JLabel lblTongSp;
	private JLabel lblSoTongSp;
	private JLabel lblSoLuong;
	private JLabel lblSoSoLuong;
	private ConnectionManager con = new ConnectionManager();

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public Jpanel_TKSP_TonKho() throws SQLException {
		setLayout(null);

		pnlThongKe = new JPanel();
		pnlThongKe.setBounds(0, 0, 1646, 975);
		pnlThongKe.setBackground(new Color(255, 255, 237));
		add(pnlThongKe);
		pnlThongKe.setLayout(null);

		pnlChinh = new JPanel();
		pnlChinh.setLayout(null);
		pnlChinh.setBounds(0, 0, 1646, 975);
		pnlChinh.setBackground(new Color(255, 255, 237));
		pnlThongKe.add(pnlChinh);

		pnlHeader = new JPanel();
		pnlHeader.setBounds(0, 0, 1646, 154);
		pnlHeader.setBackground(new Color(255, 255, 237));
		pnlChinh.add(pnlHeader);
		pnlHeader.setLayout(null);

		rdoTKTonKho = new JRadioButton("Thống kê tồn kho");
		rdoTKTonKho.setHorizontalAlignment(SwingConstants.CENTER);
		rdoTKTonKho.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdoTKTonKho.setBounds(11, 15, 280, 30);
		rdoTKTonKho.setBackground(new Color(255, 255, 237));
		rdoTKTonKho.setSelected(true);
		pnlHeader.add(rdoTKTonKho);

		rdoTKSp = new JRadioButton("Thống kê sản phẩm đã bán");
		rdoTKSp.setHorizontalAlignment(SwingConstants.CENTER);
		rdoTKSp.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdoTKSp.setBounds(350, 15, 280, 30);
		rdoTKSp.setBackground(new Color(255, 255, 237));
		pnlHeader.add(rdoTKSp);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdoTKSp);
		bg.add(rdoTKTonKho);

		cboLoai = new JComboBox();
		cboLoai.setBounds(65, 80, 148, 35);
		cboLoai.setBackground(new Color(255, 128, 64));
		cboLoai.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"Áo", "Quần", "Váy", "Đầm" }));
		pnlHeader.add(cboLoai);

		txtTim = new JTextField();
		txtTim.setColumns(10);
		txtTim.setBounds(480, 80, 200, 35);
		pnlHeader.add(txtTim);

		JLabel lblNewLabel = new JLabel("Tìm kiếm:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(360, 80, 100, 35);
		pnlHeader.add(lblNewLabel);

		pnlCenter = new JPanel();
		pnlCenter.setBounds(0, 154, 1646, 821);
		pnlCenter.setBackground(new Color(255, 255, 237));
		pnlChinh.add(pnlCenter);
		pnlCenter.setLayout(null);

		pnlLeft = new JPanel();
		pnlLeft.setBounds(0, 0, 966, 820);
		pnlLeft.setBackground(new Color(255, 255, 237));
		pnlCenter.add(pnlLeft);
		pnlLeft.setLayout(null);

		pnlRight = new JPanel();
		pnlRight.setBounds(964, 0, 682, 820);
		pnlRight.setBackground(new Color(255, 255, 237));
		pnlCenter.add(pnlRight);
		pnlRight.setLayout(null);

		btnXuatHD = new JButton("Xuất");
		btnXuatHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuatHD.setBackground(new Color(250, 128, 114));
		btnXuatHD.setBounds(290, 710, 150, 50);
		pnlRight.add(btnXuatHD);

		lblTongSp = new JLabel("Tổng sản phẩm");
		lblTongSp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongSp.setBounds(169, 650, 145, 25);
		pnlRight.add(lblTongSp);

		lblSoTongSp = new JLabel("Số tổng SP");
		lblSoTongSp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoTongSp.setBounds(169, 610, 145, 20);
		lblSoTongSp.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlRight.add(lblSoTongSp);

		lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoLuong.setBounds(415, 650, 145, 25);
		pnlRight.add(lblSoLuong);

		lblSoSoLuong = new JLabel("Số số lượng");
		lblSoSoLuong.setHorizontalAlignment(SwingConstants.CENTER);

		lblSoSoLuong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoSoLuong.setBounds(415, 610, 145, 20);
		pnlRight.add(lblSoSoLuong);

		String row[] = { "Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lượng" };
		model = new DefaultTableModel(row, 0);
		table = new JTable(model);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255,
				153)));
		JTableHeader tbHeader = table.getTableHeader();
		tbHeader.setBackground((Color.decode("#00c691")));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 10));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		scrollPane.setLocation(10, 10);
		scrollPane.setSize(928, 794);
		pnlLeft.add(scrollPane);
		table.setRowHeight(30);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		table.setFont(new Font("Tohoma", Font.PLAIN, 18));
		ConnectionManager connectionManager = new ConnectionManager();

		// thêm dữ liệu vào giao diện
		List<SanPham> ds = DAO_SanPham.getDSSanPham();
		BUS_ThongKeSanPham.docDuLieu_TonKho(model, ds);
		capNhatTongSoSPTonKho();
		capNhatSoLuongSPTonKho();
		veBieuDoTronTonKho();

		// SỰ KIỆN

		// 1. sự kiện tìm kiếm
		cboLoai.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					timKiem();
				}
			}
		});

		txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				timKiem();
			}
		});

		rdoTKTonKho.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlThongKe.removeAll();
				pnlThongKe.setLayout(new BorderLayout());
				try {
					pnlThongKe.add(new Jpanel_TKSP_TonKho());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Tạo một instance mới của Jpanel_HoaDon
				pnlThongKe.revalidate();
				pnlThongKe.repaint();
				rdoTKTonKho.setSelected(true);
				rdoTKSp.setSelected(false);
			}
		});

		rdoTKSp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlThongKe.removeAll();
				pnlThongKe.setLayout(new BorderLayout());
				try {
					pnlThongKe.add(new Jpanel_TKSP_DaBan());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pnlThongKe.revalidate();
				pnlThongKe.repaint();
				rdoTKSp.setSelected(true);
				rdoTKTonKho.setSelected(false);
			}
		});

		btnXuatHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					exportToExcel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnXuatHD.setFocusPainted(false);

	}

	// hàm xử lí tìm kiếm (1)
	public void timKiem() {
		String selectedValue = cboLoai.getSelectedItem().toString();
		String tukhoa = txtTim.getText().trim();

		// tạo điều kiện
		String sql = "select * from SanPham a ";
		if (!selectedValue.equals("Tất cả")) {
			sql += " where a.loai = N'" + selectedValue.trim()
					+ "' and ( upper(a.maSanPham) like upper(N'%" + tukhoa
					+ "%') or upper(a.tenSanPham) like upper(N'%" + tukhoa
					+ "%')  )";
		} else {
			sql += " where ( upper(a.maSanPham) like upper(N'%" + tukhoa
					+ "%') or upper(a.tenSanPham) like upper(N'%" + tukhoa
					+ "%' )) ";
		}
		BUS_ThongKeSanPham.DeleteDataTable(model);
		ConnectionManager con = new ConnectionManager();
		List<SanPham> dsloc = DAO_SanPham.getDsSanPham_Querry(sql);
		BUS_ThongKeSanPham.docDuLieu_TonKho(model, dsloc);
	}

	private void capNhatSoLuongSPTonKho() {
		int totalQuantity = dao.DAO_SanPham.getSoLuongSanPhamConTonKho();

		lblSoSoLuong.setText("" + totalQuantity);
	}

	private void capNhatTongSoSPTonKho() {
		int totalQuantity = dao.DAO_SanPham.getTongSoSPConTonKho();
		lblSoTongSp.setText("" + totalQuantity);
	}

	public void veBieuDoTron() throws SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;
		try {
			String sqlQuery = "SELECT loai, COUNT(*) AS soluong FROM SanPham GROUP BY loai";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			ResultSet resultSet = statement.executeQuery();
			DefaultPieDataset dataset = new DefaultPieDataset();
			while (resultSet.next()) {
				String loai = resultSet.getString("loai");
				int soLuong = resultSet.getInt("soluong");
				dataset.setValue(loai, soLuong);
			}
			JFreeChart chart = ChartFactory.createRingChart(
					"Biểu đồ thống kê sản phẩm đã bán", dataset, true, true,
					false);
			chart.setBackgroundPaint(null);
			LegendTitle legend = chart.getLegend();
			legend.setBackgroundPaint(new Color(0, 0, 0, 0));
			legend.setItemFont(new Font("Tahoma", Font.PLAIN, 18)); // Đặt kích
																	// thước
																	// font
			legend.setPosition(RectangleEdge.RIGHT); // Đặt vị trí của chú th

			RingPlot plot = (RingPlot) chart.getPlot();
			// Xóa màu nền trắng ở ngoài biểu đồ
			plot.setBackgroundPaint(null); // Đặt màu nền của plot thành màu
											// trắng trong suốt
			plot.setOutlineVisible(false);

			// Tắt viền cho các phần tử
			plot.setSectionOutlinesVisible(false);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBounds(69, 52, 581, 517);
			chartPanel.setBackground(new Color(255, 255, 237));
			chartPanel.setBorder(null);
			// pnlRight.removeAll(); // Trước khi thêm biểu đồ mới, xóa hết các
			// thành phần trong pnlRight
			pnlRight.repaint(); // Gọi repaint trên panel chứa biểu đồ

			pnlRight.add(chartPanel);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void veBieuDoTronTonKho() throws SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;
		try {
			ResultSet rs = connectionManager
					.getdata("SELECT loai, SUM(soLuong) AS soluong FROM SanPham GROUP BY loai");
			DefaultPieDataset dataset = new DefaultPieDataset();
			while (rs.next()) {
				String loai = rs.getString("loai");
				int soLuong = rs.getInt("soluong");
				dataset.setValue(loai, soLuong);
			}
			JFreeChart chart = ChartFactory.createRingChart(
					"Biểu đồ thống kê sản phẩm tồn kho", dataset, true, true,
					false);
			chart.setBackgroundPaint(null);
			LegendTitle legend = chart.getLegend();
			legend.setBackgroundPaint(new Color(0, 0, 0, 0));
			legend.setItemFont(new Font("Tahoma", Font.PLAIN, 18));
			legend.setPosition(RectangleEdge.RIGHT);

			RingPlot plot = (RingPlot) chart.getPlot();
			plot.setBackgroundPaint(null);
			plot.setOutlineVisible(false);
			plot.setSectionOutlinesVisible(false);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBounds(69, 52, 581, 517);
			chartPanel.setBackground(new Color(255, 255, 237));
			chartPanel.setBorder(null);
			// pnlRight.removeAll(); // Trước khi thêm biểu đồ mới, xóa hết các
			// thành phần trong pnlRight
			pnlRight.repaint(); // Gọi repaint trên panel chứa biểu đồ

			pnlRight.add(chartPanel);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Thêm phương thức xóa biểu đồ cũ
	private void xoaBieuDoCu() {
		Component[] components = pnlRight.getComponents();
		for (Component component : components) {
			if (component instanceof ChartPanel) {
				pnlRight.remove(component);
			}
		}
	}

	private void exportToExcel() throws IOException {
		int nutbam = JOptionPane.showConfirmDialog(new JFrame(),
				"Bạn chắc chắn xuất file ?", "", JOptionPane.YES_NO_OPTION);
		if (nutbam == JOptionPane.YES_OPTION) {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("DanhSachSanPhamTonKho");

			// Tạo dòng tiêu đề
			Row headerRow = sheet.createRow(1);

			// Thêm cột STT vào đầu tiên
			Cell sttHeaderCell = headerRow.createCell(0);
			sttHeaderCell.setCellValue("STT");
			sttHeaderCell.setCellStyle(getHeaderCellStyle(workbook));
			sheet.autoSizeColumn(0);

			// Thêm các cột từ bảng vào tiếp theo
			for (int col = 0; col < table.getColumnCount(); col++) {
				Cell cell = headerRow.createCell(col + 1);
				cell.setCellValue(table.getColumnName(col));
				cell.setCellStyle(getHeaderCellStyle(workbook));

				// Điều chỉnh chiều rộng của mỗi cột trong tiêu đề
				sheet.setColumnWidth(col + 1, 15 * 256);
			}

			// Thêm dữ liệu từ bảng
			for (int row = 0; row < table.getRowCount(); row++) {
				Row sheetRow = sheet.createRow(row + 2);
				Cell sttCell = sheetRow.createCell(0);
				sttCell.setCellValue(row + 1);
				sttCell.setCellStyle(getCellTextStyle(workbook, true));
				sheet.setColumnWidth(0, 0 * 256);

				for (int col = 0; col < table.getColumnCount(); col++) {
					Cell cell = sheetRow.createCell(col + 1);
					cell.setCellValue(String.valueOf(table.getValueAt(row, col)));
					cell.setCellStyle(getCellTextStyle(workbook, false));
				}

				// Điều chỉnh chiều cao của từng dòng
				sheetRow.setHeightInPoints(20); // Đặt giá trị chiều cao mong
												// muốn (đơn vị là điểm)
			}

			// Điều chỉnh chiều rộng của từng cột
			for (int col = 0; col <= table.getColumnCount(); col++) {
				sheet.autoSizeColumn(col);
			}

			// Lưu workbook vào tệp Excel
			try (FileOutputStream outputStream = new FileOutputStream(
					"ThongKe/DanhSachSanPhamTonKho.xlsx")) {
				workbook.write(outputStream);
				thongbao.thongbao("Xuất Excel thành công!", "Thống báo");
			} catch (Exception e) {
				e.printStackTrace();
				thongbao.thongbao("Xuất File thất bại!", "Thông báo");
			}

			workbook.close();
		}
	}

	private CellStyle getHeaderCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);
		return style;
	}

	private CellStyle getCellTextStyle(Workbook workbook, boolean isBold) {
		CellStyle style = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
		font.setBold(isBold);
		font.setFontHeightInPoints((short) 12);
		font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		return style;
	}
}
