package de.philipphock.lib;

import javax.swing.JTextArea;

public class JTextAreaAutoScroll extends JTextArea{

	private static final long serialVersionUID = -4731498671625142458L;
	
	@Override
	public void setText(String t) {
	
		super.setText(t);
		int length = this.getText().length();
		setCaretPosition(length);
	}

	
	public JTextAreaAutoScroll() {
		super();
	}
	public JTextAreaAutoScroll(String text) {
		super(text);
	}
}
