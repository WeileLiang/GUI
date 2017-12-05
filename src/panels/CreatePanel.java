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
import views.ItemLabel;
import views.VerticalLineSeparator;

/**
 * 用于添加产品、车间的界面
 * 
 * @author Robot
 *
 */
public class CreatePanel extends JPanel implements MouseListener{
	private int width = MyFrame.WIDTH * 3 / 4;
	private int height = MyFrame.HEIGHT * 5 / 6;
	private int gapVer = height / 33;
	private int gapHor = MyFrame.WIDTH * 7 / 6 / 48;;

	private int marginTB = height / 15;
	// Item的边长
	private int itemSize = MyFrame.WIDTH / 12;
	private int fontSize = 15;

	private JLabel allSelect = new JLabel("全选", JLabel.CENTER);
	private JLabel notSelect = new JLabel("全不选", JLabel.CENTER);
	private JLabel inverseSelect = new JLabel("反选", JLabel.CENTER);
	private JLabel ensure = new JLabel("确定", JLabel.CENTER);

	private List<JLabel> btns = Arrays.asList(allSelect, notSelect, inverseSelect, ensure);

	private List<ItemLabel> items = new ArrayList<>();
	private List<Boolean> states = new ArrayList<>();
	private List<String> itemNames = new ArrayList<>();

	// 按钮的宽高
	private int btnHeight = MyFrame.HEIGHT / 18;
	private int btnWidth = width / 4 * 2 / 3;

	private int itemCount = 5;

	private NotifyListener notifyListener;
	
	public CreatePanel(List<String> names) {
		this.itemNames = names;
		initViews();
		measureAndLayout();
		setListeners();
	}

	public CreatePanel(List<String> names, int itemSize, int itemCount, int fontSize) {
		this.itemNames = names;
		this.itemSize = itemSize;
		this.itemCount = itemCount;
		this.fontSize = fontSize;
		initViews();
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
		Font font = new Font("黑体", Font.PLAIN, 15);

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
		int btnY = marginTB * 2;
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
		separator.setBounds(width * 3 / 4-separator.getWidth(), 0, separator.getWidth(), separator.getHeight());
		add(separator);
	}

	private void setListeners() {
		for(int i=0;i<items.size();i++) {
			final int position=i;
			items.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					boolean state=states.get(position);
					states.set(position, !state);
					items.get(position).setChosenBorder(!state);
				}
			});
		}
		
		for(JLabel btn:btns)
			btn.addMouseListener(this);
		
	}
	
	/**
	 * 处理全部item的状态
	 */
	public void handleAllStates(boolean chosen) {
		for (int i = 0; i < states.size(); i++) {
			states.set(i, chosen);
			items.get(i).setChosenBorder(chosen);
		}
	}

	/**
	 * 反选
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
		JComponent component=(JComponent) e.getSource();
		AnimationUtil.doShrinkAnima(component);
		if(component==allSelect) handleAllStates(true);
		else if (component==notSelect) handleAllStates(false); 
		else if (component==inverseSelect) inverseSelect(); 
		else if(component==ensure&&notifyListener!=null) notifyListener.notifyParent(0); 
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel label=(JLabel) e.getSource();
		label.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel label=(JLabel) e.getSource();
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
	
	
	public CreatedProduct getNewProduct(){
		List<String> jobs=new ArrayList<>();
		for(int i=0;i<states.size();i++){
			if(states.get(i)) jobs.add(itemNames.get(i));
		}
		
		return new CreatedProduct("", jobs);
	}
	
	public void setNotifyListener(NotifyListener notifyListener) {
		this.notifyListener = notifyListener;
	}
	
	class CreatedProduct{
		String name;
		List<String> jobs;
		
		public CreatedProduct(String name,List<String> jobs) {
			// TODO Auto-generated constructor stub
			this.name=name;
			this.jobs=jobs;
		}
	}
}
