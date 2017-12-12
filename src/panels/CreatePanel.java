package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import adapter.NotifyListener;
import main.MyFrame;
import utils.AnimationUtil;
import views.HintTextField;
import views.ItemLabel;
import views.VerticalLineSeparator;

/**
 * ������Ӳ�Ʒ������Ľ���
 * 
 * @author Robot
 *
 */
public abstract class CreatePanel extends JPanel implements MouseListener {
	private int width = MyFrame.WIDTH * 3 / 4;
	private int height = MyFrame.HEIGHT * 5 / 6;
	private int gapVer = height / 33;
	private int gapHor = MyFrame.WIDTH * 7 / 6 / 48;;

	private int marginTB = height / 15;
	// Item�ı߳�
	private int itemSize = MyFrame.WIDTH / 12;
	private int fontSize = 12;

	private JLabel allSelect = new JLabel("ȫѡ", JLabel.CENTER);
	private JLabel notSelect = new JLabel("ȫ��ѡ", JLabel.CENTER);
	private JLabel inverseSelect = new JLabel("��ѡ", JLabel.CENTER);
	protected JLabel ensure = new JLabel("ȷ��", JLabel.CENTER);

	//���������ࣨ��Ʒ/���䣩
	protected String createType;
	//��������ࣨ���/�豸��
	protected String itemType;
	//�����ĳ�ʼ��ʾ
	protected String tips;
	// Ҫ����������
	protected HintTextField nameField = new HintTextField();
	// �������淶����ʾ
	protected JLabel errorLabel = new JLabel();

	private List<JLabel> btns = Arrays.asList(allSelect, notSelect, inverseSelect, ensure);

	private List<ItemLabel> items = new ArrayList<>();
	protected List<Boolean> states = new ArrayList<>();
	private List<String> itemNames = new ArrayList<>();

	// ��ť�Ŀ��
	private int btnHeight = MyFrame.HEIGHT / 18;
	private int btnWidth = width / 4 * 2 / 3;

	private int itemCount = 5;

	protected NotifyListener notifyListener;

	public CreatePanel(List<String> names) {
		this.itemNames = names;
		init();
		initViews();
		measureAndLayout();
		setListeners();
	}

	public CreatePanel(List<String> names, int itemSize, int itemCount, int fontSize) {
		this.itemNames = names;
		this.itemSize = itemSize;
		this.itemCount = itemCount;
		this.fontSize = fontSize;
		init();
		initViews();
		nameField.setTips("123");
		measureAndLayout();
		setListeners();
	}

	private void initViews() {
		setLayout(null);
		setSize(width, height);
		setBackground(Color.GRAY);

		for (int i = 0; i < itemNames.size(); i++) {
			items.add(new ItemLabel(itemNames.get(i), 1.f, itemSize, fontSize, 2));
			states.add(false);
		}

		Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
		Font font = new Font("����", Font.PLAIN, 15);

		nameField.setFont(font);
		nameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		errorLabel.setFont(new Font("����", Font.PLAIN, 12));
		errorLabel.setForeground(Color.WHITE);

		for (JLabel label : btns) {
			label.setOpaque(true);
			label.setBackground(Color.GRAY);
			label.setForeground(Color.WHITE);
			label.setFont(font);
			label.setBorder(border);
		}
	}

	private void measureAndLayout() {
		int btnX = width - (width / 4 - btnWidth) / 2 - btnWidth - width / 233;
		int btnY = marginTB * 3 / 2;

		nameField.setSize(btnWidth, btnHeight);
		nameField.setBounds(btnX, btnY, nameField.getWidth(), nameField.getHeight());
		nameField.setTips(tips);
		btnY += nameField.getHeight();
		errorLabel.setSize(btnWidth, btnHeight);
		errorLabel.setBounds(btnX, btnY, errorLabel.getWidth(), errorLabel.getHeight());
		add(nameField);
		add(errorLabel);

		btnY += errorLabel.getHeight() + marginTB / 3;
		for (JLabel label : btns) {
			label.setSize(btnWidth, btnHeight);
			label.setBounds(btnX, btnY, label.getWidth(), label.getHeight());
			btnY += label.getHeight() + label.getHeight() * 2 / 3;
			add(label);
		}

		int itemX = gapHor;
		int itemY = marginTB;
		for (int i = 0; i < items.size(); i++) {
			ItemLabel item = items.get(i);
			item.setBounds(itemX, itemY, item.getWidth(), item.getHeight());
			add(item);

			if (i % itemCount == itemCount - 1) {
				itemX = gapHor;
				itemY += item.getHeight() + gapVer;
			} else
				itemX += item.getWidth() + gapHor;

		}

		VerticalLineSeparator separator = new VerticalLineSeparator(3, height, Color.white);
		separator.setBounds(width * 3 / 4 - separator.getWidth(), 0, separator.getWidth(), separator.getHeight());
		add(separator);
	}

	private void setListeners() {
		for (int i = 0; i < items.size(); i++) {
			final int position = i;
			items.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					nameField.setFocusable(false);
					boolean state = states.get(position);
					states.set(position, !state);
					items.get(position).setChosenBorder(!state);
				}
			});
		}

		for (JLabel btn : btns)
			btn.addMouseListener(this);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				nameField.setFocusable(false);
			}
		});
	}

	/**
	 * ���Ϸ��ԣ������Ƿ����������ơ�������ѡ��
	 * 
	 * @return
	 */
	public abstract boolean checkIllegality();
	/**
	 * ��ʼ������createType��
	 */
	public abstract void init();
	/**
	 * Ϊȷ����ť���õ���¼�
	 */
	public abstract void setEnsureListener();
	
	/**
	 * ����ȫ��item��״̬
	 */
	public void handleAllStates(boolean chosen) {
		for (int i = 0; i < states.size(); i++) {
			states.set(i, chosen);
			items.get(i).setChosenBorder(chosen);
		}
	}

	/**
	 * ��ѡ
	 */
	public void inverseSelect() {
		for (int i = 0; i < states.size(); i++) {
			boolean curState = states.get(i);
			states.set(i, !curState);
			items.get(i).setChosenBorder(!curState);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		nameField.setFocusable(false);
		
		JComponent component = (JComponent) e.getSource();
		AnimationUtil.doShrinkAnima(component);
		if (component == allSelect)
			handleAllStates(true);
		else if (component == notSelect)
			handleAllStates(false);
		else if (component == inverseSelect)
			inverseSelect();
		else if (component == ensure)
			setEnsureListener();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel label = (JLabel) e.getSource();
		label.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel label = (JLabel) e.getSource();
		label.setBackground(null);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setNotifyListener(NotifyListener notifyListener) {
		this.notifyListener = notifyListener;
	}


}
