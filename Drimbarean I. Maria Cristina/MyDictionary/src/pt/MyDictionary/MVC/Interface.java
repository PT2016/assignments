package pt.MyDictionary.MVC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import pt.MyDictionary.model.Word;

public class Interface {
	private String buffer;
	
	private String[] nameColumn = {"Words"};
	private Object[][] dataColumn = {{null}};
	
	private DefaultTableModel dtm;
	
	private JFrame frame;
	private JPanel panel1,panel2,panel3,panel2a,panel2b,searchPanel;
	private JTextField searchBar;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem add,remove,save;
	
	private JTable table;
	
	private JTextPane panelText;
	
	private JButton searchButton;
	private JLabel consistency;
	public Interface(){
		refresh();
	}
	
	private void refresh(){
		frame = new JFrame();
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		buildFirstPanel();
		buildSecondPanel();
		buildThirdPanel();

		frame.setTitle("Programming techniques-Assignment5-Drimbarean Maria");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setMaximumSize(new Dimension(200, 150));
		frame.setMinimumSize(new Dimension(800,600));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setBackground(Color.pink);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	private void buildFirstPanel(){
		panel1 = new JPanel();
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.CYAN);
		menu = new JMenu("Actions");
		add = new JMenuItem("Add word");
		add.setBackground(Color.CYAN);
		remove = new JMenuItem("Remove word");
		remove.setBackground(Color.CYAN);
		save = new JMenuItem("Save");
		save.setBackground(Color.CYAN);
		
		menu.setMnemonic(KeyEvent.VK_A);
		add.setMnemonic(KeyEvent.VK_A);
		remove.setMnemonic(KeyEvent.VK_S);
		save.setMnemonic(KeyEvent.VK_E);
		
		frame.getContentPane().add(panel1);
		panel1.setBackground(Color.PINK);
		panel1.add(menuBar);
		menuBar.add(menu);
		menu.add(add);
		menu.add(remove);
		menu.add(save);
	}
	protected void buildSecondPanel(){

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.YELLOW); 
		
		panel2.removeAll();
		panel2.revalidate();
		panel2.repaint();
		
		panel2a = new JPanel();
		panel2b = new JPanel();
		frame.getContentPane().add(panel2);
		panel2.add(panel2a);
		panel2.add(panel2b);
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		panel2b.add(searchPanel);
		
		// A
		table = new JTable();
		dtm = new DefaultTableModel(dataColumn,nameColumn){
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		dtm.removeRow(0);
		table = new JTable(dtm);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setMinimumSize(new Dimension(300,400));
		scrollpane.setPreferredSize(new Dimension(300,400));
		panel2a.add(scrollpane);
		
		// B
		panel2b.setLayout(new BoxLayout(panel2b, BoxLayout.PAGE_AXIS));
		
		searchBar = new JTextField();
		searchBar.setColumns(20);
		searchButton = new JButton("Search");
		searchButton.setBackground(Color.CYAN);
		panelText = new JTextPane();
		panelText.setPreferredSize(new Dimension(300,300));
		//panou2b.add(searchBar);
		//panou2b.add(searchButton);
		searchPanel.add(searchBar);
		searchPanel.add(searchButton);
		panel2b.add(panelText);
		
	}
	protected void updateList(){
		
		panel2a.removeAll();
		panel2a.revalidate();
		panel2a.repaint();
		
		table = new JTable();
		dtm = new DefaultTableModel(dataColumn,nameColumn){
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		dtm.removeRow(0);
		table = new JTable(dtm);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setMinimumSize(new Dimension(300,400));
		scrollpane.setPreferredSize(new Dimension(300,400));
		panel2a.add(scrollpane);
	}
	private void buildThirdPanel(){
		panel3 = new JPanel();
		panel3.setBackground(Color.PINK);
		consistency = new JLabel("Consistency: ");
		panel3.add(consistency);
		frame.getContentPane().add(panel3);
	}
///Action Listeners///
	protected void ForAddActionListener(ActionListener action){
		add.addActionListener(action);
	}
	protected void ForRemoveActionListener(ActionListener action){
		remove.addActionListener(action);
	}
	protected void ForSearchActionListener(ActionListener action){
		searchButton.addActionListener(action);
	}
	protected void ForSaveActionListener(ActionListener action){
		save.addActionListener(action);
	}
	
	protected void addMause(){
	table.addMouseListener(new MouseAdapter() {
		@Override
	    //public void mouseClicked(MouseEvent me) {
		public void mousePressed(MouseEvent me) {
	        int row = table.rowAtPoint(me.getPoint());
	        int col = table.columnAtPoint(me.getPoint());
	        if (row >= 0 && col >= 0 && me.getClickCount() == 2) {
	            buffer = new String((String)dtm.getValueAt(row, col));
	            setSearchBar(buffer);
	        }
	    }
	});};
//getters and setters//
	/**
	 * @return the nameColumn
	 */
	public String[] getNameColumn() {
		return nameColumn;
	}

	/**
	 * @param nameColumn the nameColumn to set
	 */
	public void setNameColumn(String[] nameColumn) {
		this.nameColumn = nameColumn;
	}

	/**
	 * @return the dataColumn
	 */
	public Object[][] getDataColumn() {
		return dataColumn;
	}

	/**
	 * @param dataColumn the dataColumn to set
	 */
	public void setDataColumn(Object[][] dataColumn) {
		this.dataColumn = dataColumn;
	}

	/**
	 * @return the searchBar
	 */
	public JTextField getSearchBar() {
		return searchBar;
	}

	/**
	 * @param searchBar the searchBar to set
	 */
	public void setSearchBar(String searchBar) {
		this.searchBar.setText(searchBar);
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the panelText
	 */
	public JTextPane getPanelText() {
		return panelText;
	}

	/**
	 * @param panelText the panelText to set
	 */
	public void setPanelText(String panelText) {
		this.panelText.setText(panelText);
	}

	/**
	 * @return the searchButton
	 */
	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * @param searchButton the searchButton to set
	 */
	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	/**
	 * @return the consistency
	 */
	public JLabel getConsistency() {
		return consistency;
	}

	/**
	 * @param consistency the consistency to set
	 */
	public void setConsistency(String consistency) {
		this.consistency.setText(consistency);
	}
	protected void addWord(Word c){
		
		Object[] i = {c.getWord()};
		dtm.addRow(i);
	}
	
	protected void addWord(String c){
		
		Object[] i = {c};
		dtm.addRow(i);}
}
