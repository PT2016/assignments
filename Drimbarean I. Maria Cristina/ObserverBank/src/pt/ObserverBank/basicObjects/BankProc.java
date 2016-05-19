package pt.ObserverBank.basicObjects;

/**
 * add/remove persons, add/remove holder associated accounts, read/write accounts data, report generators
 *  Specify the pre and post conditions for the interface methods.
 * @author Chiti
 *
 */
public interface BankProc {
/**
 * Adds a new client
 * @param id
 * @param name
 * @pre  name!=NULL && id>0
 * @post getSize() == getSize()@pre + 1
 */
public void addPerson(int id,String name);
/**
 * Removes a client that exists
 * @param name
 * @pre name!=NULL && id>0
 * @post getSize() == getSize()@pre - 1
 */
public void removePerson(int id,String name);
/**
 * Adds a account 
 * @param person
 * @param sum
 * @param type
 * @pre person!=NULL && sum>0 
 * @post getSize() == getSize()@pre + 1
 */
public void addAccount(Person person,double sum,AccountType type);
/**
 * Removes an existing account
 * @param person
 * @param accountId
 * @pre person!=NULL && accountId>0
 * @post getSize() == getSize()@pre -1
 */
public void removeAccount(Person person,int accountId,AccountType type);
/**
 * Withdraw from an account
 * @param person
 * @param accountId
 * @param sum
 * @pre person!=null && acountId>=0 && sum>0
 * @post getBalance() == getBalance()@pre-sum
 */
public void withdraw(Person person,int accountId,double sum);
/**
 * Apply interest only if saving account
 * @param person
 * @param accountId
 * @param interest
 * @param type
 * @pre person!=null && acountId>=0 && interest>0
 */
public void applyInterest(Person person,int accountId,double interest);
/**
 * Deposit money in accounts
 * @param person
 * @param accountId
 * @param sum
 * @param type
 * @pre person!=null && acountId>=0 && interest>0
 * @post getBalance() == getBalance()@pre+sum
 */
public void deposit(Person person,int accountId,double sum);
}