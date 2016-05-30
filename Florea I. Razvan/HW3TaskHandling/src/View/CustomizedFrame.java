package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class CustomizedFrame extends JFrame {

	private static final long serialVersionUID = -9160978991919686312L;
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 600;

	private OutputPanel outputPanel;
	private CommandPanel commandPanel;

	public CustomizedFrame(String title) {
		setTitle(title);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		outputPanel = new OutputPanel();
		commandPanel = new CommandPanel(outputPanel.getTaskLabels(), outputPanel.getServerLabels());
		add(outputPanel, BorderLayout.CENTER);
		add(commandPanel, BorderLayout.SOUTH);

		setVisible(true);
	}
}
