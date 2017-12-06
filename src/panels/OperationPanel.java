package panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import adapter.NotifyListener;
import main.MyFrame;
import utils.AnimationUtil;
import views.ItemLabel;

public abstract class OperationPanel extends JPanel {
	// �Ƴ�����
	public static final int SIGNAL_REMOVE = 0;

	private int width = MyFrame.WIDTH;
	private int height = MyFrame.HEIGHT;

	// ÿ��Item�������Ŀ
	public static int ITEM_COUNT_IN_LINE = 5;

	private List<ItemLabel> labels = new ArrayList<ItemLabel>();

	// ���ڹ���״̬��Item�����ѡ��Ч��������������Ч��
	protected boolean inManageState = false;
	protected String firstLabeltext;

	protected LeftSidePanel leftSidePanel;
	protected GridPanel gridPanel;
	
	private NotifyListener notifyListener;

	protected String tag;

	public OperationPanel(String tag) {

		this.tag = tag;
		initLeftPanel();
		initGridPanel();
		initViews();
		measureAndLayout();
		setListeners();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		setSize(width, height);
		setLayout(null);

		setBackground(null);
		setOpaque(false);

	}

	private void measureAndLayout() {
		leftSidePanel.setBounds(-leftSidePanel.getWidth(), 0, leftSidePanel.getWidth(), leftSidePanel.getHeight());
		gridPanel.setBounds(leftSidePanel.getVisibleWidth(), 0, gridPanel.getWidth(), gridPanel.getHeight());

		add(leftSidePanel);
		add(gridPanel);

		doReboundSlideInAnim();
		doAlpahInAima();
	}

	public abstract void initLeftPanel();

	public abstract void initGridPanel();

	public abstract void setListeners();

	/**
	 * ���˵���������
	 */
	public void doReboundSlideInAnim() {
		AnimationUtil.doSlideAima(leftSidePanel, -leftSidePanel.getWidth(), 0, 0, 0, AnimationUtil.SLIDE_TIME, 0);
		AnimationUtil.doSlideAima(leftSidePanel, 0, -LeftSidePanel.REBOUND_WIDTH, 0, 0, AnimationUtil.REBOUND_TIME,
				AnimationUtil.SLIDE_TIME);
	}

	/**
	 * �Ҳ�GridPanel�ĵ��붯��
	 */
	public void doAlpahInAima() {
		gridPanel.doAlphaInAnim(AnimationUtil.SLIDE_TIME+AnimationUtil.REBOUND_TIME);
	}

	protected void closeMyself() {
		if (notifyListener != null) {
			notifyListener.notifyParent(SIGNAL_REMOVE);
			leftSidePanel.setBounds(-leftSidePanel.getWidth(), 0, leftSidePanel.getWidth(), leftSidePanel.getHeight());
			gridPanel.setAlpha(.0f);
			gridPanel.clearChosenStates();
		}

	}

	public void setNotifyListener(NotifyListener notifyListener) {
		this.notifyListener = notifyListener;
	}
}
