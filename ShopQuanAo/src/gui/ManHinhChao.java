package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.DAO_SanPham;
import entity.SanPham;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Dimension;

public class ManHinhChao extends javax.swing.JFrame {

	public ManHinhChao() {
		initComponents();
		Start();
	}

	private void Start() {
		ProgLoading.setStringPainted(true);
		Timer t = new Timer(8, new ActionListener() {
			int index = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				index += 1;
				ProgLoading.setValue(index);
				if (index == 100) {
					((Timer) e.getSource()).stop();
					HienThiDangNhap();
				}
			}

		});
		t.start();
	}

	private void HienThiDangNhap() {
		this.dispose();
		DangNhap frm = new DangNhap();
		frm.setVisible(true);
	}

	private void initComponents() {

		ProgLoading = new javax.swing.JProgressBar();
		pnlTieuDe = new javax.swing.JPanel();
		lblTieuDe = new javax.swing.JLabel();
		lbl_TieuDe0 = new javax.swing.JLabel();
		lblAnhNen = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Chào mừng");
		setBackground(new java.awt.Color(255, 255, 255));
		setMinimumSize(new java.awt.Dimension(1022, 680));
		setUndecorated(true);
		setSize(new Dimension(1022, 672));
		getContentPane().setLayout(null);
		getContentPane().add(ProgLoading);
		ProgLoading.setBounds(0, 650, 1020, 20);

		pnlTieuDe.setBackground(Color.decode("#00c691"));

		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 48)); // NOI18N
		lblTieuDe.setForeground(new java.awt.Color(255, 255, 255));
		lblTieuDe.setText("STREET FASHION SHOP");

		lbl_TieuDe0.setFont(new Font("Tahoma", Font.PLAIN, 25)); // NOI18N
		lbl_TieuDe0.setForeground(new java.awt.Color(255, 255, 255));
		lbl_TieuDe0.setText("Chào mừng đến với");

		javax.swing.GroupLayout pnlTieuDeLayout = new javax.swing.GroupLayout(
				pnlTieuDe);
		pnlTieuDeLayout.setHorizontalGroup(
			pnlTieuDeLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, pnlTieuDeLayout.createSequentialGroup()
					.addGap(142)
					.addComponent(lbl_TieuDe0, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(650, Short.MAX_VALUE))
				.addGroup(pnlTieuDeLayout.createSequentialGroup()
					.addContainerGap(331, Short.MAX_VALUE)
					.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 677, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pnlTieuDeLayout.setVerticalGroup(
			pnlTieuDeLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(pnlTieuDeLayout.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(lbl_TieuDe0, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTieuDe)
					.addGap(32))
		);
		pnlTieuDe.setLayout(pnlTieuDeLayout);

		getContentPane().add(pnlTieuDe);
		pnlTieuDe.setBounds(0, 0, 1020, 160);

		lblAnhNen.setIcon(new javax.swing.ImageIcon("IMG_Hinh//manhinhchao.png")); // NOI18N
		getContentPane().add(lblAnhNen);
		lblAnhNen.setBounds(0, 0, 1020, 650);

		pack();
		setLocationRelativeTo(null);
		xoaKhuyenMaiSP();
		
	}
	
	public void xoaKhuyenMaiSP() {
		
		List<SanPham> dsSPCoKM= DAO_SanPham.getDsSanPham_Querry("select * from SanPham where SanPham.maKhuyenMai is not null");
		
		for (SanPham a : dsSPCoKM) {
			int day= (int) ChronoUnit.DAYS.between( a.getKhuyenMai().getNgayKetThuc(),LocalDate.now());
			if(day>0) {
				a.setKhuyenMai(null);
				DAO_SanPham.capNhatKhuyenMai(a.getMaSanPham().trim());
			}
		}
	}


	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ManHinhChao().setVisible(true);
			}
		});
	}

	private javax.swing.JProgressBar ProgLoading;
	private javax.swing.JLabel lblAnhNen;
	private javax.swing.JLabel lblTieuDe;
	private javax.swing.JLabel lbl_TieuDe0;
	private javax.swing.JPanel pnlTieuDe;

}
