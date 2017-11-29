package panels;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import adapter.NotifyListener;
import constant.Constants;

import views.ItemLabel;

import main.MyFrame;
import utils.AnimationUtil;

public abstract class OperationPanel extends JPanel {
	// 移除自身
	public static final int SIGNAL_REMOVE = 0;

	private int width = MyFrame.WIDTH;
	private int height = MyFrame.HEIGHT;

	// 每行Item的最大数目
	public static int ITEM_COUNT_IN_LINE = 5;

	private List<ItemLabel> labels = new ArrayList<ItemLabel>();

	protected LeftSidePanel leftSidePanel;
	protected GridPanel gridPanel;

	private NotifyListener notifyListener;
	
	protected String tag;
	public OperationPanel(String tag) {
//		leftSidePanel = new LeftSidePanel(tag, Arrays.asList("管理组件", "导入组件"));
//		gridPanel = new GridPanel(
//				Arrays.asList("JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB"));
		
		this.tag=tag;
		initLeftPanel();
		initGridPanel();
		initViews();
		measureAndLayout();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		setSize(width, height);
		setLayout(null);

		setBackground(null);
		setOpaque(false);

	}

	public abstract void initLeftPanel();
	public abstract void initGridPanel();
	
	
	private void measureAndLayout() {
		leftSidePanel.setBounds(-leftSidePanel.getWidth(), 0, leftSidePanel.getWidth(), leftSidePanel.getHeight());
		gridPanel.setBounds(leftSidePanel.getVisibleWidth(), 0, gridPanel.getWidth(), gridPanel.getHeight());

		add(leftSidePanel);
		add(gridPanel);
		
		doReboundSlideInAnim();
		gridPanel.doAlphaInAnim(AnimationUtil.SLIDE_TIME+AnimationUtil.REBOUND_TIME);
	}

	/**
	 * 左侧菜单弹出动画
	 */
	public void doReboundSlideInAnim() {
		AnimationUtil.doSlideAima(leftSidePanel, -leftSidePanel.getWidth(), 0, 0, 0, AnimationUtil.SLIDE_TIME, 0);
		AnimationUtil.doSlideAima(leftSidePanel, 0, -LeftSidePanel.REBOUND_WIDTH, 0, 0, AnimationUtil.REBOUND_TIME,
				AnimationUtil.SLIDE_TIME);

	}

	protected void closeMyself() {
		if (notifyListener != null) {
			notifyListener.notifyParent(SIGNAL_REMOVE);
			leftSidePanel.setBounds(-leftSidePanel.getWidth(), 0, leftSidePanel.getWidth(), leftSidePanel.getHeight());
		}

	}

	public void setNotifyListener(NotifyListener notifyListener) {
		this.notifyListener = notifyListener;
	}
}
