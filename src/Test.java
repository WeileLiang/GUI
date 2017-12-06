import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import constant.Info;
import main.MyFrame;
import panels.CreatePanel;
import utils.FileUtil;
import views.HintTextField;
import views.VerticalLineSeparator;

public class Test extends JFrame {

	static int i = 0;

	public static void main(String[] args) {
//		Test frame = new Test();
//		frame.setSize(300, 300);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		frame.setVisible(true);
//
//		String fileName=Info.PRODUCT_PATH+File.separator+"pro233.txt";
//		FileUtil.deleteFile(fileName);
		
	}

	public Test() {
		setLayout(null);

		getContentPane().setBackground(Color.GRAY);
		
		HintTextField field=new HintTextField();
		field.setSize(100,50);
		field.setBounds(10, 10, field.getWidth(), field.getHeight());
		field.setTips("123");
		add(field);
			addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				field.setFocusable(false);
			}
		});
	}

}
