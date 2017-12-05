import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< Updated upstream

import main.MyFrame;
import oracle.jrockit.jfr.JFR;
import panels.CreatePanel;
import panels.ItemInfoPanel;
import views.VerticalLineSeparator;
=======
import javax.swing.JScrollPane;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
		getContentPane().setBackground(Color.GRAY);
		JButton btn = new JButton("123");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JDialog dialog = new JDialog();
				CreatePanel panel = new CreatePanel(
						Arrays.asList("JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB",
								"JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB"));
				dialog.setLayout(null);
				dialog.setSize(new Dimension(panel.getWidth(), panel.getHeight()));
				panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
				dialog.add(panel);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setLocationRelativeTo(null);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}
		});

		btn.setSize(100, 50);
		btn.setBounds(100, 100, btn.getWidth(), btn.getHeight());
		add(btn);
		
		VerticalLineSeparator separator=new VerticalLineSeparator(1, MyFrame.HEIGHT, Color.WHITE);
		separator.setBounds(MyFrame.WIDTH*3/4,0,separator.getWidth(),separator.getHeight());
		System.out.println(MyFrame.HEIGHT+" "+separator.getHeight());
		add(separator);
=======

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);

		panel.setPreferredSize(new Dimension(300, 500));
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 0, 300, 300);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
>>>>>>> Stashed changes
	}

}
