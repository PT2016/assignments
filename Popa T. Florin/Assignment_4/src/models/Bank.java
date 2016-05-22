package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Bank implements BankProc {
	
	public HashMap<Person,ArrayList<Account>> data;
		
	public Bank(){
		data=new HashMap<>();
	}

	@Override
	public void addPerson(Person person) {
		if(person.getAge()>18){
			data.put(person, new ArrayList<Account>());
		}else{
		}
	}

	@Override
	public void removePerson(Person person) {
		
		if(data.containsKey(person)){
			data.remove(person);
		}else{
		}
	}

	@Override
	public void addHolderAccount(Person holder, Account account) {
		
		if(data.containsKey(holder)){
			data.get(holder).add(account);
			holder.setNrOfAccounts(holder.getNrOfAccounts()+1);
		}
		
	}

	@Override
	public void removeHolderAccount(Person holder, Account account) {
		
		if(data.containsKey(holder)){
			int index=0;
			int iAccount=-1;
			for(Account a:data.get(holder)){
				if(account.equals(a)){
					iAccount=index;
				}
				index++;
			}
			data.get(holder).remove(iAccount);
			holder.setNrOfAccounts(holder.getNrOfAccounts()-1);
		}
	}

}
