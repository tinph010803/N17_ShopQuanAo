package gui;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Rectangle;

public class Jpanel_TroGiup extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Jpanel_TroGiup() {
		setBounds(new Rectangle(0, 0, 1422, 913));
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1422, 913);
		add(panel);
		panel.setLayout(null);
		setBackground(Color.yellow);

	}

}
