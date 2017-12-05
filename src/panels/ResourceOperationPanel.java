package panels;

import java.awt.Dialog.ModalityType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;

import adapter.ItemClickListener;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import utils.AnimationUtil;
import utils.FileUtil;

public class ResourceOperationPanel extends OperationPanel {

	public static final String JOBSHOPS_PATH = ".\\jobshops";

	private static String[] leftItems = { "车间详情", "车间管理" };
	// 产品的名称集合
	private List<String> itemNames;
	// 产品包含的组件集合
	private List<List<String>> machineItems;

	public ResourceOperationPanel(String tag) {
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
		initGridList();
		gridPanel = new GridPanel(itemNames);
		firstLabeltext = "添加";
	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub
		leftSidePanel.setItemClickListener(new ItemClickListener() {

			@Override
			public void onItemClick(int position) {
				// TODO Auto-generated method stub

				if (position == leftItems.length - 1)
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

		gridPanel.setGridItemClickListener(new ItemClickListener() {

			public void onItemClick(int position) {
				// TODO Auto-generated method stub
				if (inManageState) {
					gridPanel.setChosenState(position);

				} else {
					AnimationUtil.doShrinkAnima(gridPanel.getKthItem(position));
					showItemInfoDialog(position);
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

	}

	/**
	 * 读取products文件夹下的产品信息
	 */
	private void initGridList() {
		itemNames = new ArrayList<String>();
		machineItems = new ArrayList<>();

		File file = new File(JOBSHOPS_PATH);
		if (!file.exists())
			return;

		File[] jobShops = file.listFiles();

		for (int i = 0; i < jobShops.length; i++) {
			itemNames.add(jobShops[i].getName().split("\\.")[0]);
			List<String> machines = new ArrayList<>();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(jobShops[i].getAbsolutePath())));
				String line = null;
				while ((line = reader.readLine()) != null) {
					machines.add(line.trim());
				}

				machineItems.add(machines);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (reader != null)
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}

	}
	
	private void showItemInfoDialog(int position) {
		ItemInfoPanel panel = new ItemInfoPanel(itemNames.get(position), machineItems.get(position));
		JDialog dialog = new JDialog();
		dialog.setTitle(itemNames.get(position));
		dialog.setSize(panel.getWidth(), panel.getHeight());
		dialog.setLayout(null);
		panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		dialog.add(panel);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
	}
	
}
