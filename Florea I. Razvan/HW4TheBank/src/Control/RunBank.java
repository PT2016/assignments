package Control;

import GraphicUserInterface.AdminFrame;
import GraphicUserInterface.ClientFrame;

public class RunBank {

	public static void main(String[] args) {

		Login login = new Login();
		String type = login.askForUserType();
		
		if(type.equals("user")){
			new ClientFrame(login.authenticateClient());
		}
		if(type.equals("administrator"))
			new AdminFrame();
		
//		Bank bank = new Bank();
//		
//		Person p1 = new User("diana", "1234");
//		Person p2 = new User("andi", "5678");
//		Person p3 = new User("vladut", "9999");
//		
//		bank.addClient(p1.getName(), p1.getPassword(), "saving");
//		bank.addClient(p2.getName(), p2.getPassword(), "saving");
//		bank.addClient(p3.getName(), p3.getPassword(), "spending");
//		
//		for(Map.Entry<Person, List<Account>> entry : bank.getBankData().entrySet()){
//			for(Account account : entry.getValue()){
//				System.out.println(account);
//			}
//		}
		
	}

}
