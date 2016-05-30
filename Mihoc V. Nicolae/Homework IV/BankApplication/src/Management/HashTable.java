package Management;
import java.util.LinkedList;

public class HashTable {
	@SuppressWarnings("rawtypes")
	private LinkedList[] hashTable = new LinkedList[9973];
	private int size;
	private int totalElements;

	public HashTable(int size) {
		this.size = size;
		for (int i = 0; i < size; i++)
			hashTable[i] = new LinkedList<Account>();
		totalElements = 0;
	}

	public int getTotalElements() {
		return totalElements;
	}

	@SuppressWarnings("rawtypes")
	public LinkedList[] getAllElements() {
		return hashTable;
	}

	public boolean insert(Account a) {
		int index = a.hashFunction();
		@SuppressWarnings("unchecked")
		boolean result = hashTable[index].add(a);
		if (result == true)
			totalElements++;
		return result;
	}

	public boolean remove(Account a) {
		int index = a.hashFunction();
		boolean result = hashTable[index].remove(a);
		if (result == true) {
			totalElements--;
		}
		return result;
	}

	// is well formed if the nr of elements found in the table
	// are equal with to the number of elements stored. Also the
	// placement according to the hash function is checked
	public boolean isWellFormed() {
		int j, count = 0, i;
		Account aux;
		for (i = 0; i < size; i++) {
			j = 0;
			while (j < hashTable[i].size()) {
				aux = (Account) hashTable[i].get(j);
				if (aux.hashFunction() != i)
					return false;
				j++;
				count++;
			}
		}
		if (count != getTotalElements())
			return false;
		return true;
	}
}
