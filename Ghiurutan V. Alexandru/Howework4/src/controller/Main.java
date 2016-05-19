package controller;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import test.TestBank;
import view.Gui;

public class Main {
	public static void main(String[] args) {
		new Gui();
		Result result = JUnitCore.runClasses(TestBank.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}
