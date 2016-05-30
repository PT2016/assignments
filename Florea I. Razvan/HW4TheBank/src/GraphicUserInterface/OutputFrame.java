package GraphicUserInterface;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OutputFrame extends JFrame {

	private static final long serialVersionUID = -3822380646988676686L;
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_POSITION_X = 480;
	private static final int FRAME_POSITION_Y = 80;
	
	private JPanel componentsPanel;
	

	public OutputFrame(JTable table, String object) {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Information about: " + object);
		setLocation(FRAME_POSITION_X,FRAME_POSITION_Y);
		setLayout(new BorderLayout());

		createComponentsPanel(table);
		add(componentsPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	private void createComponentsPanel(JTable table) {
		componentsPanel = new JPanel();
		componentsPanel.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);
		
		componentsPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	public JFrame getOutputFrame(){
		return this;
	}
}
