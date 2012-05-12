package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ATM remote interface
 * 
 * @author	Larry Tambascio
 * @version	1.2
 */
public interface ATM extends Remote
{
	/**
	 * Deposit into the referenced account the amount of money specified.
	 * 
	 * @param	acctInfo	Account number to operate on.
	 * @param	amount	Amount to deposit into the account.
	 * @throws	RemoteException	Required since it is an RMI implementation class.
	 * @throws	SecurityException	Authentication or authorization issue
	 */
	public void deposit(AccountInfo acctInfo, float amount)
			throws RemoteException, SecurityException;
	
	/**
	 * Withdraw from the referenced account the amount of money specified.
	 * 
	 * @param	acctInfo	Account number to operate on.
	 * @param	amount		Amount to withdraw from the account.
	 * @throws	RemoteException		Required since it is an RMI implementation class.
	 * @throws	SecurityException	Authentication or authorization issue
	 * @throws	InsufficientFundsException	Thrown if there's not enough money 
	 * 										in the account or the ATM.
	 */
	public void withdraw(AccountInfo acctInfo, float amount)
			throws RemoteException, SecurityException, InsufficientFundsException;
	
	/**
	 * Returns the current amount in the referenced account.
	 * 
	 * @param	acctInfo	Account number to operate on.
	 * @return	Account balance for specified account.
	 * @throws	RemoteException		Required since it is an RMI implementation class.
	 * @throws	SecurityException	Authentication or authorization issue
	 */
	public Float getBalance(AccountInfo acctInfo)
			throws RemoteException, SecurityException;
	
	/**
	 * Transfer the amount from one account to another account.
	 * 
	 * @param	acctFromInfo	Account money is being transferred from.
	 * @param	acctToInfo		Account money is being transferred to.
	 * @param	amount			Amount to transfer.
	 * @throws	RemoteException		Required for remote calls.
	 * @throws	SecurityException	Authentication or authorization issue
	 */
	public void transfer(AccountInfo acctFromInfo, AccountInfo acctToInfo, float amount) 
			throws RemoteException, SecurityException, InsufficientFundsException;
	
	/**
	 * Register client as a listener to notifications.
	 * 
	 * @param	atmListener		Client to register
	 */
	public void registerListener(ATMListener atmListener)
			throws RemoteException, SecurityException;

}