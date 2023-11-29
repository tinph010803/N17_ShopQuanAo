package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class ThongTinNV extends JPanel {

	/**
	 * Create the panel.
	 */
	public ThongTinNV() {
		setLayout(null);
		
		JPanel pnlThongTinKH = new JPanel();
		pnlThongTinKH.setBounds(0, 0, 1646, 975);
		add(pnlThongTinKH);
		pnlThongTinKH.setLayout(null);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBounds(0, 0, 1646, 425);
		pnlThongTinKH.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JPanel pnlLeft = new JPanel();
		pnlLeft.setBackground(new Color(248, 248, 255));
		pnlLeft.setBounds(15, 16, 530, 393);
		pnlHeader.add(pnlLeft);
		pnlLeft.setLayout(null);
		
		JLabel lblAnh = new JLabel("Ảnh");
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnh.setBounds(160, 40, 180, 180);
		pnlLeft.add(lblAnh);
		
		JButton btnDoiMK = new JButton("Đổi mật khẩu");
		btnDoiMK.setBackground(Color.CYAN);
		btnDoiMK.setBounds(168, 253, 161, 35);
		pnlLeft.add(btnDoiMK);
		
		JLabel lblTen = new JLabel("Phan Hữu Tín");
		lblTen.setBounds(201, 319, 105, 20);
		pnlLeft.add(lblTen);
		
		JLabel lblChucVu = new JLabel("QUẢN LÝ");
		lblChucVu.setHorizontalAlignment(SwingConstants.CENTER);
		lblChucVu.setForeground(Color.RED);
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChucVu.setBounds(201, 355, 95, 29);
		pnlLeft.add(lblChucVu);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(248, 248, 255));
		pnlCenter.setBounds(560, 16, 530, 393);
		pnlHeader.add(pnlCenter);
		pnlCenter.setLayout(null);
		
		JLabel lblAnhNV = new JLabel("New label");
		lblAnhNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnhNV.setBounds(15, 107, 130, 160);
		pnlCenter.add(lblAnhNV);
		
		JLabel lblThongTinNV = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblThongTinNV.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblThongTinNV.setBounds(140, 15, 237, 32);
		pnlCenter.add(lblThongTinNV);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNV.setBounds(178, 65, 140, 25);
		pnlCenter.add(lblMaNV);
		
		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenNV.setBounds(178, 110, 140, 25);
		pnlCenter.add(lblTenNV);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGioiTinh.setBounds(178, 155, 140, 25);
		pnlCenter.add(lblGioiTinh);
		
		JRadioButton rdoNam = new JRadioButton("Nam");
		rdoNam.setBounds(336, 154, 65, 29);
		pnlCenter.add(rdoNam);
		
		JRadioButton rdoNu = new JRadioButton("Nữ");
		rdoNu.setBounds(436, 154, 65, 29);
		pnlCenter.add(rdoNu);
		
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCccd.setBounds(178, 200, 140, 25);
		pnlCenter.add(lblCccd);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSDT.setBounds(178, 245, 140, 25);
		pnlCenter.add(lblSDT);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDiaChi.setBounds(178, 290, 140, 25);
		pnlCenter.add(lblDiaChi);
		
		JLabel lblChucVuNv = new JLabel("Chức vụ:");
		lblChucVuNv.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChucVuNv.setBounds(178, 335, 140, 25);
		pnlCenter.add(lblChucVuNv);
		
		JComboBox cboChucVu = new JComboBox();
		cboChucVu.setBounds(333, 335, 168, 26);
		pnlCenter.add(cboChucVu);
		
		JLabel lblMaNvRong = new JLabel("Mã tự bung");
		lblMaNvRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaNvRong.setBounds(331, 65, 170, 25);
		pnlCenter.add(lblMaNvRong);
		
		JLabel lblTenNvRong = new JLabel("Tên tự bung");
		lblTenNvRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenNvRong.setBounds(331, 107, 170, 25);
		pnlCenter.add(lblTenNvRong);
		
		JLabel lblCCCDRong = new JLabel("CCCD tự bung");
		lblCCCDRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCCCDRong.setBounds(331, 203, 170, 25);
		pnlCenter.add(lblCCCDRong);
		
		JLabel lblSDTRong = new JLabel("Tên tự bung");
		lblSDTRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSDTRong.setBounds(331, 248, 170, 25);
		pnlCenter.add(lblSDTRong);
		
		JLabel lblDiaChiRong = new JLabel("Tên tự bung");
		lblDiaChiRong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiaChiRong.setBounds(331, 293, 170, 25);
		pnlCenter.add(lblDiaChiRong);
		
		JPanel pnlRight = new JPanel();
		pnlRight.setBounds(1105, 16, 530, 393);
		pnlHeader.add(pnlRight);
		pnlRight.setLayout(null);
		pnlRight.setBackground(new Color(248, 248, 255));
		
		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setForeground(new Color(30, 144, 255));
		lblThang.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThang.setBounds(25, 30, 81, 34);
		pnlRight.add(lblThang);
		
		JLabel lblSoHDLap = new JLabel("Số hóa đơn đã lập:");
		lblSoHDLap.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoHDLap.setBounds(25, 110, 185, 30);
		pnlRight.add(lblSoHDLap);
		
		JLabel lblSoHDBan = new JLabel("Số hóa đơn đã bán:");
		lblSoHDBan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoHDBan.setBounds(25, 170, 185, 30);
		pnlRight.add(lblSoHDBan);
		
		JLabel lblThangRong = new JLabel("New label");
		lblThangRong.setBounds(121, 39, 69, 20);
		pnlRight.add(lblThangRong);
		
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongTien.setBounds(25, 230, 108, 30);
		pnlRight.add(lblTongTien);
		
		JLabel lblTongTienRong = new JLabel("10.000.000 VND");
		lblTongTienRong.setBounds(145, 230, 220, 30);
		pnlRight.add(lblTongTienRong);
		
		JLabel lblSoHDLapRong = new JLabel("22");
		lblSoHDLapRong.setBounds(225, 110, 70, 30);
		pnlRight.add(lblSoHDLapRong);
		
		JLabel lblSoHDBanRong = new JLabel("22");
		lblSoHDBanRong.setBounds(225, 170, 70, 30);
		pnlRight.add(lblSoHDBanRong);
		
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBounds(0, 424, 1646, 551);
		pnlThongTinKH.add(pnlFooter);

	}
}
