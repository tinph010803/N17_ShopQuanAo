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
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

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
import java.util.Iterator;
import java.util.List;

import dao.DAO_SanPham;
import entity.SanPham;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Jpanel_TKSP_DaBan extends JPanel {
	private DefaultTableModel model;
	private JComponent tbHeader;
	private JTable table;
	private Container pnlTKKH;
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
	private JLabel lblTbSLLoaiSP;
	private JLabel lblSoLuong;
	private JLabel lblTbSoLuong;
	private ConnectionManager con = new ConnectionManager();
	private JLabel lblTongTien;

	private JLabel lblTien;
	private JLabel lblTbTien;
	private JComboBox cbosoLuong;
	private JComboBox cboTien;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public Jpanel_TKSP_DaBan() throws SQLException {
		setLayout(null);

		pnlThongKe = new JPanel();
		pnlThongKe.setBounds(0, 0, 1646, 975);
		add(pnlThongKe);
		pnlThongKe.setLayout(null);

		pnlChinh = new JPanel();
		pnlChinh.setLayout(null);
		pnlChinh.setBounds(0, 0, 1646, 975);
		pnlThongKe.add(pnlChinh);

		pnlHeader = new JPanel();
		pnlHeader.setBounds(0, 0, 1646, 154);
		pnlChinh.add(pnlHeader);
		pnlHeader.setLayout(null);

		rdoTKTonKho = new JRadioButton("Thống kê tồn kho");
		rdoTKTonKho.setHorizontalAlignment(SwingConstants.CENTER);
		rdoTKTonKho.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdoTKTonKho.setBounds(11, 15, 280, 30);

		pnlHeader.add(rdoTKTonKho);

		rdoTKSp = new JRadioButton("Thống kê sản phẩm đã bán");
		rdoTKSp.setHorizontalAlignment(SwingConstants.CENTER);
		rdoTKSp.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdoTKSp.setBounds(350, 15, 280, 30);
		rdoTKSp.setSelected(true);
		pnlHeader.add(rdoTKSp);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdoTKSp);
		bg.add(rdoTKTonKho);

		cboLoai = new JComboBox();
		cboLoai.setBounds(63, 91, 120, 35);
		cboLoai.setBackground(new Color(255, 128, 64));
		cboLoai.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"Áo", "Quần", "Váy", "Đầm" }));
		pnlHeader.add(cboLoai);

		cbosoLuong = new JComboBox();
		cbosoLuong.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"10", "20", "50" }));
		cbosoLuong.setBackground(new Color(255, 128, 64));
		cbosoLuong.setBounds(277, 91, 120, 35);
		pnlHeader.add(cbosoLuong);

		cboTien = new JComboBox();
		cboTien.setModel(new DefaultComboBoxModel(new String[] { "Tất cả",
				"500,000 VND", "1,000,000 VND", "2,000,000 VND",
				"5,000,000 VND", "10,000,000 VND" }));
		cboTien.setBackground(new Color(255, 128, 64));
		cboTien.setBounds(510, 91, 120, 35);
		pnlHeader.add(cboTien);

		JLabel lblLocLoaiSP = new JLabel("Loại sản phẩm");
		lblLocLoaiSP.setBounds(86, 68, 87, 20);
		pnlHeader.add(lblLocLoaiSP);

		JLabel lblLocSoLuong = new JLabel("Số lượng ");
		lblLocSoLuong.setBounds(296, 68, 87, 20);
		pnlHeader.add(lblLocSoLuong);

		JLabel lblLocTien = new JLabel("Tiền");
		lblLocTien.setBounds(523, 68, 87, 20);
		pnlHeader.add(lblLocTien);

		pnlCenter = new JPanel();
		pnlCenter.setBounds(0, 154, 1646, 821);
		pnlChinh.add(pnlCenter);
		pnlCenter.setLayout(null);

		pnlLeft = new JPanel();
		pnlLeft.setBounds(0, 0, 966, 820);
		pnlCenter.add(pnlLeft);
		pnlLeft.setLayout(null);

		pnlRight = new JPanel();
		pnlRight.setBounds(964, 0, 682, 820);
		pnlCenter.add(pnlRight);
		pnlRight.setLayout(null);

		btnXuatHD = new JButton("Xuất");
		btnXuatHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuatHD.setBackground(new Color(250, 128, 114));
		btnXuatHD.setBounds(290, 710, 150, 50);
		pnlRight.add(btnXuatHD);

		lblTongSp = new JLabel("Số loại sản phẩm ");
		lblTongSp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongSp.setBounds(92, 650, 188, 25);
		pnlRight.add(lblTongSp);

		lblTbSLLoaiSP = new JLabel("Số tổng SP");
		lblTbSLLoaiSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblTbSLLoaiSP.setBounds(90, 610, 145, 20);
		lblTbSLLoaiSP.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlRight.add(lblTbSLLoaiSP);

		lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoLuong.setBounds(290, 650, 145, 25);
		pnlRight.add(lblSoLuong);

		lblTbSoLuong = new JLabel("Số số lượng");
		lblTbSoLuong.setHorizontalAlignment(SwingConstants.CENTER);

		lblTbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTbSoLuong.setBounds(290, 610, 145, 20);
		pnlRight.add(lblTbSoLuong);

		String row[] = { "Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lượng",
				"Tổng tiền" };
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
		scrollPane.setLocation(30, 0);
		scrollPane.setSize(908, 804);
		pnlLeft.add(scrollPane);

		table.setRowHeight(30);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		table.setFont(new Font("Tohoma", Font.PLAIN, 18));
		lblTien = new JLabel("Tổng Tiền");
		lblTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTien.setBounds(493, 650, 145, 25);
		pnlRight.add(lblTien);

		lblTbTien = new JLabel("");
		lblTbTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTbTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTbTien.setBounds(493, 610, 145, 20);
		pnlRight.add(lblTbTien);

		// thêm dữ liệu lên bảng
		ConnectionManager connectionManager = new ConnectionManager();
		List<String> ds = DAO_SanPham.getDuLieu_SPDaBan();
		BUS_ThongKeSanPham.docDuLieu_DaBan(model, ds);
		capNhatThongTin();
		veBieuDoTron();

		// SỰ KIỆN

		rdoTKTonKho.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlThongKe.removeAll();
				pnlThongKe.setLayout(new BorderLayout());
				try {
					pnlThongKe.add(new JPanel_TKSP_TonKho());
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

		// 1. sự kiện tìm kiếm
		cboLoai.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Loc();
				}
			}
		});

		cbosoLuong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Loc();
				}
			}
		});

		cboTien.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Loc();
				}
			}
		});

		btnXuatHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					exportToExcel(table.getModel());

				} catch (Exception ex) {
					ex.printStackTrace();

				}
			}
		});

	}

	public void Loc() {
		String loai = cboLoai.getSelectedItem().toString();
		String tien = cboTien.getSelectedItem().toString();
		String soLuong = cbosoLuong.getSelectedItem().toString();

		String query1 = "SELECT c.maSanPham,SUM(c.soLuong) AS soLuongDaBan, sum(c.tienCuoiCung) as tongtien "
				+ " \n FROM ChiTietHoaDon c "
				+ "\n JOIN SanPham p ON c.maSanPham = p.maSanPham ";

		String query2 = " \n GROUP BY c.maSanPham, p.tenSanPham,p.giaNhap, p.loai";

		if (loai.trim().equals("Tất cả")) {
			if (soLuong.trim().equals("Tất cả")) {
				if (tien.trim().equals("Tất cả")) {
					query1 += query2;
					// System.err.println(query1);
					System.out.println("Dạng 01 : L-SL-T");// L-SL-T
				} else {
					tien = tien.replace(",", "");
					tien = tien.replace(" VND", "");
					query1 += query2 + "\n having  sum(c.tienCuoiCung) >= "
							+ tien + " ";
					// System.err.println(query1);
					System.out.println("Dạng 02 : L-SL-#");// L-SL-#
				}
			} else {
				if (tien.trim().equals("Tất cả")) {
					int sl = Integer.valueOf(soLuong.trim());
					query1 += query2 + "\n having  SUM(c.soLuong) >= " + sl
							+ "";
					// System.err.println(query1);
					System.out.println("Dạng 03 : L-#-SL");// L-#-SL
				} else {
					tien = tien.replace(",", "");
					tien = tien.replace(" VND", "");
					int sl = Integer.valueOf(soLuong.trim());
					query1 += query2 + "\n having  sum(c.tienCuoiCung) >= "
							+ tien + " and  SUM(c.soLuong) >= " + sl + " ";
					// System.err.println(query1);
					System.out.println("Dạng 04 : L-#-#");// L-#-#
				}
			}
		} else {
			if (soLuong.trim().equals("Tất cả")) {
				if (tien.trim().equals("Tất cả")) {
					query1 += "\n where upper(p.loai) = upper(N'" + loai + "')"
							+ query2;
					// System.err.println(query1);
					System.out.println("Dạng 05 : #-SL-T");// #-SL-T

				} else {
					tien = tien.replace(",", "");
					tien = tien.replace(" VND", "");
					query1 += "\n where upper(p.loai) = upper(N'" + loai + "')"
							+ query2;
					query1 += "\n having  sum(c.tienCuoiCung) >= " + tien + " ";
					// System.err.println(query1);
					System.out.println("Dạng 06 : #-SL-#");// #-SL-#
				}
			} else {
				if (tien.trim().equals("Tất cả")) {
					int sl = Integer.valueOf(soLuong.trim());
					query1 += "\n where upper(p.loai) = upper(N'" + loai + "')"
							+ query2;
					query1 += "\n having  SUM(c.soLuong) >= " + sl + "";
					// System.err.println(query1);
					System.out.println("Dạng 07 : #-SL-T");// #-#-T

				} else {
					tien = tien.replace(",", "");
					tien = tien.replace(" vnd", "");
					int sl = Integer.valueOf(soLuong.trim());
					query1 += "\n where upper(p.loai) = upper(N'" + loai + "')"
							+ query2;
					query1 += "\n having  sum(c.tienCuoiCung) >= " + tien
							+ " and  SUM(c.soLuong) >= " + sl + " ";
					// System.err.println(query1);
					System.out.println("Dạng 08 : #-SL-#");// #-#-#
				}
			}
		}
		List<String> dsLoc = DAO_SanPham.getDuLieu_Loc(query1);
		BUS_ThongKeSanPham.docDuLieu_DaBan(model, dsLoc);
	}

	private void capNhatThongTin() {
		ConnectionManager con = new ConnectionManager();
		List<String> ds = new DAO_SanPham().getDuLieu_SPDaBan();
		DecimalFormat df = new DecimalFormat("#,###");
		int count = 0;
		double tong = 0;
		for (String e : ds) {
			String[] str = e.split(":");
			count += Integer.valueOf(str[1]);
			tong += Double.valueOf(str[2]);
		}
		;
		lblTbSLLoaiSP.setText("" + ds.size());
		lblTbSoLuong.setText("" + count);
		lblTbTien.setText(df.format(tong));
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
			chartPanel.setBorder(null);
			// pnlRight.removeAll(); // Trước khi thêm biểu đồ mới, xóa hết các
			// thành phần
			// trong pnlRight
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
			chartPanel.setBorder(null);
			// pnlRight.removeAll(); // Trước khi thêm biểu đồ mới, xóa hết các
			// thành phần
			// trong pnlRight
			pnlRight.repaint(); // Gọi repaint trên panel chứa biểu đồ

			pnlRight.add(chartPanel);

			lblTongTien = new JLabel("Tổng tiền ");
			lblTongTien.setHorizontalAlignment(SwingConstants.CENTER);
			lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblTongTien.setBounds(496, 650, 145, 25);
			pnlRight.add(lblTongTien);

			lblTbTien = new JLabel("");
			lblTbTien.setHorizontalAlignment(SwingConstants.CENTER);
			lblTbTien.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblTbTien.setBounds(496, 610, 145, 20);
			pnlRight.add(lblTbTien);
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

	private void exportToExcel(TableModel model) throws Exception {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("DanhSachSanPhamDaBan");

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
			sheetRow.setHeightInPoints(20); // Đặt giá trị chiều cao mong muốn
											// (đơn vị là điểm)
		}

		// Điều chỉnh chiều rộng của từng cột
		for (int col = 0; col <= table.getColumnCount(); col++) {
			sheet.autoSizeColumn(col);
		}

		// Lưu workbook xuống file
		try (FileOutputStream outputStream = new FileOutputStream(
				"ThongKe/DanhSachSanPhamDaBan.xlsx")) {
			workbook.write(outputStream);
			thongbao.thongbao("Xuất File thành công!", "Thông báo");
		} catch (Exception e) {
			e.printStackTrace();
			thongbao.thongbao("Xuất File thất bại!", "Thông báo");
		}
		workbook.close();

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
