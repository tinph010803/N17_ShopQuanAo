package gui;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.HashMap;
import java.util.Map;

import connectDB.ConnectionManager;
import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;
import javax.swing.border.MatteBorder;

public class Jpanel_CaNhan extends JPanel implements ActionListener {

	private int tongSoSanPham;
	private double tongTien;
	private int i;
	private double tongTienThang1;
	private double tongTienThang2;
	private double tongTienThang3;
	private double tongTienThang4;
	private double tongTienThang5;
	private double tongTienThang12;
	private double tongTienThang11;
	private double tongTienThang9;
	private double tongTienThang8;
	private double tongTienThang7;
	private double tongTienThang6;
	private double tongTienThang10;
	public NhanVien nhanVien = new NhanVien();

	/**
	 * Create the panel.
	 */
	public Jpanel_CaNhan(NhanVien nv, TaiKhoan vt) {
		setLayout(null);

		JPanel pnlCaNhan = new JPanel();
		pnlCaNhan.setBounds(0, 0, 1646, 975);
		add(pnlCaNhan);
		pnlCaNhan.setLayout(null);

		JPanel pnlTop = new JPanel();
		pnlTop.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pnlTop.setBackground(new Color(255, 255, 237));
		pnlTop.setBounds(0, 0, 1646, 426);
		pnlCaNhan.add(pnlTop);
		pnlTop.setLayout(null);

		JPanel pnlLeft = new JPanel();
		pnlLeft.setBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		pnlLeft.setBackground(new Color(255, 255, 237));
		pnlLeft.setBounds(15, 16, 530, 393);
		pnlTop.add(pnlLeft);
		pnlLeft.setLayout(null);

		nhanVien = nv;

		JLabel lblAnh = new JLabel("Ảnh");
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnh.setBounds(170, 25, 180, 220);
		pnlLeft.add(lblAnh);

		ImageIcon ii = new ImageIcon(new ImageIcon("IMG_NhanVien//"
				+ nhanVien.getHinhAnh()).getImage().getScaledInstance(
				lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH));
		lblAnh.setIcon(ii);

		JButton btnDoiMK = new JButton("Đổi mật khẩu");
		btnDoiMK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDoiMK.setBackground(Color.CYAN);
		btnDoiMK.setBounds(180, 255, 160, 35);
		pnlLeft.add(btnDoiMK);

		JLabel lblTen = new JLabel(nv.getTenNhanVien());
		lblTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTen.setBounds(158, 320, 200, 30);
		pnlLeft.add(lblTen);

		JLabel lblChucVu = new JLabel();
		lblChucVu.setHorizontalAlignment(SwingConstants.CENTER);
		lblChucVu.setForeground(Color.RED);
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChucVu.setBounds(180, 355, 160, 29);
		pnlLeft.add(lblChucVu);
		DAO_TaiKhoan daotk = new DAO_TaiKhoan();

		if (daotk.getTKtheoMa(nv.getMaNhanVien()).isVaiTro()) {
			lblChucVu.setText("QUẢN LÝ");
		} else {
			lblChucVu.setText("NHÂN VIÊN");
		}

		JPanel pnlCenter = new JPanel();
		pnlCenter.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				0)));
		pnlCenter.setBackground(new Color(255, 255, 237));
		pnlCenter.setBounds(560, 16, 530, 393);
		pnlTop.add(pnlCenter);
		pnlCenter.setLayout(null);

		JLabel lblThongTinNV = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblThongTinNV.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblThongTinNV.setBounds(140, 15, 237, 32);
		pnlCenter.add(lblThongTinNV);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNV.setBounds(90, 85, 140, 25);
		pnlCenter.add(lblMaNV);

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenNV.setBounds(90, 130, 140, 25);
		pnlCenter.add(lblTenNV);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGioiTinh.setBounds(90, 175, 140, 25);
		pnlCenter.add(lblGioiTinh);

		JRadioButton rdoNam = new JRadioButton("Nam");
		rdoNam.setBackground(new Color(255, 255, 237));
		rdoNam.setBounds(245, 175, 65, 29);
		rdoNam.setSelected(true);
		pnlCenter.add(rdoNam);

		JRadioButton rdoNu = new JRadioButton("Nữ");
		rdoNu.setBackground(new Color(255, 255, 237));
		rdoNu.setBounds(345, 175, 65, 29);
		pnlCenter.add(rdoNu);

		ButtonGroup a = new ButtonGroup();
		a.add(rdoNu);
		a.add(rdoNam);

		if (!nv.isGioiTinh())
			rdoNu.setSelected(true);
		//

		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCccd.setBounds(90, 220, 140, 25);
		pnlCenter.add(lblCccd);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSDT.setBounds(90, 265, 140, 25);
		pnlCenter.add(lblSDT);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDiaChi.setBounds(90, 310, 140, 25);
		pnlCenter.add(lblDiaChi);

		JLabel lblMaNvRong = new JLabel(nv.getMaNhanVien());
		lblMaNvRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaNvRong.setBounds(245, 85, 270, 25);
		pnlCenter.add(lblMaNvRong);

		JLabel lblTenNvRong = new JLabel(nv.getTenNhanVien());
		lblTenNvRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenNvRong.setBounds(245, 130, 270, 25);
		pnlCenter.add(lblTenNvRong);

		JLabel lblCCCDRong = new JLabel(nv.getCccd());
		lblCCCDRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCCCDRong.setBounds(245, 220, 270, 25);
		pnlCenter.add(lblCCCDRong);

		JLabel lblSDTRong = new JLabel(nv.getSdt());
		lblSDTRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSDTRong.setBounds(245, 265, 270, 25);
		pnlCenter.add(lblSDTRong);

		JLabel lblDiaChiRong = new JLabel(nv.getDiaChi());
		lblDiaChiRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiaChiRong.setBounds(245, 310, 270, 25);
		pnlCenter.add(lblDiaChiRong);

		JPanel pnlRight = new JPanel();
		pnlRight.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				0)));
		pnlRight.setBounds(1105, 16, 530, 393);
		pnlTop.add(pnlRight);
		pnlRight.setLayout(null);
		pnlRight.setBackground(new Color(255, 255, 237));

		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setForeground(new Color(30, 144, 255));
		lblThang.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblThang.setBounds(25, 30, 81, 35);
		pnlRight.add(lblThang);

		JLabel lblSoSPDB = new JLabel("Số sản phẩm đã bán:");
		lblSoSPDB.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoSPDB.setBounds(25, 110, 200, 30);
		pnlRight.add(lblSoSPDB);

		JLabel lblSoHDBan = new JLabel("Số hóa đơn đã bán:");
		lblSoHDBan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoHDBan.setBounds(25, 170, 200, 30);
		pnlRight.add(lblSoHDBan);

		JLabel lblThangRong = new JLabel();
		lblThangRong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThangRong.setBounds(120, 30, 100, 35);
		pnlRight.add(lblThangRong);
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
		String formattedDate = currentDate.format(formatter);
		lblThangRong.setText(formattedDate);

		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongTien.setBounds(25, 230, 200, 30);
		pnlRight.add(lblTongTien);
		JLabel lblTongTienRong = new JLabel();
		lblTongTienRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTongTienRong.setBounds(235, 230, 220, 30);
		pnlRight.add(lblTongTienRong);
		DecimalFormat df = new DecimalFormat("#,###,###");
		// ResultSet rs2 =
		// ConnectionManager.getdata("SELECT SUM(tongTien) AS TongTien FROM HoaDon WHERE maNhanVien = maNhanVien and MONTH(ngay) = MONTH(getdate()) AND YEAR(ngay) = YEAR(getdate());");
		ResultSet rs2 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTien FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nv.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = MONTH(getdate()) AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (rs2.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTien = rs2.getDouble("TongTien");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Đặt giá trị lên JLabel
		lblTongTienRong.setText(df.format(tongTien) + " VND");

		JLabel lblSoSPDBRong = new JLabel();
		lblSoSPDBRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoSPDBRong.setBounds(235, 110, 155, 30);
		pnlRight.add(lblSoSPDBRong);
		ResultSet rs1 = ConnectionManager
				.getdata("SELECT SUM(soLuong) AS SoLuongSanPhamBanDuoc FROM ChiTietHoaDon cthd JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon WHERE hd.maNhanVien = "
						+ "N'"
						+ nv.getMaNhanVien().trim()
						+ "'"
						+ " AND MONTH(hd.ngay) = MONTH(getdate())  AND YEAR(hd.ngay) = YEAR(getdate());");
		try {
			while (rs1.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongSoSanPham = rs1.getInt("SoLuongSanPhamBanDuoc");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Đặt giá trị lên JLabel
		lblSoSPDBRong.setText(Integer.toString(tongSoSanPham));

		JLabel lblSoHDBanRong = new JLabel();
		lblSoHDBanRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoHDBanRong.setBounds(235, 170, 155, 30);
		pnlRight.add(lblSoHDBanRong);
		ResultSet rs = ConnectionManager
				.getdata("SELECT COUNT(*) AS SoLuongHoaDon FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nv.getMaNhanVien().trim()
						+ "'"
						+ " AND MONTH(ngay) = MONTH(getdate())  AND YEAR(ngay) = YEAR(getdate());");
		i = 1;
		try {
			while (rs.next()) {
				i = rs.getInt("SoLuongHoaDon");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblSoHDBanRong.setText(Integer.toString(i));

		JPanel pnlBot = new JPanel();
		pnlBot.setBackground(new Color(255, 255, 237));
		pnlBot.setBounds(0, 424, 1646, 551);
		pnlCaNhan.add(pnlBot);

		pnlBot.setLayout(new BorderLayout());
		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		pnlBot.add(chartPanel, BorderLayout.CENTER);

		btnDoiMK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoiMatKhau frm = new DoiMatKhau(nv.getMaNhanVien());
				frm.setVisible(true);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		ResultSet thang01 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang1 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 1 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang01.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang1 = thang01.getDouble("TongTienThang1");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang02 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang2 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 2 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang02.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang2 = thang02.getDouble("TongTienThang2");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ResultSet thang03 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang3 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 3 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang03.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang3 = thang03.getDouble("TongTienThang3");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang04 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang4 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 4 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang04.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang4 = thang04.getDouble("TongTienThang4");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang05 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang5 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 5 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang05.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang5 = thang05.getDouble("TongTienThang5");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang06 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang6 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 6 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang06.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang6 = thang06.getDouble("TongTienThang6");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang07 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang7 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 7 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang07.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang7 = thang07.getDouble("TongTienThang7");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang08 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang8 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 8 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang08.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang8 = thang08.getDouble("TongTienThang8");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ResultSet thang09 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang9 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 9 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang09.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang9 = thang09.getDouble("TongTienThang9");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang10 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang10 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 10 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang10.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang10 = thang10.getDouble("TongTienThang10");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang11 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang11 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 11 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang11.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang11 = thang11.getDouble("TongTienThang11");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		ResultSet thang12 = ConnectionManager
				.getdata("SELECT SUM(tongTien) AS TongTienThang12 FROM HoaDon WHERE maNhanVien = "
						+ "N'"
						+ nhanVien.getMaNhanVien().trim()
						+ "'"
						+ " and MONTH(ngay) = 12 AND YEAR(ngay) = YEAR(getdate());");
		try {
			while (thang12.next()) {
				// Lấy giá trị từ cột "TongSoSanPham"
				tongTienThang12 = thang12.getDouble("TongTienThang12");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		dataset.addValue(tongTienThang1, "Tổng Tiền", "Tháng 1");
		dataset.addValue(tongTienThang2, "Tổng Tiền", "Tháng 2");
		dataset.addValue(tongTienThang3, "Tổng Tiền", "Tháng 3");
		dataset.addValue(tongTienThang4, "Tổng Tiền", "Tháng 4");
		dataset.addValue(tongTienThang5, "Tổng Tiền", "Tháng 5");
		dataset.addValue(tongTienThang6, "Tổng Tiền", "Tháng 6");
		dataset.addValue(tongTienThang7, "Tổng Tiền", "Tháng 7");
		dataset.addValue(tongTienThang8, "Tổng Tiền", "Tháng 8");
		dataset.addValue(tongTienThang9, "Tổng Tiền", "Tháng 9");
		dataset.addValue(tongTienThang10, "Tổng Tiền", "Tháng 10");
		dataset.addValue(tongTienThang11, "Tổng Tiền", "Tháng 11");
		dataset.addValue(tongTienThang12, "Tổng Tiền", "Tháng 12");
		return dataset;
	}

	private JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart("Thống Kê", // Tiêu đề
																	// biểu đồ
				"", // Nhãn trục x
				"Tổng Tiền", // Nhãn trục y
				dataset, // Dữ liệu
				PlotOrientation.VERTICAL, true, true, false);

		// Thay đổi màu sắc và font chữ
		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setPaint(Color.blue);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(202, 225, 255));
		plot.setRangeGridlinePaint(Color.white);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

		domainAxis.setTickLabelPaint(Color.black);

		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setTickLabelPaint(Color.black);

		return chart;
	}

}