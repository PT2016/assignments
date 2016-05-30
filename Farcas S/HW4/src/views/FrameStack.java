package views;

import java.util.*;

public class FrameStack {

	private static FrameStack instance;
	private ArrayList<Frame> stack;

	private FrameStack() {
		stack = new ArrayList<Frame>();
	}

	public static FrameStack getInstance() {
		if (instance == null) {
			instance = new FrameStack();
		}
		return instance;
	}

	public Frame peek() {
		return stack.get(0);
	}

	public void push(Frame frame) {
		if (!stack.isEmpty())
			peek().setVisible(false);
		frame.setVisible(true);
		stack.add(0, frame);
	}

	public void pop() {
		if (!stack.isEmpty()) {
			peek().dispose();
			stack.remove(0);
			if (!stack.isEmpty()) {
				peek().setVisible(true);
			}
		}
	}
}