package panels;

import java.awt.Color;

import javax.swing.JPanel;

import constant.Info;
import main.MyFrame;
import views.VerticalLineSeparator;

public class DispatchPanel extends JPanel{

	private int width=MyFrame.WIDTH;
	private int height=MyFrame.HEIGHT;
	
	private LeftSidePanel leftSidePanel;
	private TimePanel timePanel;
	private TimeLinePanel timeLinePanel;
	
	public DispatchPanel(){
		setSize(width,height);
		setLayout(null);
		
		initViews();
		measureAndLayout();
	}
	
	private void initViews(){
		leftSidePanel=new LeftSidePanel("µ÷¶È·ÂÕæ", Info.getJobshopNames(false));
		timeLinePanel=new TimeLinePanel();
		timePanel=new TimePanel();
	}
	
	private void measureAndLayout(){
		leftSidePanel.setBounds(0, 0, leftSidePanel.getWidth(), leftSidePanel.getHeight());
		VerticalLineSeparator separator=new VerticalLineSeparator(3, height, Color.WHITE);
		separator.setBounds(leftSidePanel.getWidth()-separator.getWidth(),0,separator.getWidth(),separator.getHeight());
		
		timePanel.setBounds(leftSidePanel.getWidth(), 0, timePanel.getWidth(), timePanel.getHeight());
		timeLinePanel.setBounds(leftSidePanel.getWidth(), timePanel.getHeight(), timeLinePanel.getWidth(), timeLinePanel.getHeight());
		
		add(separator);
		add(leftSidePanel);
		add(timePanel);
		add(timeLinePanel);
		
	}
}
