package panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import main.MyFrame;
import panels.ChoicePanel.ItemClickListener;
import sun.nio.cs.ext.MacHebrew;
import utils.AnimationUtil;
import views.ItemLabel;

public class GridPanel extends JPanel {

	public static final int DURATION = 350;
	public static final int PERIOD = 5;
	private int curTime = 0;

	public static final int ITEM_COUNT_IN_LINE = 5;

	private int width = MyFrame.WIDTH * 3 / 4;
	private int height = MyFrame.HEIGHT;

	private int marginLR = width / 20;
	private int marginTB = height / 12;
	private int gapHor = width / 18;
	private int gapVer = height / 20;

	protected List<String> datas;
	protected List<ItemLabel> labels;

	// �Ƿ��ڹ���״̬�����ǣ���item�ĵ��Ч����ѡ�У�������Ч��������
	protected boolean inManageState = false;

	protected ItemClickListener itemClickListener;

	public GridPanel(List<String> datas) {
		this.datas = datas;

		initViews();
		measureAndLayout();
		// setListeners();
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

		int curX = marginLR, curY = marginTB;
		for (int i = 0; i < datas.size(); i++) {
			ItemLabel label = new ItemLabel(datas.get(i), .0f);

			label.setBounds(curX, curY, label.getWidth(), label.getHeight());
			labels.add(label);
			add(label);

			if (i % ITEM_COUNT_IN_LINE == ITEM_COUNT_IN_LINE - 1) {
				curX = marginLR;
				curY += label.getHeight() + gapVer;
			} else
				curX += label.getWidth() + gapHor;

		}
	}

	private void setListeners() {
		for (ItemLabel label : labels) {
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);

					if (inManageState) {

					} else {
						AnimationUtil.doShrinkAnima(label.getInnerLabel());
					}
				}
			});
		}
	}

	/**
	 * ִ��Item�ĵ��붯��
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

	public void setItemClickListener(ItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

}
