import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.MyFrame;
import oracle.jrockit.jfr.JFR;
import panels.ItemInfoPanel;

public class Test extends JFrame {

	static int i = 0;

	public static void main(String[] args) {
		Test frame = new Test();
		frame.setSize(MyFrame.WIDTH, MyFrame.HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public Test() {
		System.out.println(1);
		setLayout(null);
		getContentPane().setBackground(Color.GRAY);
		JButton btn=new JButton("123");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame dialog=new JFrame();
				ItemInfoPanel panel=new ItemInfoPanel("Pro1", Arrays.asList("JOB0","JOB1","JOB2"));
				dialog.setLayout(null);
				dialog.setSize(new Dimension(panel.getWidth(),panel.getHeight()));
				panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
				dialog.add(panel);
//				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});
		
		btn.setSize(100,50);
		btn.setBounds(100,100,btn.getWidth(),btn.getHeight());
		add(btn);
	}

}