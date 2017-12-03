package panels;

import java.util.Arrays;
import java.util.List;

public class ProductOperationPanel extends OperationPanel {

	private static String[] leftItems = { "产品详情", "产品管理" };
	// 产品的名称集合
	private List<String> itemNames;
	// 产品信息的文件路径
	private List<String> itemPath;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void setListeners() {
		// TODO Auto-generated method stub

	}

}
