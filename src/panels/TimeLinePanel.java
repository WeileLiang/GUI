package panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import main.MyFrame;
import views.TimeLineItem;

public class TimeLinePanel extends JPanel {
	private int width = MyFrame.WIDTH * 3 / 4;
	private int height = MyFrame.HEIGHT * 5 / 6;
	private int marginLR = width / 20;
	private int marginTB = height / 20;
	private int itemHeight = height / 12;

	private Font font = new Font("����", Font.PLAIN, 18);

	List<List<int[]>> timeLines = new ArrayList<List<int[]>>();
	TimeLineItem[] items;

	public TimeLinePanel() {
		setSize(width, height);
		setLayout(null);
		setBackground(Color.GRAY);
		// setOpaque(false);

		timeLines.add(Arrays.asList(new int[] { 0, 2 }, new int[] { 6, 24 }));
		timeLines.add(Arrays.asList(new int[] { 0, 12 }, new int[] { 27, 44 }));
		timeLines.add(Arrays.asList(new int[] { 1, 18 }));
		timeLines.add(Arrays.asList(new int[] { 0, 1 }, new int[] { 27, 44 }));
		timeLines.add(Arrays.asList(new int[] { 17, 27 }, new int[] { 31, 46 }));
		timeLines.add(Arrays.asList(new int[] { 0, 4 }));

		initViews();
		measureAndLayout();
	}

	public TimeLinePanel(List<List<int[]>> timeLines) {
		setSize(width, height);
		setLayout(null);
		setBackground(Color.GRAY);
		// setOpaque(false);
		this.timeLines = timeLines;
		initViews();
		measureAndLayout();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		items = new TimeLineItem[timeLines.size()];
		for (int i = 0; i < timeLines.size(); i++)
			items[i] = new TimeLineItem("Machine" + new Random().nextInt(20), timeLines.get(i));

	}

	private void measureAndLayout() {
		int curY = marginTB;
		for (TimeLineItem item : items) {
			item.setBounds(0, curY, item.getWidth(), item.getHeight());
			curY += item.getHeight();
			add(item);
		}
	}

	// @Override
	// protected void paintComponent(Graphics g) {
	// // TODO Auto-generated method stub
	// super.paintComponent(g);
	// Graphics2D g2 = (Graphics2D) g; // g��Graphics����
	// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	// g2.setStroke(new BasicStroke(1));
	// g2.setColor(Color.WHITE);
	//
	// int curY = marginTB;
	//
	//
	// for (int i = 0; i < chips.size(); i++) {
	// g2.setFont(font);
	// FontMetrics fm = g2.getFontMetrics();
	// String text="Machine"+(i+1)+": ";
	// int textWidth = fm.stringWidth(text);
	// int textX = marginLR;
	// int ascent = fm.getAscent();
	// int descent = fm.getDescent();
	// int textY = (itemHeight - (ascent + descent)) / 2 + ascent + curY;
	// //�豸����
	// g2.drawString(text, textX, textY);
	// //������
	// int lineX1=textWidth+marginLR;
	// int lineX2=width-marginLR;
	// int lineY=curY+itemHeight/2;
	// g2.drawLine(lineX1, lineY, lineX2, lineY);
	// //��ͷ
	// int arrowX = width - marginLR - itemHeight / 4;
	// int upArrowY=lineY-itemHeight/8;
	// int downArrowY=lineY+itemHeight/8;
	// g2.drawLine(arrowX, upArrowY, lineX2, lineY);
	// g2.drawLine(arrowX, downArrowY, lineX2, lineY);
	//
	// //ʱ��Ƭ
	// //��������ܳ���
	// int len=lineX2-lineX1;
	// //��������������
	// int max=60;
	// List<int[]> mChips=chips.get(i);
	// for(int[] chip:mChips){
	// int x1=lineX1+len*chip[0]/max;
	// int x2=lineX1+len*chip[1]/max;
	// int y=lineY-itemHeight/4;
	// g2.drawLine(x1, lineY, x1, y);
	// g2.drawLine(x1, y, x2, y);
	// g2.drawLine(x2, y, x2, lineY);
	//
	// //����������
	// String xText1=String.valueOf(chip[0]);
	// String xText2=String.valueOf(chip[1]);
	//
	// g2.setFont(new Font("����",Font.PLAIN,15));
	// FontMetrics fm2 = g2.getFontMetrics();
	// String text1=String.valueOf(chip[0]);
	// String text2=String.valueOf(chip[1]);
	// int textWidth1 = fm2.stringWidth(text1);
	// int textWidth2 = fm2.stringWidth(text2);
	// int mx1 = x1-textWidth1/2;
	// int ascent1 = fm2.getAscent();
	// int descent1 = fm2.getDescent();
	// int my = (itemHeight/4 - (ascent + descent)) / 2 + ascent +lineY;
	//
	// int mx2 = x2-textWidth2/2;
	// g2.drawString(text1, mx1, my);
	// g2.drawString(text2, mx2, my);
	// }
	//
	// curY+=itemHeight;
	// }
	//
	// }
}
