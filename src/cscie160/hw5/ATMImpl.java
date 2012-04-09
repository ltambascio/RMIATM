package cscie160.hw5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * Remote implementation class for an ATM.
 * 
 * @author	Larry Tambascio
 * @version	1.1
 */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
	/**
	 * Accounts data structure will hold the accounts managed by the ATM.
	 */
	private HashMap<Integer,Account> accounts;

	/**
	 * No arg constructor.
	 * 
	 * @throws	RemoteException		Potentially thrown by parent class
	 */
	protected ATMImpl() throws RemoteException
	{
		super();

		Account acct = new Account();
		
		accounts = new HashMap<Integer,Account>();
		
		// Account 1
		acct.setBalance(0);
		accounts.put(1, acct);
		
		// Account 2
		acct = new Account();
		acct.setBalance(100);
		accounts.put(2, acct);
		
		// Account 3
		acct = new Account();
		acct.setBalance(500);
		accounts.put(3, acct);
	}

	/**
	 * Deposit into the referenced account the amount of money specified.
	 * 
	 * @param	acctNum	Account number to operate on.
	 * @param	amount	Amount to deposit into the account.
	 * @throws	RemoteException	Required since it is an RMI implementation class.
	 */
	@Override
	public void deposit(int acctNum, float amount) throws RemoteException
	{
		Account acct;
		
		acct = accounts.get(acctNum);
		acct.setBalance(acct.getBalance() + amount);
	}

	/**
	 * Withdraw from the referenced account the amount of money specified.
	 * 
	 * @param	acctNum	Account number to operate on.
	 * @param	amount	Amount to withdraw from the account.
	 * @throws	RemoteException	Required since it is an RMI implementation class.
	 */
	@Override
	public void withdraw(int acctNum, float amount) throws RemoteException
	{
		Account acct;
		
		acct = accounts.get(acctNum);
		acct.setBalance(acct.getBalance() - amount);
	}

	/**
	 * Returns the current amount in the referenced account.
	 * 
	 * @param	acctNum	Account number to operate on.
	 * @return	Account balance for specified account.
	 * @throws	RemoteException	Required since it is an RMI implementation class.
	 */
	@Override
	public Float getBalance(int acctNum) throws RemoteException
	{
		Account acct;
		
		acct = accounts.get(acctNum);
		return acct.getBalance();
	}

}
