import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.MyFrame;
import views.TimeLineItem;
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
		TimeLineItem item = new TimeLineItem("Machine1", Arrays.asList(new int[] { 0, 2 }, new int[] { 6, 24 }));
		item.setBounds(100, 100, item.getWidth(), item.getHeight());
		add(item);
	}

}
