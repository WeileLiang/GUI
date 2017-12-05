import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Test extends JFrame {

	static int i = 0;

	public static void main(String[] args) {
		Test frame = new Test();
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public Test() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);

		panel.setPreferredSize(new Dimension(300, 500));
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 0, 300, 300);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
	}

}
