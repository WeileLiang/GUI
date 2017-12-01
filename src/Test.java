import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;import com.sun.accessibility.internal.resources.accessibility_zh_CN;

import constant.Constants;
import main.MyFrame;
import panels.CraftOperationPanel;
import utils.AnimationUtil;
import utils.FileUtil;
import views.ItemLabel;

public class Test extends JFrame {

	static int i = 0;

	public static void main(String[] args) {
//		Test frame = new Test();
//		frame.setSize(MyFrame.WIDTH, MyFrame.HEIGHT);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		frame.setVisible(true);
		
	}

	public Test() {
		setLayout(null);
		getContentPane().setBackground(Color.lightGray);
		CraftOperationPanel panel=new CraftOperationPanel("123");
		
		panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		add(panel);
	}

}
