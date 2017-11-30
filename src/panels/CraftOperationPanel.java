package panels;

import java.util.Arrays;

import adapter.ItemClickListener;
import utils.AnimationUtil;

public class CraftOperationPanel extends OperationPanel {

	private static String[] leftItems = { "组件详情", "导入组件", "管理组件" };

	private static String[] rightItems = { "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB", "JOB",
			"JOB" };

	public CraftOperationPanel(String tag) {
		super(tag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initLeftPanel() {
		// TODO Auto-generated method stub
		leftSidePanel = new LeftSidePanel(tag, Arrays.asList(leftItems));
	}

	@Override
	public void initGridPanel() {
		// TODO Auto-generated method stub
		gridPanel = new GridPanel(Arrays.asList(rightItems));
	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub
		gridPanel.setItemClickListener(new ItemClickListener() {

			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				if (inManageState) {
					gridPanel.setChosenState(position);
				} else
					AnimationUtil.doShrinkAnima(gridPanel.getKthItem(position));

			}
		});
		
		leftSidePanel.setItemClickListener(new ItemClickListener() {
			
			@Override
			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				if(position==leftItems.length-1) inManageState=true;
				else {
					inManageState=false;
					gridPanel.clearChosenStates();
				}
				switch (position) {
				case 0://组件详情
					
					break;

				default:
					break;
				}
			}
		});
	}

}
