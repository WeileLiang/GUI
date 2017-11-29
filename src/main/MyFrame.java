package main;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import adapter.NotifyListener;
import panels.ChoicePanel;
import panels.ChoicePanel.ItemClickListener;
import panels.CraftOperationPanel;
import panels.LoginPanel;
import panels.OperationPanel;
import views.ShadePanel;

public class MyFrame extends JFrame {
	public static int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.7);
	public static int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);

	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setTitle("");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private JLayeredPane layeredPane;
	// 灰色渐变背景
	private ShadePanel shadePanel = new ShadePanel();
	// 登录界面
	private LoginPanel loginPanel = new LoginPanel();
	// 操作选择界面
	private ChoicePanel choicePanel = new ChoicePanel();

	private OperationPanel operationPanel;

	public MyFrame() {

		initViews();
		measureAndLayout();
		setListeners();
	}

	private void initViews() {
		// TODO Auto-generated method stub

	}

	private void measureAndLayout() {
		setLayout(null);
		shadePanel.setBounds(0, 0, WIDTH, HEIGHT);

		int[] loginXY = computeXY(WIDTH, HEIGHT, loginPanel.getWidth(), loginPanel.getHeight());
		loginPanel.setBounds(loginXY[0], loginXY[1], loginPanel.getWidth(), loginPanel.getHeight());

		choicePanel.setBounds(0, 0, choicePanel.getWidth(), choicePanel.getHeight());

		layeredPane = getLayeredPane();

		layeredPane.add(shadePanel, 0);
		layeredPane.add(choicePanel, 0);
		layeredPane.add(loginPanel, 0);
		// add(loginPanel);
		// add(choicePanel);
		// add(shadePanel);
	}

	private void setListeners() {
		loginPanel.setNotifyListener(new NotifyListener() {

			public void notifyParent(int signalType) {
				// TODO Auto-generated method stub
				switch (signalType) {
				case LoginPanel.LOGIN_EXIT_SIGNAL:

					choicePanel.doAlphaAnim(ChoicePanel.ANIM_IN);
					break;

				default:
					break;
				}
			}
		});

		// 点击其中一个操作选项后就加载对应的操作详情页
		choicePanel.setItemClickListener(new ItemClickListener() {

			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				layeredPane.remove(choicePanel);
				// choicePanel.setVisible(false);
				// repaint();
				addOperationPanel(position);
			}
		});

	}

	private void addOperationPanel(int position) {
		if (operationPanel == null) {
			operationPanel = new CraftOperationPanel(ChoicePanel.OPERATION_NAMES[position]);
			operationPanel.setBounds(0, 0, operationPanel.getWidth(), operationPanel.getHeight());
			layeredPane.add(operationPanel, 0);
			operationPanel.setNotifyListener(new NotifyListener() {

				@Override
				public void notifyParent(int signalType) {
					// TODO Auto-generated method stub
					layeredPane.remove(operationPanel);
					// operationPanel.setVisible(false);
					// repaint();
					readdChoicePanel();
					// System.out.println(123);
				}
			});

		} else {
			readdOperationPanel();
			// operationPanel.setVisible(true);
		}

	}

	public void readdOperationPanel(){
		layeredPane.add(operationPanel,0);
		operationPanel.doReboundSlideInAnim();
	}
	
	public void readdChoicePanel() {
		layeredPane.add(choicePanel, 0);
		// choicePanel.setVisible(true);
		choicePanel.doAlphaAnim(ChoicePanel.ANIM_IN);
	}

	public static int[] computeXY(int parentWidth, int parentHeight, int childWidth, int childHeight) {
		int x = (parentWidth - childWidth) / 2;
		int y = (parentHeight - childHeight) / 2;

		return new int[] { x, y };
	}

}
