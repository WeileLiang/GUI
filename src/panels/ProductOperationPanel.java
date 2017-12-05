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

public class ProductOperationPanel extends OperationPanel {

	public static final String PRODUCT_PATH = ".\\products";

	private static String[] leftItems = { "产品详情", "产品管理" };
	// 产品的名称集合
	private List<String> itemNames;
	// 产品包含的组件集合
	private List<List<String>> jobsItems;

	public ProductOperationPanel(String tag) {
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
				}
				switch (position) {
				case 0:// 产品详情

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
				case 0:// 添加

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
		jobsItems = new ArrayList<>();

		File file = new File(PRODUCT_PATH);
		if (!file.exists())
			return;

		File[] products = file.listFiles();

		for (int i = 0; i < products.length; i++) {
			itemNames.add(products[i].getName().split("\\.")[0]);
			List<String> jobs = new ArrayList<>();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(products[i].getAbsolutePath())));
				String line = null;
				while ((line = reader.readLine()) != null) {
					jobs.add(line.trim());
				}

				jobsItems.add(jobs);
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
		ItemInfoPanel panel = new ItemInfoPanel(itemNames.get(position), jobsItems.get(position));
		JDialog dialog = new JDialog();
		dialog.setTitle(itemNames.get(position));
		dialog.setSize(panel.getWidth(), panel.getHeight());
		dialog.setLayout(null);
		panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());

		panel.setItemClickListener(new ItemClickListener() {

			@Override
			public void onItemClick(int index) {
				// TODO Auto-generated method stub
				File file = new File(
						CraftOperationPanel.JOBS_PATH + File.separator + jobsItems.get(position).get(index) + ".txt");
				if (file.exists())
					FileUtil.openFile(file.getAbsolutePath());
			}
		});

		dialog.add(panel);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
	}

}
