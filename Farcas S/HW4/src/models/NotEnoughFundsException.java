package models;

public class NotEnoughFundsException extends Exception {
	public NotEnoughFundsException(String message){
		super(message);
	}
}
