package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectionManager;
import dao.DAO_SanPham;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.SwingConstants;

public class Jpanel_ThongKeDoanhThu extends JPanel {

	private JPanel pnlThongKeDT;
	private JLabel lblTKDT;
	private JComboBox cboThongKe;
	private JButton btnThongKe;
	private JPanel pnlSoHD;
	private JLabel lblTextSHD;
	private JLabel lblSoHD;
	private JPanel pnlTongTien;
	private JLabel lblTongTien;
	private JLabel lblTextTongTien;
	private JPanel pnlVAT;
	private JLabel lblTongVAT;
	private JLabel lblTextVAT;
	private JPanel pnlLoiNhuan;
	private JLabel lblLoiNhan;
	private JLabel lblTextLoiNhuan;
	private JButton btnXuat;
	private JLabel lblThng;
	private JComboBox cbThang;
	private JLabel lblNewLabel;
	private JComboBox cbQuy;
	private JLabel lblNam;
	private JComboBox cbNam;
	private JPanel pnlRight;
	private JDateChooser txtTimNgayBD;
	private JDateChooser txtTimNgayKT;
	private JButton btnLammoi;

	/**
	 * Create the panel.
	 */
	public Jpanel_ThongKeDoanhThu() {
		setLayout(null);

		pnlThongKeDT = new JPanel();
		pnlThongKeDT.setBounds(12, 13, 1646, 962);
		pnlThongKeDT.setBackground(new Color(255, 255, 237));
		//pnlRight.setBackground(new Color(255, 255, 237));
		add(pnlThongKeDT);
		pnlThongKeDT.setLayout(null);

		pnlSoHD = new JPanel();
		pnlSoHD.setLayout(null);
		pnlSoHD.setBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		pnlSoHD.setBackground(new Color(255, 215, 0));
		pnlSoHD.setBounds(63, 180, 270, 94);
		pnlThongKeDT.add(pnlSoHD);

		lblTextSHD = new JLabel("Số hóa đơn");
		lblTextSHD.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTextSHD.setBounds(69, 0, 140, 40);
		pnlSoHD.add(lblTextSHD);

		lblSoHD = new JLabel("a"); // text ghi số hóa đơn
		lblSoHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoHD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoHD.setBounds(49, 46, 175, 35);
		pnlSoHD.add(lblSoHD);

		pnlTongTien = new JPanel();
		pnlTongTien.setLayout(null);
		pnlTongTien.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		pnlTongTien.setBackground(new Color(72, 209, 204));
		pnlTongTien.setBounds(372, 180, 270, 94);
		pnlThongKeDT.add(pnlTongTien);

		lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTongTien.setBounds(74, 0, 140, 40);
		pnlTongTien.add(lblTongTien);

		lblTextTongTien = new JLabel("b"); // text ghi tổng tiền
		lblTextTongTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextTongTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTextTongTien.setBounds(49, 46, 175, 35);
		pnlTongTien.add(lblTextTongTien);

		pnlVAT = new JPanel();
		pnlVAT.setLayout(null);
		pnlVAT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnlVAT.setBackground(new Color(100, 149, 237));
		pnlVAT.setBounds(698, 180, 270, 94);
		pnlThongKeDT.add(pnlVAT);

		lblTongVAT = new JLabel("Tổng VAT");
		lblTongVAT.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTongVAT.setBounds(75, 0, 140, 40);
		pnlVAT.add(lblTongVAT);

		lblTextVAT = new JLabel("c"); // text ghi tổng VAT
		lblTextVAT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextVAT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTextVAT.setBounds(49, 46, 175, 35);
		pnlVAT.add(lblTextVAT);

		btnXuat = new JButton("Xuất");
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnXuat.setBackground(new Color(250, 128, 114));
		btnXuat.setBounds(1461, 600, 138, 50);
		pnlThongKeDT.add(btnXuat);

		JLabel lblTu = new JLabel("Từ");
		lblTu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTu.setBounds(59, 100, 75, 28);
		pnlThongKeDT.add(lblTu);
		// jtextfield ngày
		txtTimNgayBD = new JDateChooser();
		txtTimNgayBD.setDateFormatString("dd/MM/yyyy");
		txtTimNgayBD.setBounds(104, 100, 120, 35);
		pnlThongKeDT.add(txtTimNgayBD);

		JLabel lblDen = new JLabel("Đến");
		lblDen.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDen.setBounds(257, 99, 50, 30);
		pnlThongKeDT.add(lblDen);
		// jtextfield ngày
		txtTimNgayKT = new JDateChooser();
		txtTimNgayKT.setDateFormatString("dd/MM/yyyy");
		txtTimNgayKT.setBounds(319, 100, 120, 35);
		pnlThongKeDT.add(txtTimNgayKT);

		pnlRight = new JPanel();
		pnlRight.setLayout(null);
		pnlRight.setBackground(new Color(255, 255, 237));
		// pnlRight.setBounds(964, 0, 682, 820);
		pnlRight.setBounds(43, 362, 1441, 600);
		pnlThongKeDT.add(pnlRight);

		JLabel lblTngQuanBo = new JLabel("Báo Cáo Doanh Thu");
		lblTngQuanBo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTngQuanBo.setBounds(43, 45, 410, 28);
		pnlThongKeDT.add(lblTngQuanBo);

		pnlLoiNhuan = new JPanel();
		pnlLoiNhuan.setBounds(1025, 180, 270, 94);
		pnlThongKeDT.add(pnlLoiNhuan);
		pnlLoiNhuan.setLayout(null);
		pnlLoiNhuan.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		pnlLoiNhuan.setBackground(new Color(250, 250, 210));

		lblLoiNhan = new JLabel("Lợi nhuận");
		lblLoiNhan.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblLoiNhan.setBounds(77, 0, 140, 40);
		pnlLoiNhuan.add(lblLoiNhan);

		lblTextLoiNhuan = new JLabel("d"); // text ghi tổng lợi nhuận
		lblTextLoiNhuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTextLoiNhuan.setBounds(49, 46, 175, 35);
		pnlLoiNhuan.add(lblTextLoiNhuan);

		btnLammoi = new JButton("Reset");
		btnLammoi.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnLammoi.setBackground(Color.CYAN);
		btnLammoi.setBounds(495, 90, 150, 45);
		pnlThongKeDT.add(btnLammoi);

		btnXuat.setFocusPainted(false);
		capNhatSoLuongHD();
		capNhatTongTienHD();
		capNhatTongVAT();
		capNhatTongLoiNhuan();

		veBieuDoCot();

		txtTimNgayKT.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if ("date".equals(evt.getPropertyName())) {
							timKiemThongKe();
						}
					}
				});

		btnXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatDuLieuExcel();
			}
		});
		btnLammoi.setFocusPainted(false);
		btnLammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reset giá trị ngày cho JDateChooser
				xoaBieuDoCu();

				// Cập nhật lại tất cả các giá trị ban đầu
				capNhatSoLuongHD();
				capNhatTongTienHD();
				capNhatTongVAT();
				capNhatTongLoiNhuan();

				// Reset giá trị của JDateChooser
				txtTimNgayBD.setDate(null);
				txtTimNgayKT.setDate(null);
				// Vẽ lại biểu đồ cột với dữ liệu mặc định
				veBieuDoCot();

				// Hiển thị lại dữ liệu trên giao diện
				pnlThongKeDT.revalidate();
				pnlThongKeDT.repaint();
			}
		});

	}

	private void xoaBieuDoCu() {
		Component[] components = pnlRight.getComponents();
		for (Component component : components) {
			if (component instanceof ChartPanel) {
				pnlRight.remove(component);
			}
		}
	}

	private void capNhatSoLuongHD() {
		int totalHD = dao.DAO_ThongKe.getTongSoHD();
		lblSoHD.setText("" + totalHD);
	}

	private void capNhatSoLuongHDTheoNgay() {
		Date ngayBD = txtTimNgayBD.getDate();
		Date ngayKT = txtTimNgayKT.getDate();
		lblSoHD.setText("" + dao.DAO_ThongKe.getSoLuongHD(ngayBD, ngayKT));

	}

	private void capNhatTongTienHD() {
		DecimalFormat df = new DecimalFormat("#,###");
		double totalTien = dao.DAO_ThongKe.getTongTienHoaDon();
		lblTextTongTien.setText("" + df.format(totalTien));
	}

	private void capNhatTongTienHDTheoNgay() {
		Date ngayBD = txtTimNgayBD.getDate();
		Date ngayKT = txtTimNgayKT.getDate();
		DecimalFormat df = new DecimalFormat("#,###");
		lblTextTongTien.setText(""
				+ df.format(dao.DAO_ThongKe.getTongTienHD(ngayBD, ngayKT)));
	}

	private void capNhatTongVAT() {
		DecimalFormat df = new DecimalFormat("#,###");
		double totalVAT = dao.DAO_ThongKe.getTongVAT();
		lblTextVAT.setText("" + df.format(totalVAT));
	}

	private void capNhatTongVATTheoNgay() {
		Date ngayBD = txtTimNgayBD.getDate();
		Date ngayKT = txtTimNgayKT.getDate();
		DecimalFormat df = new DecimalFormat("#,###");
		lblTextVAT.setText(""
				+ df.format(dao.DAO_ThongKe.getTongVAT(ngayBD, ngayKT)));
	}

	private void capNhatTongLoiNhuan() {
		DecimalFormat df = new DecimalFormat("#,###");
		double totalLoiNhuan = dao.DAO_ThongKe.getTongLoiNhuan();
		lblTextLoiNhuan.setText("" + df.format(totalLoiNhuan));
	}

	private void capNhatTongLoiNhuanTheoNgay() {
		Date ngayBD = txtTimNgayBD.getDate();
		Date ngayKT = txtTimNgayKT.getDate();
		DecimalFormat df = new DecimalFormat("#,###");
		lblTextLoiNhuan.setText(""
				+ df.format(dao.DAO_ThongKe.getTongLoiNhuanTheoNgay(ngayBD,
						ngayKT)));
	}

	private void veBieuDoCot() {
		CategoryDataset dataset = createColumnDataset();
		JFreeChart chart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU",
				"Ngày", "Tổng doanh thu", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		chart.setBackgroundPaint(new Color(0, 0, 0, 0));
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(null);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());

		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		rangeAxis.setNumberFormatOverride(decimalFormat);

		LegendTitle legend = chart.getLegend();
		legend.setBackgroundPaint(null);
		legend.setItemFont(new Font("Tahoma", Font.PLAIN, 18));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(69, 52, 1250, 550);
		chartPanel.setBorder(null);
		// chartPanel.setBackground(new Color(0, 0, 0, 0));
		chartPanel.setBackground(new Color(255, 255, 237));
		pnlRight.add(chartPanel);
		pnlRight.revalidate();
		pnlRight.repaint();
	}

	private CategoryDataset createColumnDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;
		try {
			String sqlQuery = "SELECT CONVERT(VARCHAR(10), ngay, 103) AS ngayThangNam, SUM(tongTien) AS tongTien,ngay "
					+ "FROM HoaDon "
					+ "GROUP BY CONVERT(VARCHAR(10), ngay, 103),ngay ORDER BY ngay asc";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String ngayThangNam = resultSet.getString("ngayThangNam");
				double tongTien = resultSet.getDouble("tongTien");

				dataset.addValue(tongTien, "Tổng tiền", ngayThangNam);
			}

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

		return dataset;
	}

	private boolean validateNgayTimKiem() {
		Date ngayBD = txtTimNgayBD.getDate();
		Date ngayKT = txtTimNgayKT.getDate();

		if (ngayBD == null || ngayKT == null) {

			return false;
		}

		if (ngayBD.after(ngayKT)) {

			thongbao.thongbao("Ngày kết thúc không thể trước ngày bắt đầu.", "");
			txtTimNgayKT.requestFocus();
			return false;
		}

		return true;
	}

	private void timKiemThongKe() {
		if (validateNgayTimKiem()) {
			Date ngayBD = txtTimNgayBD.getDate();
			Date ngayKT = txtTimNgayKT.getDate();

			// Gọi các phương thức cập nhật dữ liệu dựa trên khoảng ngày đã chọn
			capNhatSoLuongHDTheoNgay();
			capNhatTongTienHDTheoNgay();
			capNhatTongVATTheoNgay();
			capNhatTongLoiNhuanTheoNgay();
			// Cập nhật biểu đồ cột
			veBieuDoCot(ngayBD, ngayKT);
		}
	}

	private CategoryDataset createColumnDataset(Date ngayBD, Date ngayKT) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;
		try {
			String sqlQuery = "SELECT CONVERT(VARCHAR(10), ngay, 103) AS ngayThangNam, SUM(tongTien) AS tongTien, ngay "
					+ "FROM HoaDon "
					+ "WHERE ngay BETWEEN ? AND ? "
					+ "GROUP BY CONVERT(VARCHAR(10), ngay, 103), ngay ORDER BY ngay asc";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setDate(1, new java.sql.Date(ngayBD.getTime()));
			statement.setDate(2, new java.sql.Date(ngayKT.getTime()));
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String ngayThangNam = resultSet.getString("ngayThangNam");
				double tongTien = resultSet.getDouble("tongTien");

				dataset.addValue(tongTien, "Tổng tiền", ngayThangNam);
			}

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

		return dataset;
	}

	private void veBieuDoCot(Date ngayBD, Date ngayKT) {
		CategoryDataset dataset = createColumnDataset(ngayBD, ngayKT);
		JFreeChart chart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU",
				"Ngày", "Tổng doanh thu", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		chart.setBackgroundPaint(new Color(0, 0, 0, 0));
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(null);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());

		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		rangeAxis.setNumberFormatOverride(decimalFormat);

		LegendTitle legend = chart.getLegend();
		legend.setBackgroundPaint(null);
		legend.setItemFont(new Font("Tahoma", Font.PLAIN, 18));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(69, 52, 1250, 500);
		chartPanel.setBorder(null);
		chartPanel.setBackground(new Color(255, 255, 237));
		pnlRight.removeAll();
		pnlRight.add(chartPanel);
		pnlRight.revalidate();
		pnlRight.repaint();
	}

	private void xuatDuLieuExcel() {

		int nutbam = JOptionPane.showConfirmDialog(new JFrame(),
				"Bạn chắc chắn xuất file ?", "", JOptionPane.YES_NO_OPTION);
		if (nutbam == JOptionPane.YES_OPTION) {
			try {
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet("ThongKeDoanhThu");

				// Tạo dòng cho tiêu đề
				Row rowTitle = sheet.createRow(0);
				Cell cellTitle = rowTitle.createCell(0);
				cellTitle.setCellValue("Thông Tin Thống Kê Doanh Thu");
				cellTitle.setCellStyle(getBoldCellStyle(workbook));

				// Tạo dòng cho các tiêu đề cụ thể
				Row rowHeaders = sheet.createRow(2);
				createCell(rowHeaders, 0, "Số hóa đơn");
				createCell(rowHeaders, 1, "Tổng tiền");
				createCell(rowHeaders, 2, "Tổng VAT");
				createCell(rowHeaders, 3, "Lợi nhuận");

				// Tạo dòng cho giá trị
				Row rowValues = sheet.createRow(3);
				createCell(rowValues, 0, lblSoHD.getText());
				createCell(rowValues, 1, lblTextTongTien.getText());
				createCell(rowValues, 2, lblTextVAT.getText());
				createCell(rowValues, 3, lblTextLoiNhuan.getText());

				// Lấy giá trị ngày từ txtTimNgayBD và txtTimNgayKT
				Date ngayBDValue = txtTimNgayBD.getDate();
				Date ngayKTValue = txtTimNgayKT.getDate();

				String uniqueFileName;
				if (ngayBDValue != null && ngayKTValue != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"ddMMyyyy");
					String ngayBD = dateFormat.format(ngayBDValue);
					String ngayKT = dateFormat.format(ngayKTValue);

					// Tạo tên tệp tin dựa trên ngày
					uniqueFileName = "ThongKe/ThongKeDoanhThu_" + ngayBD + "_"
							+ ngayKT + ".xlsx";
				} else {
					// Trường hợp không chọn ngày, giữ nguyên tên cũ hoặc có thể
					// đặt
					// tên mới theo logic mong muốn
					uniqueFileName = "ThongKe/ThongKeDoanhThu_Default.xlsx";
				}

				// Lưu workbook xuống tệp tin Excel với tên độc đáo
				try (FileOutputStream fileOut = new FileOutputStream(
						uniqueFileName)) {
					workbook.write(fileOut);
					thongbao.thongbao("Xuất File thành công!", "Thông báo");
				}

				workbook.close();

			} catch (Exception e) {
				e.printStackTrace();
				thongbao.thongbao("Xuất File thất bại!", "Thông báo");
			}
		}
	}

	private void createCell(Row row, int columnIndex, String value) {
		Cell cell = row.createCell(columnIndex);
		cell.setCellValue(value);
	}

	private CellStyle getBoldCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);
		return style;
	}

}
