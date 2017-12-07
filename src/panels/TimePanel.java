package panels;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.MyFrame;

public class TimePanel extends JPanel{
	private int width=MyFrame.WIDTH*3/4;
	private int height=MyFrame.HEIGHT/5;
	private int marginLR=width/20;
	private int marginTB=height/4;
	
	private JLabel totalTimeLabel=new JLabel("������ʱ��: 317s");
	private JLabel restTimeLabel=new JLabel("ʣ��ʱ��: 303s");
	
	List<JLabel> labels=Arrays.asList(totalTimeLabel,restTimeLabel);
	
	public TimePanel(){
		setSize(width,height);
		setLayout(null);
		setBackground(Color.GRAY);
//		setOpaque(false);
		
		initViews();
	}
	
	private void initViews(){
		Font font1=new Font("����",Font.PLAIN,28);
		Font font2=new Font("����",Font.PLAIN,25);
		
		for(JLabel label:labels){
			label.setForeground(Color.WHITE);
			label.setOpaque(false);
		}
		
		totalTimeLabel.setBounds(marginLR,marginTB,width,height/2);
		totalTimeLabel.setFont(font1);
		restTimeLabel.setBounds(marginLR,marginTB+height/2,width,height/5);
		restTimeLabel.setFont(font2);
		
		add(totalTimeLabel);
		add(restTimeLabel);
	}
	
}
