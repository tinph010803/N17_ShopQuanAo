package gui;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;

import java.awt.Color;


import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import entity.ChiTietHoaDon;
import entity.HoaDon;

public class DanhSachCho extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String cl_yellow = "#fcbe00";
	private String cl_green = "#00c691";
	private String cl_greyblue = "#f0f6fb";
	private JTextField txtTimkiem;
	private JTable tableHD;
	private DefaultTableModel modelHD;
	private JTable tableCTHD;
	private DefaultTableModel modelCTHD;
	private JPanel pn_Top;
	private JButton btnThanhToan;
	private JButton btnXoa;
	private JPanel pn_Bot_center;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// DanhSachCho frame = new DanhSachCho();
	// frame.setVisible(true);
	// frame.setLocationRelativeTo(null);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	public static List<HoaDon> dsHoaDonCho = new ArrayList<HoaDon>();
	public static List<List<ChiTietHoaDon>> listDanhSachCTHDCho = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public DanhSachCho() {
		setResizable(false);
		setBounds(100, 100, 1201, 948);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.setBackground(Color.decode(cl_greyblue));

		JPanel pn_center = new JPanel();
		pn_center.setBackground(Color.decode(cl_greyblue));
		pn_center.setBounds(0, 69, 1200, 880);
		contentPane.add(pn_center);
		pn_center.setLayout(null);

		JLabel lbl_TimKiem = new JLabel("Tìm kiếm  : ");
		lbl_TimKiem.setBounds(527, 8, 123, 25);
		lbl_TimKiem.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_TimKiem.setIcon(new ImageIcon(
				"N:\\Phát Triển Ứng Dụng\\Code\\Icon\\search.png"));
		pn_center.add(lbl_TimKiem);

		txtTimkiem = new JTextField();
		txtTimkiem.setSelectionColor(new Color(255, 128, 0));
		txtTimkiem.setCaretColor(new Color(255, 0, 0));
		txtTimkiem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0,
				0, 0)));
		txtTimkiem.setBounds(660, 10, 150, 23);
		txtTimkiem.setFont(new Font("Arial", Font.ITALIC, 15));
		txtTimkiem.setForeground(Color.black);

		pn_center.add(txtTimkiem);
		txtTimkiem.setColumns(10);
		txtTimkiem.setBackground(Color.decode(cl_greyblue));

		JPanel pn_Top_center = new JPanel();
		pn_Top_center.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		pn_Top_center.setBounds(0, 56, 1200, 342);
		pn_center.add(pn_Top_center);

		String row[] = { "Tên khách hàng", "Số điện thoại", "Tổng tiền",
				"Tổng tiền cần thu" };
		modelHD = new DefaultTableModel(row, 0);
		pn_Top_center.setLayout(null);
		tableHD = new JTable(modelHD);
		tableHD.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPaneHD = new JScrollPane(tableHD);
		scrollPaneHD.setBounds(10, 10, 1030, 319);
		pn_Top_center.add(scrollPaneHD);
		tableHD.setFont(new Font("Tohoma", Font.PLAIN, 18));

		tableHD.setRowHeight(30);

		DecimalFormat decimalFormat = new DecimalFormat("#,###");

		for (int i = 0; i < dsHoaDonCho.size(); i++) {
			dsHoaDonCho.get(i).setTongTienCanThu(listDanhSachCTHDCho.get(i));;
			if (dsHoaDonCho.get(i).getKhachHang() != null) {
				
				modelHD.addRow(new Object[] {
						dsHoaDonCho.get(i).getKhachHang().getTenKhachHang(),
						dsHoaDonCho.get(i).getKhachHang().getSdt(),
						decimalFormat.format(dsHoaDonCho.get(i).tinhTongTien(
								listDanhSachCTHDCho.get(i))),
						decimalFormat.format(dsHoaDonCho.get(i)
								.getTongTienCanThu()) });
			} else {
				modelHD.addRow(new Object[] {
						"Khách vãng lai",
						"",
						decimalFormat.format(dsHoaDonCho.get(i).tinhTongTien(
								listDanhSachCTHDCho.get(i))),
						decimalFormat.format(dsHoaDonCho.get(i)
								.getTongTienCanThu()) });
			}

		}

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setBounds(1060, 83, 120, 36);
		pn_Top_center.add(btnThanhToan);
		btnThanhToan.setIcon(null);
		btnThanhToan.setBackground(Color.decode(cl_yellow));
		btnThanhToan.setFocusPainted(false);
		btnThanhToan.setBorder(null);
		btnThanhToan.setFont(new Font("Arial", Font.BOLD, 13));

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(1060, 172, 120, 36);
		pn_Top_center.add(btnXoa);
		btnXoa.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoa.setFocusPainted(false);
		btnXoa.setBorder(null);
		btnXoa.setBackground(new Color(255, 0, 0));

		pn_Bot_center = new JPanel();
		pn_Bot_center.setBounds(0, 397, 1200, 500);
		pn_center.add(pn_Bot_center);

		JTableHeader tbheaderHD = tableHD.getTableHeader();
		tbheaderHD.setBackground(Color.decode(cl_green));
		tbheaderHD.setForeground(Color.white);
		tbheaderHD.setFont(new Font("Tohoma", Font.BOLD, 18));

		pn_Top = new JPanel();
		pn_Top.setBounds(0, 0, 1200, 70);
		pn_Top.setBackground(Color.decode(cl_green));
		contentPane.add(pn_Top);
		pn_Top.setLayout(null);

		lblNewLabel = new JLabel("CHỜ THANH TOÁN");
		lblNewLabel.setBounds(485, 20, 230, 30);
		lblNewLabel.setForeground(new Color(252, 190, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		pn_Top.add(lblNewLabel);

		String rowCTHD[] = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá",
				"Tiền khuyến mãi", "Thành tiền" };
		modelCTHD = new DefaultTableModel(rowCTHD, 0);
		pn_Top_center.setLayout(null);
		pn_Bot_center.setLayout(null);
		tableCTHD = new JTable(modelCTHD);
		tableCTHD.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPaneCTHD = new JScrollPane(tableCTHD);
		scrollPaneCTHD.setBounds(10, 10, 1180, 431);
		pn_Bot_center.add(scrollPaneCTHD);
		tableCTHD.setFont(new Font("Tohoma", Font.PLAIN, 18));

		JTableHeader tbheaderCTHD = tableCTHD.getTableHeader();
		tbheaderCTHD.setBackground(Color.decode(cl_green));
		tbheaderCTHD.setForeground(Color.white);
		tbheaderCTHD.setFont(new Font("Tohoma", Font.BOLD, 18));

		tableCTHD.setRowHeight(30);

		tableHD.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				modelCTHD.setRowCount(0);
				if (tableHD.getSelectedRowCount() == 1) {
					int vitri = tableHD.getSelectedRow();
					for (ChiTietHoaDon cthd : listDanhSachCTHDCho.get(vitri)) {
						modelCTHD.addRow(new Object[] {
								cthd.getSanPham().getMaSanPham(),
								cthd.getSanPham().getTenSanPham(),
								cthd.getSoLuong(),
								decimalFormat.format(cthd.getSanPham()
										.getGiaBan()),
								decimalFormat.format(cthd.getKhuyenMai()),
								decimalFormat.format(cthd.getThanhTien()) });
					}
				}
			}
		});
		
		
		TableColumnModel columnModelHD = tableHD.getColumnModel();
		TableColumn ttHD = columnModelHD.getColumn(2);
		TableColumn ttctHD = columnModelHD.getColumn(3);

		DefaultTableCellRenderer rightRendererHD = new DefaultTableCellRenderer();
		rightRendererHD.setHorizontalAlignment(SwingConstants.RIGHT);
		ttHD.setCellRenderer(rightRendererHD);
		ttctHD.setCellRenderer(rightRendererHD);
		
		
		
		TableColumnModel columnModelCTHD = tableCTHD.getColumnModel();
		TableColumn slCTHD = columnModelCTHD.getColumn(2);
		TableColumn giaCTHD = columnModelCTHD.getColumn(3);
		TableColumn kmCTHD = columnModelCTHD.getColumn(4);
		TableColumn ttCTHD = columnModelCTHD.getColumn(5);

		DefaultTableCellRenderer rightRendererCTHD = new DefaultTableCellRenderer();
		rightRendererCTHD.setHorizontalAlignment(SwingConstants.RIGHT);
		slCTHD.setCellRenderer(rightRendererCTHD);
		giaCTHD.setCellRenderer(rightRendererCTHD);
		kmCTHD.setCellRenderer(rightRendererCTHD);
		ttCTHD.setCellRenderer(rightRendererCTHD);

		btnThanhToan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnThanhToanActionPerformed(e);
			}

		});

		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnXoaActionPerformed(e);
			}
		});

		txtTimkiem.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filterData();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filterData();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filterData();
			}
		});

		// txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
		// public void keyReleased(java.awt.event.KeyEvent evt) {
		// txtTimkiemKeyReleased(evt);
		// }
		//
		// private void txtTimkiemKeyReleased(KeyEvent evt) {
		// // TODO Auto-generated method stub
		// String tuKhoa = txtTimkiem.getText().trim();
		//
		// }
		//
		// private void timKiem(String tuKhoa) {
		// int rowCount = modelHD.getRowCount();
		// modelHD.setRowCount(0);
		// for (int i = 0; i < rowCount; i++) {
		// if (rootPaneCheckingEnabled) {
		//
		// }
		// }
		//
		// }
		// });

	}

	private void filterData() {
		String keyword = txtTimkiem.getText();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelHD);
		tableHD.setRowSorter(sorter);
		// Lọc sdt
		RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(
				keyword, 1);
		sorter.setRowFilter(rowFilter);
	}

	private void btnThanhToanActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (tableHD.getSelectedRowCount() == 1) {
			int viTri = tableHD.getSelectedRow();
			if (dsHoaDonCho.get(viTri).getKhachHang() != null) {
				Jpanel_BanHang.txtSDT.setText(dsHoaDonCho.get(viTri)
						.getKhachHang().getSdt());
				Jpanel_BanHang.layThongTin();
			}
			Jpanel_BanHang.dsCTHD = listDanhSachCTHDCho.get(viTri);
			Jpanel_BanHang.btnTaoHDActionPerformed(e);
			Jpanel_BanHang.ganCTHDVaoTable();
			Jpanel_BanHang.setTien();
			dsHoaDonCho.remove(viTri);
			listDanhSachCTHDCho.remove(viTri);
			setVisible(false);

		} else {
			thongbao.thongbao("Chọn dòng cần thanh toán !", "");
		}
	}

	private void btnXoaActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int soRowChon = tableHD.getSelectedRowCount();
		if (soRowChon == 1) {
			int row = tableHD.getSelectedRow();
			int nutbam = JOptionPane.showConfirmDialog(new JFrame(),
					"Bạn chắc chắn xóa ?", "Xóa", JOptionPane.YES_NO_OPTION);
			if (nutbam == JOptionPane.YES_OPTION) {
				modelHD.removeRow(row);
				modelCTHD.setRowCount(0);
				dsHoaDonCho.remove(row);
				listDanhSachCTHDCho.remove(row);
			}
		} else {
			thongbao.thongbao("Chọn 1 dòng để xóa !", "");
		}
	}
}
