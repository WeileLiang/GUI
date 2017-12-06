package panels;

import java.util.ArrayList;
import java.util.List;

import constant.Info;

public class ProductCreatePanel extends CreatePanel {

	public ProductCreatePanel(List<String> names) {
		super(names);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkIllegality() {
		// TODO Auto-generated method stub
		// if(Info.get)
		if (nameField.isEmpty()) {
			errorLabel.setText("�²�Ʒ���Ʋ���Ϊ�գ�");
			return false;
		}

		if (Info.getProductNames(false).contains(nameField.getText())) {
			errorLabel.setText("�Ѵ��ڸò�Ʒ��");
			return false;
		}

		if (!states.contains(true)) {
			errorLabel.setText("������ѡ��һ�������");
			return false;
		}

		errorLabel.setText("");
		return true;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		createType = "��Ʒ";
		itemType = "���";

		tips = "�������²�Ʒ����";
	}

	@Override
	public void setEnsureListener() {
		if (notifyListener != null && checkIllegality()) {
			notifyListener.notifyParent(0);
		}
	}

	public CreatedProduct getNewProduct() {
		List<String> jobs = new ArrayList<>();
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i))
				jobs.add(Info.getJobNames().get(i));
		}

		return new CreatedProduct(nameField.getText(), jobs);
	}

	class CreatedProduct {
		String name;
		List<String> jobs;

		public CreatedProduct(String name, List<String> jobs) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.jobs = jobs;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<jobs.size();i++)
				sb.append(jobs.get(i)).append('\n');
			
			return sb.toString().trim();
		}
	}

}
