package panels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.MyFrame;

public class MyManagePanel extends JPanel {

	int width = MyFrame.WIDTH / 2;
	int height = 80;

	public MyManagePanel() {
		setBackground(null);
		setOpaque(false);
		setSize(width, height);

		JLabel label = new JLabel("É¾³ý", JLabel.CENTER);
		label.setSize(width/2,height);
		label.setBounds(0,0,label.getWidth(),label.getHeight());
		label.setOpaque(true);
		label.setBackground(Color.RED);
		add(label);
	}
}
