package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class BankImpl extends UnicastRemoteObject implements Bank
{

	/**
	 * Accounts data structure will hold the accounts managed by the ATM.
	 */
	private HashMap<Integer,Account> accounts;
	
	/**
	 * No arg constructor that initializes accounts and the remote security 
	 * object.
	 * 
	 * @throws RemoteException	Required by super constructor.
	 */
	protected BankImpl() throws RemoteException
	{
		super();

		Account acct;
		
		accounts = new HashMap<Integer,Account>(3);
		
		// Account 1
		acct = new Account(0);
		accounts.put(new Integer(1), acct);
		
		// Account 2
		acct = new Account(100);
		accounts.put(new Integer(2), acct);
		
		// Account 3
		acct = new Account(500);
		accounts.put(new Integer(3), acct);
		
	}

	/**
	 * Deposit the amount of money into the specified account.
	 * 
	 * @param	acctInfo	Account money is to be deposited into
	 * @param	amount		Amount to be deposited
	 * @throws	RemoteException	Required for RMI
	 */
	@Override
	public void deposit(AccountInfo acctInfo, float amount)
			throws RemoteException
	{
		Account acct = accounts.get(acctInfo.getAcctNumber());
		
		acct.deposit(amount);
	}

	/**
	 * Withdraw the amount of money from the specified account.
	 * 
	 * @param	acctInfo	Account money is to be withdrawn from
	 * @param	amount		Amount to be withdrawn
	 * @throws	RemoteException	Required for RMI
	 * @throws	InsufficientFundsException	Thrown if the account has 
	 * 										insufficient funds for the withdrawal
	 */
	@Override
	public void withdraw(AccountInfo acctInfo, float amount)
			throws RemoteException, InsufficientFundsException
	{
		Account acct = accounts.get(acctInfo.getAcctNumber());
		
		acct.withdraw(amount);
	}

	/**
	 * Return the current balance of the specified account.
	 * 
	 * @param	acctInfo	Account to get the balance of
	 * @return	The account's balance
	 * @throws	RemoteException	Required for RMI
	 */
	@Override
	public float getBalance(AccountInfo acctInfo) throws RemoteException
	{
		Account acct = accounts.get(acctInfo.getAcctNumber());
		
		return acct.getBalance();
	}

}
