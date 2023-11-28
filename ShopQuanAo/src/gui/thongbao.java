package gui;

import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class thongbao {
	public static void thongbao(String noidung, String tieude) {
		JOptionPane.showMessageDialog(new JOptionPane(), noidung, tieude,
				JOptionPane.INFORMATION_MESSAGE);
	}
}