package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Operations;
import Model.Polynomial;

public class MainWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame f = new JFrame();

	private JPanel panel1, panel2, panel3, panel4;
	private JButton addButt, subButt, divButt, mulButt, operations;
	private JLabel pOfX, qOfX;
	private JTextField pol1, pol2, result;
	private JButton testButton;
	private JButton derivativeButt, integralButt, evaluateButt, defIntegralButt;
	private JButton clean;

	public MainWindow() {

		f.setTitle("Polynomials");

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new GridBagLayout());
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER));

		operations = new JButton("Operations:");
		operations.setPreferredSize(new Dimension(100, 50));
		panel1.add(operations);

		addButt = new JButton("+");
		addButt.addActionListener(this);
		addButt.setPreferredSize(new Dimension(50, 50));
		addButt.setBackground(Color.cyan);
		panel1.add(addButt);
		subButt = new JButton("-");
		subButt.addActionListener(this);
		subButt.setPreferredSize(new Dimension(50, 50));
		subButt.setBackground(Color.cyan);
		panel1.add(subButt);
		divButt = new JButton("/");
		divButt.addActionListener(this);
		divButt.setPreferredSize(new Dimension(50, 50));
		divButt.setBackground(Color.cyan);
		panel1.add(divButt);
		mulButt = new JButton("*");
		mulButt.addActionListener(this);
		mulButt.setPreferredSize(new Dimension(50, 50));
		mulButt.setBackground(Color.cyan);
		panel1.add(mulButt);

		derivativeButt = new JButton("Find derivative");
		derivativeButt.setBackground(Color.cyan);
		derivativeButt.addActionListener(this);
		integralButt = new JButton("Find indefinite integral");
		integralButt.setBackground(Color.cyan);
		integralButt.addActionListener(this);
		defIntegralButt = new JButton("Find definite integral");
		defIntegralButt.setBackground(Color.cyan);
		defIntegralButt.addActionListener(this);
		evaluateButt = new JButton("Evaluate");
		evaluateButt.setBackground(Color.cyan);
		evaluateButt.addActionListener(this);

		clean = new JButton("Press here to clean");
		clean.addActionListener(this);
		panel4.add(clean);

		// panel3.add(derivativeButt);
		// panel3.add(integralButt);
		// panel3.add(defIntegralButt);
		// panel3.add(evaluateButt);

		GridBagConstraints constr = new GridBagConstraints();
		constr.gridx = 0;
		constr.gridy = 0;
		panel3.add(derivativeButt, constr);
		constr.gridx = 2;
		constr.gridy = 0;
		panel3.add(integralButt, constr);
		constr.gridx = 0;
		constr.gridy = 1;
		panel3.add(defIntegralButt, constr);
		constr.gridx = 1;
		constr.gridy = 1;
		panel3.add(evaluateButt, constr);

		pOfX = new JLabel();
		pOfX.setIcon(new ImageIcon("Images/firstPol.jpg"));
		qOfX = new JLabel();
		qOfX.setIcon(new ImageIcon("Images/secondPol.jpg"));

		GridBagConstraints c = new GridBagConstraints();
		pol1 = new JTextField("                                       ");
		pol2 = new JTextField("                                       ");
		result = new JTextField("                                       ");
		// pol1.addActionListener(this);
		// pol2.addActionListener(this);
		testButton = new JButton("The result is:");
		c.gridx = 0;
		c.gridy = 0;
		panel2.add(pOfX, c);
		c.gridx = 1;
		c.gridy = 0;
		panel2.add(pol1, c);
		c.gridx = 0;
		c.gridy = 1;
		panel2.add(qOfX, c);
		c.gridx = 1;
		c.gridy = 1;
		panel2.add(pol2, c);
		c.gridx = 0;
		c.gridy = 3;
		panel2.add(testButton, c);
		c.gridx = 1;
		c.gridy = 3;
		panel2.add(result, c);
		validate();
		JOptionPane.showMessageDialog(f,
				"Please enter the coefficients of the polynomials, separated by space! If a degree lower than the maximum is missing, please enter 0 instead! ");

		f.getContentPane().setLayout(new BorderLayout());
		f.add(panel1, BorderLayout.NORTH);
		f.add(panel2, BorderLayout.CENTER);
		f.add(panel3, BorderLayout.EAST);
		f.add(panel4, BorderLayout.SOUTH);

		f.setVisible(true);
		f.setSize(600, 400);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent e) {

		int[] firstPolCoeff, secondPolCoeff;
		Operations op = new Operations();
		firstPolCoeff = getPolynomial(pol1);
		secondPolCoeff = getPolynomial(pol2);

		if (e.getSource() == clean) {
			cleanArea();

		}
		if (e.getSource() == addButt) {
			Polynomial addResult = op.addPolynomials(new Polynomial(firstPolCoeff), new Polynomial(secondPolCoeff));
			int[] addResultCorrect = addResult.reverseCoefficients(addResult.getCoeff());
			displayPolynomial(addResultCorrect);

		}

		else if (e.getSource() == subButt) {
			Polynomial subResult = op.subtractPolynomials(new Polynomial(firstPolCoeff),
					new Polynomial(secondPolCoeff));
			int[] subResultCorrect = subResult.reverseCoefficients(subResult.getCoeff());
			displayPolynomial(subResultCorrect);
		}

		else if (e.getSource() == mulButt) {
			Polynomial mulResult = op.multiplyPolynomials(new Polynomial(firstPolCoeff),
					new Polynomial(secondPolCoeff));
			int[] mulResultCorrect = mulResult.reverseCoefficients(mulResult.getCoeff());
			displayPolynomial(mulResultCorrect);
		}

		else if (e.getSource() == derivativeButt) {

			Polynomial result = op.findDerivative(new Polynomial(firstPolCoeff));
			int[] derivativeResult = result.reverseCoefficients(result.getCoeff());
			displayPolynomial(derivativeResult);

		} else if (e.getSource() == evaluateButt) {

			String number = JOptionPane.showInputDialog(this, "Enter value for evaluation:");
			double n = Integer.parseInt(number);
			double result = op.evaluatePolynomial(new Polynomial(firstPolCoeff), n);
			JOptionPane.showMessageDialog(f,"The result is: " + result);

		}
	}

	public void displayPolynomial(int[] res) {
		Polynomial correctPolynomial = new Polynomial(res);
		result.setText(correctPolynomial.toString());
	}

	public void cleanArea() {
		pol1.setText("");
		pol2.setText("");
		result.setText("");
	}

	public int[] getPolynomial(JTextField polyn) {
		int[] pol = null;
		try {
			String text = (polyn.getText()).trim();
			String[] splitActionCommand = text.split(" ");
			pol = new int[splitActionCommand.length];

			for (int i = 0; i < splitActionCommand.length; i++) {
				pol[i] = Integer.parseInt(splitActionCommand[i]);
				System.out.println(pol[i]);
			}

		} catch (NumberFormatException exception) {
			int messageType = JOptionPane.PLAIN_MESSAGE;
			JOptionPane.showMessageDialog(null, "Please enter only integers!", "Error!", messageType);
		}
		return pol;
	}
}
