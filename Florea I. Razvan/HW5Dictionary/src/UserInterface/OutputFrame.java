package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

public class OutputFrame extends CustomizedFrame {

	private static final long serialVersionUID = -6654121713624766247L;
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 650;
	private static final int POSITION_X = 560;
	private static final int POSITION_Y = 50;

	public OutputFrame() {
		this.adjustFrame();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	void addComponents() {

		OutputArea area = OutputArea.getInstance();

		area.setEnabled(false);
		area.setDisabledTextColor(Color.blue);
		area.setFont(new Font(null, Font.PLAIN, 16));
		setLayout(new BorderLayout());
		add(area, BorderLayout.CENTER);
	}

	@Override
	void setTheLocation() {
		setLocation(POSITION_X, POSITION_Y);

	}

	@Override
	void setTheTitle() {
		setTitle("Dictionary of synonyms");

	}

	@Override
	void setTheSize() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);

	}

}
