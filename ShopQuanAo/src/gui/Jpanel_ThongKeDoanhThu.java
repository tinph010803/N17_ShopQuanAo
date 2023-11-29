package gui;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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

import connectDB.ConnectionManager;
import dao.DAO_SanPham;

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

	/**
	 * Create the panel.
	 */
	public Jpanel_ThongKeDoanhThu() {
		setLayout(null);

		pnlThongKeDT = new JPanel();
		pnlThongKeDT.setBounds(0, 0, 1646, 975);
		add(pnlThongKeDT);
		pnlThongKeDT.setLayout(null);

		lblTKDT = new JLabel("Thống kê theo:");
		lblTKDT.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTKDT.setBounds(65, 38, 178, 28);
		pnlThongKeDT.add(lblTKDT);

		cboThongKe = new JComboBox();
		cboThongKe.setBounds(251, 35, 202, 40);
		pnlThongKeDT.add(cboThongKe);

		btnThongKe = new JButton("Thống kê");
		btnThongKe.setBackground(new Color(152, 251, 152));
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThongKe.setBounds(802, 90, 150, 50);
		pnlThongKeDT.add(btnThongKe);

		pnlSoHD = new JPanel();
		pnlSoHD.setLayout(null);
		pnlSoHD.setBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		pnlSoHD.setBackground(new Color(255, 215, 0));
		pnlSoHD.setBounds(65, 205, 270, 240);
		pnlThongKeDT.add(pnlSoHD);

		lblTextSHD = new JLabel("Số hóa đơn");
		lblTextSHD.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTextSHD.setBounds(65, 15, 140, 40);
		pnlSoHD.add(lblTextSHD);

		lblSoHD = new JLabel("a");  // text ghi số hóa đơn
		lblSoHD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoHD.setBounds(47, 108, 175, 35);
		pnlSoHD.add(lblSoHD);

		pnlTongTien = new JPanel();
		pnlTongTien.setLayout(null);
		pnlTongTien.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		pnlTongTien.setBackground(new Color(72, 209, 204));
		pnlTongTien.setBounds(465, 205, 270, 240);
		pnlThongKeDT.add(pnlTongTien);

		lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTongTien.setBounds(65, 15, 140, 40);
		pnlTongTien.add(lblTongTien);

		lblTextTongTien = new JLabel("b"); // text ghi tổng tiền
		lblTextTongTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTextTongTien.setBounds(47, 108, 175, 35);
		pnlTongTien.add(lblTextTongTien);

		pnlVAT = new JPanel();
		pnlVAT.setLayout(null);
		pnlVAT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnlVAT.setBackground(new Color(100, 149, 237));
		pnlVAT.setBounds(67, 580, 270, 240);
		pnlThongKeDT.add(pnlVAT);

		lblTongVAT = new JLabel("Tổng VAT");
		lblTongVAT.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTongVAT.setBounds(65, 15, 140, 40);
		pnlVAT.add(lblTongVAT);

		lblTextVAT = new JLabel("c"); // text ghi tổng VAT
		lblTextVAT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTextVAT.setBounds(47, 108, 175, 35);
		pnlVAT.add(lblTextVAT);

		pnlLoiNhuan = new JPanel();
		pnlLoiNhuan.setLayout(null);
		pnlLoiNhuan.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		pnlLoiNhuan.setBackground(new Color(250, 250, 210));
		pnlLoiNhuan.setBounds(465, 580, 270, 240);
		pnlThongKeDT.add(pnlLoiNhuan);

		lblLoiNhan = new JLabel("Lợi nhuận");
		lblLoiNhan.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblLoiNhan.setBounds(65, 15, 140, 40);
		pnlLoiNhuan.add(lblLoiNhan);

		lblTextLoiNhuan = new JLabel("d"); // text ghi tổng lợi nhuận
		lblTextLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTextLoiNhuan.setBounds(47, 108, 175, 35);
		pnlLoiNhuan.add(lblTextLoiNhuan);

		btnXuat = new JButton("Xuất");
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnXuat.setBackground(new Color(250, 128, 114));
		btnXuat.setBounds(1100, 870, 150, 50);
		pnlThongKeDT.add(btnXuat);

		lblThng = new JLabel("Tháng:");
		lblThng.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThng.setBounds(59, 100, 75, 28);
		pnlThongKeDT.add(lblThng);

		cbThang = new JComboBox();
		cbThang.setBounds(135, 100, 120, 35);
		pnlThongKeDT.add(cbThang);

		lblNewLabel = new JLabel("Quý:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(335, 100, 50, 30);
		pnlThongKeDT.add(lblNewLabel);

		cbQuy = new JComboBox();
		cbQuy.setBounds(385, 100, 120, 35);
		pnlThongKeDT.add(cbQuy);

		lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNam.setBounds(585, 100, 57, 35);
		pnlThongKeDT.add(lblNam);

		cbNam = new JComboBox();
		cbNam.setBounds(640, 100, 120, 35);
		pnlThongKeDT.add(cbNam);
		
		pnlRight = new JPanel();
		pnlRight.setLayout(null);
//		pnlRight.setBounds(964, 0, 682, 820);
//		pnlRight.setBounds(850, 205, 700, 600);
		pnlRight.setBounds(850, 205, 1000, 800);
		pnlThongKeDT.add(pnlRight);
	
		btnThongKe.setFocusPainted(false);
		btnXuat.setFocusPainted(false);
		capNhatSoLuongHD();
		capNhatTongTienHD();
		capNhatTongVAT();
		
		veBieuDoCot();
		
	}
	private void capNhatSoLuongHD() {
		int totalHD=dao.DAO_ThongKe.getTongSoSPDaBan();
		lblSoHD.setText("" + totalHD);
	}
	private void capNhatTongTienHD() {
		DecimalFormat df = new DecimalFormat("#,###");
		double totalTien=dao.DAO_ThongKe.getTongTienHoaDon();
		lblTextTongTien.setText(""+df.format(totalTien));
	}
	private void capNhatTongVAT() {
		DecimalFormat df = new DecimalFormat("#,###");
		double totalVAT=dao.DAO_ThongKe.getTongVAT();
		lblTextVAT.setText(""+df.format(totalVAT));
	}
	private void veBieuDoCot() {
        CategoryDataset dataset = createColumnDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU", "Ngày", "Tổng doanh thu", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        chart.setBackgroundPaint(new Color(0, 0, 0, 0));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(null);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        rangeAxis.setNumberFormatOverride(decimalFormat);

        LegendTitle legend = chart.getLegend();
        legend.setBackgroundPaint(null);
        legend.setItemFont(new Font("Tahoma", Font.PLAIN, 18));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(69, 52, 581, 517);
        chartPanel.setBorder(null);
        chartPanel.setBackground(new Color(0, 0, 0, 0));
        pnlRight.add(chartPanel);
        pnlRight.revalidate();
        pnlRight.repaint();
    }
	 private CategoryDataset createColumnDataset() {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        ConnectionManager connectionManager = new ConnectionManager();
	        Connection conn = connectionManager.conn;

	        try {
//	            String sqlQuery = "SELECT CONVERT(VARCHAR(10), ngay, 103) AS ngayThangNam, SUM(tongTien) AS tongTien " +
//	                    "FROM HoaDon " + "ORDER BY ngayThangNam desc" +
//	                    "GROUP BY CONVERT(VARCHAR(10), ngay, 103)" ;
	            String sqlQuery = "SELECT CONVERT(VARCHAR(10), ngay, 103) AS ngayThangNam, SUM(tongTien) AS tongTien,ngay " +
	                    "FROM HoaDon "  +
	                    "GROUP BY CONVERT(VARCHAR(10), ngay, 103),ngay ORDER BY ngay asc";
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
	
	
}
