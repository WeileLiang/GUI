import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.MyFrame;
import views.TransparentPanel;

public class Test extends JFrame {

	static int i = 0;

	public static void main(String[] args) {
		Test frame = new Test();
		frame.setSize(MyFrame.WIDTH, MyFrame.HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	float alpha = 1.f;

	public Test() {
		setLayout(null);

		JLayeredPane layeredPane = getLayeredPane();
		TransparentPanel panel = new TransparentPanel(1.0f);
		panel.setBackground(Color.GRAY);

		panel.setBounds(0, 0, MyFrame.WIDTH, MyFrame.HEIGHT);

		JLabel label = new JLabel("AAA");
		label.setFont(new Font("ºÚÌå", Font.BOLD, 33));
		label.setForeground(Color.WHITE);
		label.setBounds(200, 200, 100, 50);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				System.out.println(333);
			}
		});

		layeredPane.add(label, 1);
//		layeredPane.add(panel, 0);
//		Timer timer = new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("cur: "+alpha);
//				panel.setAlpha(alpha);
//				alpha -= .1f;
//				if (alpha < .0f) {
//					panel.setAlpha(.0f);
//					timer.cancel();
//					System.out.println(123);
//				}
//			}
//		}, 100, 20);
	}

}
