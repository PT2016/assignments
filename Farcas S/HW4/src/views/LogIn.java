package views;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LogIn extends Frame{
	
	JTextField userName = new JTextField("UserName");
	JButton submit = new JButton("Submit");
	
	public LogIn(String title){
		super(title);
		userName.setPreferredSize(new Dimension(100, 20));
		contentPanel.add(userName);
		contentPanel.add(submit);
		setVisible(true);
	}
	
	public JTextField getUserName() {
		return userName;
	}

	public void setSubmitButtonActionListener(ActionListener a) {
		submit.addActionListener(a);
	}
	
}