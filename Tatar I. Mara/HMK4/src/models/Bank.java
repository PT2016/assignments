package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.Serializer;

public class Bank implements BankProc,Observer,Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID =  577739697471859697L;
	private static double minStartSum=10;
    private static double comission=0.3;
    private static double increaseSum=1;
    private static double accountsNoLimit=5;
    private String report;

	private HashMap<Person,ArrayList<Account>> bankData ;
	public Bank(){
		bankData=new HashMap<>();
		
		
	}
	@Override
	public void addPerson(Person person) {
		assert (person!=null);
		assert (person.getAge()>18);
		assert (isWellFormed());
		int sizeBefore=bankData.size();
		if (person.getAge()>18){
		bankData.put(person, new ArrayList<Account>());
		report=report+person.getName()+" was added to bank.\n";
		}
		else{
			report=report+person.getName()+"Minors cannot hold accounts\n";
		}
		int sizeAfter=bankData.size();
		assert (sizeAfter==sizeBefore+1);
		assert (isWellFormed());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePerson(Person person) {
		assert (person!=null);
		assert (bankData.containsKey(person));
		assert (isWellFormed());
		int sizeBefore=bankData.size();
		if (bankData.containsKey(person)){
			bankData.remove(person);
			report=report+person.getName()+" was removed from bank.\n ";
		}
		else{
			report=report+person.getName()+" person is not a client\n";
		}
		int sizeAfter=bankData.size();
		assert (sizeAfter==sizeBefore-1);
		assert (isWellFormed());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHolderAccount(Person holder, Account account) {
		assert (holder!=null);
		assert (account!=null);
		assert (isWellFormed());
	   int sizeBefore=holder.getNumberOfAccounts();
		if (bankData.containsKey(holder)){
			if (bankData.get(holder).size()==accountsNoLimit) 
				report=report+holder.getName()+" has reached max no of accounts.\n";
			else{
				if (account.sumIsInsufficient()) {
					report=report+holder.getName()+"the start deposit sum is insufficient.\n";
					return;
				}
				else{
				bankData.get(holder).add(account);
				account.addObserver(this);
				holder.setNumberOfAccounts(holder.getNumberOfAccounts()+1);
				report=report+account.getAccountID()+" was created.\n";
				}
			}
		}
		else report=report+holder.getName()+" person is not a client\n";
		// TODO Auto-generated method stub
		int sizeAfter=holder.getNumberOfAccounts();
		assert (sizeAfter==sizeBefore+1);
		assert (isWellFormed());
	}

	@Override
	public void removeHolderAccount(Person holder, Account account) {
		assert (holder!=null);
		assert (account!=null);
		assert (isWellFormed());
	   int sizeBefore=holder.getNumberOfAccounts();
		if (bankData.containsKey(holder)){
			int index=0;
			int indexOfAccount=-1;
			boolean foundAccount=false;
			for (Account a:bankData.get(holder)){
				if (account.equals(a)){
					indexOfAccount=index;
					foundAccount=true;
					
				}
				index++;
			}
			if (foundAccount==false) report=report+holder.getName()+" owning account not found\n";
			else{
				bankData.get(holder).remove(indexOfAccount);
				holder.setNumberOfAccounts(holder.getNumberOfAccounts()-1);
				report=report+account.getAccountID()+" was removed from accounts.\n ";
			}
		}
		else report=report+holder.getName()+" person is not a client\n";
		int sizeAfter=holder.getNumberOfAccounts();
		assert (sizeAfter==sizeBefore-1);
		assert (isWellFormed());
	}



	@Override
	public void generateReport() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void update(Observable o, Object arg) {
		report = report + "\n" + "An operation has been done on account: " + ((Account) o).getAccountID()
				+ ", now the amout of money is: " + ((Account) o).getAccountSum();
	

	}


	public static double getMinStartSum() {
		// TODO Auto-generated method stub
		return minStartSum;
	}

	public static double getComission() {
		return comission;
	}

	public static double getIncreaseSum() {
		// TODO Auto-generated method stub
		return increaseSum;
	}
	public String getReport(){
		return report;
	}
	public HashMap<Person,ArrayList<Account>> getBankData(){
		return bankData;
	}
	public boolean isWellFormed(){
		for (Person person:bankData.keySet()){
			if (person.getName()==null) return false;
			if (person.getOccupation()==null) return false;
			if (person.getAge()<18) return false;
			for (Account acc:bankData.get(person)){
				if (acc==null) return false;
				if (acc.getType()=="Saving")
					if (acc.getAccountSum()<10)
						return false;
				if (acc.getType()=="Spending")
					if (acc.getAccountSum()<0)
						return false;
				if (acc.getcreationDate().after(acc.getValidUntilDate())) return false;
				if (acc.getcreationDate().equals(acc.getValidUntilDate())) return false;
			}
		}
		return true;
	}

}
