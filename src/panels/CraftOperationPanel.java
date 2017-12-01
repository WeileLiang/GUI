package panels;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.ItemClickListener;
import utils.AnimationUtil;
import utils.FileUtil;

public class CraftOperationPanel extends OperationPanel {

	private static final String JOBS_PATH = ".\\jobs";

	private static String[] leftItems = { "组件详情", "导入组件", "管理组件" };

	// 组件的名称集合
	private List<String> itemNames;
	// 组件文件的路径集合
	private List<String> itemPaths;

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
	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub
		gridPanel.setGridItemClickListener(new ItemClickListener() {

			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				if (inManageState) {
					gridPanel.setChosenState(position);
				} else{
					AnimationUtil.doShrinkAnima(gridPanel.getKthItem(position));
					FileUtil.openFile(itemPaths.get(position));
				}

			}
		});

		leftSidePanel.setItemClickListener(new ItemClickListener() {

			@Override
			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				if (position == leftItems.length - 1)
					inManageState = true;
				else {
					inManageState = false;
					gridPanel.clearChosenStates();
//					gridPanel.hideManagePanel();
				}
				switch (position) {
				case 0:// 组件详情

					break;
				case 2:
					gridPanel.showManagePanel();
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
		File jobsFile = new File(JOBS_PATH);

		File[] files = jobsFile.listFiles();
		itemNames = new ArrayList<>();
		itemPaths = new ArrayList<>();

		for (File file : files) {
			itemNames.add(file.getName().split("\\.")[0]);
			itemPaths.add(file.getAbsolutePath());
		}

	}

}
