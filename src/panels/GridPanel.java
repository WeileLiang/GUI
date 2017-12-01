package panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position;

import adapter.ItemClickListener;
import main.MyFrame;
import utils.AnimationUtil;
import views.ItemLabel;
import views.TransparentLabel;

public class GridPanel extends JPanel {

	public static final int DURATION = 500;
	public static final int PERIOD = 5;
	private int curTime = 0;

	public static final int ITEM_COUNT_IN_LINE = 5;

	private int width = MyFrame.WIDTH * 3 / 4;
	private int height = MyFrame.HEIGHT;

	private int marginLR = width / 20;
	private int marginTB = height / 10;
	private int gapHor = width / 16;
	private int gapVer = height / 25;

	protected List<String> datas;
	protected List<ItemLabel> labels;

	private List<Boolean> chosenStates;

	// 是否处于管理状态，若是，则item的点击效果是选中，否则点击效果是缩放
	protected boolean inManageState = false;

	protected ItemClickListener gridItemClickListener;
	private ItemClickListener manageItemClickListener;
	private ManagePanel managePanel;

	public GridPanel(List<String> datas) {
		this.datas = datas;

		initViews();
		measureAndLayout();
		setListeners();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(width, height);
		setBackground(null);
		setOpaque(false);
	}

	private void measureAndLayout() {
		labels = new ArrayList<ItemLabel>();
		chosenStates = new ArrayList<>();
		int curX = marginLR, curY = marginTB;
		for (int i = 0; i < datas.size(); i++) {
			ItemLabel label = new ItemLabel(datas.get(i), .0f);

			label.setBounds(curX, curY, label.getWidth(), label.getHeight());
			labels.add(label);
			chosenStates.add(false);
			add(label);

			if (i % ITEM_COUNT_IN_LINE == ITEM_COUNT_IN_LINE - 1) {
				curX = marginLR;
				curY += label.getHeight() + gapVer;
			} else
				curX += label.getWidth() + gapHor;

		}
	}

	private void setListeners() {
		for (int i = 0; i < labels.size(); i++) {
			final int Position = i;
			labels.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					if (gridItemClickListener != null)
						gridItemClickListener.onItemClick(Position);

				}
			});
		}
	}

	public void showManagePanel() {
		if (managePanel == null) {
			managePanel = new ManagePanel();
			managePanel.setBounds(marginLR, -managePanel.getHeight(), managePanel.getWidth(), managePanel.getHeight());
			add(managePanel);
			AnimationUtil.doSlideAima(managePanel, managePanel.getX(), managePanel.getX(), -managePanel.getHeight(),
					(marginTB - managePanel.getHeight()) / 2, 500, 0);
			
		} else {
			add(managePanel);
		}
	}

	public void hideManagePanel() {
		remove(managePanel);
	}

	public TransparentLabel getKthItem(int position) {
		return labels.get(position).getInnerLabel();
	}

	/**
	 * 执行Item的淡入动画
	 */
	public void doAlphaInAnim(int delayTime) {
		curTime = 0;

		float initAlpha = .0f;
		float targetAlpha = 1.f;

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				float curAlpah = (float) (initAlpha + (1 - Math.cos(Math.PI / 2 * curTime / DURATION)) * targetAlpha);
				setAlpha(curAlpah);

				curTime += PERIOD;
				if (curTime > DURATION)
					timer.cancel();
			}
		}, delayTime, PERIOD);
	}

	public void setAlpha(float alpha) {
		for (ItemLabel label : labels)
			label.setAlpha(alpha);

		repaint();
	}

	/**
	 * 清楚搜索Item的选中状态
	 */
	public void clearChosenStates() {
		for (int i = 0; i < chosenStates.size(); i++) {
			if (chosenStates.get(i)) {
				chosenStates.set(i, false);
				labels.get(i).setChosenBorder(false);
			}
		}
	}

	public void setChosenState(int position) {
		boolean state = chosenStates.get(position);
		chosenStates.set(position, !state);
		labels.get(position).setChosenBorder(!state);
	}

	public void setGridItemClickListener(ItemClickListener itemClickListener) {
		this.gridItemClickListener = itemClickListener;
	}

	public void setManageItemClickListener(ItemClickListener manageItemClickListener) {
		this.manageItemClickListener = manageItemClickListener;
	}
}
