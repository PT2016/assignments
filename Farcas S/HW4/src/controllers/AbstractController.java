package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import views.*;
import models.*;

public class AbstractController {
	
	protected Frame frame;

	public AbstractController(Frame frame, boolean hasBackButton) {
		this.frame = frame;
		if (hasBackButton)
			frame.setBackButtonActionListener(new BackButtonListener());
	}

	private class BackButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FrameStack.getInstance().pop();
		}
	}	
}
