package controllers;

import model.Bank;
import utilities.IllegalProcedureException;
import view.BankView;
import view.LoginView;

public class Main {

	public static void main(String[] args) throws IllegalProcedureException{
	
		BankView bankView = new BankView();
		Bank bank = new Bank();
		
		bank = bank.writeAccountsData();
		BankController bc = new BankController(bankView, bank);
		
		
		
	}
}
