
package main;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Person;


/**
 * @author Lorand
 *
 */
public class Bank implements BankProc, java.io.Serializable {
	
	public Hashtable<String, ArrayList<Account>> bank;
	
	public Bank(){
		bank= new  Hashtable<String, ArrayList<Account>>();
	}
	

	
	@Override
	public void readAccounts() {
		assert bank !=null;
		try{
			FileInputStream out= new FileInputStream("src/bank.ser");
			ObjectInputStream in= new ObjectInputStream(out);
			bank= (Hashtable<String, ArrayList<Account>>) in.readObject();
			in.close();
			out.close();
		}catch( IOException | ClassNotFoundException e){
			e.printStackTrace();
			
		}
		
	}

	
	@Override
	public void writeAccounts() {
		assert bank != null;
		boolean ok=false;
		try{
			FileOutputStream out= new FileOutputStream("src/bank.ser");
			ObjectOutputStream in= new ObjectOutputStream(out);
			in.writeObject(bank);
			in.close();
			out.close();
			ok=true;
		}catch( IOException e){
			e.printStackTrace();
			
		}
		
		assert ok;
		
	}

	
	@Override
	public void addAccount(Account acc) {
		assert acc.correctParam();
		
		String CNP = acc.getPerson().getCNP();
		int size = bank.size();
		int size1=0;
		if(bank.containsKey(CNP)== true){
			size1= bank.get(CNP).size();
			bank.get(CNP).add(acc);
			
		}
		else{
			ArrayList<Account> list = new ArrayList<Account>();
			size1= list.size();
			list.add(acc);
			bank.put(CNP, list);
			
		}
	}


	@Override
	public void deleteAccount(Account acc) {
		assert acc.correctParam();
		
		String CNP = acc.getPerson().getCNP();
		int size=0;
		int size1=0;
		if(bank.containsKey(CNP)==true){
			if(bank.get(CNP).size()!=0){
				size1= bank.get(CNP).size();
				bank.get(CNP).remove(acc);
			}
			else 
			{
				size= bank.size();
				bank.remove(CNP);
			}
		}
		
		assert bank.size()== size-1 || bank.get(CNP).size()== size1-1;
	}
	
	
	public Account getAccount(int acc){
		assert acc>0;
		Account a=null;
		
		Set<Map.Entry<String, ArrayList<Account>>> entrySet= bank.entrySet();
		Iterator<Map.Entry<String,ArrayList<Account>>> i= entrySet.iterator();
		
		while(i.hasNext()){
			Map.Entry<String, ArrayList<Account>> w= i.next();
			
			ArrayList<Account> list = (ArrayList<Account>) w.getValue();
			for (int j = 0;j<list.size();j++)
			{
				if (list.get(j).getAccountId() == acc)
					a = list.get(j);
			}
		}
		if (a == null)
			return null;
		assert a.correctParam();
		return a;
	}
	public  double depositMoneyIntoAccount( int Id, double m){
		
		double b=-1;
		Account a= getAccount(Id);
		if (a == null)
			return -1;
		String cnp = a.getPerson().getCNP();
	
		if (bank.containsKey(cnp))
		{
			ArrayList<Account> list = bank.get(cnp);
			for (int i = 0 ;i<list.size();i++)
			{
				if (list.get(i).getAccountId() == a.getAccountId())
				
					b= list.get(i).depositMoney(m); 
				
				
			}
		}
	
		return b;
	}



	
	@Override
	public double extractMoneyIntoAccount(int accountId, double money) {
		
		double extr=-1;
		Account a= getAccount(accountId);
		String CNP= a.getPerson().getCNP();
		if(bank.containsKey(CNP)==true){
			ArrayList<Account> c= bank.get(CNP);
			for(int i=0;i<c.size();i++)
				if(c.get(i).getAccountId()==a.getAccountId()){
				 extr= c.get(i).extractMoney(money);
					
					
				}
		}
		return extr;
	}


	public String toString(){
		String rez ="";
		
		Set<Map.Entry<String, ArrayList<Account>>> entrySet= bank.entrySet();
		Iterator<Map.Entry<String,ArrayList<Account>>> i= entrySet.iterator();
		
		while(i.hasNext()){
			Map.Entry<String, ArrayList<Account>> w= i.next();
			ArrayList<Account> ac = (ArrayList<Account>) w.getValue();
			rez += ac.get(0).getPerson().getFristName() + "  " + ac.get(0).getPerson().getLastName() + "\n" ;
			for (int j = 0;j < ac.size();j++)
				rez += "    "+ac.get(j).getAccountId() + " "+ ac.get(j).getAcountName()+ "  "+ ac.get(j).getMoneyAmount() +"\n"; 
		}
		return rez;
	}
	
	
	
	
	public int nextIdAccount() {
		int rez = 0;
		Set<Entry<String, ArrayList<Account>>> entrySet = bank.entrySet();
		for (Entry entry : entrySet)
		{
			ArrayList<Account> li = (ArrayList<Account>) entry.getValue();
			for (int i = 0;i<li.size();i++)
			{
				if (rez < li.get(i).getAccountId())
					rez = li.get(i).getAccountId();
			}
		}
		return rez;
	}

	
	
	public Vector<Vector<Object>> searchAccountA(String id, String mesaj) {
		if (mesaj=="ID"){
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<Object> row = new Vector<Object>();
		
		
		Account ac = getAccount(Integer.parseInt(id));
		if (ac == null)
			return null;
		Person p = ac.getPerson();
		row.add(p.getLastName());
		row.add(p.getFristName());
		row.add(p.getCNP());
		row.add(ac.getAccountId());
		
		row.add(ac.getMoneyAmount());
		row.add(ac.getAcountName());
		data.add(row);
		
		return data;
		
		}
		
		else if (mesaj=="CNP")
		{
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			Vector<Object> row = new Vector<Object>();
			ArrayList<Account> list = bank.get(id);
			if (list == null)
				return null;
			Person p = list.get(0).getPerson();
			int i=0;
			
				row.add(p.getLastName());
				row.add(p.getFristName());
				row.add(p.getCNP());
				row.add(list.get(i).getAccountId());
				System.out.println(list.get(i).getAccountId());
				
				row.add(list.get(i).getMoneyAmount());
				row.add(list.get(i).getAcountName());
				System.out.println(list.get(i).getAcountName());
				data.add(row);
			
			return data;
			
		}
		return null;
	}
	
	public boolean isWellFormed()
	{
		
		if (bank==null)
		{
			return false;
		}
		for (Entry<String, ArrayList<Account>> entry : bank.entrySet())
		{
			if(entry.getKey()!=null)
			{
				return false;
			}
			for(Account account : entry.getValue())
			{
				if(account.getPerson().correctParam())
				{
					return false;
				}
			}
		}
		return true;
	}
	
}
