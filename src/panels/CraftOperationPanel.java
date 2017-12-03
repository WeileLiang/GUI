package panels;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.ItemClickListener;
import jdk.internal.dynalink.beans.StaticClass;
import utils.AnimationUtil;
import utils.FileUtil;

public class CraftOperationPanel extends OperationPanel {

	public static final String JOBS_PATH = ".\\jobs";

	private static String[] leftItems = { "�������", "�������" };

	// ��������Ƽ���
	private List<String> itemNames;
	// ����ļ���·������
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

				} else {
					AnimationUtil.doShrinkAnima(gridPanel.getKthItem(position));
					FileUtil.openFile(itemPaths.get(position));
				}

			}
		});

		gridPanel.setManageItemClickListener(new ItemClickListener() {

			@Override
			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:// ����

					break;
				case 1:// ɾ��
					break;
				case 2:// ȫѡ
					gridPanel.handleAllStates(true);
					break;
				case 3:// ȫ��ѡ
					gridPanel.handleAllStates(false);
					break;
				case 4:// ��ѡ
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
					// gridPanel.hideManagePanel();
				}
				switch (position) {
				case 0:// �������

					break;
				case 1:
					gridPanel.showManagePanel();
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * ��ȡ��ǰ������ļ����ƺ��ļ�·��
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
