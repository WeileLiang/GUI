package panels;

import java.util.Arrays;
import java.util.List;

public class ProductOperationPanel extends OperationPanel {

	private static String[] leftItems = { "��Ʒ����", "��Ʒ����" };
	// ��Ʒ�����Ƽ���
	private List<String> itemNames;
	// ��Ʒ��Ϣ���ļ�·��
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
