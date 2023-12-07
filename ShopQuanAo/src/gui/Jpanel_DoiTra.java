package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import bus.BUS_DoiTra;
import connectDB.ConnectionManager;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoaDonDoiTra;
import dao.DAO_HoaDon;
import dao.DAO_HoaDonDoiTra;
import dao.DAO_SanPham;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.NhanVien;
import entity.SanPham;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class Jpanel_DoiTra extends JPanel {

	private String cl_yellow = "#fcbe00";
	private String cl_green = "#00c691";
	private DefaultTableModel modelCTHD;
	private DefaultTableModel modelDSDT;
	private JPanel pnlDoiTra;
	private JPanel pnl_center;
	private JButton btnDoiTra;
	private JTableHeader tbHeader;
	private JTableHeader tbHeader1;
	private JButton btnQuayLai;
	private JButton btnChon = null;
	private JTable tableDSDT;
	private JScrollPane scrollPane1;
	private JTable tableCTHD;
	private JScrollPane scrollPane;
	private JLabel lblVATRong;
	private BUS_DoiTra busdt = new BUS_DoiTra();
	private JTextField txtSoLuong;
	private List<ChiTietHoaDonDoiTra> ds = new ArrayList<>();
	private ConnectionManager connectionManager = new ConnectionManager();
	private DecimalFormat df = new DecimalFormat("#,###,###");
	/**
	 * Create the panel.
	 */
	public Jpanel_DoiTra(HoaDon hd, NhanVien nvhientai) {
		setLayout(null);

		pnlDoiTra = new JPanel();
		pnlDoiTra.setBounds(0, 0, 1673, 1053);
		add(pnlDoiTra);
		pnlDoiTra.setLayout(null);
		setLayout(null);

		pnl_center = new JPanel();
		pnl_center.setBounds(420, 0, 1224, 975);
		pnlDoiTra.add(pnl_center);
		pnl_center.setLayout(null);

		JPanel pnlRightTop = new JPanel();
		pnlRightTop.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pnlRightTop.setBounds(0, 0, 1224, 431);
		pnl_center.add(pnlRightTop);
		pnlRightTop.setLayout(null);

		JLabel lblCTHD = new JLabel("Chi tiết hóa đơn");
		lblCTHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblCTHD.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCTHD.setBounds(445, 35, 210, 35);
		pnlRightTop.add(lblCTHD);

		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnThem.setFocusPainted(false);
		btnThem.setBackground(Color.GREEN);
		btnThem.setBounds(1090, 228, 115, 38);
		pnlRightTop.add(btnThem);

		
		String row[] = {"Mã SP", "Tên SP",  "Loại", "Màu sắc",
				"Kích thước", "Số lượng","Được đổi","Giá bán","Tiền KM","Thành tiền" ,"Tiền Thuế","Tiền Bậc","Tổng Tiền"  };
		
		modelCTHD = new DefaultTableModel(row, 0);
		tableCTHD = new JTable(modelCTHD);
		tableCTHD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255, 153)));
		tbHeader = ((JTable) tableCTHD).getTableHeader();
		scrollPane = new JScrollPane(tableCTHD);
		scrollPane.setBounds(15, 85, 1053, 330);
		pnlRightTop.add(scrollPane);
		scrollPane.setBorder(null);
		
		TableColumnModel columnModelCTHD = tableCTHD.getColumnModel();
		TableColumn soLuong = columnModelCTHD.getColumn(5);
		TableColumn duocDoi = columnModelCTHD.getColumn(6);
		TableColumn gia = columnModelCTHD.getColumn(7);
		TableColumn khuyenMai = columnModelCTHD.getColumn(8);
		TableColumn thanhTien = columnModelCTHD.getColumn(9);
		TableColumn tienThue = columnModelCTHD.getColumn(10);
		TableColumn tienBac = columnModelCTHD.getColumn(11);
		TableColumn tongTien = columnModelCTHD.getColumn(12);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		soLuong.setCellRenderer(rightRenderer);
		soLuong.setCellRenderer(rightRenderer);
		gia.setCellRenderer(rightRenderer);
		khuyenMai.setCellRenderer(rightRenderer);
		thanhTien.setCellRenderer(rightRenderer);
		tienThue.setCellRenderer(rightRenderer);
		tienBac.setCellRenderer(rightRenderer);
		tongTien.setCellRenderer(rightRenderer);

		txtSoLuong = new JTextField("1");
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setBounds(1130, 167, 35, 30);
		pnlRightTop.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		JButton btnGiam = new JButton("-");
		btnGiam.setBounds(1080, 167, 43, 30);
		pnlRightTop.add(btnGiam);

		JButton btnTang = new JButton("+");
		btnTang.setBounds(1170, 167, 43, 30);
		pnlRightTop.add(btnTang);

		JPanel pnlRightBot = new JPanel();
		pnlRightBot.setBounds(0, 431, 1224, 544);
		pnl_center.add(pnlRightBot);
		pnlRightBot.setLayout(null);

		
		String rowCTHDDT[] = {"Mã Sản Phẩm", "Tên sản phẩm",  "Loại", "Màu sắc",
				"Kích thước", "Số lượng","Giá bán","Thành tiền","Tiền được giảm","Tiền hoàn trả"  };
		
		modelDSDT = new DefaultTableModel(rowCTHDDT, 0);

		JLabel lblDSDoiTra = new JLabel("Danh sách đổi trả");
		lblDSDoiTra.setBounds(445, 20, 210, 35);
		pnlRightBot.add(lblDSDoiTra);
		lblDSDoiTra.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSDoiTra.setFont(new Font("Tahoma", Font.BOLD, 24));
		tableDSDT = new JTable(modelDSDT);
		tableDSDT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255, 153)));
		tbHeader1 = ((JTable) tableDSDT).getTableHeader();
		scrollPane1 = new JScrollPane(tableDSDT);
		scrollPane1.setBounds(15, 70, 1053, 318);
		pnlRightBot.add(scrollPane1);
		scrollPane1.setBorder(null);
		
		
		
		
		TableColumnModel columnModelDSDT = tableDSDT.getColumnModel();
		TableColumn soluong = columnModelDSDT.getColumn(5);
		TableColumn giaBan = columnModelDSDT.getColumn(6);
		TableColumn thanhtien = columnModelDSDT.getColumn(7);
		TableColumn tienGiam = columnModelDSDT.getColumn(8);
		TableColumn tienTra = columnModelDSDT.getColumn(9);
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		soluong.setCellRenderer(rightRenderer);
		giaBan.setCellRenderer(rightRenderer);
		thanhtien.setCellRenderer(rightRenderer);
		tienGiam.setCellRenderer(rightRenderer);
		tienTra.setCellRenderer(rightRenderer);
		
		
		btnDoiTra = new JButton("Đổi trả");
		btnDoiTra.setBounds(214, 466, 115, 38);
		pnlRightBot.add(btnDoiTra);
		btnDoiTra.setBackground(new Color(250, 128, 114));
		btnDoiTra.setFont(new Font("Tahoma", Font.BOLD, 19));

		btnDoiTra.setFocusPainted(false);

		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setBounds(540, 466, 115, 38);
		pnlRightBot.add(btnQuayLai);
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnQuayLai.setBackground(Color.CYAN);
		btnQuayLai.setFocusPainted(false);
		
		JLabel lblTongTienTra = new JLabel("Tổng Tiền Trả :");
		lblTongTienTra.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongTienTra.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongTienTra.setBounds(799, 429, 115, 30);
		pnlRightBot.add(lblTongTienTra);
		
		JLabel lblTbTien = new JLabel("");
		lblTbTien.setForeground(new Color(255, 0, 0));
		lblTbTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTbTien.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblTbTien.setBounds(926, 429, 142, 30);
		pnlRightBot.add(lblTbTien);

		btnQuayLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() != btnChon) {
					btnQuayLai.setBackground(Color.decode("#DA81F5"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (e.getSource() != btnChon) {
					btnQuayLai.setBackground(Color.cyan);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlDoiTra.removeAll();
				pnlDoiTra.setLayout(new BorderLayout());
				pnlDoiTra.add(new Jpanel_HoaDon(nvhientai));
				pnlDoiTra.revalidate();
				pnlDoiTra.repaint();

			}
		});

		JPanel pnlLeft = new JPanel();
		pnlLeft.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 0, 0)));
		pnlLeft.setBounds(0, 0, 420, 975);
		pnlDoiTra.add(pnlLeft);
		pnlLeft.setLayout(null);

		JLabel lblHD = new JLabel("Hóa đơn");
		lblHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblHD.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblHD.setBounds(135, 35, 210, 35);
		pnlLeft.add(lblHD);

		JLabel lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMaHD.setBounds(30, 160, 190, 40);
		pnlLeft.add(lblMaHD);

		JLabel lblNgayLapHD = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNgayLapHD.setBounds(30, 220, 190, 40);
		pnlLeft.add(lblNgayLapHD);

		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMaKH.setBounds(30, 280, 190, 40);
		pnlLeft.add(lblMaKH);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSDT.setBounds(30, 340, 190, 40);
		pnlLeft.add(lblSDT);

		JLabel lblNV = new JLabel("Nhân viên:");
		lblNV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNV.setBounds(30, 400, 190, 40);
		pnlLeft.add(lblNV);

		JLabel lblMaHDRong = new JLabel();
		lblMaHDRong.setText(busdt.updateMa());
		lblMaHDRong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaHDRong.setBounds(220, 160, 173, 40);
		pnlLeft.add(lblMaHDRong);
		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		JLabel lblNgayLapHDRong = new JLabel(d.format(LocalDate.now()));
		lblNgayLapHDRong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgayLapHDRong.setBounds(220, 220, 173, 40);
		pnlLeft.add(lblNgayLapHDRong);
		
		String tenkh,sdt;
		if(hd.getKhachHang()==null) {
			tenkh="Khách vãng lai";
			sdt="xxx";
		}
		else {
			tenkh= hd.getKhachHang().getTenKhachHang();
			sdt= hd.getKhachHang().getSdt();
		}
		JLabel lblMaKHRong = new JLabel(tenkh);
		lblMaKHRong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaKHRong.setBounds(220, 280, 173, 40);
		pnlLeft.add(lblMaKHRong);

		JLabel lblsdtKH = new JLabel(sdt);
		lblsdtKH.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblsdtKH.setBounds(220, 340, 173, 40);
		pnlLeft.add(lblsdtKH);

		JLabel lblTenNV = new JLabel(nvhientai.getTenNhanVien());
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTenNV.setBounds(220, 400, 173, 40);
		pnlLeft.add(lblTenNV);

		
		
		busdt.docDuLieu_CTHD(modelCTHD,hd.getMaHoaDon());
//				sự kiện 
		
		
		btnGiam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a = txtSoLuong.getText().trim();
				if (a.equals("")) {
					thongbao.thongbao("Yêu cầu nhập số", "Chú ý");
				} else {
					int soluong = Integer.valueOf(a) - 1;
					if (soluong <= 0)
						soluong = 0;
					txtSoLuong.setText(String.valueOf(soluong));
				}

			}
		});

		btnTang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a = txtSoLuong.getText().trim();
				if (a.equals("")) {
					thongbao.thongbao("Yêu cầu nhập số", "Chú ý");
				} else {
					int row = tableCTHD.getSelectedRow();
					if (row != -1) {
						int sLHienCo = Integer.valueOf(tableCTHD.getValueAt(row, 5).toString().trim());

						int soluong = Integer.valueOf(a) + 1;
						if (soluong >= sLHienCo)
							soluong = sLHienCo;
						txtSoLuong.setText(String.valueOf(soluong));
					} else {
						thongbao.thongbao("Yêu cầu chọn sản phẩm đổi trả", "Chú ý");
					}

				}

			}
		});

		btnThem.addActionListener(new ActionListener() {
			private int tong;

			public void actionPerformed(ActionEvent arg0) {
				int row = tableCTHD.getSelectedRow();
			
				ChiTietHoaDonDoiTra az;
				SanPham sp = null;
				if (row == -1) {
					thongbao.thongbao("Yêu cầu chọn sản phẩm ", "Chú ý");
				} else {

					int sLHienCo = Integer.valueOf(tableCTHD.getValueAt(row, 6).toString().trim());
					int slchon = Integer.valueOf(txtSoLuong.getText().trim());
					String masp = tableCTHD.getValueAt(row, 0).toString().trim();
//					System.err.println(sLHienCo + " - "+ slchon +" - " + masp);
					
					az = checkSP(masp);
					if (az == null) {
						if (sLHienCo < slchon) {
							thongbao.thongbao("Số lượng đổi không đúng yêu cầu chọn lại", "Chú ý");
						} 
						else {
							ChiTietHoaDon cthdsp = DAO_ChiTietHoaDon.layCTHDtheoMaSp(hd.getMaHoaDon().trim(), masp);
							double tien = cthdsp.getTienCuoiCung() / cthdsp.getSoLuong() * slchon;
//							System.err.println("tien :" + tien);
							az = new ChiTietHoaDonDoiTra(DAO_SanPham.laySanPhamTheoMa(masp), null, slchon,
									tien * 70 / 100);

							sp = az.getSanPham();
							ds.add(az);
							tableCTHD.setValueAt(String.valueOf(sLHienCo - slchon), row, 6);
							busdt.docDuLieu_CTHDDT(modelDSDT, ds);
							tableCTHD.clearSelection();
						}
						
					
					} else {
						if (sLHienCo < slchon) {
							thongbao.thongbao("Số lượng đổi không đúng yêu cầu chọn lại", "Chú ý");
						} 
						else {
							int sl = az.getSoLuong();
							az.setSoLuong(sl + slchon);
							ChiTietHoaDon cthdsp= DAO_ChiTietHoaDon.layCTHDtheoMaSp(hd.getMaHoaDon().trim(), masp);
//							System.err.println(cthdsp);
							double tien =cthdsp.getTienCuoiCung()/cthdsp.getSoLuong()*az.getSoLuong();
//							System.err.println(cthdsp.getSanPham().getTenSanPham() +"  -    :"+tien);
							az.setTienTra(tien*70/100);
							sp = az.getSanPham();
							
							tableCTHD.setValueAt(String.valueOf(sLHienCo - slchon), row,6 );
							busdt.docDuLieu_CTHDDT(modelDSDT, ds);
							tableCTHD.clearSelection();
						}
					

					}
					 tong =0;
					for (ChiTietHoaDonDoiTra e : ds) {
						tong+=e.getTienTra();
					}
					lblTbTien.setText(df.format(tong));
				}
			}

		});

		btnDoiTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HoaDonDoiTra hddt = new HoaDonDoiTra(busdt.updateMa(), hd, nvhientai);
//				System.err.println(hddt);
				double tong=0;
				for (ChiTietHoaDonDoiTra a : ds) {
					a.setHoaDonDoiTra(hddt);
					tong+=a.getTienTra();
				}
				
				hddt.setTongTienHoanTra(tong);
				
//				System.err.println("\n" + ds);
//				System.err.println(hddt);
				DAO_HoaDonDoiTra.themHoaDonDoiTra(hddt);
				ds.forEach(e -> {
					if(!DAO_ChiTietHoaDonDoiTra.themDsChiTietHoaDon(e)) {
						thongbao.thongbao("Thêm không thành công", "chú ý");
					}else{
						dao.DAO_SanPham.capNhatSoLuongTang(e.getSanPham(), e.getSoLuong());
					}
				});
				
				pnlDoiTra.removeAll();
				pnlDoiTra.setLayout(new BorderLayout());
				pnlDoiTra.add(new Jpanel_HoaDon(nvhientai));
				pnlDoiTra.revalidate();
				pnlDoiTra.repaint();
			}
		});
	}

	public ChiTietHoaDonDoiTra checkSP(String masp) {
		for (ChiTietHoaDonDoiTra a : ds) {
			if (a.getSanPham().getMaSanPham().trim().equals(masp))
				return a;
		}
		return null;
	}
}
