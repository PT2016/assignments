package Bank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import Management.Account;
import Management.HashTable;

public class Bank implements BankProc, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashTable hashTable = new HashTable(9973);
	private FileInputStream fileIn;
	private FileOutputStream fileOut;
	private ObjectInputStream objIn;
	private ObjectOutputStream objOut;

	@Override
	public boolean addAccount(Account x) {
		int sizePre = hashTable.getTotalElements();
		assert x != null;
		assert hashTable.isWellFormed();
		boolean result = hashTable.insert(x);
		assert result;
		assert hashTable.getTotalElements() == sizePre + 1;
		assert hashTable.isWellFormed();
		return result;
	}

	@Override
	public boolean removeAccount(Account x) {
		int sizePre = hashTable.getTotalElements();
		assert x != null && hashTable.getTotalElements() > 0;
		assert hashTable.isWellFormed();
		boolean result = hashTable.remove(x);
		assert result;
		assert hashTable.getTotalElements() == sizePre - 1;
		assert hashTable.isWellFormed();
		return result;
	}

	public void writeAccount(File file) {
        int j;
        Account a;
        assert file != null;
        try {
            fileOut = new FileOutputStream(file);
        } catch (FileNotFoundException exc) {
            System.err.println(exc);
            System.exit(1);
        }
        try {
            objOut = new ObjectOutputStream(fileOut);
        } catch (IOException exc) {
            System.err.println(exc);
            System.exit(2);
        }
        try {
            for (LinkedList<?> list : hashTable.getAllElements()) {
                j = 0;
                while (j != list.size() && list != null) {
                    a = (Account) list.get(list.size() - j-1);
                    objOut.writeObject(a);
                    j++;
                }
            }
            objOut.close();
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(3);
        }
    }
	public void readAccount(File file) {
        Object obj;
        Account a;
        assert file != null;
        assert hashTable.isWellFormed();
        int error = 0;
        try {
            fileIn = new FileInputStream(file);
        } catch (FileNotFoundException exc) {
            System.err.println(exc);
            System.exit(1);
        }

        try {
            objIn = new ObjectInputStream(fileIn);
        } catch (IOException exc) {
            error = 1;
        }
        if (error == 0) {
        	try {
                while ((obj = objIn.readObject()) != null) {
                    if (obj instanceof Account) {
                        a = (Account) obj;
                        addAccount(a);
                    }
                }
            } catch (IOException exc) {
                try {
                    objIn.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                    System.exit(5);
                }
            } catch (ClassNotFoundException exc) {
            	System.out.println(exc.getLocalizedMessage());
                exc.printStackTrace();
                System.exit(4);
            } finally {
                try {
                    objIn.close();
                } catch (IOException exc) {
                    System.err.println(exc);
                    System.exit(5);
                }
            }
        }
        assert hashTable.isWellFormed();
    }

	@Override
	public Account findAccount(Account x) {
		assert x != null;
		Account acc = null;
		Iterator<?> it;
		for (LinkedList<?> list : hashTable.getAllElements()) {
			it = list.iterator();
			for (; it.hasNext();) {
				acc = (Account) it.next();
				if (x.getId() == acc.getId() && x.getClientID() == x.getClientID()) {
					return acc;
				}
			}
		}
		return null;
	}

	public Object[][] searchAccount(int id, int clientId) {
		Object[][] data = new Object[9973][8];
		assert id > 0 && clientId > 0;
		Account a = new Account(id, clientId, 0, "", "");
		Account ac;
		ac = findAccount(a);
		assert ac == findAccount(a);
		if (ac != null) {
			data[0] = new Object[] { new Integer(ac.getId()), new Integer(ac.getClientID()),
					ac.getPerson().getAddress(), ac.getPerson().getName(), new Integer(ac.getBalance()) };
		} else {
			data = null;
		}
		return data;
	}
	
	 public Object[][] reportAccount() {
	        Object[][] data = new Object[hashTable.getTotalElements()][7];
	        int i = 0, j;
	        Account aux;
	        for (LinkedList<?> list : hashTable.getAllElements()) {
	            j = 0;
	            while (j != list.size()) {
	                aux = (Account) list.get(list.size() - j - 1);
	                data[i] = new Object[]{aux.getId(),
	                            aux.toString(),
	                            aux.getClientID(), aux.getPerson().getName(),
	                             aux.getPerson().getAddress(), aux.getBalance()};
	                i++;
	                j++;
	            }
	        }
	        return data;
	    }
}
