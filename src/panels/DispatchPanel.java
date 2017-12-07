package panels;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import adapter.NotifyListener;
import constant.Info;
import main.MyFrame;
import utils.AnimationUtil;
import views.TransparentPanel;
import views.VerticalLineSeparator;

public class DispatchPanel extends JPanel {

	private int width = MyFrame.WIDTH;
	private int height = MyFrame.HEIGHT;

	private LeftSidePanel leftSidePanel;
	private TimePanel timePanel;
	private TimeLinePanel timeLinePanel;

	private VerticalLineSeparator separator;

	private TransparentPanel alphaPanel;

	private NotifyListener notifyListener;
	
	public DispatchPanel() {
		setSize(width, height);
		setLayout(null);
		setBackground(Color.GRAY);
		initViews();
		measureAndLayout();
	}

	private void initViews() {
		leftSidePanel = new LeftSidePanel("调度仿真", Info.getJobshopNames(false));
		timeLinePanel = new TimeLinePanel();
		timePanel = new TimePanel();
		separator = new VerticalLineSeparator(6, height, Color.WHITE);

		alphaPanel = new TransparentPanel(1.f);
		alphaPanel.setSize(MyFrame.WIDTH * 3 / 4, MyFrame.HEIGHT);
		alphaPanel.setBackground(Color.GRAY);
	}

	private void measureAndLayout() {
		leftSidePanel.setBounds(-LeftSidePanel.REBOUND_WIDTH, 0, leftSidePanel.getWidth(), leftSidePanel.getHeight());
		separator.setBounds(leftSidePanel.getVisibleWidth(), 0, separator.getWidth(), separator.getHeight());

		timePanel.setBounds(separator.getWidth() + leftSidePanel.getVisibleWidth(), 0, timePanel.getWidth(),
				timePanel.getHeight());
		timeLinePanel.setBounds(separator.getWidth() + leftSidePanel.getVisibleWidth(), timePanel.getHeight(),
				timeLinePanel.getWidth(), timeLinePanel.getHeight());

		alphaPanel.setBounds(leftSidePanel.getVisibleWidth(), 0, alphaPanel.getWidth(), alphaPanel.getHeight());
		add(leftSidePanel);
		add(alphaPanel);
		add(separator);
		add(timePanel);
		add(timeLinePanel);

		doReboundSlideInAnim();
		doAlphaOutAnim();
	}

	private int curTime = 0;

	public void doReboundSlideInAnim() {
		AnimationUtil.doSlideAima(leftSidePanel, -leftSidePanel.getWidth(), 0, 0, 0, AnimationUtil.SLIDE_TIME, 0);
		AnimationUtil.doSlideAima(leftSidePanel, 0, -LeftSidePanel.REBOUND_WIDTH, 0, 0, AnimationUtil.REBOUND_TIME,
				AnimationUtil.SLIDE_TIME);
	}
	
	public void doAlphaOutAnim() {
		doRightSideAlphaAnima(1.f, .0f, 650, AnimationUtil.SLIDE_TIME+AnimationUtil.REBOUND_TIME);
	}
	
	private void doRightSideAlphaAnima(Float initAlpha, float targetAlpha, int duration, int delay) {
		curTime = 0;
		float distance = targetAlpha - initAlpha;
		int period = 5;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				float curAlpha = (float) (initAlpha + (1 - Math.cos(Math.PI / 2 * curTime / duration)) * distance);
				alphaPanel.setAlpha(curAlpha);

				curTime += period;
				if (curTime > duration) {
					alphaPanel.setAlpha(targetAlpha);
					timer.cancel();
				}
				
				repaint();
			}
		}, delay, period);
	}
	
	public void closeMyself() {
		if(notifyListener!=null) {
			notifyListener.notifyParent(0);
			reset();
		}
	}
	
	/**
	 * 左边菜单移除，以及透明面板置为不透明
	 */
	public void reset() {
		leftSidePanel.setBounds(-LeftSidePanel.REBOUND_WIDTH, 0, leftSidePanel.getWidth(), leftSidePanel.getHeight());
		alphaPanel.setAlpha(1.f);
		repaint();
	}
	
	
	public void setNotifyListener(NotifyListener notifyListener) {
		this.notifyListener = notifyListener;
	}
}
