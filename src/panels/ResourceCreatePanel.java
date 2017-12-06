package panels;

import java.util.ArrayList;
import java.util.List;

import constant.Info;

public class ResourceCreatePanel extends CreatePanel {

	public ResourceCreatePanel(List<String> names) {
		super(names);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkIllegality() {
		// TODO Auto-generated method stub
		// if(Info.get)
		if (nameField.isEmpty()) {
			errorLabel.setText("�³������Ʋ���Ϊ�գ�");
			return false;
		}

		if (Info.getProductNames(false).contains(nameField.getText())) {
			errorLabel.setText("�Ѵ��ڸó��䣡");
			return false;
		}

		if (!states.contains(true)) {
			errorLabel.setText("������ѡ��һ���豸��");
			return false;
		}

		errorLabel.setText("");
		return true;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		createType = "����";
		itemType = "�豸";

		tips = "�������³�������";
	}

	@Override
	public void setEnsureListener() {
		if (notifyListener != null && checkIllegality()) {
			notifyListener.notifyParent(0);
		}
	}

	public CreatedJobshop getNewJobshop() {
		List<String> jobs = new ArrayList<>();
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i))
				jobs.add(Info.getJobNames().get(i));
		}

		return new CreatedJobshop(nameField.getText(), jobs);
	}

	class CreatedJobshop {
		String name;
		List<String> machines;

		public CreatedJobshop(String name, List<String> machines) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.machines = machines;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<machines.size();i++)
				sb.append(machines.get(i)).append('\n');
			
			return sb.toString().trim();
		}
	}

}
