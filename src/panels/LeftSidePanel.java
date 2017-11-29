package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import adapter.ClickedListener;
import constant.Constants;
import main.MyFrame;
import panels.ChoicePanel.ItemClickListener;
import views.LineSeparator;

public class LeftSidePanel extends JPanel {

	// 动画回弹的宽度
	public static final int REBOUND_WIDTH = MyFrame.WIDTH / 30;
	private int width = MyFrame.WIDTH / 4 + REBOUND_WIDTH;
	private int height = MyFrame.HEIGHT;
	private int topMargin = width / 13;
	private int marginLRl = REBOUND_WIDTH + width / 10;
	private int marginLRs = REBOUND_WIDTH + width / 12;
	private int labelHeight = width / 8;
	private int tagHeight = (MyFrame.WIDTH / 4) / 3;

	private String tag;
	// 分割线高度
	private int separatorHeight = 1;

	private JLabel returnLabel = new JLabel("<返回");

	private JLabel tagLabel;

	protected List<String> items;
	private List<JLabel> labels = new ArrayList<>();

	private ItemClickListener itemClickListener;

	public LeftSidePanel(String tag, List<String> items) {
		this.tag = tag;
		this.items = items;

		initViews();
		measureAndLayout();
		setListeners();
	}

	private void initViews() {
		returnLabel.setFont(new Font("黑体", Font.PLAIN, 16));
		returnLabel.setForeground(Color.WHITE);

		Font tagFont = new Font("黑体", Font.PLAIN, 33);
		tagLabel = new JLabel(tag);
		tagLabel.setForeground(Color.WHITE);
		tagLabel.setFont(tagFont);

		setLayout(null);
		setSize(width, height);
		setBackground(Color.GRAY);
	}

	private void measureAndLayout() {
		returnLabel.setBounds(marginLRs, topMargin, width / 5, width / 10);

		int curY = topMargin + width / 10;

		tagLabel.setBounds(marginLRs, curY, width, tagHeight);

		add(returnLabel);
		add(tagLabel);
		curY += tagHeight;

		labels = new ArrayList<>();
		Font contentFont = new Font("黑体", Font.PLAIN, 18);
		for (String item : items) {
			JLabel label = new JLabel("        " + item);
			label.setBounds(0, curY, width, labelHeight);
			label.setForeground(Color.WHITE);
			label.setFont(contentFont);
			label.setOpaque(true);
			label.setBackground(null);
			curY += labelHeight;

			LineSeparator line = new LineSeparator(width, separatorHeight, Color.WHITE);
			line.setBounds(0, curY, line.getWidth(), line.getHeight());

			curY += separatorHeight;

			add(label);
			add(line);
			// repaint();
			labels.add(label);
		}
	}

	// 返回实际宽度-回弹宽度，即界面上实际可看到的宽度
	public int getVisibleWidth() {
		return getWidth() - REBOUND_WIDTH;
	}

	private void setListeners() {
		returnLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				returnLabel.setForeground(new Color(Constants.LIGHT_GRAY));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				returnLabel.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				returnLabel.setForeground(Color.WHITE);
				((OperationPanel) getParent()).closeMyself();
			}
		});

		for (int i = 0; i < labels.size(); i++) {
			final JLabel label = labels.get(i);
			final int position = i;
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					clearClickedState();
					label.setBackground(new Color(Constants.LIGHT_GRAY));

					if (itemClickListener != null)
						itemClickListener.onItemClick(position);
				}
			});
		}

	}

	// 把之前点击的Item的背景色恢复
	private void clearClickedState() {
		for (JLabel label : labels)
			label.setBackground(null);
	}

	public void setItemClickListener(ItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

}
