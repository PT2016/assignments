package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.SimpleHashMap;

public class Window {
	private MainPanel mainPanel;

	public Window() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 390);
		frame.setResizable(false);
		SimpleHashMap hashMap = new SimpleHashMap();
		mainPanel = new MainPanel(frame,hashMap);
		frame.setVisible(true);
		frame.setTitle("Dictionary");
		frame.add(mainPanel, BorderLayout.CENTER);
	}
}
