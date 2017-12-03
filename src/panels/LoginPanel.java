package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import adapter.ClickedListener;
import adapter.NotifyListener;
import constant.Constants;
import main.MyFrame;
import views.PressedButton;

public class LoginPanel extends JPanel {

	public static final int LOGIN_EXIT_SIGNAL=0;
	
	private JLabel userNameLabel = new JLabel("�û���:", JLabel.CENTER);
	private JLabel passwordLabel = new JLabel("����:", JLabel.CENTER);
	private JTextField userNameField = new JTextField();
	private JTextField passwordField = new JTextField();

	private PressedButton loginBtn = new PressedButton("��¼");
	private PressedButton registerBtn = new PressedButton("ע��");

	private NotifyListener listener;

	int width = MyFrame.WIDTH / 4;
	int height = MyFrame.HEIGHT / 4;

	int itemHeight = height / 5;

	int verticalGap = 8;

	// ��ʱ��ʱ����
	public static int PERIOD = 5;
	// �˳������ĳ���ʱ��
	public static int LAST_TIME = 500;
	// �����Ѿ�ִ�е�ʱ��
	private int curTime = 0;

	public LoginPanel() {

		initViews();
		measureAndLayout();
		setListeners();
	}

	private void initViews() {
		Font font = new Font("����", Font.PLAIN, 15);
		Font font2 = new Font("����", Font.PLAIN, 17);
		userNameLabel.setFont(font);
		passwordLabel.setFont(font);
		loginBtn.setFont(font2);
		registerBtn.setFont(font2);

		loginBtn.setBackground(Color.GRAY);
		loginBtn.setForeground(Color.WHITE);
		registerBtn.setBackground(Color.GRAY);
		registerBtn.setForeground(Color.WHITE);

		// ����͸��
		setBackground(null);
		setOpaque(false);

	}

	private void measureAndLayout() {
		setSize(width, height);
		setLayout(null);
		// ��ǰ��Y����
		int curY = 0;
		int labelWidth = width / 3;
		// �����Ŀ��
		int fieldWidth = width * 2 / 3;
		userNameLabel.setBounds(0, curY, labelWidth, itemHeight);
		userNameField.setBounds(userNameLabel.getWidth(), curY, fieldWidth,
				itemHeight);
		userNameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		curY += itemHeight + verticalGap;
		passwordLabel.setBounds(0, curY, labelWidth, itemHeight);
		passwordField.setBounds(passwordLabel.getWidth(), curY, fieldWidth,
				itemHeight);
		passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		curY += itemHeight + 5 * verticalGap;
		int marginHorizontal = width / 8;
		int btnWidth = width / 3;
		loginBtn.setBounds(marginHorizontal, curY, btnWidth, itemHeight);
		registerBtn.setBounds(width - btnWidth - marginHorizontal, curY,
				width / 3, itemHeight);

		add(userNameLabel);
		add(userNameField);
		add(passwordLabel);
		add(passwordField);
		add(loginBtn);
		add(registerBtn);
	}

	long last = 0;
	int count = 0;

	private void setListeners() {
		loginBtn.setOnClickedListener(new ClickedListener() {

			public void onClick(JComponent component) {
				// TODO Auto-generated method stub
				curTime = 0;
				// ��¼�����˳�����
				final Timer timer = new Timer();
				final int initY = getY();
				final int totalDistance = MyFrame.HEIGHT - initY;
				final long start = System.currentTimeMillis();
				last = start;
				timer.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
//						long cur = System.currentTimeMillis();
//						System.out.println(count++ + " " + (cur - last));
//						last = cur;
						int curY = (int) (initY + (1 - Math.cos(Math.PI / 2
								* curTime / LAST_TIME))
								* totalDistance);
						setBounds(getX(), curY, getWidth(), getHeight());
						curTime += PERIOD;

						if (curTime >= LAST_TIME) {
							if (listener != null)
								listener.notifyParent(LOGIN_EXIT_SIGNAL);
							timer.cancel();
//							System.out.println("end:"
//									+ (System.currentTimeMillis() - start));
						}

					}
				}, 0, PERIOD);

			}
		});
	}

	public void setNotifyListener(NotifyListener listener) {
		this.listener = listener;
	}
}
