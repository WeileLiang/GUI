package views;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class HintTextField extends JTextField implements FocusListener{

	private String tips="";
	public HintTextField(){
		super();
		addFocusListener(this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				setFocusable(true);
				requestFocus();
				System.out.println(123);
			}
		});
	}

	public void setTips(String tips) {
		this.tips = tips;
		setFocusable(false);
		setText(tips);
		setForeground(Color.LIGHT_GRAY);
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(getText().equals(tips)) {
			setForeground(Color.BLACK);
			setText("");
		} 
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(getText().equals("")) {
			setForeground(Color.lightGray);
			setText(tips);
		}
	}
}
