package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectionManager;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Jpanel_ThongKeKhachHang extends JPanel {

	private DefaultTableModel model;
	private JTable table;
	private JPanel pnlThongKeSP;
	private JPanel pnlHeader;
	private JComboBox cboChon;
	private JComboBox cboNam;
	private JLabel lblNam;
//	private JButton btnThongKe;
	private JPanel pnlCenter;
	private JPanel pnlRight;
	private JButton btnXuatHD;
	private JLabel lblTongTien;
	private JPanel pnlMau;
	private JPanel pnlLeft;
	private JComboBox cboThang;
	private JComboBox cboNgay;
	private JDateChooser txtNgay;
 
	/**
	 * Create the panel.
	 */
	public Jpanel_ThongKeKhachHang() {
		setLayout(null);

		pnlThongKeSP = new JPanel();
		pnlThongKeSP.setBounds(0, 0, 1646, 975);
		add(pnlThongKeSP);
		pnlThongKeSP.setLayout(null);

		pnlHeader = new JPanel();
		pnlHeader.setBounds(0, 0, 1646, 154);
		pnlThongKeSP.add(pnlHeader);
		pnlHeader.setLayout(null);

//		cboChon = new JComboBox();
//		cboChon.setToolTipText("Thống kê theo");
//		cboChon.setBounds(90, 60, 250, 35);
//		cboChon.setBackground(new Color(255, 128, 64));
//		cboChon.setModel(new DefaultComboBoxModel(new String[] { "Năm", "Tháng",
//				"Ngày"}));
//		pnlHeader.add(cboChon);

//		cboNam = new JComboBox();
//		cboNam.setToolTipText("");
//		cboNam.setBounds(490, 60, 100, 35);
//		pnlHeader.add(cboNam);

//		lblNam = new JLabel("Năm");
//		lblNam.setFont(new Font("Tahoma", Font.BOLD, 20));
//		lblNam.setBounds(440, 60, 55, 35);
//		pnlHeader.add(lblNam);

//		
//		cboNam = new JComboBox();
//		cboNam.setToolTipText("");
//		cboNam.setBounds(490, 60, 100, 35);
//		pnlHeader.add(cboNam);
//
//		cboThang = new JComboBox();
//		cboThang.setToolTipText("");
//		cboThang.setBounds(650, 60, 100, 35);
//		pnlHeader.add(cboThang);
//
////		cboNgay = new JComboBox();
////		cboNgay.setToolTipText("");
////		cboNgay.setBounds(810, 60, 100, 35);
////		pnlHeader.add(cboNgay);
////		
//		txtNgay= new JDateChooser();
//		txtNgay.setDateFormatString("dd/MM/yyyy");
//		txtNgay.setBounds(810, 60, 100, 35);
//		txtNgay.setMinSelectableDate(new Date());
//		pnlHeader.add(txtNgay);
		
//		cboChon.addActionListener(new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent e) {
//		        String selectedOption = cboChon.getSelectedItem().toString();
//
//		        // Ẩn tất cả các JComboBox phụ
//		        cboNam.setVisible(false);
//		        cboThang.setVisible(false);
//		        txtNgay.setVisible(false);
//
//		        // Hiển thị JComboBox phụ tương ứng với lựa chọn của người dùng
//		        if (selectedOption.equals("Năm")) {
//		            cboNam.setVisible(true);
//		        } else if (selectedOption.equals("Tháng")) {
//		            cboNam.setVisible(true);
//		            cboThang.setVisible(true);
//		            
//		        } else if (selectedOption.equals("Ngày")) {
//		            txtNgay.setVisible(true);
//		        }
//		    }
//		});

		
		
		
		
		
		
		
		
//		btnThongKe = new JButton("Thống kê");
//		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 15));
//		btnThongKe.setBackground(new Color(152, 251, 152));
//		btnThongKe.setBounds(686, 50, 150, 50);
//		pnlHeader.add(btnThongKe);

		pnlCenter = new JPanel();
		pnlCenter.setBounds(0, 154, 1646, 821);
		pnlThongKeSP.add(pnlCenter);
		pnlCenter.setLayout(null);

		pnlRight = new JPanel();
		pnlRight.setBounds(964, 0, 682, 820);
		pnlCenter.add(pnlRight);
		pnlRight.setLayout(null);

		btnXuatHD = new JButton("Xuất");
		btnXuatHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuatHD.setBackground(new Color(250, 128, 114));
		btnXuatHD.setBounds(300, 710, 150, 50);
		pnlRight.add(btnXuatHD);

//		lblTongTien = new JLabel("Tổng tiền");
//		lblTongTien.setHorizontalAlignment(SwingConstants.CENTER);
//		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
//		lblTongTien.setBounds(300, 659, 145, 25);
//		pnlRight.add(lblTongTien);

//		pnlMau = new JPanel();
//		pnlMau.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
//		pnlMau.setBackground(Color.RED);
//		pnlMau.setBounds(359, 628, 30, 30);
//		pnlRight.add(pnlMau);

		pnlLeft = new JPanel();
		pnlLeft.setBounds(0, 0, 966, 820);
		pnlCenter.add(pnlLeft);
		pnlLeft.setLayout(null);

		String row[] = { "Mã khách hàng", "Tên tên khách", "Số hóa đơn",
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
		dao.DAO_KhachHang.doDuLieuThongKe(table);  // cái tổng tiền đã mua chưa lấy được từ cái tiền tính giá bán nên đưa tạm tienKHDua
		table.setRowHeight(30);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tohoma", Font.BOLD, 18));
		table.setFont(new Font("Tohoma", Font.PLAIN, 18));
		
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn tongTien = columnModel.getColumn(3);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tongTien.setCellRenderer(rightRenderer);
		
		//btnThongKe.setFocusPainted(false);
		//btnXuatHD.setFocusPainted(false);
		btnXuatHD.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        exportToExcel();
		    }
		});
		veBieuDoCot();
		btnXuatHD.setFocusPainted(false);
		 
	}
	private void veBieuDoCot() {
        CategoryDataset dataset = createColumnDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ doanh số bán hàng", "Tên khách hàng", "Số tiền đã mua", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        
        chart.setBackgroundPaint(new Color(0, 0, 0, 0)); // bỏ background
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(null);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());

        // Sử dụng DecimalFormat để định dạng giá trị số
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        rangeAxis.setNumberFormatOverride(decimalFormat);
        
        LegendTitle legend = chart.getLegend();
        
        // Bỏ màu nền của chú thích
        legend.setBackgroundPaint(null);
        legend.setItemFont(new Font("Tahoma", Font.PLAIN, 18));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(69, 52, 581, 517);
        chartPanel.setBorder(null);
        //chartPanel.setBackground(new Color(0, 0, 0, 0));
        pnlRight.add(chartPanel);
        pnlRight.revalidate();
        pnlRight.repaint();
    }

	
	
	// vẽ biểu đồ cột
	private CategoryDataset createColumnDataset() {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;

	    try {
//	        String sqlQuery = "SELECT TOP 5 kh.tenKhachHang, SUM(kh.soTienDaMua) AS tongTien " +
//	                          "FROM KhachHang kh " +
//	                          "LEFT JOIN HoaDon h ON kh.maKhachHang = h.maKhachHang " +
//	                          "GROUP BY kh.tenKhachHang";
	    	  String sqlQuery = "SELECT TOP 5 kh.tenKhachHang, SUM(kh.soTienDaMua) AS tongTien " +
                      "FROM HoaDon h " +
                      "JOIN KhachHang kh ON kh.maKhachHang = h.maKhachHang " +
                      "GROUP BY kh.tenKhachHang";
	        PreparedStatement statement = conn.prepareStatement(sqlQuery);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String tenKhachHang = resultSet.getString("tenKhachHang");
	            double tongTien = resultSet.getDouble("tongTien");

	            dataset.addValue(tongTien, "Tổng tiền", tenKhachHang);
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


	private void exportToExcel() {
	    try {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("ThongKeKhachHang");

	        // Thêm dòng tiêu đề tĩnh (có thể điều chỉnh để in hoa và tô đậm nếu cần)
//	        Row titleRow = sheet.createRow(0);
//	        Cell titleCell = titleRow.createCell(0);
//	        titleCell.setCellValue("THỐNG KÊ KHÁCH HÀNG");
//	        titleCell.setCellStyle(getTitleCellStyle(workbook, sheet));

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
	            sheetRow.setHeightInPoints(20); // Đặt giá trị chiều cao mong muốn (đơn vị là điểm)
	        }

	        // Điều chỉnh chiều rộng của từng cột
	        for (int col = 0; col <= table.getColumnCount(); col++) {
	            sheet.autoSizeColumn(col);
	        }

	        // Đường dẫn tuyệt đối để lưu file Excel
	        String absoluteFilePath = "ThongKe/ThongKeKhachHang.xlsx";

	        try (FileOutputStream outputStream = new FileOutputStream(absoluteFilePath)) {
	            workbook.write(outputStream);
	            thongbao.thongbao("Xuất File thành công!", "Thông báo");
	            System.out.println("Xuất Excel thành công!");
	        } catch (Exception e) {
	            thongbao.thongbao("Xuất File không thành công!", "Thông báo");
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

	private CellStyle getTitleCellStyle(Workbook workbook, Sheet sheet) {
	    CellStyle style = workbook.createCellStyle();
	    org.apache.poi.ss.usermodel.Font font = workbook.createFont();
	    font.setBold(true);
	    font.setFontHeightInPoints((short) 10);
	    font.setColor(IndexedColors.BLACK.getIndex());
	    style.setFont(font);
	    style.setAlignment(HorizontalAlignment.CENTER);
	    style.setVerticalAlignment(VerticalAlignment.CENTER);

	    // Điều chỉnh căn giữa theo chiều ngang
	    style.setAlignment(HorizontalAlignment.CENTER_SELECTION);

	    // Điều chỉnh căn giữa theo chiều dọc
	    style.setVerticalAlignment(VerticalAlignment.CENTER);

	    // Điều chỉnh chiều rộng của cột để căn giữa
	    int numColumns = table.getColumnCount();
	    int totalColumnWidth = 0;
	    for (int col = 0; col < numColumns; col++) {
	        totalColumnWidth += sheet.getColumnWidth(col);
	    }
	    int remainingWidth = sheet.getColumnWidth(numColumns) - totalColumnWidth;
	    int offset = remainingWidth / 2;
	    sheet.setColumnWidth(numColumns, totalColumnWidth + offset);

	    return style;
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
