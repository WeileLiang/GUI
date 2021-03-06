package panels;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import adapter.ItemClickListener;
import constant.Info;
import utils.AnimationUtil;
import utils.FileUtil;

public class CraftOperationPanel extends OperationPanel {

	public static final String JOBS_PATH = ".\\jobs";

	private static String[] leftItems = { "组件详情", "管理组件" };

	// 组件的名称集合
	private List<String> itemNames;
//	// 组件文件的路径集合
//	private List<String> itemPaths;

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
		initRightItems();
		gridPanel = new GridPanel(itemNames);

		firstLabeltext = "导入";
	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub
		gridPanel.setGridItemClickListener(new ItemClickListener() {

			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				if (inManageState) {
					gridPanel.setChosenState(position);

				} else {
					AnimationUtil.doShrinkAnima(gridPanel.getKthItem(position));
					FileUtil.openFile(Info.JOBS_PATH+File.separator+itemNames.get(position)+".txt");
				}

			}
		});

		gridPanel.setManageItemClickListener(new ItemClickListener() {

			@Override
			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:// 导入

					break;
				case 1:// 删除
					break;
				case 2:// 全选
					gridPanel.handleAllStates(true);
					break;
				case 3:// 全不选
					gridPanel.handleAllStates(false);
					break;
				case 4:// 反选
					gridPanel.inverseSelect();
					break;
				default:
					break;
				}
			}
		});

		leftSidePanel.setItemClickListener(new ItemClickListener() {

			@Override
			public void onItemClick(int position) {
				// TODO Auto-generated method stub

				if (position == 1)
					inManageState = true;
				else {
					inManageState = false;
					gridPanel.clearChosenStates();
					gridPanel.hideManagePanel();
					repaint();
				}
				switch (position) {
				case 0:// 组件详情

					break;
				case 1:
					gridPanel.showManagePanel(firstLabeltext);
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * 获取当前的组件文件名称和文件路径
	 */
	private void initRightItems() {
		itemNames=Info.getJobNames();
	}

}
