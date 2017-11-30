package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import adapter.ItemClickListener;
import adapter.NotifyListener;
import constant.Constants;
import main.MyFrame;
import utils.AnimationUtil;
import views.TransparentLabel;

public class ChoicePanel extends JPanel {

	// public static String

	public static String[] OPERATION_NAMES = { "��Ʒ����", "��Դ����", "��������", "���ȿ�ʼ" };

	// ���붯��
	public static int ANIM_IN = 0;
	// ��������
	public static int ANIM_OUT = 1;

	// ���ĳ������ѡ����л�����Ӧ�Ĳ�������ҳ
	public static int CHOIC_OVER_SIGNAL = 0;

	private float initAlpha = .0f;
	// ���뵭�������Ѿ�ִ�е�ʱ��
	private int curTime = 0;

	private int width = MyFrame.WIDTH;
	private int height = MyFrame.HEIGHT;

	// ��ť��ȡ��߶��Լ���ť��ļ��
	private int itemWidth = width / 5;
	private int itemHeight = itemWidth / 2;
	private int marginLR = width / 20;
	private int gapHorizontal = width / 35;

	// ����Ĳ������
	private int itemPosition;

	private TransparentLabel productLabel = new TransparentLabel("��Ʒ����", initAlpha);
	private TransparentLabel resourceLabel = new TransparentLabel("��Դ����", initAlpha);
	private TransparentLabel craftLabel = new TransparentLabel("��������", initAlpha);
	private TransparentLabel dispatchLabel = new TransparentLabel("���ȿ�ʼ", initAlpha);

	List<TransparentLabel> labels = Arrays.asList(productLabel, resourceLabel, craftLabel, dispatchLabel);

	private ItemClickListener itemClickListener;
	private NotifyListener notifyListener;

	public ChoicePanel() {
		initViews();
		measureAndLayout();
		setListeners();
	}

	private void measureAndLayout() {
		int y = (height - itemHeight) / 2;
		int curX = marginLR;

		for (TransparentLabel label : labels) {
			label.setBounds(curX, y, itemWidth, itemHeight);
			add(label);
			curX += itemWidth + gapHorizontal;
		}
	}

	private void initViews() {
		setSize(width, height);
		setLayout(null);
		// ����͸��
		setBackground(null);
		setOpaque(false);

		Color bgColor = new Color(Constants.WINE_RED);
		Font font = new Font("����", Font.PLAIN, 100);

		for (TransparentLabel label : labels) {
			label.setForeground(Color.WHITE);
			label.setBackground(bgColor);
			// label.setFont(font);
		}

	}

	private void setListeners() {
		for (int i = 0; i < labels.size(); i++) {
			final int position = i;
			labels.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					// ��ִ�а�ť��С�Ŵ󶯻���ִ�е�������
					AnimationUtil.doShrinkAnima(labels.get(position));

					itemPosition = position;
					doAlphaAnim(ANIM_OUT);
					// itemClickListener.onItemClick(position);

				}
			});
		}
	}

	/**
	 * ���뵭������
	 */
	public void doAlphaAnim(int animType) {
		curTime = 0;
		initAlpha = productLabel.getAlpha();
		final float targetAlpha = animType == ANIM_IN ? 1.0f - initAlpha : .0f - initAlpha;
		int delayTime = animType == ANIM_IN ? 0 : AnimationUtil.SHRINK_TIME * 2;

		final int lastTime = LoginPanel.LAST_TIME * 4 / 3;
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				float curAlpha = (float) (initAlpha + (1 - Math.cos(Math.PI / 2 * curTime / lastTime)) * targetAlpha);

				for (TransparentLabel label : labels) {
					label.setAlpha(curAlpha);
					repaint();
				}

				curTime += LoginPanel.PERIOD;
				if (curTime > lastTime) {
					timer.cancel();

					if (animType == ANIM_OUT && itemClickListener != null)
						itemClickListener.onItemClick(itemPosition);

				}
			}
		}, delayTime, LoginPanel.PERIOD);
	}

	// ���¼��ظ�Panelʱ���ø�����ť��͸����
	public void refreshUI() {
		for (TransparentLabel label : labels) {
			label.setAlpha(1.f);
		}

		repaint();
	}

	public void setItemClickListener(ItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	public void setNotifyListener(NotifyListener notifyListener) {
		this.notifyListener = notifyListener;
	}

}
