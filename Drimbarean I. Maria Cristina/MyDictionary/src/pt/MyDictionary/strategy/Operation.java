package pt.MyDictionary.strategy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class Operation {
	private JFrame frame;
	private JPanel panel;
	private JLabel wordL, synonymL, descriptionL;
	private JTextField wordF, synonymF, descriptionF;
	private JButton execute;

	public Operation() {
		frame = new JFrame();
		panel=new JPanel();
		wordL = new JLabel("Word");
		synonymL = new JLabel("Synonym");
		descriptionL = new JLabel("Description");
		wordF = new JTextField();
		synonymF = new JTextField();
		descriptionF = new JTextField();
		execute = new JButton("Done!");
		wordF.setColumns(40);
		wordF.setColumns(40);
		wordF.setColumns(40);
		
		panel.setBackground(Color.PINK);
		wordL.setBackground(Color.YELLOW);
		synonymL.setBackground(Color.YELLOW);
		descriptionL.setBackground(Color.YELLOW);
		
		execute.setBackground(Color.CYAN);
		
		frame.setTitle("Programming techniques-Assignment5-Drimbarean Maria");
		frame.setPreferredSize(new Dimension(550, 200));
		frame.setMaximumSize(new Dimension(200, 150));
		frame.setMinimumSize(new Dimension(600, 300));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setBackground(Color.pink);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * @return the wordL
	 */
	public JLabel getWordL() {
		return wordL;
	}

	/**
	 * @param wordL the wordL to set
	 */
	public void setWordL(JLabel wordL) {
		this.wordL = wordL;
	}

	/**
	 * @return the synonymL
	 */
	public JLabel getSynonymL() {
		return synonymL;
	}

	/**
	 * @param synonymL the synonymL to set
	 */
	public void setSynonymL(JLabel synonymL) {
		this.synonymL = synonymL;
	}

	/**
	 * @return the descriptionL
	 */
	public JLabel getDescriptionL() {
		return descriptionL;
	}

	/**
	 * @param descriptionL the descriptionL to set
	 */
	public void setDescriptionL(JLabel descriptionL) {
		this.descriptionL = descriptionL;
	}

	/**
	 * @return the wordF
	 */
	public String getWordF() {
		return wordF.getText();
	}
	public JTextField getWordF1() {
		return wordF;
	}
	/**
	 * @param wordF the wordF to set
	 */
	public void setWordF(JTextField wordF) {
		this.wordF = wordF;
	}

	/**
	 * @return the synonymF
	 */
	public String getSynonymF() {
		return synonymF.getText();
	}
	public JTextField getSynonymF1() {
		return synonymF;
	}
	/**
	 * @param synonymF the synonymF to set
	 */
	public void setSynonymF(JTextField synonymF) {
		this.synonymF = synonymF;
	}

	/**
	 * @return the descriptionF
	 */
	public String getDescriptionF() {
		return descriptionF.getText();
	}
	public JTextField getDescriptionF1() {
		return descriptionF;
	}
	/**
	 * @param descriptionF the descriptionF to set
	 */
	public void setDescriptionF(JTextField descriptionF) {
		this.descriptionF = descriptionF;
	}

	/**
	 * @return the execute
	 */
	public JButton getExecute() {
		return execute;
	}

	/**
	 * @param execute the execute to set
	 */
	public void setExecute(JButton execute) {
		this.execute = execute;
	}

	public void AddActionListener(ActionListener action) {
		execute.addActionListener(action);
	}
   protected abstract void addContents();
}
