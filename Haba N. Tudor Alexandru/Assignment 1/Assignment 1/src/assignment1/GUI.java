package assignment1;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	PolynomialOperation poly;
	int maximum;

	private JButton exit = new JButton("Exit");
	private JButton show1 = new JButton("Show polynom 1");
	private JButton show2 = new JButton("Show polynom 2");
	private JButton show3 = new JButton("Show polynom 3");
	private JButton add = new JButton("Addition");
	private JButton subs = new JButton("Substraction");
	private JButton multi = new JButton("Multiplication");
	private JButton derivation = new JButton("Derivation");
	private JButton integrate = new JButton("Integrate");
	private JButton division = new JButton("Division");
	private JButton clear = new JButton("Clear");
	private JFrame frame = new JFrame("Result");
	private JTextArea text = new JTextArea();
	private JPanel panel = new JPanel();

	public GUI(PolynomialOperation poly) {
		panel.setLayout(new GridLayout(6, 2));
		this.poly = poly;
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.add(show1);
		panel.add(show2);
		panel.add(add);
		panel.add(subs);
		panel.add(multi);
		panel.add(show3);
		panel.add(derivation);
		panel.add(integrate);

		panel.add(division);
		panel.add(clear);
		panel.add(exit);
		panel.add(text);
		showPoly1();
		showPoly2();
		add();
		subs();
		multi();
		showPoly3();
		derivation();
		integrate();
		exit();
		text.setEditable(false);
		System.out.println("");
		text.setBackground(Color.GRAY);
		clear();

	}

	private void writePoly(int[] polynom, int size) {

		for (int j = size; j >= 0; j--) {
			if (j != 0)
				if (polynom[j] >= 0) {
					text.append(polynom[j] + "x^" + j + "+");
				} else {
					text.append(polynom[j] + "x^" + j + "-");
				}
			else {
				text.append(polynom[j] + j + "=0\n");
			}
		}
	}

	private void clear() {
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				text.setText("");

			}
		});
	}

	private void exit() {
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
	}

	private void showPoly1() {
		show1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("Polynomial 1:");
				writePoly(poly.getP1(), poly.getSize1());
			}
		});
	}

	private void showPoly2() {
		show2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("Polynomial 2:");
				writePoly(poly.getP2(), poly.getSize2());
			}
		});
	}

	private void add() {
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				poly.addition();
			}
		});
	}

	private void subs() {
		subs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				poly.substraction();
			}
		});
	}

	private void multi() {
		multi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				maximum = poly.getSize1() + poly.getSize2();
				poly.multiplication();
			}
		});
	}

	private void showPoly3() {
		show3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				maximum = Math.max(poly.getSize1(), poly.getSize2());
				writePoly(poly.getPres(), maximum);
			}
		});
	}

	private void derivation() {
		derivation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				poly.derivate();
				int i;
				int polynom[] = poly.getP1();
				for (int j = poly.getSize1(); j > 0; j--) {
					i = j - 1;
					if (j != 1)
						if (polynom[j] >= 0) {
							i = j - 1;
							System.out.print(polynom[j] + "x^" + j + "+");
							text.append(polynom[j] + "x^" + i + "+");
						} else {
							System.out.print(polynom[j] + "x^" + j + "-");
							text.append(polynom[j] + "x^" + i + "-");
						}
					else {
						System.out.print(polynom[j] + j + "=0\n");
						text.append(polynom[j] + i + "=0\n");
					}
				}

			}
		});
	}

	private void integrate() {
		integrate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				poly.integrate();
				double m[] = poly.getM();
				for (int j = poly.getSize1() + 1; j >= 2; j--) {
					text.append(m[j - 1] + "x^" + j + "+");
				}
			}
		});
	}
}
