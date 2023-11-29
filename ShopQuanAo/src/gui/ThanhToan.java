package gui;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;

public class ThanhToan extends JFrame {

	private JPanel pnlThanhToan;
	private final JPanel pnlChinh = new JPanel();
	private final JLabel lblTieuDe = new JLabel("THANH TOÁN HÓA ĐƠN");
	private JTextField txtTienKhachDua;
	private JLabel lblNgayLapHD;
	private JLabel lblNgayLap;
	private JPanel pnlCenter;
	private JLabel lblMaHD;
	private JLabel lblMaKH;
	private JLabel lblTenKH;
	private JLabel lblTongThanhToan;
	private JLabel lblTienKhachDua;
	private JLabel lblTienTraKhach;
	private JLabel lblMa;
	private JLabel lblTen;
	private JLabel lblThanhToan;
	private JLabel lblTienTra;
	private JButton btnThanhToan;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// ThanhToan frame = new ThanhToan(new HoaDon(),new KhachHang(),new
	// ArrayList<ChiTietHoaDon>());
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	private HoaDon hoaDon;
	private List<ChiTietHoaDon> dsChiTietHoaDon;

	public ThanhToan(HoaDon hd, List<ChiTietHoaDon> dsCTHD) {
		hoaDon = hd;
		dsChiTietHoaDon = dsCTHD;
		setBounds(100, 100, 634, 465);
		setLocationRelativeTo(null);
		setResizable(false);
		pnlThanhToan = new JPanel();
		pnlThanhToan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlThanhToan);
		pnlThanhToan.setLayout(null);
		pnlChinh.setBounds(0, 0, 628, 429);
		pnlThanhToan.add(pnlChinh);
		pnlChinh.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(93, 244, 144));
		pnlHeader.setBounds(0, 0, 628, 90);
		pnlChinh.add(pnlHeader);
		pnlHeader.setLayout(null);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTieuDe.setBounds(165, 5, 310, 30);

		pnlHeader.add(lblTieuDe);

		lblNgayLapHD = new JLabel("Ngày lập HĐ:");
		lblNgayLapHD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNgayLapHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgayLapHD.setForeground(Color.WHITE);
		lblNgayLapHD.setBounds(368, 51, 107, 20);
		pnlHeader.add(lblNgayLapHD);
		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		lblNgayLap = new JLabel(d.format(hd.getNgay()));
		lblNgayLap.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgayLap.setForeground(Color.WHITE);
		lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNgayLap.setBounds(490, 52, 107, 20);
		pnlHeader.add(lblNgayLap);

		pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(178, 239, 199));
		pnlCenter.setBounds(0, 84, 628, 347);
		pnlChinh.add(pnlCenter);
		pnlCenter.setLayout(null);

		lblMaHD = new JLabel(hd.getMaHoaDon());
		lblMaHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMaHD.setBounds(210, 15, 225, 25);
		pnlCenter.add(lblMaHD);

		lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKH.setBounds(70, 55, 160, 25);
		pnlCenter.add(lblMaKH);

		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenKH.setBounds(70, 95, 160, 25);
		pnlCenter.add(lblTenKH);

		lblTongThanhToan = new JLabel("Tổng thanh toán:");
		lblTongThanhToan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongThanhToan.setBounds(70, 135, 160, 25);
		pnlCenter.add(lblTongThanhToan);

		lblTienKhachDua = new JLabel("Tiền khách đưa:");
		lblTienKhachDua.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTienKhachDua.setBounds(70, 175, 160, 25);
		pnlCenter.add(lblTienKhachDua);

		lblTienTraKhach = new JLabel("Tiền trả khách:");
		lblTienTraKhach.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTienTraKhach.setBounds(70, 215, 160, 25);
		pnlCenter.add(lblTienTraKhach);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTienKhachDua.setBounds(260, 175, 300, 25);
		pnlCenter.add(txtTienKhachDua);
		txtTienKhachDua.setColumns(10);

		AbstractDocument document = (AbstractDocument) txtTienKhachDua
				.getDocument();
		document.setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int length,
					String text, AttributeSet attrs)
					throws BadLocationException {
				if (isNumeric(text)) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});

		lblMa = new JLabel();
		lblMa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMa.setBounds(260, 56, 300, 25);
		pnlCenter.add(lblMa);

		lblTen = new JLabel();
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblTen.setBounds(260, 98, 300, 25);
		pnlCenter.add(lblTen);

		if (hd.getKhachHang() != null) {
			lblMa.setText(hd.getKhachHang().getMaKhachHang());
			lblTen.setText(hd.getKhachHang().getTenKhachHang());
		} else {
			lblMa.setText("Khách vãng lai");
			lblTen.setText("");
		}

		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		lblThanhToan = new JLabel(decimalFormat.format(hd.getTongTienCanThu()));
		lblThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhToan.setBounds(260, 138, 300, 25);
		pnlCenter.add(lblThanhToan);

		lblTienTra = new JLabel();
		lblTienTra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTienTra.setHorizontalAlignment(SwingConstants.CENTER);
		lblTienTra.setBounds(260, 218, 300, 25);
		pnlCenter.add(lblTienTra);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBackground(Color.CYAN);
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThanhToan.setBounds(345, 270, 125, 30);
		pnlCenter.add(btnThanhToan);
		btnThanhToan.setFocusPainted(false);
		btnThanhToan.setEnabled(false);
		// txtTienKhachDua.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// hd.setTienKhachDua(Double.parseDouble(txtTienKhachDua.getText()));
		// lblTienTra.setText(decimalFormat.format(hd.tinhTienThua(dsCTHD)));
		// }
		//
		// });

		txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if (txtTienKhachDua.getText().equals("")) {
					hd.setTienKhachDua(0.0);
					lblTienTra.setText(decimalFormat.format(hd
							.tinhTienThua(dsCTHD)));
					btnThanhToan.setEnabled(false);
				} else {
					hd.setTienKhachDua(Double.parseDouble(txtTienKhachDua
							.getText()));
					lblTienTra.setText(decimalFormat.format(hd
							.tinhTienThua(dsCTHD)));
					if (hd.tinhTienThua(dsCTHD) >= 0) {
						btnThanhToan.setEnabled(true);

					} else {
						btnThanhToan.setEnabled(false);

					}
				}
			}
		});

		btnThanhToan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dao.DAO_HoaDon.them(hd);
				// xuatHDFilePDF();
				
				if (hd.getKhachHang() != null) {
					hd.getKhachHang()
							.capNhatSoTienDaMua(hd.getTongTienCanThu());
					dao.DAO_KhachHang.capNhatSTDM(hd.getKhachHang());
				}
				for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
					dao.DAO_ChiTietHoaDon.them(chiTietHoaDon);
					dao.DAO_SanPham.capNhatSoLuong(chiTietHoaDon.getSanPham(),
							chiTietHoaDon.getSoLuong());
				}
				xuatHoaDon();
				dsCTHD.clear();
				setVisible(false);
			}

		});
	}

	private static boolean isNumeric(String text) {
		return text.matches("^[0-9]*$");
	}

	public void xuatHoaDon() {
		try {
			DecimalFormat decimalFormat = new DecimalFormat("#,###");
			Map<String, Object> map = new Hashtable();
			JasperReport report = JasperCompileManager
					.compileReport("src/gui/rp_XuatHoaDon.jrxml");

			map.put("MaHD", hoaDon.getMaHoaDon());
			if (hoaDon.getKhachHang()!=null) {
				map.put("MaKH", hoaDon.getKhachHang().getMaKhachHang());
			}else{
				map.put("MaKH", "Khách vãng lai");
			}
			
			map.put("NgayBan", getCurrentTimestamp());
			map.put("TenNV", hoaDon.getNhanVien().getTenNhanVien());
			map.put("TongThanhTien", hoaDon.tinhTongTien(dsChiTietHoaDon));
			map.put("TienKM", hoaDon.tinhTongTienKM(dsChiTietHoaDon));
			map.put("TienThue", hoaDon.tinhTongTienThue(dsChiTietHoaDon));
			map.put("TienKMBac", hoaDon.tinhTongKhuyeMaiTheoBac(dsChiTietHoaDon));
			map.put("TienCanThu", hoaDon.getTongTienCanThu());
			map.put("TienKhachDua", hoaDon.getTienKhachDua());
			map.put("TienThua", hoaDon.tinhTienThua(dsChiTietHoaDon));

			
			
			
			JasperPrint p = JasperFillManager.fillReport(report,map,connectDB.ConnectionManager.conn);
			JasperViewer.viewReport(p, false);
			JasperExportManager.exportReportToPdfFile(p, "test.pdf");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// private void xuatPDF(){
	// // String pdfFilePath = "DSHoaDon//"
	//
	//
	// }
	//
	// private void taoPDF(String filePath, ){
	// Document
	// }

	private String getCurrentTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(new Date());
	}

	// private void xuatHDFilePDF(){
	// String maHD = hoaDon.getMaHoaDon();
	// String pdfFilePath = "DSHoaDon//" + maHD + ".pdf";
	//
	// try {
	// createInvoicePDF(pdfFilePath);
	// System.out.println("PDF created successfully.");
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// private void createInvoicePDF(String filePath) throws Exception {
	// Document document = new Document();
	// PdfWriter.getInstance(document, new FileOutputStream(filePath));
	// document.open();
	//
	// // Add content to the PDF
	// addInvoiceContent(document);
	//
	// document.close();
	// }

	// private void addInvoiceContent(Document document) throws SQLException,
	// DocumentException, IOException {
	// String fontPath = "Font//Font_Freedom.ttf";
	// BaseFont freedom = BaseFont.createFont(fontPath,
	// BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	//
	// BaseFont unicode = BaseFont.createFont("Font//unicode.ttf",
	// BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	//
	// com.itextpdf.text.Font font18 = new com.itextpdf.text.Font(unicode, 18);
	// com.itextpdf.text.Font font14 = new com.itextpdf.text.Font(unicode, 14);
	// com.itextpdf.text.Font font10 = new com.itextpdf.text.Font(unicode, 10);
	// com.itextpdf.text.Font font = new com.itextpdf.text.Font(freedom, 28);
	//
	// Paragraph tenShop = new Paragraph("STREET FASHION SHOP", font);
	// tenShop.setAlignment(Element.ALIGN_CENTER);
	// tenShop.setSpacingAfter(30f);
	// document.add(tenShop);
	//
	// Paragraph hdbh = new Paragraph("HÓA ĐƠN BÁN HÀNG", font18);
	// hdbh.setAlignment(Element.ALIGN_CENTER);
	// hdbh.setSpacingAfter(15f);
	// document.add(hdbh);
	//
	// Paragraph para = new Paragraph();
	// para.add(new Chunk("Mã HĐ: " + hoaDon.getMaHoaDon(),font14));
	// para.add(new Chunk("                              "));
	// if (hoaDon.getKhachHang()!=null) {
	// para.add(new Chunk("Mã KH: " +
	// hoaDon.getKhachHang().getMaKhachHang(),font14));
	// }else{
	// para.add(new Chunk("Mã KH: Khách vãng lai",font14));
	// }
	// para.add(Chunk.NEWLINE);
	// String tg = getCurrentTimestamp();
	// para.add(new Chunk("Ngày bán: " + tg,font14));
	// para.add(new Chunk("                     "));
	// para.add(new Chunk("Tên NV: " +
	// hoaDon.getNhanVien().getTenNhanVien(),font14));
	//
	// para.setSpacingAfter(20f);
	// para.setIndentationLeft(65f);
	//
	// document.add(para);
	//
	// addInvoiceDetailsToTable(document);
	//
	// DecimalFormat decimalFormat = new DecimalFormat("#,###");
	// PdfPTable bangTien = new PdfPTable(2);
	// bangTien.setWidthPercentage(100);
	// bangTien.getDefaultCell().setBorder(0);
	//
	// addCell_NoBorder(bangTien, "Tổng thành tiền:", font14,
	// Element.ALIGN_LEFT);
	// addCell_NoBorder(bangTien,
	// decimalFormat.format(hoaDon.tinhTongTien(dsChiTietHoaDon)), font14,
	// Element.ALIGN_RIGHT);
	//
	// addCell_NoBorder(bangTien, "Tiền khuyến mãi:", font14,
	// Element.ALIGN_LEFT);
	// addCell_NoBorder(bangTien,
	// decimalFormat.format(hoaDon.tinhTongTienKM(dsChiTietHoaDon)), font14,
	// Element.ALIGN_RIGHT);
	//
	// addCell_NoBorder(bangTien, "Tổng tiền thuế:", font14,
	// Element.ALIGN_LEFT);
	// addCell_NoBorder(bangTien,
	// decimalFormat.format(hoaDon.tinhTongTienThue(dsChiTietHoaDon)), font14,
	// Element.ALIGN_RIGHT);
	//
	// addCell_NoBorder(bangTien, "Tiền khuyến mãi theo bậc:", font14,
	// Element.ALIGN_LEFT);
	// addCell_NoBorder(bangTien,
	// decimalFormat.format(hoaDon.tinhTongKhuyeMaiTheoBac(dsChiTietHoaDon)),
	// font14, Element.ALIGN_RIGHT);
	//
	// addCell_NoBorder(bangTien, "Tổng tiền cần thu:", font14,
	// Element.ALIGN_LEFT);
	// addCell_NoBorder(bangTien,
	// decimalFormat.format(hoaDon.getTongTienCanThu()), font14,
	// Element.ALIGN_RIGHT);
	//
	// addCell_NoBorder(bangTien, "Tiền khách đưa:", font14,
	// Element.ALIGN_LEFT);
	// addCell_NoBorder(bangTien,
	// decimalFormat.format(hoaDon.getTienKhachDua()), font14,
	// Element.ALIGN_RIGHT);
	//
	// addCell_NoBorder(bangTien, "Tổng tiền trả lại:", font14,
	// Element.ALIGN_LEFT);
	// addCell_NoBorder(bangTien,
	// decimalFormat.format(hoaDon.tinhTienThua(dsChiTietHoaDon)), font14,
	// Element.ALIGN_RIGHT);
	// bangTien.setSpacingAfter(15f);
	// document.add(bangTien);
	//
	// Paragraph xhd = new Paragraph("Chỉ xuất hóa đơn trong ngày", font10);
	// xhd.setAlignment(Element.ALIGN_CENTER);
	// document.add(xhd);
	//
	// Paragraph xhdTA = new
	// Paragraph("Tax Invoice will be issued within same day", font10);
	// xhdTA.setAlignment(Element.ALIGN_CENTER);
	// xhdTA.setSpacingAfter(10f);
	// document.add(xhdTA);
	//
	// Paragraph camon = new Paragraph("CẢM ƠN QUÝ KHÁCH VÀ HẸN GẶP LẠI",
	// font14);
	// camon.setAlignment(Element.ALIGN_CENTER);
	// document.add(camon);
	//
	// Paragraph hotline = new Paragraph("Hotline: 0366459144 - Tòng", font10);
	// hotline.setAlignment(Element.ALIGN_CENTER);
	// document.add(hotline);
	//
	// }
	//
	// private void addInvoiceDetailsToTable(Document document)
	// throws SQLException, DocumentException, IOException {
	// PdfPTable table = new PdfPTable(5);
	// table.setWidthPercentage(100);
	// table.setSpacingAfter(15f);
	// addTableHeader(table);
	// addInvoiceDetailsRows(table);
	//
	// document.add(table);
	// }
	//
	//
	// private void addTableHeader(PdfPTable table) throws DocumentException,
	// IOException {
	// BaseFont unicode = BaseFont.createFont("Font//unicode.ttf",
	// BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	//
	// com.itextpdf.text.Font font13 = new com.itextpdf.text.Font(unicode, 13);
	//
	// addCell(table, "Tên sản phẩm", font13,Element.ALIGN_CENTER);
	// addCell(table, "Đơn giá", font13,Element.ALIGN_CENTER);
	// addCell(table, "Số lượng", font13,Element.ALIGN_CENTER);
	// addCell(table, "Tiền khuyến mãi", font13,Element.ALIGN_CENTER);
	// addCell(table, "Tổng tiền", font13,Element.ALIGN_CENTER);
	// }
	//
	// private static void addCell(PdfPTable table, String text,
	// com.itextpdf.text.Font font13,int alignment) {
	// PdfPCell cell = new PdfPCell(new Phrase(text, font13));
	// cell.setHorizontalAlignment(alignment);
	// table.addCell(cell);
	// }
	//
	// private static void addCell_NoBorder(PdfPTable table, String text,
	// com.itextpdf.text.Font font13,int alignment) {
	// PdfPCell cell = new PdfPCell(new Phrase(text, font13));
	// cell.setHorizontalAlignment(alignment);
	// cell.setBorder(0);
	// table.addCell(cell);
	// }
	//
	// private void addInvoiceDetailsRows(PdfPTable table) throws SQLException,
	// DocumentException, IOException {
	// DecimalFormat decimalFormat = new DecimalFormat("#,###");
	// BaseFont unicode = BaseFont.createFont("Font//unicode.ttf",
	// BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	//
	// com.itextpdf.text.Font font12 = new com.itextpdf.text.Font(unicode, 12);
	//
	//
	//
	// for (ChiTietHoaDon cthd : dsChiTietHoaDon) {
	// addCell(table, cthd.getSanPham().getTenSanPham(),
	// font12,Element.ALIGN_LEFT);
	// addCell(table, decimalFormat.format(cthd.getSanPham().getGiaBan()),
	// font12,Element.ALIGN_RIGHT);
	// addCell(table, decimalFormat.format(cthd.getSoLuong()),
	// font12,Element.ALIGN_RIGHT);
	// addCell(table, decimalFormat.format(cthd.tinhTienKhuyenMai()),
	// font12,Element.ALIGN_RIGHT);
	// addCell(table, decimalFormat.format(cthd.tinhThanhTien()),
	// font12,Element.ALIGN_RIGHT);
	// }
	//
	// }

}
