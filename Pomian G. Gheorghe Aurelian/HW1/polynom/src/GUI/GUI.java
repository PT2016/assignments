package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import helpers.Operations;
import main.IntegerP;
import main.RealP;

public class GUI extends Operations {

	private String SA, SB, SR, SRes;
	private IntegerP A = new IntegerP();
	private IntegerP B = new IntegerP();
	private RealP ResultR = new RealP();
	private IntegerP ResultI = new IntegerP();
	private int select;

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	public static void main(String[] args) {
		GUI window = new GUI();
		window.frame.setVisible(true);
	}

	public GUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(455, 305);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(79, 11, 245, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(79, 42, 245, 20);
		frame.getContentPane().add(textField_1);

		JLabel lblPolynomA = new JLabel("Polynom A");
		lblPolynomA.setBounds(13, 14, 87, 14);
		frame.getContentPane().add(lblPolynomA);

		JLabel lblPolynomB = new JLabel("Polynom B");
		lblPolynomB.setBounds(13, 45, 91, 14);
		frame.getContentPane().add(lblPolynomB);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SA = textField.getText();
				A = toPolynomI(SA);
				SA = toStringI(A);
				textField_2.setText(SA);
			}
		});
		btnUpdate.setBounds(343, 10, 77, 23);
		frame.getContentPane().add(btnUpdate);

		JButton button = new JButton("Update");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SB = textField_1.getText();
				B = toPolynomI(SB);
				SB = toStringI(B);
				textField_4.setText(SB);
			}
		});
		button.setBounds(342, 41, 78, 23);
		frame.getContentPane().add(button);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(69, 73, 351, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(toStringI(A));

		JRadioButton rdbtnA = new JRadioButton("A -");
		JRadioButton rdbtnB = new JRadioButton("B -");
		JRadioButton rdbtnResult = new JRadioButton("Result");
		rdbtnA.setSelected(true);

		rdbtnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnA.isEnabled()) {
					select = 0;
					rdbtnA.setSelected(true);
					rdbtnB.setSelected(false);
					rdbtnResult.setSelected(false);
				}
			}
		});
		rdbtnA.setBounds(13, 70, 50, 23);
		frame.getContentPane().add(rdbtnA);

		rdbtnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnB.isEnabled()) {
					select = 1;
					rdbtnB.setSelected(true);
					rdbtnA.setSelected(false);
					rdbtnResult.setSelected(false);
				}
			}
		});
		rdbtnB.setBounds(13, 107, 50, 23);
		frame.getContentPane().add(rdbtnB);

		JButton btnAB = new JButton("A + B");
		btnAB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultR = addP(toRealP(A), toRealP(B));
				SR = toStringR(ResultR);
				textField_3.setText(SR);
				textField_6.setText("");
			}
		});
		btnAB.setBounds(146, 141, 66, 23);
		frame.getContentPane().add(btnAB);

		JButton btnAB_1 = new JButton("A - B");
		btnAB_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultR = subP(toRealP(A), toRealP(B));
				SR = toStringR(ResultR);
				textField_3.setText(SR);
				textField_6.setText("");
			}
		});
		btnAB_1.setBounds(217, 141, 61, 23);
		frame.getContentPane().add(btnAB_1);

		JButton btnAB_2 = new JButton("A * B");
		btnAB_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultR = mulP(toRealP(A), toRealP(B));
				SR = toStringR(ResultR);
				textField_3.setText(SR);
				textField_6.setText("");
			}
		});
		btnAB_2.setBounds(288, 141, 61, 23);
		frame.getContentPane().add(btnAB_2);

		JButton btnAB_3 = new JButton("A / B");
		btnAB_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultR = divP(toRealP(A), toRealP(B));
				SA = toStringR(ResultR);
				textField_3.setText(SA);
				SA = toStringR(getRemain());
				textField_6.setText(SA);
			}
		});
		btnAB_3.setBounds(359, 141, 61, 23);
		frame.getContentPane().add(btnAB_3);

		JButton btnIntegrate = new JButton("Integrate");
		btnIntegrate.setToolTipText("for [Result] polynomial");
		btnIntegrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int C = Integer.parseInt(textField_5.getText());
				if (select == 0) {
					ResultR = toRealP(A);
					ResultR = integrateR(ResultR, C);
					SA = toStringR(ResultR);
					textField_3.setText(SA);
					textField_6.setText("");
				} else if (select == 1) {
					ResultR = toRealP(B);
					ResultR = integrateR(ResultR, C);
					SB = toStringR(ResultR);
					textField_3.setText(SB);
					textField_6.setText("");
				} else {
					SRes = textField_3.getText();
					ResultR = toPolynomR(SRes);
					ResultR = integrateR(ResultR, C);
					SRes = toStringR(ResultR);
					textField_3.setText(SRes);
					textField_6.setText("");
				}
			}
		});
		btnIntegrate.setBounds(333, 243, 87, 23);
		frame.getContentPane().add(btnIntegrate);

		JButton btnDerivate = new JButton("Derivate");
		btnDerivate.setToolTipText("for [Result] polynomial");
		btnDerivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (select == 0) {
					ResultI = derivateI(A);
					SR = toStringI(ResultI);
					textField_3.setText(SR);
					textField_6.setText("");
				} else if (select == 1) {
					ResultI = derivateI(B);
					SR = toStringI(ResultI);
					textField_3.setText(SR);
					textField_6.setText("");
				} else {
					SRes = textField_3.getText();
					ResultR = toPolynomR(SRes);
					ResultR = derivateR(ResultR);
					SRes = toStringR(ResultR);
					textField_3.setText(SRes);
					textField_6.setText("");
				}
			}
		});
		btnDerivate.setBounds(13, 243, 88, 23);
		frame.getContentPane().add(btnDerivate);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(69, 175, 351, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JButton btnA = new JButton("A");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A = toPolynomI(SA);
				SA = toStringI(A);
				textField_3.setText(SA);
				textField_6.setText("");
			}
		});
		btnA.setBounds(13, 141, 61, 23);
		frame.getContentPane().add(btnA);

		JButton btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B = toPolynomI(SB);
				SB = toStringI(B);
				textField_3.setText(SB);
				textField_6.setText("");
			}
		});
		btnB.setBounds(79, 141, 61, 23);
		frame.getContentPane().add(btnB);

		textField_4 = new JTextField();
		textField_4.setText(toStringI(B));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(69, 108, 351, 20);
		frame.getContentPane().add(textField_4);

		textField_5 = new JTextField();
		textField_5.setBounds(304, 244, 26, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		textField_5.setText("0");

		JLabel lblC = new JLabel("C");
		lblC.setBounds(288, 247, 13, 14);
		frame.getContentPane().add(lblC);

		JLabel lblRemain = new JLabel("Remain");
		lblRemain.setBounds(13, 209, 50, 14);
		frame.getContentPane().add(lblRemain);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(69, 206, 351, 20);
		frame.getContentPane().add(textField_6);

		JLabel lblX = new JLabel("X");
		lblX.setBounds(132, 247, 13, 14);
		frame.getContentPane().add(lblX);

		textField_7 = new JTextField();
		textField_7.setText("0");
		textField_7.setColumns(10);
		textField_7.setBounds(146, 244, 26, 20);
		frame.getContentPane().add(textField_7);

		JButton btnEvaluate = new JButton("Evaluate");
		btnEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int X = Integer.parseInt(textField_7.getText());
				int ResI;
				float ResR;
				if (select == 0) {
					ResI = evaluateI(A, X);
					SA = "" + ResI;
					textField_3.setText(SA);
					textField_6.setText("");
				} else if (select == 1) {
					ResI = evaluateI(B, X);
					SB = "" + ResI;
					textField_3.setText(SB);
					textField_6.setText("");
				} else {
					SRes = textField_3.getText();
					ResultR = toPolynomR(SRes);
					ResR = evaluateR(ResultR, X);
					SRes = "" + ResR;
					textField_3.setText(SRes);
					textField_6.setText("");
				}
			}
		});
		btnEvaluate.setToolTipText("for [Result] polynomial");
		btnEvaluate.setBounds(174, 243, 87, 23);
		frame.getContentPane().add(btnEvaluate);

		rdbtnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnResult.isEnabled()) {
					select = 2;
					rdbtnB.setSelected(false);
					rdbtnA.setSelected(false);
					rdbtnResult.setSelected(true);
				}
			}
		});
		rdbtnResult.setBounds(6, 174, 61, 23);
		frame.getContentPane().add(rdbtnResult);
	}
}
