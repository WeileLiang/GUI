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

public class CraftOperationPanel extends OperationPanel {
	
	
	
	private String[] leftItems= {
			"组件详情","导入组件","管理组件"
	};
	
	private String[] gridItems;
	
	public CraftOperationPanel(String tag) {
		super(tag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initLeftPanel() {
		// TODO Auto-generated method stub
		leftSidePanel=new LeftSidePanel(tag, Arrays.asList(leftItems));
	}

	@Override
	public void initGridPanel() {
		// TODO Auto-generated method stub
		
	}
	
}
